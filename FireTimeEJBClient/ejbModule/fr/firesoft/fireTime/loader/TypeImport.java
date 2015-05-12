/**
 * 
 */
package fr.firesoft.fireTime.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xbeaufils
 *
 */
public enum TypeImport {
	
	SITUATION("affectation" , new String[] {"matricule", "debut", "fin", "grade", "organigramme"}),
	AGENT("agent",new String[]{"matricule", "nom", "prenom", "date_naissance", "organigramme"}),
	COMPETENCE("competence", new String[]{""});
	private List<String> xmlAttributes;
	private String entete;
	
	private TypeImport( String entete, String[] xmlAttributes ) {
		this.xmlAttributes = new ArrayList<String>();
		this.xmlAttributes.addAll(Arrays.asList(xmlAttributes));
		this.entete = entete;
	}
	/**
	 * @return the xmlAttribute
	 */
	public List<String> getXmlAttributes() {
		return xmlAttributes;
	}
	/**
	 * @return the entete
	 */
	public String getEntete() {
		return entete;
	}
	
	public static TypeImport getValueByEntete(String entete) {
		if (SITUATION.getEntete().equalsIgnoreCase(entete))
			return SITUATION;
		else if (AGENT.getEntete().equalsIgnoreCase(entete))
			return AGENT;
		else if (COMPETENCE.getEntete().equalsIgnoreCase(entete))
			return COMPETENCE;
/*		else if (ORGANIGRAMME.getEntete().equalsIgnoreCase(entete))
			return ORGANIGRAMME;
		else if (GRADE.getEntete().equalsIgnoreCase(entete))
			return GRADE;
		else if (STATUT_RH.getEntete().equalsIgnoreCase(entete))
			return STATUT_RH;
*/		throw new AssertionError();
	}
	
}
