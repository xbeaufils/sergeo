/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.bean.AgentDaoLocal;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.exception.AgentNotFoundException;
import fr.firesoft.fireTime.factory.HelperFactory;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;

/**
 * @author xbeaufils
 *
 */
@Local (AgentDaoLocal.class)
@Stateless
public class AgentDao implements AgentDaoLocal {


	@PersistenceContext(unitName = "FireTime")
	    private EntityManager em;
	
	public static Logger log = LoggerFactory.getLogger(AgentDao.class.getName());
	/**
	 * 
	 */
	public AgentDao() {
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AgentDaoLocal#makeListAgent(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Agent> makeListAgent(Integer idfOrganisation) {
		Query query = em.createNamedQuery("Agent.makeListForOrganisation");
		List<Agent> lstAgent = query.getResultList();
		Collections.sort(lstAgent, new AgentComparator());
		return lstAgent;
	}

	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AgentDaoLocal#selectByMatricule(java.lang.Integer)
	 */
	@Override
	public Agent selectByMatricule(Integer matricule) throws AgentNotFoundException{
		try {
			Query query = em.createNamedQuery("Agent.selectByMatricule");
			query.setParameter("aMatricule", matricule);
			return (Agent) query.getSingleResult();
		}
		catch (NoResultException e) {
			log.warn("Agent non trouvé " + matricule);
			throw new AgentNotFoundException();
		}
	}


	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AgentDaoLocal#makeListAgentForEmploiAndEchelon(java.util.Date, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CompetenceAgentHelper> makeListAgentForEmploiAndEchelon(Date day,Integer idfEmploi, Integer idfEchelon) {
		Calendar calDebut = Calendar.getInstance();
		calDebut.setTime(day);
		calDebut.set(Calendar.DATE, 1);
		calDebut.set(Calendar.HOUR_OF_DAY, 0);
		calDebut.set(Calendar.MINUTE, 0);
		calDebut.set(Calendar.SECOND, 0);
		calDebut.set(Calendar.MILLISECOND, 0);
		log.debug("debut {}", calDebut.getTime() );
		log.debug("Emploi {}", idfEmploi);
		Query query = em.createNamedQuery("CompetenceAgent.MakeListAgentForEchelonAndEmploi");
		query.setParameter("idfEchelon", idfEchelon);
		query.setParameter("dateDay", calDebut.getTime());
		query.setParameter("idfEmploi", idfEmploi);
		List<CompetenceAgentHelper> lstAgent = HelperFactory.makeListCompetence( query.getResultList() );
		return lstAgent;
	
	}


	/**
	 * @author xbeaufils
	 *
	 */
	public class AgentComparator implements Comparator<Agent> {

		/**
		 * 
		 */
		public AgentComparator() {
		}

		@Override
		public int compare(Agent arg0, Agent arg1) {
			int compar  = arg0.getNom().compareTo(arg1.getNom());
			if (compar != 0)
				return compar;
			return arg0.getPrenom().compareTo(arg1.getPrenom());
		}

	}


}
