package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (schema="firesoft", name="INTERVALLE")
@SequenceGenerator (allocationSize=1,name="SeqIntervalle", sequenceName="EFFECTIF.SEQ_INTERVALLE")
@NamedQueries ( {
	@NamedQuery (name="Intervalle.makeListIntervalle",
			query="FROM Intervalle WHERE agent.idfAgent = :aCodAgt "+
				"AND ligneVie = :ligne"),
	@NamedQuery (name="Intervalle.firstIntervalle",
			query="FROM Intervalle int " +
			" WHERE int.prior IS NULL " +
			" AND int.agent.idfAgent = :aCodagt " +
			" AND ligneVie = :ligne "),
	@NamedQuery (name="Intervalle.listAffectationByNom",
			query="SELECT int.affectation FROM Intervalle int " 
				+ " WHERE int.dateDebut <= :datePresente " 
				+ " AND (int.dateFin >= :datePresente OR int.dateFin IS NULL)" 
				+ " AND int.agent.nom LIKE :name"),
	@NamedQuery (name="Intervalle.listAffectationFutur",
			query="select int.affectation FROM Intervalle int "
				+ " WHERE int.prior IS NULL " 
				+ " AND int.dateDebut >= :datePresente"),
	@NamedQuery (name="Intervalle.listAffectationFuturByNom",
			query="select int.affectation FROM Intervalle int "
				+ " WHERE int.prior IS NULL " 
				+ " AND int.dateDebut >= :datePresente" 
				+ " AND int.agent.nom LIKE :name"),
	@NamedQuery (name="Intervalle.listAffectationInactifByNom",
			query="select int.affectation FROM Intervalle int "
				+ " WHERE int.next IS NULL " 
				+ " AND int.dateFin IS NOT NULL"
				+ " AND int.agent.nom LIKE :name"),
	@NamedQuery (name="Intervalle.listAffectationForDateAndCodagt",
			query="SELECT int.affectation FROM Intervalle int "
				+ " WHERE int.dateDebut <= :datePresente " 
				+ " AND (int.dateFin >= :datePresente OR int.dateFin IS NULL)" 
				+ " AND int.agent.idfAgent = :codagt"),
	@NamedQuery(name="Intervalle.listActifForListCodagt",
			query="SELECT int.affectation FROM Intervalle int "
				+ " WHERE int.dateDebut <= :datePresente " 
				+ " AND (int.dateFin >= :datePresente OR int.dateFin IS NULL)" 
				+ " AND int.agent.idfAgent in (:lstIdfAgent)"),
	@NamedQuery (name="Intervalle.listInactifForListCodagt",
			query="select int.affectation FROM Intervalle int "
				+ " WHERE int.next IS NULL " 
				+ " AND int.dateFin IS NOT NULL"
				+ " AND int.agent.idfAgent in (:lstIdfAgent)"),
	@NamedQuery (name="Intervalle.listFuturForListCodagt",
			query="select int.affectation FROM Intervalle int "
				+ " WHERE int.prior IS NULL " 
				+ " AND int.dateDebut >= :datePresente" 
				+ " AND int.agent.idfAgent in (:lstIdfAgent)"),
	@NamedQuery( name="Intervalle.listIntervalleBetweenDate",
			query="FROM Intervalle WHERE agent.idfAgent = :aCodAgt "
				+ " AND ligneVie = :ligne " 
				+ " AND "
				// -------|===========|---------
				//-----|==================|-----
				+ "    (dateDebut <= :debut AND ( dateFin IS NULL OR dateFin >= :fin )"
				// -------|===========|---------
				//-----|=========|--------------
				+ " OR (dateDebut <= :debut AND ( dateFin IS NULL OR dateFin <= :fin ))"
				// --------|==========|----------
				// -----------|====|-------------
				// --------|=====================
				+ " OR (dateDebut >= :debut AND ( dateFin IS NULL OR dateFin <= :fin )) )"),
		@NamedQuery( name="Intervalle.listNoEndingIntervalle",
				query="FROM Intervalle WHERE agent.idfAgent = :aCodAgt "
					+ " AND ligneVie = :ligne " 
					+ " AND "
					// --------|==========|----------
					// -----------|====|-------------
					// --------|=====================
					+ " dateDebut = :debut AND dateFin IS NULL")

	})
public class Intervalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idIntervalle;
	private Agent agent;
	private Date dateDebut;
	private Date dateFin;
	private Affectation affectation;
	private Intervalle prior;
	private Intervalle next;
	private String ligneVie;
	
	public Intervalle() {
		super();
	}

	public Intervalle(String ligneVie) {
		this.ligneVie = ligneVie;
	}
	@Id
	@GeneratedValue ( strategy=GenerationType.AUTO)
	@Column (name="IDF_INTERVALLE")
	public Integer getIdIntervalle() {
		return idIntervalle;
	}

	public void setIdIntervalle(Integer idIntervalle) {
		this.idIntervalle = idIntervalle;
	}

	@Column (name="DATE_DEBUT")
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Column (name="DATE_FIN")
	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@OneToOne
	@JoinColumn(name="IDF_AFFECT_RECONST")
	public Affectation getAffectation() {
		return affectation;
	}

	public void setAffectation(Affectation affectation) {
		this.affectation = affectation;
	}
	@ManyToOne
	@JoinColumn (name="IDF_AGENT", referencedColumnName="IDF_AGENT")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@ManyToOne
	@JoinColumn (name="IDF_INTERVALLE_PRIOR")
	public Intervalle getPrior() {
		return prior;
	}

	public void setPrior(Intervalle prior) {
		this.prior = prior;
	}

	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn (name="IDF_INTERVALLE_NEXT")
	public Intervalle getNext() {
		return next;
	}

	public void setNext(Intervalle next) {
		this.next = next;
	}

	@Column (name="LIGNE_VIE")
	public String getLigneVie() {
		return ligneVie;
	}

	public void setLigneVie(String ligneVie) {
		this.ligneVie = ligneVie;
	}

	
	
}
