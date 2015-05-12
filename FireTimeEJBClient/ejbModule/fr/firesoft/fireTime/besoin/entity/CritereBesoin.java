/**
 * 
 */
package fr.firesoft.fireTime.besoin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.EmploiOperationnel;


/**
 * @author xavier
 *
 */
@Entity
@Table ( schema="firesoft" , name="CRITERE")
public class CritereBesoin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7695198301718204961L;

	private Integer idfCritere;
	private EmploiOperationnel emploi;
	private Integer nombre;
	private Besoin besoin;
	/**
	 * 
	 */
	public CritereBesoin() {
	}
	/**
	 * @return the idfCritere
	 */	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="IDF_CRITERE")
	public Integer getIdfCritere() {
		return idfCritere;
	}
	/**
	 * @param idfCritere the idfCritere to set
	 */
	public void setIdfCritere(Integer idfCritere) {
		this.idfCritere = idfCritere;
	}
	/**
	 * @return the emploi
	 */
	@ManyToOne 
	@JoinColumn (name="IDF_EMPLOI")
	public EmploiOperationnel getEmploi() {
		return emploi;
	}
	/**
	 * @param emploi the emploi to set
	 */
	public void setEmploi(EmploiOperationnel emploi) {
		this.emploi = emploi;
	}
	/**
	 * @return the besoin
	 */
	@ManyToOne
	@JoinColumn (name="IDF_BESOIN")
	public Besoin getBesoin() {
		return besoin;
	}
	/**
	 * @param besoin the besoin to set
	 */
	public void setBesoin(Besoin besoin) {
		this.besoin = besoin;
	}
	/**
	 * @return the nombre
	 */
	public Integer getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}

}
