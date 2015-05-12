/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@NamedQueries ({
	@NamedQuery (name="EchelonOrganigramme.selectByCode",
			query="FROM EchelonOrganigramme " 
				+ "WHERE code = :aCode"),
	@NamedQuery (name="EchelonOrganigramme.selectByName",
			query="FROM EchelonOrganigramme " 
				+ "WHERE libelle = :aName"),
	@NamedQuery (name="EchelonOrganigramme.makeListByOrganisation",
				query="FROM EchelonOrganigramme " 
					+ "WHERE organisation.nom = :orgaName")

})

@Entity
@Table (name="ECHELON", schema="firesoft" )
public class EchelonOrganigramme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5394040341397188609L;
	private Integer idfEchelon;
	private String libelle;
	private EchelonOrganigramme superieur;
	private String code;
	private Organisation organisation;
	private List<EchelonOrganigramme> lstSubalterne;
	/**
	 * 
	 */
	public EchelonOrganigramme() {
	}
	/**
	 * @return the idfEchelon
	 */
	@Id
	@Column (name="IDF_ECHELON")
	@GeneratedValue (strategy=GenerationType.AUTO)
	public Integer getIdfEchelon() {
		return idfEchelon;
	}
	/**
	 * @param idfEchelon the idfEchelon to set
	 */
	public void setIdfEchelon(Integer idfEchelon) {
		this.idfEchelon = idfEchelon;
	}
	/**
	 * @return the libelle
	 */
	@Column (name="LIBELLE")
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * @return the organisation
	 */
	@ManyToOne
	@JoinColumn (name="ORGANISATION")
	public Organisation getOrganisation() {
		return organisation;
	}
	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	/**
	 * @return the superieur
	 */
	
	@ManyToOne
	@JoinColumn (name="IDF_ECHELON_SUP")
	public EchelonOrganigramme getSuperieur() {
		return superieur;
	}
	/**
	 * @param superieur the superieur to set
	 */
	public void setSuperieur(EchelonOrganigramme superieur) {
		this.superieur = superieur;
	}
	/**
	 * @return the lstSubalterne
	 */
	@OneToMany (mappedBy="superieur")
	public List<EchelonOrganigramme> getLstSubalterne() {
		return lstSubalterne;
	}
	/**
	 * @param lstSubalterne the lstSubalterne to set
	 */
	public void setLstSubalterne(List<EchelonOrganigramme> lstSubalterne) {
		this.lstSubalterne = lstSubalterne;
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
