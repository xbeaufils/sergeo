/**
 * 
 */
package fr.firesoft.firetime.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import au.com.bytecode.opencsv.CSVReader;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.exception.AgentNotFoundException;
import fr.firesoft.fireTime.exception.GradeNotFoundException;
import fr.firesoft.fireTime.exception.OrganigrammeNotFoundException;
import fr.firesoft.fireTime.loader.TypeImport;
import fr.firesoft.firetime.authentification.User;

/**
 * @author xbeaufils
 *
 */
public class LoaderAction extends AuthentifiedAction implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8512001143322705344L;
	Logger log = LoggerFactory.getLogger(LoaderAction.class);
	
	private String fileUploadFileName;
	private String fileUploadContentType;
	private File fileUpload;
	private List<TypeImport> lstImport;
	private TypeImport selectedTypeImport;
	private List<String> colTypeImport; 
	//private String typeImport;
	private String separateurChamp;
	
    private File file;
    private String contentType;
    private String filename;
	/**
	 * 
	 */
	public LoaderAction() {
		lstImport = new ArrayList<TypeImport>();
		lstImport.addAll( Arrays.asList( TypeImport.values() ) );
	}
	
	public String view() {
		lstImport = new ArrayList<TypeImport>();
		lstImport.addAll( Arrays.asList( TypeImport.values() ) );	
		session.remove("entete");
		getSession().remove("dataSample");
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String upload() {
		try {
			String firetimeDir = System.getProperty( "firetime.dir" );
	        File fileToCreate = new File(firetimeDir, selectedTypeImport 
	        		+  "-" + ((User) getSession().get("currentUser")).getOrganisationId());
	        
	        FileUtils.copyFile(this.fileUpload, fileToCreate);
	        log.debug("File {} created", fileToCreate.getAbsolutePath());
	        // Copie du fichier
	        /*FileReader in = new FileReader(filename);
	        FileWriter out = new FileWriter(fileToCreate);
	        int c;

	        while ((c = in.read()) != -1)
	          out.write(c);

	        in.close();
	        out.close();
	        */
			// selectedTypeImport = TypeImport.valueOf(typeImport);

	        // Parse du fichier         			
			CSVReader csvReader = new CSVReader(new FileReader(fileToCreate), separateurChamp.charAt(0));
			ArrayList<ArrayList<String>>dataTable = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>>dataSample = new ArrayList<ArrayList<String>>();
			ArrayList<String> dataRow;	
			// String line = null;
			int nbLine = 0;
		    String [] nextLine;
		    while ((nextLine = csvReader.readNext()) != null) {
				dataRow = new ArrayList<String>();
				dataRow.addAll( Arrays.asList(nextLine) );
				dataTable.add(dataRow);
				if (nbLine == 0)
					getSession().put("entete", dataRow);
				else if (nbLine < 10)
					dataSample.add(dataRow);
				else
					break;
				log.debug ("NbLigne read {}", nbLine);
				nbLine++;
		    }
			getSession().put("dataSample", dataSample);
		}
		catch (FileNotFoundException e) {
			log.error("File not found ", e);
		}
		catch (IOException e) {
			log.error("IOException", e);
		}
		return SUCCESS;
	}
	
	public String transfert() {
		getSession().remove("entete");
        // Parse du fichier         		
		EffectifManagerLocal aManager = ServiceLocatorFireTime.getEffectifManagerBean((AuthentificationLocal) session.get("authentifcationCache"));
		try {
			String firetimeDir = System.getProperty( "firetime.dir" );
			File tmpFile = new File(firetimeDir,selectedTypeImport 
				+ "-" + ((User) getSession().get("currentUser")).getOrganisationId());
			//TypeImport thisImport = TypeImport.valueOf( typeImport );
			//selectedTypeImport.get
			
			//TypeImport thisImport = selectedTypeImport;
			log.debug("import is {}", selectedTypeImport);
			log.debug("File to parse {}", tmpFile.getAbsoluteFile());
			CSVReader csvReader = new CSVReader(new FileReader(tmpFile), separateurChamp.charAt(0));
			// String line = null;
		    String [] nextLine;
			//Document doc = new Document(root);
			XMLOutputter outputter = new XMLOutputter();
			if (csvReader.readNext() == null)
				return selectedTypeImport.toString();
		    while ((nextLine = csvReader.readNext()) != null) {
				Element root = new Element(selectedTypeImport.getEntete());
				//root.setAttribute("organisation",super.getIdfEchelon().toString());
				Element nodeOrga  = new Element("organigramme");
				nodeOrga.setText(super.getIdfEchelon().toString());
				root.addContent(nodeOrga);
				for (String element : selectedTypeImport.getXmlAttributes()) {
					int index = colTypeImport.indexOf(element);
					Element node  = new Element(element);
					if ( ! nextLine[index].isEmpty() ) {
						node.setText(nextLine[index]);
						root.addContent(node);
					}
				}
				log.debug("Message XML " + outputter.outputString(root));
				aManager.transfert(outputter.outputString(root));
		    }		
		    //log.debug("XML message " + outputter.outputString(root));
/*			this.loadAffectation(new File(firetimeDir,typeImport 
	        		+ "-" + super.getOrganisation() + "-" + super.getIdfEchelon()), 
	        		TypeImport.valueOf( typeImport ));
*/			return selectedTypeImport.toString();
		}
		catch (AgentNotFoundException e) {
			log.error("Erreur", e);
		}
		catch (OrganigrammeNotFoundException e) {
			log.error("Erreur", e);
		}
		catch(GradeNotFoundException e) {
			log.error("Erreur", e);	
		}
		catch(FileNotFoundException e ) {
			log.error("Erreur", e);
		}
		catch (IOException e) {
			log.error("Erreur", e);			
		}
		return ERROR;
	}
	//---------- private methods --------------------
/*	private Document loadAffectation(File fileAffectation, 
			TypeImport typeImport) throws FileNotFoundException, IOException{
		log.debug("File to parse "+ fileAffectation.getAbsoluteFile());
		CSVReader csvReader = new CSVReader(new FileReader(fileAffectation));
		// String line = null;
	    String [] nextLine;
		Element root = new Element("affectations");
		root.setAttribute("organisation", super.getOrganisation());
		root.setAttribute("echelon", super.getIdfEchelon().toString());
		Document doc = new Document(root);
		XMLOutputter outputter = new XMLOutputter();
	    while ((nextLine = csvReader.readNext()) != null) {
			Element nodeAffectation = new Element(typeImport.getEntete());
			for (String element : typeImport.getXmlAttributes()) {
				int index = colTypeImport.indexOf(element);
				Element node  = new Element(element);
				node.setText(nextLine[index]);
				nodeAffectation.addContent(node);
			}
			log.debug("Affectation XML " + outputter.outputString(nodeAffectation));
			root.addContent(nodeAffectation);
	    }		
	    log.debug("XML message " + outputter.outputString(root));
	    return doc;
	}
*/	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings("unchecked")
	public Map getSession() {
		return session;
	}
	// ------------Getters / Setters --------

	/**
	 * @return the fileUpload
	 */
	public File getFileUpload() {
		return fileUpload;
	}

	/**
	 * @param fileUpload the fileUpload to set
	 */
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	/**
	 * @return the lstImport
	 */
	public List<TypeImport> getLstImport() {
		return lstImport;
	}

	/**
	 * @param lstImport the lstImport to set
	 */
	public void setLstImport(List<TypeImport> lstImport) {
		this.lstImport = lstImport;
	}

	/**
	 * @return the typeImport
	 *
	public String getTypeImport() {
		return typeImport;
	}

	/**
	 * @param typeImport the typeImport to set
	 *
	public void setTypeImport(String typeImport) {
		this.typeImport = typeImport;
	}

	/**
	 * @return the selectedTypeImport
	 */
	public TypeImport getSelectedTypeImport() {
		return selectedTypeImport;
	}

	/**
	 * @param selectedTypeImport the selectedTypeImport to set
	 */
	public void setSelectedTypeImport(TypeImport selectedTypeImport) {
		this.selectedTypeImport = selectedTypeImport;
	}

	/**
	 * @return the colTypeImport
	 */
	public List<String> getColTypeImport() {
		return colTypeImport;
	}

	/**
	 * @param colTypeImport the colTypeImport to set
	 */
	public void setColTypeImport(List<String> colTypeImport) {
		this.colTypeImport = colTypeImport;
	}

	/**
	 * @return the separateurChamp
	 */
	public String getSeparateurChamp() {
		return separateurChamp;
	}

	/**
	 * @param separateurChamp the separateurChamp to set
	 */
	public void setSeparateurChamp(String separateurChamp) {
		this.separateurChamp = separateurChamp;
	}

	/**
	 * @return the fileUploadFileName
	 */
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	/**
	 * @param fileUploadFileName the fileUploadFileName to set
	 */
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	/**
	 * @return the fileUploadContentType
	 */
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	/**
	 * @param fileUploadContentType the fileUploadContentType to set
	 */
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
		
}
