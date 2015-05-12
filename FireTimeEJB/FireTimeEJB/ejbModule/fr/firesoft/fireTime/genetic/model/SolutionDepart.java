/**
 * 
 */
package fr.firesoft.fireTime.genetic.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * 
 * Une solution de départ est un individu (ou chromosome) au sens de AG
 * @author xavier
 *
 */
public abstract class SolutionDepart {
	protected Float adaptation;
	protected Boolean viable = true;
	protected HashMap<Piquet, Agent> genes;
	private Long identifiant;
	// attributs utilitaires
    protected static Random randomizer = new Random();
	
	public abstract void evaluate();		
		
	public void setGenes(Map<Piquet, Agent> genes) {
		this.genes = (HashMap<Piquet, Agent>) genes;
	}
	
	public Map<Piquet, Agent> getGenes() {
		return genes;
	}

	public Float getAdaptation() {
		return adaptation;
	}
	
	/**
	 * @return the viable
	 */
	public Boolean getViable() {
		return viable;
	}

	/**
	 * @param viable the viable to set
	 */
	public void setViable(Boolean viable) {
		this.viable = viable;
	}

	/**
	 * @return the identifiant
	 */
	public Long getIdentifiant() {
		return identifiant;
	}


	/**
	 * @param identifiant the identifiant to set
	 */
	protected void setIdentifiant(Long identifiant) {
		this.identifiant = identifiant;
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
		SolutionDepart other = (SolutionDepart) obj;
		if (identifiant == null) {
			if (other.identifiant != null)
				return false;
		} else if (!identifiant.equals(other.identifiant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer genesBuffer = new StringBuffer();
		for (Map.Entry<Piquet, Agent> entry : genes.entrySet()) {
			genesBuffer.append( "\n"+ entry.getKey() + "\t" + entry.getValue());
		}
		return "[SolutionDepart : adaptation = " + adaptation + " viable="+ viable + " genes = " + genesBuffer.toString() +"]";
	}

}
