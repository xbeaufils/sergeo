/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.bean.CycleManagerLocal;
import fr.firesoft.fireTime.bean.ParametreDaoLocal;
import fr.firesoft.fireTime.entity.horaire.CycleTravail;
import fr.firesoft.fireTime.entity.horaire.SequenceTravail;
import fr.firesoft.fireTime.entity.horaire.SequenceTravailPk;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;

/**
 * @author xbeaufils
 *
 */
@Local (CycleManagerLocal.class)
@Stateless
public class CycleManager implements CycleManagerLocal {

	Logger log = LoggerFactory.getLogger(CycleManager.class);

	@PersistenceContext(unitName = "FireTime")
	private EntityManager em;

	@EJB
		ParametreDaoLocal aDaoParametre;
	/**
	 * 
	 */
	public CycleManager() {
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CycleManagerLocal#addSequence(fr.firesoft.fireTime.entity.horaire.CycleTravail, fr.firesoft.fireTime.entity.horaire.SequenceTravail)
	 */
	@Override
	public void addSequence(Integer idfCycleTravail, Integer idfStatut) {
		CycleTravail aCycle= this.select(idfCycleTravail);
		StatutPeriode statut = null;
		if (idfStatut != null)
			statut = aDaoParametre.select(idfStatut);
		SequenceTravail aSequence = new SequenceTravail();
		SequenceTravailPk aSeqId = new SequenceTravailPk();
		aSeqId.setCycle(aCycle);
		aSeqId.setOrdre(aCycle.getLstSequence().size() );
		aSequence.setId(aSeqId);
		aSequence.setStatut(statut);
		em.persist(aSequence);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CycleManagerLocal#makeListForCycle(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CycleTravail> makeListForCycle(Integer idfCycle) {
		Query query = em.createNamedQuery("CycleTravail.makeListForCycle");
		query.setParameter("idfCycle", idfCycle);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CycleManagerLocal#removeSequence(java.lang.Integer)
	 */
	@Override
	public void removeSequence( Integer idfCycle, Integer ordre) {
		try {
			List<SequenceTravail> lstSequence = this.makeListSequenceForCycle(idfCycle);
			// SequenceTravail deletedSequence = null;
			log.debug("size of sequence " + lstSequence.size());
			for (int index = 0; index < lstSequence.size(); index++) {
				if (lstSequence.get(index).getId().getOrdre() > ordre) {
					if (index < lstSequence.size()-1 ) {
						lstSequence.get(index).setStatut(lstSequence.get(index+1).getStatut());
					}
				}
			}
			em.remove(lstSequence.get(lstSequence.size()-1));
			em.flush();
		}
		catch (PersistenceException e) {
			log.error("ERREUR", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CycleManagerLocal#save(fr.firesoft.fireTime.entity.horaire.CycleTravail)
	 */
	@Override
	public void save(CycleTravail cycle) {
		if (cycle.getIdfCycle() == null)
			em.persist(cycle);
		else
			em.merge(cycle);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CycleManagerLocal#select(java.lang.Integer)
	 */
	@Override
	public CycleTravail select(Integer idfCycle) {
		CycleTravail aCycle = em.find(CycleTravail.class, idfCycle);
		aCycle.getLstSequence().size();
		return aCycle;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CycleManagerLocal#makeListSequenceForCycle(java.lang.Integer)
	 */
	@Override
	public List<SequenceTravail> makeListSequenceForCycle(Integer idfCycle) {
		CycleTravail aCycle = em.find(CycleTravail.class, idfCycle);
		List<SequenceTravail>lstSequence = aCycle.getLstSequence();
		Collections.sort(lstSequence, new SequenceComparator());
		return lstSequence;
	}
	/**
	 * @author xbeaufils
	 *
	 */
	public class SequenceComparator implements Comparator<SequenceTravail> {

		/**
		 * 
		 */
		public SequenceComparator() {
		}

		@Override
		public int compare(SequenceTravail arg0, SequenceTravail arg1) {
			return arg0.getId().getOrdre().compareTo(arg1.getId().getOrdre());
		}

	}

}
