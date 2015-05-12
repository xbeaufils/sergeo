/**
 * 
 */
package fr.firesoft.fireTime.genetic.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.firesoft.fireTime.genetic.SolverSolutionDepart;

/**
 * @author xavier
 *
 */
public class SolutionDepartFPT extends SolutionDepart {

	public SolutionDepartFPT(SolverSolutionDepart solver, Map<String, Float> ponderation) {
		Calendar now = Calendar.getInstance();
		super.setIdentifiant(now.getTimeInMillis());
		genes = new HashMap<Piquet, Agent> ();
		List<Agent> lstTmpAgent = new ArrayList<Agent>(solver.getLstAgent());
		List<String> besoin = new ArrayList<String>(solver.getBesoin());
		int i=0;
		for (String aBesoin: besoin) {
			int index = randomizer.nextInt(lstTmpAgent.size());
			genes.put( new Piquet(i++, aBesoin, ponderation.get(aBesoin)), lstTmpAgent.get(index));
			lstTmpAgent.remove(index);
		}
		
	}

	public SolutionDepartFPT() {
		genes = new HashMap<Piquet, Agent>();
		Calendar now = Calendar.getInstance();
		super.setIdentifiant(now.getTimeInMillis());
	}
	
	public SolutionDepartFPT(SolutionDepartFPT clone) {
		genes = new HashMap<Piquet, Agent>(clone.genes);
		Calendar now = Calendar.getInstance();
		super.setIdentifiant(now.getTimeInMillis());
	}
	
	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.factory.genetic.model.SolutionDepart#evaluate()
	 */
	@Override
	public void evaluate() {
		super.adaptation = Float.valueOf(0);
		// if (! viable) return;
		for ( Piquet piquet : getGenes().keySet()) {
			Agent agent = getGenes().get(piquet);
			if (agent.getLstCompetence().contains(piquet.getBesoin()) )
				super.adaptation += piquet.getImportance();
		}
	}

}
