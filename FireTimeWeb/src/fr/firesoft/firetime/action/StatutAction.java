/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.interceptor.ScopedModelDriven;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.bean.ParametreDaoLocal;
import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.ServiceLocatorExtFireTime;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.Presence;
import fr.firesoft.fireTime.ext.horaire.Statut;

/**
 * @author xbeaufils
 *
 */
public class StatutAction extends AuthentifiedAction implements ScopedModelDriven<Statut> , SessionAware {

	public static Logger log = LoggerFactory.getLogger(StatutAction.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -6082877137021396545L;
	private Integer idfStatutSelected;
	private Integer idfCycleSelect;
	private Statut statut;
	private String scopeKey;
    private List<Presence> lstPresence;
    private List<Integer> lstHours;
    private List<Integer> lstMinutes;
    private List<PlageHoraireHelper> lstPlage;
    private List<StatutPeriode> lstStatut;
    private List<CycleHoraire> lstCycle;
	//private List<Integer> lstIdfPlage;
   	/**
	 * 
	 */
	public StatutAction() {;
		lstPresence = new ArrayList<Presence>();
		lstPresence.add(Presence.PRESENT);
		lstPresence.add(Presence.ABSENT);
		lstPresence.add(Presence.DISPO);
	}
	
	public String create() {
		//CycleHoraire currentCycle = (CycleHoraire) getSession().get("currentCycle");
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstPlage = dao.makeListPlageForCycle(idfCycleSelect);
		//statut.setLstPlageHoraire(new ArrayList<PlageHoraire>());
		statut.setLstIdfPlage(new ArrayList<Integer>());
		statut.setIdfStatut(null);
		statut.setLibelle(null);
		statut.setDuree(null);
		statut.setCode(null);
		return SUCCESS;
	}
	
	public String select() {
		ParametreDaoLocal aDao = ServiceLocatorFireTime.getParametreDaoBean();
		log.debug("Select statut {}", idfStatutSelected);
		log.debug("Select statut {}", statut.getIdfStatut());
		statut = aDao.selectHelper(idfStatutSelected);
		setModel(statut);
		//getSession().put("statut", statut);
		log.debug("Select statut {}", statut.getLibelle());
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstPlage = dao.makeListPlageForCycle(statut.getIdfCycle());
		return SUCCESS;
	}
	
	public String save() {
		log.debug("Statut plage" + statut.getLstIdfPlage());
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		//idfCycleSelect = aManager.getCurrentCycle().getIdfCycle();
		aManager.savePlageForStatut(statut);
		addActionMessage(getText("ok.save"));
		lstStatut = aManager.makeListStatutForCycle(statut.getIdfCycle());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String view() {
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		lstStatut = aManager.makeListStatutForCycle(idfCycleSelect);
		CycleHoraire currentCycle = aManager.selectCycle(idfCycleSelect);
		getSession().put("currentCycle", currentCycle);
		getSession().put("currentManager", aManager);
		return SUCCESS;
	}
	
	public String cancel() {
		lstPlage = null;
		return SUCCESS;
	}
    //---------- Implementation ScopedModelDriven --------------
	public String getScopeKey() {
		log.debug("scopeKey is {}", scopeKey);
		return scopeKey;
	}

	public void setModel(Statut arg0) {
		log.debug("New model is {} ", arg0.getLibelle());
		statut = arg0;
	}

	public void setScopeKey(String arg0) {
		scopeKey = arg0;
	}

	public Statut getModel() {
		if (statut != null)
			log.debug("Model is {}", statut.getLibelle());
		else
			log.debug("No model !!!");
		return statut;
	}
    //---------- Implementation SessionAware --------------
    private Map<?, ?> session;
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void setSession(Map arg0) {
		session  = arg0;		
	}

    @SuppressWarnings("rawtypes")
    public Map getSession() {
        return session;
    }
	// ------------Getters / Setters --------
	/**
	 * @return the lstPresence
	 */
	public List<Presence> getLstPresence() {
		return lstPresence;
	}

	/**
	 * @param lstPresence the lstPresence to set
	 */
	public void setLstPresence(List<Presence> lstPresence) {
		this.lstPresence = lstPresence;
	}

	/**
	 * @return the lstHours
	 */
	public List<Integer> getLstHours() {
		return lstHours;
	}

	/**
	 * @param lstHours the lstHours to set
	 */
	public void setLstHours(List<Integer> lstHours) {
		this.lstHours = lstHours;
	}

	/**
	 * @return the lstMinutes
	 */
	public List<Integer> getLstMinutes() {
		return lstMinutes;
	}

	/**
	 * @param lstMinutes the lstMinutes to set
	 */
	public void setLstMinutes(List<Integer> lstMinutes) {
		this.lstMinutes = lstMinutes;
	}

	/**
	 * @return the lstStatut
	 */
	public List<StatutPeriode> getLstStatut() {
		return lstStatut;
	}

	/**
	 * @param lstStatut the lstStatut to set
	 */
	public void setLstStatut(List<StatutPeriode> lstStatut) {
		this.lstStatut = lstStatut;
	}

	/**
	 * @return the idfStatutSelected
	 */
	public Integer getIdfStatutSelected() {
		return idfStatutSelected;
	}

	/**
	 * @param idfStatutSelected the idfStatutSelected to set
	 */
	public void setIdfStatutSelected(Integer idfStatutSelected) {
		this.idfStatutSelected = idfStatutSelected;
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

	/**
	 * @return the lstPlage
	 */
	public List<PlageHoraireHelper> getLstPlage() {
		return lstPlage;
	}

	/**
	 * @param lstPlage the lstPlage to set
	 */
	public void setLstPlage(List<PlageHoraireHelper> lstPlage) {
		this.lstPlage = lstPlage;
	}

	/**
	 * @return the lstCycle
	 */
	public List<CycleHoraire> getLstCycle() {
		return lstCycle;
	}

	/**
	 * @param lstCycle the lstCycle to set
	 */
	public void setLstCycle(List<CycleHoraire> lstCycle) {
		this.lstCycle = lstCycle;
	}

/*	*//**
	 * @return the lstIdfPlage
	 *//*
	public List<Integer> getLstIdfPlage() {
		return lstIdfPlage;
	}

	*//**
	 * @param lstIdfPlage the lstIdfPlage to set
	 *//*
	public void setLstIdfPlage(List<Integer> lstIdfPlage) {
		this.lstIdfPlage = lstIdfPlage;
	}
*/

}
