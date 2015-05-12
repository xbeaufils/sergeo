/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.effectif.Agent;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;

/**
 * @author beaufils
 *
 */
@NamedQueries (
		value ={
		@NamedQuery (name="Disponibilite.selectForAgentBetweenDate",
				query="FROM Disponibilite disp " +
						"WHERE disp.agent.idfAgent = :idfAgent " +
						"AND disp.idOrganization = :idfEchelon " +
						"AND disp.jour between :debut AND :fin"),
		@NamedQuery (name="Disponibilite.selectForAgentAndDate",
				query ="FROM Disponibilite disp " +
						"WHERE disp.agent.idfAgent = :idfAgent " +
						"AND disp.idOrganization = :idfEchelon " +
						"AND disp.plage.idfPlage = :idfPlage " +
						"AND disp.jour = :jour"),
		@NamedQuery (name="Disponibilite.selectForEchelonBetweenDate",
				query="FROM Disponibilite disp " +
						"WHERE disp.idOrganization = :idfEchelon " +
						"AND disp.jour between :debut AND :fin"),
	
})
@Entity 
@Table ( catalog="firesoft", name="DISPO" )
public class Disponibilite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8475781656100327605L;
	/**
	 * 
	 */
	private Integer idfDispo;
	private Calendar jour;
	private PlageHoraire plage;
	private Agent agent;
	private Integer idOrganization;

	public Disponibilite() {
	}

	/**
	 * @return the idfPeriode
	 */
	@Id 
	@Column (name="IDF_DISPO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdfDispo() {
		return idfDispo;
	}

	/**
	 * @param idfPeriode the idfPeriode to set
	 */
	public void setIdfDispo(Integer idfDispo) {
		this.idfDispo = idfDispo;
	}

	/**
	 * @return the jour
	 */
	@Column (name="JOUR")
	public Calendar getJour() {
		return jour;
	}

	/**
	 * @param jour the jour to set
	 */
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
	 * @return the plage
	 */
	@OneToOne
	@JoinColumn (name="IDF_PLAGE")	
	public PlageHoraire getPlage() {
		return plage;
	}

	/**
	 * @param plage the plage to set
	 */
	public void setPlage(PlageHoraire plage) {
		this.plage = plage;
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

}
