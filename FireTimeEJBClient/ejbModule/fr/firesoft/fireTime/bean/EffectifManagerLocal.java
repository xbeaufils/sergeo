/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import fr.firesoft.fireTime.entity.EchelonOrganigramme;
import fr.firesoft.fireTime.entity.effectif.Affectation;
import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.exception.AgentNotFoundException;
import fr.firesoft.fireTime.exception.GradeNotFoundException;
import fr.firesoft.fireTime.exception.OrganigrammeNotFoundException;
import fr.firesoft.fireTime.helper.AffectationViewHelper;

/**
 * @author xbeaufils
 *
 */
public interface EffectifManagerLocal {
	public void save(Agent agent);
	
	public void saveAffectation(Integer idfAgent, String orga, String grade, String fonction, Date debut, Date fin)
		throws AgentNotFoundException, OrganigrammeNotFoundException, GradeNotFoundException;
	public void saveAffectation (Affectation anAffectation);
	public void saveAffectation (AffectationViewHelper anAffectation) throws ParseException;
	public void deleteAffectation(Integer idfAffectation);
	public void add (String code, String libelle, String codeSup);
	public void add (EchelonOrganigramme echelon, String codeSup);
	public void save(EchelonOrganigramme echelon);
	public EchelonOrganigramme selectEchelon (Integer idfEchelon);
	public List<EchelonOrganigramme> makeListByOrganisation(String organisationName);
	public void saveGrade(String code, String libelle, String groupe, String filiere);
	public void saveGrade(Integer idfGrade, String code, Integer idfEchelon);
	public void checkGrade();
	public void checkEmploiOperationnel();
	public void saveEmploiOperationnel(Integer idfEmploi, String code, Integer idfEchelon);
	public EchelonOrganigramme searchEchelonByCode(String code) throws OrganigrammeNotFoundException;	
	public void transfert(String message) throws AgentNotFoundException, OrganigrammeNotFoundException, GradeNotFoundException;
	public void setAuthentificationCache(AuthentificationLocal auth);
}
