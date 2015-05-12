/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@NamedQueries ( {
	@NamedQuery (name="CycleTravail.makeListForCycle",
			query = "FROM CycleTravail " 
				+ " WHERE cycle.idfCycle = :idfCycle ")
})
@Entity
@Table(schema="firesoft", name="CYCLE_TRAVAIL")
public class CycleTravail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5501348884844278565L;
	private Integer idfCycle;
	private String libelle;
	private CycleHoraire cycle;
	private List<SequenceTravail> lstSequence;
	/**
	 * 
	 */
	public CycleTravail() {
	}
	/**
	 * @return the idfCycle
	 */
	@Id
	@Column (name="IDF_CYCLE")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdfCycle() {
		return idfCycle;
	}
	/**
	 * @param idfCycle the idfCycle to set
	 */
	public void setIdfCycle(Integer idfCycle) {
		this.idfCycle = idfCycle;
	}
	/**
	 * @return the libelle
	 */
	@Column (name="LIBELLE")
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the cycle
	 */
	@ManyToOne 
	@JoinColumn (name="IDF_CYCLE_HORAIRE")
	public CycleHoraire getCycle() {
		return cycle;
	}
	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(CycleHoraire cycle) {
		this.cycle = cycle;
	}
	/**
	 * @return the lstSequence
	 */
	@OneToMany(mappedBy="id.cycle") 
	public List<SequenceTravail> getLstSequence() {
		return lstSequence;
	}
	/**
	 * @param lstSequence the lstSequence to set
	 */
	public void setLstSequence(List<SequenceTravail> lstSequence) {
		this.lstSequence = lstSequence;
	}

}
