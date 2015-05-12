/**
 * 
 */
package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * @author xbeaufils
 *
 */
@NamedQueries ( {
	@NamedQuery(name="Agent.makeListForOrganisation",
			query=" FROM Agent " +
					"WHERE organisation = :orga"),
	@NamedQuery(name="Agent.selectByMatriculeAndOrga",
			query="FROM Agent " 
				+ "WHERE matricule = :aMatricule" 
				+ " AND organisation = :idfEchelon")
})
@Entity
@Table ( schema="firesoft", name="AGENT" )
public class Agent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1519121127544364255L;

	private Integer idfAgent;
	private Integer matricule;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private Integer organisation;
	private List<Affectation> lstAffectation;

	/**
	 * 
	 */
	public Agent() {
	}
	/**
	 * @return the idfAgent
	 */
	@Id
	@Column (name="IDF_AGENT")
	@GeneratedValue ( strategy=GenerationType.AUTO)
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
	 * @return the matricule
	 */
	@Column (name="MATRICULE")
	public Integer getMatricule() {
		return matricule;
	}
	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}
	/**
	 * @return the nom
	 */
	@Column (name="NOM")
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
	@Column (name="PRENOM")
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
	 * @return the organisation
	 */
	@Column (name="ORGANISATION")
	public Integer getOrganisation() {
		return organisation;
	}
	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(Integer organisation) {
		this.organisation = organisation;
	}

	/**
	 * @return the dateNaissance
	 */
	@Column (name="DATE_NAISSANCE")
	public Date getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	/**
	 * @return the lstAffectation
	 */
	@OneToMany (mappedBy="agent")
	public List<Affectation> getLstAffectation() {
		return lstAffectation;
	}
	/**
	 * @param lstAffectation the lstAffectation to set
	 */
	public void setLstAffectation(List<Affectation> lstAffectation) {
		this.lstAffectation = lstAffectation;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		return idfAgent.equals( ( (Agent) arg0).getIdfAgent());
	}

}
