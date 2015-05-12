package fr.firesoft.fireTime.factory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.firesoft.fireTime.entity.effectif.Affectation;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.helper.AffectationViewHelper;
import fr.firesoft.fireTime.helper.AgentHelper;

public class EffectifHelperFactory {
	
	public static AgentHelper make(Agent entity) {
		AgentHelper anAgent = new AgentHelper();
		anAgent.setMatricule(entity.getMatricule().toString());
		anAgent.setIdfAgent(entity.getIdfAgent());
		anAgent.setNom(entity.getNom());
		anAgent.setPrenom(entity.getPrenom());
		anAgent.setIdfEchelon(entity.getOrganisation());
		return anAgent;
	}
	
	public static AffectationViewHelper makeAffectationHelper(Affectation anAffectation) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		AffectationViewHelper affectation = new AffectationViewHelper();
		affectation.setIdfAgent(anAffectation.getAgent().getIdfAgent());
		affectation.setDebut(sdFormat.format( anAffectation.getDebut()) );
		if (anAffectation.getFin() != null)
			affectation.setFin(sdFormat.format( anAffectation.getFin()) );
		affectation.setIdfGrade(anAffectation.getGrade().getId());
		affectation.setGrade(anAffectation.getGrade().getLibelle());
		affectation.setIdfAffectation(anAffectation.getIdfAffectation());		
		return affectation;
	}
	public static AgentHelper make(Affectation anAffectation) {
		AgentHelper anAgent = new AgentHelper();
		anAgent.setMatricule(anAffectation.getAgent().getMatricule().toString());
		anAgent.setIdfAgent(anAffectation.getAgent().getIdfAgent());
		anAgent.setNom(anAffectation.getAgent().getNom());
		anAgent.setPrenom(anAffectation.getAgent().getPrenom());
		anAgent.setIdfEchelon(anAffectation.getIdfEchelon());
		anAgent.setDebut(anAffectation.getDebut());
		anAgent.setFin(anAffectation.getFin());
		anAgent.setGrade(HelperFactory.make(anAffectation.getGrade()));
		return anAgent;
	}
	

	public static List<AffectationViewHelper> makeListAffectation(List<Affectation> lstEntity) {
		List<AffectationViewHelper> lstHelper = new ArrayList<AffectationViewHelper>();
		for (Affectation entity: lstEntity) {
			lstHelper.add(EffectifHelperFactory.makeAffectationHelper(entity));
		}
		return lstHelper;
	}
	
	public static List<AgentHelper> make (List<Affectation> lstAffectation) {
		List<AgentHelper> lstHelper = new ArrayList<AgentHelper>();
		for (Affectation anAffectation : lstAffectation) {
			lstHelper.add(EffectifHelperFactory.make(anAffectation));
		}
		return lstHelper;
	}

}
