/**
 * vertigo - application development platform
 *
 * Copyright (C) 2013-2022, Vertigo.io, team@vertigo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.vega.impl.authentication;

import java.util.Map;

public final class AuthenticationResult<T> {
	private boolean isRequestConsumed;
	private final Map<String, Object> claims;
	private final T rawAuthenticationResult;

	private static final AuthenticationResult NO_OP_CALLBACK_RESULT = new AuthenticationResult<>(false, Map.of(), null);

	public static <T> AuthenticationResult<T> ofConsumed() {
		return new AuthenticationResult<>(true, null, null);
	}

	public static <T> AuthenticationResult<T> ofNotConsumed() {
		return new AuthenticationResult<>(false, null, null);
	}

	public static <T> AuthenticationResult<T> ofNoOp() {
		return NO_OP_CALLBACK_RESULT;
	}

	public static <T> AuthenticationResult<T> of(final Map<String, Object> claims, final T rawCallbackResult) {
		return new AuthenticationResult<>(false, claims, rawCallbackResult);
	}

	private AuthenticationResult(final boolean isRequestConsumed, final Map<String, Object> claims, final T rawCallbackResult) {
		this.isRequestConsumed = isRequestConsumed;
		this.claims = claims;
		this.rawAuthenticationResult = rawCallbackResult;
	}

	public boolean isRequestConsumed() {
		return isRequestConsumed;
	}

	public Map<String, Object> getClaims() {
		return claims;
	}

	public T getRawCallbackResult() {
		return rawAuthenticationResult;
	}

	public void setRequestConsumed(final boolean isRequestConsumed) {
		this.isRequestConsumed = isRequestConsumed;
	}

}
