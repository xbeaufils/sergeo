/**
 * 
 */
package fr.firesoft.firetime.action;

import com.opensymphony.xwork2.interceptor.ScopedModelDriven;

import fr.firesoft.fireTime.entity.horaire.CycleHoraire;

/**
 * @author xavier
 *
 */
public class PeriodeValiditeAction extends AuthentifiedAction implements
		ScopedModelDriven<CycleHoraire> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6911880745887108527L;
	private String scopeKey;
	private CycleHoraire model;
	/**
	 * 
	 */
	public PeriodeValiditeAction() {
	}
	
	//---------- Implementation ScopedModelDriven --------------
    /* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public CycleHoraire getModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.ScopedModelDriven#getScopeKey()
	 */
	public String getScopeKey() {
		return scopeKey;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.ScopedModelDriven#setModel(java.lang.Object)
	 */
	public void setModel(CycleHoraire arg0) {
		model = arg0;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.ScopedModelDriven#setScopeKey(java.lang.String)
	 */
	public void setScopeKey(String arg0) {
		scopeKey = arg0;
		
	}

}
