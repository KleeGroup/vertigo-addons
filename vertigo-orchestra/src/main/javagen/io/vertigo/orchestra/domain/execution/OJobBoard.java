package io.vertigo.orchestra.domain.execution;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
@io.vertigo.dynamo.domain.stereotype.DataSpace("orchestra")
public final class OJobBoard implements Entity {
	private static final long serialVersionUID = 1L;

	private String jid;

	private String status;

	private Long nodeId;

	private java.time.ZonedDateTime maxDate;

	private Integer maxRetry;

	private Integer currentRetry;


	/** {@inheritDoc} */
	@Override
	public URI<OJobBoard> getURI() {
		return DtObjectUtil.createURI(this);
	}

	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'Id de l'execution du job'.
	 * @return String jid <b>Obligatoire</b>
	 */
	@Field(domain = "DO_O_CODE_IDENTIFIANT", type = "ID", required = true, label = "Id de l'execution du job")
	public String getJid() {
		return jid;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'Id de l'execution du job'.
	 * @param jid String <b>Obligatoire</b>
	 */
	public void setJid(final String jid) {
		this.jid = jid;
	}
	
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Status de l'execution'.
	 * @return String status <b>Obligatoire</b>
	 */
	@Field(domain = "DO_O_STATUS_CODE", required = true, label = "Status de l'execution")
	public String getStatus() {
		return status;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Status de l'execution'.
	 * @param status String <b>Obligatoire</b>
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Id du noeud'.
	 * @return Long nodeId <b>Obligatoire</b>
	 */
	@Field(domain = "DO_O_IDENTIFIANT", required = true, label = "Id du noeud")
	public Long getNodeId() {
		return nodeId;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Id du noeud'.
	 * @param nodeId Long <b>Obligatoire</b>
	 */
	public void setNodeId(final Long nodeId) {
		this.nodeId = nodeId;
	}
	
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Date max d'execution'.
	 * @return java.time.ZonedDateTime maxDate
	 */
	@Field(domain = "DO_O_TIMESTAMP", label = "Date max d'execution")
	public java.time.ZonedDateTime getMaxDate() {
		return maxDate;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Date max d'execution'.
	 * @param maxDate java.time.ZonedDateTime
	 */
	public void setMaxDate(final java.time.ZonedDateTime maxDate) {
		this.maxDate = maxDate;
	}
	
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Nb max de retry'.
	 * @return Integer maxRetry
	 */
	@Field(domain = "DO_O_NOMBRE", label = "Nb max de retry")
	public Integer getMaxRetry() {
		return maxRetry;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Nb max de retry'.
	 * @param maxRetry Integer
	 */
	public void setMaxRetry(final Integer maxRetry) {
		this.maxRetry = maxRetry;
	}
	
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Nb courrant de retry'.
	 * @return Integer currentRetry
	 */
	@Field(domain = "DO_O_NOMBRE", label = "Nb courrant de retry")
	public Integer getCurrentRetry() {
		return currentRetry;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Nb courrant de retry'.
	 * @param currentRetry Integer
	 */
	public void setCurrentRetry(final Integer currentRetry) {
		this.currentRetry = currentRetry;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
