/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.ParametreDaoLocal;
import fr.firesoft.fireTime.bean.PlanningManagerLocal;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.fireTime.ext.planning.CommentDayHelper;
import fr.firesoft.fireTime.ext.planning.JourStatut;
import fr.firesoft.fireTime.ext.planning.PlageStatut;
import fr.firesoft.fireTime.helper.JourAgent;
import fr.firesoft.firetime.viewer.CommentDayViewer;
import fr.firesoft.firetime.viewer.DispoViewer;

/**
 * @author xavier
 *
 *
 */
public class PlanningMensuelAction extends AuthentifiedAction {

	Logger log = LoggerFactory.getLogger(PlanningMensuelAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 4627706762463487222L;
	private JourAgent managedDay;
	private Integer idAgent;
	private Integer jour;
	private Integer idStatut;
	private Integer selectedMonth;
	private Integer selectedYear;
	private List<JourAgent> lstJour;
	private List<DispoViewer> lstDispo;
	private List<CommentDayViewer> lstComment;
	/**
	 * 
	 */
	public PlanningMensuelAction() {
	}
	
	public String affecteStatut() {
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");
		Calendar aJour = Calendar.getInstance();
		aJour.set(Calendar.DAY_OF_MONTH, this.jour);
		aJour.set(Calendar.MONTH, currentPlanner.getCurrentCalendar().get(Calendar.MONTH));
		aJour.set(Calendar.YEAR, currentPlanner.getCurrentCalendar().get(Calendar.YEAR));
		log.debug("Echelon " + super.getIdfEchelon() );
		Integer idfPeriode = currentPlanner.setStatut(idAgent, aJour, idStatut,super.getIdfEchelon());
    	ParametreDaoLocal aDao = ServiceLocatorFireTime.getParametreDaoBean();
    	managedDay = new JourAgent();
    	managedDay.setIdfAgent(idAgent);
    	managedDay.setIdfPeriode(idfPeriode);
    	managedDay.setJour(jour);
    	StatutPeriode aStatutPeriode = aDao.select(idStatut);
    	managedDay.setStatut(this.make(aStatutPeriode));
		return SUCCESS;
	}
	
    public String deleteStatut() {
      	PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");
		Calendar aJour = Calendar.getInstance();
		aJour.set(Calendar.DAY_OF_MONTH, this.jour);
		aJour.set(Calendar.MONTH,  currentPlanner.getCurrentCalendar().get(Calendar.MONTH));
		aJour.set(Calendar.YEAR,  currentPlanner.getCurrentCalendar().get(Calendar.YEAR));
  		log.debug("Suppression de {} pour {}", new Object[] {this.jour, idAgent} );
    	managedDay = new JourAgent();
    	managedDay.setIdfAgent(idAgent);
    	managedDay.setJour(jour);
    	managedDay.setStatut(null);
    	managedDay.setIdfPeriode( currentPlanner.deleteStatut(idAgent, aJour, super.getIdfEchelon()));  	
      	return SUCCESS;
    }
    

	public String makeMonth() {
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");;
		selectedMonth = currentPlanner.getCurrentCalendar().get(Calendar.MONTH) ;
		selectedYear = currentPlanner.getCurrentCalendar().get(Calendar.YEAR);
		lstJour = currentPlanner.makeListForMonth(selectedMonth, selectedYear, super.getIdfEchelon());
		return SUCCESS;
	}
	
	public String makeDispoMonth() {
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");;
		selectedMonth = currentPlanner.getCurrentCalendar().get(Calendar.MONTH) ;
		selectedYear = currentPlanner.getCurrentCalendar().get(Calendar.YEAR);
		List<JourStatut> lstJour = currentPlanner.makeListDispoForMonth(selectedMonth, selectedYear, super.getIdfEchelon());		
		lstDispo = new ArrayList<DispoViewer>();
		for (JourStatut jour: lstJour) {
			for ( PlageStatut plage : jour.getLstPlage()) {
				DispoViewer viewer = new DispoViewer();
				viewer.setIdfAgent(jour.getIdfAgent());
				viewer.setIdfDispo(plage.getIdfDispo());
				viewer.setIdfPlage(plage.getIdfPlage());
				viewer.setJour(jour.getJour().get(Calendar.DAY_OF_MONTH));
				lstDispo.add(viewer);
			}
		}
		return SUCCESS;
	}
	
	public String makeCommentMonth() {
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");;
		selectedMonth = currentPlanner.getCurrentCalendar().get(Calendar.MONTH) ;
		selectedYear = currentPlanner.getCurrentCalendar().get(Calendar.YEAR);
		List<CommentDayHelper> lstHelper = currentPlanner.makeListCommentDayforMonth(selectedMonth, selectedYear, super.getIdfEchelon());
		lstComment = new ArrayList<CommentDayViewer>();
		Calendar dateJour = Calendar.getInstance();
		for (CommentDayHelper helper : lstHelper) {
			CommentDayViewer viewer = new CommentDayViewer();
			viewer.setIdfComment(helper.getIdfComment());
			viewer.setIdfAgent(helper.getIdfAgent());
			viewer.setIdOrganization(helper.getIdfOrga());
			dateJour.setTime(helper.getDteComment());
			viewer.setJour(dateJour.get(Calendar.DAY_OF_MONTH));
			viewer.setCommentaire(helper.getComment());
			lstComment.add(viewer);
		}
		return SUCCESS;
	}
	
	// ------------ private methods ----------
	private Statut make(StatutPeriode aStatutPeriode) {
    	Statut aStatut = new Statut();
    	
    	aStatut.setCode(aStatutPeriode.getCode());
    	aStatut.setIdfStatut(aStatutPeriode.getIdfStatut());
    	aStatut.setLibelle(aStatutPeriode.getLibelle());
    	return aStatut;
	}
	// ------------Getters / Setters --------
	/**
	 * @return the createdDay
	 */
	public JourAgent getManagedDay() {
		return managedDay;
	}
	/**
	 * @param createdDay the createdDay to set
	 */
	public void setManagedDay(JourAgent managedDay) {
		this.managedDay = managedDay;
	}
	/**
	 * @return the idAgent
	 */
	public Integer getIdAgent() {
		return idAgent;
	}
	/**
	 * @param idAgent the idAgent to set
	 */
	public void setIdAgent(Integer idAgent) {
		this.idAgent = idAgent;
	}
	/**
	 * @return the jour
	 */
	public Integer getJour() {
		return jour;
	}
	/**
	 * @param jour the jour to set
	 */
	public void setJour(Integer jour) {
		this.jour = jour;
	}
	/**
	 * @return the idStatut
	 */
	public Integer getIdStatut() {
		return idStatut;
	}
	/**
	 * @param idStatut the idStatut to set
	 */
	public void setIdStatut(Integer idStatut) {
		this.idStatut = idStatut;
	}

	/**
	 * @return the selectedMonth
	 */
	public Integer getSelectedMonth() {
		return selectedMonth;
	}

	/**
	 * @param selectedMonth the selectedMonth to set
	 */
	public void setSelectedMonth(Integer selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	/**
	 * @return the selectedYear
	 */
	public Integer getSelectedYear() {
		return selectedYear;
	}

	/**
	 * @param selectedYear the selectedYear to set
	 */
	public void setSelectedYear(Integer selectedYear) {
		this.selectedYear = selectedYear;
	}

	/**
	 * @return the lstJour
	 */
	public List<JourAgent> getLstJour() {
		return lstJour;
	}

	/**
	 * @param lstJour the lstJour to set
	 */
	public void setLstJour(List<JourAgent> lstJour) {
		this.lstJour = lstJour;
	}

	/**
	 * @return the lstDispo
	 */
	public List<DispoViewer> getLstDispo() {
		return lstDispo;
	}

	/**
	 * @param lstDispo the lstDispo to set
	 */
	public void setLstDispo(List<DispoViewer> lstDispo) {
		this.lstDispo = lstDispo;
	}

	/**
	 * @return the lstComment
	 */
	public List<CommentDayViewer> getLstComment() {
		return lstComment;
	}

	/**
	 * @param lstComment the lstComment to set
	 */
	public void setLstComment(List<CommentDayViewer> lstComment) {
		this.lstComment = lstComment;
	}


}
