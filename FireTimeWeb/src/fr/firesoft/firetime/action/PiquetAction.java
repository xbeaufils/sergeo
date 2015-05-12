/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;


import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.bean.GardeManagerLocal;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.bean.PeriodeDaoLocal;
import fr.firesoft.fireTime.bean.PlanningManagerLocal;
import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.Periode;
import fr.firesoft.fireTime.entity.Piquet;
import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.ext.ServiceLocatorExtFireTime;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.horaire.BesoinEquipageHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.helper.PiquetReport;

/**
 * @author xbeaufils
 *
 */
public class PiquetAction extends AuthentifiedAction implements SessionAware {
	
	Logger log = LoggerFactory.getLogger(PiquetAction.class);
	private Integer idfBesoin;
	private Integer idfPeriode;
	private Integer idfPlageHoraire;
	private Integer idfPiquet;
	private String libelleBesoin;
	private Map<Integer, List<Piquet> >lstPiquet;
	private Map<Integer, Integer> piquet;
	private Integer dayOfMonth;
	private Integer idfEchelonPrinted; 
	private List<PiquetReport> lstReport;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4684358525306965967L;

	/**
	 * 
	 */
	public PiquetAction() {
	}
	
	@SuppressWarnings("unchecked")
	public String selectForDay() {
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		currentPlanner.getCurrentCalendar().set(Calendar.DAY_OF_MONTH, dayOfMonth);
		log.debug("CurrentCalendar {}", currentPlanner.getCurrentCalendar());
		// Recuperation des plages
		HoraireManagerLocal aHoraireManager = ServiceLocatorFireTime.getHoraireManagerBean();
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		CycleHoraire aCycle = aHoraireManager.seletCycleForDate(currentPlanner.getCurrentCalendar(), super.getIdfEchelon());
		List<PlageHoraireHelper> lstPlage  = dao.makeListPlageForCycle(aCycle.getIdfCycle());
		getSession().put("lstPlage", lstPlage);
		// La premiere plage est sélectionnée par defaut
		PlageHoraireHelper currentPlage = lstPlage.get(0);
		getSession().put("currentPlage", currentPlage);
		// recuperation des periodes (donc ds agents)
		this.selectPlage(currentPlage.getIdfPlage());
		// Recuperation des besoins
		BesoinManagerLocal besoinManager = ServiceLocatorFireTime.getBesoinManagerBean();
		List<BesoinEquipageHelper> lstBesoin = besoinManager.makeListBesoinForPlage(currentPlage.getIdfPlage());
		getSession().put("lstBesoin", lstBesoin);
		GardeManagerLocal aManager = ServiceLocatorFireTime.getGardeManagerBean();
		getSession().put("currentGarde", aManager);
		return SUCCESS;
	}
	
