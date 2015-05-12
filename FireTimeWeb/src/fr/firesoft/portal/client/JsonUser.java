/**
 * 
 */
package fr.firesoft.portal.client;

/**
 * @author beaufils
 *
 */
public class JsonUser {


	private String matricule;
	private Integer userId;
	private String emailAddress;
	private String lastName;
	private String bienvenue;
	private String firstName;
	private String screenName;
	private String profil;
	private Long organizationId;
	/**
	 * 
	 */
	public JsonUser() {
	}
	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}
	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	/**
	 * @return the idUser
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setUserId(Integer idUser) {
		this.userId = idUser;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the prenom
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setFirstName(String prenom) {
		this.firstName = prenom;
	}
	/**
	 * @return the bienvenue
	 */
	public String getBienvenue() {
		return bienvenue;
	}
	/**
	 * @param bienvenue the bienvenue to set
	 */
	public void setBienvenue(String bienvenue) {
		this.bienvenue = bienvenue;
	}
	/**
	 * @return the nom
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setLastName(String nom) {
		this.lastName = nom;
	}
	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @return the profil
	 */
	public String getProfil() {
		return profil;
	}
	/**
	 * @param profil the profil to set
	 */
	public void setProfil(String profil) {
		this.profil = profil;
	}
	/**
	 * @return the organizationId
	 */
	public Long getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
