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
package io.vertigo.core.dsl.dynamic;

import io.vertigo.core.dsl.entity.Entity;
import io.vertigo.lang.Assertion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Classe permettant de créer dynamiquement une structure grammaticale.
 * Cette Classe est utilisée pour parcourir dynamiquement les modèles.
 * Rappelons qu'une structure est elle-même composée de sous structure grammaticales.
 *
 * @author  pchretien
 */
final class DynamicDefinitionImpl implements DynamicDefinitionBuilder, DynamicDefinition {
	/** Type de l'objet. */
	private final Entity entity;

	/** Nom du package. */
	private String packageName;
	//-----
	//-----BODY
	//-----
	/**
	 * Clé de la définition.
	 */
	private final String dynamicDefinitionName;
	/**
	 * Conteneur des couples (propriétés, valeur)
	 */
	private final Map<String, Object> properties = new HashMap<>();
	/**
	 * Map des (FieldName, definitionKeyList)
	 */
	private final Map<String, List<String>> definitionNamesByFieldName = new LinkedHashMap<>();
	private final Map<String, List<DynamicDefinition>> definitionsByFieldName = new LinkedHashMap<>();

	/**
	 * Constructeur.
	 * @param dynamicDefinitionKey Clé de la définition
	 * @param entity Entité
	 */
	DynamicDefinitionImpl(final String dynamicDefinitionName, final Entity entity) {
		Assertion.checkNotNull(dynamicDefinitionName);
		//packageName peut être null
		Assertion.checkNotNull(entity);
		//-----
		this.dynamicDefinitionName = dynamicDefinitionName;
		this.entity = entity;
	}

	/** {@inheritDoc} */
	@Override
	public String getPackageName() {
		return packageName;
	}

	/** {@inheritDoc} */
	@Override
	public Entity getEntity() {
		return entity;
	}

