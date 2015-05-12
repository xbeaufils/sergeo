/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * @author xavier
 *
 */
@DiscriminatorValue(value="P")
public class IntervallePlageHoraire extends IntervalleValidite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7506782134980739475L;
	private List<PlageHoraire> lstPlage;
	private PlageHoraire firstPlage;

	/**
	 * 
	 */
	public IntervallePlageHoraire() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the lstPlage
	 */
	@OneToMany (mappedBy="intervalleValidite")	
	public List<PlageHoraire> getLstPlage() {
		return lstPlage;
	}

	/**
	 * @param lstPlage the lstPlage to set
	 */
	public void setLstPlage(List<PlageHoraire> lstPlage) {
		this.lstPlage = lstPlage;
	}

	/**
	 * @return the firstPlage
	 */
	@OneToOne
	@JoinColumn (name="IDF_FIRST_PLAGE")
	public PlageHoraire getFirstPlage() {
		return firstPlage;
	}

	/**
	 * @param firstPlage the firstPlage to set
	 */
	public void setFirstPlage(PlageHoraire firstPlage) {
		this.firstPlage = firstPlage;
	}

}
