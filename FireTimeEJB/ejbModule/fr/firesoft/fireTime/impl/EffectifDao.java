/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.entity.CodeEmploiOperationnel;
import fr.firesoft.fireTime.entity.EchelonOrganigramme;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.effectif.Affectation;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.effectif.CodeGrade;
import fr.firesoft.fireTime.entity.effectif.Filiere;
import fr.firesoft.fireTime.entity.effectif.Grade;
import fr.firesoft.fireTime.ext.bean.EffectifDaoRemote;
import fr.firesoft.fireTime.ext.exception.AgentNotFoundException;
import fr.firesoft.fireTime.factory.EffectifHelperFactory;
import fr.firesoft.fireTime.factory.HelperFactory;
import fr.firesoft.fireTime.helper.AffectationViewHelper;
import fr.firesoft.fireTime.helper.AgentHelper;
import fr.firesoft.fireTime.helper.EmploiOperationnelHelper;
import fr.firesoft.fireTime.helper.GradeHelper;

/**
 * @author xbeaufils
 *
 */
/**
 * @author xbeaufils
 *
 */
@Local(EffectifDaoLocal.class)
@Remote (EffectifDaoRemote.class)
@Stateless
public class EffectifDao implements EffectifDaoLocal, EffectifDaoRemote {

	@PersistenceContext(unitName = "FireTime")
	    private EntityManager em;

	public static Logger log = LoggerFactory.getLogger(EffectifDao.class.getName());

	
	private	AuthentificationLocal authManager;
	  
	/**
	 * 
	 */
	public EffectifDao() {
		authManager = ServiceLocatorFireTime.getAuthentificationBean();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#selectForDateAndEchelon(java.lang.Integer, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AgentHelper> selectForDateAndEchelon(Integer idfEchelon, Date day) {
		Query query = em.createNamedQuery("Affectation.selectForDayAndEchelon");
		query.setParameter("anIdfEchelon", idfEchelon);
		query.setParameter("dateDay", day);
		return EffectifHelperFactory.make( query.getResultList() );
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#select(java.lang.Integer)
	 */
	@Override
	public Agent select(Integer idfAgent) {
		Agent anAgent = em.find(Agent.class, idfAgent);
		return anAgent;
	}
	
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#selectByMatricule(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Agent selectByMatricule(Integer matricule, Integer idfEchelon) {
		Query query = em.createNamedQuery("Agent.selectByMatriculeAndOrga");
		query.setParameter("aMatricule", matricule);
		query.setParameter("idfEchelon", idfEchelon);		
		List<Agent> lstAgent =  query.getResultList();
		if (! lstAgent.isEmpty())
			return lstAgent.get(0);
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#selectForIntervalleAndEchelon(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AgentHelper> selectForIntervalleAndEchelon(Integer idfEchelon,
			Date day, int intervalle) {
		Calendar calDebut = this.getInferiorLimit(day, intervalle);
		log.debug("debut {}", calDebut.getTime() );
		log.debug("Echelon {}", idfEchelon);
		Query query = em.createNamedQuery("Affectation.selectForDayAndEchelon");
		query.setParameter("anIdfEchelon", idfEchelon);
		query.setParameter("dateDay", calDebut.getTime());
		return  EffectifHelperFactory.make( query.getResultList() );
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#selectForIntervalleAndEchelon(java.lang.Integer, java.util.Date, int, fr.firesoft.fireTime.entity.Filiere)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AgentHelper> selectForIntervalleAndEchelon(Integer idfEchelon,
			Date day, int intervalle, List<Filiere> lstFiliere) {
		Calendar calDebut = this.getInferiorLimit(day, intervalle);
		if (lstFiliere == null)
			return new ArrayList<AgentHelper>();
		if (lstFiliere.isEmpty())
			return new ArrayList<AgentHelper>();
		log.debug("debut {}", calDebut.getTime() );
		log.debug("Echelon {}", idfEchelon);
		Query query = em.createNamedQuery("Affectation.selectForDayAndEchelonAndFiliere");
		query.setParameter("anIdfEchelon", idfEchelon);
		query.setParameter("dateDay", calDebut.getTime());
		query.setParameter("filiere", lstFiliere);
		List<AgentHelper> lstHelper = EffectifHelperFactory.make( query.getResultList() );
		Collections.sort(lstHelper, new AgentComparator());
		return lstHelper;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#selectForAgentInDayAndEchelon(java.lang.Integer, java.lang.Integer, java.util.Date, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AgentHelper> selectForAgentInDayAndEchelon(
			Integer idfAgent, Integer idfEchelon, Date day, int intervalle) {
		Calendar calDebut = this.getInferiorLimit(day, intervalle);
		log.debug("Agent {} day {} echelon {}", new Object[]{idfAgent, calDebut.getTime(),  idfEchelon});
		Query query = em.createNamedQuery("Affectation.selectForAgentAndDayAndEchelon");
		query.setParameter("anIdfEchelon", idfEchelon);
		query.setParameter("day", calDebut.getTime());
		query.setParameter("idfAgent", idfAgent);
		return  EffectifHelperFactory.make( query.getResultList() );
	}

