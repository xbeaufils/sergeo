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
import javax.persistence.Table;


/**
 * @author xavier
 *
 */
@Entity
@Table (name="FACTURE", schema="firesoft")
public class Facture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8298378222071476370L;

	private Integer idFacture;
	private Date dateFacturation;
	private Date datePaiement;
	private Client client;
	/**
	 * 
	 */
	public Facture() {
	}
	/**
	 * @return the idFacture
	 */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="IDF_ECHELON")
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
	@Column (name="DATE_FACTURATION")
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
	 * @return the datePaiement
	 */
	@Column (name="DATE_PAIEMENT")
	public Date getDatePaiement() {
		return datePaiement;
	}
	/**
	 * @param datePaiement the datePaiement to set
	 */
	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}
	/**
	 * @return the client
	 */
	@ManyToOne
	@JoinColumn (name="IDF_CLIENT")
	public Client getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	
}
