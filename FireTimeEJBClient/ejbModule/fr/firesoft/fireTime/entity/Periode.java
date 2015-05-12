/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;

/*
 * @author xbeaufils
 *
 */
@NamedQueries ( {
	@NamedQuery(name="Periode.makeListForIntervalle",
			query=" FROM Periode "
				+ " WHERE jour >= :debut"
				+ " AND jour <= :fin"
				+ " AND idOrganization = :idfEchelon"),
	@NamedQuery(name="Periode.makeListForDateAndEchelon",
			query=" FROM Periode "
				+ " WHERE jour = :date"
				+ " AND idOrganization = :idfEchelon"),
	@NamedQuery(name="Periode.selectForAgentAndDate",
			query=" FROM Periode "
				+ " WHERE agent.idfAgent = :anIdfAgent " 
				+ " AND idOrganization = :idfEchelon "
				+ " AND jour = :aDay"),
	@NamedQuery(name="Periode.selectForAgentBetweenDate",
			query=" FROM Periode "
				+ " WHERE agent.idfAgent = :anIdfAgent"
				+ " AND idOrganization = :idfEchelon"
				+ " AND jour between :debut AND :fin"),
	@NamedQuery(name="Periode.deleteForAgentBetweenDate",
			query=" DELETE Periode "
				+ " WHERE agent.idfAgent = :anIdfAgent"
				+ " AND jour between :debut AND :fin"),
	@NamedQuery(name="Periode.calculateDureeForIntervalle",
			query = "SELECT SUM(statut.duree) FROM Periode "
				+ " WHERE jour between :debut AND :fin "
				+ " AND agent.idfAgent = :idfAgent"
				+ " GROUP BY agent.idfAgent"),
	@NamedQuery(name="Periode.makeListAgentForDateAndPlage",
			query = "SELECT per FROM Periode per "
				+ " JOIN  per.statut.lstComposant pl "
				+ " WHERE per.jour = :aDay"
				+ " AND per.idOrganization = :idfEchelon "
				+ " AND pl.idfPlage = :idfPlageHoraire")		

})
@Entity 
@Table ( catalog="firesoft", name="PERIODE" )
public class Periode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 163203693414899662L;
	private Integer idfPeriode;
	private Calendar jour;
	private Agent agent;
	private StatutPeriode statut;
	private Integer idOrganization;
//	private EchelonOrganigramme echelon;
	private List<Piquet> lstPiquet;
	/**
	 * 
	 */
	public Periode() {
	}
	@Id 
	@Column (name="IDF_PERIODE")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdfPeriode() {
		return idfPeriode;
	}

	public void setIdfPeriode(Integer idfPeriode) {
		this.idfPeriode = idfPeriode;
	}
	@Column (name="JOUR")
	public Calendar getJour() {
		return jour;
	}

	public void setJour(Calendar jour) {
		this.jour = jour;
	}
	/**
	 * @return the agent
	 */
	@OneToOne
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
	 * @return the statut
	 */
	@OneToOne
	@JoinColumn (name="IDF_STATUT")
	public StatutPeriode getStatut() {
		return statut;
	}
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(StatutPeriode statut) {
		this.statut = statut;
	}
	/**
	 * @return the idOrganization
	 */
	@Column (name="ID_ORGA")
	public Integer getIdOrganization() {
		return idOrganization;
	}

	/**
	 * @param idOrganization the idOrganization to set
	 */
	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
	}
	/**
	 * @return the echelon
	 *
	@ManyToOne
	@JoinColumn (name="IDF_ECHELON")
	public EchelonOrganigramme getEchelon() {
		return echelon;
	}
	/**
	 * @param echelon the echelon to set
	 *
	public void setEchelon(EchelonOrganigramme echelon) {
		this.echelon = echelon;
	}
	/**
	 * @return the lstPiquet
	 */
	@OneToMany (cascade = CascadeType.ALL, mappedBy="periode",fetch=FetchType.LAZY)
	public List<Piquet> getLstPiquet() {
		return lstPiquet;
	}
	/**
	 * @param lstPiquet the lstPiquet to set
	 */
	public void setLstPiquet(List<Piquet> lstPiquet) {
		this.lstPiquet = lstPiquet;
	}

}
