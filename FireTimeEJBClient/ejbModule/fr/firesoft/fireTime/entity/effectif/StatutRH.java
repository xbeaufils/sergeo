/**
 * 
 */
package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@Entity
@Table (schema="firesoft", name="STATUT_RH")
public class StatutRH implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6931359116503450016L;
	private Integer idfStatut;
	private String codPos;
	private String libelle;
	private String actif;
	private String present;
	/**
	 * 
	 */
	public StatutRH() {
	}
	/**
	 * @return the idfStatut
	 */
	@Id
	@Column (name="IDF_STATUT")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdfStatut() {
		return idfStatut;
	}
	/**
	 * @param idfStatut the idfStatut to set
	 */
	public void setIdfStatut(Integer idfStatut) {
		this.idfStatut = idfStatut;
	}
	/**
	 * @return the codPos
	 */
	@Column (name="CODE")
	public String getCodPos() {
		return codPos;
	}
	/**
	 * @param codPos the codPos to set
	 */
	public void setCodPos(String codPos) {
		this.codPos = codPos;
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
	 * @return the actif
	 */
	@Column (name="ACTIF")
	public String getActif() {
		return actif;
	}
	/**
	 * @param actif the actif to set
	 */
	public void setActif(String actif) {
		this.actif = actif;
	}
	/**
	 * @return the present
	 */
	@Column (name="PRESENT")
	public String getPresent() {
		return present;
	}
	/**
	 * @param present the present to set
	 */
	public void setPresent(String present) {
		this.present = present;
	}

}
