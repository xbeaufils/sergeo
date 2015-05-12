/**
 * 
 */
package fr.firesoft.fireTime;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.bean.AdminLocal;
import fr.firesoft.fireTime.bean.AgentDaoLocal;
import fr.firesoft.fireTime.bean.AgresManagerLocal;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.BesoinManagerLocal;
import fr.firesoft.fireTime.bean.CycleManagerLocal;
import fr.firesoft.fireTime.bean.GardeManagerLocal;
import fr.firesoft.fireTime.bean.HoraireManagerLocal;
import fr.firesoft.fireTime.bean.CompetenceManagerLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.bean.ParametreDaoLocal;
import fr.firesoft.fireTime.bean.PeriodeDaoLocal;
import fr.firesoft.fireTime.bean.PlanningManagerLocal;

/**
 * @author xbeaufils
 *
 */
public class ServiceLocatorFireTime {

	public static Logger log = LoggerFactory.getLogger(ServiceLocatorFireTime.class.getName());
	/**
	 * 
	 */
	public static AgresManagerLocal getAgresManagerBean() {
		try {
			Context initialContext = new InitialContext();    		
			AgresManagerLocal bean =
				(AgresManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.AgresManager_fr.firesoft.fireTime.bean.AgresManagerLocal@Local");
			return bean;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}

	public static PeriodeDaoLocal getPeriodeDaoBean() {
		try {
			Context initialContext = new InitialContext();    		
			PeriodeDaoLocal aDao =
				// (PeriodeDaoLocal) initialContext.lookup("FireTime/PeriodeDao/local");
			(PeriodeDaoLocal) initialContext.lookup("fr.firesoft.fireTime.impl.PeriodeDao_fr.firesoft.fireTime.bean.PeriodeDaoLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}
	
	public static AgentDaoLocal getAgentDaoBean() {
		try {
			Context initialContext = new InitialContext();    		
			AgentDaoLocal aDao =
				//(AgentDaoLocal) initialContext.lookup("FireTime/AgentDao/local");
			(AgentDaoLocal) initialContext.lookup("fr.firesoft.fireTime.impl.AgentDao_fr.firesoft.fireTime.bean.AgentDaoLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}
	
	public static EffectifDaoLocal getEffectifDaoBean(AuthentificationLocal auth) {
		try {
			Context initialContext = new InitialContext();    		
			EffectifDaoLocal aDao =
				//(EffectifDaoLocal) initialContext.lookup("FireTime/EffectifDao/local");
			(EffectifDaoLocal) initialContext.lookup("fr.firesoft.fireTime.impl.EffectifDao_fr.firesoft.fireTime.bean.EffectifDaoLocal@Local");
			aDao.setAuthentificationCache(auth);
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}
	
	public static EffectifManagerLocal getEffectifManagerBean(AuthentificationLocal auth) {
		try {
			Context initialContext = new InitialContext();    		
			EffectifManagerLocal aDao =
				//(EffectifManagerLocal) initialContext.lookup("FireTime/EffectifManager/local");
				(EffectifManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.EffectifManager_fr.firesoft.fireTime.bean.EffectifManagerLocal@Local");
			aDao.setAuthentificationCache(auth);
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}
	public static HoraireManagerLocal getHoraireManagerBean() {
		try {
			Context initialContext = new InitialContext();    		
			HoraireManagerLocal aDao =
				//(HoraireManagerLocal) initialContext.lookup("FireTime/HoraireManager/local");
			(HoraireManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.HoraireManager_fr.firesoft.fireTime.bean.HoraireManagerLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}
	
	public static ParametreDaoLocal getParametreDaoBean() {
		try {
			Context initialContext = new InitialContext();    		
			ParametreDaoLocal aDao =   
				(ParametreDaoLocal) initialContext.lookup("fr.firesoft.fireTime.impl.ParametreDao_fr.firesoft.fireTime.bean.ParametreDaoLocal@Local");
				// (ParametreDaoLocal) initialContext.lookup("FireTime/ParametreDao/local");
			
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}
	
	public static PlanningManagerLocal getPlanningManagerBean() {
		try {
			Context initialContext = new InitialContext();    		
			PlanningManagerLocal aDao = 
				//(PlanningManagerLocal) initialContext.lookup("FireTime/PlanningManager/local");
			(PlanningManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.PlanningManager_fr.firesoft.fireTime.bean.PlanningManagerLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}				
	}
	
	public static AdminLocal getAdminBean() {
		try {
			Context initialContext = new InitialContext();    		
			AdminLocal aDao =
				//(AdminLocal) initialContext.lookup("FireTime/Admin/local");
			(AdminLocal) initialContext.lookup("fr.firesoft.fireTime.impl.Admin_fr.firesoft.fireTime.bean.AdminLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}						
	}
	
	public static CompetenceManagerLocal getCompetenceManagerBean() {
		try {
			Context initialContext = new InitialContext();    		
			CompetenceManagerLocal aDao =
				//(CompetenceManagerLocal) initialContext.lookup("FireTime/CompetenceManager/local");
			(CompetenceManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.CompetenceManager_fr.firesoft.fireTime.bean.CompetenceManagerLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}						
	}
	public static GardeManagerLocal getGardeManagerBean() {
		try {
			Context initialContext = new InitialContext();    		
			GardeManagerLocal aDao =
				//(GardeManagerLocal) initialContext.lookup("FireTime/GardeManager/local");
			(GardeManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.GardeManager_fr.firesoft.fireTime.bean.GardeManagerLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}						
	}
	public static BesoinManagerLocal getBesoinManagerBean() {
		try {
			Context initialContext = new InitialContext();    		
			BesoinManagerLocal aDao =
				//(BesoinManagerLocal) initialContext.lookup("FireTime/BesoinManager/local");
			(BesoinManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.BesoinManager_fr.firesoft.fireTime.bean.BesoinManagerLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}						
	}
	
	public static CycleManagerLocal getCycleManagerBean() {
		try {
			Context initialContext = new InitialContext();    		
			CycleManagerLocal aDao =
				// (CycleManagerLocal) initialContext.lookup("FireTime/CycleManager/local");
			(CycleManagerLocal) initialContext.lookup("fr.firesoft.fireTime.impl.CycleManager_fr.firesoft.fireTime.bean.CycleManagerLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}						
		
	}
	public static AuthentificationLocal getAuthentificationBean() {
		try {
			Context initialContext = new InitialContext();    		
			AuthentificationLocal aDao =
				//(GardeManagerLocal) initialContext.lookup("FireTime/GardeManager/local");
			(AuthentificationLocal) initialContext.lookup("fr.firesoft.fireTime.impl.AuthentificationManager_fr.firesoft.fireTime.bean.AuthentificationLocal@Local");
			return aDao;
		}
		catch (NamingException e) {
			log.error("ERREUR",e);
			return null;
		}						
	}

	
}