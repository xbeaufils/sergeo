/**
 * 
 */
package fr.firesoft.firetime.viewer;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author beaufils
 *
 */
public class Heure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = LoggerFactory.getLogger(Heure.class);

	private boolean onNextDay;
	private Integer heure;
	private Integer minute;
	/**
	 * 
	 */
	public Heure() {
	}
	
	public Heure(Integer heure, Integer minute, boolean onNextDay) {
		this.heure = heure;
		this.minute = minute;		
		this.onNextDay = onNextDay;
	}
	
	public Heure(Heure another) {
		this.heure = another.heure;
		this.minute = another.minute;		
		this.onNextDay = another.onNextDay;
	}
	/**
	 * @return the heure
	 */
	public Integer getHeure() {
		return heure;
	}
	/**
	 * @param heure the heure to set
	 */
	public void setHeure(Integer heure) {
		this.heure = heure;
	}
	/**
	 * @return the minute
	 */
	public Integer getMinute() {
		return minute;
	}
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	/**
	 * @return the onNextDay
	 */
	public boolean isOnNextDay() {
		return onNextDay;
	}

	/**
	 * @param onNextDay the onNextDay to set
	 */
	public void setOnNextDay(boolean onNextDay) {
		this.onNextDay = onNextDay;
	}

	public TimePosition compare(Heure another) {
		TimePosition returnPosition = null;
		if (onNextDay)
			if ( ! another.onNextDay)
				returnPosition = TimePosition.AFTER;
		if (! onNextDay)
			if ( another.onNextDay)
				returnPosition = TimePosition.BEFORE;
		if (returnPosition != null) {
			log.debug("This {} another {} return {}", new Object[]{this, another, returnPosition});
			return returnPosition;
		}
		if (heure < another.getHeure()) 
			returnPosition = TimePosition.BEFORE;
		else if (heure > another.getHeure())
			returnPosition = TimePosition.AFTER;
		if (returnPosition != null) {
			log.debug("This {} another {} return {}", new Object[]{this, another, returnPosition});
			return returnPosition;
		}
		// Heure est egale
		if (minute < another.getMinute())
			returnPosition = TimePosition.BEFORE;
		else if (minute > another.getMinute())
			returnPosition = TimePosition.AFTER;
		else
			returnPosition = TimePosition.EQUAL;
		return 	returnPosition;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heure == null) ? 0 : heure.hashCode());
		result = prime * result + ((minute == null) ? 0 : minute.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Heure other = (Heure) obj;
		if (heure == null) {
			if (other.heure != null)
				return false;
		} else if (!heure.equals(other.heure))
			return false;
		if (minute == null) {
			if (other.minute != null)
				return false;
		} else if (!minute.equals(other.minute))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Heure [heure=" + heure + ", minute=" + minute + "]";
	}

}
