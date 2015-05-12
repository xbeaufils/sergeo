/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author xbeaufils
 *
 */
@Embeddable
public class SequenceTravailPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2560872727188055686L;

	private CycleTravail cycle;
	private Integer ordre;
	/**
	 * 
	 */
	public SequenceTravailPk() {
	}
	/**
	 * @return the cycle
	 */
	@ManyToOne
	@JoinColumn (name="IDF_CYCLE_TRAVAIL")
	public CycleTravail getCycle() {
		return cycle;
	}
	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(CycleTravail cycle) {
		this.cycle = cycle;
	}
	/**
	 * @return the ordre
	 */
	@Column (name="ORDRE")
	public Integer getOrdre() {
		return ordre;
	}
	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

}
