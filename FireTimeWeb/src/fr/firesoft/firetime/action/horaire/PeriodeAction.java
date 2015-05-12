/**
 * 
 */
package fr.firesoft.firetime.action.horaire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.opensymphony.xwork2.interceptor.ScopedModelDriven;

//import com.jgeppert.struts2.jquery.tree.result.TreeNode;



import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
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
public class PeriodeAction extends AuthentifiedAction implements ScopedModelDriven<CycleHoraire>{

	Logger log = LoggerFactory.getLogger(PeriodeAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1197076671008848875L;

	private Integer idfCycleSelect;
	private Integer idfPlageSelect;
	private String idViewerSelect;
	private String dateValidite;
	private String libelle;
	private List<CycleHoraireHelper> lstCycle;
	private CycleHoraire currentCycle;

	private String scopeKey;
	//private List<TreeNode> nodes = new ArrayList<TreeNode>();
    /**
	 * 
	 */
	public PeriodeAction() {
	}
	
	public String view() {
		log.debug ("Echelon " +super.getIdfEchelon());
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstCycle = dao.makeListForEchelon( super.getIdfEchelon() );
		return SUCCESS;
	}
	public String create() {
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		//getSession().put("currentHoraireManager", aManager);
		return SUCCESS;
	}
	
	public String viewPeriode() {
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		currentCycle = aManager.selectCycle(idfCycleSelect);
		return SUCCESS;		
	}
	
	public String selectPeriode() {
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		currentCycle = aManager.selectCycle(idfCycleSelect);
		//getSession().put("currentHoraireManager", aManager);
		return SUCCESS;
	}
	
	
	public String save() {
		log.debug("Saving {}", libelle);
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		//SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		aManager.save(currentCycle.getIdfCycle(), currentCycle.getLibelle(), currentCycle.getDateValidite(), super.getIdfEchelon());
		//aManager.save(libelle, sdFormat.parse(dateValidite), super.getIdfEchelon());
		lstCycle = dao.makeListForEchelon( super.getIdfEchelon() );
		//getSession().put("lstCycle", lstCycle);
		return SUCCESS;
	}
	
	
	// --------- Private methods ---------------
	/*
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
	*/
	private List<TreeNode> makePlageNode(Integer idfCycle) {
		List<TreeNode> lstNodePlage = new ArrayList<TreeNode>();
		HoraireManagerLocal manager = ServiceLocatorFireTime.getHoraireManagerBean();
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
		}
		return lstNodeStatut;
	}
    //---------- Implementation ScopedModelDriven --------------
	public String getScopeKey() {
		log.debug("scopeKey is {}", scopeKey);
		return scopeKey;
	}

	public void setModel(CycleHoraire arg0) {
		log.debug("New model is {} ", arg0.getLibelle());
		currentCycle = arg0;
	}

	public void setScopeKey(String arg0) {
		scopeKey = arg0;
	}

	public CycleHoraire getModel() {
		if (currentCycle != null)
			log.debug("Model is {}", currentCycle.getLibelle());
		else
			log.debug("No model !!!");
		return currentCycle;
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
	 * @return the dateValidite
	 */
	public String getDateValidite() {
		return dateValidite;
	}
	/**
	 * @param dateValidite the dateValidite to set
	 */
	public void setDateValidite(String dateValidite) {
		this.dateValidite = dateValidite;
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
	 * @param lstPlageViewer the lstPlageViewer to set
	 *
	public void setLstPlageViewer(List<PlageHoraireViewer> lstPlageViewer) {
		this.lstPlageViewer = lstPlageViewer;
	}

	/**
	 * @return the idfPlageSelect
	 */
	public Integer getIdfPlageSelect() {
		return idfPlageSelect;
	}

	/**
	 * @param idfPlageSelect the idfPlageSelect to set
	 */
	public void setIdfPlageSelect(Integer idfPlageSelect) {
		this.idfPlageSelect = idfPlageSelect;
	}

	/**
	 * @return the idViewerSelect
	 */
	public String getIdViewerSelect() {
		return idViewerSelect;
	}

	/**
	 * @param idViewerSelect the idViewerSelect to set
	 */
	public void setIdViewerSelect(String idViewerSelect) {
		this.idViewerSelect = idViewerSelect;
	}
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
	 * @return the currentCycle
	 */
	public CycleHoraire getCurrentCycle() {
		return currentCycle;
	}

	/**
	 * @param currentCycle the currentCycle to set
	 */
	public void setCurrentCycle(CycleHoraire currentCycle) {
		this.currentCycle = currentCycle;
	}

	
}
