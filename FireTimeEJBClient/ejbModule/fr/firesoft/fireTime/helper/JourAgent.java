package fr.firesoft.fireTime.helper;

import java.io.Serializable;

import fr.firesoft.fireTime.ext.horaire.Statut;




public class JourAgent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1369352114950317946L;
	private Integer idfPeriode;
	private Statut statut;
	private Integer jour; 
	private Integer idfAgent;
	
	public JourAgent() {
	}

	/**
	 * @return the idfPeriode
	 */
	public Integer getIdfPeriode() {
		return idfPeriode;
	}

	/**
	 * @param idfPeriode the idfPeriode to set
	 */
	public void setIdfPeriode(Integer idfPeriode) {
		this.idfPeriode = idfPeriode;
	}

	/**
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/**
	 * @return the jour
	 */
	public Integer getJour() {
		return jour;
	}

	/**
	 * @param jour the jour to set
	 */
	public void setJour(Integer jour) {
		this.jour = jour;
	}

	/**
	 * @return the idfAgent
	 */
	public Integer getIdfAgent() {
		return idfAgent;
	}

	/**
	 * @param idfAgent the idfAgent to set
	 */
	public void setIdfAgent(Integer idfAgent) {
		this.idfAgent = idfAgent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if ( ! (obj instanceof JourAgent))
			return super.equals(obj);
		int compar = idfAgent.compareTo(((JourAgent)obj).idfAgent);
		if (compar != 0 )
			return false;
		return jour.equals( ((JourAgent)obj).jour);
	}

}
