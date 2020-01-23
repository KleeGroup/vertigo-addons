package io.vertigo.ui.data.domain.reference;

import io.vertigo.core.lang.Generated;
import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.dynamo.domain.stereotype.DisplayField;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.stereotype.SortField;
import io.vertigo.dynamo.domain.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
@io.vertigo.dynamo.ngdomain.annotations.Mapper(clazz = io.vertigo.dynamo.domain.util.JsonMapper.class, dataType = io.vertigo.dynamo.domain.metamodel.DataType.String)
public final class Commune implements Entity {
	private static final long serialVersionUID = 1L;

	private Long idInsee;
	private String codePostal;
	private String commune;
	private String departement;

	/** {@inheritDoc} */
	@Override
	public UID<Commune> getUID() {
		return UID.of(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'idInsee'.
	 * @return Long idInsee <b>Obligatoire</b>
	 */
	@Field(domain = "STyId", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "idInsee")
	@SortField
	public Long getIdInsee() {
		return idInsee;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'idInsee'.
	 * @param idInsee Long <b>Obligatoire</b>
	 */
	public void setIdInsee(final Long idInsee) {
		this.idInsee = idInsee;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'codePostal'.
	 * @return String codePostal
	 */
	@Field(domain = "STyCodePostal", label = "codePostal")
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'codePostal'.
	 * @param codePostal String
	 */
	public void setCodePostal(final String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'commune'.
	 * @return String commune
	 */
	@Field(domain = "STyLabel", label = "commune")
	@DisplayField
	public String getCommune() {
		return commune;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'commune'.
	 * @param commune String
	 */
	public void setCommune(final String commune) {
		this.commune = commune;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'departement'.
	 * @return String departement
	 */
	@Field(domain = "STyLabel", label = "departement")
	public String getDepartement() {
		return departement;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'departement'.
	 * @param departement String
	 */
	public void setDepartement(final String departement) {
		this.departement = departement;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
