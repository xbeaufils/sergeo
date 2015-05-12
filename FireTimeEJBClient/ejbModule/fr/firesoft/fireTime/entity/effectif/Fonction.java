package fr.firesoft.fireTime.entity.effectif;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@NamedQueries (value= {
		@NamedQuery (name="Fonction.selectByCode",
				query="FROM Fonction WHERE code = :aCode")
})
@Entity
@Table ( schema="firesoft", name="FONCTION") 
public class Fonction implements Serializable {

	public final static long serialVersionUID = 1;
	
	private Integer id;
	private String code;
	private String libelle;

	@Id
	@Column (name="IDF_FONCTION")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column (name="LIBELLE")
	public String getLibelle() {
		return libelle;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@Transient
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.id.equals( ((Fonction)obj).getId());
	}

}
