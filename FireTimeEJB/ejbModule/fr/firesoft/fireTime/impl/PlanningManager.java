/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.bean.CycleManagerLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.bean.ParametreDaoLocal;
import fr.firesoft.fireTime.bean.PeriodeDaoLocal;
import fr.firesoft.fireTime.bean.PlanningManagerLocal;
import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.CompetenceAgent;
import fr.firesoft.fireTime.entity.Disponibilite;
import fr.firesoft.fireTime.entity.Periode;
import fr.firesoft.fireTime.entity.Piquet;
import fr.firesoft.fireTime.entity.Poste;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.entity.horaire.SequenceTravail;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.entity.planning.CommentDay;
import fr.firesoft.fireTime.entity.planning.CommentMonth;
import fr.firesoft.fireTime.ext.bean.PlanningManagerRemote;
import fr.firesoft.fireTime.ext.besoin.BesoinPlageHelper;
import fr.firesoft.fireTime.ext.besoin.StatutPlanning;
import fr.firesoft.fireTime.ext.exception.AgentNotFoundException;
import fr.firesoft.fireTime.ext.horaire.BesoinEmploiHelper;
import fr.firesoft.fireTime.ext.horaire.Presence;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.fireTime.ext.planning.CommentDayHelper;
import fr.firesoft.fireTime.ext.planning.CommentMonthHelper;
import fr.firesoft.fireTime.ext.planning.JourStatut;
import fr.firesoft.fireTime.ext.planning.PlageStatut;
import fr.firesoft.fireTime.factory.HelperFactory;
import fr.firesoft.fireTime.factory.HoraireHelperFactory;
import fr.firesoft.fireTime.helper.JourAgent;
import fr.firesoft.fireTime.helper.PeriodeAgregator;
import fr.firesoft.fireTime.helper.besoin.BesoinWeekHelper;
import fr.firesoft.fireTime.helper.besoin.BesoinJourHelper;

/**
 * @author xbeaufils
 *
 */
@Local (PlanningManagerLocal.class)
@Remote (PlanningManagerRemote.class)
@Stateful
public class PlanningManager implements PlanningManagerLocal, PlanningManagerRemote {

	Logger log = LoggerFactory.getLogger(PlanningManager.class);
	@PersistenceContext(unitName = "FireTime")
		private EntityManager em;
	   
	@EJB
		ParametreDaoLocal aDaoParametre;
	@EJB
	   	PeriodeDaoLocal aDaoPeriode;
	@EJB
		EffectifDaoLocal aDaoEffectif;
	@EJB
		EffectifManagerLocal aManagerEffectif;
	@EJB
		CycleManagerLocal aCycleManager;
	@EJB
		BesoinManagerLocal managerBesoin;
	@EJB
		HoraireManagerLocal managerHoraire;
	@EJB
		CompetenceManagerLocal managerCompetence;
	
