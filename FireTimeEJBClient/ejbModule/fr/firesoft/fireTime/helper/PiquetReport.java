/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xbeaufils
 *
 */
public class PiquetReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5920395220819789678L;
	
	private Date datePiquet; 
	private String libellePlage;
	private String libelleBesoin;
	private String libelleEmploi;
	private Integer affichageEmploi;
	private String nomPrenom; 
	/**
	 * 
	 */
	public PiquetReport() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the datePiquet
	 */
	public Date getDatePiquet() {
		return datePiquet;
	}
	/**
	 * @param datePiquet the datePiquet to set
	 */
	public void setDatePiquet(Date datePiquet) {
		this.datePiquet = datePiquet;
	}
	/**
	 * @return the libellePlage
	 */
	public String getLibellePlage() {
		return libellePlage;
	}
	/**
	 * @param libellePlage the libellePlage to set
	 */
	public void setLibellePlage(String libellePlage) {
		this.libellePlage = libellePlage;
	}
	/**
	 * @return the libelleBesoin
	 */
	public String getLibelleBesoin() {
		return libelleBesoin;
	}
	/**
	 * @param libelleBesoin the libelleBesoin to set
	 */
	public void setLibelleBesoin(String libelleBesoin) {
		this.libelleBesoin = libelleBesoin;
	}
	/**
	 * @return the libelleEmploi
	 */
	public String getLibelleEmploi() {
		return libelleEmploi;
	}
	/**
	 * @param libelleEmploi the libelleEmploi to set
	 */
	public void setLibelleEmploi(String libelleEmploi) {
		this.libelleEmploi = libelleEmploi;
	}
	/**
	 * @return the affichageEmploi
	 */
	public Integer getAffichageEmploi() {
		return affichageEmploi;
	}
	/**
	 * @param affichageEmploi the affichageEmploi to set
	 */
	public void setAffichageEmploi(Integer affichageEmploi) {
		this.affichageEmploi = affichageEmploi;
	}
	/**
	 * @return the nomPrenom
	 */
	public String getNomPrenom() {
		return nomPrenom;
	}
	/**
	 * @param nomPrenom the nomPrenom to set
	 */
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}
	
}
