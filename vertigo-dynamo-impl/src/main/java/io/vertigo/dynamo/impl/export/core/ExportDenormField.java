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
package io.vertigo.dynamo.impl.export.core;

import io.vertigo.dynamo.domain.metamodel.DtField;
import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.export.ExportField;

/**
 * D�finition d'une colonne de type d�normalisation � exporter.
 * On pr�sice la liste et le champs a utiliser comme libell� � afficher � la place de l'id de la liste de l'export.
 *
 * @author pchretien, npiedeloup
 * @version $Id: ExportDenormField.java,v 1.2 2014/01/20 17:49:10 pchretien Exp $
 */
final class ExportDenormField extends ExportField {
	private final DtList<?> list;
	private DtField keyField;
	private final DtField displayField;

	/**
	 * Constructeur.
	 * @param dtField Champ � exporter
	 * @param list Liste de �lements d�norm�s
	 * @param displayField Champs d�norm�
	 */
	ExportDenormField(final DtField dtField, final DtList<?> list, final DtField displayField) {
		super(dtField);
		this.list = list;
		this.displayField = displayField;
	}

	/**
	 * @return DtList<?> liste contenant les �l�ments d�norm�s.
	 */
	DtList<?> getDenormList() {
		return list;
	}

	/**
	 * @return DtField repr�sentant le display de la liste de d�norm.
	 */
	DtField getDisplayField() {
		return displayField;
	}

	/**
	 * @return DtField repr�sentant la cl� de la liste de d�norm. (par d�faut la key du DT)
	 */
	DtField getKeyField() {
		if (keyField == null) {
			keyField = list.getDefinition().getIdField().get();
		}
		return keyField;
	}

	//--------------------------------------------------------------------------

	//	/**
	//	 * @param keyField repr�sentant la cl� de la liste de d�norm. (util seulement si diff�rent de la key du DT)
	//	 */
	//	void setKeyField(final DtField keyField) {
	//		this.keyField = keyField;
	//	}
}
