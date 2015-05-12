package fr.firesoft.fireTime.entity.horaire;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
//import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.Periode;
import fr.firesoft.fireTime.ext.horaire.Presence;

/**
 * @author xbeaufils
 *
 */
@NamedQueries ( {
	@NamedQuery(name="StatutPeriode.makeListAll",
			query=" FROM StatutPeriode "),
	@NamedQuery (name="StatutPeriode.makeListForCycle",
			query = "FROM StatutPeriode " 
				+ " WHERE cycle.idfCycle = :idfCycle "),
	@NamedQuery (name="StatutPeriode.makeListDispoForCycle",
	query = "FROM StatutPeriode sta" 
		+ " WHERE sta.cycle.idfCycle = :idfCycle "
		+ " AND sta.presence = fr.firesoft.fireTime.ext.horaire.Presence.DISPO")											
				
})
@Entity 
@Table ( schema="firesoft",name="STATUT")
/*@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_STATUT")
*/
public class StatutPeriode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -540291274901149029L;
	private Integer idfStatut;
	private Integer duree;
	private CycleHoraire cycle;
	private List<PlageHoraire> lstComposant;
	private Presence presence;
	private String libelle;
	private String code;
	private List<Periode> lstPeriode;
	/**
	 * 
	 */


	public StatutPeriode() {
		super();
	}
	/**
	 * @return the idfStatut
	 */
	@Id
	@Column (name="IDF_STATUT")
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	 * @return the duree
	 */
	@Column (name="DUREE")
	public Integer getDuree() {
		return duree;
	}
	/**
	 * @param duree the duree to set
	 */
	public void setDuree(Integer duree) {
		this.duree = duree;
	}
	/**
	 * @return the presence
	 */
	@Enumerated 
	@Column (name="PRESENCE")
	public Presence getPresence() {
		return presence;
	}
	/**
	 * @param presence the presence to set
	 */
	public void setPresence(Presence presence) {
		this.presence = presence;
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
	 * @return the cycle
	 */
	@ManyToOne
	@JoinColumn (name="IDF_CYCLE")
	public CycleHoraire getCycle() {
		return cycle;
	}
	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(CycleHoraire cycle) {
		this.cycle = cycle;
	}
	/**
	 * @return the lstComposant
	 */
	@ManyToMany(targetEntity=PlageHoraire.class, fetch=FetchType.EAGER)
	@JoinTable (name="COMPOS_STATUT", schema="firesoft",
			joinColumns = @JoinColumn (name="IDF_STATUT"),
			inverseJoinColumns = @JoinColumn (name="IDF_PLAGE"))
	public List<PlageHoraire> getLstComposant() {
		return lstComposant;
	}
	/**
	 * @param lstComposant the lstComposant to set
	 */
	public void setLstComposant(List<PlageHoraire> lstComposant) {
		this.lstComposant = lstComposant;
	}
	/**
	 * @return the lstPeriode
	 */
	@OneToMany (mappedBy="statut")
	public List<Periode> getLstPeriode() {
		return lstPeriode;
	}
	/**
	 * @param lstPeriode the lstPeriode to set
	 */
	public void setLstPeriode(List<Periode> lstPeriode) {
		this.lstPeriode = lstPeriode;
	}

	
}
