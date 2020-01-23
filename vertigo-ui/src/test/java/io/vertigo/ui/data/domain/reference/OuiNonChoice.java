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
public final class OuiNonChoice implements Entity {
	private static final long serialVersionUID = 1L;

	private Boolean key;
	private String libelle;

	/** {@inheritDoc} */
	@Override
	public UID<OuiNonChoice> getUID() {
		return UID.of(this);
	}

	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Valeur'.
	 * @return Boolean key <b>Obligatoire</b>
	 */
	@Field(domain = "STyOuiNon", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Valeur")
	public Boolean getKey() {
		return key;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Valeur'.
	 * @param key Boolean <b>Obligatoire</b>
	 */
	public void setKey(final Boolean key) {
		this.key = key;
	}

	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Libellé'.
	 * @return String libelle
	 */
	@Field(domain = "STyLabel", label = "Libellé")
	@SortField
	@DisplayField
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Libellé'.
	 * @param libelle String
	 */
	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
