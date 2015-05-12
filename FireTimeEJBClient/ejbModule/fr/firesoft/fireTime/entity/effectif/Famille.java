/**
 * 
 */
package fr.firesoft.fireTime.entity.effectif;

/**
 * @author xbeaufils
 *
 */
public enum Famille {
    SP ("SP"),
    SSSM ("SSSM");

    private String valueString;

    private Famille(String strValue) {
    	valueString = strValue;
    }

}
