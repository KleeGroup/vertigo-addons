/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2016, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
 * KleeGroup, Centre d'affaire la Boursidiere - BP 159 - 92357 Le Plessis Robinson Cedex - France
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
package io.vertigo.x.notification.webservices;

import java.util.Set;

import javax.inject.Inject;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.parsing.Parser;

import io.vertigo.app.AutoCloseableApp;
import io.vertigo.app.Home;
import io.vertigo.core.component.di.injector.DIInjector;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.x.account.Account;
import io.vertigo.x.account.AccountGroup;
import io.vertigo.x.account.AccountManager;
import io.vertigo.x.connectors.redis.RedisConnector;
import io.vertigo.x.notification.MyAppConfig;
import io.vertigo.x.notification.Notification;
import io.vertigo.x.notification.NotificationBuilder;
import io.vertigo.x.notification.NotificationManager;
import io.vertigo.x.notification.data.Accounts;
import redis.clients.jedis.Jedis;
import spark.Spark;

public final class NotificationWebServicesTest {
	private static final int WS_PORT = 8088;
	private final SessionFilter sessionFilter = new SessionFilter();
	private static AutoCloseableApp app;

	@Inject
	private AccountManager accountManager;
	@Inject
	private RedisConnector redisConnector;
	@Inject
	private NotificationManager notificationManager;

	@BeforeClass
	public static void setUp() {
		beforeSetUp();
		app = new AutoCloseableApp(MyAppConfig.vegaConfig());
	}

	@Before
	public void setUpInstance() {
		DIInjector.injectMembers(this, Home.getApp().getComponentSpace());
		//---
		try (final Jedis jedis = redisConnector.getResource()) {
			jedis.flushAll();
		}
		Accounts.initData(accountManager);
	}

	@AfterClass
	public static void tearDown() {
		if (app != null) {
			app.close();
		}
	}

	@Before
	public void preTestLogin() {
		RestAssured.registerParser("plain/text", Parser.TEXT);
		RestAssured.given()
				.filter(sessionFilter)
				.get("/test/login?id=1");
	}

	private static void beforeSetUp() {
		Spark.setPort(WS_PORT);

		//RestAsssured init
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = WS_PORT;
	}

	@Test
	public void testGetCurrentNotifications() {
		final Notification notification = new NotificationBuilder()
				.withSender("ExtensionTest")
				.withType("MSG")
				.withTitle("Message de Vertigo")
				.withTargetUrl("#keyConcept@2")
				.withContent("Lorem ipsum")
				.build();
		final Set<URI<Account>> accountURIs = accountManager.getStore().getAccountURIs(DtObjectUtil.createURI(AccountGroup.class, "100"));
		notificationManager.send(notification, accountURIs);

		RestAssured.given().filter(sessionFilter)
				.expect()
				.body("size()", Matchers.greaterThanOrEqualTo(1))
				.statusCode(HttpStatus.SC_OK)
				.log().ifError()
				.when()
				.get("/x/notification/api/messages");
	}

	@Test
	public void testGetRemoveNotifications() {
		final Notification notification = new NotificationBuilder()
				.withSender("ExtensionTest")
				.withType("MSG")
				.withTitle("Message de Vertigo")
				.withTargetUrl("#keyConcept@2")
				.withContent("Lorem ipsum")
				.build();
		final Set<URI<Account>> accountURIs = accountManager.getStore().getAccountURIs(DtObjectUtil.createURI(AccountGroup.class, "100"));
		notificationManager.send(notification, accountURIs);

		RestAssured.given().filter(sessionFilter)
				.expect()
				.body("size()", Matchers.equalTo(1))
				.statusCode(HttpStatus.SC_OK)
				.log().ifError()
				.when()
				.get("/x/notification/api/messages");

		RestAssured.given().filter(sessionFilter)
				.expect()
				.statusCode(HttpStatus.SC_NO_CONTENT)
				.log().ifError()
				.when()
				.delete("/x/notification/api/messages/" + notification.getUuid().toString());

		RestAssured.given().filter(sessionFilter)
				.expect()
				.body("size()", Matchers.equalTo(0))
				.statusCode(HttpStatus.SC_OK)
				.log().ifError()
				.when()
				.get("/x/notification/api/messages");

	}

	@Test
	public void testGetStatus() {
		RestAssured.given()
				.expect()
				.statusCode(HttpStatus.SC_OK)
				.log().ifError()
				.when()
				.get("/x/notification/status");
	}

	@Test
	public void testGetStats() {
		RestAssured.given()
				.expect()
				.statusCode(HttpStatus.SC_OK)
				.log().ifError()
				.when()
				.get("/x/notification/stats");
	}

	@Test
	public void testGetConfig() {
		RestAssured.given()
				.expect()
				.statusCode(HttpStatus.SC_OK)
				.log().ifError()
				.when()
				.get("/x/notification/config");
	}

	@Test
	public void testGetHelp() {
		RestAssured.given()
				.expect()
				.statusCode(HttpStatus.SC_OK)
				.log().ifError()
				.when()
				.get("/x/notification/help");
	}

}
