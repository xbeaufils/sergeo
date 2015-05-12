/**
 * 
 */
package fr.firesoft.fireTime.bean;

import java.util.Map;

/**
 * @author beaufils
 *
 */
public interface AuthentificationLocal {
	public void setToken(String token);
	public String getToken();
	public Map<String, String> getCookies();
	public void setCookies(Map<String, String> cookies);
}
