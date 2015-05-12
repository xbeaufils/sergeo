/**
 * 
 */
package fr.firesoft.fireTime.entity.customer;

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
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.EchelonOrganigramme;

/**
 * @author xavier
 *
 */
@NamedQueries ({
	@NamedQuery (name="Client.selectByEmail",
		query= " FROM Client "
			+ " WHERE email = :anEmail")
})
@Entity
@Table (name="CLIENT", schema="firesoft")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1700530018585000990L;

	private Integer idfClient;
	private String libelle;
	private String adresse;
	private String adresseComplementaire;
	private String codePostal;
	private String ville;
	private String email;
	private Date dateValidite;
	private EchelonOrganigramme echelon;
	/**
	 * 
	 */
	public Client() {
	}
	/**
	 * @return the idfClient
	 */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="IDF_CLIENT")
	public Integer getIdfClient() {
		return idfClient;
	}
	/**
	 * @param idfClient the idfClient to set
	 */
	public void setIdfClient(Integer idfClient) {
		this.idfClient = idfClient;
	}
	/**
	 * @return the libelle
	 */
	@Column (name="NOM")
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
	 * @return the adresse
	 */
	@Column (name="ADRESSE")
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the adresseComplementaire
	 */
	@Column (name="ADRESSE_COMP")
	public String getAdresseComplementaire() {
		return adresseComplementaire;
	}
	/**
	 * @param adresseComplementaire the adresseComplementaire to set
	 */
	public void setAdresseComplementaire(String adresseComplementaire) {
		this.adresseComplementaire = adresseComplementaire;
	}
	/**
	 * @return the codePostal
	 */
	@Column (name="CODE_POSTAL")
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * @return the ville
	 */
	@Column (name="VILLE")
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the email
	 */
	@Column (name="EMAIL")
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the echelon
	 */
	@ManyToOne
	@JoinColumn (name="IDF_ECHELON")
	public EchelonOrganigramme getEchelon() {
		return echelon;
	}
	/**
	 * @param echelon the echelon to set
	 */
	public void setEchelon(EchelonOrganigramme echelon) {
		this.echelon = echelon;
	}
	/**
	 * @return the dateValidite
	 */
	@Column (name="DATE_VALIDITE")
	public Date getDateValidite() {
		return dateValidite;
	}
	/**
	 * @param dateValidite the dateValidite to set
	 */
	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
	}

}