	public String selectForType() {
		log.debug("Search for besoin " + idfBesoin);
		BesoinManagerLocal aBesoinManager = ServiceLocatorFireTime.getBesoinManagerBean();
		BesoinEquipage currentBesoin = aBesoinManager.selectBesoin(idfBesoin); 
		libelleBesoin = currentBesoin.getLibelle();		
		GardeManagerLocal aManager = (GardeManagerLocal) getSession().get("currentGarde");
		PlageHoraireHelper currentPlage = (PlageHoraireHelper) getSession().get("currentPlage");
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		lstPiquet = aManager.makeCompleteListForBesoin(idfBesoin, currentPlage.getIdfPlage(), currentPlanner.getCurrentCalendar());
		log.debug("lstPiquet {}", lstPiquet.values());
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String selectPlageHoraire () {
		HoraireManagerLocal aHoraireManager = ServiceLocatorFireTime.getHoraireManagerBean();
		PlageHoraireHelper currentPlage =  aHoraireManager.selectHelper(idfPlageHoraire);
		getSession().put("currentPlage", currentPlage);
		this.selectPlage(idfPlageHoraire);		
		return SUCCESS;
	}
	
	public String affecte() {
		// idfPeriode;
		if (piquet != null) {
			log.debug("Piquets " + piquet.values());
			PlageHoraireHelper currentPlage = (PlageHoraireHelper) getSession().get("currentPlage");
			GardeManagerLocal aManager = (GardeManagerLocal) getSession().get("currentGarde");
			aManager.affecte(idfPeriode, currentPlage.getIdfPlage(), new ArrayList<Integer>(piquet.values()) );
			this.selectPlage(currentPlage.getIdfPlage());
		}
		return SUCCESS;
	}
	
	public String print() {
		log.debug("idfEchelonPrinted in request " + ActionContext.getContext().getParameters().get("idfEchelonPrinted")
				+ " class " + ActionContext.getContext().getParameters().get("idfEchelonPrinted").getClass().getName());
		
		String strIdfEchelonPrinted = ( (String[])ActionContext.getContext().getParameters().get("idfEchelonPrinted"))[0];
		idfEchelonPrinted = Integer.parseInt(strIdfEchelonPrinted);
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");

		GardeManagerLocal aManager = (GardeManagerLocal) ActionContext.getContext().getSession().get("currentGarde");
		log.debug ("Today " + currentPlanner.getCurrentCalendar().getTime() + " printed echelon " + idfEchelonPrinted);
		lstReport = aManager.makeListForDay(currentPlanner.getCurrentCalendar(), idfEchelonPrinted);
		return SUCCESS;
	}
	// ------------ private method --------------
	@SuppressWarnings("unchecked")
	private void selectPlage(Integer selectedIdfPlage) {
		//Calendar calendar = (Calendar) getSession().get("currentDate");
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		PeriodeDaoLocal aDaoPeriode = ServiceLocatorFireTime.getPeriodeDaoBean();
		List<Periode> lstPeriodeForDay = aDaoPeriode.makeListForDate( currentPlanner.getCurrentCalendar(),super.getIdfEchelon()) ;
		List<Periode> lstPeriode = new ArrayList<Periode>();
		log.debug("searched plage " + selectedIdfPlage);
		for (Periode aPeriode: lstPeriodeForDay) {
			log.debug("Agent " + aPeriode.getAgent().getNom());
			for ( PlageHoraire aPlageHoraire : aPeriode.getStatut().getLstComposant()) {
				log.debug("\t plageHoraire " + aPlageHoraire.getIdfPlage()+" time " +  aPlageHoraire.getDebut().getTime());
				if (aPlageHoraire.getIdfPlage().equals(selectedIdfPlage))
					lstPeriode.add(aPeriode);
			}
		}
		getSession().put("lstPeriode", lstPeriode);		
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
	 * @return the dayOfMonth
	 */
	public Integer getDayOfMonth() {
		return dayOfMonth;
	}
	/**
	 * @param dayOfMonth the dayOfMonth to set
	 */
	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
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
	 * @return the libelleBesoin
	 */
	public String getLibelleBesoin() {
		return libelleBesoin;
	}

	/**
	 * @param libelleBesoin the libelleBesoin to set
	 */
	public void setLibelleBesoin(String libelleBesoin) {
		this.libelleBesoin = libelleBesoin;
	}

	/**
	 * @return the lstPiquet
	 */
	public Map<Integer, List<Piquet>> getLstPiquet() {
		return lstPiquet;
	}

	/**
	 * @param lstPiquet the lstPiquet to set
	 */
	public void setLstPiquet(Map<Integer, List<Piquet>> lstPiquet) {
		this.lstPiquet = lstPiquet;
	}

	/**
	 * @return the piquet
	 */
	public Map<Integer, Integer> getPiquet() {
		return piquet;
	}

	/**
	 * @param piquet the piquet to set
	 */
	public void setPiquet(Map<Integer, Integer> piquet) {
		this.piquet = piquet;
	}

	/**
	 * @return the idfPeriode
	 */
	public Integer getIdfPeriode() {
		return idfPeriode;
	}

	/**
	 * @param idfPeriode the idfPeriode to set
	 */
	public void setIdfPeriode(Integer idfPeriode) {
		this.idfPeriode = idfPeriode;
	}

	/**
	 * @return the idfPlageHoraire
	 */
	public Integer getIdfPlageHoraire() {
		return idfPlageHoraire;
	}

	/**
	 * @param idfPlageHoraire the idfPlageHoraire to set
	 */
	public void setIdfPlageHoraire(Integer idfPlageHoraire) {
		this.idfPlageHoraire = idfPlageHoraire;
	}

	/**
	 * @return the idfPiquet
	 */
	public Integer getIdfPiquet() {
		return idfPiquet;
	}

	/**
	 * @param idfPiquet the idfPiquet to set
	 */
	public void setIdfPiquet(Integer idfPiquet) {
		this.idfPiquet = idfPiquet;
	}

	/**
	 * @return the idfEchelonPrinted
	 */
	public Integer getIdfEchelonPrinted() {
		return idfEchelonPrinted;
	}

	/**
	 * @param idfEchelonPrinted the idfEchelonPrinted to set
	 */
	public void setIdfEchelonPrinted(Integer idfEchelonPrinted) {
		this.idfEchelonPrinted = idfEchelonPrinted;
	}

	/**
	 * @return the lstReport
	 */
	public List<PiquetReport> getLstReport() {
		return lstReport;
	}

	/**
	 * @param lstReport the lstReport to set
	 */
	public void setLstReport(List<PiquetReport> lstReport) {
		this.lstReport = lstReport;
	}

}
