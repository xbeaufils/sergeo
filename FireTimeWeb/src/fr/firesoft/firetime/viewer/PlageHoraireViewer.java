/**
 * 
 */
package fr.firesoft.firetime.viewer;

import java.io.Serializable;
import java.text.DecimalFormat;



/**
 * @author xbeaufils
 *
 */
public class PlageHoraireViewer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4571897164955731950L;
	private String idViewer;
	private Integer idfPlage;
	private String libelle;
	private Integer heureDebut;
	private Integer minuteDebut;
	private Integer heureFin;
	private Integer minuteFin;
	
	//private Interval interval;
	
	private Intervalle interval;
	private Intervalle extremeIntervalle;
	
	private StatutPlage statut;
	//private boolean paddingInterval;
	private boolean finishing; 
	
	/*
	private Integer minHour;
	private Integer minMinute;
	
	private Integer maxHour;
	private Integer maxMinute;
	*/
	private PlageHoraireViewer previous;
	private PlageHoraireViewer next;
	/**
	 * 
	 */
	public PlageHoraireViewer() {
		//paddingInterval = false;
	}
	/**
	 * @return the idfPlage
	 */
	public Integer getIdfPlage() {
		return idfPlage;
	}
	/**
	 * @param idfPlage the idfPlage to set
	 */
	public void setIdfPlage(Integer idfPlage) {
		this.idfPlage = idfPlage;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Integer getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Integer heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Integer getMinuteDebut() {
		return minuteDebut;
	}
	public void setMinuteDebut(Integer minuteDebut) {
		this.minuteDebut = minuteDebut;
	}
	public Integer getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Integer heureFin) {
		this.heureFin = heureFin;
	}
	public Integer getMinuteFin() {
		return minuteFin;
	}
	public void setMinuteFin(Integer minuteFin) {
		this.minuteFin = minuteFin;
	}
	public String getFormatedDebut() {
		DecimalFormat format = new DecimalFormat("00");
		return format.format(heureDebut) + ":" + format.format(minuteDebut);
	}
	
	public String getFormatedFin() {
		DecimalFormat format = new DecimalFormat("00");
		return format.format(heureFin) + ":" + format.format(minuteFin);
	}
	/**
	 * @return the paddingInterval
	 *
	public boolean isPaddingInterval() {
		return paddingInterval;
	}
	/**
	 * @param paddingInterval the paddingInterval to set
	 *
	public void setPaddingInterval(boolean paddingInterval) {
		this.paddingInterval = paddingInterval;
	}
	*/
	/**
	 * @return the statut
	 */
	public StatutPlage getStatut() {
		return statut;
	}
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(StatutPlage statut) {
		this.statut = statut;
	}
	
	/**
	 * @return the finishing
	 */
	public boolean isFinishing() {
		return finishing;
	}
	/**
	 * @param finishing the finishing to set
	 */
	public void setFinishing(boolean finishing) {
		this.finishing = finishing;
	}
	
	public Integer getMin() {
	if (this.extremeIntervalle != null)
		return this.extremeIntervalle.getDebut().getHeure() * 6 + this.extremeIntervalle.getDebut().getMinute() / 10 ;
	else
		return 0;
	}
	
	public Integer getMax() {
		if (this.extremeIntervalle != null)
			return this.extremeIntervalle.getFin().getHeure() * 6 + this.extremeIntervalle.getFin().getMinute() / 10 ;
		else
			return 24 *6 ;
	}
	/**
	 * @return the idViewer
	 */
	public String getIdViewer() {
		return idViewer;
	}
	/**
	 * @param idViewer the idViewer to set
	 */
	public void setIdViewer(String idViewer) {
		this.idViewer = idViewer;
	}
	/**
	 * @return the previous
	 */
	public PlageHoraireViewer getPrevious() {
		return previous;
	}
	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(PlageHoraireViewer previous) {
		this.previous = previous;
	}
	/**
	 * @return the next
	 */
	public PlageHoraireViewer getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(PlageHoraireViewer next) {
		this.next = next;
	}
	/**
	 * @return the minHour
	 *
	public Integer getMinHour() {
		return minHour;
	}
	/**
	 * @param minHour the minHour to set
	 *
	public void setMinHour(Integer minHour) {
		this.minHour = minHour;
	}
	/**
	 * @return the minMinute
	 *
	public Integer getMinMinute() {
		return minMinute;
	}
	/**
	 * @param minMinute the minMinute to set
	 *
	public void setMinMinute(Integer minMinute) {
		this.minMinute = minMinute;
	}
	/**
	 * @return the maxHour
	 *
	public Integer getMaxHour() {
		return maxHour;
	}
	/**
	 * @param maxHour the maxHour to set
	 *
	public void setMaxHour(Integer maxHour) {
		this.maxHour = maxHour;
	}
	/**
	 * @return the maxMinute
	 *
	public Integer getMaxMinute() {
		return maxMinute;
	}
	/**
	 * @param maxMinute the maxMinute to set
	 *
	public void setMaxMinute(Integer maxMinute) {
		this.maxMinute = maxMinute;
	}

	/**
	 * @return the interval
	 */
	public Intervalle getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(Intervalle interval) {
		this.interval = interval;
	}
	/**
	 * @return the extremeIntervalle
	 */
	public Intervalle getExtremeIntervalle() {
		return extremeIntervalle;
	}
	/**
	 * @param extremeIntervalle the extremeIntervalle to set
	 */
	public void setExtremeIntervalle(Intervalle extremeIntervalle) {
		this.extremeIntervalle = extremeIntervalle;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlageHoraireViewer [idViewer=" + idViewer + ", idfPlage="
				+ idfPlage + ", libelle=" + libelle + ", interval=" + interval + ", extremeIntervalle="
				+ extremeIntervalle + ", statut=" + statut + ", finishing="
				+ finishing + "]";
	}
	
	
}
