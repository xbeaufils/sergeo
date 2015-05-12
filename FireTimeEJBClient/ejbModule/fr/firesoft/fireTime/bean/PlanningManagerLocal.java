/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.Calendar;
import java.util.List;

import fr.firesoft.fireTime.ext.besoin.BesoinPlageHelper;
import fr.firesoft.fireTime.ext.planning.CommentDayHelper;
import fr.firesoft.fireTime.ext.planning.CommentMonthHelper;
import fr.firesoft.fireTime.ext.planning.JourStatut;
import fr.firesoft.fireTime.helper.JourAgent;
import fr.firesoft.fireTime.helper.PeriodeAgregator;
import fr.firesoft.fireTime.helper.besoin.BesoinWeekHelper;

/**
 * @author xbeaufils
 *
 */
public interface PlanningManagerLocal {
        public Integer setStatut(Integer currentIdfAgent, Calendar currentJour, Integer idfStatut, Integer idfEchelon) ;
        public Integer deleteStatut(Integer currentIdfAgent, Calendar currentJour, Integer idfEchelon);
        public void setCycle(Integer currentIdfAgent, Calendar currentJour, Calendar dateFin, Integer idfCycle, Integer idfEchelon);
        public List<PeriodeAgregator> makeListForAgent (Integer idfAgent, Integer year);
        public List<BesoinPlageHelper>checkBesoin(Integer idfEchelon, Calendar currentJour);
        public List<BesoinWeekHelper> makeListBesoin (Integer idfEchelon);
        public Calendar getCurrentCalendar() ;
        public List<JourAgent> makeListForMonth(Integer month, Integer year, Integer idfEchelon) ;
        public List<JourStatut> makeListDispoForMonth(Integer month, Integer year, Integer idfEchelon) ;
        public CommentMonthHelper getCommentMonth(Integer month, Integer year,Integer idfAgent, Integer idfEchelon);
        public CommentDayHelper select (Integer idfComment );
        public List<CommentDayHelper> makeListCommentDayforMonth(Integer month, Integer year, Integer idfEchelon);
     }
