/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.List;

import fr.firesoft.fireTime.entity.horaire.StatutPeriode;
import fr.firesoft.fireTime.ext.horaire.Statut;
import fr.firesoft.fireTime.helper.EmploiOperationnelHelper;

/**
 * @author xbeaufils
 *
 */
public interface ParametreDaoLocal {
	public List<StatutPeriode> makeListStatut() ;
	public StatutPeriode select(Integer idfStatutPeriode);
	public Statut selectHelper(Integer idfStatutPeriode);
	
	public List<EmploiOperationnelHelper> makeListEmploiOperationel();
}
