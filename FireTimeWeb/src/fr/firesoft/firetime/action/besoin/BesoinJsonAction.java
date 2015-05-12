/**
 * 
 */
package fr.firesoft.firetime.action.besoin;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.ext.horaire.BesoinEquipageHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufils
 *
 */
public class BesoinJsonAction extends AuthentifiedAction implements SessionAware {

	Logger log = LoggerFactory.getLogger(BesoinJsonAction.class);
	
	private Integer idfPlage;
	private Integer idfBesoin;
	private List<BesoinEquipageHelper> lstBesoin;
	
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
	private static final long serialVersionUID = 5896640266614967848L;

	/**
	 * 
	 */
	public BesoinJsonAction() {
	}

		
	
	public String view() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		lstBesoin = aManager.makeListBesoinForPlage(idfPlage);
		log.debug("view Plage {}", new Object[]{idfPlage});
		//log.debug("view Besoin 0 {}", new Object[]{lstBesoin.get(0).getLibelle()});
		return SUCCESS;
	}
	
	public String viewGrid() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		lstBesoin = aManager.makeListBesoinForPlage(Integer.parseInt(id));
		log.debug("view Plage {}", new Object[]{id});
		//log.debug("view Besoin 0 {}", new Object[]{lstBesoin.get(0).getLibelle()});
		return SUCCESS;
	}
	
	public String delete() {
		BesoinManagerLocal aManager  = ServiceLocatorFireTime.getBesoinManagerBean();
		aManager.deleteBesoinEquipage(idfBesoin);
		return SUCCESS;
	}
	// ------------Getters / Setters --------

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
	 * @return the idfBesoin
	 */
	public Integer getIdfBesoin() {
		return idfBesoin;
	}



	/**
	 * @param idfBesoin the idfBesoin to set
	 */
	public void setIdfBesoin(Integer idfBesoin) {
		this.idfBesoin = idfBesoin;
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



	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

}
