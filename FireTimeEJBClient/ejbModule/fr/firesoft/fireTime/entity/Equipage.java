/**
 * 
 */
package fr.firesoft.fireTime.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@NamedQueries({
		@NamedQuery (name="Equipage.makeListFofEchelon",
				query = "FROM Equipage " 
					+ "WHERE idOrganization = :idfEchelon")
	})

@Entity
@Table (schema="firesoft", name="EQUIPAGE")
public class Equipage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6942592917198617437L;
	private Integer idfEquipage;
	private String libelle;
	private Integer idOrganization;
	//private EchelonOrganigramme echelon;
	private List<Poste> lstPoste;
	/**
	 * 
	 */
	public Equipage() {
	}

	/**
	 * @return the idfEquipage
	 */
	@Id
	@Column (name="IDF_EQUIPAGE")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdfEquipage() {
		return idfEquipage;
	}

	/**
	 * @param idfEquipage the idfEquipage to set
	 */
	public void setIdfEquipage(Integer idfEquipage) {
		this.idfEquipage = idfEquipage;
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
	 * @return the idOrganization
	 */
	@Column (name="ID_ORGA")
	public Integer getIdOrganization() {
		return idOrganization;
	}

	/**
	 * @param idOrganization the idOrganization to set
	 */
	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
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
	 * @return the lstPoste
	 */
	@OneToMany (mappedBy="equipage")
	public List<Poste> getLstPoste() {
		return lstPoste;
	}

	/**
	 * @param lstPoste the lstPoste to set
	 */
	public void setLstPoste(List<Poste> lstPoste) {
		this.lstPoste = lstPoste;
	}

}
