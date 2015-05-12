/**
 * 
 */
package fr.firesoft.firetime.action;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.bean.PeriodeDaoLocal;
import fr.firesoft.fireTime.bean.PlanningManagerLocal;
import fr.firesoft.fireTime.entity.CompetenceAgent;
import fr.firesoft.fireTime.entity.effectif.Affectation;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.effectif.Filiere;
import fr.firesoft.fireTime.helper.AgentHelper;
import fr.firesoft.fireTime.helper.PeriodeAgregator;

/**
 * @author xbeaufils
 *
 */
public class EffectifAction extends AuthentifiedAction implements SessionAware {

	public static Logger log = LoggerFactory.getLogger(EffectifAction.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -5480560984745548507L;

	private Integer idfAgent;
	private Integer month;
	private String monthString;
	private Integer year;
	private Long cumulatedHours; 
	private DateFormatSymbols symbolMonth;
	private List<PeriodeAgregator> lstAgregat;
	private List<Affectation>lstAffectations;
	private List<CompetenceAgent> lstCompetence;
	/**
	 * 
	 */
	public EffectifAction() {
		symbolMonth = new DateFormatSymbols();
	}
	
	@SuppressWarnings("unchecked")
	public String makeListAgent() {
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		Calendar today = Calendar.getInstance();
		year = today.get(Calendar.YEAR);
		getSession().put("currentYear", year);
		ArrayList<Filiere>lstFiliere = new ArrayList<Filiere>();
		lstFiliere.add(Filiere.SPP);
		lstFiliere.add(Filiere.SPV);
		List<AgentHelper> lstAgent = aDaoEffectif.selectForDateAndEchelon(
				super.getIdfEchelon(), 
				today.getTime());
		log.debug("Searching agents for echelon {} date {} intervalle {} Filiere {}",new Object[]{super.getIdfEchelon(), today.getTime(), Calendar.YEAR,lstFiliere}); 
		log.debug("Nb Agents found {}", lstAgent.size());
		getSession().put("lstAgent", lstAgent);
		getSession().remove("currentAgent");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String select() {
		Calendar today = Calendar.getInstance();
		year = today.get(Calendar.YEAR);
		getSession().put("currentYear", year);
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		Agent currentAgent = aDaoEffectif.select(idfAgent);
		getSession().put("currentAgent", currentAgent);
		return this.viewAffectation();
	}
	
	public String viewCompetence() {
		CompetenceManagerLocal managerCompetence = ServiceLocatorFireTime.getCompetenceManagerBean();
		lstCompetence = managerCompetence.makeListForAgent(idfAgent);
		return SUCCESS;
	}
	
	public String viewAffectation() {
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		lstAffectations = aDaoEffectif.makeListForAgent(idfAgent);
		return SUCCESS;
	}
	public String viewHoraire() {
		year = (Integer) getSession().get("currentYear") ;
		PlanningManagerLocal aPlanningManager = ServiceLocatorFireTime.getPlanningManagerBean();
		lstAgregat = aPlanningManager.makeListForAgent(idfAgent, year);
		return SUCCESS;
	}
	
	public String calculateCumul() {
		year = (Integer) getSession().get("currentYear") ;
		PeriodeDaoLocal aDaoPeriode = ServiceLocatorFireTime.getPeriodeDaoBean();
		cumulatedHours = aDaoPeriode.calculateDureeForAgentAndMonth(idfAgent, month, year);
		monthString = symbolMonth.getMonths()[month];
		log.debug("cumulated hours " + cumulatedHours);
		return SUCCESS;
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
	//-------- Getters/Setters ---------------
	/**
	 * @return the idfAgent
	 */
	public Integer getIdfAgent() {
		return idfAgent;
	}

	/**
	 * @param idfAgent the idfAgent to set
	 */
	public void setIdfAgent(Integer idfAgent) {
		this.idfAgent = idfAgent;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the cumulatedHours
	 */
	public Long getCumulatedHours() {
		return cumulatedHours;
	}

	/**
	 * @param cumulatedHours the cumulatedHours to set
	 */
	public void setCumulatedHours(Long cumulatedHours) {
		this.cumulatedHours = cumulatedHours;
	}

	/**
	 * @return the symbolMonth
	 */
	public DateFormatSymbols getSymbolMonth() {
		return symbolMonth;
	}

	/**
	 * @param symbolMonth the symbolMonth to set
	 */
	public void setSymbolMonth(DateFormatSymbols symbolMonth) {
		this.symbolMonth = symbolMonth;
	}

	/**
	 * @return the monthString
	 */
	public String getMonthString() {
		return monthString;
	}

	/**
	 * @param monthString the monthString to set
	 */
	public void setMonthString(String monthString) {
		this.monthString = monthString;
	}

	/**
	 * @return the lstAgregat
	 */
	public List<PeriodeAgregator> getLstAgregat() {
		return lstAgregat;
	}

	/**
	 * @param lstAgregat the lstAgregat to set
	 */
	public void setLstAgregat(List<PeriodeAgregator> lstAgregat) {
		this.lstAgregat = lstAgregat;
	}

	/**
	 * @return the lstAffectations
	 */
	public List<Affectation> getLstAffectations() {
		return lstAffectations;
	}

	/**
	 * @param lstAffectations the lstAffectations to set
	 */
	public void setLstAffectations(List<Affectation> lstAffectations) {
		this.lstAffectations = lstAffectations;
	}

	/**
	 * @return the lstCompetence
	 */
	public List<CompetenceAgent> getLstCompetence() {
		return lstCompetence;
	}

	/**
	 * @param lstCompetence the lstCompetence to set
	 */
	public void setLstCompetence(List<CompetenceAgent> lstCompetence) {
		this.lstCompetence = lstCompetence;
	}


}
