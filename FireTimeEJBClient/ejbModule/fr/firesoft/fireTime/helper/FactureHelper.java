/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xavier
 *
 */
public class FactureHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8143647123269117424L;
	private Integer idFacture;
	private Date dateFacturation;
	private String libelle;
	private String adresse;
	private String adresseComplementaire;
	private String codePostal;
	private String ville;
	/**
	 * 
	 */
	public FactureHelper() {
	}
	/**
	 * @return the idFacture
	 */
	public Integer getIdFacture() {
		return idFacture;
	}
	/**
	 * @param idFacture the idFacture to set
	 */
	public void setIdFacture(Integer idFacture) {
		this.idFacture = idFacture;
	}
	/**
	 * @return the dateFacturation
	 */
	public Date getDateFacturation() {
		return dateFacturation;
	}
	/**
	 * @param dateFacturation the dateFacturation to set
	 */
	public void setDateFacturation(Date dateFacturation) {
		this.dateFacturation = dateFacturation;
	}
	/**
	 * @return the libelle
	 */
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
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

}
