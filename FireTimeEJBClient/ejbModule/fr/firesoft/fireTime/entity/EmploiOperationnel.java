/**
 * 
 */
package fr.firesoft.fireTime.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author xbeaufils
 *
 */
@NamedQueries({
	@NamedQuery (name="EmploiOperationnel.selectByCodeAndLevel",
			query = "FROM EmploiOperationnel " +
				 	"WHERE uniteValeur.code = :code " +
				 	"AND niveau = :level"),
	@NamedQuery (name="EmploiOperationnel.makeListAll",
			query = "FROM EmploiOperationnel " ),
	@NamedQuery (name="EmploiOperationnel.makeListOperationnel",
			query = "FROM EmploiOperationnel ") // +
//					"WHERE categorie IN  (fr.firesoft.fireTime.entity.CategorieEmploi.LOGISTIQUE, " +
//					" fr.firesoft.fireTime.entity.CategorieEmploi.TRONC_COMMUN)")
			
})

@Entity 
@Table(name="EMPLOI_OPERATIONNEL", schema="firesoft")
public class EmploiOperationnel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3316895680975928503L;
	private Integer idEmploi;
	private UniteValeur uniteValeur;
	private String libelle;
	private Integer niveau;
	private String code;
	private List<CodeEmploiOperationnel> lstCode;
	private EmploiOperationnel pere;
	private CategorieEmploi categorie;
	/**
	 * 
	 */
	public EmploiOperationnel() {
	}
	/**
	 * @return the idEmploi
	 */
	@Id
	@Column (name="IDF_EMPLOI")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdEmploi() {
		return idEmploi;
	}
	/**
	 * @param idEmploi the idEmploi to set
	 */
	public void setIdEmploi(Integer idEmploi) {
		this.idEmploi = idEmploi;
	}
	@ManyToOne
	@JoinColumn (name="IDF_UNITE_VALEUR")
	public UniteValeur getUniteValeur() {
		return uniteValeur;
	}
	public void setUniteValeur(UniteValeur uniteValeur) {
		this.uniteValeur = uniteValeur;
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
	@Column (name="NIVEAU")
	public Integer getNiveau() {
		return niveau;
	}
	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}
	
	/**
	 * @return the code
	 */
	@Column (name="CODE")
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the lstCode
	 */
	@OneToMany (mappedBy="id.emploi")
	public List<CodeEmploiOperationnel> getLstCode() {
		return lstCode;
	}
	/**
	 * @param lstCode the lstCode to set
	 */
	public void setLstCode(List<CodeEmploiOperationnel> lstCode) {
		this.lstCode = lstCode;
	}
	@Enumerated
	@Column (name="CATEGORIE")
	public CategorieEmploi getCategorie() {
		return categorie;
	}
	public void setCategorie(CategorieEmploi categorie) {
		this.categorie = categorie;
	}
	
	@ManyToOne
	@JoinColumn(name="IDF_PERE")
	public EmploiOperationnel getPere() {
		return pere;
	}
	
	public void setPere(EmploiOperationnel pere) {
		this.pere = pere;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idEmploi == null) ? 0 : idEmploi.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmploiOperationnel other = (EmploiOperationnel) obj;
		if (idEmploi == null) {
			if (other.idEmploi != null)
				return false;
		} else if (!idEmploi.equals(other.idEmploi))
			return false;
		return true;
	}
	
}
