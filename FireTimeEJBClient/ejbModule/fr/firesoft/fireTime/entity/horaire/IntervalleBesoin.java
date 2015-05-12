/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author xavier
 *
 */
@Entity
@DiscriminatorValue (value="B")
public class IntervalleBesoin extends IntervalleValidite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 199517835402810639L;

	/**
	 * 
	 */
	public IntervalleBesoin() {
		// TODO Auto-generated constructor stub
	}

}
