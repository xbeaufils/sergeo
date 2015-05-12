/**
 * 
 */
package fr.firesoft.firetime.action.equipage;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.interceptor.ScopedModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AgresManagerLocal;
import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.entity.Poste;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufils
 *
 */
public class EquipageAction extends AuthentifiedAction implements SessionAware, ScopedModelDriven<EquipageHelper> {
		
	
	Logger log = LoggerFactory.getLogger(EquipageAction.class);
	private EquipageHelper modelEquipage;
	private String scopeKey;
	
	private Integer idfEquipageSelected;
	private Integer idfEmploi;
	private Integer idfPoste;
	private boolean optionnel;
	private List<Poste> lstPoste;
	private List<EmploiOperationnel> lstOperationnel;
	private List<EquipageHelper> lstEquipage;
	private AgresManagerLocal aManager;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EquipageAction() {
		aManager = ServiceLocatorFireTime.getAgresManagerBean();
	}

	@SuppressWarnings("unchecked")
	public String view() {
		BesoinManagerLocal aManager = ServiceLocatorFireTime.getBesoinManagerBean();
		List<EquipageHelper> lstEquipage = aManager.makeListEquipageForEchelon(super.getIdfEchelon());
		getSession().put("lstEquipage", lstEquipage);
		getSession().remove("currentEquipage");
		return SUCCESS;
	}
	
	public String viewEquipage() {
		lstPoste = aManager.makeListPosteForEquipage(idfEquipageSelected);
		BesoinManagerLocal aBesoinManager = ServiceLocatorFireTime.getBesoinManagerBean();
		Equipage equipage= aBesoinManager.selectEquipage(idfEquipageSelected);
		modelEquipage.setIdfEquipage(equipage.getIdfEquipage());
		modelEquipage.setLibelle(equipage.getLibelle());
		modelEquipage.setIdOrga(equipage.getIdOrganization());
		return SUCCESS;
	}
	
	public String create() {
		modelEquipage.setIdfEquipage(null);
		modelEquipage.setLibelle(null);
		modelEquipage.setIdOrga(super.getIdfEchelon());
		return SUCCESS;
	}

	public String select() {
		BesoinManagerLocal aBesoinManager = ServiceLocatorFireTime.getBesoinManagerBean();
		Equipage equipage= aBesoinManager.selectEquipage(idfEquipageSelected);
		modelEquipage.setIdfEquipage(equipage.getIdfEquipage());
		modelEquipage.setLibelle(equipage.getLibelle());
		modelEquipage.setIdOrga(equipage.getIdOrganization());
		return SUCCESS;
	}
	
	public String save() {
		AgresManagerLocal manager = ServiceLocatorFireTime.getAgresManagerBean();
		manager.save(modelEquipage);
		return this.view();
	}

	//---------- Implementation ScopedModelDriven --------------
 	public String getScopeKey() {
		return scopeKey;
	}
	
	public void setModel(EquipageHelper arg0) {
		modelEquipage = arg0;
	}
	
	public void setScopeKey(String arg0) {
		scopeKey = arg0;
	}
	
	public EquipageHelper getModel() {
		return modelEquipage;
	}
 	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings({  "rawtypes" })
	public Map getSession() {
		return session;
	}
	// ------------Getters / Setters --------

	/**
	 * @return the lstPoste
	 */
	public List<Poste> getLstPoste() {
		return lstPoste;
	}

	/**
	 * @param lstPoste the lstPoste to set
	 */
	public void setLstPoste(List<Poste> lstPoste) {
		this.lstPoste = lstPoste;
	}

	public List<EmploiOperationnel> getLstOperationnel() {
		return lstOperationnel;
	}

	public void setLstOperationnel(List<EmploiOperationnel> lstOperationnel) {
		this.lstOperationnel = lstOperationnel;
	}

	/**
	 * @return the lstEquipage
	 */
	public List<EquipageHelper> getLstEquipage() {
		return lstEquipage;
	}

	/**
	 * @param lstEquipage the lstEquipage to set
	 */
	public void setLstEquipage(List<EquipageHelper> lstEquipage) {
		this.lstEquipage = lstEquipage;
	}

	/**
	 * @return the idfEquipageSelected
	 */
	public Integer getIdfEquipageSelected() {
		return idfEquipageSelected;
	}

	/**
	 * @param idfEquipageSelected the idfEquipageSelected to set
	 */
	public void setIdfEquipageSelected(Integer idfEquipageSelected) {
		this.idfEquipageSelected = idfEquipageSelected;
	}

	public Integer getIdfEmploi() {
		return idfEmploi;
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

	public void setIdfEmploi(Integer idfEmploi) {
		this.idfEmploi = idfEmploi;
	}

	public boolean isOptionnel() {
		return optionnel;
	}

	public void setOptionnel(boolean optionnel) {
		this.optionnel = optionnel;
	}

}
