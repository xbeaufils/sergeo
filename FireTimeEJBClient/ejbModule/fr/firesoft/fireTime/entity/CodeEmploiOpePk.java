/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author xavier
 *
 */
@Embeddable
public class CodeEmploiOpePk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7818525028154329849L;
	private Integer organisation;
	private EmploiOperationnel emploi;
	/**
	 * 
	 */
	public CodeEmploiOpePk() {
	}
	/**
	 * @return the organisation
	 */
	@Column (name="ORGA")
	public Integer getOrganisation() {
		return organisation;
	}
	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(Integer organisation) {
		this.organisation = organisation;
	}
	/**
	 * @return the emploi
	 */
	@ManyToOne
	@JoinColumn (name="IDF_EMPLOI")
	public EmploiOperationnel getEmploi() {
		return emploi;
	}
	/**
	 * @param emploi the emploi to set
	 */
	public void setEmploi(EmploiOperationnel emploi) {
		this.emploi = emploi;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emploi == null) ? 0 : emploi.hashCode());
		result = prime * result
				+ ((organisation == null) ? 0 : organisation.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeEmploiOpePk other = (CodeEmploiOpePk) obj;
		if (emploi == null) {
			if (other.emploi != null)
				return false;
		} else if (!emploi.equals(other.emploi))
			return false;
		if (organisation == null) {
			if (other.organisation != null)
				return false;
		} else if (!organisation.equals(other.organisation))
			return false;
		return true;
	}

}
