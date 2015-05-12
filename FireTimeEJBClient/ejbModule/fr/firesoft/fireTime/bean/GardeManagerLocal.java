/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import fr.firesoft.fireTime.entity.Piquet;
import fr.firesoft.fireTime.helper.PiquetReport;

/**
 * @author xbeaufils
 *
 */
public interface GardeManagerLocal {
	
	public Map<Integer,List<Piquet>> makeCompleteListForBesoin(Integer idfBesoin, Integer idfPlage, Calendar day);
	public List<PiquetReport> makeListForDay(Calendar day, Integer idfEchelon);
	
	public void affecte(Integer idfPeriode, Integer idfPlage, List<Integer> lstIdfPiquet);
	public void affecte(Integer idfPeriode, Integer idfPlage, Integer idfPoste, Integer idfBesoin);
	public void delete(Integer idfPiquet);
}
