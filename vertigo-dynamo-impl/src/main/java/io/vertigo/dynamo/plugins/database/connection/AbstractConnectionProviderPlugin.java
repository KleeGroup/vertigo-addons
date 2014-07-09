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
package io.vertigo.dynamo.plugins.database.connection;

import io.vertigo.dynamo.database.connection.KConnection;
import io.vertigo.dynamo.database.vendor.DataBase;
import io.vertigo.dynamo.impl.database.ConnectionProviderPlugin;
import io.vertigo.kernel.component.ComponentInfo;
import io.vertigo.kernel.component.Describable;
import io.vertigo.kernel.lang.Assertion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de base des fournisseurs de connexions dynamo.
 *
 * @author pchretien
 */
public abstract class AbstractConnectionProviderPlugin implements ConnectionProviderPlugin, Describable {
	/**
	 * Base de données utilisée
	 */
	private final DataBase dataBase;

	/**
	 * Constructeur.
	 * @param dataBase Type de base de données
	 */
	protected AbstractConnectionProviderPlugin(final DataBase dataBase) {
		Assertion.checkNotNull(dataBase);
		Assertion.checkNotNull(dataBase.getSqlMapping());
		Assertion.checkNotNull(dataBase.getSqlExceptionHandler());
		//---------------------------------------------------------------------
		this.dataBase = dataBase;
	}

	//----------------------GESTION DU CONNECTION PROVIDER----------------------
	/** {@inheritDoc} */
	public final DataBase getDataBase() {
		return dataBase;
	}

	@Override
	public final List<ComponentInfo> getInfos() {
		final List<ComponentInfo> componentInfos = new ArrayList<>();
		try {
			// 
			final KConnection connection = obtainConnection();
			final Connection jdbcConnection = connection.getJdbcConnection();//NOPMD
			try {
				final DatabaseMetaData metaData = jdbcConnection.getMetaData();
				//---
				componentInfos.add(new ComponentInfo("database.name", metaData.getDatabaseProductName()));
				componentInfos.add(new ComponentInfo("database.version", metaData.getDatabaseProductVersion()));

				componentInfos.add(new ComponentInfo("database.driver.name", metaData.getDriverName()));
				componentInfos.add(new ComponentInfo("database.driver.name", metaData.getDriverVersion()));
				componentInfos.add(new ComponentInfo("database.driver.url", metaData.getURL()));
			} finally {
				connection.rollback();
				connection.release();
			}
		} catch (final Exception ex) {
			//
		}
		return componentInfos;
	}
}
