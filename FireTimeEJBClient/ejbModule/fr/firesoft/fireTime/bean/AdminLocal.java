/**
 * 
 */
package fr.firesoft.fireTime.bean;

import fr.firesoft.fireTime.entity.horaire.StatutPeriode;

/**
 * @author xbeaufils
 *
 */
public interface AdminLocal {
	public void saveStatut(StatutPeriode aStatut);
	
	public void createClient(String email, String centre, String organisationName,
			String adresse, String adresseComplementaire, String codePostal, String ville);
}
