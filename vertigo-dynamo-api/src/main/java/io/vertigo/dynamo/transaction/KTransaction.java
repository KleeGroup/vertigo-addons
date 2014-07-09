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
package io.vertigo.dynamo.transaction;

/**
 * Transaction.
 * Une transaction :
 * - contient un ensemble de ressources de type BDD, fichiers, JMS...
 * - est soit démarrée, soit terminée.
 * - peut posséder (ou être) une transaction imbriquée.
 *
 * @author  pchretien
 */
public interface KTransaction {
	/**
	 * Ajoute une ressource à la transaction en précisant son ordre au sein de la transaction.
	 * Il n'est pas possible d'enregistrer pour une même transaction, deux ressources avec le même identifiant.
	 * @param id Identifiant de la ressource transactionnelle au sein de la transaction
	 * @param resource Ressource transactionnelle
	 * @param <TR> Ressource transactionnelle
	 */
	<TR extends KTransactionResource> void addResource(KTransactionResourceId<TR> id, TR resource);

	/**
	 * @param transactionResourceId Identifiant/type de ressource transactionnelle.
	 * @return Ressource transactionnelle correspondant à l'id
	 * @param <TR> Ressource transactionnelle
	 */
	<TR extends KTransactionResource> TR getResource(KTransactionResourceId<TR> transactionResourceId);
}
