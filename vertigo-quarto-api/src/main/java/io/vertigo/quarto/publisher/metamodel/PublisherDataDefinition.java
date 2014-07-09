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
package io.vertigo.quarto.publisher.metamodel;

import io.vertigo.kernel.lang.Assertion;
import io.vertigo.kernel.metamodel.Definition;
import io.vertigo.kernel.metamodel.Prefix;

/**
 * D�finition d'un mod�le d'�dition.
 * Un mod�le d'�dition est un arbre de donn�es.
 *
 * @author npiedeloup, pchretien
 * @version $Id: PublisherDataDefinition.java,v 1.3 2013/10/22 10:50:53 pchretien Exp $
 */
@Prefix("PU")
public final class PublisherDataDefinition implements Definition {
	/** Nom de la d�finition. */
	private final String name;
	private final PublisherNodeDefinition rootNodeDefinition;

	public PublisherDataDefinition(final String name, final PublisherNodeDefinition rootNodeDefinition) {
		Assertion.checkArgNotEmpty(name);
		Assertion.checkNotNull(rootNodeDefinition);
		//---------------------------------------------------------------------
		this.name = name;
		this.rootNodeDefinition = rootNodeDefinition;
	}

	/**
	 * @return D�finition du noeud racine
	 */
	public PublisherNodeDefinition getRootNodeDefinition() {
		return rootNodeDefinition;
	}

	/** {@inheritDoc} */
	public String getName() {
		return name;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return name;
	}
}
