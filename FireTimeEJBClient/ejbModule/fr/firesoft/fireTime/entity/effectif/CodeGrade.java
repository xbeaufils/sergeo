/**
 * 
 */
package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author xbeaufils
 *
 */
@Entity
@Table (schema="firesoft", name="CODE_GRADE")
@NamedQueries (value= {
		@NamedQuery (name="CodeGrade.makeListGradeForOrga",
				query="SELECT grd.id.grade FROM CodeGrade grd "
					+ "WHERE grd.id.organisation = :anOrga "
					+ "ORDER BY grd.id.grade.libelle"),
		@NamedQuery (name="CodeGrade.selectByCode",
				query="SELECT grd.id.grade FROM CodeGrade grd "
					+ "WHERE grd.code = :aCode "
					+ "ORDER BY grd.id.grade.libelle"),
		@NamedQuery (name="CodeGrade.makeListForOrga",
				query="SELECT grd FROM CodeGrade grd "
					+ "WHERE grd.id.organisation = :anOrga "),
		@NamedQuery (name="CodeGrade.makeListForOrgaById",
				query="SELECT grd FROM CodeGrade grd "
					+ "WHERE grd.id.organisation = :anOrga ")
})
public class CodeGrade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1743726598548303850L;

	private CodeGradePK id;
	private String code;
	
	public CodeGrade() {
	}

	/**
	 * @return the id
	 */
	@EmbeddedId
	public CodeGradePK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(CodeGradePK id) {
		this.id = id;
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
