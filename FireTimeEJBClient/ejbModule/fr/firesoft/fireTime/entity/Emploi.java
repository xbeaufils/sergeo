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
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@Entity
@Table ( schema="firesoft",name="EMPLOI")
public class Emploi implements Serializable {

	private Integer idfEmploi;
	private String libelle;
	private String code;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6933598784588774200L;

	/**
	 * 
	 */
	public Emploi() {
	}

	/**
	 * @return the idfEmploi
	 */
	@Id
	@Column (name="IDF_EMPLOI")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdfEmploi() {
		return idfEmploi;
	}

	/**
	 * @param idfEmploi the idfEmploi to set
	 */
	public void setIdfEmploi(Integer idfEmploi) {
		this.idfEmploi = idfEmploi;
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