	/** {@inheritDoc} */
	@Override
	public DynamicDefinition build() {
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public final String getName() {
		return dynamicDefinitionName;
	}

	/** {@inheritDoc} */
	@Override
	public Object getPropertyValue(final String propertyName) {
		Assertion.checkNotNull(propertyName);
		// On ne vérifie rien sur le type retourné par le getter.
		// le type a été validé lors du put.
		//-----
		// Conformémément au contrat, on retourne null si pas de propriété
		// trouvée
		return properties.get(propertyName);
	}

	/** {@inheritDoc} */
	@Override
	public Set<String> getPropertyNames() {
		return Collections.unmodifiableSet(properties.keySet());
	}

	/** {@inheritDoc} */
	@Override
	public final List<String> getDefinitionNames(final String fieldName) {
		return obtainDefinitionNames(fieldName);
	}

	/** {@inheritDoc} */
	@Override
	public final List<DynamicDefinition> getChildDefinitions(final String fieldName) {
		return obtainComposites(fieldName);
	}

	/** {@inheritDoc} */
	@Override
	public final List<DynamicDefinition> getAllChildDefinitions() {
		final List<DynamicDefinition> dynamicDefinitions = new ArrayList<>();
		for (final List<DynamicDefinition> dynamicDefinitionList : definitionsByFieldName.values()) {
			dynamicDefinitions.addAll(dynamicDefinitionList);
		}
		return dynamicDefinitions;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean containsDefinitionName(final String fieldName) {
		return definitionNamesByFieldName.containsKey(fieldName);
	}

	/** {@inheritDoc} */
	@Override
	public final String getDefinitionName(final String fieldName) {
		Assertion.checkArgument(containsDefinitionName(fieldName), "Aucune définition déclarée pour ''{0}'' sur ''{1}'' ", fieldName, getName());
		final List<String> list = definitionNamesByFieldName.get(fieldName);
		final String definitionName = list.get(0);
		//-----
		// On vérifie qu'il y a une définition pour le champ demandé
		Assertion.checkNotNull(definitionName);
		return definitionName;
	}

	/** {@inheritDoc} */
	@Override
	public final List<String> getAllDefinitionNames() {
		final List<String> allDefinitionNames = new ArrayList<>();
		for (final List<String> dynamicDefinitionNames : definitionNamesByFieldName.values()) {
			allDefinitionNames.addAll(dynamicDefinitionNames);
		}
		return allDefinitionNames;
	}

	/** {@inheritDoc} */
	@Override
	public DynamicDefinitionBuilder withPackageName(final String newPackageName) {
		packageName = newPackageName;
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public final DynamicDefinitionBuilder addPropertyValue(final String propertyName, final Object value) {
		getEntity().getPrimitiveType(propertyName).checkValue(value);
		properties.put(propertyName, value);
		return this;
	}

	private List<String> obtainDefinitionNames(final String fieldName) {
		Assertion.checkNotNull(fieldName);
		//-----
		List<String> list = definitionNamesByFieldName.get(fieldName);
		//-----
		if (list == null) {
			list = new ArrayList<>();
			definitionNamesByFieldName.put(fieldName, list);
		}
		return list;
	}

	/** {@inheritDoc} */
	@Override
	public final DynamicDefinitionBuilder addDefinition(final String fieldName, final DynamicDefinition definition) {
		doAddChildDefinition(fieldName, Collections.singletonList(definition));
		return this;
	}

	private final void doAddChildDefinition(final String fieldName, final List<DynamicDefinition> definitions) {
		Assertion.checkNotNull(fieldName);
		Assertion.checkNotNull(definitions);
		//-----
		obtainComposites(fieldName).addAll(definitions);
	}

	private void doAddDefinitions(final String fieldName, final List<String> definitionNames) {
		Assertion.checkNotNull(fieldName);
		Assertion.checkNotNull(definitionNames);
		// On vérifie que la liste est vide pour éviter les syntaxe avec multi
		Assertion.checkArgument(obtainDefinitionNames(fieldName).isEmpty(), "syntaxe interdite");
		//-----
		obtainDefinitionNames(fieldName).addAll(definitionNames);
	}

	/** {@inheritDoc} */
	@Override
	public final DynamicDefinitionBuilder addAllDefinitions(final String fieldName, final List<String> definitionNames) {
		doAddDefinitions(fieldName, definitionNames);
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public final DynamicDefinitionBuilder addDefinition(final String fieldName, final String definitionName) {
		Assertion.checkNotNull(fieldName);
		Assertion.checkNotNull(definitionName);
		//-----
		doAddDefinitions(fieldName, Collections.singletonList(definitionName));
		return this;
	}

	private List<DynamicDefinition> obtainComposites(final String fieldName) {
		Assertion.checkNotNull(fieldName);
		//-----
		List<DynamicDefinition> list = definitionsByFieldName.get(fieldName);
		//-----
		if (list == null) {
			list = new ArrayList<>();
			definitionsByFieldName.put(fieldName, list);
		}
		return list;
	}

	/** {@inheritDoc} */
	@Override
	public final DynamicDefinitionBuilder addBody(final DynamicDefinition dynamicDefinition) {
		// 1. maj des EntityProperty
		for (final String propertyName : dynamicDefinition.getPropertyNames()) {
			addPropertyValue(propertyName, dynamicDefinition.getPropertyValue(propertyName));
		}

		// 2. maj fieldNameDefinitionKeyListMap
		final DynamicDefinitionImpl other = (DynamicDefinitionImpl) dynamicDefinition;

		for (final Entry<String, List<String>> entry : other.definitionNamesByFieldName.entrySet()) {
			final String fieldName = entry.getKey();
			final List<String> definitionNames = entry.getValue();
			//-----
			doAddDefinitions(fieldName, definitionNames);
		}

		// 3.
		for (final Entry<String, List<DynamicDefinition>> entry : other.definitionsByFieldName.entrySet()) {
			final String fieldName = entry.getKey();
			final List<DynamicDefinition> definitions = entry.getValue();
			//-----
			doAddChildDefinition(fieldName, definitions);
		}
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		//nécessaire pour le log
		return dynamicDefinitionName;
	}

}
