/**
 * 
 */
package fr.firesoft.fireTime.impl;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.firesoft.fireTime.bean.AuthentificationLocal;

/**
 * @author beaufils
 *
 */

@Stateful
@Local(AuthentificationLocal.class)
public class AuthentificationManager implements AuthentificationLocal {

	
	private String token;
	private Map<String, String> cookies;
	
	Logger log = LoggerFactory.getLogger(AuthentificationManager.class);
	/**
	 * 
	 */
	public AuthentificationManager() {
		log.debug("AuthentificationManager is born");
	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AuthentificationLocal#setToken(java.lang.String)
	 */
	@Override
	public void setToken(String token) {
		log.debug("Setting token {}", token);
		this.token = token;

	}

	/* (non-Javadoc)
	 * @see fr.firesoft.fireTime.bean.AuthentificationLocal#getToken()
	 */
	@Override
	public String getToken() {
		log.debug ("Returning token {}", token);
		return token;
	}

	/**
	 * @return the cookies
	 */
	public Map<String, String> getCookies() {
		return cookies;
	}

	/**
	 * @param cookies the cookies to set
	 */
	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

}
