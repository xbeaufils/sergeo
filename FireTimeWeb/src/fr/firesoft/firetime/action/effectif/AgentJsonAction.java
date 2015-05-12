/**
 * 
 */
package fr.firesoft.firetime.action.effectif;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.helper.AgentHelper;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufils
 *
 */
public class AgentJsonAction extends AuthentifiedAction implements SessionAware {


	private List<AgentHelper> lstAgent;
	private List<CompetenceAgentHelper> lstCompetence;
	private Integer idfCompetenceAgentSelected;
	private Integer idfAgentSelected; 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7085921385065157892L;
	Logger log = LoggerFactory.getLogger(AgentJsonAction.class);

	private String id;
	//get how many rows we want to have into the grid - rowNum attribute in the grid
	private Integer             rows             = 0;
	//Get the requested page. By default grid sets this to 1.
	private Integer             page             = 0;
	// sorting order - asc or desc
	private String              sord;
	// get index row - i.e. user click to sort.
	private String              sidx;
	// Search Field
	private String              searchField;
	// The Search String
	private String              searchString;
	// he Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String              searchOper;
	// The operation 'add' or 'edit'
	private String oper;
	private String rowid;
	/**
	 * 
	 */
	public AgentJsonAction() {
	}
	
	public String makeListAgent() {
		// Construction de la liste des agentsrawtypesrawtypesrawtypes
		EffectifDaoLocal aDaoAgent = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		Calendar today = Calendar.getInstance();
		lstAgent = aDaoAgent.selectForDateAndEchelon(super.getIdfEchelon(), today.getTime());
		if (sidx.equalsIgnoreCase("nom"))
			Collections.sort(lstAgent, new NomAgentComparator());
		if (sidx.equalsIgnoreCase("grade.libelle"))
			Collections.sort(lstAgent, new GradeAgentComparator());
		log.debug("Nb Agents found {}", lstAgent.size());
		return SUCCESS;
	}
	
	
	public String makeListCompetenceForAgent() {
		CompetenceManagerLocal managerCompetence = ServiceLocatorFireTime.getCompetenceManagerBean();
		lstCompetence = managerCompetence.makeListHelperForAgent(Integer.parseInt(id));		
		return SUCCESS;
	}
	
	public String deleteCompetence() {
		CompetenceManagerLocal managerCompetence = ServiceLocatorFireTime.getCompetenceManagerBean();
		managerCompetence.deleteCompetence(idfCompetenceAgentSelected);
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

	public List<AgentHelper> getLstAgent() {
		return lstAgent;
	}

	public void setLstAgent(List<AgentHelper> lstAgent) {
		this.lstAgent = lstAgent;
	}


	/**
	 * @return the lstCompetence
	 */
	public List<CompetenceAgentHelper> getLstCompetence() {
		return lstCompetence;
	}

	/**
	 * @param lstCompetence the lstCompetence to set
	 */
	public void setLstCompetence(List<CompetenceAgentHelper> lstCompetence) {
		this.lstCompetence = lstCompetence;
	}


	/**
	 * @return the idfCompetenceAgentSelected
	 */
	public Integer getIdfCompetenceAgentSelected() {
		return idfCompetenceAgentSelected;
	}

	/**
	 * @param idfCompetenceAgentSelected the idfCompetenceAgentSelected to set
	 */
	public void setIdfCompetenceAgentSelected(Integer idfCompetenceAgentSelected) {
		this.idfCompetenceAgentSelected = idfCompetenceAgentSelected;
	}

	/**
	 * @return the idfAgentSelected
	 */
	public Integer getIdfAgentSelected() {
		return idfAgentSelected;
	}

	/**
	 * @param idfAgentSelected the idfAgentSelected to set
	 */
	public void setIdfAgentSelected(Integer idfAgentSelected) {
		this.idfAgentSelected = idfAgentSelected;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}

	/**
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * @return the searchField
	 */
	public String getSearchField() {
		return searchField;
	}

	/**
	 * @param searchField the searchField to set
	 */
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the searchOper
	 */
	public String getSearchOper() {
		return searchOper;
	}

	/**
	 * @param searchOper the searchOper to set
	 */
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	/**
	 * @return the oper
	 */
	public String getOper() {
		return oper;
	}

	/**
	 * @param oper the oper to set
	 */
	public void setOper(String oper) {
		this.oper = oper;
	}

	/**
	 * @return the rowid
	 */
	public String getRowid() {
		return rowid;
	}

	/**
	 * @param rowid the rowid to set
	 */
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	/**
	 * @author beaufils
	 *
	 */
	public class NomAgentComparator implements Comparator<AgentHelper> {

		/**
		 * 
		 */
		public NomAgentComparator() {
		}

		public int compare(AgentHelper arg0, AgentHelper arg1) {
			int compar  = arg0.getNom().compareTo(arg1.getNom());
			if (compar != 0)
				return compar;
			return arg0.getPrenom().compareTo(arg1.getPrenom());
		}

	}
	/**
	 * @author beaufils
	 *
	 */
	public class GradeAgentComparator implements Comparator<AgentHelper> {

		/**
		 * 
		 */
		public GradeAgentComparator() {
		}

		public int compare(AgentHelper arg0, AgentHelper arg1) {
			return arg0.getGrade().getLibelle().compareTo(arg1.getGrade().getLibelle());
		}

	}

}
