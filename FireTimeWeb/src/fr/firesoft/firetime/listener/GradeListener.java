/**
 * 
 */
package fr.firesoft.firetime.listener;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;

/**
 * @author xavier
 *
 */
public class GradeListener implements ServletContextListener {

	/**
	 * 
	 */
	public GradeListener() {
	    System.out.println("Logback contextInitialized !!!!");
	    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory(); 
	    JoranConfigurator jc = new JoranConfigurator(); 
	    jc.setContext(context); context.reset(); 
        // override default configuration 
        // inject the name of the current application as "application-name" 
        // property of the LoggerContext 
	    context.putProperty("application-name", "menu_dinamico"); 
	    try { 
	    	InputStream is = getClass().getClassLoader().getResourceAsStream("logback.xml");
	        if(is == null) {
	        	System.out.println("Logback xml file non trovato");
	        }
	        else {
	        	jc.doConfigure(is);
	           
	        }
	    } catch (JoranException ex) {
	            System.out.println("Logback contextInitialized error");
	            StatusPrinter.print(context);
	            ex.printStackTrace();
	    }	
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		EffectifManagerLocal manager = ServiceLocatorFireTime.getEffectifManagerBean(null);
		manager.checkGrade();
		manager.checkEmploiOperationnel();
	}

}
