/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.besoin.BesoinPlageHelper;
import fr.firesoft.fireTime.ext.horaire.CycleHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.Presence;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.fireTime.factory.HoraireHelperFactory;

/**
 * @author xbeaufils
 *
 */
@Local (HoraireManagerLocal.class)
@Remote (HoraireDaoRemote.class)
@Stateless
public class HoraireManager implements HoraireManagerLocal, HoraireDaoRemote {


	@PersistenceContext(unitName = "FireTime")
	private EntityManager em;
	
	
	Logger log = LoggerFactory.getLogger(HoraireManager.class);
	private PlageHoraire plage; 
	private CycleHoraire cycle;
	/**
	 * 
	 */
	public HoraireManager() {
	}
	/**
	 * Gestion des cycle horaires
	 */
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#makeListForEchelon(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CycleHoraireHelper> makeListForEchelon(Integer idfEchelon) {
		Query query = em.createNamedQuery("CycleHoraire.makeListForEchelon");
		query.setParameter("anIdfEchelon", idfEchelon);
		return this.makeListCycle(query.getResultList());
		/*
		List<CycleHoraire> lstCycleHoraires = query.getResultList();
		List<CycleHoraireHelper> lstHelper = new ArrayList<CycleHoraireHelper>();
		for (CycleHoraire aCycle : lstCycleHoraires)
			aCycle.getLstPlage().size();
		return lstHelper;
		*/
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#selectCycle(java.lang.Integer)
	 */
	@Override
	public CycleHoraire selectCycle(Integer idfCycle) {
		cycle = em.find(CycleHoraire.class, idfCycle);
		return cycle;
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#makeListForCycle(java.lang.Integer)
	 */
	@Override
	public List<PlageHoraireHelper> makeListPlageForCycle(Integer idfCycle) {
		cycle = em.find(CycleHoraire.class, idfCycle);
		return HoraireHelperFactory.makeListPlage(cycle.getLstPlage());
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#makeListForStatut(java.lang.Integer)
	 */
	@Override
	public List<PlageHoraireHelper> makeListForStatut(Integer idfStatut) {
		// TODO Auto-generated method stub
		StatutPeriode aStatut = em.find(StatutPeriode.class, idfStatut);
		return HoraireHelperFactory.makeListPlage( aStatut.getLstComposant());
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#saveStatut(java.lang.Integer, java.util.List, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Remove
	public void saveStatut(Integer idfStatut, 
			String presence, String libelle, String code, Integer duree, Integer idfCycle) {
		StatutPeriode aStatut = null;
		if (idfStatut != null) {
			aStatut = em.find(StatutPeriode.class, idfStatut);
			log.debug("Composant size " + aStatut.getLstComposant().size());
			// Suppression des composants existants
			//aStatut.setLstComposant(new ArrayList<PlageHoraire>());
		}
		else {
			log.debug("Creating statut {} for cycle {}", new Object[]{libelle, idfCycle});
			CycleHoraire cycle = em.find(CycleHoraire.class, idfCycle);
			aStatut = new StatutPeriode();
			aStatut.setCycle(cycle);
		}
		aStatut.setCode(code);
		aStatut.setLibelle(libelle);
		aStatut.setPresence(Presence.valueOf(presence));
		aStatut.setDuree(duree);
		if (aStatut.getIdfStatut() == null)
			em.persist(aStatut); 
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#savePlageForStatut(fr.firesoft.fireTime.helper.Statut)
	 */
	@Override
	public void savePlageForStatut(Statut statut) {
		StatutPeriode currentStatut = em.find(StatutPeriode.class, statut.getIdfStatut());
		List<PlageHoraire> lstPresentPlage = currentStatut.getLstComposant();
		List<PlageHoraire> lstNewLstPlageHoraire = new ArrayList<PlageHoraire>();
		for (PlageHoraire plage : lstPresentPlage) {
			for (Integer newIdfPlage : statut.getLstIdfPlage()) {
				if (newIdfPlage.equals(plage.getIdfPlage())) {
					lstNewLstPlageHoraire.add(plage);
				}
			}
		}
		for (Integer newIdfPlage : statut.getLstIdfPlage()) {
			boolean mustBeAdded = true;
			for (PlageHoraire plage : lstPresentPlage) {
				if (newIdfPlage.equals(plage.getIdfPlage())) {
					mustBeAdded = false;
				}
			}
			if (mustBeAdded) {
				PlageHoraire newPlage = em.find(PlageHoraire.class, newIdfPlage);
				lstNewLstPlageHoraire.add(newPlage);
			}				
		}
		currentStatut.setLstComposant(lstNewLstPlageHoraire);
		em.flush();
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#deleteStatut(java.lang.Integer)
	 */
	@Override
	public void deleteStatut(Integer idfStatut) {
		StatutPeriode statut = em.find(StatutPeriode.class, idfStatut);
		em.remove(statut);
		
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#makeListStatutForCycle(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StatutPeriode> makeListStatutForCycle(Integer idfCycle) {
		Query query = em.createNamedQuery("StatutPeriode.makeListForCycle");
		query.setParameter("idfCycle", idfCycle);
		return query.getResultList();
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#makeListStatutHelperForCycle(java.lang.Integer)
	 */
	@Override
	public List<Statut> makeListStatutHelperForCycle(Integer idfCycle) {
		return HoraireHelperFactory.makeList(this.makeListStatutForCycle(idfCycle));
	}

	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.HoraireDaoRemote#makeListStatutDispoForCycle(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Statut> makeListStatutDispoForCycle(Integer idfCycle) {
		Query query = em.createNamedQuery("StatutPeriode.makeListDispoForCycle");
		query.setParameter("idfCycle", idfCycle);
		return  HoraireHelperFactory.makeList(query.getResultList());
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#save(java.lang.Integer, java.lang.String, java.util.Date, java.lang.Integer)
	 */
	@Override
	public void save(Integer idfCycle, String libelle, Date dateValidite,
			Integer idfEchelon) {
		if (idfCycle == null) {
			cycle = new CycleHoraire();
			cycle.setIdOrganization(idfEchelon);
		}
		else
			cycle = em.find(CycleHoraire.class, idfCycle);
		cycle.setDateValidite(dateValidite);
		cycle.setLibelle(libelle);
		if (cycle.getIdfCycle() == null)
			em.persist(cycle);
		else
			em.merge(cycle);
		
		
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#save(fr.firesoft.fireTime.entity.CycleHoraire)
	 */
	@Override
	public void save(String libelle, Date dateValidite, Integer idfEchelon) {
		// TODO Gérer la continuité des cycles
		if (cycle == null) {
			cycle = new CycleHoraire();
			cycle.setIdOrganization(idfEchelon);
		}
		cycle.setDateValidite(dateValidite);
		cycle.setLibelle(libelle);
		if (cycle.getIdfCycle() == null)
			em.persist(cycle);
		else
			em.merge(cycle);
		
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#seletCycleForDate(java.util.Calendar, java.lang.Integer)
	 */
	@Override
	public CycleHoraire seletCycleForDate(Calendar day, Integer idfEchelon) {
		Query query = em.createNamedQuery("CycleHoraire.selectForDateAndEchelon");
		log.debug("Date " + day.getTime() + " Echelon " + idfEchelon);
		query.setParameter("date", day.getTime());
		query.setParameter("anIdfEchelon", idfEchelon);
		return (CycleHoraire) query.getSingleResult();
	}
	/**
	 * Gestion des plages horaires
	 */
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#save(fr.firesoft.fireTime.helper.CritereHelper)
	 */
	@Override
	public void save(PlageHoraireHelper helper) {
		log.debug("Saving plage {} width id {}", 
				new Object[]{helper.getLibelle(), helper.getIdfPlage()} );
		/*
		if (plage == null) {
			plage = new PlageHoraire();
			plage.setDebut(Calendar.getInstance());
			plage.getDebut().setTimeInMillis(0);
			plage.setCycle(cycle);
		}
		*/
		PlageHoraire plage = null;
		if (helper.getIdfPlage() == null)
			plage = new PlageHoraire();
		else {
			plage = em.find(PlageHoraire.class, helper.getIdfPlage());
		}
		plage.setLibelle(helper.getLibelle());
		plage.setDebut(Calendar.getInstance());
		plage.getDebut().setTime( helper.getDebut() );
		plage.setFin(Calendar.getInstance());
		plage.getFin().setTime(helper.getFin());
		
		//plage.getDebut().set(Calendar.HOUR_OF_DAY, helper.getHeureDebut());
		//plage.getDebut().set(Calendar.MINUTE, helper.getMinuteDebut());
		if (plage.getIdfPlage() == null) {
			CycleHoraire cycle = em.find(CycleHoraire.class, helper.getIdfCycle());
			plage.setCycle(cycle);
			em.persist(plage);
		}
		else 
			em.merge(plage);
	}


	@Override
	public PlageHoraireHelper create(Integer idfEchelon) {
		plage = new PlageHoraire();
		plage.setDebut(Calendar.getInstance());
		plage.getDebut().setTimeInMillis(0);
		return HoraireHelperFactory.make(plage);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#select(java.lang.Integer)
	 */
	@Override
	public PlageHoraireHelper selectHelper(Integer idfCritere) {
		plage  = em.find(PlageHoraire.class, idfCritere);
		return HoraireHelperFactory.make(plage);
	}


	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#select(java.lang.Integer)
	 */
	@Override
	public PlageHoraire select(Integer idfCritere) {
		plage  = em.find(PlageHoraire.class, idfCritere);
		return plage;
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#getCurrentCycle()
	 */
	@Override
	public CycleHoraire getCurrentCycle() {
		return cycle;
	}
	@Override
	public void deleteBesoin(PlageHoraireHelper aCritere, Integer idfBesoin) {
		int index = -1;
		for (int i = 0; i< aCritere.getLstBesoin().size(); i++) {
			if (aCritere.getLstBesoin().get(i).getIdfBesoin().equals(idfBesoin))
				index = i;
		}
		if (index >= 0) {
			if ( ((BesoinPlageHelper) aCritere.getLstBesoin().get(index)).getIdfBesoin() < 0)
				aCritere.getLstBesoin().remove(index);
			else
				((BesoinPlageHelper) aCritere.getLstBesoin().get(index)).setDeleted(true);
		}
		log.debug ("Index " + index + " idfBesoin " + idfBesoin);
	}
	@Override
	@Remove
	public void abort() {
		// NOP	
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.HoraireManagerLocal#savePlageHoraire(java.util.List)
	 *
	@Override
	public void savePlageHoraire(List<PlageHoraireHelper> lstPlage) {
		Collections.sort(lstPlage, new PlageComparator());
		List<PlageHoraire> lstPlageToUpdate = new ArrayList<PlageHoraire>();
		for (PlageHoraireHelper aPlage: lstPlage) {
			/*
			  if (aPlage.getIdfPlage() > 0) {
				
				// c'est un update ou un delete
				PlageHoraire existingPlage  = em.find(PlageHoraire.class, aPlage.getIdfPlage());
				if (aPlage.getHeureDebut() == null ) {
					// c'est un delete
					em.remove(existingPlage);
				}
				else {
					// c'est un update
					existingPlage.setLibelle(aPlage.getLibelle());
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(0);
					//cal.set(Calendar.HOUR_OF_DAY, aPlage.getHeureDebut());
					//cal.set(Calendar.MINUTE, aPlage.getMinuteDebut());
					existingPlage.setDebut(cal);	
					lstPlageToUpdate.add(existingPlage);
				}				
			}
			else {
				// c'est une création
				if (aPlage.getHeureDebut() != null) {
					// c'est une création
					PlageHoraire newPlage = new PlageHoraire();
					newPlage.setLibelle(aPlage.getLibelle());
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(0);
					cal.set(Calendar.HOUR_OF_DAY, aPlage.getHeureDebut());
					cal.set(Calendar.MINUTE, aPlage.getMinuteDebut());
					newPlage.setDebut(cal);
					newPlage.setCycle(cycle);
					em.persist(newPlage);
					lstPlageToUpdate.add(newPlage);
				}
			}
			*
		}
		if (lstPlageToUpdate.size()>1) {
			// il n'y a un next que si on a au moins 2 plages horaires
			for (int i= lstPlageToUpdate.size()-2; i>=0; i--) {
				lstPlageToUpdate.get(i).setNext(lstPlageToUpdate.get(i+1));
			}
		}
		
	}*/
	@Override
	@Remove
	public void delete() {
		if (plage.getIdfPlage() == null)
			return;
		plage = em.merge(plage);
		em.remove(plage);
	}


	// --------- private methods ---------------
	private CycleHoraireHelper make(CycleHoraire entity) {
		CycleHoraireHelper helper = new CycleHoraireHelper();
		helper.setIdfCycle(entity.getIdfCycle());
		helper.setDateValidite(entity.getDateValidite());
		helper.setIdOrganization(entity.getIdOrganization());
		helper.setLibelle(entity.getLibelle());
		return helper;
	}
	
	private List<CycleHoraireHelper> makeListCycle(List<CycleHoraire> lstCycle) {
		List<CycleHoraireHelper> lstHelper = new ArrayList<CycleHoraireHelper>();
		for (CycleHoraire aCycle : lstCycle) {
			lstHelper.add(make(aCycle));
		}
		return lstHelper;
	}

/*	private List<BesoinHelper> make (List<BesoinCentre> lstBesoin) {
		if (lstBesoin == null)
			return new ArrayList<BesoinHelper>();
		List<BesoinHelper> lstHelper = new ArrayList<BesoinHelper>();
		for (BesoinCentre aBesoin : lstBesoin) {
			BesoinHelper aHelper = new BesoinHelper();
			aHelper.setIdfBesoin(aBesoin.getIdfBesoin());
			aHelper.setEmploi(aBesoin.getEmploi());
			aHelper.setNombre(aBesoin.getNombre());
			lstHelper.add(aHelper);
		}
		return lstHelper;
	}
*/	
	/**
	 * @author xbeaufils
	 *
	 */
	public class PlageComparator implements Comparator<PlageHoraireHelper> {

		/**
		 * 
		 */
		public PlageComparator() {
		}
		
		@Override
		public int compare(PlageHoraireHelper arg0, PlageHoraireHelper arg1) {
			if (arg0.getDebut() == null)
				if (arg1.getDebut() == null)
					return 0;
				else
					return -1;
			else
				if (arg1.getDebut() == null)
					return 1;
			return arg0.getDebut().compareTo(arg1.getDebut()) ;
		}
		
	}


}
