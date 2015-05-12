/**
 * 
 */
package fr.firesoft.firetime.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.SMDMethod;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.googlecode.jsonplugin.annotations.SMDMethod;
import com.opensymphony.xwork2.ActionContext;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AgentDaoLocal;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.CycleManagerLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.bean.ParametreDaoLocal;
import fr.firesoft.fireTime.bean.PeriodeDaoLocal;
import fr.firesoft.fireTime.bean.PlanningManagerLocal;
import fr.firesoft.fireTime.entity.Periode;
import fr.firesoft.fireTime.entity.effectif.Filiere;
import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.horaire.CycleTravail;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.ServiceLocatorExtFireTime;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.besoin.BesoinPlageHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.fireTime.helper.AgentHelper;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;
import fr.firesoft.fireTime.helper.EmploiOperationnelHelper;
import fr.firesoft.fireTime.helper.JourAgent;
import fr.firesoft.fireTime.helper.besoin.BesoinJourHelper;
import fr.firesoft.fireTime.helper.besoin.BesoinWeekHelper;
import fr.firesoft.firetime.helper.PlanningHelper;
import fr.firesoft.firetime.helper.PlanningReport;

/**
 * @author xbeaufils
 *
 */
public class PlanningAction extends AuthentifiedAction implements SessionAware {


	Logger log = LoggerFactory.getLogger(PlanningAction.class);

	private List<PlanningHelper> lstPlanning;
	private List<Integer> lstDayOfMonth;
	private List<Integer> lstDayOfWeek;
	private List<BesoinWeekHelper> lstBesoinMonth;
	private List<StatutPeriode> lstStatut;
	private List<CycleTravail> lstCycleTravail;
	private List<PlanningReport> lstJourReport;
	private List<PlageHoraireHelper> lstPlage;
	private List<AgentHelper> lstAgent;
	private List<EmploiOperationnelHelper> lstEmploi;
	//private List<ChoiceFiliere> lstFiliere;
	private List<String> lstFiliere;
	private List<String> lstFiliereCritere;
	
	private Integer maxDay;
	private Integer selectedMonth;
	private List<Integer> lstYearCritere;
	private Integer selectedYear;
	private Integer selectedEmploi;
	private String selectedFiliere;
	
	private JourAgent createdDay;
	
	private Integer idfPlageBesoin;
	private Integer jourBesoin;
	private BesoinPlageHelper besoinPlage;
	
	
	@SuppressWarnings({ "rawtypes" })
	private HashMap reportParams = new HashMap();
	 
	private SimpleDateFormat sfFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4137544608470683246L;

	/**
	 * 
	 */
	public PlanningAction() {
		lstFiliereCritere = new ArrayList<String>();
		lstFiliereCritere.add(Filiere.SPP.name());
		lstFiliereCritere.add(Filiere.SPV.name());
	}

	@SuppressWarnings("unchecked")
	public String initMonth() {
		PlanningManagerLocal currentPlanner ;
		currentPlanner = ServiceLocatorFireTime.getPlanningManagerBean();
		getSession().put("currentPlanner", currentPlanner);			
		return this.viewMonth();
	}
	
