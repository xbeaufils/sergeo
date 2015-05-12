/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.bean.AgresManagerLocal;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.entity.Poste;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.factory.BesoinHelperFactory;
import fr.firesoft.fireTime.helper.PosteHelper;

/**
 * @author xavier
 *
 */
@Local (AgresManagerLocal.class)
@Stateless
public class AgresManager implements AgresManagerLocal {

	Logger log = LoggerFactory.getLogger(AgresManager.class);
	@PersistenceContext(unitName = "FireTime")
    private EntityManager em;
   
	@EJB
		private CompetenceManagerLocal competenceManager;
	/**
	 * 
	 */
	public AgresManager() {
	}

	
	public void saveEquipage(Equipage equipage) {
		if (equipage.getIdfEquipage() == null)
			em.persist(equipage);
		else
			em.merge(equipage);
	}


	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AgresManagerLocal#save(fr.firesoft.fireTime.helper.EquipageHelper)
	 */
	@Override
	public void save(EquipageHelper equipage) {
		if (equipage.getIdfEquipage() == null) {
			//EchelonOrganigramme echelon = effectifManager.selectEchelon(equipage.getIdfEchelon());
			Equipage entity = new Equipage();
			entity.setLibelle(equipage.getLibelle());
			//entity.setEchelon(echelon);
			entity.setIdOrganization(equipage.getIdOrga());
			em.persist(entity);
		}
		else {
			Equipage entity = em.find(Equipage.class, equipage.getIdfEquipage());
			entity.setLibelle(equipage.getLibelle());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#addEmploi(java.lang.Integer, fr.firesoft.fireTime.entity.Equipage, boolean)
	 */
	@Override
	public void addEmploi(Integer idfEmploi, Integer idfEquipage, boolean optionnel) {
		EmploiOperationnel emploi = competenceManager.selectEmploi(idfEmploi);
		Equipage equipage = em.find(Equipage.class, idfEquipage);
		equipage.getLstPoste().size();
		int affichage;
		if (equipage.getLstPoste().isEmpty()) {
			affichage = 1;
		}
		else {
			// On l'ajoute à la fin 
			Poste lastEquipage = equipage.getLstPoste().get(equipage.getLstPoste().size() -1);
			affichage = lastEquipage.getAffichage() + 1;
		}
		Poste newPoste = new Poste();
		newPoste.setEquipage(equipage);
		newPoste.setEmploi(emploi);
		newPoste.setOptionnel(optionnel);
		newPoste.setAffichage(affichage);
		em.persist(newPoste);
	}


	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#downEmploi(java.lang.Integer)
	 */
	@Override
	public void downEmploi(Integer idfPoste) {
		Poste downPoste = em.find(Poste.class, idfPoste);
		List<Poste> lstExistantEquipage = this.makeListPosteForEquipage(downPoste.getEquipage().getIdfEquipage());
		for (Poste iteratedEquipage : lstExistantEquipage) {
			if (iteratedEquipage.getAffichage().intValue() == downPoste.getAffichage().intValue() + 1) {
				iteratedEquipage.setAffichage(downPoste.getAffichage());
				downPoste.setAffichage(new Integer(downPoste.getAffichage().intValue()+1));
				log.debug("idfPoste {} - affichage {}", idfPoste, downPoste.getAffichage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AgresManagerLocal#changeOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void changeOrder(Integer idfPoste, Integer newRow) {
		Poste changedPoste = em.find(Poste.class, idfPoste);
		Integer oldRow = changedPoste.getAffichage();
		List<Poste> lstExistantPoste = this.makeListPosteForEquipage(changedPoste.getEquipage().getIdfEquipage());
		for (Poste iteratedPoste : lstExistantPoste) {
			log.debug("Traitement de {} affichage {}", new Object[]{iteratedPoste.getIdfPoste(), iteratedPoste.getAffichage()});
			// Incrementation des records entre la nouvelle position et l'ancienne
			
			if (newRow < oldRow ) {
				if (iteratedPoste.getAffichage() >= newRow 
					&& iteratedPoste.getAffichage() <= oldRow) {
					iteratedPoste.setAffichage(iteratedPoste.getAffichage() + 1);
					log.debug("Increment de {}", iteratedPoste.getIdfPoste());
				}
			}
			else if (newRow > oldRow ) {
				if (iteratedPoste.getAffichage() <= newRow 
						&& iteratedPoste.getAffichage() >= oldRow) {
					iteratedPoste.setAffichage(iteratedPoste.getAffichage() - 1);
					log.debug("Décrement de {}", iteratedPoste.getIdfPoste());					
				}
			}
			// Cas du remplaçant qui doit s'incrementer 
			else if (iteratedPoste.getAffichage().equals(newRow)
					&& ! iteratedPoste.getIdfPoste().equals(idfPoste) ) {				
				if (newRow < oldRow ) {
					iteratedPoste.setAffichage(iteratedPoste.getAffichage() + 1);
					log.debug("Increment de {} qui perd sa place", iteratedPoste.getIdfPoste());
				}
				else {
					iteratedPoste.setAffichage(iteratedPoste.getAffichage() - 1);
					log.debug("Décrement de {} qui perd sa place", iteratedPoste.getIdfPoste());					
				}
			}
			if (iteratedPoste.getIdfPoste().equals(idfPoste) ){
				log.debug("Poste {} is at {}", new Object[]{idfPoste, newRow});
				iteratedPoste.setAffichage(newRow);
			}
		}		
		for (Poste iteratedPoste : lstExistantPoste)
			log.debug("idfPoste {} affichage {}", new Object[]{iteratedPoste.getIdfPoste(), iteratedPoste.getAffichage()} );
	}


	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#removeEmploi(java.lang.Integer)
	 */
	@Override
	public void removeEmploi(Integer idfPoste) {
		Poste removedEquipage = em.find(Poste.class, idfPoste);
		em.remove(removedEquipage);		
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#makeListPosteForEquipage(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Poste> makeListPosteForEquipage(Integer idfEquipage) {
		Query query = em.createNamedQuery("Poste.makeListForEquipage");
		query.setParameter("anIdf", idfEquipage);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#makeListPosteHelperForEquipage(java.lang.Integer)
	 */
	@Override
	public List<PosteHelper> makeListPosteHelperForEquipage(Integer idfEquipage) {
		return BesoinHelperFactory.makeListPoste(this.makeListPosteForEquipage(idfEquipage));
	}


}
