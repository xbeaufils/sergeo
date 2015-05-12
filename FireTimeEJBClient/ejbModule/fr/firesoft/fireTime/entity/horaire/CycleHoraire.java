/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author xbeaufils
 *
 */
@Entity
@Table (schema="firesoft", name="CYCLE_LF")
@NamedQueries({
	@NamedQuery (name="CycleHoraire.makeListForEchelon",
			query = "FROM CycleHoraire "+
					"WHERE idOrganization = :anIdfEchelon"),
	@NamedQuery (name="CycleHoraire.selectForDateAndEchelon",
			query="SELECT cycle "
				+ "FROM CycleHoraire cycle "
				+ "LEFT OUTER JOIN cycle.next as nextCycle "
				+ "WHERE cycle.idOrganization = :anIdfEchelon " 
				+ "AND cycle.dateValidite <= :date "
				+ "AND (nextCycle IS NULL OR nextCycle.dateValidite > :date )")					
})

public class CycleHoraire implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 2333872592316683563L;
	private Integer idfCycle;
	private String libelle;
	private Integer idOrganization;
	private Date dateValidite;
	private CycleHoraire next;
	private List<PlageHoraire> lstPlage;
	// private List<BesoinEquipage> lstBesoin;
	private PlageHoraire firstPlage;
	
	/**
	 * 
	 */
	public CycleHoraire() {
	}

	/**
	 * @return the idfCyle
	 */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="IDF_CYCLE")
	public Integer getIdfCycle() {
		return idfCycle;
	}

	/**
	 * @param idfCyle the idfCyle to set
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
	 * @return the idOrganization
	 */
	@Column (name="ID_ORGA")
	public Integer getIdOrganization() {
		return idOrganization;
	}

	/**
	 * @param idOrganization the idOrganization to set
	 */
	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
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
	 * @return the next
	 */
	@ManyToOne
	@JoinColumn (name="IDF_NEXT_CYCLE")
	public CycleHoraire getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(CycleHoraire next) {
		this.next = next;
	}

	/**
	 * @return the lstPlage
	 */
	@OneToMany (mappedBy="cycle")	
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
	 * @return the lstBesoin
	 *
	@OneToMany (mappedBy="cycle")	
	public List<BesoinEquipage> getLstBesoin() {
		return lstBesoin;
	}

	/**
	 * @param lstBesoin the lstBesoin to set
	 *
	public void setLstBesoin(List<BesoinEquipage> lstBesoin) {
		this.lstBesoin = lstBesoin;
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
