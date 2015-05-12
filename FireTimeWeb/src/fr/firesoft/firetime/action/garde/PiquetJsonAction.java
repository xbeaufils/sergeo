package fr.firesoft.firetime.action.garde;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.GardeManagerLocal;
import fr.firesoft.firetime.action.AuthentifiedAction;

public class PiquetJsonAction extends AuthentifiedAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7639182701266029820L;
	Logger log = LoggerFactory.getLogger(PiquetJsonAction.class);

	private Integer idfPeriode;
	private Integer idfPoste;
	private Integer idfBesoin;
	private Integer idfPlage;
	private Integer idfPiquet;
	
	public PiquetJsonAction() {
		super();
	}


	public String affecte() {
		log.debug("Affecte to save periode {} plage {} poste {} besoin {}", 
				new Object[]{idfPeriode, idfPlage, idfPoste, idfBesoin});
		GardeManagerLocal aManager = ServiceLocatorFireTime.getGardeManagerBean();
		aManager.affecte(idfPeriode, idfPlage, idfPoste, idfBesoin);
		return SUCCESS;
	}
	
	public String delete() {
		GardeManagerLocal aManager = ServiceLocatorFireTime.getGardeManagerBean();
		aManager.delete(idfPiquet);
		return SUCCESS;
	}
	
	/**
	 * @return the idfPeriode
	 */
	public Integer getIdfPeriode() {
		return idfPeriode;
	}

	/**
	 * @param idfPeriode the idfPeriode to set
	 */
	public void setIdfPeriode(Integer idfPeriode) {
		this.idfPeriode = idfPeriode;
	}

	/**
	 * @return the idfPoste
	 */
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
	 * @return the idfBesoin
	 */
	public Integer getIdfBesoin() {
		return idfBesoin;
	}

	/**
	 * @param idfBesoin the idfBesoin to set
	 */
	public void setIdfBesoin(Integer idfBesoin) {
		this.idfBesoin = idfBesoin;
	}

	/**
	 * @return the idfPlage
	 */
	public Integer getIdfPlage() {
		return idfPlage;
	}

	/**
	 * @param idfPlage the idfPlage to set
	 */
	public void setIdfPlage(Integer idfPlage) {
		this.idfPlage = idfPlage;
	}


	/**
	 * @return the idfPiquet
	 */
	public Integer getIdfPiquet() {
		return idfPiquet;
	}


	/**
	 * @param idfPiquet the idfPiquet to set
	 */
	public void setIdfPiquet(Integer idfPiquet) {
		this.idfPiquet = idfPiquet;
	}

	
}
