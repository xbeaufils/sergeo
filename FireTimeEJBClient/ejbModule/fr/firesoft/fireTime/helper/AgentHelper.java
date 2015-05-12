/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;
import java.util.Date;


/**
 * @author xbeaufils
 *
 */
public class AgentHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1711504252990998937L;
	private Integer idfAgent;
	private String matricule;
	private String nom;
	private String prenom;
	private GradeHelper grade;
	//private Fonction fonction;
	public Integer idfEchelon;
	private Date debut;
	private Date fin;
	/**
	 * 
	 */
	public AgentHelper() {
	}
	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}
	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the grade
	 */
	public GradeHelper getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(GradeHelper grade) {
		this.grade = grade;
	}
	/**
	 * @return the fonction
	 
	public Fonction getFonction() {
		return fonction;
	}
	/**
	 * @param fonction the fonction to set
	 *
	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	/**
	 * @return the echelon
	 */
	public Integer getIdfEchelon() {
		return idfEchelon;
	}
	/**
	 * @param echelon the echelon to set
	 */
	public void setIdfEchelon(Integer echelon) {
		this.idfEchelon = echelon;
	}
	/**
	 * @return the debut
	 */
	public Date getDebut() {
		return debut;
	}
	/**
	 * @param debut the debut to set
	 */
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

}
