/**
 * 
 */
package fr.firesoft.fireTime.entity.effectif;

/**
 * @author xbeaufils
 *
 */
public enum Groupe {
    /**
     * Caporal
     */
    CAP("CAPORAL"),
    /**
     * Infirmier
     */
    INF("INFIRMIER"),
    /**
     * Medecin
     */
    MED("MEDECIN"),
    /**
     * Officier
     */
    OFF("OFFICIER"),
    /**
     * Pharmacien
     */
    PHA("PHARMACIEN "),
    /**
     * Sapeur
     */
    SAP ("SAP"),
    /**
     * Sous Officier
     */
    SOF ("SOUS_OFFICIER "),
    /**
     * Veterinaire
     */
    VET("VETERINAIRE ");

    private String valueString;

    private Groupe(String strValue) {
    valueString = strValue;
    }


}
