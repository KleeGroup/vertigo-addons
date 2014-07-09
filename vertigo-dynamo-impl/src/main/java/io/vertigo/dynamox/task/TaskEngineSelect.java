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
package io.vertigo.dynamox.task;

import io.vertigo.commons.script.ScriptManager;
import io.vertigo.dynamo.database.connection.KConnection;
import io.vertigo.dynamo.database.statement.KPreparedStatement;
import io.vertigo.dynamo.database.statement.QueryResult;
import io.vertigo.dynamo.task.metamodel.TaskAttribute;
import io.vertigo.kernel.exception.VRuntimeException;
import io.vertigo.kernel.lang.Assertion;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Permet de réaliser des requêtes sur un base de données.<br>
 * <br>
 * Paramètres d'entrée : n String, Date, Boolean, Double, Integer ou DTO, DTC<br>
 * Paramètres de sorties : 1 DTO <u>ou</u> DTC <u>
 * <br>
 * Dans le cas d'un DtObject en sortie, la requête SQL doit ramenée un et un seul
 * enregistrement. Dans le cas contraire, la méthode execute() de la classe service
 * remontera un SQLException().<br>
 * <br>
 * Chaine de configuration :<br>
 * La chaine de configuration utilise les délimiteurs #NOM# pour les paramètres.
 * L'utilisation d'une valeur d'un DtObject est déclarée par #DTOBJECT.FIELD#.
 * Le paramètre de sortie n'apparaît pas dans la chaine de configuration.<br>
 * <br>
 * Un DtObject d'entrée peut être utilisé pour la sortie et est alors déclaré en
 * entrée/sortie.
 *
 * @author  FCONSTANTIN
 */
public class TaskEngineSelect extends AbstractTaskEngineSQL<KPreparedStatement> {

	/**
	 * Constructeur.
	 * @param scriptManager Manager de traitment de scripts
	 */
	@Inject
	public TaskEngineSelect(final ScriptManager scriptManager) {
		super(scriptManager);
	}

	/** {@inheritDoc} */
	@Override
	protected void checkSqlQuery(final String sql) {
		//Aucune vérification à priori.
	}

	/*
	 * Récupération de l'attribut OUT. Il doit être unique. 
	 */
	private TaskAttribute getOutTaskAttribute() {
		TaskAttribute foundedAttribute = null;
		for (final TaskAttribute attribute : getTaskDefinition().getAttributes()) {
			if (!attribute.isIn()) {
				Assertion.checkState(foundedAttribute == null, "TaskEngineSelect ne peut créer qu'un seul DtObject ou DtList !");
				foundedAttribute = attribute;
			}
		}
		if (foundedAttribute == null) {
			throw new VRuntimeException("TaskEngineSelect doit affecter au moins UN DtObject ou DtList!", null);
		}
		return foundedAttribute;

	}

	/** {@inheritDoc} */
	@Override
	protected int doExecute(final KConnection connection, final KPreparedStatement statement) throws SQLException {
		setParameters(statement);
		final TaskAttribute outAttribute = getOutTaskAttribute();

		final QueryResult result = statement.executeQuery(outAttribute.getDomain());
		setValue(outAttribute.getName(), result.getValue());
		return result.getSQLRowCount();
	}

	/** {@inheritDoc} */
	@Override
	protected final KPreparedStatement createStatement(final String sql, final KConnection connection) {
		return getDataBaseManager().createPreparedStatement(connection, sql, false);
	}
}
