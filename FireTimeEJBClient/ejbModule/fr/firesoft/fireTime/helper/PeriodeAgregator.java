/**
 * 
 */
package fr.firesoft.fireTime.helper;

import java.io.Serializable;

/**
 * @author xbeaufils
 *
 */
public class PeriodeAgregator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5998219521337982078L;

	private String month;
	private Long dureeComptabilisee;
	private Long dureeEffective;
	/**
	 * 
	 */
	public PeriodeAgregator() {
	}
	
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the dureeComptabilisee
	 */
	public Long getDureeComptabilisee() {
		return dureeComptabilisee;
	}
	/**
	 * @param dureeComptabilisee the dureeComptabilisee to set
	 */
	public void setDureeComptabilisee(Long dureeComptabilisee) {
		this.dureeComptabilisee = dureeComptabilisee;
	}
	/**
	 * @return the dureeEffective
	 */
	public Long getDureeEffective() {
		return dureeEffective;
	}
	/**
	 * @param dureeEffective the dureeEffective to set
	 */
	public void setDureeEffective(Long dureeEffective) {
		this.dureeEffective = dureeEffective;
	}

}
