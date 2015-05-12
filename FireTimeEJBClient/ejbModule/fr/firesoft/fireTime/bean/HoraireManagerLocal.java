package fr.firesoft.fireTime.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.firesoft.fireTime.entity.horaire.CycleHoraire;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.Statut;

public interface HoraireManagerLocal {
	/**
	 * Gestion des cycles horaires
	 */
	//public List<CycleHoraireHelper> makeListForEchelon(Integer idfEchelon);
	public CycleHoraire selectCycle(Integer idfCycle);
	public CycleHoraire seletCycleForDate(Calendar day, Integer idfEchelon);
	public void save(String libelle, Date dateValidite, Integer idfEchelon);
	public void save(Integer idfCycle, String libelle, Date dateValidite, Integer idfEchelon);
	//public void savePlageHoraire(List<PlageHoraireHelper> lstPlage);
	/**
	 * Gestion des statuts de période
	 */
	public List<StatutPeriode> makeListStatutForCycle(Integer idfCycle); 
	public void deleteStatut(Integer idfStatut);
	public void savePlageForStatut(Statut statut);
	/**
	 *  Gestion des plages horaires
	 */
	public PlageHoraireHelper selectHelper(Integer idfCritere);	
	public PlageHoraire select (Integer idfCritere);	
	public void save(PlageHoraireHelper critere);
	public void abort();
	public List<PlageHoraireHelper> makeListForStatut(Integer idfStatut);
	/*
	 * Gestion des besoins
	 */
	public CycleHoraire getCurrentCycle();
	public void deleteBesoin(PlageHoraireHelper aCritere, Integer idfBesoin);
	public PlageHoraireHelper create(Integer idfEchelon);
	public void saveStatut(	Integer idfStatut,  
			String presence, String libelle, String code, Integer duree, Integer idfCycle);
	public void delete();
}
