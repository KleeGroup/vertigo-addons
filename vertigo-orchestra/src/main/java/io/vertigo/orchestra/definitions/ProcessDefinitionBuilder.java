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
package io.vertigo.orchestra.definitions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.vertigo.lang.Assertion;
import io.vertigo.lang.Builder;
import io.vertigo.util.ListBuilder;
import io.vertigo.util.MapBuilder;

/**
 * Builder d'une définition de processus Orchestra.
 *
 * @author mlaroche.
 * @version $Id$
 */
public final class ProcessDefinitionBuilder implements Builder<ProcessDefinition> {
	private final String name;
	private final String label;
	private ProcessType myType;
	private boolean myActive;
	private Optional<String> myCronExpression = Optional.empty();
	private final MapBuilder<String, String> myInitialParams = new MapBuilder<>();
	private boolean multiExecution;
	private int myRescuePeriod;

	private final Map<String, String> myMetadatas = new HashMap<>();

	/**
	 * Constructor.
	 * @param processName le nom du processus
	 * @param processLabel le libellé du processus
	 */
	ProcessDefinitionBuilder(final String processName, final String processLabel) {
		Assertion.checkArgNotEmpty(processName);
		//-----
		name = processName;
		label = processLabel;
		// active by default
		myActive = true;
	}

	/**
	 * Processus actif.
	 * @return this
	 */
	public ProcessDefinitionBuilder inactive() {
		myActive = false;
		return this;
	}

	/**
	 *
	 * @param type
	 * @return this
	 */
	public ProcessDefinitionBuilder withProcessType(final ProcessType type) {
		Assertion.checkNotNull(type);
		myType = type;
		return this;
	}

	/**
	 * Processus autorisant la multi-execution.
	 * @return this
	 */
	public ProcessDefinitionBuilder withMultiExecution() {
		multiExecution = true;
		return this;
	}

	/**
	 * Durée pendant laquelle une planification peut être restaurée (durée de validité).
	 * @param rescuePeriod la durée en secondes
	 * @return this
	 */
	public ProcessDefinitionBuilder withRescuePeriod(final int rescuePeriod) {
		myRescuePeriod = rescuePeriod;
		return this;
	}

	/**
	 * Adds params used to start the first activity.
	 * @param initialParams the params definened as a map of key-value
	 * @return this
	 */
	public ProcessDefinitionBuilder addInitialParams(final Map<String, String> initialParams) {
		myInitialParams.putAll(initialParams);
		return this;
	}

	/**
	 * Adds param used to start the first activity.
	 * @param paramName the name of the param
	 * @param paramValue the value of the param
	 * @return this
	 */
	public ProcessDefinitionBuilder addInitialParam(final String paramName, final String paramValue) {
		myInitialParams.put(paramName, paramValue);
		return this;
	}

	/**
	 * Définit l'expression cron du process.
	 * @param cronExpression l'expression cron de recurrence
	 * @return this
	 */
	public ProcessDefinitionBuilder withCronExpression(final String cronExpression) {
		Assertion.checkNotNull(cronExpression);
		// ---
		myCronExpression = Optional.of(cronExpression);
		return this;
	}

	/**
	 * Définit le informations du process.
	 * @param metadatas les métadonnées sous format JSON
	 * @return this
	 */
	public ProcessDefinitionBuilder withMetadatas(final Map<String, String> metadatas) {
		Assertion.checkNotNull(metadatas);
		// ---
		myMetadatas.putAll(metadatas);
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public ProcessDefinition build() {

		if (myType == null) {
			myType = ProcessType.SUPERVISED;
		}

		return new ProcessDefinition(
				name,
				label,
				myActive,
				myType,
				myMetadatas,
				new ProcessTriggeringStrategy(
						myCronExpression,
						myInitialParams.unmodifiable().build(),
						multiExecution,
						myRescuePeriod));
	}

}
