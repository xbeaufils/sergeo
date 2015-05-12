/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author xbeaufils
 *
 */
@NamedQueries({
	@NamedQuery (name="Poste.makeListForEquipage",
			query =	"FROM Poste " + 
					"WHERE equipage.idfEquipage = :anIdf " +
					"ORDER BY affichage"),
	@NamedQuery (name="Poste.makeListForEchelon",
	query =	"FROM Poste " + 
			"WHERE equipage.idOrganization = :idfEchelon " +
			"ORDER BY equipage.libelle, affichage")
})
@Entity
@Table (schema="firesoft", name="POSTE")
public class Poste implements Serializable{

	private Integer idfPoste; 
	private Equipage equipage;
	private EmploiOperationnel emploi;
	private Integer affichage;
	private boolean optionnel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2410396847403395061L;

	/**
	 * 
	 */
	public Poste() {
	}
	/**
	 * @return the idfPoste
	 */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="IDF_POSTE")
	public Integer getIdfPoste() {
		return idfPoste;
	}
	/**
	 * @param idfPoste the idfPoste to set
	 */
	public void setIdfPoste(Integer idfPoste) {
		this.idfPoste = idfPoste;
	}
	/**
	 * @return the equipage
	 */
	@ManyToOne
	@JoinColumn (name="IDF_EQUIPAGE")
	public Equipage getEquipage() {
		return equipage;
	}
	/**
	 * @param equipage the equipage to set
	 */
	public void setEquipage(Equipage equipage) {
		this.equipage = equipage;
	}
	@ManyToOne
	@JoinColumn (name="IDF_EMPLOI")
	public EmploiOperationnel getEmploi() {
		return emploi;
	}

	public void setEmploi(EmploiOperationnel emploi) {
		this.emploi = emploi;
	}
	@Column (name="AFFICHAGE")
	public Integer getAffichage() {
		return affichage;
	}

	public void setAffichage(Integer affichage) {
		this.affichage = affichage;
	}
	@Column (name="OPTIONNEL")
	public boolean isOptionnel() {
		return optionnel;
	}
	public void setOptionnel(boolean optionnel) {
		this.optionnel = optionnel;
	}

	
}
