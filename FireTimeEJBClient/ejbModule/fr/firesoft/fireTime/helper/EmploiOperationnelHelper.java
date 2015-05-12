/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;

/**
 * @author xavier
 *
 */
public class EmploiOperationnelHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3975326098734452894L;
	private Integer idEmploi;
	private String libelle;
	private String code;

	/**
	 * 
	 */
	public EmploiOperationnelHelper() {
	}

	/**
	 * @return the idEmploi
	 */
	public Integer getIdEmploi() {
		return idEmploi;
	}

	/**
	 * @param idEmploi the idEmploi to set
	 */
	public void setIdEmploi(Integer idEmploi) {
		this.idEmploi = idEmploi;
	}

	/**
	 * @return the libelle
	 */
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
