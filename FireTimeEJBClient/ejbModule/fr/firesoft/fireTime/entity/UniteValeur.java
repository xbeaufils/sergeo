/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@NamedQueries ({
	@NamedQuery (name="UniteValeur.selectByCode",
			query = "FROM UniteValeur " +
					"WHERE code = :code")
})
@Entity
@Table (name="UNITE_VALEUR", schema="firesoft")
public class UniteValeur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6530803283867274631L;
	private Integer idfUniteValeur;
	private String libelle;
	private String code;
	/**
	 * 
	 */
	public UniteValeur() {
	}
	/**
	 * @return the idfUniteValeur
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="IDF_UNITE_VALEUR")
	public Integer getIdfUniteValeur() {
		return idfUniteValeur;
	}
	/**
	 * @param idfUniteValeur the idfUniteValeur to set
	 */
	public void setIdfUniteValeur(Integer idfUniteValeur) {
		this.idfUniteValeur = idfUniteValeur;
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
	 * @return the code
	 */
	@Column (name="CODE")
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
