/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.List;

import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.helper.PosteHelper;
import fr.firesoft.fireTime.ext.horaire.BesoinEquipageHelper;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;

/**
 * @author xbeaufils
 *
 */
public interface BesoinManagerLocal {
	public Equipage selectEquipage( Integer idfEquipage);
	public List<EquipageHelper> makeListEquipageForEchelon(Integer idfEchelon);
	public List<PosteHelper> makeLisPosteForEchelon(Integer idfEchelon);
	public List<BesoinEquipageHelper> makeListBesoinForPlage(Integer idfPlage);
	public BesoinEquipage selectBesoin(Integer idfBesoin); 
	public BesoinEquipageHelper selectBesoinHelper(Integer idfBesoin); 
	public void addBesoinEquipage(BesoinEquipageHelper besoin);
	public void deleteBesoinEquipage(PlageHoraireHelper aCritere, Integer idfBesoin);
	public void deleteBesoinEquipage(Integer idfBesoin);
}
