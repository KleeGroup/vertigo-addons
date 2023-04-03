package io.vertigo.vega.impl.servlet.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertigo.account.authorization.UserAuthorizations;
import io.vertigo.account.authorization.VSecurityException;
import io.vertigo.account.authorization.definitions.Authorization;
import io.vertigo.account.authorization.definitions.AuthorizationName;
import io.vertigo.account.security.UserSession;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.locale.MessageText;
import io.vertigo.core.node.Node;
import io.vertigo.core.param.Param;
import io.vertigo.core.param.ParamManager;

/**
 * Filter to check Authorization rights over some webapp routes, without intrusive codes
 * @author npiedeloup
 */
public final class AuthorizationWebFilter extends AbstractFilter {

	private static final String SECURED_DEV_MODE_PARAM_NAME = "devMode.authzLogOnly";

	private static final String ERROR_CODE_PARAM_NAME = "errorCode";

	private static final String ATZ_SPLIT_PATTERN = "\\s;\\s";

	private static final Logger LOG = LogManager.getLogger(AuthorizationWebFilter.class);
	public Boolean securedDevMode;
	private final Map<AuthorizationName[], Optional<Pattern>> filterRulesPerRoutes = new HashMap<>();
	private int errorCode = 403; //403 "Unauthorized" par défaut

	/**
	 * Nom de l'objet Session dans la session J2EE
	 */
	private static final String USER_SESSION = "io.vertigo.Session";
	private static final String USER_SESSION_ACL_KEY = "vertigo.account.authorizations";

	/** {@inheritDoc} */
	@Override
	public void doInit() {
		final FilterConfig filterConfig = getFilterConfig();
		final String errorCodeValue = filterConfig.getInitParameter(ERROR_CODE_PARAM_NAME);
		if (errorCodeValue != null) {
			errorCode = Integer.parseInt(errorCodeValue);
		}

		final Enumeration<String> authz = filterConfig.getInitParameterNames();
		while (authz.hasMoreElements()) {
			final String authName = authz.nextElement();
			if (EXCLUDE_PATTERN_PARAM_NAME.equals(authName) || ERROR_CODE_PARAM_NAME.equals(authName)) {
				continue;
			}
			final String patternStr = filterConfig.getInitParameter(authName);
			final Optional<Pattern> matchPattern = parsePattern(patternStr);
			if (matchPattern.isPresent()) {
				final AuthorizationName[] authorizationNames = Arrays.stream(authName.split(ATZ_SPLIT_PATTERN))
						.map(value -> {
							Assertion.check().isTrue(value.startsWith(Authorization.PREFIX), "Authorization names must use prefixed AuthorizationName, can't use {0} (you should add the '{1}' prefix) ", value, Authorization.PREFIX);
							return (AuthorizationName) () -> value;
						})
						.toArray(AuthorizationName[]::new);
				filterRulesPerRoutes.put(authorizationNames, matchPattern);
			}
		}
	}

	@Override
	public void doMyFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
		if (!(req instanceof HttpServletRequest) || !(res instanceof HttpServletResponse)) {
			chain.doFilter(req, res);
			return;
		}
		final UserSession userSession = getUserSession(req);
		final UserAuthorizations userAuthorizations = getUserAuthorizations(userSession);
		for (final Entry<AuthorizationName[], Optional<Pattern>> entry : filterRulesPerRoutes.entrySet()) {
			if (isUrlMatch(req, entry.getValue())) {
				//il faut ajouter la session ici, sinon on ne peut pas controler les droits
				if (userAuthorizations == null) {
					//pas d'authorization
					sendSecurityException(res, () -> "Authentified");
				} else if (!hasAuthorization(userAuthorizations, entry.getKey())) {
					sendSecurityException(res, entry.getKey());
				}
			}
		}
		chain.doFilter(req, res);

	}

	private void sendSecurityException(final ServletResponse res, final AuthorizationName... permissionNames) throws IOException {
		final String authNames = Arrays.stream(permissionNames)
				.map(a -> a.name())
				.collect(Collectors.joining(", "));
		if (getSecuredDevMode()) {
			LOG.error("securedDevMode: Not enought authorizations '" + authNames + "' => keep going, don't throw VSecurityException");
		} else {
			((HttpServletResponse) res).sendError(errorCode);
			LOG.warn("Not enought authorizations '" + authNames + "'");
			throw new VSecurityException(MessageText.of("Not enought authorizations"));//no too sharp info here : may use log
		}
	}

	private boolean hasAuthorization(final UserAuthorizations userAuthorizations, final AuthorizationName... permissionNames) {
		Assertion.check().isNotNull(permissionNames);
		//may check authorizationNames exists to prevent badly names
		//---
		return userAuthorizations.hasAuthorization(permissionNames);
	}

	private UserSession getUserSession(final ServletRequest request) {
		final HttpSession session = ((HttpServletRequest) request).getSession(false);
		return session == null ? null : (UserSession) session.getAttribute(USER_SESSION);
	}

	private UserAuthorizations getUserAuthorizations(final UserSession userSession) {
		if (userSession == null) {
			// Si il n'y a pas de session alors pas d'autorisation.
			return null;
		}
		return userSession.getAttribute(USER_SESSION_ACL_KEY);

	}

	private boolean getSecuredDevMode() {
		if (securedDevMode == null) {
			final ParamManager paramManager = Node.getNode().getComponentSpace().resolve(ParamManager.class);
			securedDevMode = paramManager.getOptionalParam(SECURED_DEV_MODE_PARAM_NAME).map(Param::getValueAsBoolean).orElse(Boolean.FALSE);
		}
		return securedDevMode.booleanValue();
	}
}
