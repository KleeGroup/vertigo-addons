/*
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
package io.vertigo.planning.agenda.domain;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.DtObject;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class TrancheHoraireDisplay implements DtObject {
	private static final long serialVersionUID = 1L;

	private Long trhId;
	private java.time.LocalDate dateLocale;
	private Integer minutesDebut;
	private Integer minutesFin;
	private Integer nbGuichet;
	private Integer nbReserve;
	private Integer nbReserveNonPublie;
	private Integer nbTotal;
	private String etatPublication;
	private java.time.Instant instantPublication;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Id'.
	 * @return Long trhId
	 */
	@Field(smartType = "STyPId", label = "Id")
	public Long getTrhId() {
		return trhId;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Id'.
	 * @param trhId Long
	 */
	public void setTrhId(final Long trhId) {
		this.trhId = trhId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date du creneau'.
	 * @return LocalDate dateLocale
	 */
	@Field(smartType = "STyPLocalDate", label = "Date du creneau")
	public java.time.LocalDate getDateLocale() {
		return dateLocale;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Date du creneau'.
	 * @param dateLocale LocalDate
	 */
	public void setDateLocale(final java.time.LocalDate dateLocale) {
		this.dateLocale = dateLocale;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Heure de début'.
	 * @return Integer minutesDebut
	 */
	@Field(smartType = "STyPHeureMinute", label = "Heure de début")
	public Integer getMinutesDebut() {
		return minutesDebut;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Heure de début'.
	 * @param minutesDebut Integer
	 */
	public void setMinutesDebut(final Integer minutesDebut) {
		this.minutesDebut = minutesDebut;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Heure de fin'.
	 * @return Integer minutesFin
	 */
	@Field(smartType = "STyPHeureMinute", label = "Heure de fin")
	public Integer getMinutesFin() {
		return minutesFin;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Heure de fin'.
	 * @param minutesFin Integer
	 */
	public void setMinutesFin(final Integer minutesFin) {
		this.minutesFin = minutesFin;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Nombre de guichets'.
	 * @return Integer nbGuichet
	 */
	@Field(smartType = "STyPNbGuichet", label = "Nombre de guichets")
	public Integer getNbGuichet() {
		return nbGuichet;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Nombre de guichets'.
	 * @param nbGuichet Integer
	 */
	public void setNbGuichet(final Integer nbGuichet) {
		this.nbGuichet = nbGuichet;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Réservé'.
	 * @return Integer nbReserve
	 */
	@Field(smartType = "STyPNombre", label = "Réservé")
	public Integer getNbReserve() {
		return nbReserve;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Réservé'.
	 * @param nbReserve Integer
	 */
	public void setNbReserve(final Integer nbReserve) {
		this.nbReserve = nbReserve;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Réservé et non publié'.
	 * @return Integer nbReserveNonPublie
	 */
	@Field(smartType = "STyPNombre", label = "Réservé et non publié")
	public Integer getNbReserveNonPublie() {
		return nbReserveNonPublie;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Réservé et non publié'.
	 * @param nbReserveNonPublie Integer
	 */
	public void setNbReserveNonPublie(final Integer nbReserveNonPublie) {
		this.nbReserveNonPublie = nbReserveNonPublie;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Total'.
	 * @return Integer nbTotal
	 */
	@Field(smartType = "STyPNombre", label = "Total")
	public Integer getNbTotal() {
		return nbTotal;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Total'.
	 * @param nbTotal Integer
	 */
	public void setNbTotal(final Integer nbTotal) {
		this.nbTotal = nbTotal;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Publication'.
	 * @return String etatPublication
	 */
	@Field(smartType = "STyPCode", label = "Publication")
	public String getEtatPublication() {
		return etatPublication;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Publication'.
	 * @param etatPublication String
	 */
	public void setEtatPublication(final String etatPublication) {
		this.etatPublication = etatPublication;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date publication'.
	 * @return Instant instantPublication
	 */
	@Field(smartType = "STyPInstant", label = "Date publication")
	public java.time.Instant getInstantPublication() {
		return instantPublication;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Date publication'.
	 * @param instantPublication Instant
	 */
	public void setInstantPublication(final java.time.Instant instantPublication) {
		this.instantPublication = instantPublication;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
