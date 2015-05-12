/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;

/**
 * @author xbeaufils
 *
 */
public class GradeHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7711695667607532339L;

	private Integer id;
	private String libelle;
	private String code;
	private String filiere;
	/**
	 * 
	 */
	public GradeHelper() {
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	/**
	 * @return the filiere
	 */
	public String getFiliere() {
		return filiere;
	}
	/**
	 * @param filiere the filiere to set
	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

}
