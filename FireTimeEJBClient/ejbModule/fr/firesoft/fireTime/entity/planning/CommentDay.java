/**
 * 
 */
package fr.firesoft.fireTime.entity.planning;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.firesoft.fireTime.entity.effectif.Agent;

/**
 * @author beaufils
 *
 */
@NamedQueries({
	@NamedQuery(name="CommentDay.ListForMonthAndAgent",
		query="FROM CommentDay "
			+ "WHERE agent.idfAgent = :idfAgent "
			+ "AND dteComment between :debut AND :fin "
			+ "AND idOrganization = :idOrga"),
	@NamedQuery(name="CommentDay.SelectForDayAndAgent",
		query="FROM CommentDay "
			+ "WHERE agent.idfAgent = :idfAgent "
			+ "AND dteComment = :day "
			+ "AND idOrganization = :idOrga"),
	@NamedQuery (name="CommentDay.selectForEchelonBetweenDate",
		query="FROM CommentDay cmt " +
				"WHERE cmt.idOrganization = :idfEchelon " +
				"AND cmt.dteComment between :debut AND :fin")

})
@Entity 
@Table (name="COMMENT_DAY", schema="firesoft")
public class CommentDay {

	private Integer idfComment;
	private Calendar dteComment; 
	private String comment;
	private Agent agent;
	private Integer idOrganization;
	/**
	 * 
	 */
	public CommentDay() {
	}
	@Id 
	@Column (name="IDF_COMMENT")
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	 * @return the dteComment
	 */
	@Column (name="DATE_COMMENT")
	public Calendar getDteComment() {
		return dteComment;
	}
	/**
	 * @param dteComment the dteComment to set
	 */
	public void setDteComment(Calendar dteComment) {
		this.dteComment = dteComment;
	}
	/**
	 * @return the comment
	 */
	@Column (name="COMMENT")
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the agent
	 */
	@OneToOne
	@JoinColumn (name="IDF_AGENT")
	public Agent getAgent() {
		return agent;
	}
	/**
	 * @param agent the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	/**
	 * @return the idOrganization
	 */
	@Column (name="ID_ORGA")
	public Integer getIdOrganization() {
		return idOrganization;
	}
	/**
	 * @param idOrganization the idOrganization to set
	 */
	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
	}

}
