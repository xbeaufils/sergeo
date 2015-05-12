/**
 * 
 */
package fr.firesoft.fireTime.helper.besoin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author xavier
 *
 */
public class BesoinWeekHelper implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4887785823277372209L;
	private List<BesoinJourHelper> lstJour;
	/**
	 * 
	 */
	public BesoinWeekHelper() {
		// TODO Auto-generated constructor stub
		lstJour = new ArrayList<BesoinJourHelper> ();
	}
	
	/**
	 * @return the lstJour
	 */
	public List<BesoinJourHelper> getLstJour() {
		return lstJour;
	}
	/**
	 * @param lstJour the lstJour to set
	 */
	public void setLstJour(List<BesoinJourHelper> lstJour) {
		this.lstJour = lstJour;
	}
		
}
