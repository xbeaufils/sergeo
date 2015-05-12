/**
 * 
 */
package fr.firesoft.fireTime.genetic.model;

/**
 * @author xavier
 *
 */
public class Piquet {

	private Integer identifiant;
	private String besoin;
	private Float importance;
	
	public Piquet() {
	}
	
	public Piquet(Integer identifiant, String besoin, Float importance) {
		this.identifiant = identifiant;
		this.besoin = besoin;
		this.importance = importance;
	}

	public Integer getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}
	
	public String getBesoin() {
		return besoin;
	}
	
	public void setBesoin(String besoin) {
		this.besoin = besoin;
	}
	/**
	 * @return the importance
	 */
	public Float getImportance() {
		return importance;
	}

	/**
	 * @param importance the importance to set
	 */
	public void setImportance(Float importance) {
		this.importance = importance;
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
		Piquet other = (Piquet) obj;
		if (identifiant == null) {
			if (other.identifiant != null)
				return false;
		} else if (!identifiant.equals(other.identifiant))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifiant == null) ? 0 : identifiant.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Piquet [identifiant=" + identifiant + ", besoin=" + besoin + "]";
	}

}
