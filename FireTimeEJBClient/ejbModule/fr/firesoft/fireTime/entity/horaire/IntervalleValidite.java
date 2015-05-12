/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.EchelonOrganigramme;

/**
 * @author xavier
 *
 */
@Entity
@Table (schema="firesoft", name="INTERVALLE")
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="TYPE_INTERVALLE")
public abstract class IntervalleValidite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4538283388552589971L;
	private Integer idfIntervalle;
	private Date dateValidite;
	private String libelle;
	private EchelonOrganigramme echelon;
	private IntervalleValidite next;
	/**
	 * 
	 */
	public IntervalleValidite() {
	}
	/**
	 * @return the idfIntervalle
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdfIntervalle() {
		return idfIntervalle;
	}
	/**
	 * @param idfIntervalle the idfIntervalle to set
	 */
	public void setIdfIntervalle(Integer idfIntervalle) {
		this.idfIntervalle = idfIntervalle;
	}
	/**
	 * @return the dateValidite
	 */
	@Column (name="DATE_VALIDITE")
	public Date getDateValidite() {
		return dateValidite;
	}
	/**
	 * @param dateValidite the dateValidite to set
	 */
	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
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
	 * @return the echelon
	 */
	@ManyToOne
	@JoinColumn (name="IDF_ECHELON")
	public EchelonOrganigramme getEchelon() {
		return echelon;
	}
	/**
	 * @param echelon the echelon to set
	 */
	public void setEchelon(EchelonOrganigramme echelon) {
		this.echelon = echelon;
	}
	/**
	 * @return the next
	 */
	@ManyToOne
	@JoinColumn (name="NEXT_INTERVALLE")
	public IntervalleValidite getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(IntervalleValidite next) {
		this.next = next;
	}

}