	private Calendar getInferiorLimit(Date day, int intervalle) {
		Calendar calDebut = Calendar.getInstance();
		calDebut.setTime(day);
		switch (intervalle) {
		case Calendar.MONTH:
			calDebut.set(Calendar.DATE, 1);
			calDebut.set(Calendar.HOUR_OF_DAY, 0);
			calDebut.set(Calendar.MINUTE, 0);
			calDebut.set(Calendar.SECOND, 0);
			calDebut.set(Calendar.MILLISECOND, 0);
			break;
		case Calendar.YEAR :
			calDebut.set(Calendar.DATE, 1);
			calDebut.set(Calendar.MONTH, 0);
			calDebut.set(Calendar.HOUR_OF_DAY, 0);
			calDebut.set(Calendar.MINUTE, 0);
			calDebut.set(Calendar.SECOND, 0);
			calDebut.set(Calendar.MILLISECOND, 0);		
			break;
		default:
		}
		return calDebut;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#makeListForAgent(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Affectation> makeListForAgent(Integer idfAgent) {
		Query query = em.createNamedQuery("Affectation.selectForAgent");
		query.setParameter("idfAgent", idfAgent);
		return query.getResultList();
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#makeListViewForAgent(java.lang.Integer)
	 */
	@Override
	public List<AffectationViewHelper> makeListViewForAgent(Integer idfAgent) {
		return EffectifHelperFactory.makeListAffectation(this.makeListForAgent(idfAgent));
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#selectAffectation(java.lang.Integer)
	 */
	@Override
	public Affectation selectAffectation(Integer idfAffectation) {
		Affectation anAffectation = em.find(Affectation.class, idfAffectation);
		return anAffectation;
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#selectAffectationHelper(java.lang.Integer)
	 */
	@Override
	public AffectationViewHelper selectAffectationHelper(Integer idfAffectation) {
		Affectation anAffectation = em.find(Affectation.class, idfAffectation);
		return EffectifHelperFactory.makeAffectationHelper(anAffectation);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#makeListGradeForOrganisation(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeHelper> makeListGradeForOrganisation(Integer idfEchelon) {
		EchelonOrganigramme anEchelon = em.find(EchelonOrganigramme.class, idfEchelon);
		Query query = em.createNamedQuery("CodeGrade.makeListGradeForOrga");
		query.setParameter("anOrga", anEchelon.getOrganisation().getNom());
		List<Grade> lstGrade = query.getResultList();
		return HelperFactory.makeListGrade(lstGrade);
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#makeListGradeForOrganization(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeHelper> makeListGradeForOrganization(Integer idOrga) {
		Query query = em.createNamedQuery("CodeGrade.makeListGradeForOrga");
		query.setParameter("anOrga", idOrga);
		List<Grade> lstGrade = query.getResultList();
		return HelperFactory.makeListGrade(lstGrade);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifDaoLocal#makeListGradeForOrganisationWithEmptyCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeHelper> makeListGradeForOrganisationWithEmptyCode(
			Integer  idfEchelon) {
		Query query = em.createNamedQuery("Grade.selectAll");
		List<Grade> lstGrade = query.getResultList();
		log.debug("Nbre found grades={}", lstGrade.size());
		//OrganigrammeRemote orgaDao  = ServiceLocatorOrag.getOrganigrammeBean();
		//Organization organization = orgaDao.getOrganizationJsonById( idfEchelon.longValue(), authManager.getToken(), authManager.getCookies());
		//EchelonOrganigramme anEchelon = em.find(EchelonOrganigramme.class, idfEchelon);
		query = em.createNamedQuery("CodeGrade.makeListForOrga");
		query.setParameter("anOrga", idfEchelon);
		List<CodeGrade> lstCodePresent = query.getResultList();
		List<GradeHelper> lstHelper = new ArrayList<GradeHelper>();
		for (Grade grade : lstGrade) {
			boolean found = false;
			for (CodeGrade code : lstCodePresent) {
				if (code.getId().getGrade().equals(grade)) {
					found = true;
					GradeHelper helper = new GradeHelper();
					helper.setCode(code.getCode());
					helper.setFiliere(grade.getFiliere().toString());
					helper.setId(grade.getId());
					helper.setLibelle(grade.getLibelle());
					lstHelper.add(helper);
				}
			}
			if ( ! found ) {
				GradeHelper helper = new GradeHelper();
				helper.setCode(null);
				helper.setFiliere(grade.getFiliere().toString());
				helper.setId(grade.getId());
				helper.setLibelle(grade.getLibelle());
				lstHelper.add(helper);				
			}
		}
		return lstHelper;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmploiOperationnelHelper> makeListEmploiOpeForOrganisationWithEmptyCode(
			Integer  idfEchelon) {
		Query query = em.createNamedQuery("EmploiOperationnel.makeListAll");
		List<EmploiOperationnel> lstEmploi = query.getResultList();
		//OrganigrammeLocal orgaDao  = ServiceLocatorOrag.getOrganigrammeBean();
		//Organization organization = orgaDao.getOrganizationJsonById( idfEchelon.longValue(), authManager.getToken());
		//EchelonOrganigramme anEchelon = em.find(EchelonOrganigramme.class, idfEchelon);
		query = em.createNamedQuery("CodeEmploiOperationnel.makeListForOrga");
		query.setParameter("anOrga", idfEchelon);
		List<CodeEmploiOperationnel> lstCodePresent = query.getResultList();
		List<EmploiOperationnelHelper> lstHelper = new ArrayList<EmploiOperationnelHelper>();
		for (EmploiOperationnel emploi : lstEmploi) {
			boolean found = false;
			for (CodeEmploiOperationnel code : lstCodePresent) {
				if (code.getId().getEmploi().equals(emploi)) {
					found = true;
					EmploiOperationnelHelper helper = new EmploiOperationnelHelper();
					helper.setCode(code.getCode());
					helper.setIdEmploi(emploi.getIdEmploi());
					helper.setLibelle(emploi.getLibelle());
					lstHelper.add(helper);
				}
			}
			if ( ! found ) {
				EmploiOperationnelHelper helper = new EmploiOperationnelHelper();
				helper.setCode(null);
				helper.setIdEmploi(emploi.getIdEmploi());
				helper.setLibelle(emploi.getLibelle());
				lstHelper.add(helper);				
			}
		}		
		log.debug("Nbre found emplois={}", lstHelper.size());
		return lstHelper;
	}
	
	@SuppressWarnings("unchecked")
	public List<EmploiOperationnelHelper> makeListEmploiOpeForOrganisation(
			Integer  idfEchelon) {
		EchelonOrganigramme anEchelon = em.find(EchelonOrganigramme.class, idfEchelon);
		Query query = em.createNamedQuery("CodeEmploiOperationnel.makeListForOrga");
		query.setParameter("anOrga", anEchelon.getOrganisation().getNom());
		List<CodeEmploiOperationnel> lstCodePresent = query.getResultList();
		log.debug("Nbre found emplois with code={}", lstCodePresent.size());
		return HelperFactory.makeListEmploi(lstCodePresent);
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ext.bean.EffectifDaoRemote#getIdfAgentByMatricule(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Integer getIdfAgentByMatricule(Integer matricule, Integer idfEchelon) 
		throws AgentNotFoundException{
		Agent agent = selectByMatricule(matricule, idfEchelon);
		if (agent == null)
			throw new AgentNotFoundException();
		return agent.getIdfAgent();
	}
	
	/**
	 * @author xbeaufils
	 *
	 */
	public class AgentComparator implements Comparator<AgentHelper> {

		/**
		 * 
		 */
		public AgentComparator() {
		}

		@Override
		public int compare(AgentHelper arg0, AgentHelper arg1) {
			int compar  = arg0.getNom().compareTo(arg1.getNom());
			if (compar != 0)
				return compar;
			return arg0.getPrenom().compareTo(arg1.getPrenom());
		}

	}

	@Override
	public void setAuthentificationCache(AuthentificationLocal auth) {
		this.authManager = auth;
		
	}

	
}
