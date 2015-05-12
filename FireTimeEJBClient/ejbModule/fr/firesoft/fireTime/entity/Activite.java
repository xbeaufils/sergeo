/**
 * 
 */
package fr.firesoft.fireTime.entity;

/**
 * @author xbeaufils
 *
 */
public enum Activite {
	ACTIF ("O"),
	INACTIF("N");
	   private String valueString;
	   
	private Activite(String value) {
		valueString = value;
	}
	
	static public Activite getValueFrom(String aValue) {
		if (aValue.equals(ACTIF.valueString) )
			return ACTIF;
		else if (aValue.equals(INACTIF.valueString))
			return INACTIF;
		throw new AssertionError();
	}
}
