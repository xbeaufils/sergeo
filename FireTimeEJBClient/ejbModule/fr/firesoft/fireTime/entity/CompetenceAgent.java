/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.effectif.Agent;

/**
 * @author xbeaufils
 *
 */
@NamedQueries({
	@NamedQuery (name="CompetenceAgent.selectByAgentAndEmploi",
		query = "FROM CompetenceAgent " +
			 	"WHERE agent.idfAgent = :idfAgent " +
			 	"AND emploi.idEmploi = :idfEmploi"),
	@NamedQuery (name="CompetenceAgent.selectByAgent",
			query = "FROM CompetenceAgent " +
				 	"WHERE agent.idfAgent = :idfAgent " ),
	@NamedQuery (name="CompetenceAgent.MakeListAgentForIntervalle",
			query="SELECT cpt.agent "
				+ " FROM CompetenceAgent cpt "
				+ " WHERE cpt.emploi.idEmploi = :idfEmploi"
				+ " AND (cpt.dteFin IS NULL OR cpt.dteFin >= :dateDay) "
				+ " AND cpt.dteDebut <= :dateDay "),
	@NamedQuery (name="CompetenceAgent.MakeListAgentForEchelonAndEmploi",
			query="SELECT cpt " 
					+ " FROM CompetenceAgent cpt "
					+ " JOIN cpt.agent agt"
					+ " JOIN agt.lstAffectation aff "
					+ " WHERE (cpt.emploi.idEmploi = :idfEmploi OR :idfEmploi IS NULL)"
					+ " AND aff.idfEchelon = :idfEchelon " 
					+ " AND (cpt.dteFin IS NULL OR cpt.dteFin >= :dateDay) "
					+ " AND cpt.dteDebut <= :dateDay "
					+ " AND aff.debut <= :dateDay "
					+ " AND (aff.fin IS NULL OR aff.fin >= :dateDay)")
})
@Entity
@Table (name="COMPETENCE_AGENT", schema="firesoft")
public class CompetenceAgent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5995401820149652468L;
	private Integer idfCompetenceAgent;
	private Agent agent;
	private EmploiOperationnel emploi;
	private Date dteDebut;
	private Date dteFin;
	private Activite activite;
	/**
	 * 
	 */
	public CompetenceAgent() {
	}
	/**
	 * @return the idfCompetenceAgent
	 */
	@Id
	@Column (name="IDF_COMPETENCE_AGENT")
	@GeneratedValue (strategy= GenerationType.AUTO)
	public Integer getIdfCompetenceAgent() {
		return idfCompetenceAgent;
	}
	/**
	 * @param idfCompetenceAgent the idfCompetenceAgent to set
	 */
	public void setIdfCompetenceAgent(Integer idfCompetenceAgent) {
		this.idfCompetenceAgent = idfCompetenceAgent;
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
	@ManyToOne
	@JoinColumn (name="IDF_EMPLOI")
	public EmploiOperationnel getEmploi() {
		return emploi;
	}
	public void setEmploi(EmploiOperationnel emploi) {
		this.emploi = emploi;
	}
	/**
	 * @return the dteDebut
	 */
	@Column (name="DATE_DEBUT")
	public Date getDteDebut() {
		return dteDebut;
	}
	/**
	 * @param dteDebut the dteDebut to set
	 */
	public void setDteDebut(Date dteDebut) {
		this.dteDebut = dteDebut;
	}
	/**
	 * @return the dteFin
	 */
	@Column (name="DATE_FIN")
	public Date getDteFin() {
		return dteFin;
	}
	/**
	 * @param dteFin the dteFin to set
	 */
	public void setDteFin(Date dteFin) {
		this.dteFin = dteFin;
	}
	/**
	 * @return the activite
	 */
	@Column (name="ACTIVITE")
	@Enumerated
	public Activite getActivite() {
		return activite;
	}
	/**
	 * @param activite the activite to set
	 */
	public void setActivite(Activite activite) {
		this.activite = activite;
	}

}
