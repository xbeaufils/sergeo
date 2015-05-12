/**
 * 
 */
package fr.firesoft.fireTime.entity;

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

import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;

/**
 * @author xbeaufils
 *
 */
@NamedQueries({
	@NamedQuery (name="Piquet.makeListForEquipage",
			query = "FROM Piquet " 
				+ "WHERE besoin.equipage.idfEquipage = :idfEquipage "),
	@NamedQuery (name="Piquet.makeListForBesoin",
			query ="FROM Piquet " 
				+ " WHERE besoin.idfBesoin = :idfBesoin "
				+ " AND plage.idfPlage = :idfPlage "
				+ " AND periode.jour = :date"	),
	@NamedQuery (name="Piquet.makeListForPoste",
			query ="FROM Piquet " 
				+ " WHERE besoin.idfBesoin = :idfBesoin "
				+ " AND plage.idfPlage = :idfPlage "
				+ " AND poste.idfPoste = :idfPoste "
				+ " AND periode.jour = :date"	),
	@NamedQuery (name="Piquet.makeListForDay",
		query = "FROM Piquet " 
			+ "WHERE periode.jour = :date "
			+ "AND periode.idOrganization =:idfEchelon ")
})
@Entity
@Table (schema="firesoft", name="PIQUET")
public class Piquet implements Serializable {

	private Integer idfPiquet;
	private Periode periode;
	private Poste poste;
	private PlageHoraire plage;
	private BesoinEquipage besoin;
	/**
	 * 
	 */
	private static final long serialVersionUID = 662286368184168564L;

	/**
	 * 
	 */
	public Piquet() {
	}
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="IDF_PIQUET")
	public Integer getIdfPiquet() {
		return idfPiquet;
	}

	public void setIdfPiquet(Integer idfPiquet) {
		this.idfPiquet = idfPiquet;
	}

	@ManyToOne
	@JoinColumn (name="IDF_PERIODE")
	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	@ManyToOne
	@JoinColumn (name="IDF_POSTE")
	public Poste getPoste() {
		return poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}
	/**
	 * @return the besoin
	 */
	@ManyToOne
	@JoinColumn(name="IDF_BESOIN")
	public BesoinEquipage getBesoin() {
		return besoin;
	}
	/**
	 * @param besoin the besoin to set
	 */
	public void setBesoin(BesoinEquipage besoin) {
		this.besoin = besoin;
	}

	/**
	 * @return the plage
	 */
	@ManyToOne
	@JoinColumn(name="IDF_PLAGE")
	public PlageHoraire getPlage() {
		return plage;
	}
	/**
	 * @param plage the plage to set
	 */
	public void setPlage(PlageHoraire plage) {
		this.plage = plage;
	}
}
