/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.Date;
import java.util.List;

import fr.firesoft.fireTime.entity.Activite;
import fr.firesoft.fireTime.entity.CategorieEmploi;
import fr.firesoft.fireTime.entity.CompetenceAgent;
import fr.firesoft.fireTime.entity.EmploiOperationnel;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;


/**
 * @author xbeaufils
 *
 */
public interface CompetenceManagerLocal {
	public void addCompetenceForAgent(Integer idfAgent, 
			String codeUniteValeur, Integer niveau, Date debut, Date fin, Activite actif );
	public void saveCompetence(CompetenceAgentHelper competence);
	public CompetenceAgentHelper selectCompetence(Integer idfCompetence);
	public void deleteCompetence(Integer idfCompetence);
	public List<PlageHoraire> makeListForEchelon(Integer idfEchelon);
	public void saveEmploi(String code, String libelle,Integer niveau, CategorieEmploi categorie);
	public void saveUniteValeur(String code, String libelle);
	
	public List<EmploiOperationnel> makeListEmploiOperationel();
	public EmploiOperationnel selectEmploi(Integer idfEmploi);
	// Gestion des agents
	public List<CompetenceAgent> makeListForAgent(Integer idfAgent);
	public List<CompetenceAgentHelper> makeListHelperForAgent(Integer idfAgent);
}
