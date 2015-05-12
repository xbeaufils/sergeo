/**
 * 
 */
package fr.firesoft.fireTime.helper.besoin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.firesoft.fireTime.ext.besoin.BesoinPlageHelper;

/**
 * @author xavier
 *
 */
public class BesoinJourHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1539629500629219786L;

	private Integer jour;
	private List<BesoinPlageHelper> lstBesoinPlage;
	/**
	 * 
	 */
	public BesoinJourHelper() {
		lstBesoinPlage = new ArrayList<BesoinPlageHelper>();		
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
	 * @return the lstBesoinPlage
	 */
	public List<BesoinPlageHelper> getLstBesoinPlage() {
		return lstBesoinPlage;
	}
	/**
	 * @param lstBesoinPlage the lstBesoinPlage to set
	 */
	public void setLstBesoinPlage(List<BesoinPlageHelper> lstBesoinPlage) {
		this.lstBesoinPlage = lstBesoinPlage;
	}

}
