/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.interceptor.ScopedModelDriven;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.CycleManagerLocal;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.horaire.CycleTravail;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;

/**
 * @author xbeaufils
 *
 */
public class CycleAction extends AuthentifiedAction 
	implements SessionAware, ScopedModelDriven<CycleTravail> , RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6614420701383799378L;
    private List<StatutPeriode> lstStatut;
    private CycleTravail model;
    private String scopeKey;
	private Integer idfCycleSelect;
	private Integer idfStatut;

	/**
	 * 
	 */
	public CycleAction() {
	}

	@SuppressWarnings("unchecked")
	public String view() {
		// CycleHoraire currentCycle = (CycleHoraire) getSession().get("currentCycle");
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		CycleHoraire currentCycle = aManager.selectCycle(idfCycleSelect);
		CycleManagerLocal cycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		List<CycleTravail> lstCycle = cycleManager.makeListForCycle(idfCycleSelect);
		model.setCycle(currentCycle);
		getSession().put("lstCycleTravail", lstCycle);
		lstStatut = aManager.makeListStatutForCycle(idfCycleSelect);
		getSession().put("lstStatut", lstStatut);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String create() {
		model.setIdfCycle(null);
		getRequest().put("viewCycle", "cycle");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String select() {
		CycleManagerLocal cycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		CycleTravail aCycle = cycleManager.select(idfCycleSelect);
		model.setIdfCycle(aCycle.getIdfCycle());
		model.setLibelle(aCycle.getLibelle());
		getRequest().put("viewCycle", "cycle");
		return SUCCESS;
	}
	
	public String save() {
		CycleManagerLocal cycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		cycleManager.save(model);
		return SUCCESS;
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

    //---------- Implementation RequestAware --------------
    private Map<?, ?> request;
   /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.RequestAware#setRequest(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void setRequest(Map arg0) {
		request = arg0;	
	}
	/**
	 * @return the request
	 */
	@SuppressWarnings("rawtypes")
	public Map getRequest() {
		return request;
	}

	//---------- Implementation ScopedModelDriven --------------
    /* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public CycleTravail getModel() {
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
	public void setModel(CycleTravail arg0) {
		model = arg0;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.ScopedModelDriven#setScopeKey(java.lang.String)
	 */
	public void setScopeKey(String arg0) {
		scopeKey = arg0;
		
	}
	// ------------Getters / Setters --------

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
	 * @return the idfStatut
	 */
	public Integer getIdfStatut() {
		return idfStatut;
	}

	/**
	 * @param idfStatut the idfStatut to set
	 */
	public void setIdfStatut(Integer idfStatut) {
		this.idfStatut = idfStatut;
	}

}
