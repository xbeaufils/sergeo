/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.firesoft.fireTime.bean.ParametreDaoLocal;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.fireTime.factory.HoraireHelperFactory;
import fr.firesoft.fireTime.helper.EmploiOperationnelHelper;

/**
 * @author xbeaufils
 *
 */
@Stateless
@Local (ParametreDaoLocal.class)
public class ParametreDao implements ParametreDaoLocal {

	   @PersistenceContext(unitName = "FireTime")
	    private EntityManager em;
	/**
	 * 
	 */
	public ParametreDao() {
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.ParametreDaoLocal#makeListStatut()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StatutPeriode> makeListStatut() {
		Query query = em.createNamedQuery("StatutPeriode.makeListAll");
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.ParametreDaoLocal#select(java.lang.Integer)
	 */
	@Override
	public StatutPeriode select(Integer idfStatutPeriode) {
		StatutPeriode aStatut = em.find(StatutPeriode.class, idfStatutPeriode);
		return aStatut;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.ParametreDaoLocal#selectHelper(java.lang.Integer)
	 */
	@Override
	public Statut selectHelper(Integer idfStatutPeriode) {
		return HoraireHelperFactory.make(this.select(idfStatutPeriode));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmploiOperationnelHelper> makeListEmploiOperationel() {
		Query query = em.createNamedQuery("EmploiOperationnel.makeListAll");
		return query.getResultList();
	}

}
