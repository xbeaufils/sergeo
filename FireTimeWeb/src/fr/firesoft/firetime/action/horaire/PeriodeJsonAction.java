/**
 * 
 */
package fr.firesoft.firetime.action.horaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;

//import com.jgeppert.struts2.jquery.tree.result.TreeNode;



import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.ServiceLocatorExtFireTime;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.horaire.CycleHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;

/**
 * @author xbeaufilsAuthentifiedAction
 *
 */
// @ParentPackage(value = "showcase" )
public class PeriodeJsonAction extends AuthentifiedAction {

	Logger log = LoggerFactory.getLogger(PeriodeJsonAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1197076671008848875L;
	private List<CycleHoraireHelper> lstCycle;
    private List<StatutPeriode> lstStatut;
    private Integer idfCycleSelect;
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

	  private List<TreeNode> nodes = new ArrayList<TreeNode>();
    /**
	 * 
	 */
	public PeriodeJsonAction() {
	}
	
	public String view() {
		log.debug ("Echelon " +super.getIdfEchelon());
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstCycle = dao.makeListForEchelon( super.getIdfEchelon() );
		return SUCCESS;
	}
/*	
    @Actions( { @Action(value = "/json-tree-data", 
    		results = { 
    			@Result(name = "success", type = "json", 
    					params = {
    						"root", "nodes" }) }) })
   */
	public String viewTree() {
		
		if (id.equalsIgnoreCase("0"))
			// On renvoit la liste des cycles
			nodes = this.makeCycleNode();
		else {
			String[] realId = id.split("-");
			if (realId[0].equalsIgnoreCase("C")){
				// C'est un cycle, trouvons les Plages horaires
				Integer idfCycle = Integer.parseInt(realId[1]);
				nodes = this.makePlageNode(idfCycle);
	            nodes.addAll(this.makeListStatutPeriode(idfCycle));
			}
			else if (realId[0].equalsIgnoreCase("P")){;
				// C'est une plage horaire
			}
			else if (realId[0].equalsIgnoreCase("S")) {
				
			}
		}
		return SUCCESS;
	}
	//@SuppressWarnings()
	public String viewListStatut() {
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		lstStatut = aManager.makeListStatutForCycle(idfCycleSelect);
		return SUCCESS;
	}
	
	// --------- Private methods ---------------
	private List<TreeNode> makeCycleNode() {
		log.debug("Nb nodes before populate {} ", nodes.size());
		log.debug ("Echelon " +super.getIdfEchelon());
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstCycle = dao.makeListForEchelon( super.getIdfEchelon() );
		List<TreeNode> lstNodeCycle = new ArrayList<TreeNode>();
		for (CycleHoraireHelper cycle: lstCycle) {
			TreeNode node = new TreeNode();
            node.setId("C-" + cycle.getIdfCycle());
            node.setState(TreeNode.NODE_STATE_CLOSED);
            node.setTitle(cycle.getLibelle());
            node.setType("CYCLE");
            lstNodeCycle.add(node);
		}
		return lstNodeCycle;
	}
	
	private List<TreeNode> makePlageNode(Integer idfCycle) {
		List<TreeNode> lstNodePlage = new ArrayList<TreeNode>();
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		List<PlageHoraireHelper> lstPlage = dao.makeListPlageForCycle( idfCycle );
		for (PlageHoraireHelper plage : lstPlage) {
			TreeNode node = new TreeNode();
            node.setId("P-" + plage.getIdfPlage());
            node.setState(TreeNode.NODE_STATE_LEAF);
            node.setTitle(plage.getLibelle());
            node.setType("PLAGE");
            node.setIcon("../images/clock.png");
            lstNodePlage.add(node);			
		}
		return lstNodePlage;
	}
	
	private List<TreeNode> makeListStatutPeriode(Integer idfCycle) {
		List<TreeNode> lstNodeStatut = new ArrayList<TreeNode>();
		/*
		HoraireManagerLocal manager = ServiceLocatorFireTime.getHoraireManagerBean();
		List<StatutPeriode> lstStatut = manager.makeListStatutForCycle(idfCycle);
		for (StatutPeriode statut: lstStatut) {
			TreeNode node = new TreeNode();
            node.setId("S-" + statut.getIdfStatut());
            node.setState(TreeNode.NODE_STATE_LEAF);
            node.setTitle(statut.getLibelle());
            node.setType("STATUT");
            node.setIcon("../images/plugin.png");
 			lstNodeStatut.add(node);
		}*/
		return lstNodeStatut;
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
	// ------------Getters / Setters --------
	/*
	 * Needed for grid
	 */

	/**
	 * @return the lstCycle
	 */
	public List<CycleHoraireHelper> getLstCycle() {
		return lstCycle;
	}

	/**
	 * @param lstCycle the lstCycle to set
	 */
	public void setLstCycle(List<CycleHoraireHelper> lstCycle) {
		this.lstCycle = lstCycle;
	}

	/**
	 * @return the lstStatut
	 */
	public List<StatutPeriode> getLstStatut() {
		return lstStatut;
	}

	/**
	 * @param lstStatut the lstStatut to set
	 */
	public void setLstStatut(List<StatutPeriode> lstStatut) {
		this.lstStatut = lstStatut;
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

	// ------------- Neede for tree jquery plugin -----
	/**
	 * @return the nodes
	 */
	public List<TreeNode> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}
	
}
