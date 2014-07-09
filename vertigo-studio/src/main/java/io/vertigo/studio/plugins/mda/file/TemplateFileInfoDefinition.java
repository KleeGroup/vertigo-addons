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
package io.vertigo.studio.plugins.mda.file;

import io.vertigo.dynamo.file.metamodel.FileInfoDefinition;
import io.vertigo.kernel.lang.Assertion;
import io.vertigo.kernel.metamodel.DefinitionUtil;
import io.vertigo.kernel.util.StringUtil;

/**
 * Génération des classes/méthodes des fileInfo.
 * 
 * @author npiedeloup
 */
public final class TemplateFileInfoDefinition {
	private final FileInfoDefinition fileInfoDefinition;

	TemplateFileInfoDefinition(final FileInfoDefinition fileInfoDefinition) {
		Assertion.checkNotNull(fileInfoDefinition);
		//-----------------------------------------------------------------
		this.fileInfoDefinition = fileInfoDefinition;
	}

	/**
	 * @return Urn de la fileInfoDefinition
	 */
	public String getUrn() {
		return fileInfoDefinition.getName();
	}

	/**
	 * @return Nom de la class en CamelCase
	 */
	public String getClassSimpleName() {
		final String localName = DefinitionUtil.getLocalName(fileInfoDefinition.getName(), FileInfoDefinition.class);
		return StringUtil.constToCamelCase(localName, true);
	}
}
