/**
 * 
 */
package fr.firesoft.fireTime.factory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.Disponibilite;
import fr.firesoft.fireTime.entity.Periode;
import fr.firesoft.fireTime.entity.horaire.PlageHoraire;
import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.horaire.BesoinEquipageHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.fireTime.ext.horaire.Presence;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.fireTime.ext.planning.JourStatut;
import fr.firesoft.fireTime.ext.planning.PlageStatut;

/**
 * @author beaufils
 *
 */
public class HoraireHelperFactory {

	/**
	 * 
	 */
	public HoraireHelperFactory() {
	}

	public static List<Statut> makeList(List<StatutPeriode> lstEntity) {
		List<Statut> lstHelper = new ArrayList<Statut>();
		for (StatutPeriode entity : lstEntity) {
			lstHelper.add(HoraireHelperFactory.make(entity));
		}
		return lstHelper;
	}
	
	public static Statut make(StatutPeriode entity){
		Statut helper = new Statut();
		helper.setIdfStatut(entity.getIdfStatut());
		helper.setLibelle(entity.getLibelle());
		helper.setCode(entity.getCode());
		helper.setDuree(entity.getDuree());
		helper.setPresence(entity.getPresence().name());
		helper.setIdfCycle(entity.getCycle().getIdfCycle());
		helper.setLstIdfPlage(new ArrayList<Integer>());
		for (PlageHoraire plage: entity.getLstComposant()) {
			helper.getLstIdfPlage().add(plage.getIdfPlage());
		}
		return helper;
			
	}
	public static List<PlageHoraireHelper> makeListPlage(List<PlageHoraire> lstPlage) {
		List<PlageHoraireHelper> lstHelper = new ArrayList<PlageHoraireHelper>();
		for (PlageHoraire aPlage : lstPlage) {
			lstHelper.add(HoraireHelperFactory.make(aPlage));
		}
		return lstHelper;
	}
	
	public static PlageHoraireHelper make(PlageHoraire critere) {
		PlageHoraireHelper aHelper = new PlageHoraireHelper();
		aHelper.setIdfPlage(critere.getIdfPlage());
		aHelper.setIdfCycle(critere.getCycle().getIdfCycle());
		aHelper.setLibelle(critere.getLibelle());
		aHelper.setDebut( critere.getDebut().getTime());
		aHelper.setHeureDebut(critere.getDebut().get(Calendar.HOUR_OF_DAY));
		aHelper.setMinuteDebut(critere.getDebut().get(Calendar.MINUTE));
		aHelper.setFin(critere.getFin().getTime() );
		aHelper.setHeureFin(critere.getFin().get(Calendar.HOUR_OF_DAY));
		aHelper.setMinuteFin(critere.getFin().get(Calendar.MINUTE));
		List<BesoinEquipageHelper> lstHelper = new ArrayList<BesoinEquipageHelper>();
		for (BesoinEquipage aBesoin : critere.getLstBesoinEquipage()) {
			lstHelper.add( BesoinHelperFactory.make(aBesoin) );
		}
		aHelper.setLstBesoinEquipage(lstHelper);
		return aHelper;
	}
	
	public static PlageStatut makePlageStatut(Disponibilite entity) {
		PlageStatut plageHelper = new PlageStatut();
		plageHelper.setIdfDispo(entity.getIdfDispo());
		plageHelper.setIdfPlage(entity.getPlage().getIdfPlage());
		plageHelper.setPresence(Presence.DISPO);
		return plageHelper;
	}
		
	public static JourStatut makeJourStatut(Disponibilite entity) {
		JourStatut helper = new JourStatut();
		helper.setIdfAgent(entity.getAgent().getIdfAgent());
		helper.setIdOrganization(entity.getIdOrganization());
		helper.setJour(entity.getJour());
		helper.setLstPlage(new ArrayList<PlageStatut>());
		helper.getLstPlage().add( HoraireHelperFactory.makePlageStatut(entity) );
		return helper;
	}
	
	public static List<JourStatut> makeLstJourStatut(List<Disponibilite> lstEntity) {
		Map<Calendar, JourStatut> mapStatut = new HashMap<Calendar, JourStatut>();
		for (Disponibilite entity : lstEntity) {
			JourStatut jour = mapStatut.get(entity.getJour());
			if (jour == null) {
				mapStatut.put(entity.getJour(), HoraireHelperFactory.makeJourStatut(entity) );				
			}
			else
				jour.getLstPlage().add( HoraireHelperFactory.makePlageStatut(entity) );
		}
		return new ArrayList<JourStatut>( mapStatut.values() );
	}
	
	public static JourStatut makeJourStatut(Periode entity) {
		JourStatut jour=  new JourStatut();
		jour.setIdfAgent(entity.getAgent().getIdfAgent());
		jour.setIdOrganization(entity.getIdOrganization());
		jour.setJour(entity.getJour());
		jour.setLstPlage(new ArrayList<PlageStatut>());
		for (PlageHoraire plage: entity.getStatut().getLstComposant()) {
			//helper.setIdfDispo(entity.getIdfDispo());
			PlageStatut helper = new PlageStatut();
			helper.setIdfPlage(plage.getIdfPlage());
			helper.setPresence(entity.getStatut().getPresence());
			jour.getLstPlage().add(helper);
		}
		return jour;
	}
	
	public static List<JourStatut> makeLstJourStatutWithPeriode(List<Periode> lstEntity) {
		List<JourStatut> lstPeriode =  new ArrayList<JourStatut>();
		for (Periode entity : lstEntity) {
			lstPeriode.add(HoraireHelperFactory.makeJourStatut(entity));
		}
		return lstPeriode;
	}

}
