/**
 * 
 */
package fr.firesoft.firetime.action.horaire;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.ext.ServiceLocatorExtFireTime;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufils
 *
 */
public class PlageJsonAction extends AuthentifiedAction implements SessionAware {

	Logger log = LoggerFactory.getLogger(PlageJsonAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1197076671008848875L;

	private List<PlageHoraireHelper> lstPlage;
	
	private Integer idfPlage;
	private String  libelle;
	private Integer heureDebut;
	private Integer minuteDebut;
	private Integer heureFin;
	private Integer minuteFin;
	private Integer idfCycle;
	
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
	public PlageJsonAction() {
	}
	
	
	public String viewPlage() {
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstPlage = dao.makeListPlageForCycle( idfCycle); //Integer.parseInt(id) );
		return SUCCESS;
	}	
	
	public String updatePlage() {
		log.debug("Libelle to update {}", libelle);
		PlageHoraireHelper helper = new PlageHoraireHelper();	
		if (oper.equalsIgnoreCase("edit"))
			helper.setIdfPlage(idfPlage);
		helper.setLibelle(libelle);
		Calendar calDebut = Calendar.getInstance();
		calDebut.set(0, 0, 1, heureDebut, minuteDebut, 0);
		helper.setDebut(calDebut.getTime());
		Calendar calFin = Calendar.getInstance();
		calFin.set(0, 0, 1, heureFin, minuteFin, 0);
		helper.setFin(calFin.getTime());
		helper.setIdfCycle(idfCycle);
		HoraireManagerLocal manager = ServiceLocatorFireTime.getHoraireManagerBean();
		manager.save(helper);
		return SUCCESS;
	}
	
	
	// --------- Private methods ---------------


	
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
	// ------------Getters / Setters --------
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

	/*
	 * Needed for grid
	 */

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
	 * @return the idfPlage
	 */
	public Integer getIdfPlage() {
		return idfPlage;
	}


	/**
	 * @param idfPlage the idfPlage to set
	 *
	public void setIdfPlage(Integer idfPlage) {
		this.idfPlage = idfPlage;
	}

	/**
	 * @param idfPlage the idfPlage to set
	 */
	public void setIdfPlage(String idfPlage) {
		try {
			this.idfPlage = Integer.parseInt(idfPlage);
		}
		catch (NumberFormatException e) {
			log.warn("Trying to set idfPlage with {}", idfPlage);
		}
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}


	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * @return the heureDebut
	 */
	public Integer getHeureDebut() {
		return heureDebut;
	}


	/**
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(Integer heureDebut) {
		this.heureDebut = heureDebut;
	}


	/**
	 * @return the minuteDebut
	 */
	public Integer getMinuteDebut() {
		return minuteDebut;
	}


	/**
	 * @param minuteDebut the minuteDebut to set
	 */
	public void setMinuteDebut(Integer minuteDebut) {
		this.minuteDebut = minuteDebut;
	}


	/**
	 * @return the heureFin
	 */
	public Integer getHeureFin() {
		return heureFin;
	}


	/**
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(Integer heureFin) {
		this.heureFin = heureFin;
	}


	/**
	 * @return the minuteFin
	 */
	public Integer getMinuteFin() {
		return minuteFin;
	}


	/**
	 * @param minuteFin the minuteFin to set
	 */
	public void setMinuteFin(Integer minuteFin) {
		this.minuteFin = minuteFin;
	}


	/**
	 * @return the idfCycle
	 */
	public Integer getIdfCycle() {
		return idfCycle;
	}


	/**
	 * @param idfCycle the idfCycle to set
	 */
	public void setIdfCycle(Integer idfCycle) {
		this.idfCycle = idfCycle;
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
	

}
