/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.Calendar;
import java.util.List;

import fr.firesoft.fireTime.entity.Periode;


/**
 * @author xbeaufils
 *
 */
public interface PeriodeDaoLocal {

	public List<Periode> makeListForDate(Calendar day, Integer idfEchelon);
	public List<Periode> makeListForDateAndPlage(Calendar day, Integer idfEchelon, Integer idfPlage );
	public List<Periode> makeListForMonth(Integer month, Integer year, Integer idfEchelon) ;
	public List<Periode> makeListForWeek(Integer week, Integer year, Integer idfEchelon) ;
	public Periode selectForAgentAndDate(Integer idfAgent, Calendar day, Integer idfEchelon);
	public List<Periode> makeListForAgentAndDate(Integer idfAgent, Calendar debut, Calendar fin, Integer idfEchelon);
	public Periode select(Integer idfPeriode);
	public Long calculateDureeForAgentAndMonth(Integer idfAgent, Integer month, Integer year);
}
