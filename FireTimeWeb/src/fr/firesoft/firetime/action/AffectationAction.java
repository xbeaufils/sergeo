/**
 * 
 */
package fr.firesoft.firetime.action;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ModelDriven;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.helper.AffectationViewHelper;
import fr.firesoft.fireTime.helper.GradeHelper;

/**
 * @author xbeaufils
 *
 */
public class AffectationAction extends AuthentifiedAction 
	implements ModelDriven<AffectationViewHelper>, SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113465134990667590L;
	public static Logger log = LoggerFactory.getLogger(EffectifAction.class.getName());
	private List<GradeHelper> lstGrade;
	private Integer idfSearchAffectation;
	private AffectationViewHelper affectation;
	
	/**
	 * 
	 */
	public AffectationAction() {
	}
	
	public String prepare() {
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean( (AuthentificationLocal) getSession().get("authentifcationCache") );
		lstGrade = aDaoEffectif.makeListGradeForOrganisation(super.getIdfEchelon());
		Agent currentAgent = (Agent) getSession().get("currentAgent");
		affectation.setIdfAgent(currentAgent.getIdfAgent());
		return SUCCESS;
	} 
	
	public String select() {
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) getSession().get("authentifcationCache"));
		AffectationViewHelper anAffectation = aDaoEffectif.selectAffectationHelper(idfSearchAffectation);
		affectation.setIdfAgent(anAffectation.getIdfAgent());
		affectation.setDebut(anAffectation.getDebut() );
		affectation.setFin( anAffectation.getFin() );
		affectation.setIdfGrade(anAffectation.getIdfGrade());
		affectation.setIdfAffectation(anAffectation.getIdfAffectation());
		log.debug ("Organisation " + getOrganisation());
		lstGrade = aDaoEffectif.makeListGradeForOrganisation(super.getIdfEchelon());
		log.debug ("Grade size " + lstGrade.size());
		return SUCCESS;
	}
	
	public String save() {
		EffectifManagerLocal manager = ServiceLocatorFireTime.getEffectifManagerBean((AuthentificationLocal) session.get("authentifcationCache"));
		try {
			manager.saveAffectation(affectation);
			addActionMessage("Enregistrement effectué");
		}
		catch (ParseException e) {
			addActionError(getText("affectation.error.date"));
			log.warn("Erreur ", e);
		}
		return SUCCESS;
	}
	
	public String delete() {
		EffectifManagerLocal manager =  ServiceLocatorFireTime.getEffectifManagerBean((AuthentificationLocal) session.get("authentifcationCache"));
		manager.deleteAffectation(affectation.getIdfAffectation());
		return SUCCESS;
	}
	
	public String cancel() {
		lstGrade = null;
		return SUCCESS;
	}
	//---------- Implementation ModelDriven --------------	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public AffectationViewHelper getModel() {
		affectation = new AffectationViewHelper();
		return affectation;
	}

 	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings("rawtypes" )
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}
	//-------- Getters/Setters ---------------

	/**
	 * @return the lstGrade
	 */
	public List<GradeHelper> getLstGrade() {
		return lstGrade;
	}

	/**
	 * @param lstGrade the lstGrade to set
	 */
	public void setLstGrade(List<GradeHelper> lstGrade) {
		this.lstGrade = lstGrade;
	}

	/**
	 * @return the idfSearchAffectation
	 */
	public Integer getIdfSearchAffectation() {
		return idfSearchAffectation;
	}

	/**
	 * @param idfSearchAffectation the idfSearchAffectation to set
	 */
	public void setIdfSearchAffectation(Integer idfSearchAffectation) {
		this.idfSearchAffectation = idfSearchAffectation;
	}

}
