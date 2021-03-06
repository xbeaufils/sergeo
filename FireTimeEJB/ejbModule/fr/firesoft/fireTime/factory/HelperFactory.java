/**
 * 
 */
package fr.firesoft.fireTime.factory;

import java.util.ArrayList;
import java.util.List;

import fr.firesoft.fireTime.entity.CodeEmploiOperationnel;
import fr.firesoft.fireTime.entity.CompetenceAgent;
import fr.firesoft.fireTime.entity.customer.Facture;
import fr.firesoft.fireTime.entity.effectif.Filiere;
import fr.firesoft.fireTime.entity.effectif.Grade;
import fr.firesoft.fireTime.entity.planning.CommentDay;
import fr.firesoft.fireTime.entity.planning.CommentMonth;
import fr.firesoft.fireTime.ext.planning.CommentDayHelper;
import fr.firesoft.fireTime.ext.planning.CommentMonthHelper;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;
import fr.firesoft.fireTime.helper.EmploiOperationnelHelper;
import fr.firesoft.fireTime.helper.FactureHelper;
import fr.firesoft.fireTime.helper.GradeHelper;

/**
 * @author xbeaufils
 *
 */
public class HelperFactory {

	/**
	 * 
	 */
	public HelperFactory() {
	}

	
	public static GradeHelper make (Grade aGrade) {
		GradeHelper aHelper = new GradeHelper();
		aHelper.setId(aGrade.getId());
		String libelle = aGrade.getLibelle();
		if (aGrade.getFiliere() != null) {
			if (aGrade.getFiliere().equals(Filiere.SPP)) {
				libelle += " (SPP)";
				aHelper.setFiliere("SPP");
			}
			else
				if (aGrade.getFiliere().equals(Filiere.SPV)) {
					libelle += " (SPV)";
					aHelper.setFiliere("SPV");
				}
		}
		aHelper.setLibelle(libelle);
		// TODO Récupérer le code
		aHelper.setCode(null);
		return aHelper;
	}
	
	public static List<GradeHelper> makeListGrade (List<Grade> lstGrade) {
		List<GradeHelper> lstHelper = new ArrayList<GradeHelper>();
		for (Grade aGrade : lstGrade) 
			lstHelper.add(HelperFactory.make(aGrade));
		return lstHelper;
	}
	
	public static List<EmploiOperationnelHelper> makeListEmploi(List<CodeEmploiOperationnel> lstCode) {
		List<EmploiOperationnelHelper> lstHelper = new ArrayList<EmploiOperationnelHelper>();
		for (CodeEmploiOperationnel code : lstCode) {
			EmploiOperationnelHelper helper = new EmploiOperationnelHelper();
			helper.setCode(code.getCode());
			helper.setIdEmploi(code.getId().getEmploi().getIdEmploi());
			helper.setLibelle(code.getId().getEmploi().getLibelle());
			lstHelper.add(helper);
		}
		return lstHelper;
	}
	
	public static FactureHelper makeFacture(Facture facture) {
		FactureHelper helper = new FactureHelper();
		helper.setIdFacture(facture.getIdFacture());
		helper.setDateFacturation(facture.getDateFacturation());
		helper.setLibelle(facture.getClient().getLibelle());
		helper.setAdresse(facture.getClient().getAdresse());
		helper.setAdresseComplementaire(facture.getClient().getAdresseComplementaire());
		helper.setCodePostal(facture.getClient().getCodePostal());
		helper.setVille(facture.getClient().getVille());
		return helper;
	}
	
	public static List<FactureHelper> makeListFacture(List<Facture> lstFacture) {
		List<FactureHelper> lstHelper = new ArrayList<FactureHelper>();
		for (Facture facture : lstFacture) {
			lstHelper.add(HelperFactory.makeFacture(facture));
		}
		return lstHelper;
	}
	
	public static CompetenceAgentHelper makeCompetence(CompetenceAgent entity) {
		CompetenceAgentHelper helper = new CompetenceAgentHelper();
		helper.setIdfCompetenceAgent(entity.getIdfCompetenceAgent());
		helper.setDebut(entity.getDteDebut());
		helper.setFin(entity.getDteFin());
		helper.setIdfAgent(entity.getAgent().getIdfAgent());
		helper.setIdfEmploiOpe(entity.getEmploi().getIdEmploi());
		helper.setEmploi(entity.getEmploi().getLibelle());
		return helper;
		
	}
	
	public static List<CompetenceAgentHelper> makeListCompetence(List<CompetenceAgent> lstEntity) {
		List<CompetenceAgentHelper> lstHelper = new ArrayList<CompetenceAgentHelper>();
		for (CompetenceAgent entity : lstEntity) 
			lstHelper.add(HelperFactory.makeCompetence(entity));
		return lstHelper;
	}
	
	public static CommentDayHelper make(CommentDay entity) {
		CommentDayHelper helper = new CommentDayHelper();
		helper.setIdfComment(entity.getIdfComment());
		helper.setDteComment(entity.getDteComment().getTime());
		helper.setComment(entity.getComment());
		helper.setIdfAgent(entity.getAgent().getIdfAgent());
		helper.setIdfOrga(entity.getIdOrganization());
		return helper;
	}
	
	public static List<CommentDayHelper> makeListCommentDay(List<CommentDay> lstEntity) {
		List<CommentDayHelper> lstHelper = new ArrayList<CommentDayHelper>();
		for (CommentDay entity: lstEntity)
			lstHelper.add(HelperFactory.make(entity));
		return lstHelper;
	}
	
	public static CommentMonthHelper make(CommentMonth entity) {
		CommentMonthHelper helper = new CommentMonthHelper();
		return helper;
	}
}
