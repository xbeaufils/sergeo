/**
 * 
 */
package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * @author xbeaufils
 *
 */
@NamedQueries ( {
	@NamedQuery(name="Affectation.selectForAgent",
			query=" FROM Affectation "
				+ "WHERE agent.idfAgent = :idfAgent "),
	@NamedQuery(name="Affectation.selectSame",
			query=" FROM Affectation "
				+ "WHERE idfEchelon = :anIdfEchelon "
				+ "AND agent.idfAgent = :idfAgent "
				+ "AND debut = :debut"),
	@NamedQuery(name="Affectation.selectForDayAndEchelon",
			query=" FROM Affectation "
				+ "WHERE idfEchelon = :anIdfEchelon "
				+ "AND (fin IS NULL OR fin >= :dateDay) "
				+ "AND debut <= :dateDay"),
	@NamedQuery(name="Affectation.selectForDayAndEchelonAndFiliere",
			query=" FROM Affectation "
				+ "WHERE idfEchelon = :anIdfEchelon "
				+ "AND (fin IS NULL OR fin >= :dateDay) "
				+ "AND debut <= :dateDay "
				+ "AND grade.filiere IN (:filiere )"),
	@NamedQuery(name="Affectation.selectForIntervalleAndEchelonAndFiliere",
			query=" FROM Affectation "
				+ "WHERE idfEchelon = :anIdfEchelon "
				+ "AND (fin IS NULL OR fin >= :dateFin) "
				+ "AND debut <= :dateDebut "
				+ "AND grade.filiere IN (:filiere )"),
	@NamedQuery(name="Affectation.selectForIntervalleAndEchelon",
			query=" FROM Affectation "
				+ "WHERE idfEchelon = :anIdfEchelon "
				+ "AND (fin IS NULL OR fin >= :dateFin) "
				+ "AND debut <= :dateDebut "),
	@NamedQuery(name="Affectation.selectForAgentAndDayAndEchelon",
			query=" FROM Affectation "
				+ "WHERE idfEchelon = :anIdfEchelon "
				+ "AND agent.idfAgent = :idfAgent "
				+ "AND (fin IS NULL OR fin >= :day) "
				+ "AND debut <= :day ")
				
})
@Entity
@Table ( schema="firesoft", name="AFFECTATION" )
public class Affectation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5935588844211690999L;
	private Integer idfAffectation;
	private Agent agent;
	private Grade grade;
	private Fonction fonction;
	//private EchelonOrganigramme echelon;
	private Integer idfEchelon;
	private Date debut;
	private Date fin;
	/**
	 * 
	 */
	public Affectation() {
	}
	/**
	 * @return the idfAffectation
	 */
	@Id
	@Column (name="IDF_AFFECTATION")
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	 * @return the agent
	 */
	@ManyToOne
	@JoinColumn (name="IDF_AGENT")
	public Agent getAgent() {
		return agent;
	}
	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	/**
	 * @return the echelon
	 */
	@Column (name="IDF_ECHELON")
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
	 * @return the idfGrade
	 */
	@OneToOne
	@JoinColumn (name="IDF_GRADE")
	public Grade getGrade() {
		return grade;
	}
	/**
	 * @param idfGrade the idfGrade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	/**
	 * @return the idfFonction	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	 */
	@OneToOne
	@JoinColumn(name="IDF_FONCTION")
	public Fonction getFonction() {
		return fonction;
	}
	/**
	 * @param idfFonction the idfFonction to set
	 */
	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	/**
	 * @return the debut
	 */
	@Column (name="DATE_DEBUT")
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
	@Column (name="DATE_FIN")
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
