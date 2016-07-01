/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.x.impl.account;

import java.util.Iterator;

import io.vertigo.app.config.DefinitionProvider;
import io.vertigo.core.spaces.definiton.Definition;
import io.vertigo.dynamo.domain.metamodel.DataType;
import io.vertigo.dynamo.domain.metamodel.Domain;
import io.vertigo.dynamo.domain.metamodel.DtDefinition;
import io.vertigo.dynamo.domain.metamodel.DtDefinitionBuilder;
import io.vertigo.util.ListBuilder;

/**
 * Provides all the definitions used in the 'account' module. 
 * @author pchretien
 */
public final class AccountDefinitionProvider implements DefinitionProvider {

	/** {@inheritDoc} */
	@Override
	public Iterator<Definition> iterator() {
		final Domain domainAccountId = new Domain("DO_X_ACCOUNT_ID", DataType.String);
		final Domain domainAccountName = new Domain("DO_X_ACCOUNT_NAME", DataType.String);
		final Domain domainAccountEmail = new Domain("DO_X_ACCOUNT_EMAIL", DataType.String);

		final DtDefinition accountDtDefinition = new DtDefinitionBuilder("DT_ACCOUNT")
				.addIdField("ID", "id", domainAccountId, false, false)
				.addDataField("DISPLAY_NAME", "displayName", domainAccountName, false, true, true, true)
				.addDataField("EMAIL", "email", domainAccountEmail, false, true, false, false)
				.build();

		final DtDefinition accountGroupDtDefinition = new DtDefinitionBuilder("DT_ACCOUNT_GROUP")
				.addIdField("ID", "id", domainAccountId, false, false)
				.addDataField("DISPLAY_NAME", "displayName", domainAccountName, false, true, true, true)
				.build();

		return new ListBuilder<Definition>()
				.add(domainAccountId)
				.add(domainAccountName)
				.add(domainAccountEmail)
				.add(accountDtDefinition)
				.add(accountGroupDtDefinition)
				.build()
				.iterator();
	}
}
