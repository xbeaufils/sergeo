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
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.Poste;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.helper.PosteHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufils
 *
 */
public class PosteAction extends AuthentifiedAction implements SessionAware, ScopedModelDriven<PosteHelper> {
		
	
	Logger log = LoggerFactory.getLogger(PosteAction.class);
	private String scopeKey;
	private PosteHelper model;
	private Integer idfPoste;
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
	public PosteAction() {
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
	

	public String viewAdd() {
		log.debug("Echelon is {}", super.getIdfEchelon());
		CompetenceManagerLocal aCompetenceManager = ServiceLocatorFireTime.getCompetenceManagerBean();
		lstOperationnel = aCompetenceManager.makeListEmploiOperationel();
		BesoinManagerLocal aBesoinManager = ServiceLocatorFireTime.getBesoinManagerBean();
		lstEquipage = aBesoinManager.makeListEquipageForEchelon(super.getIdfEchelon());
		log.debug("Nb equipage {}", lstEquipage.size());
		model = new PosteHelper();
		return SUCCESS;
	}
	
	public String addEmploi() {
		model = (PosteHelper) getSession().get("currentPoste");
		log.debug ("idfEmploi "+ model.getIdEmploi());
		aManager.addEmploi(model.getIdEmploi(), model.getIdfEquipage(), model.isOptionnel());
		return SUCCESS;
	}
	
	public String removeEmploi() {
		aManager.removeEmploi(idfPoste);
		return SUCCESS;
	}
	
	public String upDownEmploi() {
		aManager.downEmploi(idfPoste);
		return SUCCESS;
	}
	
    //---------- Implementation ScopedModelDriven --------------
 	public String getScopeKey() {
		return scopeKey;
	}
	
	public void setModel(PosteHelper arg0) {
		model = arg0;
	}
	
	public void setScopeKey(String arg0) {
		scopeKey = arg0;
	}
	
	public PosteHelper getModel() {
		return model;
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


}
