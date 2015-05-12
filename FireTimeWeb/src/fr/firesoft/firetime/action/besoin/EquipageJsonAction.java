/**
 * 
 */
package fr.firesoft.firetime.action.besoin;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.SMDMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;



import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AgresManagerLocal;
import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.helper.PosteHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufils
 *
 */
public class EquipageJsonAction extends AuthentifiedAction implements SessionAware {
		
	
	Logger log = LoggerFactory.getLogger(EquipageJsonAction.class);
	
	private Integer idfEquipage;
	private Integer idfEquipageSelected;
	private Integer idfEmploi;
	private Integer idfPoste;
	private boolean optionnel;
	
	private List<PosteHelper> lstPoste;
	private List<EquipageHelper> lstEquipage;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	public EquipageJsonAction() {
	}

	public String view() {
		BesoinManagerLocal aManager = ServiceLocatorFireTime.getBesoinManagerBean();
		lstPoste = aManager.makeLisPosteForEchelon(super.getIdfEchelon());
		lstEquipage = aManager.makeListEquipageForEchelon(super.getIdfEchelon());
		return SUCCESS;
	}
	public String viewPoste() {
		AgresManagerLocal aManager = ServiceLocatorFireTime.getAgresManagerBean();
		lstPoste = aManager.makeListPosteHelperForEquipage( Integer.parseInt(id) );
		return SUCCESS;
	}
	
    public String smd() {
        return Action.SUCCESS;
    }
     
	public String editPoste() {
		if (oper.equalsIgnoreCase("del")) {
			log.debug("Removing emploi {}", idfPoste);
			AgresManagerLocal aManager = ServiceLocatorFireTime.getAgresManagerBean();
			aManager.removeEmploi(idfPoste);
		}
		return SUCCESS;
	}
	@SMDMethod
	public String changeRow(Integer idfPoste, Integer rowNum) {
		log.debug("moving emploi {} to row {}", new Object[]{idfPoste, rowNum});
		AgresManagerLocal aManager = ServiceLocatorFireTime.getAgresManagerBean();
		aManager.changeOrder(idfPoste, rowNum);
		return SUCCESS;
	}
 	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings({  "rawtypes" })
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}
	// ------------Getters / Setters --------
	/**
	 * @return the lstPoste
	 */
	public List<PosteHelper> getLstPoste() {
		return lstPoste;
	}

	/**
	 * @param lstPoste the lstPoste to set
	 */
	public void setLstPoste(List<PosteHelper> lstPoste) {
		this.lstPoste = lstPoste;
	}

	/**
	 * @return the idfEquipageSelected
	 */
	public Integer getIdfEquipageSelected() {
		return idfEquipageSelected;
	}


	/**
	 * @param idfEquipageSelected the idfEquipageSelected to set
	 */
	public void setIdfEquipageSelected(Integer idfEquipageSelected) {
		this.idfEquipageSelected = idfEquipageSelected;
	}

	public Integer getIdfEmploi() {
		return idfEmploi;
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

	public void setIdfEmploi(Integer idfEmploi) {
		this.idfEmploi = idfEmploi;
	}

	public boolean isOptionnel() {
		return optionnel;
	}

	public void setOptionnel(boolean optionnel) {
		this.optionnel = optionnel;
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
	 * @return the idfEquipage
	 */
	public Integer getIdfEquipage() {
		return idfEquipage;
	}

	/**
	 * @param idfEquipage the idfEquipage to set
	 */
	public void setIdfEquipage(Integer idfEquipage) {
		this.idfEquipage = idfEquipage;
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

}
