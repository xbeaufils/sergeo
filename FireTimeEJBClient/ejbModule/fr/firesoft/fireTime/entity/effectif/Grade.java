package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table ( schema="firesoft", name="GRADE") 
// @SequenceGenerator (allocationSize=1,name="SeqGrade", sequenceName="EFFECTIF.SEQ_GRADE")
@NamedQueries (value= {
		@NamedQuery (name="Grade.selectAll",
				query="SELECT g FROM Grade g")
})
public class Grade implements Serializable {

	public final static long serialVersionUID = 1;

	private Integer id;
	private String libelle;
	private Groupe groupe;
	private Famille famille;
	private Filiere filiere; 
	private List<CodeGrade> lstCode;
	
	@Id
	@Column (name="IDF_GRADE")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column (name="LIBELLE")
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
    /**
     * @return the groupe
     */
    @Enumerated (EnumType.STRING)
    @Column (name="GROUPE")
    public Groupe getGroupe() {
            return groupe;
    }
    /**^M
     * @param groupe the groupe to set^M
     */
    @Enumerated (EnumType.STRING)
    @Column (name="FAMILLE")
    public void setGroupe(Groupe groupe) {
            this.groupe = groupe;
    }
 
	/**
	 * @return the famille
	 */
	public Famille getFamille() {
		return famille;
	}

	/**
	 * @param famille the famille to set
	 */
	public void setFamille(Famille famille) {
		this.famille = famille;
	}
	@Enumerated
	@Column (name="FILIERE")
	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
		

	/**
	 * @return the lstCode
	 */
	@OneToMany (mappedBy="id.grade")
	public List<CodeGrade> getLstCode() {
		return lstCode;
	}

	/**
	 * @param lstCode the lstCode to set
	 */
	public void setLstCode(List<CodeGrade> lstCode) {
		this.lstCode = lstCode;
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
		Grade other = (Grade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}

}
