/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;

/**
 * @author beaufils
 *
 */
public class PosteHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2330769639983622501L;

	private Integer idfPoste; 
	private Integer idfEquipage;
	private String equipage;
	private Integer idOrganization;
	private Integer idEmploi;
	private String emploi;
	private Integer affichage;
	private boolean optionnel;
	/**
	 * 
	 */
	public PosteHelper() {
	}
	/**
	 * @return the idfPoste
	 */
	public Integer getIdfPoste() {
		return idfPoste;
	}
	/**
	 * @param idfPoste the idfPoste to set
	 */
	public void setIdfPoste(Integer idfPoste) {
		this.idfPoste = idfPoste;
	}
	/**
	 * @return the idfEquipage
	 */
	public Integer getIdfEquipage() {
		return idfEquipage;
	}
	/**
	 * @param idfEquipage the idfEquipage to set
	 */
	public void setIdfEquipage(Integer idfEquipage) {
		this.idfEquipage = idfEquipage;
	}
	/**
	 * @return the equipage
	 */
	public String getEquipage() {
		return equipage;
	}
	/**
	 * @param equipage the equipage to set
	 */
	public void setEquipage(String equipage) {
		this.equipage = equipage;
	}
	/**
	 * @return the idOrganization
	 */
	public Integer getIdOrganization() {
		return idOrganization;
	}
	/**
	 * @param idOrganization the idOrganization to set
	 */
	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
	}
	/**
	 * @return the idEmploi
	 */
	public Integer getIdEmploi() {
		return idEmploi;
	}
	/**
	 * @param idEmploi the idEmploi to set
	 */
	public void setIdEmploi(Integer idEmploi) {
		this.idEmploi = idEmploi;
	}
	/**
	 * @return the emploi
	 */
	public String getEmploi() {
		return emploi;
	}
	/**
	 * @param emploi the emploi to set
	 */
	public void setEmploi(String emploi) {
		this.emploi = emploi;
	}
	/**
	 * @return the affichage
	 */
	public Integer getAffichage() {
		return affichage;
	}
	/**
	 * @param affichage the affichage to set
	 */
	public void setAffichage(Integer affichage) {
		this.affichage = affichage;
	}
	/**
	 * @return the optionnel
	 */
	public boolean isOptionnel() {
		return optionnel;
	}
	/**
	 * @param optionnel the optionnel to set
	 */
	public void setOptionnel(boolean optionnel) {
		this.optionnel = optionnel;
	}

}
