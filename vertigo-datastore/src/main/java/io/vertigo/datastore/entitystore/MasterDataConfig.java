/*
 * vertigo - application development platform
 *
 * Copyright (C) 2013-2024, Vertigo.io, team@vertigo.io
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
package io.vertigo.datastore.entitystore;

import java.util.function.Predicate;

import io.vertigo.datamodel.data.definitions.DataDefinition;
import io.vertigo.datamodel.data.model.DataObject;
import io.vertigo.datamodel.data.model.DtListURIForMasterData;
import io.vertigo.datastore.entitystore.definitions.MasterDataDefinition;

/**
 * Configuration des données de référence.
 * @author  pchretien
 */
public interface MasterDataConfig {

	/**
	 * Enregistre la stratégie d'accès à une liste de référence.
	 * La liste de référence est La liste racine.
	 * @param uri URI
	 */
	void register(final MasterDataDefinition masterDataDefinition);

	/**
	 * Indique s'il existe une MasterDataList pour ce type d'objet.
	 * @param dataDefinition  Définition de DT
	 * @return True, s'il existe une MasterDataList
	 */
	boolean containsMasterData(final DataDefinition dataDefinition);

	/**
	 * Renvoi l'URI à partir d'une définition.
	 * @param dataDefinition DId de la Définition de DT
	 * @return URI de retour (notNUll)
	 */
	DtListURIForMasterData getDtListURIForMasterData(final DataDefinition dataDefinition);

	/**
	 * @param uri URI de la liste
	 * @return Fonction à appliquer sur la liste (par rapport à la liste complète).
	 */
	Predicate<? super DataObject> getFilter(final DtListURIForMasterData uri);
}
