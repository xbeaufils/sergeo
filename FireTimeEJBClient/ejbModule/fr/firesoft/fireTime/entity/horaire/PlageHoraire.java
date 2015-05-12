/**
 * 
 */
package fr.firesoft.fireTime.entity.horaire;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;


/**
 * @author xbeaufils
 *
 */
@NamedQueries({
	@NamedQuery (name="PlageHoraire.makeListForEchelon",
			query = "FROM PlageHoraire "+
					"WHERE cycle.idOrganization = :anIdfEchelon")
/*					,
	@NamedQuery (name="PlageHoraire.makeListForDateAndEchelon",
			query="SELECT pl FROM PlageHoraire  pl "
				+ "WHERE pl.lstStatutPeriode.lstPeriode.idfEchelon = :anIdfEchelon " 
				+ " AND pl.lst")
*/})

@Entity
@Table (name="PLAGE_HORAIRE", catalog="firesoft")
public class PlageHoraire implements Serializable {

	private Integer idfPlage;
	private String libelle;
	private CycleHoraire cycle;
	private Calendar debut;
	private Calendar fin;
	private PlageHoraire next;
	private List<BesoinEquipage> lstBesoin;
	private List<StatutPeriode> lstStatutPeriode;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7695198301718204961L;

	/**
	 * 
	 */
	public PlageHoraire() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="IDF_PLAGE")
	public Integer getIdfPlage() {
		return idfPlage;
	}

	public void setIdfPlage(Integer idfPlage) {
		this.idfPlage = idfPlage;
	}
	@Column (name="LIBELLE")
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
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
	 * @return the debut
	 */
	@Column (name="DTE_DEBUT")
	public Calendar getDebut() {
		return debut;
	}
	/**
	 * @param debut the debut to set
	 */
	public void setDebut(Calendar debut) {
		this.debut = debut;
	}
	/**
	 * @return the fin
	 */
	@Column (name="DTE_FIN")
	public Calendar getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(Calendar fin) {
		this.fin = fin;
	}

	/**
	 * @return the next
	 */
	@OneToOne
	@JoinColumn (name="IDF_NEXT_PLAGE")
	public PlageHoraire getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(PlageHoraire next) {
		this.next = next;
	}
	/**
	 * @return the lstBesoinEquipage
	 */
	@OneToMany(mappedBy="plage")
	public List<BesoinEquipage> getLstBesoinEquipage() {
		return lstBesoin;
	}
	/**
	 * @param lstBesoinEquipage the lstBesoinEquipage to set
	 */
	public void setLstBesoinEquipage(List<BesoinEquipage> lstBesoin) {
		this.lstBesoin = lstBesoin;
	}
	/**
	 * @return the lstPeriode
	 */
	@ManyToMany(targetEntity=StatutPeriode.class, fetch=FetchType.EAGER)
	@JoinTable (name="COMPOS_STATUT", schema="firesoft",
			joinColumns = @JoinColumn (name="IDF_PLAGE"),
			inverseJoinColumns = @JoinColumn (name="IDF_STATUT"))
	public List<StatutPeriode> getLstStatutPeriode() {
		return lstStatutPeriode;
	}
	/**
	 * @param lstPeriode the lstPeriode to set
	 */
	public void setLstStatutPeriode(List<StatutPeriode> lstPeriode) {
		this.lstStatutPeriode = lstPeriode;
	}

}
