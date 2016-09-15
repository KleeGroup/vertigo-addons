/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2013-2016, KleeGroup, direction.technique@kleegroup.com (http://www.kleegroup.com)
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
package io.vertigo.dynamo.plugins.environment.loaders.kpr.rules;

import io.vertigo.commons.parser.Rule;
import io.vertigo.commons.parser.Rules;
import io.vertigo.commons.parser.WordRule;

/**
 *
 * Les lettres interdites dans les mots sont les suivantes espace
 * =();[]"/.
 *
 * @author pchretien
 */
final class DslSyntaxRules {
	/** Liste des caractères réservés. */
	private static final String RESERVED = "\"=();[]/,{}:";
	/** Liste des caractères blancs. */
	private static final String WHITE_SPACE = " \t\n\r";
	/** Liste des délimiteurs. */
	private static final String DELIMITERS = RESERVED + WHITE_SPACE;

	/** règle de suppression des blancs. */
	static final Rule<?> SPACES = Rules.skipBlanks(WHITE_SPACE);

	static final Rule<String> ARRAY_START = Rules.term("["); //like arrays in json syntax
	static final Rule<String> ARRAY_END = Rules.term("]");
	static final Rule<String> ARRAY_SEPARATOR = Rules.term(",");

	static final Rule<String> OBJECT_START = Rules.term("{"); //like json { name:"john doe", city:"kjkjk"}
	static final Rule<String> OBJECT_END = Rules.term("}");
	static final Rule<String> OBJECT_SEPARATOR = Rules.term(",");

	static final Rule<String> PAIR_SEPARATOR = Rules.term(":"); //name:"bill"
	static final Rule<String> QUOTATION_MARK = Rules.term("\"");

	static final Rule<String> PROPERTY_VALUE = Rules.word(false, "\"", WordRule.Mode.REJECT_ESCAPABLE); //En fait il faut autoriser tous les caractères sauf les guillemets".
	//Il faut gérer le caractère d'évitement.
	static final Rule<String> WORD = Rules.word(false, DELIMITERS, WordRule.Mode.REJECT, "DELIMITERS");

	private DslSyntaxRules() {
		//Classe sans état
	}

}
