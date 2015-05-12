/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.interceptor.ScopedModelDriven;


import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.ext.horaire.BesoinEquipageHelper;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;

/**
 * @author xbeaufils
 *
 */
public class BesoinAction extends AuthentifiedAction implements SessionAware,ScopedModelDriven<BesoinEquipageHelper> {

	Logger log = LoggerFactory.getLogger(BesoinAction.class);
	
	private Integer idfBesoinSelected;
	private BesoinEquipageHelper modelBesoin;
	private Integer idfPlage;
	private Integer idfCycleSelect;
	private Integer idfBesoinDelete;
	private Integer idfEmploi;
	private List<EmploiOperationnel> lstEmploi;
	private List<EquipageHelper> lstEquipage;
	private List<BesoinEquipageHelper> lstBesoin;
	private PlageHoraireHelper plage;
	private String scopeKey;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5896640266614967848L;

	/**
	 * 
	 */
	public BesoinAction() {
	}

	
	public String viewAddBesoin() {
		CompetenceManagerLocal aDao = ServiceLocatorFireTime.getCompetenceManagerBean();
		lstEmploi = aDao.makeListEmploiOperationel();
		return SUCCESS;
	}
	
	public String deleteBesoin() {
		log.debug("before {}", plage.getLstBesoin().size());		
		HoraireManagerLocal aManager = (HoraireManagerLocal) session.get("currentHoraireManager");
		aManager.deleteBesoin(plage, idfBesoinDelete);
		log.debug("after {}", plage.getLstBesoin().size());
		return SUCCESS;
	}
	
	public String view() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		HoraireManagerLocal horaire = ServiceLocatorFireTime.getHoraireManagerBean();
		plage = horaire.selectHelper(idfPlage);
		lstBesoin = aManager.makeListBesoinForPlage(idfPlage);
		log.debug("view Plage {} {}", new Object[]{idfPlage, plage.getLibelle()});
		return SUCCESS;
	}
	
	public String viewBesoinEquipage() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		lstEquipage = aManager.makeListEquipageForEchelon(super.getIdfEchelon());
		modelBesoin.setIdfBesoin(null);
		modelBesoin.setEquipage(null);
		modelBesoin.setLibelle(null);
		modelBesoin.setIdfPlageHelper(this.idfPlage);
		return SUCCESS;
	}
	
	public String selectBesoinEquipage() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		lstEquipage = aManager.makeListEquipageForEchelon(super.getIdfEchelon());
		BesoinEquipageHelper currentBesoin = aManager.selectBesoinHelper(idfBesoinSelected);
		log.debug("select {}", new Object[]{currentBesoin.getLibelle()});
		modelBesoin.setIdfBesoin(currentBesoin.getIdfBesoin());
		modelBesoin.setEquipage(currentBesoin.getEquipage());
		modelBesoin.setLibelle(currentBesoin.getLibelle());
		modelBesoin.setIdfPlageHelper(currentBesoin.getIdfPlageHelper());
		return SUCCESS;
	}
	
	public String deleteBesoinEquipage() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		aManager.deleteBesoinEquipage(idfBesoinDelete);
		log.debug("delete Besoin {} Plage {} model plage {}", new Object[]{idfBesoinDelete, idfPlage, modelBesoin.getIdfPlageHelper()});
		idfPlage = modelBesoin.getIdfPlageHelper();
		return this.view();
	}
	
	public String addBesoinEquipage() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		//idfCycleSelect = modelBesoin.getCycle().getIdfCycle();
		log.debug("delete libelle {} equipage {} Plage {}", new Object[]{modelBesoin.getLibelle(), modelBesoin.getIdfEquipage(), modelBesoin.getIdfPlageHelper()});
		aManager.addBesoinEquipage(modelBesoin);
		addActionMessage("Le besoin a bien été enregistré");
		HoraireManagerLocal horaireManager = (HoraireManagerLocal) session.get("currentHoraireManager");
		idfCycleSelect = horaireManager.getCurrentCycle().getIdfCycle();
		return this.view();
	}
	
	
	public String cancel() {
		HoraireManagerLocal aManager = (HoraireManagerLocal) session.get("currentHoraireManager");
		aManager.abort();
		session.remove("currentBesoinManager");
		return SUCCESS;
	}
	
    //---------- Implementation ScopedModelDriven --------------
 	public String getScopeKey() {
		return scopeKey;
	}
	
	public void setModel(BesoinEquipageHelper arg0) {
		modelBesoin = arg0;
	}
	
	public void setScopeKey(String arg0) {
		scopeKey = arg0;
	}
	
	public BesoinEquipageHelper getModel() {
		return modelBesoin;
	}
	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings("unchecked")
	public Map getSession() {
		return session;
	}
	// ------------Getters / Setters --------

	public List<EmploiOperationnel> getLstEmploi() {
		return lstEmploi;
	}

	/**
	 * @return the idfCycleSelect
	 */
	public Integer getIdfCycleSelect() {
		return idfCycleSelect;
	}


	/**
	 * @param idfCycleSelect the idfCycleSelect to set
	 */
	public void setIdfCycleSelect(Integer idfCycleSelect) {
		this.idfCycleSelect = idfCycleSelect;
	}


	public void setLstEmploi(List<EmploiOperationnel> lstEmploi) {
		this.lstEmploi = lstEmploi;
	}

	public Integer getIdfEmploi() {
		return idfEmploi;
	}

	public void setIdfEmploi(Integer idfEmploi) {
		this.idfEmploi = idfEmploi;
	}

	public Integer getIdfBesoinDelete() {
		return idfBesoinDelete;
	}

	public void setIdfBesoinDelete(Integer idfBesoinDelete) {
		this.idfBesoinDelete = idfBesoinDelete;
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
	 * @return the idfBesoinSelected
	 */
	public Integer getIdfBesoinSelected() {
		return idfBesoinSelected;
	}

	/**
	 * @param idfBesoinSelected the idfBesoinSelected to set
	 */
	public void setIdfBesoinSelected(Integer idfBesoinSelected) {
		this.idfBesoinSelected = idfBesoinSelected;
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
	 * @return the lstBesoin
	 */
	public List<BesoinEquipageHelper> getLstBesoin() {
		return lstBesoin;
	}


	/**
	 * @param lstBesoin the lstBesoin to set
	 */
	public void setLstBesoin(List<BesoinEquipageHelper> lstBesoin) {
		this.lstBesoin = lstBesoin;
	}


	/**
	 * @return the plage
	 */
	public PlageHoraireHelper getPlage() {
		return plage;
	}


	/**
	 * @param plage the plage to set
	 */
	public void setPlage(PlageHoraireHelper plage) {
		this.plage = plage;
	}

}
