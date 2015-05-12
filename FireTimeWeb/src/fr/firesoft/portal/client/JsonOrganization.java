/**
 * 
 */
package fr.firesoft.portal.client;

/**
 * @author beaufils
 *
 */
public class JsonOrganization {

//	  "comments": "",
//	  "companyId": 10154,
//	  "countryId": 0,
	  private String name; //": "SDIS38",
	  private Integer organizationId; //": 10612,
	  private Integer parentOrganizationId; //": 0,
//	  "recursable": true,
//	  "regionId": 0,
	  private Integer statusId; //": 12017,
	  private String treePath; //": "/10612/",
	  private String type; //": "regular-organization"	
	 /**
	 * 
	 */
	public JsonOrganization() {
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the organizationId
	 */
	public Integer getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	/**
	 * @return the parentOrganizationId
	 */
	public Integer getParentOrganizationId() {
		return parentOrganizationId;
	}
	/**
	 * @param parentOrganizationId the parentOrganizationId to set
	 */
	public void setParentOrganizationId(Integer parentOrganizationId) {
		this.parentOrganizationId = parentOrganizationId;
	}
	/**
	 * @return the statusId
	 */
	public Integer getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the treePath
	 */
	public String getTreePath() {
		return treePath;
	}
	/**
	 * @param treePath the treePath to set
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
