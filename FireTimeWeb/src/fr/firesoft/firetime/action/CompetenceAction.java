/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;

/**
 * @author xbeaufils
 *
 */
public class CompetenceAction extends AuthentifiedAction
	implements ModelDriven<CompetenceAgentHelper>, SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3496639970900330974L;

	private List<EmploiOperationnel> lstEmploi;
	private CompetenceAgentHelper competence;
	private Integer idfSearchCompetence;
	/**
	 * 
	 */
	public CompetenceAction() {
	}
	public String prepare() {
		CompetenceManagerLocal aManager = ServiceLocatorFireTime.getCompetenceManagerBean();
		lstEmploi = aManager.makeListEmploiOperationel();
		return SUCCESS;
	} 
	
	public String select() {
		CompetenceManagerLocal aManager = ServiceLocatorFireTime.getCompetenceManagerBean();
		lstEmploi = aManager.makeListEmploiOperationel();
		CompetenceAgentHelper helper =  aManager.selectCompetence(idfSearchCompetence);
		competence.setIdfCompetenceAgent(helper.getIdfCompetenceAgent());
		competence.setIdfAgent(helper.getIdfAgent());
		competence.setIdfEmploiOpe(helper.getIdfEmploiOpe());
		competence.setDebut(helper.getDebut());
		competence.setFin(helper.getFin());
		return SUCCESS;
	}
	
	public String save() {
		CompetenceManagerLocal aManager = ServiceLocatorFireTime.getCompetenceManagerBean();
		aManager.saveCompetence(competence); 
		return SUCCESS;
	}
	//---------- Implementation ModelDriven --------------	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public CompetenceAgentHelper getModel() {
		competence = new CompetenceAgentHelper();
		return competence;
	}

 	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings({ "rawtypes" })
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings({ "rawtypes" })
	public Map getSession() {
		return session;
	}
	
	//-------- Getters/Setters ---------------
	/**
	 * @return the lstEmploi
	 */
	public List<EmploiOperationnel> getLstEmploi() {
		return lstEmploi;
	}
	/**
	 * @param lstEmploi the lstEmploi to set
	 */
	public void setLstEmploi(List<EmploiOperationnel> lstEmploi) {
		this.lstEmploi = lstEmploi;
	}
	/**
	 * @return the idfSearchCompetence
	 */
	public Integer getIdfSearchCompetence() {
		return idfSearchCompetence;
	}
	/**
	 * @param idfSearchCompetence the idfSearchCompetence to set
	 */
	public void setIdfSearchCompetence(Integer idfSearchCompetence) {
		this.idfSearchCompetence = idfSearchCompetence;
	}
	
	
}
