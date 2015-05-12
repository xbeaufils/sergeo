/**
 * 
 */
package fr.firesoft.firetime.action.horaire;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.ext.ServiceLocatorExtFireTime;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufils
 *
 */
public class StatutJsonAction extends AuthentifiedAction  {

	public static Logger log = LoggerFactory.getLogger(StatutJsonAction.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -6082877137021396545L;
	private Integer idfStatut;
	private String presence;
	private String libelle;
	private String code;
	private Integer duree;
	private List<Integer> lstIdfPlage;
	
    private List<Statut> lstStatut;
    private List<PlageHoraireHelper> lstPlage;
    
	private Integer idfStatutSelected;
	private Integer idfCycleSelect;
	//private List<Integer> lstIdfPlage;
	private String id;
	private String oper;
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
  	/**
	 * 
	 */
	public StatutJsonAction() {;
	}
	
	public String viewListStatut() {
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstStatut = dao.makeListStatutHelperForCycle(idfCycleSelect);
		return SUCCESS;
	}
	
	public String viewListPlage() {
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		lstPlage = aManager.makeListForStatut( Integer.parseInt(id));
		return SUCCESS;
	}
	
	public String select() {
		return SUCCESS;
	}
	
	public String save() {
		log.debug("Statut statut {} operation {} ", new Object []{libelle, oper});
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		//lstPlage = aManager.makeListPlageForCycle(idfCycleSelect);
		//idfCycleSelect = aManager.getCurrentCycle().getIdfCycle();
		if (oper.equalsIgnoreCase("add") 
				|| oper.equalsIgnoreCase("edit")) {
			aManager.saveStatut(idfStatut, presence, libelle, code, duree, idfCycleSelect);
		}
		if (oper.equalsIgnoreCase("del") ){
			aManager.deleteStatut(idfStatut);
		}
		// lstStatut = aManager.makeListStatutForCycle(currentCycle.getIdfCycle());
		return SUCCESS;
	}
	// ------------Getters / Setters --------


	/**
	 * @return the idfStatut
	 */
	public Integer getIdfStatut() {
		return idfStatut;
	}


	/**
	 * @param idfStatut the idfStatut to set
	 */
	public void setIdfStatut(String idfStatut) {
		try {
			this.idfStatut = Integer.parseInt(idfStatut);
		}
		catch (NumberFormatException e) {
			log.warn("Trying to set idfStatut with {}", idfStatut);
		}
	}


	/**
	 * @return the presence
	 */
	public String getPresence() {
		return presence;
	}


	/**
	 * @param presence the presence to set
	 */
	public void setPresence(String presence) {
		this.presence = presence;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @return the duree
	 */
	public Integer getDuree() {
		return duree;
	}


	/**
	 * @param duree the duree to set
	 */
	public void setDuree(Integer duree) {
		this.duree = duree;
	}


	/**
	 * @return the lstIdfPlage
	 */
	public List<Integer> getLstIdfPlage() {
		return lstIdfPlage;
	}


	/**
	 * @param lstIdfPlage the lstIdfPlage to set
	 */
	public void setLstIdfPlage(List<Integer> lstIdfPlage) {
		this.lstIdfPlage = lstIdfPlage;
	}


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

	/**
	 * @return the idfStatutSelected
	 */
	public Integer getIdfStatutSelected() {
		return idfStatutSelected;
	}


	/**
	 * @param idfStatutSelected the idfStatutSelected to set
	 */
	public void setIdfStatutSelected(Integer idfStatutSelected) {
		this.idfStatutSelected = idfStatutSelected;
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
	 * @return the lstStatut
	 */
	public List<Statut> getLstStatut() {
		return lstStatut;
	}

	/**
	 * @param lstStatut the lstStatut to set
	 */
	public void setLstStatut(List<Statut> lstStatut) {
		this.lstStatut = lstStatut;
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

}
