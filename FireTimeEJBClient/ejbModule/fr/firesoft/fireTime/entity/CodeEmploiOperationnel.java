/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author xavier
 *
 */
@NamedQueries ({
	@NamedQuery (name="CodeEmploiOperationnel.makeListForOrga",
			query="FROM CodeEmploiOperationnel "
				+ "WHERE id.organisation = :anOrga "),
	
})
@Entity
@Table (schema="firesoft", name="CODE_EMPLOI_OPE")
public class CodeEmploiOperationnel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6655503409313358026L;
	private String code;
	private CodeEmploiOpePk id;
	/**
	 * 
	 */
	public CodeEmploiOperationnel() {
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
	/**
	 * @return the id
	 */
	@EmbeddedId
	public CodeEmploiOpePk getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(CodeEmploiOpePk id) {
		this.id = id;
	}

}
