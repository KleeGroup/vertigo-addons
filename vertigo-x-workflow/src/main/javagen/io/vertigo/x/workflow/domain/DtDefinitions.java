/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2017, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.x.workflow.domain;

import java.util.Arrays;
import java.util.Iterator;
import io.vertigo.dynamo.domain.metamodel.DtFieldName;

/**
 * Attention cette classe est générée automatiquement !
 */
public final class DtDefinitions implements Iterable<Class<?>> {

	/**
	 * Enumération des DtDefinitions.
	 */
	public enum Definitions {
		/** Objet de données WfActivity. */
		WfActivity(io.vertigo.x.workflow.domain.instance.WfActivity.class),
		/** Objet de données WfActivityDefinition. */
		WfActivityDefinition(io.vertigo.x.workflow.domain.model.WfActivityDefinition.class),
		/** Objet de données WfDecision. */
		WfDecision(io.vertigo.x.workflow.domain.instance.WfDecision.class),
		/** Objet de données WfMultiplicityDefinition. */
		WfMultiplicityDefinition(io.vertigo.x.workflow.domain.model.WfMultiplicityDefinition.class),
		/** Objet de données WfStatus. */
		WfStatus(io.vertigo.x.workflow.domain.instance.WfStatus.class),
		/** Objet de données WfTransitionDefinition. */
		WfTransitionDefinition(io.vertigo.x.workflow.domain.model.WfTransitionDefinition.class),
		/** Objet de données WfWorkflow. */
		WfWorkflow(io.vertigo.x.workflow.domain.instance.WfWorkflow.class),
		/** Objet de données WfWorkflowDefinition. */
		WfWorkflowDefinition(io.vertigo.x.workflow.domain.model.WfWorkflowDefinition.class),
		;

		private final Class<?> clazz;

		private Definitions(final Class<?> clazz) {
			this.clazz = clazz;
		}

		/** 
		 * Classe associée.
		 * @return Class d'implémentation de l'objet 
		 */
		public Class<?> getDtClass() {
			return clazz;
		}
	}

	/**
	 * Enumération des champs de WfActivity.
	 */
	public enum WfActivityFields implements DtFieldName<io.vertigo.x.workflow.domain.instance.WfActivity> {
		/** Propriété 'Id activity'. */
		WFA_ID,
		/** Propriété 'creation date'. */
		CREATION_DATE,
		/** Propriété 'WfWorkflow'. */
		WFW_ID,
		/** Propriété 'WfActivityDefinition'. */
		WFAD_ID,
	}

	/**
	 * Enumération des champs de WfActivityDefinition.
	 */
	public enum WfActivityDefinitionFields implements DtFieldName<io.vertigo.x.workflow.domain.model.WfActivityDefinition> {
		/** Propriété 'Id Activity Definition'. */
		WFAD_ID,
		/** Propriété 'name'. */
		NAME,
		/** Propriété 'level'. */
		LEVEL,
		/** Propriété 'WfMultiplicityDefinition'. */
		WFMD_CODE,
		/** Propriété 'WfWorkflowDefinition'. */
		WFWD_ID,
	}

	/**
	 * Enumération des champs de WfDecision.
	 */
	public enum WfDecisionFields implements DtFieldName<io.vertigo.x.workflow.domain.instance.WfDecision> {
		/** Propriété 'Id Decision'. */
		WFE_ID,
		/** Propriété 'username'. */
		USERNAME,
		/** Propriété 'choice'. */
		CHOICE,
		/** Propriété 'decision date'. */
		DECISION_DATE,
		/** Propriété 'comments'. */
		COMMENTS,
		/** Propriété 'WfActivity'. */
		WFA_ID,
	}

	/**
	 * Enumération des champs de WfMultiplicityDefinition.
	 */
	public enum WfMultiplicityDefinitionFields implements DtFieldName<io.vertigo.x.workflow.domain.model.WfMultiplicityDefinition> {
		/** Propriété 'Multiplicity code'. */
		WFMD_CODE,
		/** Propriété 'Label'. */
		LABEL,
	}

	/**
	 * Enumération des champs de WfStatus.
	 */
	public enum WfStatusFields implements DtFieldName<io.vertigo.x.workflow.domain.instance.WfStatus> {
		/** Propriété 'Code Status'. */
		WFS_CODE,
		/** Propriété 'label'. */
		LABEL,
	}

	/**
	 * Enumération des champs de WfTransitionDefinition.
	 */
	public enum WfTransitionDefinitionFields implements DtFieldName<io.vertigo.x.workflow.domain.model.WfTransitionDefinition> {
		/** Propriété 'Id Transition Definition'. */
		WFTD_ID,
		/** Propriété 'name'. */
		NAME,
		/** Propriété 'WfWorkflowDefinition'. */
		WFWD_ID,
		/** Propriété 'transitionFrom'. */
		WFAD_ID_FROM,
		/** Propriété 'transitionTo'. */
		WFAD_ID_TO,
	}

	/**
	 * Enumération des champs de WfWorkflow.
	 */
	public enum WfWorkflowFields implements DtFieldName<io.vertigo.x.workflow.domain.instance.WfWorkflow> {
		/** Propriété 'Id Workflow'. */
		WFW_ID,
		/** Propriété 'creation date'. */
		CREATION_DATE,
		/** Propriété 'itemId'. */
		ITEM_ID,
		/** Propriété 'username'. */
		USERNAME,
		/** Propriété 'user_logic'. */
		USER_LOGIC,
		/** Propriété 'WfWorkflowDefinition'. */
		WFWD_ID,
		/** Propriété 'WfStatus'. */
		WFS_CODE,
		/** Propriété 'current'. */
		WFA_ID_2,
	}

	/**
	 * Enumération des champs de WfWorkflowDefinition.
	 */
	public enum WfWorkflowDefinitionFields implements DtFieldName<io.vertigo.x.workflow.domain.model.WfWorkflowDefinition> {
		/** Propriété 'Id Workflow definition'. */
		WFWD_ID,
		/** Propriété 'name'. */
		NAME,
		/** Propriété 'date'. */
		DATE,
		/** Propriété 'startActivity'. */
		WFAD_ID,
	}

	/** {@inheritDoc} */
	@Override
	public Iterator<Class<?>> iterator() {
		return new Iterator<Class<?>>() {
			private Iterator<Definitions> it = Arrays.asList(Definitions.values()).iterator();

			/** {@inheritDoc} */
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			/** {@inheritDoc} */
			@Override
			public Class<?> next() {
				return it.next().getDtClass();
			}
		};
	}
}
