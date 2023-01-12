/**
 * vertigo - application development platform
 *
 * Copyright (C) 2013-2023, Vertigo.io, team@vertigo.io
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
package io.vertigo.account.authentication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.vertigo.account.authentication.MyNodeConfig.AuthentPlugin;
import io.vertigo.account.impl.authentication.PasswordHelper;
import io.vertigo.core.node.config.NodeConfig;

/**
 * Implementation standard de la gestion centralisee des droits d'acces.
 *
 * @author npiedeloup
 */
public final class TextAuthenticationManagerTest extends AbstractAuthenticationManagerTest {

	@Override
	protected NodeConfig buildNodeConfig() {
		return MyNodeConfig.config(AuthentPlugin.text, false);
	}

	@Override
	@Disabled
	@Test
	public void testLoginUsername() {
		//
	}

	@Test
	public void testCreatePassword() {
		final PasswordHelper passwordHelper = new PasswordHelper();
		final String encodedPassword = passwordHelper.createPassword("MyTruePasswordTest");
		Assertions.assertTrue(passwordHelper.checkPassword(encodedPassword, "MyTruePasswordTest"), "Good encoding");
		Assertions.assertFalse(passwordHelper.checkPassword(encodedPassword, "MyFalsePasswordTest"), "Good encoding");
	}

}
