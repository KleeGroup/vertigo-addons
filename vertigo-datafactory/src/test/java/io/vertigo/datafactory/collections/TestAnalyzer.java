/**
 * vertigo - application development platform
 *
 * Copyright (C) 2013-2023, Vertigo.io, team@vertigo.io
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
package io.vertigo.datafactory.collections;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

/**
 * Classe d'analyse des chaïnes de caractères.
 * @author  pchretien
 */
final class TestAnalyzer extends Analyzer {
	/**
	   * Creates a TokenStream which tokenizes all the text in the provided Reader.
	   *
	   * @return A TokenStream build from a StandardTokenizer filtered with
	   *         StandardFilter, StopFilter, FrenchStemFilter and LowerCaseFilter
	   */
	@Override
	protected TokenStreamComponents createComponents(final String fieldName) {
		/* initialisation du token */
		final Tokenizer source = new StandardTokenizer();
		//-----
		/* on retire les accents */
		final TokenStream filter = new ASCIIFoldingFilter(source);
		/* on met en minuscule */
		//filter = new LowerCaseFilter(filter);
		return new TokenStreamComponents(source, filter);
	}
}
