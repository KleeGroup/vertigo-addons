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
package io.vertigo.quarto.plugins.converter.work;

import io.vertigo.dynamo.file.model.KFile;
import io.vertigo.kernel.lang.Assertion;

/**
 * Travail de conversion.
 * Poss�de :
 * - le fichier � convertir
 * - le format destination
 * 
 * @author npiedeloup
 * @version $Id: ConverterWork.java,v 1.5 2014/01/28 18:49:24 pchretien Exp $
 */
final class ConverterWork {
	private final KFileSerializable file;
	private final String targetFormat;

	/**
	 * Constructeur.
	 * @param file fichier � convertir
	 * @param targetFormat format destination
	 */
	ConverterWork(final KFile file, final String targetFormat) {
		Assertion.checkNotNull(file);
		Assertion.checkNotNull(targetFormat);
		//-----------------------------------------------------------------
		this.file = new KFileSerializable(file);
		this.targetFormat = targetFormat;
	}

	/** {@inheritDoc} */
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * @return fichier � convertir
	 */
	KFile getInputFile() {
		return file;
	}

	/**
	 * @return format destination
	 */
	String geTargetFormat() {
		return targetFormat;
	}
}