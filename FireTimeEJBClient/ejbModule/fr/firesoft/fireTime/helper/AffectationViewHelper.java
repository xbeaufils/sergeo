/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;

/**
 * @author xbeaufils
 *
 */
public class AffectationViewHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863251179491925136L;
	private Integer idfAffectation;
	private Integer idfAgent;
	private Integer idfGrade;
	private String grade;
	private String debut;
	private String fin;
	/**
	 * 
	 */
	public AffectationViewHelper() {
	}
	/**
	 * @return the idfAffectation
	 */
	public Integer getIdfAffectation() {
		return idfAffectation;
	}
	/**
	 * @param idfAffectation the idfAffectation to set
	 */
	public void setIdfAffectation(Integer idfAffectation) {
		this.idfAffectation = idfAffectation;
	}
	/**
	 * @return the idfAgent
	 */
	public Integer getIdfAgent() {
		return idfAgent;
	}
	/**
	 * @param idfAgent the idfAgent to set
	 */
	public void setIdfAgent(Integer idfAgent) {
		this.idfAgent = idfAgent;
	}
	/**
	 * @return the idfGrade
	 */
	public Integer getIdfGrade() {
		return idfGrade;
	}
	/**
	 * @param idfGrade the idfGrade to set
	 */
	public void setIdfGrade(Integer idfGrade) {
		this.idfGrade = idfGrade;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the debut
	 */
	public String getDebut() {
		return debut;
	}
	/**
	 * @param debut the debut to set
	 */
	public void setDebut(String debut) {
		this.debut = debut;
	}
	/**
	 * @return the fin
	 */
	public String getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}

}
