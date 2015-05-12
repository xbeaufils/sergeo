/**
 * 
 */
package fr.firesoft.fireTime.genetic.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author xavier
 *
 */
public class Agent {

	private String matricule;
	private List<String> lstCompetence;
	/**
	 * 
	 */
	public Agent() {
	}
	
	public Agent(String matricule, String[] lstCompetence){
		this.matricule = matricule;
		this.lstCompetence = Arrays.asList(lstCompetence);
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the lstCompetence
	 */
	public List<String> getLstCompetence() {
		return lstCompetence;
	}

	/**
	 * @param lstCompetence the lstCompetence to set
	 */
	public void setLstCompetence(List<String> lstCompetence) {
		this.lstCompetence = lstCompetence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agent [lstCompetence=" + lstCompetence + ", matricule="
				+ matricule + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricule == null) ? 0 : matricule.hashCode());
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
		Agent other = (Agent) obj;
		if (matricule == null) {
			if (other.matricule != null)
				return false;
		} else if (!matricule.equals(other.matricule))
			return false;
		return true;
	}

	
}
