/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.List;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.CycleManagerLocal;
import fr.firesoft.fireTime.entity.horaire.CycleTravail;
import fr.firesoft.fireTime.entity.horaire.SequenceTravail;

/**
 * @author xbeaufils
 *
 */
public class SequenceAction extends AuthentifiedAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6528272444066643353L;

	private List<SequenceTravail> lstSequence;
	private Integer idfCycleTravail;
	private Integer idfStatut;
	private Integer ordre;
	/**
	 * 
	 */
	public SequenceAction() {
	}
	
	public String add() {
		CycleManagerLocal cycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		cycleManager.addSequence(idfCycleTravail, idfStatut);
		return SUCCESS;
	}
	
	public String remove() {
		CycleManagerLocal cycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		cycleManager.removeSequence(idfCycleTravail, ordre);
		return SUCCESS;
	}
	
	public String list() {
		CycleManagerLocal cycleManager = ServiceLocatorFireTime.getCycleManagerBean();
		CycleTravail aCycle = cycleManager.select(idfCycleTravail);
		lstSequence = aCycle.getLstSequence();
		return SUCCESS;
	}
	// ------------Getters / Setters --------
	/**
	 * @return the lstSequence
	 */
	public List<SequenceTravail> getLstSequence() {
		return lstSequence;
	}
	/**
	 * @param lstSequence the lstSequence to set
	 */
	public void setLstSequence(List<SequenceTravail> lstSequence) {
		this.lstSequence = lstSequence;
	}

	/**
	 * @return the idfCycleTravail
	 */
	public Integer getIdfCycleTravail() {
		return idfCycleTravail;
	}

	/**
	 * @param idfCycleTravail the idfCycleTravail to set
	 */
	public void setIdfCycleTravail(Integer idfCycleTravail) {
		this.idfCycleTravail = idfCycleTravail;
	}

	/**
	 * @return the idfStatut
	 */
	public Integer getIdfStatut() {
		return idfStatut;
	}

	/**
	 * @param idfStatut the idfStatut to set
	 */
	public void setIdfStatut(Integer idfStatut) {
		this.idfStatut = idfStatut;
	}

	/**
	 * @return the ordre
	 */
	public Integer getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

}