	private Calendar currentCalendar;
	/**
	 * 
	 */
	public PlanningManager() {
		currentCalendar = Calendar.getInstance();
		currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
		currentCalendar.set(Calendar.HOUR, 0);
		currentCalendar.set(Calendar.MINUTE, 0);
		currentCalendar.set(Calendar.SECOND, 0);
		currentCalendar.set(Calendar.MILLISECOND, 0);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#setStatut(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer setStatut(Integer currentIdfAgent, Calendar currentJour,
			Integer idfStatut, Integer idfEchelon) {
		StatutPeriode aStatut  = aDaoParametre.select(idfStatut);
		Calendar debut = currentJour;
		debut.set(Calendar.HOUR_OF_DAY, 0);
		debut.set(Calendar.MINUTE, 0);
		debut.set(Calendar.SECOND, 0);
		debut.set(Calendar.MILLISECOND, 0);
		Periode aPeriode = aDaoPeriode.selectForAgentAndDate(currentIdfAgent, debut, idfEchelon);
		if (aPeriode == null) {
			Agent anAgent = aDaoEffectif.select(currentIdfAgent);
			aPeriode = new Periode();
			aPeriode.setJour(debut);
			aPeriode.setAgent(anAgent);			
		}
		//EchelonOrganigramme echelon = aManagerEffectif.selectEchelon(idfEchelon);
		aPeriode.setIdOrganization(idfEchelon);
		aPeriode.setStatut(aStatut);
		if (aPeriode.getIdfPeriode() == null)
			em.persist(aPeriode);
		else
			em.merge(aPeriode);
		return aPeriode.getIdfPeriode();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#deleteStatut(java.lang.Integer, java.util.Calendar)
	 */
	@Override
	public Integer deleteStatut(Integer currentIdfAgent, Calendar currentJour, Integer idfEchelon) {
		currentJour.set(Calendar.HOUR_OF_DAY, 0);
		currentJour.set(Calendar.MINUTE, 0);
		currentJour.set(Calendar.SECOND, 0);
		currentJour.set(Calendar.MILLISECOND, 0);
		log.debug("Agent " + currentIdfAgent + " Jour " + currentJour.getTime());
		Periode aPeriode = aDaoPeriode.selectForAgentAndDate(currentIdfAgent, currentJour, idfEchelon);
		if (aPeriode == null)
			return null;
		Integer idfPeriode = aPeriode.getIdfPeriode();
		em.remove(aPeriode);
		return idfPeriode;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#setCycle(java.lang.IntegeexecuteUpdatey r, java.util.Calendar, java.util.Calendar, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void setCycle(Integer currentIdfAgent, Calendar debut,
			Calendar dateFin, Integer idfCycle, Integer idfEchelon) {
		Query query = em.createNamedQuery("Periode.deleteForAgentBetweenDate");
		query.setParameter("anIdfAgent", currentIdfAgent);
		query.setParameter("debut", debut);
		query.setParameter("fin", dateFin);
		query.executeUpdate();
		em.flush();
		debut.set(Calendar.HOUR_OF_DAY, 0);
		debut.set(Calendar.MINUTE, 0);
		debut.set(Calendar.SECOND, 0);
		debut.set(Calendar.MILLISECOND, 0);
		//EchelonOrganigramme echelon = aManagerEffectif.selectEchelon(idfEchelon);
		Agent agent = aDaoEffectif.select(currentIdfAgent);
		List<SequenceTravail> lstSequence = aCycleManager.makeListSequenceForCycle(idfCycle);
		Calendar currentJour = Calendar.getInstance();
		currentJour.setTime(debut.getTime());
		long delta = 0;
		while ( currentJour.before(dateFin) ) {
			Calendar currentDay = Calendar.getInstance();
			currentDay.set(Calendar.HOUR_OF_DAY, 0);
			currentDay.set(Calendar.MINUTE, 0);
			currentDay.set(Calendar.SECOND, 0);
			currentDay.set(Calendar.MILLISECOND, 0);
			currentDay.set(Calendar.DAY_OF_MONTH, currentJour.get(Calendar.DAY_OF_MONTH));
			currentDay.set(Calendar.MONTH, currentJour.get(Calendar.MONTH));
			currentDay.set(Calendar.YEAR, currentJour.get(Calendar.YEAR));
			StatutPeriode statut = lstSequence.get((int)delta % lstSequence.size()).getStatut();
			log.debug("index " + delta % lstSequence.size() + " delta " + delta
					+ " currentJour " + currentJour.getTime() + " currentDay " + currentDay.getTime()
					+ " debut " + debut.getTime());
			if (statut != null) { 
				Periode aPeriode = new Periode();
				aPeriode.setJour(currentDay);
				aPeriode.setStatut(statut);
				aPeriode.setIdOrganization(idfEchelon);
				aPeriode.setAgent(agent);
				em.persist(aPeriode);
			}
			currentJour.add(Calendar.DATE, 1);
			delta ++;
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#checkBesoin(java.lang.Integer, java.util.Calendar)
	 */
	public List<BesoinPlageHelper> checkBesoin(Integer idfEchelon, Calendar currentJour) {
		CycleHoraire cycle = managerHoraire.seletCycleForDate(currentJour, idfEchelon);
		HashMap<Integer, BesoinPlageHelper> besoinPlage = this.makeBesoinEmploi(cycle);
		// Selection des périodes de garde pour le jour donné
		List<Periode> lstPeriode = aDaoPeriode.makeListForDate(currentJour, idfEchelon);
		for (Periode aPeriode : lstPeriode) {
			log.debug("Statut Periode {}", aPeriode.getStatut().getLibelle());
			// Selection des competences presentes dans cette période
			List<CompetenceAgent> lstCompetence = managerCompetence.makeListForAgent(aPeriode.getAgent().getIdfAgent());
			for (CompetenceAgent aCompetence : lstCompetence) {
				aPeriode.getStatut().getLstComposant().size();
				if (aPeriode.getAgent().getIdfAgent().equals(aCompetence.getAgent().getIdfAgent())
						&& aPeriode.getStatut().getPresence().equals(Presence.PRESENT)) {
					log.debug("\tAgent {}",aCompetence.getAgent().getIdfAgent());
					for (PlageHoraire aPlage : aPeriode.getStatut().getLstComposant()) {
						log.debug("\t\tPlage {}", aPlage.getLibelle());
						List<Integer> lstEquipageFourni = new ArrayList<Integer>();
						for (BesoinEquipage besoin : aPlage.getLstBesoinEquipage()) {
							if (besoin.isSecondaire())
								continue;
							// Si on a fourni déja un équipage pour cette plage, on ne le recompte pas
							if (lstEquipageFourni.contains(besoin.getEquipage().getIdfEquipage()))
								continue;
							// Check pour un equipage
							for (Poste poste : besoin.getEquipage().getLstPoste() ) {
								// Check pour un poste
								if (aCompetence.getEmploi().getIdEmploi() == poste.getEmploi().getIdEmploi()) {
									// Selection des competences presentes dans cette période
									log.debug("\t\t\tEmploi {}", poste.getEmploi().getLibelle());
									BesoinPlageHelper besoinPlageHelper = (BesoinPlageHelper) besoinPlage.get(aPlage.getIdfPlage());
									// Sil la plage n'existe pas 
									if (besoinPlageHelper == null) {
										besoinPlageHelper = new BesoinPlageHelper();
										besoinPlageHelper.setIdfPlage(aPlage.getIdfPlage());
										besoinPlageHelper.setLibelle(aPlage.getLibelle());
										besoinPlageHelper.setIdfBesoin(besoin.getIdfBesoin());
										BesoinEmploiHelper besoinEmploi = new BesoinEmploiHelper();
										besoinEmploi.setIdEmploi(aCompetence.getEmploi().getIdEmploi());
										besoinEmploi.setLibelle(aCompetence.getEmploi().getLibelle());
										besoinEmploi.setNbObtenu(1);
										besoinEmploi.setNbAffecte(0);
										besoinEmploi.setNbVoulu(0);
										Map<Integer, BesoinEmploiHelper> mapEmploi = new HashMap<Integer, BesoinEmploiHelper>();
										mapEmploi.put(besoinEmploi.getIdEmploi(), besoinEmploi);
										besoinPlageHelper.setLstEmploi(mapEmploi);
										besoinPlage.put(aPlage.getIdfPlage(), besoinPlageHelper);
										log.debug("Creation du besoin {} et de la plage {} pour l'agent {}", 
												new Object[]{poste.getEmploi().getLibelle(), aPlage.getLibelle(), aPeriode.getAgent().getIdfAgent()});
									}
									else {
										// La plage existe
										// Si le besoin n'a pas été trouvé 
										if (besoinPlageHelper.getLstEmploi().get(aCompetence.getEmploi().getIdEmploi()) == null) {
											BesoinEmploiHelper besoinEmploi = new BesoinEmploiHelper();
											besoinEmploi.setIdEmploi(aCompetence.getEmploi().getIdEmploi());
											besoinEmploi.setLibelle(aCompetence.getEmploi().getLibelle());
											besoinEmploi.setNbObtenu(1);
											besoinEmploi.setNbVoulu(0);
											besoinEmploi.setNbAffecte(0);
											besoinPlageHelper.getLstEmploi().put(aCompetence.getEmploi().getIdEmploi(), besoinEmploi);
											log.debug("Creation du besoin {} pour la plage {} pour l'agent {}", 
													new Object[]{poste.getEmploi().getLibelle(), aPlage.getLibelle(), aPeriode.getAgent().getIdfAgent()});
										}
										else  {
											log.debug("Increment du besoin {} pour la plage {} par l'agent {}", new Object[]{poste.getEmploi().getLibelle(),  aPlage.getLibelle(),aPeriode.getAgent().getIdfAgent()});
											// Le besoin a été trouvé, on l'incrémente
											besoinPlageHelper.getLstEmploi().get(aCompetence.getEmploi().getIdEmploi()).setNbObtenu(
													besoinPlageHelper.getLstEmploi().get(aCompetence.getEmploi().getIdEmploi()).getNbObtenu() + 1);
											besoinPlageHelper.setStatutPlanning(StatutPlanning.incomplete);
										}
										//log.debug("Compétence nécessaire {} : {}", aCompetence.getEmploi().getLibelle(), besoinPlageHelper.getLstEmploi().get(aCompetence.getEmploi().getIdEmploi()).getNbObtenu());
									}									
								}
							}
							lstEquipageFourni.add(besoin.getEquipage().getIdfEquipage());
						}
					}
				}
			}
			for (Piquet piquet  : aPeriode.getLstPiquet()) {
				BesoinPlageHelper besoinPlageHelper = (BesoinPlageHelper) besoinPlage.get(piquet.getPlage().getIdfPlage());
				besoinPlageHelper.getLstEmploi().get(piquet.getPoste().getEmploi().getIdEmploi()).setNbAffecte(
						besoinPlageHelper.getLstEmploi().get(piquet.getPoste().getEmploi().getIdEmploi()).getNbAffecte() + 1);
			}
		}
		for (BesoinPlageHelper besoin : besoinPlage.values() ) {
			boolean complete = true;
			for (BesoinEmploiHelper emploi :  besoin.getLstEmploi().values()) {
				if( emploi.getNbObtenu() < emploi.getNbVoulu() )
					complete = false;
			}
			if ( complete )
				besoin.setStatutPlanning(StatutPlanning.complete_stat);
		}
		return new ArrayList<BesoinPlageHelper>( besoinPlage.values() );
	}
	
	public List <BesoinWeekHelper> makeListBesoin (Integer idfEchelon) {
		SimpleDateFormat sfFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		List<BesoinPlageHelper> lstBesoin = new ArrayList<BesoinPlageHelper>();
		List<BesoinWeekHelper> lstBesoinMonth = new ArrayList<BesoinWeekHelper>();
		Calendar debutMois = Calendar.getInstance();
		Calendar finMois = Calendar.getInstance();
		Calendar jourMois = Calendar.getInstance();
		debutMois.setTime(currentCalendar.getTime());
		debutMois.setFirstDayOfWeek(Calendar.MONDAY);
		finMois.setTime(currentCalendar.getTime());
		finMois.add(Calendar.MONTH, 1);
		jourMois.setTime(currentCalendar.getTime());
		CycleHoraire cycle = managerHoraire.seletCycleForDate(debutMois, idfEchelon);
		if (cycle.getNext() != null) 
			if (cycle.getNext().getDateValidite().before(finMois.getTime()))
				finMois.setTime(cycle.getNext().getDateValidite());
		// On complete du lundi au 1er
		BesoinWeekHelper besoinWeek = new BesoinWeekHelper();
		while ( jourMois.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {			
			besoinWeek.getLstJour().add( new BesoinJourHelper());
			jourMois.add(Calendar.DAY_OF_MONTH, -1);
		}
		jourMois.setTime(debutMois.getTime());
		// lstBesoin = new ArrayList<BesoinPlageHelper>(this.makeBesoinEmploi(cycle).values() );	
		log.debug("Jour debut : {}",sfFormat.format( debutMois.getTime()) );
		log.debug("Jour fin : {}",sfFormat.format( finMois.getTime()) );		
		while (jourMois.before(finMois)) {
			if (jourMois.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ) {
				lstBesoinMonth.add(besoinWeek);
				besoinWeek = new BesoinWeekHelper();
			}
			// before () est equivalent à <=. On saute le dernier jour
			if (jourMois.get(Calendar.MONTH) == finMois.get(Calendar.MONTH))
				break;
			lstBesoin = this.checkBesoin(idfEchelon, jourMois);
			BesoinJourHelper besoinJour = new BesoinJourHelper();
			besoinJour.setJour(jourMois.get(Calendar.DAY_OF_MONTH));
			for (BesoinPlageHelper besoinPlage: lstBesoin) {				
				besoinJour.getLstBesoinPlage().add(besoinPlage);
			}
			besoinWeek.getLstJour().add(besoinJour);
			jourMois.add(Calendar.DAY_OF_MONTH, 1);
			log.debug("Jour : {}",sfFormat.format( jourMois.getTime()) );
		}
		if (besoinWeek.getLstJour().size() > 0)
			lstBesoinMonth.add(besoinWeek);
		return lstBesoinMonth;
	}
	
	
	private HashMap<Integer, BesoinPlageHelper>  makeBesoinEmploi(CycleHoraire cycle) {
		HashMap<Integer, BesoinPlageHelper> besoinPlage = new HashMap<Integer, BesoinPlageHelper>();
		for ( PlageHoraire plage : cycle.getLstPlage() ) {			
			for (BesoinEquipage besoin : plage.getLstBesoinEquipage()) {
				if (besoin.isSecondaire())
					continue;
				if (besoinPlage.get(plage.getIdfPlage()) == null) {
					BesoinPlageHelper newPlage = new BesoinPlageHelper();
					newPlage.setIdfPlage(plage.getIdfPlage());
					newPlage.setLibelle(plage.getLibelle());
					newPlage.setStatutPlanning(StatutPlanning.empty);
					besoinPlage.put(plage.getIdfPlage(), newPlage);
				}
				//HashMap<Integer, BesoinEmploiHelper> besoinEmploi = new HashMap<Integer, BesoinEmploiHelper>(); 
				for (Poste aPoste: besoin.getEquipage().getLstPoste() ) {					
					if ( besoinPlage.get(plage.getIdfPlage()).getLstEmploi().get(aPoste.getEmploi().getIdEmploi() ) != null ) {
						besoinPlage.get(plage.getIdfPlage()).getLstEmploi().get(aPoste.getEmploi().getIdEmploi() ).setNbVoulu(
								besoinPlage.get(plage.getIdfPlage()).getLstEmploi().get(aPoste.getEmploi().getIdEmploi() ).getNbVoulu()+1);
						log.debug("Compétence nécessaire {} : {}", new Object[] {
									aPoste.getEmploi().getLibelle(), 
									besoinPlage.get(plage.getIdfPlage()).getLstEmploi().get(aPoste.getEmploi().getIdEmploi() ).getNbVoulu()});
					}
					else {
						BesoinEmploiHelper besoinHelper = new BesoinEmploiHelper();
						besoinHelper.setIdEmploi(aPoste.getEmploi().getIdEmploi());
						besoinHelper.setLibelle(aPoste.getEmploi().getLibelle());
						besoinHelper.setNbVoulu(1);
						besoinHelper.setNbObtenu(0);
						besoinHelper.setNbAffecte(0);
						besoinPlage.get(plage.getIdfPlage()).getLstEmploi().put ( aPoste.getEmploi().getIdEmploi(), besoinHelper ) ;
						log.debug("Compétence nécessaire {} : {}", new Object[] {aPoste.getEmploi().getLibelle(), besoinHelper.getNbVoulu()});
					}				
				}
			}
		}
		return besoinPlage;
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#makeListForAgent(java.lang.Integer, java.lang.Integer)
	 */
	public List<PeriodeAgregator> makeListForAgent (Integer idfAgent, Integer year) {
		List<PeriodeAgregator> lstPeriode  = new ArrayList<PeriodeAgregator>();
		DateFormatSymbols symbolMonth = new DateFormatSymbols();
		PeriodeDaoLocal aDaoPeriode = ServiceLocatorFireTime.getPeriodeDaoBean();
		for (int month=0; month < symbolMonth.getMonths().length; month ++) {
			Long cumulatedHours = aDaoPeriode.calculateDureeForAgentAndMonth(idfAgent, month, year);
			String monthString = symbolMonth.getMonths()[month];
			PeriodeAgregator anAgregat = new PeriodeAgregator();
			anAgregat.setDureeComptabilisee(cumulatedHours);
			anAgregat.setMonth(monthString);
			lstPeriode.add(anAgregat);
		}
		return lstPeriode;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#makeListForMonth(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<JourAgent> makeListForMonth(Integer month, Integer year,
			Integer idfEchelon) {
		List<Periode> lstPeriode=  aDaoPeriode.makeListForMonth(month, year, idfEchelon);
		List<JourAgent> lstJour = new ArrayList<JourAgent>();
		for (Periode periode: lstPeriode) {
			JourAgent aJour = new JourAgent();
			aJour.setIdfPeriode(periode.getIdfPeriode());
			aJour.setIdfAgent(periode.getAgent().getIdfAgent());
			aJour.setJour(periode.getJour().get(Calendar.DAY_OF_MONTH));
	    	Statut aStatut = new Statut();
	    	aStatut.setCode(periode.getStatut().getCode());
	    	aStatut.setIdfStatut(periode.getStatut().getIdfStatut());
	    	aStatut.setLibelle(periode.getStatut().getLibelle());
	    	List<Integer> lstIdfPlage = new ArrayList<Integer>();
	    	for (PlageHoraire plage : periode.getStatut().getLstComposant() ) {
	    		lstIdfPlage.add(plage.getIdfPlage());
	    	}
	    	aStatut.setLstIdfPlage(lstIdfPlage);
			aJour.setStatut(aStatut);
			lstJour.add(aJour);
		}
		
		return lstJour;
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#makeListDispoForMonth(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<JourStatut> makeListDispoForMonth(Integer month, Integer year, Integer idfEchelon) {
		Calendar calDebut = Calendar.getInstance();
		calDebut.set(year, month , 1, 0, 0, 0);
		Calendar calFin = Calendar.getInstance();
		calFin.set(year, month , 1, 0, 0, 0);
		calFin.add(Calendar.MONTH, 1);
		log.debug("debut {} fin {} echelon {}" , new Object[]{calDebut.getTime(), calFin.getTime(),  idfEchelon});
		Query query = em.createNamedQuery("Disponibilite.selectForEchelonBetweenDate");
		query.setParameter("debut", calDebut);
		query.setParameter("fin", calFin);
		query.setParameter("idfEchelon", idfEchelon);
		List<JourStatut> lstJour = HoraireHelperFactory.makeLstJourStatut( query.getResultList());
		return lstJour;
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.PlanningManagerRemote#setDispo(java.lang.Integer, java.util.Calendar, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer setDispo(Integer matricule, Calendar currentJour,
			Integer idfPlage, Integer idfEchelon, Integer idfEchelonPere) throws AgentNotFoundException {
		
		Agent agent = aDaoEffectif.selectByMatricule(matricule, idfEchelonPere);
		if (agent == null)
			throw new AgentNotFoundException();
		Disponibilite dispo = new Disponibilite();
		Query query = em.createNamedQuery("Disponibilite.selectForAgentAndDate");
		query.setParameter("idfAgent", agent.getIdfAgent());
		query.setParameter("idfEchelon", idfEchelon);
		query.setParameter("idfPlage", idfPlage);
		query.setParameter("jour", currentJour);	
		List<Disponibilite> lstDispo = query.getResultList();
		if (lstDispo.size() == 0) {
			dispo.setIdOrganization(idfEchelon);
			dispo.setJour(currentJour);
			dispo.setAgent(agent);
			PlageHoraire plage = managerHoraire.select(idfPlage);
			dispo.setPlage(plage);
			em.persist(dispo);
			return dispo.getIdfDispo();
		}
		else {
			for (Disponibilite removedDispo : lstDispo) {
				em.remove(removedDispo);
			}
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.PlanningManagerRemote#unsetDispo(java.lang.Integer)
	 */
	@Override
	public void unsetDispo(Integer idfDispo) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.PlanningManagerRemote#makeListforAgentAndMonth(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JourStatut> makeListforAgentAndMonth(Integer matricule,
			Integer idfEchelon, Integer idfEchelonPere, Integer year, Integer month)
			throws AgentNotFoundException{
		Agent agent = aDaoEffectif.selectByMatricule(matricule, idfEchelonPere);
		if (agent == null)
			throw new AgentNotFoundException();
		Calendar calDebut = Calendar.getInstance();
		calDebut.set(year, month -1, 1, 0, 0, 0);
		Calendar calFin = Calendar.getInstance();
		calFin.set(year, month - 1, 1, 0, 0, 0);
		calFin.add(Calendar.MONTH, 1);
		log.debug("debut {} fin {} agent {} echelon {} parent echelon {}" , new Object[]{calDebut.getTime(), calFin.getTime(), agent.getIdfAgent(), idfEchelon, idfEchelonPere});
		Query query = em.createNamedQuery("Disponibilite.selectForAgentBetweenDate");
		query.setParameter("debut", calDebut);
		query.setParameter("fin", calFin);
		query.setParameter("idfEchelon", idfEchelon);
		query.setParameter("idfAgent", agent.getIdfAgent());
		List<JourStatut> lstJour = HoraireHelperFactory.makeLstJourStatut( query.getResultList());
		
		Map<Integer, JourStatut> mapJour = new HashMap<Integer, JourStatut>();
		for (JourStatut jour :  lstJour) {
			mapJour.put(jour.getJour().get(Calendar.DAY_OF_MONTH), jour);
		}
		List<Periode> lstPeriode = aDaoPeriode.makeListForAgentAndDate(
				agent.getIdfAgent(), calDebut, calFin, idfEchelon);
		log.debug("Nb Dispo {} Nb Periode {}", new Object[]{lstJour.size(), lstPeriode.size()} );
		for (Periode periode : lstPeriode) {
			JourStatut jour = mapJour.get(periode.getJour().get(Calendar.DAY_OF_MONTH) );
			if (jour != null) {
				// Pour toutes les plages ou il y a des dispos
				for (PlageHoraire plage : periode.getStatut().getLstComposant() ) {
					boolean trouve  = false;
					for (PlageStatut plageHelper : jour.getLstPlage() ) {
						if (plage.getIdfPlage() == plageHelper.getIdfPlage() ) {
							plageHelper.setPresence(periode.getStatut().getPresence());
							plageHelper.setIdfDispo(null);
							plage.setIdfPlage(plage.getIdfPlage());
							trouve = true;
						}
					}
					if ( ! trouve) {
						PlageStatut plageHelper = new PlageStatut();
						plageHelper.setIdfPlage(plage.getIdfPlage());
						plageHelper.setPresence(periode.getStatut().getPresence());
						jour.getLstPlage().add(plageHelper);
					}
				}
			}
			else {
				JourStatut jourGarde =  HoraireHelperFactory.makeJourStatut(periode);
				mapJour.put(periode.getJour().get(Calendar.DAY_OF_MONTH), jourGarde);
			}
		}
		query = em.createNamedQuery("CommentDay.ListForMonthAndAgent");
		query.setParameter("idfAgent", agent.getIdfAgent());
		query.setParameter("idOrga", idfEchelon);
		query.setParameter("debut", calDebut); 
		query.setParameter("fin", calFin); 
		List<CommentDay> lstComments = query.getResultList();
		log.debug("Nb found comments {}", lstComments.size());
		for (CommentDay comment : lstComments) {
			JourStatut jour = mapJour.get(comment.getDteComment().get(Calendar.DAY_OF_MONTH) );
			if (jour != null) {
				jour.setCommentaire(comment.getComment());
				jour.setIdfComment(comment.getIdfComment());
			}
			else {
				jour = new JourStatut();
				jour.setIdfAgent(comment.getAgent().getIdfAgent());
				jour.setIdOrganization(comment.getIdOrganization());
				jour.setJour(comment.getDteComment());
				jour.setCommentaire(comment.getComment());
				jour.setIdfComment(comment.getIdfComment());
				mapJour.put(comment.getDteComment().get(Calendar.DAY_OF_MONTH) , jour);				
			}
		}
		
		List<JourStatut> lstJourComplet = new ArrayList<JourStatut>(mapJour.values());
		return lstJourComplet;
	}
	//--------- Gestion des commantaires -------------------
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.PlanningManagerRemote#setComment(fr.firesoft.fireTime.ext.planning.CommentMonth)
	 */
	@Override
	public void setComment(CommentMonthHelper mthComment) {
		
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.PlanningManagerRemote#getCommentMonth(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CommentMonthHelper getCommentMonth(Integer month, Integer year,
			String matricule, Integer idfEchelon, Integer idfEchelonPere) 
		throws AgentNotFoundException {
		Agent agent = aDaoEffectif.selectByMatricule(Integer.parseInt(matricule), idfEchelonPere);
		if (agent == null)
			throw new AgentNotFoundException();
		Query query = em.createNamedQuery("CommentMonth.SelectForAgent");
		query.setParameter("idfAgent", agent.getIdfAgent());
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("idOrga", idfEchelon);
		List<CommentMonth> lstComments = query.getResultList();
		if (lstComments.size() == 0)
			return null;
		else
			return HelperFactory.make(lstComments.get(0));
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.PlanningManagerRemote#setComment(fr.firesoft.fireTime.ext.planning.CommentDay)
	 */
	@Override
	public void setComment(CommentDayHelper dayComment) {
		log.debug("Saving comment {} on date {} with id {}", 
				new Object[]{dayComment.getComment(), dayComment.getDteComment(), dayComment.getIdfComment()});
		CommentDay entity = null;
		if (dayComment.getIdfComment() != null) {
			entity = em.find(CommentDay.class, dayComment.getIdfComment());
		}
		else {
			entity = new CommentDay();
			Calendar calComment = Calendar.getInstance();
			calComment.setTime(dayComment.getDteComment());
			entity.setDteComment(calComment);
			entity.setIdOrganization(dayComment.getIdfOrga());
			Agent agent = aDaoEffectif.select(dayComment.getIdfAgent());
			entity.setAgent(agent);
		}
		if (dayComment.getComment().isEmpty() ) {
			if ( entity.getIdfComment() != null) 
				em.remove(entity);
		}
		else {
			entity.setComment(dayComment.getComment());
			if (entity.getIdfComment() == null)
				em.persist(entity);
		}		
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.PlanningManagerRemote#select(java.lang.Integer)
	 */
	@Override
	public CommentDayHelper select(Integer idfComment) {
		return HelperFactory.make( em.find(CommentDay.class, idfComment));
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommentDayHelper select(Calendar day, String matricule,
			Integer idfEchelon, Integer idfEchelonPere) throws AgentNotFoundException {
		Agent agent = aDaoEffectif.selectByMatricule(Integer.parseInt(matricule), idfEchelonPere);
		if (agent == null)
			throw new AgentNotFoundException();
		Query query = em.createNamedQuery("CommentDay.SelectForDayAndAgent");
		query.setParameter("day", day);
		query.setParameter("idfAgent", agent.getIdfAgent());
		query.setParameter("idOrga", idfEchelon);
		List<CommentDay> lstComments = query.getResultList();
		if (lstComments.size() == 0)
			return null;
		else
			return HelperFactory.make(lstComments.get(0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommentDayHelper> getComments(Integer month, Integer year,
			String matricule, Integer idfEchelon, Integer idfEchelonPere) throws AgentNotFoundException {
		Agent agent = aDaoEffectif.selectByMatricule(Integer.parseInt(matricule), idfEchelonPere);
		if (agent == null)
			throw new AgentNotFoundException();
		Query query = em.createNamedQuery("CommentDay.ListForMonthAndAgent");
		query.setParameter("idfAgent", agent.getIdfAgent());
		query.setParameter("idOrga", idfEchelon);
		List<CommentDay> lstComments = query.getResultList();
		return HelperFactory.makeListCommentDay(lstComments);
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PlanningManagerLocal#makeListCommentDayforMonth(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommentDayHelper> makeListCommentDayforMonth(Integer month,
			Integer year, Integer idfEchelon) {
		Calendar calDebut = Calendar.getInstance();
		calDebut.set(year, month , 1, 0, 0, 0);
		Calendar calFin = Calendar.getInstance();
		calFin.set(year, month , 1, 0, 0, 0);
		calFin.add(Calendar.MONTH, 1);
		log.debug("debut {} fin {} echelon {}" , new Object[]{calDebut.getTime(), calFin.getTime(),  idfEchelon});
		Query query = em.createNamedQuery("CommentDay.selectForEchelonBetweenDate");
		query.setParameter("debut", calDebut);
		query.setParameter("fin", calFin);
		query.setParameter("idfEchelon", idfEchelon);
		List<CommentDayHelper> lstComment = HelperFactory.makeListCommentDay( query.getResultList() );
		return lstComment;
	}

	@Override
	public CommentMonthHelper getCommentMonth(Integer month, Integer year,
			Integer idfAgent, Integer idfEchelon) {
		// TODO Auto-generated method stub
		return null;
	}

	// ------------Getters / Setters --------
	/**
	 * @return the currentCalendar
	 */
	public Calendar getCurrentCalendar() {
		return currentCalendar;
	}


	/**
	 * @param currentCalendar the currentCalendar to set
	 */
	public void setCurrentCalendar(Calendar currentCalendar) {
		this.currentCalendar = currentCalendar;
	}



}
