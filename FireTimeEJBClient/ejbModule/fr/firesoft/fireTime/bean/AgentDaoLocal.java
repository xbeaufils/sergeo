/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.Date;
import java.util.List;

import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.exception.AgentNotFoundException;
import fr.firesoft.fireTime.helper.CompetenceAgentHelper;

/**
 * @author xbeaufils
 *
 */
public interface AgentDaoLocal {
	public List<Agent> makeListAgent(Integer idfOrganisation);
	public List<CompetenceAgentHelper> makeListAgentForEmploiAndEchelon(Date day, Integer idfEmploi, Integer idfEchelon);
	public Agent selectByMatricule(Integer matricule) throws AgentNotFoundException;
}
