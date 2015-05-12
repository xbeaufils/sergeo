/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.Calendar;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;


/**
 * @author xavier
 *
 */
public class WelcomeAction extends AuthentifiedAction 
	implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4673475182958910586L;

	public static final String GRADE_DIRECT  = "grade";
	public static final String EMPLOI_DIRECT = "emploi";
	public static final String CONFIGURATION = "config";
	
	/**
	 * 
	 */
	public WelcomeAction() {
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		Calendar today = Calendar.getInstance();
		EffectifDaoLocal dao = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
/*		List<GradeHelper> lstGrade = dao.makeListGradeForOrganisation(super.getIdfEchelon());
		if (lstGrade.size() == 0) {
			addActionMessage(getText("message.grade"));
			return CONFIGURATION;
		}
		List<EmploiOperationnelHelper> lstEmploi = dao.makeListEmploiOpeForOrganisation(super.getIdfEchelon());
		if (lstEmploi.size() == 0){
			addActionMessage(getText("message.emploi"));
			return CONFIGURATION;
		}
		List<AgentHelper> lstAgent = dao.selectForDateAndEchelon(getIdfEchelon(), today.getTime());
		if (lstAgent.size() == 0) {
			addActionMessage(getText("message.agent"));
			return CONFIGURATION;
		}
*/		return SUCCESS;
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
	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

}
