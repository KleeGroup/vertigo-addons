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
package io.vertigo.dynamo.work;

/**
 * Hanlder permettant de définir le comportement après exécution asynchrone d'un work.
 * 
 * @author   pchretien, npiedeloup
 */
public interface WorkResultHandler<WR> {
	/**
	 * Démarrage de l'exécution de la tache.
	 * Notification pour information.
	 */
	void onStart();

	/**
	 * Exécution terminée avec succès.
	 * @param result Résultat de l'excution 
	 */
	void onSuccess(final WR result);

	/**
	 * Exécution terminée par la survenue d'une exception
	 * @param error  Exception
	 */
	void onFailure(final Throwable error);

	/**
	 * Exécution interrompue par un timeout
	 * 
	 * @param timeoutInSeconds TimeoutIn Seconds exprimé en secondes
	 */
	//void onTimeout(final int timeoutInSeconds);
}
