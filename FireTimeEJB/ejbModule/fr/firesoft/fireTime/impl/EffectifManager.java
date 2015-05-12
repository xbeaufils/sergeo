/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.io.ByteArrayInputStream;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.dom4j.Dom4jXPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.bean.AgentDaoLocal;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.entity.CodeEmploiOpePk;
import fr.firesoft.fireTime.entity.CodeEmploiOperationnel;
import fr.firesoft.fireTime.entity.EchelonOrganigramme;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.effectif.Affectation;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.effectif.CodeGrade;
import fr.firesoft.fireTime.entity.effectif.CodeGradePK;
import fr.firesoft.fireTime.entity.effectif.Famille;
import fr.firesoft.fireTime.entity.effectif.Filiere;
import fr.firesoft.fireTime.entity.effectif.Grade;
import fr.firesoft.fireTime.entity.effectif.Groupe;
import fr.firesoft.fireTime.exception.AgentNotFoundException;
import fr.firesoft.fireTime.exception.GradeNotFoundException;
import fr.firesoft.fireTime.exception.OrganigrammeNotFoundException;
import fr.firesoft.fireTime.helper.AffectationViewHelper;
import fr.firesoft.fireTime.loader.TypeImport;
import fr.firesoft.fireTime.transfert.Situation;
import fr.firesoft.fireTime.ws.EffectifManagerWs;
import fr.firesoft.fireTime.ws.PasserelleException;
import fr.firesoft.portal.organigramme.ServiceLocatorOrag;
import fr.firesoft.portal.organigramme.bean.OrganigrammeRemote;
import fr.firesoft.portal.organigramme.helper.Organization;

/**
 * @author xbeaufils
 *
 */
@Local (EffectifManagerLocal.class)
@Remote(EffectifManagerWs.class)
@Stateless
@WebService(endpointInterface = "fr.firesoft.fireTime.ws.EffectifManagerWs" ,
		targetNamespace="http://ws.fireTime.firesoft.fr",
		serviceName="Passerelle")
public class EffectifManager implements EffectifManagerLocal, EffectifManagerWs {
	   @PersistenceContext(unitName = "FireTime")
	    private EntityManager em;

	   @EJB
	   		EffectifDaoLocal aDaoEffectif;
	   @EJB
	   		AgentDaoLocal aDaoAgent;
	   
