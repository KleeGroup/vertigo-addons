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
package io.vertigo.dynamo.plugins.database.connection.hibernate;

import io.vertigo.dynamo.database.connection.KConnection;
import io.vertigo.dynamo.database.vendor.DataBase;
import io.vertigo.dynamo.plugins.database.connection.AbstractConnectionProviderPlugin;
import io.vertigo.dynamo.transaction.KTransaction;
import io.vertigo.dynamo.transaction.KTransactionManager;
import io.vertigo.kernel.lang.Assertion;
import io.vertigo.kernel.util.ClassUtil;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * ConnectionProvider permettant la connexion à une datasource Java.
 *
 * @author pchretien, npiedeloup
 */
abstract class JpaConnectionProviderPlugin extends AbstractConnectionProviderPlugin {
	private final KTransactionManager transactionManager;

	/**
	 * Constructeur.
	 * @param dataBaseName Nom du type de base de données
	 * @param persistenceUnit Nom de la persistenceUnit à utiliser (dans le persistence.xml)
	 */
	@Inject
	public JpaConnectionProviderPlugin(@Named("persistenceUnit") final String persistenceUnit, @Named("dataBaseName") final String dataBaseName, final KTransactionManager transactionManager) {
		super(new JpaDataBase(createDataBase(dataBaseName), Persistence.createEntityManagerFactory(persistenceUnit)));
		Assertion.checkArgNotEmpty(persistenceUnit);
		//---------------------------------------------------------------------
		this.transactionManager = transactionManager;
	}

	/** {@inheritDoc} */
	public final KConnection obtainConnection() throws SQLException {
		final EntityManager em = obtainJpaResource().getEntityManager();
		return obtainWrappedConnection(em);
	}

	/**
	 * @param em EntityManager
	 * @return KConnection sous jacente
	 * @throws SQLException Exception sql
	 */
	protected abstract KConnection obtainWrappedConnection(final EntityManager em) throws SQLException;

	/** récupère la ressource JPA de la transaction et la créé si nécessaire. */
	private JpaResource obtainJpaResource() {
		final DataBase dataBase = getDataBase();
		Assertion.checkState(dataBase instanceof JpaDataBase, "DataBase must be a JpaDataBase (current:{0}).", dataBase.getClass());
		return ((JpaDataBase) dataBase).obtainJpaResource(getCurrentTransaction());
	}

	/** récupère la transaction courante. */
	private KTransaction getCurrentTransaction() {
		return transactionManager.getCurrentTransaction();
	}

	private static DataBase createDataBase(final String dataBaseName) {
		return ClassUtil.newInstance(dataBaseName, DataBase.class);
	}
}
