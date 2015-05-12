/**
 * 
 */
package fr.firesoft.firetime.viewer;

import java.io.Serializable;

/**
 * @author beaufils
 *
 */
public class CommentDayViewer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -184750813800046369L;
	private Integer idfComment;
	private Integer jour;
	private Integer idfAgent;
	private Integer idOrganization;
	private String commentaire;
	/**
	 * 
	 */
	public CommentDayViewer() {
	}
	/**
	 * @return the idfComment
	 */
	public Integer getIdfComment() {
		return idfComment;
	}
	/**
	 * @param idfComment the idfComment to set
	 */
	public void setIdfComment(Integer idfComment) {
		this.idfComment = idfComment;
	}
	/**
	 * @return the jour
	 */
	public Integer getJour() {
		return jour;
	}
	/**
	 * @param jour the jour to set
	 */
	public void setJour(Integer jour) {
		this.jour = jour;
	}
	/**
	 * @return the idfAgent
	 */
	public Integer getIdfAgent() {
		return idfAgent;
	}
	/**
	 * @param idfAgent the idfAgent to set
	 */
	public void setIdfAgent(Integer idfAgent) {
		this.idfAgent = idfAgent;
	}
	/**
	 * @return the idOrganization
	 */
	public Integer getIdOrganization() {
		return idOrganization;
	}
	/**
	 * @param idOrganization the idOrganization to set
	 */
	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
	}
	/**
	 * @return the commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}
	/**
	 * @param commentaire the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