	private AuthentificationLocal authManager;
   	private SimpleDateFormat xmlFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static Logger log = LoggerFactory.getLogger(EffectifManager.class.getName());
	/**
	 * 
	 */
	public EffectifManager() {
		//authManager = ServiceLocatorFireTime.getAuthentificationBean();
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ws.EffectifManagerWs#transfert(java.lang.String)
	 */
	@Override
	public void transfert(String message) 
		throws AgentNotFoundException, OrganigrammeNotFoundException, GradeNotFoundException{
		try {
	        SAXReader aReader = new SAXReader();
	        Document document = aReader.read(new ByteArrayInputStream(message.getBytes()));
	        Element root = document.getRootElement();
	        log.debug( "QName is " + root.getQName().getName());
	        TypeImport type = TypeImport.getValueByEntete( root.getQName().getName().toUpperCase());
	        switch (  type ) {
			//case COMPETENCE:	
				// this.processCompetence(document);
			//	break;
			case AGENT :
				this.processAgent(document);
				break;
/*			case ORGANIGRAMME : 
				this.processOrganigramme(document);
				break;
*/			case SITUATION :
				this.processAffectation(document);
				break;
/*			case GRADE:
				this.processGrade(document);
				break;
*/			default:
				break;
			}
		}
		catch (DocumentException e) {
			log.error("ERREUR", e);
		}
	// TODO Gérer le message XML
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#save(fr.firesoft.fireTime.entity.Agent)
	 */
	@Override
	public void save(Agent agent) {
		if (agent.getIdfAgent() == null)
			em.persist(agent);
		else {
			Agent anAgent = em.find(Agent.class, agent.getIdfAgent());
			if (anAgent == null)
				em.persist(agent);
			else {
				anAgent.setMatricule(agent.getMatricule());
				anAgent.setNom(agent.getNom());
				anAgent.setPrenom(agent.getPrenom());
				anAgent.setDateNaissance(agent.getDateNaissance());
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#saveAffectation(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public void saveAffectation(Integer matricule, String orga, String grade,
			String fonction, Date debut, Date fin)
			throws AgentNotFoundException, OrganigrammeNotFoundException, GradeNotFoundException  {
		OrganigrammeRemote orgaDao = ServiceLocatorOrag.getOrganigrammeBean();
		Organization organization = orgaDao.getUppestOrganizationJson(Long.parseLong(orga), authManager.getToken(), authManager.getCookies());
		//EchelonOrganigramme echelon = em.find(EchelonOrganigramme.class, Integer.parseInt(orga)); // this.searchEchelonByCode(orga);
		//if (echelon == null)
		//	throw new OrganigrammeNotFoundException();
		Grade aGrade = this.selectGradeByCode(grade);
		Agent anAgent = aDaoEffectif.selectByMatricule(matricule, organization.getOrganizationId().intValue());
		Affectation affectation = this.selectSame(anAgent.getIdfAgent(), Integer.parseInt(orga), debut);
		if (affectation == null) {
			affectation = new Affectation();
			affectation.setAgent(anAgent);
			affectation.setDebut(debut);
			affectation.setIdfEchelon(Integer.parseInt(orga));
			affectation.setGrade(aGrade);
			affectation.setFin(fin);
			em.persist(affectation);
		}else {
			affectation.setFin(fin);
			affectation.setIdfEchelon(Integer.parseInt(orga));
			affectation.setGrade(aGrade);
			affectation.setFin(fin);
		// affectation.setFonction(fonction)
		}
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#saveAffectation(fr.firesoft.fireTime.entity.effectif.Affectation)
	 */
	@Override
	public void saveAffectation(Affectation anAffectation) {
		if (anAffectation.getIdfAffectation() == null)
			em.persist(anAffectation);
		else
			em.merge(anAffectation);
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#saveAffectation(fr.firesoft.fireTime.helper.AffectationViewHelper)
	 */
	@Override
	public void saveAffectation(AffectationViewHelper anAffectation)	
		throws ParseException {
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		Affectation affectation = null; 
		if (anAffectation.getIdfAffectation() == null ) {
			affectation = new Affectation();
			Agent anAgent = aDaoEffectif.select(anAffectation.getIdfAgent());
			affectation.setAgent(anAgent);
		}
		else {
			affectation = em.find(Affectation.class, anAffectation.getIdfAffectation());
		}
		affectation.setDebut(sdFormat.parse(anAffectation.getDebut()));
		if (anAffectation.getFin() != null) 
			affectation.setFin(sdFormat.parse(anAffectation.getFin()));
		else
			affectation.setFin(null);
		Grade aGrade  = em.find(Grade.class, anAffectation.getIdfGrade());
		affectation.setGrade(aGrade);
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#deleteAffectation(java.lang.Integer)
	 */
	@Override
	public void deleteAffectation(Integer idfAffectation) {
		Affectation deletedAffectation = em.find(Affectation.class, idfAffectation);
		em.remove(deletedAffectation);
		
	}

	private void processAffectation(Document document) 
		throws AgentNotFoundException, OrganigrammeNotFoundException, GradeNotFoundException {
		try {
	        // Recherche de l'ID
	        Dom4jXPath searchIdfAgent = new Dom4jXPath("/affectation/matricule" );
	        Element elemIdfAgent = (Element)searchIdfAgent.selectSingleNode(document);
	        Integer idfAgent =  Integer.parseInt((String) elemIdfAgent.getData());
	        // Recherche du libellé
	        Dom4jXPath searchOrga = new Dom4jXPath("/affectation/organigramme");
	        Element elemOrga = (Element)searchOrga.selectSingleNode(document);
	        String orga =  (String) elemOrga.getData();
	        // Recherche du grade
	        Dom4jXPath searchGrade = new Dom4jXPath("/affectation/grade");
	        Element elemGrade = (Element)searchGrade.selectSingleNode(document);
	        String grade = elemGrade != null ? (String) elemGrade.getData() : null;
	        // Recherche de la fonction
	        Dom4jXPath searchFct = new Dom4jXPath("/affectation/fonction");
	        Element elemFct = (Element)searchFct.selectSingleNode(document);
	        String fonction = elemFct != null? (String) elemFct.getData() : null;
	        // Recherche du debut
	        Dom4jXPath searchDebut = new Dom4jXPath("/affectation/debut");
	        Element elemDebut= (Element)searchDebut.selectSingleNode(document);
	        Date debut = xmlFormat.parse((String) elemDebut.getData() );
	        // Recherche de fin
	        Dom4jXPath searchFin = new Dom4jXPath("/affectation/fin");
	        Element elemFin= (Element)searchFin.selectSingleNode(document);
	        Date fin = null;
	        if (elemFin != null)
	        	fin = xmlFormat.parse((String) elemFin.getData() );
	        this.saveAffectation(idfAgent, orga, grade, fonction, debut, fin);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}
		catch (ParseException e) {
			log.error("ERREUR", e);			
		}

	}
	
	private void processAgent(Document document) {
		try {
	        // Recherche de l'ID
	        Dom4jXPath searchMatricule = new Dom4jXPath("/agent/matricule");
	        Element elemMatricule = (Element)searchMatricule.selectSingleNode(document);
	        Integer matricule = Integer.parseInt((String) elemMatricule.getData());
	        // Recherche du nom
	        Dom4jXPath searchNom = new Dom4jXPath("/agent/nom");
	        Element elemNom = (Element)searchNom.selectSingleNode(document);
	        String nom =  (String) elemNom.getData();
	        // Recherche du prenom
	        Dom4jXPath searchPrenom = new Dom4jXPath("/agent/prenom");
	        Element elemPrenom = (Element)searchPrenom.selectSingleNode(document);
	        String prenom =  (String) elemPrenom.getData();
	        // Recherche du prenom
	        Dom4jXPath searchOrga = new Dom4jXPath("/agent/organigramme");
	        Element elemOrga = (Element)searchOrga.selectSingleNode(document);
	        Long orgaId =  Long.parseLong( (String) elemOrga.getData() );
	        OrganigrammeRemote orgaRemote = ServiceLocatorOrag.getOrganigrammeBean();
	        Organization orga = orgaRemote.getUppestOrganizationJson(orgaId, authManager.getToken(), authManager.getCookies());
	        // Recherche de la date de naissance
	        Dom4jXPath searchDateNaiss = new Dom4jXPath("/agent/date_naissance");
	        Element elemDteNaiss = (Element)searchDateNaiss.selectSingleNode(document);
	        Date naissance = xmlFormat.parse((String) elemDteNaiss.getData() );
	        Agent anAgent = new Agent();
	        anAgent.setMatricule(matricule);
	        anAgent.setNom(nom);
	        anAgent.setPrenom(prenom);
	        anAgent.setOrganisation(orga.getOrganizationId().intValue());
	        anAgent.setDateNaissance(naissance);
	        this.save(anAgent);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void processGrade(Document document) {
		try {
	        // Recherche de l'ID
	        Dom4jXPath searchCodeGrade = new Dom4jXPath("/grade/code_grade");
	        Element elemCodeGrade = (Element)searchCodeGrade.selectSingleNode(document);
	        String codeGrade = (String) elemCodeGrade.getData();
	        // Recherche du nom
	        Dom4jXPath searchNom = new Dom4jXPath("/grade/libelle");
	        Element elemNom = (Element)searchNom.selectSingleNode(document);
	        String libelle =  (String) elemNom.getData();
	        // Recherche du prenom
	        this.saveGrade(codeGrade, libelle, null, null);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}

	}
	private void processOrganigramme(Document document) {
		try {
	        // Recherche du libellé
	        Dom4jXPath searchLibelle = new Dom4jXPath("/echelon/libelle");
	        Element elemLibelle = (Element)searchLibelle.selectSingleNode(document);
	        String libelle =  (String) elemLibelle.getData();
	        // Recherche du code
	        Dom4jXPath searchCode = new Dom4jXPath("/echelon/code");
	        Element elemCode = (Element)searchCode.selectSingleNode(document);
	        String code =  (String) elemCode.getData();
	        // Recherche du code sup
	        String codeSup = code.length()>2? code.substring(0, code.length() - 2):null;
	        this.add(code, libelle, codeSup);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}

	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.ws.EffectifManagerWs#transfertCarriere(fr.firesoft.fireTime.transfert.Situation)
	 */
	@Override
	public void transfertCarriere(Situation arg0) throws RemoteException,
			PasserelleException, AgentNotFoundException, OrganigrammeNotFoundException, GradeNotFoundException{
		Grade aGrade = this.selectGradeByCode(arg0.getGrade());
		Affectation affectation = this.selectSame(arg0.getIdfAgent(), Integer.parseInt(arg0.getOrga()), arg0.getDateDebut());
		if (affectation == null) {
			affectation = new Affectation();
			Agent anAgent = aDaoEffectif.select(arg0.getIdfAgent());
			if (anAgent == null) {
				log.warn("Agent non trouvé " + arg0.getIdfAgent());
				return;
			}
			affectation.setAgent(anAgent);
			affectation.setDebut(arg0.getDateDebut());
			affectation.setIdfEchelon(Integer.parseInt(arg0.getOrga()));
			affectation.setGrade(aGrade);
			affectation.setFin(arg0.getDateFin());
			em.persist(affectation);
		}else {
			affectation.setFin(arg0.getDateFin());
			affectation.setIdfEchelon(Integer.parseInt(arg0.getOrga()));
			affectation.setGrade(aGrade);
		// affectation.setFonction(fonction)
		}
	
	}

	private Affectation selectSame(Integer idfAgent, Integer idfEchelon, Date aDate) {
		try {
			Query query = em.createNamedQuery("Affectation.selectSame");
			query.setParameter("idfAgent", idfAgent);
			query.setParameter("anIdfEchelon", idfEchelon);
			query.setParameter("debut", aDate);
			return (Affectation) query.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AdminLocal#add(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void add(String code, String libelle, String codeSup) {
		log.debug("Echelon code " + code +" libelle " + libelle + " sup " + codeSup );
		EchelonOrganigramme existingEchelon = this.searchEchelonByCode(code);
		if (existingEchelon == null ) {
			existingEchelon = new EchelonOrganigramme();
			existingEchelon.setCode(code);
			existingEchelon.setLibelle(libelle);
			em.persist(existingEchelon);
		}
		this.add(existingEchelon, codeSup);
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AdminLocal#save(fr.firesoft.fireTime.entity.EchelonOrganigramme)
	 */
	@Override
	public void save(EchelonOrganigramme echelon) {
		if (echelon.getIdfEchelon() == null)
			em.persist(echelon);
		else
			em.merge(echelon);
	}


	public EchelonOrganigramme searchEchelonByCode(String code) {
		try {
			Query query = em.createNamedQuery("EchelonOrganigramme.selectByCode");
			query.setParameter("aCode", code);
			EchelonOrganigramme existingEchelon = (EchelonOrganigramme) query.getSingleResult();
			return existingEchelon;
		}
		catch (NoResultException e) {
			return null;
		}
		
	}
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AdminLocal#add(fr.firesoft.fireTime.entity.EchelonOrganigramme, java.lang.String)
	 */
	@Override
	public void add(EchelonOrganigramme echelon, String codeSup) {
		if (codeSup == null )
			return;
		Query query = em.createNamedQuery("EchelonOrganigramme.selectByCode");
		query.setParameter("aCode", codeSup);
		EchelonOrganigramme anEchelonSup = (EchelonOrganigramme) query.getSingleResult();
		// TODO Et si l'echelon supérieur n'existe pas ?
		echelon.setSuperieur(anEchelonSup);
	} 

	@Override
	public EchelonOrganigramme selectEchelon(Integer idfEchelon) {
		EchelonOrganigramme anEchelon = em.find(EchelonOrganigramme.class, idfEchelon);
		return anEchelon;
	}

	/*
	 * Gestion des grades
	 */

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#saveGrade(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveGrade(String code, String libelle, String groupe, String filiere) {
		try {			
			Grade aGrade = this.selectGradeByCode(code);
			if (groupe != null)
				aGrade.setGroupe(Groupe.valueOf(groupe) );
			aGrade.setLibelle(libelle);
			aGrade.setFiliere(Filiere.valueOf(filiere));
		}
		catch (GradeNotFoundException e) {
			Grade aGrade = new Grade();
			if (groupe != null)
				aGrade.setGroupe(Groupe.valueOf(groupe) );
			aGrade.setLibelle(libelle);
			//aGrade.setCode(code);
			aGrade.setFiliere(Filiere.valueOf(filiere));
			em.persist(aGrade);
		}
	}

	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#saveGrade(java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void saveGrade(Integer idfGrade, String code,
			Integer idfEchelon) {
		//EchelonOrganigramme echelon = selectEchelon(idfEchelon);
		Grade grade = em.find(Grade.class, idfGrade);
		CodeGradePK codePk = new CodeGradePK();
		codePk.setGrade(grade);
		codePk.setOrganisation(idfEchelon);
		CodeGrade codeGrade = em.find(CodeGrade.class, codePk);
		boolean existant = true;
		if (codeGrade == null) {
			codeGrade = new CodeGrade();
			codeGrade.setId(codePk);
			existant = false;
		}
		codeGrade.setCode(code);
		if ( ! existant )
			em.persist(codeGrade);
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#checkGrade()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void checkGrade() {
		Query query = em.createNamedQuery("Grade.selectAll");
		List<Grade> lstGrade = query.getResultList();
		if (lstGrade.size() > 0)
			return;
		Grade aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.SAP);
		aGrade.setLibelle("sapeur 2ème classe");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.SAP);
		aGrade.setLibelle("sapeur 2ème classe");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.SAP);
		aGrade.setLibelle("sapeur 1ère classe");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.SAP);
		aGrade.setLibelle("sapeur 1ère classe");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.CAP);
		aGrade.setLibelle("Caporal");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.CAP);
		aGrade.setLibelle("Caporal");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.CAP);
		aGrade.setLibelle("Caporal-chef");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.CAP);
		aGrade.setLibelle("Caporal-chef");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Sergent");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Sergent");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Sergent-chef");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Sergent-chef");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Adjudant");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Adjudant");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Adjudant-chef");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.SOF);
		aGrade.setLibelle("Adjudant-chef");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.OFF);
		aGrade.setLibelle("lieutenant 1ère classe");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.OFF);
		aGrade.setLibelle("Lieutenant");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPP);
		aGrade.setGroupe(Groupe.OFF);
		aGrade.setLibelle("Capitaine");
		em.persist(aGrade);
		aGrade = new Grade();
		aGrade.setFamille(Famille.SP);
		aGrade.setFiliere(Filiere.SPV);
		aGrade.setGroupe(Groupe.OFF);
		aGrade.setLibelle("Capitaine");
		em.persist(aGrade);
	}

	private Grade selectGradeByCode(String code) throws GradeNotFoundException {
		try {
			Query query = em.createNamedQuery("CodeGrade.selectByCode");
			query.setParameter("aCode", code);
			Grade existingEchelon = (Grade) query.getSingleResult();
			return existingEchelon;
		}
		catch (NoResultException e) {
			throw new GradeNotFoundException();
		}		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#checkEmploiOperationnel()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void checkEmploiOperationnel() {
		Query query = em.createNamedQuery("EmploiOperationnel.makeListAll");
		List<EmploiOperationnel> lstemploiOpe = query.getResultList();
		if (lstemploiOpe.size() > 0)
			return;
		// ------- Module Incendie ------------
		EmploiOperationnel emploiPere = this.createEmploi("Chef de site", null);
		emploiPere = this.createEmploi("Chef de colonne", emploiPere);
		emploiPere = this.createEmploi("Chef de groupe", emploiPere);
		emploiPere = this.createEmploi("Chef d'agrès", emploiPere);
		emploiPere = this.createEmploi("Chef d'équipe", emploiPere);
		emploiPere = this.createEmploi("Equipier", emploiPere);
		emploiPere = this.createEmploi("Equipier INC", emploiPere);
		// ---- Module VSAV ----------
		emploiPere = this.createEmploi("Chef d'agrès VSAV", null);
		emploiPere = this.createEmploi("Chef d'équipe VSAV", emploiPere);
		emploiPere = this.createEmploi("Equipier VSAV", emploiPere);
		emploiPere = this.createEmploi("Conducteur VSAV", null);
		// ------ Module DIV --------
		emploiPere = this.createEmploi("Chef d'agrès VTU", null);
		emploiPere = this.createEmploi("Chef d'equipe VTU", emploiPere);
		emploiPere = this.createEmploi("Equipier  VTU", emploiPere);
		// ---------- Conducteur ----------
		this.createEmploi("Qualification secours routier CFAPSR", null);
		this.createEmploi("Conducteur VL", null);
		this.createEmploi("Conducteur PL", null);
		this.createEmploi("Conducteur tout terrain VL (CO2 VL)", null);
		this.createEmploi("Conducteur tout-terrain (COD2) PL", null);
		this.createEmploi("Conducteur engin pompe (COD1) VL", null);
		this.createEmploi("Conducteur engin-pompe (COD1) PL", null);
		this.createEmploi("Conducteur échelier", null);
		this.createEmploi("Conducteur embarcation (COD4)", null);
		this.createEmploi("Conducteur VSM", null);
		// --------- Module FDF ----------
		emploiPere = this.createEmploi("chef de site feux de forêts (FDF5)", null);
		emploiPere = this.createEmploi("Chef de colonne feux de forêts (FDF4)", emploiPere);
		emploiPere = this.createEmploi("chef de groupe feux de forêts (FDF3)", emploiPere);
		emploiPere = this.createEmploi("Chef d'agrès feux de forêts (FDF2)", emploiPere);
		emploiPere = this.createEmploi("Equipier feu de forêts (FDF1)", emploiPere);	
		// ---- Module Plongee ------------
		emploiPere = this.createEmploi("Conseiller technique plongée (SAL)", null);
		emploiPere = this.createEmploi("Chef d'unité plongée (SAL)", emploiPere);
		emploiPere = this.createEmploi("Scaphandrier autonome léger", emploiPere);

		emploiPere = this.createEmploi("Conseiller technique SAL surface non libre", null);
		emploiPere = this.createEmploi("Chef d'unité SAL surface non libre", emploiPere);
		emploiPere = this.createEmploi("Scaphandrier autonome léger surface non libre", emploiPere);
		
		emploiPere = this.createEmploi("Conseiller technique SAL secours en milieux aquatiques actifs et accidentés", null);
		emploiPere = this.createEmploi("chef d'unité SAL secours en milieux aquatiques actifs et accidentés", emploiPere);
		emploiPere = this.createEmploi("Scaphandrier autonome léger secours en milieux aquatiques actifs et accidentés", emploiPere);
		
	}

	private EmploiOperationnel createEmploi(String libelle, EmploiOperationnel pere) {
		EmploiOperationnel emploi = new EmploiOperationnel();
		emploi.setLibelle(libelle);
		emploi.setPere(pere);
		em.persist(emploi);
		return emploi;
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#saveEmploiOperationnel(java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void saveEmploiOperationnel(Integer idfEmploi, String code,
			Integer idfEchelon) {
		//EchelonOrganigramme echelon = selectEchelon(idfEchelon);
		EmploiOperationnel emploi = em.find(EmploiOperationnel.class, idfEmploi);
		CodeEmploiOpePk codePk = new CodeEmploiOpePk();
		codePk.setEmploi(emploi);
		codePk.setOrganisation(idfEchelon);
		CodeEmploiOperationnel codeEmploiOperationnel = em.find(CodeEmploiOperationnel.class, codePk);
		boolean existant = true;
		if (codeEmploiOperationnel == null) {
			codeEmploiOperationnel = new CodeEmploiOperationnel();
			codeEmploiOperationnel.setId(codePk);
			existant = false;
		}
		codeEmploiOperationnel.setCode(code);
		if ( ! existant )
			em.persist(codeEmploiOperationnel);
		
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.EffectifManagerLocal#makeListByOrganisation(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EchelonOrganigramme> makeListByOrganisation(
			String organisationName) {
		Query query = em.createNamedQuery("EchelonOrganigramme.makeListByOrganisation");
		query.setParameter("orgaName", organisationName);
		return query.getResultList();
	}

	@Override
	public void setAuthentificationCache(AuthentificationLocal auth) {
		this.authManager = auth;
		
	}

	
}
