/**
 * 
 */
package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.firesoft.fireTime.entity.Organisation;

/**
 * @author xbeaufils
 *
 */
@Embeddable
public class CodeGradePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2383655165274364342L;
	/**
	 * 
	 */
	private Grade grade;
	private Integer organisation;

	public CodeGradePK() {
	}
	/**
	 * @return the grade
	 */
	@ManyToOne
	@JoinColumn (name="IDF_GRADE")
	public Grade getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	/**
	 * @return the organisation
	 */
	@Column (name="ORGA")
	public Integer getOrganisation() {
		return organisation;
	}
	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(Integer organisation) {
		this.organisation = organisation;
	}

}
