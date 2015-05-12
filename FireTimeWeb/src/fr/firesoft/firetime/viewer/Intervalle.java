/**
 * 
 */
package fr.firesoft.firetime.viewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author beaufils
 *
 */
public class Intervalle {

	Logger log = LoggerFactory.getLogger(Intervalle.class);
	/**
	 * @author beaufils
	 *
	 */
	public enum Superposition {
		EQUAL,
		BEFORE,
		BEFORE_INSIDE,
		INSIDE,
		INSIDE_AFTER,
		AFTER,
		OVERLAPP;

	}
	private Heure debut;
	private Heure fin;
	/**
	 * 
	 */
	public Intervalle() {
	}
	/**
	 * @return the debut
	 */
	public Heure getDebut() {
		return debut;
	}
	/**
	 * @param debut the debut to set
	 */
	public void setDebut(Heure debut) {
		this.debut = debut;
	}
	/**
	 * @return the fin
	 */
	public Heure getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(Heure fin) {
		this.fin = fin;
	}
	public Superposition compare(Intervalle another) {
		log.debug("This {} another {}", new Object[]{this, another});
		switch (debut.compare(another.getDebut()) ){
		case BEFORE :
			switch (fin.compare(another.getDebut())) {
			case BEFORE:
			case EQUAL:
				return Superposition.BEFORE;
			case AFTER:
				if (fin.compare(another.getFin()) == TimePosition.BEFORE
						|| fin.compare(another.getFin()) == TimePosition.EQUAL )
					return Superposition.BEFORE_INSIDE;
				else
					return Superposition.OVERLAPP;
			}
			break;
		case EQUAL :
			if (fin.compare(another.getFin()) == TimePosition.AFTER
					|| fin.compare(another.getFin()) == TimePosition.EQUAL)
				return Superposition.OVERLAPP;
			else
				return Superposition.INSIDE;
		case AFTER:
			if (fin.compare(another.getFin()) == TimePosition.BEFORE
					|| fin.compare(another.getFin()) == TimePosition.EQUAL)
				return Superposition.INSIDE;
			return Superposition.AFTER;
		}		
		return null;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Intervalle [debut=" + debut + ", fin=" + fin + "]";
	}
	
	
}
