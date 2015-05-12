/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.List;

import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.entity.Poste;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.helper.PosteHelper;

/**
 * @author xavier
 *
 */
public interface AgresManagerLocal {
	public void saveEquipage(Equipage equipage);
	
	public void save(EquipageHelper equipage);
	
	// Gestion des Postes
	public List<Poste> makeListPosteForEquipage (Integer idfEquipage);
	public List<PosteHelper> makeListPosteHelperForEquipage (Integer idfEquipage);
	public void addEmploi (Integer idfEmploi, Integer idfEquipage, boolean optionnel);
	public void removeEmploi(Integer idfPoste);
	public void downEmploi(Integer idfPoste);
	public void changeOrder(Integer idfPoste, Integer rowId);

}
