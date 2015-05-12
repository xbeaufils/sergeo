package fr.firesoft.fireTime.jms;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.dom4j.Dom4jXPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.entity.Activite;
import fr.firesoft.fireTime.entity.CategorieEmploi;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.exception.AgentNotFoundException;
import fr.firesoft.fireTime.exception.GradeNotFoundException;
import fr.firesoft.fireTime.exception.OrganigrammeNotFoundException;
@MessageDriven(
        activationConfig = {
                        @ActivationConfigProperty(
                                        propertyName="destination",
                                        propertyValue="queue/FireQueue"),
                        @ActivationConfigProperty(
                                        propertyName="destinationType",
                                        propertyValue="javax.jms.Queue")

})

public class FireTimeMessenger implements MessageListener {
    public static Logger log = LoggerFactory.getLogger(FireTimeMessenger.class.getName());
	private SimpleDateFormat xmlFormat = new SimpleDateFormat("yyyy-MM-dd");
 
	@EJB
		EffectifManagerLocal manager;
	
	@Override
	public void onMessage(Message arg0) {
		try {
	        log.debug( "Text is " + ((TextMessage) arg0).getText());
	        manager.transfert(((TextMessage) arg0).getText());
	        SAXReader aReader = new SAXReader();
	        Document document = aReader.read(new ByteArrayInputStream(((TextMessage) arg0).getText().getBytes()));
	        Element root = document.getRootElement();
	        log.debug( "QName is " + root.getQName().getName());
	        EnteteMessage entete = EnteteMessage.valueOf( root.getQName().getName().toUpperCase());
	        switch ( entete ) {
			case COMPETENCE:	
				this.processCompetence(document);
				break;
			case AGENT :
				this.processAgent(document);
				break;
			case ECHELON :
				this.processOrganigramme(document);
				break;
			case AFFECTATION :
				this.processAffectation(document);
				break;
			case GRADE:
				this.processGrade(document);
				break;
			case UV:
				this.processUniteValeur(document);
				break;
			case EMPLOI:
				this.processEmploi(document);
				break;
			default:
				break;
			}
		}
		catch (DocumentException e) {
			log.error("ERREUR", e);
		}
		catch (JMSException e) {
			log.error("ERREUR", e);			
		}
		catch (GradeNotFoundException e) {
			
		}
		catch (OrganigrammeNotFoundException e) {
			
		}
		catch(AgentNotFoundException e) {
			
		}
	}
	
	private void processCompetence(Document document) {
		try {
	        // Recherche du parametre code agent	        
	        Dom4jXPath searchCodagt = new Dom4jXPath("/competence/idfAgent");
	        Element elemCodagt = (Element)searchCodagt.selectSingleNode(document);
	        Integer idfAgent = Integer.parseInt( (String) elemCodagt.getData());
	        // Recherche de l'unité de valeur
	        Dom4jXPath searchCodUV = new Dom4jXPath("/competence/uniteValeur");
	        Element elemCodUv = (Element)searchCodUV.selectSingleNode(document);
	        String codeUniteValeur =  (String) elemCodUv.getData();
	        // Recherche du niveau
	        Dom4jXPath searchNiveau = new Dom4jXPath("/competence/niveau");
	        Element elemNiveau = (Element)searchNiveau.selectSingleNode(document);
	        Integer niveau =  Integer.parseInt((String) elemNiveau.getData());
	        // Recherche du debut
	        Dom4jXPath searchDebut = new Dom4jXPath("/competence/debut");
	        Element elemDebut = (Element)searchDebut.selectSingleNode(document);
	        Date debut = xmlFormat.parse((String) elemDebut.getData() );
	        // Recherche du niveau
	        Dom4jXPath searchFin = new Dom4jXPath("/competence/fin");
	        Element elemFin = (Element)searchFin.selectSingleNode(document);
	        Date fin = null;
	        if (elemFin != null)
	        	fin = xmlFormat.parse((String) elemFin.getData() );
	        Dom4jXPath searchActif = new Dom4jXPath("/competence/actif");
	        Element elemActif = (Element)searchActif.selectSingleNode(document);
	        Activite actif = Activite.getValueFrom((String) elemActif.getData() );
	        
	        CompetenceManagerLocal aManager = ServiceLocatorFireTime.getCompetenceManagerBean();
	        aManager.addCompetenceForAgent(idfAgent, codeUniteValeur, niveau, debut, fin, actif);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}
		catch (ParseException e){
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
	        EffectifManagerLocal aManager = ServiceLocatorFireTime.getEffectifManagerBean(null);
	        aManager.add(code, libelle, codeSup);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}

	}
	private void processAgent(Document document) {
		try {
	        // Recherche de l'ID
	        Dom4jXPath searchIdfAgent = new Dom4jXPath("/agent/idf_agent");
	        Element elemIdfAgent = (Element)searchIdfAgent.selectSingleNode(document);
	        Integer idfAgent =  Integer.parseInt((String) elemIdfAgent.getData());
	        // Recherche du nom
	        Dom4jXPath searchNom = new Dom4jXPath("/agent/nom");
	        Element elemNom = (Element)searchNom.selectSingleNode(document);
	        String nom =  (String) elemNom.getData();
	        // Recherche du prenom
	        Dom4jXPath searchPrenom = new Dom4jXPath("/agent/prenom");
	        Element elemPrenom = (Element)searchPrenom.selectSingleNode(document);
	        String prenom =  (String) elemPrenom.getData();
	        Agent anAgent = new Agent();
	        anAgent.setIdfAgent(idfAgent);
	        anAgent.setNom(nom);
	        anAgent.setPrenom(prenom);
	        EffectifManagerLocal aManager = ServiceLocatorFireTime.getEffectifManagerBean(null);
	        aManager.save(anAgent);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}

	}
	private void processGrade(Document document) {
		try {
	        // Recherche du libellé
	        Dom4jXPath searchLibelle = new Dom4jXPath("/grade/libelle");
	        Element elemLibelle = (Element)searchLibelle.selectSingleNode(document);
	        String libelle =  (String) elemLibelle.getData();
	        // Recherche du code
	        Dom4jXPath searchCode = new Dom4jXPath("/grade/code");
	        Element elemCode = (Element)searchCode.selectSingleNode(document);
	        String code =  (String) elemCode.getData();
	        // Recherche du code sup
	        Dom4jXPath searchGroupe = new Dom4jXPath("/grade/groupe");
	        Element elemGroupe = (Element)searchGroupe.selectSingleNode(document);
	        String groupe = null;
	        if (elemGroupe != null)
	        	groupe =  (String) elemGroupe.getData();
	        Dom4jXPath searchFiliere = new Dom4jXPath("/grade/filiere");
	        Element elemFiliere = (Element)searchFiliere.selectSingleNode(document);
	        String filiere =  (String) elemFiliere.getData();
	        EffectifManagerLocal anAdmin = ServiceLocatorFireTime.getEffectifManagerBean(null);
	        anAdmin.saveGrade(code, libelle, groupe, filiere);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}

	}
	
