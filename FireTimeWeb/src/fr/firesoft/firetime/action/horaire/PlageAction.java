/**
 * 
 */
package fr.firesoft.firetime.action.horaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.ext.ServiceLocatorExtFireTime;
import fr.firesoft.fireTime.ext.bean.HoraireDaoRemote;
import fr.firesoft.fireTime.ext.horaire.PlageHoraireHelper;
import fr.firesoft.firetime.action.AuthentifiedAction;
import fr.firesoft.firetime.viewer.PlageHoraireViewer;

/**
 * @author xbeaufils
 *
 */
public class PlageAction extends AuthentifiedAction implements SessionAware {

	Logger log = LoggerFactory.getLogger(PlageAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1197076671008848875L;

	private List<PlageHoraireHelper> lstPlage;
	//private List<PlageHoraireViewer> lstPlageViewer;
	private PlageHoraireHelper currentPlage;
	private PlageHoraireViewer currentPlageViewer;
	private PlageHoraireViewer firstPlageViewer;
	
	private Integer idfPlage;
	private String  libelle;
	private Integer heureDebut;
	private Integer minuteDebut;
	private Integer heureFin;
	private Integer minuteFin;
	private Integer idfCycle;
	
	/**
	 * 
	 */
	public PlageAction() {
	}
	
	
	public String viewPlage() {
		HoraireDaoRemote dao = ServiceLocatorExtFireTime.getHoraireDao();
		lstPlage = dao.makeListPlageForCycle( idfCycle); //Integer.parseInt(id) );
		return SUCCESS;
	}	
	/*
	public String updatePlage() {
		log.debug("Libelle to update {}", libelle);
		PlageHoraireHelper helper = new PlageHoraireHelper();	
		if (oper.equalsIgnoreCase("edit"))
			helper.setIdfPlage(idfPlage);
		helper.setLibelle(libelle);
		Calendar calDebut = Calendar.getInstance();
		calDebut.set(0, 0, 1, heureDebut, minuteDebut, 0);
		helper.setDebut(calDebut.getTime());
		Calendar calFin = Calendar.getInstance();
		calFin.set(0, 0, 1, heureFin, minuteFin, 0);
		helper.setFin(calFin.getTime());
		helper.setIdfCcyle(Integer.parseInt(rowid));
		/*
		currentPlageViewer = new PlageHoraireViewer();
		currentPlageViewer.setHeureDebut(heureDebut);
		currentPlageViewer.setHeureFin(heureFin);
		currentPlageViewer.setMinuteDebut(minuteDebut);
		currentPlageViewer.setMinuteFin(minuteFin);
		currentPlageViewer.setMinuteDebut(minuteDebut);
		*
		HoraireManagerLocal manager = ServiceLocatorFireTime.getHoraireManagerBean();
		manager.save(helper);
		//firstPlageViewer = (PlageHoraireViewer)getSession().get("firstPlageViewer");
		//currentPlageViewer.setIdViewer(this.buildIdViewer(currentPlageViewer));
		//log.debug("current plage {}",this.currentPlageViewer );
		//currentPlageViewer = this.addPlage(currentPlageViewer);
		return SUCCESS;
	}
	*/
	
	// --------- Private methods ---------------


	
	//---------- Implementation SessionAware --------------
	private Map<?, ?> session;
	 /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public void setSession(Map arg0) {
		session = arg0;
	}
	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}
	// ------------Getters / Setters --------
	/**
	 * @return the lstPlage
	 */
	public List<PlageHoraireHelper> getLstPlage() {
		return lstPlage;
	}
	/**
	 * @param lstPlage the lstPlage to set
	 */
	public void setLstPlage(List<PlageHoraireHelper> lstPlage) {
		this.lstPlage = lstPlage;
	}

	/**
	 * @return the currentPlage
	 */
	public PlageHoraireHelper getCurrentPlage() {
		return currentPlage;
	}

	/**
	 * @param currentPlage the currentPlage to set
	 */
	public void setCurrentPlage(PlageHoraireHelper currentPlage) {
		this.currentPlage = currentPlage;
	}

	/**
	 * @return the lstPlageViewer
	 */
	public List<PlageHoraireViewer> getLstPlageViewer() {
		List<PlageHoraireViewer> lstPlageViewer = new ArrayList<PlageHoraireViewer>();
		//PlageHoraireViewer last = null;
		PlageHoraireViewer iteratedViewer = firstPlageViewer;
		while (iteratedViewer != null) {
			lstPlageViewer.add(iteratedViewer);
			//last = iteratedViewer;
			iteratedViewer = iteratedViewer.getNext();
		}
		/*if (lstPlageViewer.size() > 1 ) {
			last.getInterval().setFin(firstPlageViewer.getInterval().getDebut());
		}
		*/
		return lstPlageViewer;
	}

	/**
	 * @param lstPlageViewer the lstPlageViewer to set
	 *
	public void setLstPlageViewer(List<PlageHoraireViewer> lstPlageViewer) {
		this.lstPlageViewer = lstPlageViewer;
	}

	/**
	 * @return the currentPlageViewer
	 */
	public PlageHoraireViewer getCurrentPlageViewer() {
		return currentPlageViewer;
	}

	/**
	 * @param currentPlageViewer the currentPlageViewer to set
	 */
	public void setCurrentPlageViewer(PlageHoraireViewer currentPlageViewer) {
		this.currentPlageViewer = currentPlageViewer;
	}

	/**
	 * @return the idfPlage
	 */
	public Integer getIdfPlage() {
		return idfPlage;
	}


	/**
	 * @param idfPlage the idfPlage to set
	 */
	public void setIdfPlage(Integer idfPlage) {
		this.idfPlage = idfPlage;
	}

	/**
	 * @param idfPlage the idfPlage to set
	 */
	public void setIdfPlage(String idfPlage) {
		try {
			this.idfPlage = Integer.parseInt(idfPlage);
		}
		catch (NumberFormatException e) {
			log.debug("Trying to set idfPlage with {}", idfPlage);
		}
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}


	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * @return the heureDebut
	 */
	public Integer getHeureDebut() {
		return heureDebut;
	}


	/**
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(Integer heureDebut) {
		this.heureDebut = heureDebut;
	}


	/**
	 * @return the minuteDebut
	 */
	public Integer getMinuteDebut() {
		return minuteDebut;
	}


	/**
	 * @param minuteDebut the minuteDebut to set
	 */
	public void setMinuteDebut(Integer minuteDebut) {
		this.minuteDebut = minuteDebut;
	}


	/**
	 * @return the heureFin
	 */
	public Integer getHeureFin() {
		return heureFin;
	}


	/**
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(Integer heureFin) {
		this.heureFin = heureFin;
	}


	/**
	 * @return the minuteFin
	 */
	public Integer getMinuteFin() {
		return minuteFin;
	}


	/**
	 * @param minuteFin the minuteFin to set
	 */
	public void setMinuteFin(Integer minuteFin) {
		this.minuteFin = minuteFin;
	}


	/**
	 * @return the idfCycle
	 */
	public Integer getIdfCycle() {
		return idfCycle;
	}


	/**
	 * @param idfCycle the idfCycle to set
	 */
	public void setIdfCycle(Integer idfCycle) {
		this.idfCycle = idfCycle;
	}

}
