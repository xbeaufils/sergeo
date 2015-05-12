/**
 * 
 */
package fr.firesoft.fireTime.factory;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.besoin.entity.BesoinEquipage;
import fr.firesoft.fireTime.entity.Equipage;
import fr.firesoft.fireTime.entity.Poste;
import fr.firesoft.fireTime.ext.horaire.BesoinEquipageHelper;
import fr.firesoft.fireTime.ext.horaire.EquipageHelper;
import fr.firesoft.fireTime.helper.PosteHelper;


/**
 * @author xbeaufils
 *
 */
public class BesoinHelperFactory {

	static Logger log = LoggerFactory.getLogger(BesoinHelperFactory.class);
	/**
	 * 
	 */
	public BesoinHelperFactory() {
	}

	public static  List<EquipageHelper> makeList(List<Equipage> lstEntity) {
		List<EquipageHelper> lstHelper = new ArrayList<EquipageHelper>();
		for (Equipage entity: lstEntity)
			lstHelper.add(make(entity));
		return lstHelper;
	}
	
	public static  EquipageHelper make (Equipage entity) {
		EquipageHelper helper = new EquipageHelper();
		helper.setIdfEquipage(entity.getIdfEquipage());
		helper.setLibelle(entity.getLibelle());
		helper.setIdOrga(entity.getIdOrganization());
		return helper;
	}
	
	public static  List<PosteHelper> makeListPoste(List<Poste> lstEntity) {
		List<PosteHelper> lstHelper = new ArrayList<PosteHelper>();
		for (Poste entity: lstEntity)
			lstHelper.add(make(entity));
		return lstHelper;
		
	}
	
	public static  PosteHelper make(Poste entity) {
		PosteHelper helper = new PosteHelper();
		helper.setIdfPoste(entity.getIdfPoste());
		helper.setAffichage(entity.getAffichage());
		helper.setEmploi(entity.getEmploi().getLibelle());
		helper.setIdEmploi(entity.getEmploi().getIdEmploi());
		helper.setEquipage(entity.getEquipage().getLibelle());
		helper.setIdfEquipage(entity.getEquipage().getIdfEquipage());
		helper.setIdOrganization(entity.getEquipage().getIdOrganization());
		helper.setOptionnel(entity.isOptionnel());
		return helper;
	}
	public static List<BesoinEquipageHelper> makeListEquipage(List<BesoinEquipage> lstEntity) {
		log.debug("Nb besoin {}", lstEntity.size() );
		List<BesoinEquipageHelper> lstHelper = new ArrayList<BesoinEquipageHelper>();
		for (BesoinEquipage entity: lstEntity)
			lstHelper.add(make(entity));
		return lstHelper;
	}
	
	public static BesoinEquipageHelper make(BesoinEquipage entity) {
		BesoinEquipageHelper helper = new BesoinEquipageHelper();
		helper.setIdfPlageHelper(entity.getPlage().getIdfPlage());
		helper.setIdfBesoin(entity.getIdfBesoin());
		helper.setLibelle(entity.getLibelle());
		helper.setEquipage( BesoinHelperFactory.make(entity.getEquipage()));
		helper.setSecondaire(entity.isSecondaire());
		log.debug("besoin {} pour {}", new Object[] { helper.getLibelle(), helper.getEquipage().getLibelle()});
		return helper;
	}
	
}
