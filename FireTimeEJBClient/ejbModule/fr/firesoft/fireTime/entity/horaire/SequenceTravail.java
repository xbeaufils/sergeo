/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@Entity
@Table (schema="firesoft", name="SEQUENCE")
public class SequenceTravail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2126998714148653798L;
	private SequenceTravailPk id;
	private StatutPeriode statut;
	
	/**
	 * 
	 */
	public SequenceTravail() {
	}
	
	/**
	 * @return the id
	 */
	@EmbeddedId
	public SequenceTravailPk getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(SequenceTravailPk id) {
		this.id = id;
	}

	/**
	 * @return the statut
	 */
	@ManyToOne
	@JoinColumn (name="IDF_STATUT")
	public StatutPeriode getStatut() {
		return statut;
	}
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(StatutPeriode statut) {
		this.statut = statut;
	}

}
