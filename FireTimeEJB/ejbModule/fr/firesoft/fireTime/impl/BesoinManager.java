/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.ext.besoin.BesoinPlageHelper;
import fr.firesoft.fireTime.ext.horaire.BesoinEquipageHelper;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.factory.BesoinHelperFactory;
import fr.firesoft.fireTime.helper.PosteHelper;

/**
 * @author xbeaufils
 *
 */
@Local (BesoinManagerLocal.class)            
@Stateless
public class BesoinManager implements BesoinManagerLocal {

	Logger log = LoggerFactory.getLogger(BesoinManager.class);
	
	@PersistenceContext(unitName = "FireTime")
    private EntityManager em;
	/**
	 * 
	 */
	public BesoinManager() {
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#makeListEquipageForEchelon(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EquipageHelper> makeListEquipageForEchelon(Integer idfEchelon) {
		Query query = em.createNamedQuery("Equipage.makeListFofEchelon");
		query.setParameter("idfEchelon", idfEchelon);
		return BesoinHelperFactory.makeList( query.getResultList() );
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#makeLisPosteForEchelon(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PosteHelper> makeLisPosteForEchelon(Integer idfEchelon) {
		Query query = em.createNamedQuery("Poste.makeListForEchelon");
		query.setParameter("idfEchelon", idfEchelon);
		return BesoinHelperFactory.makeListPoste(query.getResultList());
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#selectEquipage(java.lang.Integer)
	 */
	@Override
	public Equipage selectEquipage(Integer idfEquipage) {
		Equipage currentEquipage = em.find(Equipage.class, idfEquipage);
		currentEquipage.getLstPoste().size();
		return currentEquipage;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#makeListBesoinForPlage(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BesoinEquipageHelper> makeListBesoinForPlage(Integer idfPlage) {
		Query query = em.createNamedQuery("BesoinEquipage.makeListForPlage");
		query.setParameter("idfPlage", idfPlage);
		return BesoinHelperFactory.makeListEquipage(query.getResultList());
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#selectBesoin(java.lang.Integer)
	 */
	@Override
	public BesoinEquipage selectBesoin(Integer idfBesoin) {
		BesoinEquipage aBesoin = em.find(BesoinEquipage.class, idfBesoin);
		log.debug("select {} of echelon {}", new Object[]{aBesoin.getLibelle(), aBesoin.getPlage().getCycle().getIdOrganization()});
		return aBesoin;
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#selectBesoinHelper(java.lang.Integer)
	 */
	@Override
	public BesoinEquipageHelper selectBesoinHelper(Integer idfBesoin) {
		BesoinEquipage aBesoin = this.selectBesoin(idfBesoin);
		return BesoinHelperFactory.make(aBesoin);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#addBesoinEquipage(fr.firesoft.fireTime.helper.CritereHelper, java.lang.String, java.lang.Integer)
	 */
	@Override
	@Remove
	public void addBesoinEquipage(BesoinEquipageHelper aHelper) {
		BesoinEquipage aBesoin = this.makeEntity(aHelper);
		if (aBesoin.getIdfBesoin() == null) {
			log.debug("adding {}", aBesoin.getLibelle() );
			em.persist(aBesoin);
		}
		else
			em.merge(aBesoin);
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#deleteBesoinEquipage(fr.firesoft.fireTime.helper.CritereHelper, java.lang.Integer)
	 */
	@Override
	public void deleteBesoinEquipage(PlageHoraireHelper aCritere, Integer idfBesoin) {
		int index = -1;
		for (int i = 0; i< aCritere.getLstBesoinEquipage().size(); i++) {
			if (aCritere.getLstBesoinEquipage().get(i).getIdfBesoin().equals(idfBesoin))
				index = i;
		}
		if (index >= 0) {
			if ( ((BesoinEquipageHelper) aCritere.getLstBesoinEquipage().get(index)).getIdfBesoin() < 0)
				aCritere.getLstBesoinEquipage().remove(index);
			else
				((BesoinPlageHelper) aCritere.getLstBesoin().get(index)).setDeleted(true);
		}
		log.debug ("Index " + index + " idfBesoin " + idfBesoin);
		
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.BesoinManagerLocal#deleteBesoinEquipage(java.lang.Integer)
	 */
	@Override
	public void deleteBesoinEquipage(Integer idfBesoin) {
		log.debug ("Remove idfBesoin " + idfBesoin);
		BesoinEquipage deletedBesoin = this.selectBesoin(idfBesoin);
		em.remove(deletedBesoin);		
	}
/*
	public static EquipageHelper makeEquipage(Equipage equipage) {
		EquipageHelper helperEquipage = new EquipageHelper();
		helperEquipage.setIdfEquipage(equipage.getIdfEquipage());
		helperEquipage.setLibelle(equipage.getLibelle());
		helperEquipage.setIdOrga(equipage.getIdOrganization());
		return helperEquipage;
	}
*/
	private BesoinEquipage makeEntity(BesoinEquipageHelper aHelper) {
		BesoinEquipage aBesoin = new BesoinEquipage();
		aBesoin.setIdfBesoin(aHelper.getIdfBesoin());
		aBesoin.setLibelle(aHelper.getLibelle());
		Equipage equipage = this.selectEquipage(aHelper.getIdfEquipage());
		aBesoin.setEquipage(equipage);
		PlageHoraire plage = em.find(PlageHoraire.class, aHelper.getIdfPlageHelper());
		aBesoin.setPlage(plage);
		aBesoin.setSecondaire(aHelper.getSecondaire());
		return aBesoin;
	}
	
}
