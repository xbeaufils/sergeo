/**
 * 
 */
package fr.firesoft.firetime.action;


import com.opensymphony.xwork2.ActionSupport;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AdminLocal;

/**
 * @author xavier
 *
 */
public class InscriptionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -536802377015017413L;

	private String email;
	private String centre;
	private String adresse;
	private String adresseComplementaire;
	private String codePostal;
	private String ville;
	private String organisationName;
	/**
	 * 
	 */
	public InscriptionAction() {
	}

	public String execute() {
		System.out.println("InscriptionAction");
		AdminLocal admin = ServiceLocatorFireTime.getAdminBean();
		admin.createClient(email, centre, organisationName, adresse, adresseComplementaire, codePostal, ville);
		return SUCCESS;
	}

	// ----- Getters/setters -------
	/**
	 * @return the email
	 */
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
	 * @return the centre
	 */
	public String getCentre() {
		return centre;
	}

	/**
	 * @param centre the centre to set
	 */
	public void setCentre(String centre) {
		this.centre = centre;
	}

	/**
	 * @return the organisationName
	 */
	public String getOrganisationName() {
		return organisationName;
	}

	/**
	 * @param organisationName the organisationName to set
	 */
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
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
