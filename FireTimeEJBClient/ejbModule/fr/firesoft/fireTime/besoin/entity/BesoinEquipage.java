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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;


/**
 * @author xbeaufils
 *
 */
@NamedQueries ({
	@NamedQuery (name="BesoinEquipage.makeListForPlage",
			query = "FROM BesoinEquipage " +
					"WHERE plage.idfPlage = :idfPlage")
//	@NamedQuery (name="BesoinEquipage.makeListForPlage",
//			query = "FROM BesoinEquipage bes " +
//					"JOIN bes.equipage equ " +
//					"LEFT JOIN equ.lstPoste pos " +
//					"LEFT JOIN pos. "
//					"WHERE bes.plage.idfPlage = :idfPlage " +
//					"AND equipage")
})
@Entity
@Table (catalog="firesoft" , name="BESOIN_EQUIPAGE")
public class BesoinEquipage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6421579692841096448L;
	private Integer idfBesoin;
	private String libelle;
	private Equipage equipage;
	//private EchelonOrganigramme echelon;
	//private CycleHoraire cycle;
	private PlageHoraire plage;
	private boolean secondaire;
	/**
	 * 
	 */
	public BesoinEquipage() {
	}
	/**
	 * @return the idfBesoin
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="IDF_BESOIN")
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
	 * @return the libelle
	 */
	@Column (name="LIBELLE")
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
	 * @return the equipage
	 */
	@ManyToOne
	@JoinColumn (name="IDF_EQUIPAGE")
	public Equipage getEquipage() {
		return equipage;
	}
	/**
	 * @param equipage the equipage to set
	 */
	public void setEquipage(Equipage equipage) {
		this.equipage = equipage;
	}
	/**
	 * @return the echelon
	 *
	@ManyToOne
	@JoinColumn (name="IDF_ECHELON")
	public EchelonOrganigramme getEchelon() {
		return echelon;
	}
	/**
	 * @param echelon the echelon to set
	 *
	public void setEchelon(EchelonOrganigramme echelon) {
		this.echelon = echelon;
	}
	/**
	 * @return the cycle
	 *
	@ManyToOne
	@JoinColumn (name="IDF_CYCLE")
	public CycleHoraire getCycle() {
		return cycle;
	}
	/**
	 * @param cycle the cycle to set
	 *
	public void setCycle(CycleHoraire cycle) {
		this.cycle = cycle;
	}
	/**
	 * @return the plage
	 */
	@ManyToOne
	@JoinColumn (name="IDF_PLAGE")
	public PlageHoraire getPlage() {
		return plage;
	}
	/**
	 * @param plage the plage to set
	 */
	public void setPlage(PlageHoraire plage) {
		this.plage = plage;
	}
	/**
	 * @return the secondaire
	 */
	@Column (name="SECONDAIRE")
	public boolean isSecondaire() {
		return secondaire;
	}
	/**
	 * @param secondaire the secondaire to set
	 */
	public void setSecondaire(boolean secondaire) {
		this.secondaire = secondaire;
	}

}
