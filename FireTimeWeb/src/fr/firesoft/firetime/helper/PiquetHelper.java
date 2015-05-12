/**
 * 
 */
package fr.firesoft.firetime.helper;

import fr.firesoft.fireTime.entity.Piquet;
import fr.firesoft.fireTime.entity.effectif.Agent;

/**
 * @author xbeaufils
 *
 */
public class PiquetHelper {

	private Piquet piquet;
	private Agent agent;
	private Integer periode;
	/**
	 * 
	 */
	public PiquetHelper() {
	}
	/**
	 * @return the piquet
	 */
	public Piquet getPiquet() {
		return piquet;
	}
	/**
	 * @param piquet the piquet to set
	 */
	public void setPiquet(Piquet piquet) {
		this.piquet = piquet;
	}
	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}
	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	/**
	 * @return the periode
	 */
	public Integer getPeriode() {
		return periode;
	}
	/**
	 * @param periode the periode to set
	 */
	public void setPeriode(Integer periode) {
		this.periode = periode;
	}

}
