/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.entity.Activite;
import fr.firesoft.fireTime.entity.CategorieEmploi;
import fr.firesoft.fireTime.entity.CompetenceAgent;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.UniteValeur;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.factory.HelperFactory;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;

/**
 * @author xbeaufils
 *
 */
/**
 * @author xbeaufils
 *
 */
@Local (CompetenceManagerLocal.class)
@Stateless
public class CompetenceManager implements CompetenceManagerLocal {

	Logger log = LoggerFactory.getLogger(CompetenceManager.class);
	
	@PersistenceContext(unitName = "FireTime")
	    private EntityManager em;
	   
	   @EJB
	   		private EffectifDaoLocal aDaoEffectif;
	   
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#addCompetenceForAgent(java.lang.Integer, java.lang.String, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addCompetenceForAgent(Integer idfAgent, String codeUniteValeur,
			Integer niveau, Date debut, Date fin, Activite actif) {
		EmploiOperationnel emploi = this.selectEmploiOperationnelByCodeAndLevel(codeUniteValeur, niveau);
		Query query = em.createNamedQuery("CompetenceAgent.selectByAgentAndEmploi");
		query.setParameter("idfAgent", idfAgent);
		query.setParameter("idfEmploi", emploi.getIdEmploi());
		List<CompetenceAgent> lstCompetence = query.getResultList();		
		CompetenceAgent aCompetence;
		if  (lstCompetence.isEmpty()) {
			Agent agent = aDaoEffectif.select(idfAgent);
			if (agent == null)
				return;
			aCompetence = new CompetenceAgent();
			aCompetence.setAgent(agent);
			aCompetence.setEmploi(emploi);
			aCompetence.setDteDebut(debut);
			aCompetence.setDteFin(fin);
			aCompetence.setActivite(actif);
			em.persist(aCompetence);
		}
		else {
			for (CompetenceAgent iterateCompetence : lstCompetence) {
				if (iterateCompetence.getDteDebut().equals(debut)) {
					iterateCompetence.setActivite(actif);
					iterateCompetence.setDteFin(fin);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#saveCompetence(fr.firesoft.fireTime.helper.CompetenceAgentHelper)
	 */
	@Override
	public void saveCompetence(CompetenceAgentHelper helper) {
		CompetenceAgent competence = null;
		if (helper.getIdfCompetenceAgent() == null)
			competence = new CompetenceAgent();
		else
			competence = em.find(CompetenceAgent.class, helper.getIdfCompetenceAgent());
		if (competence.getAgent() == null ) {
			Agent agent = aDaoEffectif.select(helper.getIdfAgent());
			competence.setAgent(agent);
			competence.setActivite(Activite.ACTIF);
		}
		competence.setDteDebut(helper.getDebut());
		competence.setDteFin(helper.getFin());
		EmploiOperationnel emploi = this.selectEmploi(helper.getIdfEmploiOpe());
		competence.setEmploi(emploi);
		if (competence.getIdfCompetenceAgent() == null)
			em.persist(competence);
	}

	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#selectCompetence(java.lang.Integer)
	 */
	@Override
	public CompetenceAgentHelper selectCompetence(Integer idfCompetence) {
		CompetenceAgent competence = em.find(CompetenceAgent.class, idfCompetence);
		if (competence == null)
			return null;
		CompetenceAgentHelper helper = new CompetenceAgentHelper();
		helper.setIdfCompetenceAgent(idfCompetence);
		helper.setDebut(competence.getDteDebut());
		helper.setFin(competence.getDteFin());
		helper.setIdfAgent(competence.getAgent().getIdfAgent());
		helper.setIdfEmploiOpe(competence.getEmploi().getIdEmploi());
		return helper;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#deleteCompetence(java.lang.Integer)
	 */
	@Override
	public void deleteCompetence(Integer idfCompetence) {
		CompetenceAgent competence = em.find(CompetenceAgent.class, idfCompetence);
		if (competence == null)
			return ;
		em.remove(competence);
		em.flush();
	}

	@Override
	public void saveEmploi(String code, String libelle, Integer niveau, CategorieEmploi categorie) {
		Query query = em.createNamedQuery("UniteValeur.selectByCode");
		query.setParameter("code", code);
		UniteValeur uniteValeur = ( UniteValeur ) query.getSingleResult();		
		EmploiOperationnel anEmploi = 
			this.selectEmploiOperationnelByCodeAndLevel(code, niveau);
		if (anEmploi == null) {
			anEmploi = new EmploiOperationnel();
			anEmploi.setUniteValeur(uniteValeur);
			anEmploi.setNiveau(niveau);
		}
		anEmploi.setLibelle(libelle);
		anEmploi.setCategorie(categorie);
		if (anEmploi.getIdEmploi() == null)
			em.persist(anEmploi);
		else
			em.merge(anEmploi);		
	}
	
	private EmploiOperationnel selectEmploiOperationnelByCodeAndLevel(String code, Integer niveau) {
		try {
			Query query = em.createNamedQuery("EmploiOperationnel.selectByCodeAndLevel");
			query.setParameter("code", code);
			query.setParameter("level", niveau);
			return (EmploiOperationnel) query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EmploiOperationnel> makeListEmploiOperationel() {
		Query query = em.createNamedQuery("EmploiOperationnel.makeListOperationnel");
		List<EmploiOperationnel> lstEmploi = query.getResultList();
		Collections.sort(lstEmploi, new EmploiOperationnelComparator());
		return lstEmploi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlageHoraire> makeListForEchelon(Integer idfEchelon) {
		Query query = em.createNamedQuery("PlageHoraire.makeListForEchelon");
		query.setParameter("anIdfEchelon", idfEchelon);
		return query.getResultList();
	}

	/*
	 * Gestion des unités de valeur
	 */

	@Override
	public void saveUniteValeur(String code, String libelle) {
		UniteValeur unite = this.selectUniteValeurByCode(code);
		if (unite == null) {
			unite = new UniteValeur();
			unite.setCode(code);
			unite.setLibelle(libelle);
			em.persist(unite);
		}
		else {
			unite.setLibelle(libelle);
		}
	}
	
	private UniteValeur selectUniteValeurByCode(String code) {
		try {
			Query query = em.createNamedQuery("UniteValeur.selectByCode");
			query.setParameter("code", code);
			UniteValeur existingUV = (UniteValeur) query.getSingleResult();
			return existingUV;
		}
		catch (NoResultException e) {
			return null;
		}		
		
	}

	@Override
	public EmploiOperationnel selectEmploi(Integer idfEmploi) {
		EmploiOperationnel emploi = em.find(EmploiOperationnel.class, idfEmploi);
		return emploi;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#makeListForAgent(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CompetenceAgent> makeListForAgent(Integer idfAgent) {
		Query query = em.createNamedQuery("CompetenceAgent.selectByAgent");
		query.setParameter("idfAgent", idfAgent);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.CompetenceManagerLocal#makeListHelperForAgent(java.lang.Integer)
	 */
	@Override
	public List<CompetenceAgentHelper> makeListHelperForAgent(Integer idfAgent) {
		return HelperFactory.makeListCompetence(this.makeListForAgent(idfAgent));
	}

	/**
	 * @author xbeaufils
	 *
	 */
	public class EmploiOperationnelComparator implements Comparator<EmploiOperationnel> {

		@Override
		public int compare(EmploiOperationnel arg0, EmploiOperationnel arg1) {
			return arg0.getLibelle().compareTo(arg1.getLibelle());
		}

	}

}
