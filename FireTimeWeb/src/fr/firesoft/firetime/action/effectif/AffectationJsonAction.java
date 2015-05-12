/**
 * 
 */
package fr.firesoft.firetime.action.effectif;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.entity.effectif.Affectation;
import fr.firesoft.fireTime.helper.AffectationViewHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author beaufils
 *
 */
public class AffectationJsonAction extends AuthentifiedAction implements SessionAware  {

	private static final long serialVersionUID = -5480560984745548507L;

	private List<AffectationViewHelper>lstAffectations;
	private Integer idfAgent;

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
	public AffectationJsonAction() {
		// TODO Auto-generated constructor stub
	}
	public String viewAffectation() {
		idfAgent = Integer.parseInt(id);
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		lstAffectations = aDaoEffectif.makeListViewForAgent(idfAgent);
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
	 * @return the lstAffectations
	 */
	public List<AffectationViewHelper> getLstAffectations() {
		return lstAffectations;
	}
	/**
	 * @param lstAffectations the lstAffectations to set
	 */
	public void setLstAffectations(List<AffectationViewHelper> lstAffectations) {
		this.lstAffectations = lstAffectations;
	}
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

}