	@SuppressWarnings("unchecked")
	public String viewMonth() {
		log.debug("Echelon " + super.getIdfEchelon());
		//lstFiliere = new ArrayList<ChoiceFiliere>();
		//lstFiliere.add(new ChoiceFiliere( Filiere.SPP.name(), true));
		//lstFiliere.add(new ChoiceFiliere( Filiere.SPV.name(), true));
		lstFiliere = new ArrayList<String>();
		lstFiliere.add( Filiere.SPP.name());
		lstFiliere.add(Filiere.SPV.name());
		getSession().put("lstFiliere", lstFiliere);
		
		EffectifDaoLocal effectifDao = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		lstEmploi = effectifDao.makeListEmploiOpeForOrganisationWithEmptyCode(super.getIdfEchelon());
		getSession().put("lstEmploi", lstEmploi);
		
		PlanningManagerLocal currentPlanner ;
		if (getSession().get("currentPlanner") == null) {
			currentPlanner = ServiceLocatorFireTime.getPlanningManagerBean();
			getSession().put("currentPlanner", currentPlanner);			
		}
		else 
			currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		lstYearCritere = new ArrayList<Integer>();
		for (int i=-5; i<= 1; i++)
			lstYearCritere.add(currentPlanner.getCurrentCalendar().get(Calendar.YEAR) + i);
		selectedMonth = currentPlanner.getCurrentCalendar().get(Calendar.MONTH) ;
		selectedYear = currentPlanner.getCurrentCalendar().get(Calendar.YEAR);
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		CycleHoraire aCycle = aManager.seletCycleForDate(currentPlanner.getCurrentCalendar(), super.getIdfEchelon());
		lstStatut = aManager.makeListStatutForCycle(aCycle.getIdfCycle());
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstPlage = dao.makeListPlageForCycle(aCycle.getIdfCycle());
		
		CycleManagerLocal aCycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		lstCycleTravail = aCycleManager.makeListForCycle(aCycle.getIdfCycle());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String viewWeek() {
		log.debug("Echelon " + super.getIdfEchelon());
		//lstFiliere = new ArrayList<ChoiceFiliere>();
		//lstFiliere.add(new ChoiceFiliere( Filiere.SPP.name(), true));
		//lstFiliere.add(new ChoiceFiliere( Filiere.SPV.name(), true));
		lstFiliere = new ArrayList<String>();
		lstFiliere.add( Filiere.SPP.name());
		lstFiliere.add(Filiere.SPV.name());
		getSession().put("lstFiliere", lstFiliere);
		lstFiliereCritere = new ArrayList<String>();
		lstFiliereCritere.add(Filiere.SPP.name());
		lstFiliereCritere.add(Filiere.SPV.name());
		return this.makeWeek();
	}
	
	public String makeMonth() {
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		log.debug("Selected month {} selected year {} selected Filiere {} selected Emploi {}",new Object[] {selectedMonth, selectedYear, selectedFiliere, selectedEmploi });
		if (selectedMonth != null)
			currentPlanner.getCurrentCalendar().set(Calendar.MONTH, selectedMonth);
		if (selectedYear != null)
			currentPlanner.getCurrentCalendar().set(Calendar.YEAR, selectedYear);
		//currentPlanner.checkBesoin(super.getIdfEchelon(), currentPlanner.getCurrentCalendar());
		// Construction de la liste des statuts
		ParametreDaoLocal aDaoParametre = ServiceLocatorFireTime.getParametreDaoBean();
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		if (aDaoParametre == null)
			System.out.println("dao is null");
		CycleHoraire aCycle = aManager.seletCycleForDate(currentPlanner.getCurrentCalendar(), super.getIdfEchelon());
		lstStatut = aManager.makeListStatutForCycle(aCycle.getIdfCycle());
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstPlage = dao.makeListPlageForCycle(aCycle.getIdfCycle());
		
		CycleManagerLocal aCycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		lstCycleTravail = aCycleManager.makeListForCycle(aCycle.getIdfCycle());
		
		// Nombre max de jours à afficher
		maxDay = currentPlanner.getCurrentCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
		// Construction de la liste des jours du mois
		lstDayOfMonth = new ArrayList<Integer>();
		for (int i= 1; i <= currentPlanner.getCurrentCalendar().getActualMaximum(Calendar.DAY_OF_MONTH); i++)
			lstDayOfMonth.add(i);
		Map<Integer, PlanningHelper> mapPlanning = new HashMap<Integer, PlanningHelper>();
		// Construction de la liste des agents
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		List<AgentHelper> lstAgent = aDaoEffectif.selectForIntervalleAndEchelon(
				super.getIdfEchelon(), 
				currentPlanner.getCurrentCalendar().getTime(), Calendar.MONTH);
		// Ajout des agents dans la map
		for (AgentHelper anAgent : lstAgent) {
			// Vérifications de la filiere
			if (selectedFiliere != null) {
				if (anAgent.getGrade().getFiliere().equalsIgnoreCase(selectedFiliere)
						|| selectedFiliere.trim().length() == 0 ) {
					mapPlanning.put(anAgent.getIdfAgent(), new PlanningHelper(anAgent, 
									currentPlanner.getCurrentCalendar().get(Calendar.MONTH), 
									currentPlanner.getCurrentCalendar().get(Calendar.YEAR)));
				}
			}
			else {
				mapPlanning.put(anAgent.getIdfAgent(), new PlanningHelper(anAgent, 
						currentPlanner.getCurrentCalendar().get(Calendar.MONTH), 
						currentPlanner.getCurrentCalendar().get(Calendar.YEAR)));
				
			}
		}
		// On enleve ceux sans la compétence recherchée
		if (selectedEmploi != null) {
			AgentDaoLocal aDaoAgent = ServiceLocatorFireTime.getAgentDaoBean();
			List<CompetenceAgentHelper>lstCompetence = aDaoAgent.makeListAgentForEmploiAndEchelon(
					currentPlanner.getCurrentCalendar().getTime(), 
					selectedEmploi, 
					super.getIdfEchelon());
			Map<Integer, CompetenceAgentHelper> mapCompetence = new HashMap<Integer, CompetenceAgentHelper>();
			for (CompetenceAgentHelper competence : lstCompetence) {
				mapCompetence.put(competence.getIdfAgent(), competence);				
			}
			for (AgentHelper anAgent : lstAgent) {
				if (mapCompetence.get(anAgent.getIdfAgent()) == null)
					mapPlanning.remove(anAgent.getIdfAgent());
			}
		}
		PeriodeDaoLocal aDao = ServiceLocatorFireTime.getPeriodeDaoBean();
		List<Periode> lstPeriode = aDao.makeListForMonth(
				currentPlanner.getCurrentCalendar().get(Calendar.MONTH), 
				currentPlanner.getCurrentCalendar().get(Calendar.YEAR), 
				super.getIdfEchelon());
		log.debug("planning size {} periode size {}", new Object[]{mapPlanning.size(),  lstPeriode.size()});
		for (Periode periode: lstPeriode) {
			// recherche de l'agent dans le planning
			PlanningHelper aPlanning = mapPlanning.get(periode.getAgent().getIdfAgent());
			if (aPlanning != null) {
				log.debug ("Found " +  periode.getAgent().getIdfAgent() );
				// Mise à jour du statut de l'agent pour le jour
				JourAgent aJour = new JourAgent();
				aJour.setIdfPeriode(periode.getIdfPeriode());
				aJour.setIdfAgent(periode.getAgent().getIdfAgent());
				aJour.setJour(periode.getJour().get(Calendar.DAY_OF_MONTH));
				int indexJour = aPlanning.getMonth().indexOf(aJour);
				aPlanning.getMonth().get(indexJour).setStatut( this.make(periode.getStatut()) );
			
			}
		}
		lstPlanning = new ArrayList<PlanningHelper>(mapPlanning.values());
		Collections.sort(lstPlanning, new PlanningHelper.PlanningComparator());
		return SUCCESS;
	}
	
	public String viewBesoinAgent() {
       	PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		Calendar day = Calendar.getInstance();
		day.set(Calendar.HOUR_OF_DAY, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		day.set(Calendar.MILLISECOND, 0);
		day.set(Calendar.DAY_OF_MONTH, jourBesoin);
		day.set(Calendar.MONTH, currentPlanner.getCurrentCalendar().get(Calendar.MONTH));
		day.set(Calendar.YEAR, currentPlanner.getCurrentCalendar().get(Calendar.YEAR));

		PeriodeDaoLocal aDao = ServiceLocatorFireTime.getPeriodeDaoBean();
		List<Periode> lstPeriode = aDao.makeListForDateAndPlage(
				day, 
				super.getIdfEchelon(), 
				idfPlageBesoin);
		lstAgent = new ArrayList<AgentHelper>();
		log.debug("Plage {} Jour {}", new Object[]{ idfPlageBesoin, day.getTime()});
		log.debug("Nb found periodes = {}", lstPeriode.size());
		for (Periode periode: lstPeriode) {
			log.debug("\tFound agent {} {}", new Object[] {periode.getAgent().getPrenom(), periode.getAgent().getNom()});
			AgentHelper agent = new AgentHelper();
			agent.setIdfAgent(periode.getAgent().getIdfAgent());
			agent.setMatricule(periode.getAgent().getMatricule().toString());
			agent.setPrenom(periode.getAgent().getPrenom());
			agent.setNom(periode.getAgent().getNom());
			lstAgent.add(agent);
		}	
		log.debug("Nb found agents = {}", lstAgent.size());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String makeWeek() {
		PlanningManagerLocal currentPlanner ;
		if (getSession().get("currentPlanner") == null) {
			currentPlanner = ServiceLocatorFireTime.getPlanningManagerBean();
			getSession().put("currentPlanner", currentPlanner);			
		}
		else 
			currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		selectedMonth = currentPlanner.getCurrentCalendar().get(Calendar.MONTH) + 1;
		selectedYear = currentPlanner.getCurrentCalendar().get(Calendar.YEAR);		
		lstYearCritere = new ArrayList<Integer>();
		for (int i=-5; i<= 1; i++)
			lstYearCritere.add(currentPlanner.getCurrentCalendar().get(Calendar.YEAR) + i);
		// currentPlanner.checkBesoin(super.getIdfEchelon(), currentPlanner.getCurrentCalendar());
		// Construction de la liste des statuts
		ParametreDaoLocal aDaoParametre = ServiceLocatorFireTime.getParametreDaoBean();
		HoraireManagerLocal aManager = ServiceLocatorFireTime.getHoraireManagerBean();
		if (aDaoParametre == null)
			System.out.println("dao is null");
		CycleHoraire aCycle = aManager.seletCycleForDate(currentPlanner.getCurrentCalendar(), super.getIdfEchelon());
		lstStatut = aManager.makeListStatutForCycle(aCycle.getIdfCycle());
		CycleManagerLocal aCycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		lstCycleTravail = aCycleManager.makeListForCycle(aCycle.getIdfCycle());
		// Construction de la liste des jours du mois
		lstDayOfMonth = new ArrayList<Integer>();
		for (int i= 1; i <= 7; i++)
			lstDayOfMonth.add(i);
		lstPlanning = new ArrayList<PlanningHelper>();
		// Construction de la liste des agents
		EffectifDaoLocal aDaoEffectif = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
		List<Filiere> lstFiliereselected = new ArrayList<Filiere>();
		if (lstFiliereCritere == null)
			return SUCCESS;
		for (String strFiliere : lstFiliereCritere) {
			lstFiliereselected.add( Filiere.valueOf(strFiliere) );
		}
		List<AgentHelper> lstAgent = aDaoEffectif.selectForIntervalleAndEchelon(
				super.getIdfEchelon(), 
				currentPlanner.getCurrentCalendar().getTime(), Calendar.MONTH, lstFiliereselected);
		for (AgentHelper anAgent : lstAgent) {
			lstPlanning.add( new PlanningHelper(anAgent, 
					currentPlanner.getCurrentCalendar().get(Calendar.MONTH), 
					currentPlanner.getCurrentCalendar().get(Calendar.YEAR)) );
		}
		PeriodeDaoLocal aDao = ServiceLocatorFireTime.getPeriodeDaoBean();
		List<Periode> lstPeriode = aDao.makeListForWeek(currentPlanner.getCurrentCalendar().get(Calendar.WEEK_OF_YEAR), 
				currentPlanner.getCurrentCalendar().get(Calendar.YEAR), 
				super.getIdfEchelon());
		log.debug("planning size {} periode size {}", new Object[] {lstPlanning.size(), lstPeriode.size() } );
		for (Periode periode: lstPeriode) {
			// recherche de l'agent dans le planning
			for (PlanningHelper aPlanning : lstPlanning) {
				log.debug("planning {} periode {}", 
						new Object[] {aPlanning.getAgent().getIdfAgent(), periode.getJour().getTime()});
				if (aPlanning.getAgent().getIdfAgent().equals(periode.getAgent().getIdfAgent())) {
					log.debug ("Found " +  periode.getAgent().getIdfAgent() );
					// Mise à jour du statut de l'agent pour le jour
					JourAgent aJour = new JourAgent();
					aJour.setIdfPeriode(periode.getIdfPeriode());
					aJour.setIdfAgent(periode.getAgent().getIdfAgent());
					aJour.setJour(periode.getJour().get(Calendar.DAY_OF_MONTH));
					int indexJour = aPlanning.getMonth().indexOf(aJour);
					aPlanning.getMonth().get(indexJour).setStatut( this.make(periode.getStatut()) );
					break;
				}
			}
		}
		Collections.sort(lstPlanning, new PlanningHelper.PlanningComparator());
		return SUCCESS;
	}
	
	public String makeListOfDays() {
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		lstDayOfMonth = new ArrayList<Integer>();
		for (int i= 1; i <= currentPlanner.getCurrentCalendar().getActualMaximum(Calendar.MONTH); i++)
			lstDayOfMonth.add(i);
		return SUCCESS;		
	}
	
	@SuppressWarnings("unchecked")
	public String makeListOfWeeks() {
		PlanningManagerLocal currentPlanner = ServiceLocatorFireTime.getPlanningManagerBean();
		lstBesoinMonth = currentPlanner.makeListBesoin(super.getIdfEchelon());
		getSession().put("besoinMonth", lstBesoinMonth);			
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String changeDateBesoin() {
		
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) getSession().get("currentPlanner");
		log.debug("Selected month " + selectedMonth);
		if (selectedMonth != null)
			currentPlanner.getCurrentCalendar().set(Calendar.MONTH, selectedMonth);
		if (selectedYear != null)
			currentPlanner.getCurrentCalendar().set(Calendar.YEAR, selectedYear);
		lstBesoinMonth = currentPlanner.makeListBesoin(super.getIdfEchelon());
		getSession().put("besoinMonth", lstBesoinMonth);			
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String viewBesoinEquipage() {
		lstBesoinMonth =  (List<BesoinWeekHelper>) getSession().get("besoinMonth");
		log.debug("Jour {} plage {}", new Object[] {jourBesoin, idfPlageBesoin});
		for (BesoinWeekHelper week : lstBesoinMonth) {
			for(BesoinJourHelper jour: week.getLstJour()) {
				if (jour.getJour() != null ){
					if (jour.getJour().equals(jourBesoin)) {
						log.debug("Found jour {}", jour.getJour());
						for (BesoinPlageHelper plage :jour.getLstBesoinPlage() ) {
							if (plage.getIdfPlage().equals(idfPlageBesoin) ) {
								besoinPlage = plage;
								log.debug("Found plage {}", plage.getIdfPlage());
							}
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public String smd() {
		return SUCCESS;
	}
	
	//------------- Jasper Methods -----------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String print() {
		log.debug("",ActionContext.getContext().getParameters().get("lstFiliereCritere"));
		log.debug("Filieres " + lstFiliereCritere );
		lstJourReport = new ArrayList<PlanningReport>();
		HashMap<Integer, PlanningReport> hsPlanning = new HashMap<Integer, PlanningReport>();
		PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ActionContext.getContext().getSession().get("currentPlanner");
		PeriodeDaoLocal aDao = ServiceLocatorFireTime.getPeriodeDaoBean();
		List<Periode> lstPeriode = aDao.makeListForMonth(
				currentPlanner.getCurrentCalendar().get(Calendar.MONTH), 
				currentPlanner.getCurrentCalendar().get(Calendar.YEAR), 
				super.getIdfEchelon());
		for (Periode periode: lstPeriode) {
			// recherche de l'agent dans le planning
			PlanningReport aJour = hsPlanning.get(periode.getAgent().getIdfAgent());
			// Si il n'y est pas, on l'ajoute
			if ( aJour == null ) {
				EffectifDaoLocal anEffectifDao =ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
				List<AgentHelper> lstAgent = anEffectifDao.selectForAgentInDayAndEchelon(	
						periode.getAgent().getIdfAgent(), 
						super.getIdfEchelon(), 
						periode.getJour().getTime(), 
						Calendar.MONTH);
				if (! lstAgent.isEmpty()) {
					aJour = new PlanningReport();
					aJour.setNom(periode.getAgent().getNom());
					aJour.setPrenom(periode.getAgent().getPrenom());
					log.debug("Agent " + lstAgent.get(0).getIdfAgent() 
							+ " Filiere " + lstAgent.get(0).getGrade().getFiliere());
					aJour.setFiliere(lstAgent.get(0).getGrade().getFiliere());
					hsPlanning.put(periode.getAgent().getIdfAgent(), aJour);
				}
				else 
					log.warn("Can't process this " + periode.getAgent().getIdfAgent() );
			}
			reportParams = new HashMap();
			reportParams.put("NBJOUR", new Integer(currentPlanner.getCurrentCalendar().getActualMaximum(Calendar.DAY_OF_MONTH)));
			aJour.setNbJours(new Integer(currentPlanner.getCurrentCalendar().getActualMaximum(Calendar.DAY_OF_MONTH)));
			switch (currentPlanner.getCurrentCalendar().get(Calendar.MONTH)) {
			case Calendar.JANUARY:
				aJour.setMois("JANVIER");
				reportParams.put("MOIS", "JANVIER");
				break;
			case Calendar.FEBRUARY:
				aJour.setMois("FEVRIER");
				reportParams.put("MOIS", "FEVRIER");
				break;
			case Calendar.MARCH:
				aJour.setMois("MARS");
				reportParams.put("MOIS", "MARS");
				break;
			case Calendar.APRIL:
				aJour.setMois("AVRIL");
				reportParams.put("MOIS", "AVRIL");
				break;
			case Calendar.MAY:
				aJour.setMois("MAI");
				reportParams.put("MOIS", "MAI");
				break;
			case Calendar.JUNE:
				aJour.setMois("JUIN");
				reportParams.put("MOIS", "JUIN");
				break;
			case Calendar.JULY :
				aJour.setMois("JUILLET");
				reportParams.put("MOIS", "JUILLET");
				break;
			case Calendar.AUGUST:
				aJour.setMois("AOUT");
				reportParams.put("MOIS", "AOUT");
				break;
			case Calendar.SEPTEMBER:
				aJour.setMois("SEPTEMBRE");
				reportParams.put("MOIS", "SEPTEMBRE");
				break;
			case Calendar.OCTOBER:
				aJour.setMois("OCTOBRE");
				reportParams.put("MOIS", "OCTOBRE");
				break;
			case Calendar.NOVEMBER:
				aJour.setMois("NOVEMBRE");
				reportParams.put("MOIS", "NOVEMBRE");
				break;
			case Calendar.DECEMBER:
				aJour.setMois("DECEMBRE");
				reportParams.put("MOIS", "DECEMBRE");
				break;
			default:
				break;
			}
			switch (periode.getJour().get(Calendar.DAY_OF_MONTH)) {
			case 1:
				aJour.setJour1(periode.getStatut().getCode());
				break;
			case 2:
				aJour.setJour2(periode.getStatut().getCode());
				break;
			case 3:
				aJour.setJour3(periode.getStatut().getCode());
				break;
			case 4:
				aJour.setJour4(periode.getStatut().getCode());
				break;
			case 5:
				aJour.setJour5(periode.getStatut().getCode());
				break;
			case 6:
				aJour.setJour6(periode.getStatut().getCode());
				break;
			case 7:
				aJour.setJour7(periode.getStatut().getCode());
				break;
			case 8:
				aJour.setJour8(periode.getStatut().getCode());
				break;
			case 9:
				aJour.setJour9(periode.getStatut().getCode());
				break;
			case 10:
				aJour.setJour10(periode.getStatut().getCode());
				break;
			case 11:
				aJour.setJour11(periode.getStatut().getCode());
				break;
			case 12:
				aJour.setJour12(periode.getStatut().getCode());
				break;
			case 13:
				aJour.setJour13(periode.getStatut().getCode());
				break;
			case 14:
				aJour.setJour14(periode.getStatut().getCode());
				break;
			case 15:
				aJour.setJour15(periode.getStatut().getCode());
				break;
			case 16:
				aJour.setJour16(periode.getStatut().getCode());
				break;
			case 17:
				aJour.setJour17(periode.getStatut().getCode());
				break;
			case 18:
				aJour.setJour18(periode.getStatut().getCode());
				break;
			case 19:
				aJour.setJour19(periode.getStatut().getCode());
				break;
			case 20:
				aJour.setJour20(periode.getStatut().getCode());
				break;
			case 21:
				aJour.setJour21(periode.getStatut().getCode());
				break;
			case 22:
				aJour.setJour22(periode.getStatut().getCode());
				break;
			case 23:
				aJour.setJour23(periode.getStatut().getCode());
				break;
			case 24:
				aJour.setJour24(periode.getStatut().getCode());
				break;
			case 25:
				aJour.setJour25(periode.getStatut().getCode());
				break;
			case 26:
				aJour.setJour26(periode.getStatut().getCode());
				break;
			case 27:
				aJour.setJour27(periode.getStatut().getCode());
				break;
			case 28:
				aJour.setJour28(periode.getStatut().getCode());
				break;
			case 29:
				aJour.setJour29(periode.getStatut().getCode());
				break;
			case 30:
				aJour.setJour30(periode.getStatut().getCode());
				break;
			case 31:
				aJour.setJour31(periode.getStatut().getCode());
				break;

			default:
				break;
			}
		}
		lstJourReport.addAll(hsPlanning.values());
		Collections.sort(lstJourReport, new PlanningReport.PlanningReportComparator());		
		return SUCCESS;
	}
	
    //------------- JSON Methods -------------------
   @SMDMethod	
	public List<StatutPeriode> makeListOfStatut() {
    	ParametreDaoLocal aDao = ServiceLocatorFireTime.getParametreDaoBean();
    	return aDao.makeListStatut();
    }
    
   /* @SMDMethod
    public JourAgent setStatut(Integer currentIdfAgent, Integer currentJour, Integer idfStatut, Integer idfEchelon) {
    	// idfEchelon du certificat ne peut être recupéré
    	JourAgent newJour = new JourAgent();
    	PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");
		Calendar aJour = Calendar.getInstance();
		aJour.set(Calendar.DAY_OF_MONTH, currentJour);
		aJour.set(Calendar.MONTH, currentPlanner.getCurrentCalendar().get(Calendar.MONTH));
		aJour.set(Calendar.YEAR, currentPlanner.getCurrentCalendar().get(Calendar.YEAR));
		log.debug("Echelon " + super.getIdfEchelon() );
		currentPlanner.setStatut(currentIdfAgent, aJour, idfStatut, idfEchelon);
    	ParametreDaoLocal aDao = ServiceLocatorFireTime.getParametreDaoBean();
    	newJour.setIdfAgent(currentIdfAgent);
    	newJour.setJour(currentJour);
    	StatutPeriode aStatutPeriode = aDao.select(idfStatut);
    	newJour.setStatut(this.make(aStatutPeriode));
    	return newJour;
    }
 */   
    @SMDMethod
    public void setCycle(Integer currentIdfAgent, Integer currentJour, String dateFin, Integer idfCycle) 
    	throws ParseException {
       	PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");
		Calendar aJour = Calendar.getInstance();
		aJour.set(Calendar.DAY_OF_MONTH, currentJour);
		aJour.set(Calendar.MONTH, currentPlanner.getCurrentCalendar().get(Calendar.MONTH));
		aJour.set(Calendar.YEAR, currentPlanner.getCurrentCalendar().get(Calendar.YEAR));
		Calendar fin = Calendar.getInstance();
		fin.setTime(sfFormat.parse(dateFin));
    	PlanningManagerLocal aManager = ServiceLocatorFireTime.getPlanningManagerBean();
    	aManager.setCycle(currentIdfAgent, aJour, fin, idfCycle, 
    			super.getIdfEchelon());
    }  
    
 /*   @SMDMethod
    public JourAgent deleteStatut(Integer currentIdfAgent, Integer currentJour) {
      	PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");
		Calendar aJour = Calendar.getInstance();
		aJour.set(Calendar.DAY_OF_MONTH, currentJour);
		aJour.set(Calendar.MONTH,  currentPlanner.getCurrentCalendar().get(Calendar.MONTH));
		aJour.set(Calendar.YEAR,  currentPlanner.getCurrentCalendar().get(Calendar.YEAR));
    	currentPlanner.deleteStatut(currentIdfAgent, aJour);  	
       	JourAgent newJour = new JourAgent();
    	newJour.setIdfAgent(currentIdfAgent);
    	newJour.setJour(currentJour);
    	newJour.setStatut(null);
    	return newJour;
    }
*/    
    @SMDMethod
    public List<BesoinPlageHelper> checkBesoin( Integer currentJour, Integer idfEchelon) {
    	PlanningManagerLocal currentPlanner = (PlanningManagerLocal) ServletActionContext.getRequest().getSession().getAttribute("currentPlanner");
		Calendar aJour = Calendar.getInstance();
		aJour.set(Calendar.DAY_OF_MONTH, currentJour);
		aJour.set(Calendar.MONTH,  currentPlanner.getCurrentCalendar().get(Calendar.MONTH));
		aJour.set(Calendar.YEAR,  currentPlanner.getCurrentCalendar().get(Calendar.YEAR));
    	return currentPlanner.checkBesoin(idfEchelon, aJour);
    }
 	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings({"rawtypes" })
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings({ "rawtypes" })
	public Map getSession() {
		return session;
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
	 * @return the lstPlanning
	 */
	public List<PlanningHelper> getLstPlanning() {
		return lstPlanning;
	}

	/**
	 * @param lstPlanning the lstPlanning to set
	 */
	public void setLstPlanning(List<PlanningHelper> lstPlanning) {
		this.lstPlanning = lstPlanning;
	}

	/**
	 * @return the lstDayOfMonth
	 */
	public List<Integer> getLstDayOfMonth() {
		return lstDayOfMonth;
	}

	/**
	 * @param lstDayOfMonth the lstDayOfMonth to set
	 */
	public void setLstDayOfMonth(List<Integer> lstDayOfMonth) {
		this.lstDayOfMonth = lstDayOfMonth;
	}

	public List<Integer> getLstDayOfWeek() {
		return lstDayOfWeek;
	}

	public void setLstDayOfWeek(List<Integer> lstDayOfWeek) {
		this.lstDayOfWeek = lstDayOfWeek;
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

	public List<PlanningReport> getLstJourReport() {
		return lstJourReport;
	}

	public void setLstJourReport(List<PlanningReport> lstJourReport) {
		this.lstJourReport = lstJourReport;
	}
	public HashMap<?,?> getReportParams() {
      return reportParams;
	}

	/**
	 * @return the lstFiliere
	 */
	public List<String> getLstFiliere() {
		return lstFiliere;
	}

	/**
	 * @param lstFiliere the lstFiliere to set
	 */
	public void setLstFiliere(List<String> lstFiliere) {
		this.lstFiliere = lstFiliere;
	}

	/**
	 * @return the lstFiliereCritere
	 */
	public List<String> getLstFiliereCritere() {
		return lstFiliereCritere;
	}

	/**
	 * @param lstFiliereCritere the lstFiliereCritere to set
	 */
	public void setLstFiliereCritere(List<String> lstFiliereCritere) {
		this.lstFiliereCritere = lstFiliereCritere;
	}

	/**
	 * @return the lstCycleTravail
	 */
	public List<CycleTravail> getLstCycleTravail() {
		return lstCycleTravail;
	}

	/**
	 * @param lstCycleTravail the lstCycleTravail to set
	 */
	public void setLstCycleTravail(List<CycleTravail> lstCycleTravail) {
		this.lstCycleTravail = lstCycleTravail;
	}

	/**
	 * @return the selectMonth
	 */
	public Integer getSelectedMonth() {
		return selectedMonth;
	}

	/**
	 * @param selectMonth the selectMonth to set
	 */
	public void setSelectedMonth(Integer selectMonth) {
		this.selectedMonth = selectMonth;
	}

	/**
	 * @return the selectYear
	 */
	public Integer getSelectedYear() {
		return selectedYear;
	}

	/**
	 * @param selectYear the selectYear to set
	 */
	public void setSelectedYear(Integer selectYear) {
		this.selectedYear = selectYear;
	}

	/**
	 * @return the lstYearCritere
	 */
	public List<Integer> getLstYearCritere() {
		return lstYearCritere;
	}

	/**
	 * @param lstYearCritere the lstYearCritere to set
	 */
	public void setLstYearCritere(List<Integer> lstYearCritere) {
		this.lstYearCritere = lstYearCritere;
	}	
	/**
	 * @return the maxDay
	 */
	public Integer getMaxDay() {
		return maxDay;
	}

	/**
	 * @param maxDay the maxDay to set
	 */
	public void setMaxDay(Integer maxDay) {
		this.maxDay = maxDay;
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
	 * @return the lstBesoinMonth
	 */
	public List<BesoinWeekHelper> getLstBesoinMonth() {
		return lstBesoinMonth;
	}

	/**
	 * @param lstBesoinMonth the lstBesoinMonth to set
	 */
	public void setLstBesoinMonth(List<BesoinWeekHelper> lstBesoinMonth) {
		this.lstBesoinMonth = lstBesoinMonth;
	}

	/**
	 * @return the createdDay
	 */
	public JourAgent getCreatedDay() {
		return createdDay;
	}

	/**
	 * @param createdDay the createdDay to set
	 */
	public void setCreatedDay(JourAgent createdDay) {
		this.createdDay = createdDay;
	}

	/**
	 * @return the idfPlageBesoin
	 */
	public Integer getIdfPlageBesoin() {
		return idfPlageBesoin;
	}

	/**
	 * @param idfPlageBesoin the idfPlageBesoin to set
	 */
	public void setIdfPlageBesoin(Integer idfPlageBesoin) {
		this.idfPlageBesoin = idfPlageBesoin;
	}

	/**
	 * @return the jourBesoin
	 */
	public Integer getJourBesoin() {
		return jourBesoin;
	}

	/**
	 * @param jourBesoin the jourBesoin to set
	 */
	public void setJourBesoin(Integer jourBesoin) {
		this.jourBesoin = jourBesoin;
	}

	/**
	 * @return the besoinPlage
	 */
	public BesoinPlageHelper getBesoinPlage() {
		return besoinPlage;
	}

	/**
	 * @param besoinPlage the besoinPlage to set
	 */
	public void setBesoinPlage(BesoinPlageHelper besoinPlage) {
		this.besoinPlage = besoinPlage;
	}

	/**
	 * @return the lstAgent
	 */
	public List<AgentHelper> getLstAgent() {
		return lstAgent;
	}

	/**
	 * @param lstAgent the lstAgent to set
	 */
	public void setLstAgent(List<AgentHelper> lstAgent) {
		this.lstAgent = lstAgent;
	}

	/**
	 * @return the lstEmploi
	 */
	public List<EmploiOperationnelHelper> getLstEmploi() {
		return lstEmploi;
	}

	/**
	 * @param lstEmploi the lstEmploi to set
	 */
	public void setLstEmploi(List<EmploiOperationnelHelper> lstEmploi) {
		this.lstEmploi = lstEmploi;
	}

	/**
	 * @return the selectedEmploi
	 */
	public Integer getSelectedEmploi() {
		return selectedEmploi;
	}

	/**
	 * @param selectedEmploi the selectedEmploi to set
	 */
	public void setSelectedEmploi(Integer selectedEmploi) {
		this.selectedEmploi = selectedEmploi;
	}

	/**
	 * @return the selectedFiliere
	 */
	public String getSelectedFiliere() {
		return selectedFiliere;
	}

	/**
	 * @param selectedFiliere the selectedFiliere to set
	 */
	public void setSelectedFiliere(String selectedFiliere) {
		this.selectedFiliere = selectedFiliere;
	}

}
