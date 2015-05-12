/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.bean.GardeManagerLocal;
import fr.firesoft.fireTime.bean.PeriodeDaoLocal;
import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.entity.Periode;
import fr.firesoft.fireTime.entity.Piquet;
import fr.firesoft.fireTime.entity.Poste;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.helper.PiquetReport;

/**
 * @author xbeaufils
 *
 */
@Local (GardeManagerLocal.class)
@Stateful
public class GardeManager implements GardeManagerLocal {


	Logger log = LoggerFactory.getLogger(GardeManager.class);
	
	@PersistenceContext(unitName = "FireTime")
	    private EntityManager em;
	@EJB
		private BesoinManagerLocal aBesoinManager;
	@EJB
		private PeriodeDaoLocal aDaoPeriode;
	private Integer idfTempBesoin = -1;
	private HashMap<Integer,List<Piquet> >  lstPiquet;
	/**
	 * 
	 */
	public GardeManager() {
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.GardeManagerLocal#makeCompleteListForType(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, List<Piquet>> makeCompleteListForBesoin(Integer idfBesoin, Integer idfPlage, Calendar day) {
		BesoinEquipage currentBesoin = aBesoinManager.selectBesoin(idfBesoin);
		log.debug("besoin " + idfBesoin + " plage " + idfPlage + " date " + day.getTime()); 
		// Recherche des piquets déja pourvus lstPiquetForBesoin
		Query query = em.createNamedQuery("Piquet.makeListForBesoin");
		query.setParameter("idfBesoin", currentBesoin.getIdfBesoin());
		query.setParameter("idfPlage", idfPlage);
		query.setParameter("date", day);
		List<Piquet> lstPiquetPourvu = query.getResultList();
		// Recherche de tous les piquets necessaires
		Equipage currentEquipage = aBesoinManager.selectEquipage(currentBesoin.getEquipage().getIdfEquipage());
		for (Poste aPoste : currentEquipage.getLstPoste()) {
			boolean postePourvu = false;
			for (Piquet aPiquet : lstPiquetPourvu) {
				if (aPiquet.getPoste().equals(aPoste))
					postePourvu = true;
			}
			if (! postePourvu) {
				Piquet newPiquet = new Piquet();
				newPiquet.setIdfPiquet(idfTempBesoin-- );
				newPiquet.setPoste(aPoste);
				newPiquet.setBesoin(currentBesoin);
				lstPiquetPourvu.add(newPiquet);
			}
		}
		log.debug("Nombre de piquet "+ lstPiquetPourvu.size() + " for " + currentBesoin.getLibelle());
		if (lstPiquet == null)
			lstPiquet = new HashMap<Integer, List<Piquet>>();
		Collections.sort(lstPiquetPourvu, new PiquetComparator());
		lstPiquet.put(idfBesoin, lstPiquetPourvu);
		return lstPiquet;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.GardeManagerLocal#affecte(java.lang.Integer, java.util.List)
	 */
	@Override
	public void affecte(Integer idfPeriode, Integer idfPlage, List<Integer> lstIdfPiquet) {
		log.debug("List to save " + lstIdfPiquet + " ");
		PlageHoraire currentPlage = em.find(PlageHoraire.class, idfPlage);
		Periode periode = aDaoPeriode.select(idfPeriode);
		for (List<Piquet> aLstPiquet : lstPiquet.values()) {
			log.debug("List to check " + aLstPiquet + " ");
			for (Piquet aPiquet : aLstPiquet) {
				if (lstIdfPiquet.contains(aPiquet.getIdfPiquet())) {
					aPiquet.setPeriode(periode);
					aPiquet.setPlage(currentPlage);
					if (aPiquet.getIdfPiquet() < 0) {
						aPiquet.setIdfPiquet(null);
						em.persist(aPiquet);
					}
					else
						em.merge(aPiquet);
				}
			}
			
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.GardeManagerLocal#affecte(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void affecte(Integer idfPeriode, Integer idfPlage, Integer idfPoste,
			Integer idfBesoin) {
		log.debug("Affecte to save periode {} plage {} poste {} besoin {}", 
				new Object[]{idfPeriode, idfPlage, idfPoste, idfBesoin});
		Periode periode = aDaoPeriode.select(idfPeriode);
		Query query = em.createNamedQuery("Piquet.makeListForPoste");
		query.setParameter("idfBesoin", idfBesoin);
		query.setParameter("idfPlage", idfPlage);
		query.setParameter("idfPoste", idfPoste);
		query.setParameter("date", periode.getJour());
		@SuppressWarnings("unchecked")
		List<Piquet> lstPiquetPresent =  query.getResultList();
		for (Piquet removedPiquet : lstPiquetPresent) {
			em.remove(removedPiquet);
		}
		// Should be empty, at  most only one record
		PlageHoraire currentPlage = em.find(PlageHoraire.class, idfPlage);
		BesoinEquipage besoin = aBesoinManager.selectBesoin(idfBesoin);
		Poste poste = em.find(Poste.class, idfPoste);

		Piquet aPiquet = new Piquet();
		aPiquet.setPeriode(periode);
		aPiquet.setPlage(currentPlage);
		aPiquet.setBesoin(besoin);
		
		aPiquet.setPoste(poste);
		em.persist(aPiquet);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.GardeManagerLocal#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer idfPiquet) {
		Piquet piquet = em.find(Piquet.class, idfPiquet);
		em.remove(piquet);
		em.flush();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.GardeManagerLocal#makeListForDay(java.util.Calendar)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PiquetReport> makeListForDay(Calendar day, Integer idfEchelon) {
		Query query = em.createNamedQuery("Piquet.makeListForDay");
		query.setParameter("date", day);
		query.setParameter("idfEchelon", idfEchelon);
		List<Piquet> lstPiquet = query.getResultList();
		List<PiquetReport> lstReport = new ArrayList<PiquetReport>();
		for (Piquet aPiquet : lstPiquet) {
			PiquetReport aReport = new PiquetReport();
			aReport.setAffichageEmploi(aPiquet.getPoste().getAffichage());
			aReport.setDatePiquet(day.getTime());
			aReport.setLibelleBesoin(aPiquet.getBesoin().getLibelle());
			aReport.setLibelleEmploi(aPiquet.getPoste().getEmploi().getLibelle());
			aReport.setLibellePlage(aPiquet.getPlage().getLibelle());
			aReport.setNomPrenom(aPiquet.getPeriode().getAgent().getNom() 
					+ " " +  aPiquet.getPeriode().getAgent().getPrenom());
			lstReport.add(aReport);
		}
		Collections.sort(lstReport, new PiquetReportComparator());
		return lstReport;
	}
	/**
	 * @author xbeaufils
	 *
	 */
	public class PiquetComparator implements Comparator<Piquet> {

		/**
		 * 
		 */
		public PiquetComparator() {
		}

		@Override
		public int compare(Piquet arg0, Piquet arg1) {
			return arg0.getPoste().getAffichage().compareTo(arg1.getPoste().getAffichage());
		}

	}
	/**
	 * @author xbeaufils
	 *
	 */
	public class PiquetReportComparator implements Comparator<PiquetReport> {

		/**
		 * 
		 */
		public PiquetReportComparator() {
		}

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(PiquetReport arg0, PiquetReport arg1) {
			int compar = arg0.getLibellePlage().compareTo(arg1.getLibellePlage());
			if (compar != 0 )
				return compar;
			compar = arg0.getLibelleBesoin().compareTo(arg1.getLibelleBesoin());
			if (compar != 0)
				return compar;
			return arg0.getAffichageEmploi().compareTo(arg1.getAffichageEmploi());
		}

	}

}