	private void processUniteValeur(Document document) {
		try {
	        // Recherche du libellé
	        Dom4jXPath searchLibelle = new Dom4jXPath("/UV/libelle");
	        Element elemLibelle = (Element)searchLibelle.selectSingleNode(document);
	        String libelle =  (String) elemLibelle.getData();
	        // Recherche du code
	        Dom4jXPath searchCode = new Dom4jXPath("/UV/code");
	        Element elemCode = (Element)searchCode.selectSingleNode(document);
	        if (elemCode == null)
	        	return;
	        String code =  (String) elemCode.getData();
	        CompetenceManagerLocal anAdmin = ServiceLocatorFireTime.getCompetenceManagerBean();
	        anAdmin.saveUniteValeur(code, libelle);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}

	}
	

	private void processEmploi(Document document) {
		try {
	        // Recherche du libellé
	        Dom4jXPath searchLibelle = new Dom4jXPath("/emploi/libelle");
	        Element elemLibelle = (Element)searchLibelle.selectSingleNode(document);
	        String libelle =  (String) elemLibelle.getData();
	        // Recherche du code
	        Dom4jXPath searchCode = new Dom4jXPath("/emploi/code");
	        Element elemCode = (Element)searchCode.selectSingleNode(document);
	        String code =  (String) elemCode.getData();
	        // Recherche du niveau
	        Dom4jXPath searchNiveau = new Dom4jXPath("/emploi/niveau");
	        Element elemNiveau = (Element)searchNiveau.selectSingleNode(document);
	        Integer niveau =  Integer.parseInt((String) elemNiveau.getData());
	        // Recherche de la categorie
	        Dom4jXPath searchCateg = new Dom4jXPath("/emploi/categorie");
	        Element elemCateg = (Element)searchCateg.selectSingleNode(document);
	        String strCategorie = (String) elemCateg.getData();
	        CategorieEmploi categ = CategorieEmploi.valueOf(strCategorie.replace(' ', '_'));
	        CompetenceManagerLocal anAdmin = ServiceLocatorFireTime.getCompetenceManagerBean();
	        anAdmin.saveEmploi(code, libelle, niveau, categ);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}

	}
	private void processAffectation(Document document)
		throws  AgentNotFoundException, OrganigrammeNotFoundException, GradeNotFoundException{
		try {
	        // Recherche de l'ID
	        Dom4jXPath searchIdfAgent = new Dom4jXPath("/affectation/idf_agent");
	        Element elemIdfAgent = (Element)searchIdfAgent.selectSingleNode(document);
	        Integer idfAgent =  Integer.parseInt((String) elemIdfAgent.getData());
	        // Recherche du libellé
	        Dom4jXPath searchOrga = new Dom4jXPath("/affectation/organigramme");
	        Element elemOrga = (Element)searchOrga.selectSingleNode(document);
	        String orga =  (String) elemOrga.getData();
	        // Recherche du grade
	        Dom4jXPath searchGrade = new Dom4jXPath("/affectation/grade");
	        Element elemGrade = (Element)searchGrade.selectSingleNode(document);
	        String grade =  (String) elemGrade.getData();
	        // Recherche de la fonction
	        Dom4jXPath searchFct = new Dom4jXPath("/affectation/fonction");
	        Element elemFct = (Element)searchFct.selectSingleNode(document);
	        String fonction = (String) elemFct.getData();
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
	        EffectifManagerLocal anAdmin = ServiceLocatorFireTime.getEffectifManagerBean(null);
	        anAdmin.saveAffectation(idfAgent, orga, grade, fonction, debut, fin);
		}
		catch(JaxenException e ) {
			log.error("ERREUR", e);			
		}
		catch (ParseException e) {
			log.error("ERREUR", e);			
		}
	}

}
