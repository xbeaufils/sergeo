/**
 * 
 */
package fr.firesoft.firetime.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author xbeaufils
 *
 */
public abstract class AuthentifiedAction extends ActionSupport {

	private Integer idfEchelon;
	private String organisation;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1458935286884879905L;
	
	/**
	 * @return the organisation
	 */
	public String getOrganisation() {
		return organisation;
	}
	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	/**
	 * @return the idfEchelon
	 */
	public Integer getIdfEchelon() {
		return idfEchelon;
	}
	/**
	 * @param idfEchelon the idfEchelon to set
	 */
	public void setIdfEchelon(Integer idfEchelon) {
		this.idfEchelon = idfEchelon;
	}


}
