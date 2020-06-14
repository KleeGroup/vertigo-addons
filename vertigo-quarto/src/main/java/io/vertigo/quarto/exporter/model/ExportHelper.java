/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2019, vertigo-io, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.quarto.exporter.model;

import java.util.HashMap;
import java.util.Map;

import io.vertigo.core.lang.Assertion;
import io.vertigo.datamodel.smarttype.SmartTypeManager;
import io.vertigo.datamodel.structure.metamodel.DtField;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.model.DtListURIForMasterData;
import io.vertigo.datamodel.structure.model.DtObject;
import io.vertigo.datamodel.structure.model.Entity;
import io.vertigo.datastore.entitystore.EntityStoreManager;

/**
 * Classe utilitaire pour export.
 * @author pchretien, evernat
 */
public final class ExportHelper {
	private final EntityStoreManager entityStoreManager;
	private final SmartTypeManager smartTypeManager;

	/**
	 * Constructor.
	 * @param entityStoreManager StoreManager for MasterData management
	 */
	public ExportHelper(
			final EntityStoreManager entityStoreManager,
			final SmartTypeManager smartTypeManager) {
		Assertion.check()
				.notNull(entityStoreManager)
				.notNull(smartTypeManager);
		//-----
		this.entityStoreManager = entityStoreManager;
		this.smartTypeManager = smartTypeManager;
	}

	/**
	 * Retourne le text d'un champs du DTO en utilisant le formateur du smartType, ou l'élément issu de la liste de REF si il y a une dénormalisation à faire.
	 * @param referenceCache Cache des éléments de référence (clé-libellé), peut être vide la premiere fois il sera remplit automatiquement
	 *  (utilisé pour les champs issus d'association avec une liste de ref)
	 * @param denormCache Cache des colonnes dénormalisées par field, peut être vide la premiere fois il sera remplit automatiquement
	 *  (utilisé en cas de dénorm spécifique)
	 * @param dto Objet métier
	 * @param exportColumn Information de la colonne a exporter.
	 * @return Valeur d'affichage de la colonne de l'objet métier
	 */
	public String getText(
			final Map<DtField, Map<Object, String>> referenceCache,
			final Map<DtField, Map<Object, String>> denormCache,
			final DtObject dto,
			final ExportField exportColumn) {
		return (String) getValue(true, referenceCache, denormCache, dto, exportColumn);
	}

	/**
	 * Retourne la valeur d'un champs du DTO, ou l'élément issu de la liste de REF si il y a une dénormalisation à faire.
	 * @param referenceCache Cache des éléments de référence (clé-libellé), peut être vide la premiere fois il sera remplit automatiquement
	 * (utilisé pour les champs issus d'association avec une liste de ref)
	 * @param denormCache Cache des colonnes dénormalisées par field, peut être vide la premiere fois il sera remplit automatiquement
	 *  (utilisé en cas de dénorm spécifique)
	 * @param dto Objet métier
	 * @param exportColumn Information de la colonne a exporter.
	 * @return Valeur typée de la colonne de l'objet métier
	 */
	public Object getValue(
			final Map<DtField, Map<Object, String>> referenceCache,
			final Map<DtField, Map<Object, String>> denormCache,
			final DtObject dto,
			final ExportField exportColumn) {
		return getValue(false, referenceCache, denormCache, dto, exportColumn);
	}

	private Object getValue(
			final boolean forceStringValue,
			final Map<DtField, Map<Object, String>> referenceCache,
			final Map<DtField, Map<Object, String>> denormCache,
			final DtObject dto,
			final ExportField exportColumn) {
		final DtField dtField = exportColumn.getDtField();
		Object value;
		if (dtField.getType() == DtField.FieldType.FOREIGN_KEY && entityStoreManager.getMasterDataConfig().containsMasterData(dtField.getFkDtDefinition())) {
			Map<Object, String> referenceIndex = referenceCache.get(dtField);
			if (referenceIndex == null) {
				referenceIndex = createReferentielIndex(dtField);
				referenceCache.put(dtField, referenceIndex);
			}
			value = referenceIndex.get(dtField.getDataAccessor().getValue(dto));
		} else if (exportColumn instanceof ExportDenormField) {
			final ExportDenormField exportDenormColumn = (ExportDenormField) exportColumn;
			Map<Object, String> denormIndex = denormCache.get(dtField);
			if (denormIndex == null) {
				denormIndex = createDenormIndex(exportDenormColumn.getDenormList(), exportDenormColumn.getKeyField(), exportDenormColumn.getDisplayField());
				denormCache.put(dtField, denormIndex);
			}
			value = denormIndex.get(dtField.getDataAccessor().getValue(dto));
		} else {
			value = exportColumn.getDtField().getDataAccessor().getValue(dto);
			if (forceStringValue) {
				value = smartTypeManager.valueToString(exportColumn.getDtField().getSmartTypeDefinition(), value);
			}
		}
		//Check if we should return some magic value ("??") when exception are throw here. Previous impl manage this "useless ?" case.
		return value;
	}

	private Map<Object, String> createReferentielIndex(final DtField dtField) {
		//TODO ceci est un copier/coller de KSelectionListBean (qui resemble plus à un helper des MasterData qu'a un bean)
		//La collection n'est pas précisé alors on va la chercher dans le repository du référentiel
		final DtListURIForMasterData mdlUri = entityStoreManager.getMasterDataConfig().getDtListURIForMasterData(dtField.getFkDtDefinition());
		final DtList<Entity> valueList = entityStoreManager.findAll(mdlUri);
		final DtField dtFieldDisplay = mdlUri.getDtDefinition().getDisplayField().get();
		final DtField dtFieldKey = valueList.getDefinition().getIdField().get();
		return createDenormIndex(valueList, dtFieldKey, dtFieldDisplay);
	}

	private Map<Object, String> createDenormIndex(final DtList<?> valueList, final DtField keyField, final DtField displayField) {
		final Map<Object, String> denormIndex = new HashMap<>(valueList.size());
		for (final DtObject dto : valueList) {
			final String svalue = smartTypeManager.valueToString(displayField.getSmartTypeDefinition(), displayField.getDataAccessor().getValue(dto));
			denormIndex.put(keyField.getDataAccessor().getValue(dto), svalue);
		}
		return denormIndex;
	}

}
