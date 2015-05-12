/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.Date;
import java.util.List;

import fr.firesoft.fireTime.entity.effectif.Affectation;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.effectif.Filiere;
import fr.firesoft.fireTime.helper.AffectationViewHelper;
import fr.firesoft.fireTime.helper.AgentHelper;
import fr.firesoft.fireTime.helper.EmploiOperationnelHelper;
import fr.firesoft.fireTime.helper.GradeHelper;

/**
 * @author xbeaufils
 *
 */
public interface EffectifDaoLocal {
	public List<AgentHelper> selectForDateAndEchelon(Integer idfEchelon, Date day);
	public List<AgentHelper> selectForIntervalleAndEchelon(Integer idfEchelon, Date day, int intervalle);
	public List<AgentHelper> selectForAgentInDayAndEchelon(Integer idfAgent, 
			Integer idfEchelon, Date day, int intervalle);
	public List<AgentHelper> selectForIntervalleAndEchelon(Integer idfEchelon,
			Date day, int intervalle, List<Filiere> lstFiliere);
	public List<Affectation> makeListForAgent( Integer idfAgent);
	public List<AffectationViewHelper> makeListViewForAgent( Integer idfAgent);
	public Agent select (Integer idfAgent);
	public Agent selectByMatricule(Integer matricule, Integer idfEchelon);
	public List<GradeHelper> makeListGradeForOrganisation(Integer idfEchelon);
	public List<GradeHelper> makeListGradeForOrganization(Integer idOrga);	
	public List<GradeHelper> makeListGradeForOrganisationWithEmptyCode(Integer idfEchelon);
	public List<EmploiOperationnelHelper> makeListEmploiOpeForOrganisationWithEmptyCode(Integer idfEchelon);
	public List<EmploiOperationnelHelper> makeListEmploiOpeForOrganisation(Integer idfEchelon);
	public Affectation selectAffectation(Integer idfAffectation);
	public AffectationViewHelper selectAffectationHelper(Integer idfAffectation);
	public void setAuthentificationCache(AuthentificationLocal auth);
	
}
