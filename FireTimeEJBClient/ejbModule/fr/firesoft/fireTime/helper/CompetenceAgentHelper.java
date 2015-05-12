package fr.firesoft.fireTime.helper;

import java.io.Serializable;
import java.util.Date;

public class CompetenceAgentHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4164058208873714762L;
	private Integer idfCompetenceAgent;
	private Integer idfAgent;
	private Date debut;
	private Date fin;
	private Integer idfEmploiOpe;
	private String emploi;
	
	public CompetenceAgentHelper() {
		super();
	}

	/**
	 * @return the idfCompetenceAgent
	 */
	public Integer getIdfCompetenceAgent() {
		return idfCompetenceAgent;
	}

	/**
	 * @param idfCompetenceAgent the idfCompetenceAgent to set
	 */
	public void setIdfCompetenceAgent(Integer idfCompetenceAgent) {
		this.idfCompetenceAgent = idfCompetenceAgent;
	}

	/**
	 * @return the idfAgent
	 */
	public Integer getIdfAgent() {
		return idfAgent;
	}

	/**
	 * @param idfAgent the idfAgent to set
	 */
	public void setIdfAgent(Integer idfAgent) {
		this.idfAgent = idfAgent;
	}

	/**
	 * @return the debut
	 */
	public Date getDebut() {
		return debut;
	}

	/**
	 * @param debut the debut to set
	 */
	public void setDebut(Date debut) {
		this.debut = debut;
	}

	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * @return the idfEmploiOpe
	 */
	public Integer getIdfEmploiOpe() {
		return idfEmploiOpe;
	}

	/**
	 * @param idfEmploiOpe the idfEmploiOpe to set
	 */
	public void setIdfEmploiOpe(Integer idfEmploiOpe) {
		this.idfEmploiOpe = idfEmploiOpe;
	}

	/**
	 * @return the emploi
	 */
	public String getEmploi() {
		return emploi;
	}

	/**
	 * @param emploi the emploi to set
	 */
	public void setEmploi(String emploi) {
		this.emploi = emploi;
	}

}
