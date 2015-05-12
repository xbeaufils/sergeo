/**
 * 
 */
package fr.firesoft.firetime.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import fr.firesoft.fireTime.entity.Periode;
import fr.firesoft.fireTime.helper.AgentHelper;
import fr.firesoft.fireTime.helper.JourAgent;

/**
 * @author xbeaufils
 *
 */
public class PlanningHelper {

	private AgentHelper agent;
	private List<JourAgent> mois;
	/**
	 * 
	 */
	public PlanningHelper() {
	}

	public PlanningHelper(AgentHelper agent, Integer month, Integer year) {
		this.agent = agent;
		mois = new ArrayList<JourAgent>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		for (int day=1; day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); day++) {
			calendar.set(Calendar.DAY_OF_MONTH, day);
			JourAgent aJour = new JourAgent();
			aJour.setJour(day);
			aJour.setIdfAgent(agent.getIdfAgent());
			mois.add(aJour);
		}
	}

	public PlanningHelper(AgentHelper agent) {
		this.agent = agent;
		mois = new ArrayList<JourAgent>();
	}
	
	public void addPeriode(Periode periode) {
		// mois.put(periode.getJour().get(Calendar.DAY_OF_MONTH), periode.getStatut());
	}

	/**
	 * @return the agent
	 */
	public AgentHelper getAgent() {
		return agent;
	}

	/**
	 * @param agent the agent to set
	 */
	public void setAgent(AgentHelper agent) {
		this.agent = agent;
	}
	
	public List<JourAgent> getMonth() {
		return mois; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return agent.equals( ((PlanningHelper) obj).agent );
	}
	/**
	 * @author xbeaufils
	 *
	 */
	public static class PlanningComparator implements Comparator<PlanningHelper> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(PlanningHelper arg0, PlanningHelper arg1) {
			int compar = arg0.agent.getNom().compareTo(arg1.agent.getNom());
			if (compar != 0)
				return compar;
			compar = arg0.agent.getPrenom().compareTo(arg1.agent.getPrenom());
			return compar;
		}

		public PlanningComparator() {
			super();
		}
		

	}


	
}
