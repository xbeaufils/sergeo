/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.bean.PeriodeDaoLocal;
import fr.firesoft.fireTime.entity.Periode;

/**
 * @author xbeaufils
 *
 */
@Local (PeriodeDaoLocal.class)
@Stateless
public class PeriodeDao implements PeriodeDaoLocal {

	Logger log = LoggerFactory.getLogger(PeriodeDao.class);
	@PersistenceContext(unitName = "FireTime")
    private EntityManager em;

    /**
	 * 
	 */
	public PeriodeDao() {
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#makeListForMonth(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Periode> makeListForMonth(Integer month, Integer year, Integer idfEchelon) {
		Calendar calDebut = Calendar.getInstance();
		calDebut.set(Calendar.DAY_OF_MONTH, 1);
		calDebut.set(Calendar.MONTH, month);
		calDebut.set(Calendar.YEAR, year);	
		calDebut.set(Calendar.HOUR_OF_DAY, 0);
		calDebut.set(Calendar.MINUTE, 0);
		calDebut.set(Calendar.SECOND, 0);
		Calendar calFin = Calendar.getInstance();
		calFin.set(Calendar.MONTH, month);
		calFin.set(Calendar.YEAR, year);
		calFin.set(Calendar.DAY_OF_MONTH, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
		Query query = em.createNamedQuery("Periode.makeListForIntervalle");
		query.setParameter("debut", calDebut);
		query.setParameter("fin", calFin);
		query.setParameter("idfEchelon", idfEchelon);
		log.debug("debut {} fin {} echelon {}", new Object[]{calDebut.getTime(), calFin.getTime(), idfEchelon});
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#makeListForWeek(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Periode> makeListForWeek(Integer week, Integer year,
			Integer idfEchelon) {

		Calendar calDebut = Calendar.getInstance();
		calDebut.set(Calendar.WEEK_OF_YEAR, week);
		calDebut.set(Calendar.YEAR, year);	
		calDebut.set(Calendar.HOUR_OF_DAY, 0);
		calDebut.set(Calendar.MINUTE, 0);
		calDebut.set(Calendar.SECOND, 0);
		calDebut.set(Calendar.DAY_OF_YEAR, calDebut.get(Calendar.DAY_OF_YEAR) - calDebut.get(Calendar.DAY_OF_WEEK));
		Calendar calFin = Calendar.getInstance();
		calFin.set(Calendar.WEEK_OF_YEAR, week);
		calFin.set(Calendar.YEAR, year);
		calFin.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		Query query = em.createNamedQuery("Periode.makeListForIntervalle");
		query.setParameter("debut", calDebut);
		query.setParameter("fin", calFin);
		query.setParameter("idfEchelon", idfEchelon);
		log.debug("debut {} fin {} echelon {}", new Object[]{calDebut.getTime(), calFin.getTime(), idfEchelon});
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#selectForAgentAndDate(java.lang.Integer, java.util.Calendar)
	 */
	@Override
	public Periode selectForAgentAndDate(Integer idfAgent, Calendar day, Integer idfEchelon) {
		try {
			Query query = em.createNamedQuery("Periode.selectForAgentAndDate");
			query.setParameter("anIdfAgent", idfAgent);
			query.setParameter("aDay", day);
			query.setParameter("idfEchelon", idfEchelon);
			return (Periode) query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#select(java.lang.Integer)
	 */
	@Override
	public Periode select(Integer idfPeriode) {
		return em.find(Periode.class, idfPeriode);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#selectForAgentAndDate(java.lang.Integer, java.util.Calendar, java.util.Calendar)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Periode> makeListForAgentAndDate(Integer idfAgent, Calendar debut,
			Calendar fin, Integer idfEchelon) {
		try {
			log.debug("debut {} fin {} echelon {} idfAgent {}", new Object[]{debut.getTime(), fin.getTime(), idfEchelon, idfAgent});
			Query query = em.createNamedQuery("Periode.selectForAgentBetweenDate");
			query.setParameter("anIdfAgent", idfAgent);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			query.setParameter("idfEchelon", idfEchelon);
			return  query.getResultList();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#makeListForDate(java.util.Calendar)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Periode> makeListForDate(Calendar day, Integer idfEchelon) {
		Query query = em.createNamedQuery("Periode.makeListForDateAndEchelon");
		day.set(Calendar.HOUR_OF_DAY, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		day.set(Calendar.MILLISECOND, 0);
		query.setParameter("idfEchelon", idfEchelon);
		query.setParameter("date", day);
		List<Periode> lstPeriode = query.getResultList();
		return lstPeriode;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#calculateDureeForAgentAndMonth(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Long calculateDureeForAgentAndMonth(Integer idfAgent, Integer month, Integer year) {
		Calendar debut = Calendar.getInstance();
		debut.set(Calendar.MONTH, month);
		debut.set(Calendar.DATE, 1);
		debut.set(Calendar.HOUR, 0);
		debut.set(Calendar.MINUTE, 0);
		debut.set(Calendar.SECOND, 0);
		debut.set(Calendar.MILLISECOND, 0);
		debut.set(Calendar.YEAR, year);
		Calendar fin = Calendar.getInstance(); 
		fin.set(Calendar.MONTH, month + 1); 
		fin.set(Calendar.DATE, 1);
		fin.set(Calendar.HOUR, 0);
		fin.set(Calendar.MINUTE, 0);
		fin.set(Calendar.SECOND, 0);
		fin.set(Calendar.MILLISECOND, 0);
		fin.set(Calendar.YEAR, year);
		try {
			Query query = em.createNamedQuery("Periode.calculateDureeForIntervalle");
			query.setParameter("idfAgent", idfAgent);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			return (Long) query.getSingleResult();
		}
		catch (NoResultException e) {
			return new Long(0);
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.PeriodeDaoLocal#makeListForDateAndPlage(java.util.Calendar, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Periode> makeListForDateAndPlage(Calendar day,
			Integer idfEchelon, Integer idfPlage) {
		try {
			Query query = em.createNamedQuery("Periode.makeListAgentForDateAndPlage");
			query.setParameter("idfEchelon", idfEchelon);
			query.setParameter("idfPlageHoraire", idfPlage);
			query.setParameter("aDay", day);
			List<Periode> lstPeriode = query.getResultList();
			log.debug("Plage {} Jour {} Echelon {} Nb found periodes = {}", new Object[]{ idfPlage, day.getTime(), idfEchelon, lstPeriode.size()});
			return lstPeriode;
		}
		catch (NoResultException e) {
			return null;
		}
	}

}
