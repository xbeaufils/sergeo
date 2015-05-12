package fr.firesoft.fireTime.besoin.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.horaire.PlageHoraire;

@Entity
@Table ( schema="firesoft" , name="BESOIN")
public class Besoin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8009162053558659457L;
	private Integer idfBesoin;
	private String libelle;
	private PlageHoraire plage;
	private List<CritereBesoin> lstCritere;

	public Besoin() {
		super();
	}

	/**
	 * @return the idfBesoin
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="IDF_BESOIN")
	public Integer getIdfBesoin() {
		return idfBesoin;
	}

	/**
	 * @param idfBesoin the idfBesoin to set
	 */
	public void setIdfBesoin(Integer idfBesoin) {
		this.idfBesoin = idfBesoin;
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
	@JoinColumn(name="IDF_PLAGE")
	public PlageHoraire getPlageHoraire() {
		return plage;
	}

	/**
	 * @param cycle the cycle to set
	 */
	public void setPlageHoraire(PlageHoraire plage) {
		this.plage = plage;
	}

	/**
	 * @return the lstCritere
	 */
	@OneToMany(mappedBy="besoin")
	public List<CritereBesoin> getLstCritere() {
		return lstCritere;
	}

	/**
	 * @param lstCritere the lstCritere to set
	 */
	public void setLstCritere(List<CritereBesoin> lstCritere) {
		this.lstCritere = lstCritere;
	}

}
