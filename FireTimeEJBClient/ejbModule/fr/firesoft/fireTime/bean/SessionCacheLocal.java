/**
 * 
 */
package fr.firesoft.fireTime.bean;

/**
 * @author beaufils
 *
 */
public interface SessionCacheLocal {
	public AuthentificationLocal getSessionAuthentification(String jsessionId);
}
