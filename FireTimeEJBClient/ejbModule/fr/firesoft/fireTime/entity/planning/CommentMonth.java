/**
 * 
 */
package fr.firesoft.fireTime.entity.planning;

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
	@NamedQuery(name="CommentMonth.SelectForAgent",
			query="FROM CommentMonth "
			+ "WHERE agent.idfAgent = :idfAgent "
			+ "AND month = :month "
			+ "AND year = :year "
			+ "AND idOrganisation = :idOrga")
})
@Entity 
@Table (name="COMMENT_MONTH", schema="firesoft")
public class CommentMonth {


	private Integer idfComment;
	private String comment;
	private Integer month;
	private Integer year;
	private Agent agent;
	private Integer idOrganization;
	/**
	 * 
	 */
	public CommentMonth() {
	}
	/**
	 * @return the idfComment
	 */
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
	 * @return the month
	 */
	@Column (name="MONTH")
	public Integer getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	@Column (name="YEAR")
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
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
