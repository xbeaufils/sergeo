/**
 * 
 */
package fr.firesoft.firetime.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.fireTime.bean.EffectifDaoLocal;
import fr.firesoft.fireTime.bean.EffectifManagerLocal;
import fr.firesoft.fireTime.helper.GradeHelper;

/**
 * @author xavier
 *
 */
public class GradeAction extends AuthentifiedAction implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -182400859137040032L;
	Logger log = LoggerFactory.getLogger(GradeAction.class);

	private List<GradeHelper> gradeModel;
	
	private Integer id;
	private String code;
	

	private String _search;
	private String nd;
	private String rows;
	private String page;
	private String sidx;
	private String sord;
	/**
	 * 
	 */
	public GradeAction() {
	}
	
	public String show() {
	    EffectifDaoLocal dao = ServiceLocatorFireTime.getEffectifDaoBean((AuthentificationLocal) session.get("authentifcationCache"));
	    log.debug("idfEchelon {}",  super.getIdfEchelon());
	    gradeModel = dao.makeListGradeForOrganisationWithEmptyCode(super.getIdfEchelon() );
	    log.debug("Nombre de grade {}", gradeModel.size());
	    System.out.println("Nombre de grade " + gradeModel.size());
	    return SUCCESS;
	}
	
	public String edit() {

		EffectifManagerLocal manager = ServiceLocatorFireTime.getEffectifManagerBean((AuthentificationLocal) session.get("authentifcationCache"));
		manager.saveGrade(id, code, super.getIdfEchelon());
		return SUCCESS;
	}
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
	/**
	 * @return the gradeModel
	 */
	public List<GradeHelper> getGradeModel() {
		return gradeModel;
	}
	/**
	 * @param gradeModel the gradeModel to set
	 */
	public void setGradeModel(List<GradeHelper> gradeModel) {
		this.gradeModel = gradeModel;
	}

	/**
	 * @return the idfGrade
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param idfGrade the idfGrade to set
	 */
	public void setId(Integer idfGrade) {
		this.id = idfGrade;
	}

	/**
	 * @return the codeGrade
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param codeGrade the codeGrade to set
	 */
	public void setCode(String codeGrade) {
		this.code = codeGrade;
	}

	/**
	 * @return the _search
	 */
	public String get_search() {
		return _search;
	}

	/**
	 * @param _search the _search to set
	 */
	public void set_search(String _search) {
		this._search = _search;
	}

	/**
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}

	/**
	 * @param nd the nd to set
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}

	/**
	 * @return the rows
	 */
	public String getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}

	
}
