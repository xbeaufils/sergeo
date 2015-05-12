/**
 * 
 */
package fr.firesoft.firetime.viewer;

import java.io.Serializable;

/**
 * @author beaufils
 *
 */
public class DispoViewer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2472406582069663118L;

	private Integer idfDispo;
	private Integer jour;
	private Integer idfPlage;
	private Integer idfAgent;
	private Integer idOrganization;
	/**
	 * 
	 */
	public DispoViewer() {		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the idfDispo
	 */
	public Integer getIdfDispo() {
		return idfDispo;
	}
	/**
	 * @param idfDispo the idfDispo to set
	 */
	public void setIdfDispo(Integer idfDispo) {
		this.idfDispo = idfDispo;
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
	 * @return the idfPlage
	 */
	public Integer getIdfPlage() {
		return idfPlage;
	}
	/**
	 * @param idfPlage the idfPlage to set
	 */
	public void setIdfPlage(Integer idfPlage) {
		this.idfPlage = idfPlage;
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

}
