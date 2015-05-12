package fr.firesoft.portal.client.authentification;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import fr.firesoft.fireTime.ServiceLocatorFireTime;
import fr.firesoft.fireTime.bean.AuthentificationLocal;
import fr.firesoft.firetime.authentification.User;
import fr.firesoft.portal.client.JsonData;
import fr.firesoft.portal.client.JsonGroup;
import fr.firesoft.portal.client.JsonOrganization;
import fr.firesoft.portal.client.JsonRole;
import fr.firesoft.portal.client.JsonUser;



/**
 * Action implementation class ConnectLiferay
 */
public class ConnectLiferay extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	Logger log = LoggerFactory.getLogger(ConnectLiferay.class);
      
	private String userId ;
	private String companyId ;
	private String p_auth;
	private String key ;
	private String organizationId ;
	private String groupId;
	private String role ;
	protected HttpServletRequest servletRequest;
  /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectLiferay() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public String execute() throws Exception {
	    log.debug("key {} user id {}", new Object[]{key, userId} );
	    // Recherche d'une tentative de cross site
	    
	    String urlRootString = "http://localhost:9000/api/jsonws";	    
	    	    
		String urlParam = "p_auth=" + p_auth;
		urlParam += "&companyId=" + companyId;
		urlParam += "&className=com.liferay.portal.model.User";
		urlParam += "&tableName=CUSTOM_FIELDS";
		urlParam += "&columnName=APIKey";
		urlParam += "&classPK="+ userId;
		urlParam += "&p_auth=" + p_auth;
	    

//http://localhost:9000/api/secure/jsonws/expandovalue/get-data/company-id/10154/class-name/com.liferay.portal.model.User/table-name/CUSTOM_FIELDS/column-name/APIKey/classpk/10196?p_auth=RLD8Yx3a
	    String expandoKey= null;
	    String urlServiceString = "/expandovalue/get-json-data?";
	    log.debug("query {}",  urlRootString + urlServiceString + urlParam);

	    JsonData data = null;
	    HttpClient clientKey = HttpClientBuilder.create().build();
		HttpGet httpGetKey = new HttpGet(urlRootString + urlServiceString + urlParam);
       	for (javax.servlet.http.Cookie servletCookie : this.servletRequest.getCookies()) {
       		httpGetKey.addHeader("Cookie", servletCookie.getName() + "="+ servletCookie.getValue());       	    
 	    }
	    HttpResponse respExpando = clientKey.execute(httpGetKey);
	    try {
	        log.debug("statusLine {}", respExpando.getStatusLine());
	        HttpEntity entity1 = respExpando.getEntity();
	        expandoKey = EntityUtils.toString(entity1);
	        data = new Gson().fromJson(expandoKey, JsonData.class);
	        log.debug("[Web app] json User {}",  expandoKey);
	    } finally {
	        httpGetKey.releaseConnection();
	    }
	    if (data == null) {
	    	log.debug("[Web app] data is null  : {}", expandoKey );
	    	//response.getOutputStream().println("data is null " + expandoKey );	  	    	
	    	return LOGIN;
	    }
	    else if ( ! key.equals(data.getData())) {
	    	log.debug("differents keys : {} {}", new Object[]{data.getData(), key} );
	    	return LOGIN;
	    }
	    
	    JsonUser user= null;
	    HttpClient clientUser = HttpClientBuilder.create().build();
		HttpGet httpGetUser = new HttpGet(urlRootString + "/user/get-user-by-id/user-id/" + userId + "?p_auth=" + p_auth);
       	for (javax.servlet.http.Cookie servletCookie : this.servletRequest.getCookies()) {
       		httpGetUser.addHeader("Cookie", servletCookie.getName() + "="+ servletCookie.getValue());       	    
 	    }
	    HttpResponse respUser = clientUser.execute(httpGetUser);
	    try {
	        log.debug("Status Line {}", respUser.getStatusLine());
	        HttpEntity entity1 = respUser.getEntity();
	        String jsonUser = EntityUtils.toString(entity1);
	        log.debug("json User {}", jsonUser);
		    user = new Gson().fromJson(jsonUser, JsonUser.class);
	    } finally {
	        httpGetUser.releaseConnection();
	    }
	    if (user == null) {
	    	log.debug("User is null {}", userId);
	    	//response.getOutputStream().println("user is null " + userId);
	    	return LOGIN;	    	
	    }
	    else if ( ! user.getUserId().equals( Integer.parseInt(userId) ) ) {
	    	log.debug("differents User : {} {}",  new Object[]{user.getUserId(), userId });
	    	//response.getOutputStream().println("differents user : " + user.getUserId() + " " + userId);
	    	return LOGIN;
	    }
	    
	    
	    //response.getOutputStream().println("User email " + user.getEmailAddress());
	    //response.getOutputStream().println("User nom " + user.getLastName());
	    //response.getOutputStream().println("User id " + user.getUserId());
	    
	    urlServiceString = "/role/get-user-group-roles?";
	    urlParam = "p_auth=" + p_auth;
	    urlParam += "&user-id=" + userId;
	    urlParam += "&groupId=" + groupId;
	    urlParam += "&p_auth=" + p_auth;

	    JsonRole[] roles =null;
	    JsonRole role = null;
	    log.debug("query {}/role/get-user-group-roles/user-id/{}/group-id/{}?p_auth={}&timestamp=", 
	    		new Object[] {urlRootString, userId, groupId, p_auth, (new Date()).getTime()} );
	    HttpClient clientRole = HttpClientBuilder.create().build();
		HttpGet httpGetRole = new HttpGet(urlRootString + "/role/get-user-group-roles/user-id/" + userId + "/group-id/" + groupId + "?p_auth=" + p_auth);
       	for (javax.servlet.http.Cookie servletCookie : this.servletRequest.getCookies()) {
       		httpGetRole.addHeader("Cookie", servletCookie.getName() + "="+ servletCookie.getValue());       	    
 	    }
	    HttpResponse respRole = clientRole.execute(httpGetRole);
	    try {
	        log.debug("Status Line {}", respRole.getStatusLine());
	        HttpEntity entity1 = respRole.getEntity();
	        String roleString = EntityUtils.toString(entity1);
	        log.debug("json Role {}", roleString);
		    roles = new Gson().fromJson(roleString, JsonRole[].class);
	    } finally {
	        httpGetRole.releaseConnection();
	    }
	    if (roles == null) {
	    	log.debug("Roles is null {}", this.role);
	    	return LOGIN;
	    }
	    else  {
	    	for (JsonRole aRole : roles ) {
	    		if (aRole.getRoleId().equals(Integer.parseInt(this.role)))
	    			role = aRole;
	    	}
	    	if (role == null) {
	    		log.debug("Roles is null {}", this.role);
	    		//response.getOutputStream().println("Roles is null " + roleRequest);
		    	return LOGIN;
	    	}
	    	else {	    
	    		log.debug("Roles is : {}", role.getName());
	    		//response.getOutputStream().println("Role is : " + role.getName() );
	    	}
	    }	    	    	 
	    JsonGroup[] groups = null;
	    JsonGroup group = null;
	    urlServiceString = "/group/get-user-organizations-groups";
	    urlParam ="/user-id/" + user.getUserId();
	    urlParam += "/start/-1/end/-1?p_auth=" + p_auth;
	    
	    HttpClient clientGroup = HttpClientBuilder.create().build();
		HttpGet httpGetGroup = new HttpGet(urlRootString + urlServiceString + urlParam);
       	for (javax.servlet.http.Cookie servletCookie : this.servletRequest.getCookies()) {
       		httpGetGroup.addHeader("Cookie", servletCookie.getName() + "="+ servletCookie.getValue());       	    
 	    }
	    HttpResponse respGroup = clientGroup.execute(httpGetGroup);
	    try {
	        log.debug("Status Line {}", respGroup.getStatusLine());
	        HttpEntity entity1 = respGroup.getEntity();
	        String groupString = EntityUtils.toString(entity1);
		    System.out.println("[Web app] json group " + groupString);
		    groups = new Gson().fromJson(groupString, JsonGroup[].class);
	    } finally {
	        httpGetRole.releaseConnection();
	    }
	    if (groups == null){
	    	log.debug("Groups is null {}", groupId);
	    	//response.getOutputStream().println("Groups is null " + groupId);
	    	return LOGIN;
	    }
	    else  {
	    	for (JsonGroup aGroup : groups ) {
	    		if (aGroup.getGroupId().equals(Integer.parseInt(groupId)))
	    			group = aGroup;
	    	}
	    	if (group == null) {
	    		log.debug("group is null {}", groupId);
		    	return LOGIN;
	    		//response.getOutputStream().println("group is null " + groupId);
	    	}
	    	else {	    
	    		log.debug("Group is : {}", group.getName() );
	    		//response.getOutputStream().println("Group is : " + group.getName());
	    	}
	    }	    	
	    //http://localhost:9000/api/secure/jsonws/organization/get-organization/organization-id/10612?p_auth=vQns1j2Dp_auth
	    JsonOrganization organisation = null;
	    urlServiceString = "/organization/get-organization";
	    urlParam ="/organization-id/" + group.getClassPK();
	    urlParam += "?p_auth=" + p_auth;
	    HttpClient clientOrga = HttpClientBuilder.create().build();
		HttpGet httpGetOrga = new HttpGet(urlRootString + urlServiceString + urlParam);
       	for (javax.servlet.http.Cookie servletCookie : this.servletRequest.getCookies()) {
       		httpGetOrga.addHeader("Cookie", servletCookie.getName() + "="+ servletCookie.getValue());       	    
 	    }
	    HttpResponse respOrga = clientOrga.execute(httpGetOrga);
	    try {
	        log.debug("Status Line {}", respOrga.getStatusLine());
	        HttpEntity entity1 = respOrga.getEntity();
	        String orgaString = EntityUtils.toString(entity1);
	        log.debug("json orga {}", orgaString);
		    organisation = new Gson().fromJson(orgaString, JsonOrganization.class);
		    organisation.getTreePath().split("/");
	    } finally {
	        httpGetOrga.releaseConnection();
	    }
	    if (organisation == null) {
	    	log.debug("Organization is null {}", group.getClassPK());
	    	return LOGIN;
	    }
	    else if ( !organisation.getOrganizationId().equals( Integer.parseInt(organizationId) ) ) {
	    	log.debug("differents organisation : {} {}", new Object[]{ organisation.getOrganizationId(), organizationId });
	    	return LOGIN;
	    }
    	//response.getOutputStream().println("Organisation is " + organisation.getName() );	    	
    	 	    
	    User currentUser = new User();
	    currentUser.setNom(user.getLastName());
	    currentUser.setPrenom(user.getFirstName());
	    currentUser.setEmail(user.getEmailAddress());
	    currentUser.setUserId(user.getUserId());
	    currentUser.setProfil(role.getName());
	    currentUser.setOrganisationId(organisation.getOrganizationId());
	    currentUser.setOrganizationName(organisation.getName());
	    currentUser.setCompanyId(companyId);
	    HttpSession session = this.servletRequest.getSession();
	    session.setAttribute("currentUser", currentUser);
	    
	    AuthentificationLocal auth = ServiceLocatorFireTime.getAuthentificationBean();
	    auth.setToken(p_auth);	 
	    Map<String, String> mapCookies = new HashMap<String, String>();
	    for (javax.servlet.http.Cookie servletCookie : this.servletRequest.getCookies()) {
	    	mapCookies.put(servletCookie.getName() , servletCookie.getValue());
 	    }
	    auth.setCookies(mapCookies);
	    session.setAttribute("authentifcationCache", auth);
	    return SUCCESS;
	    /*
	    List<Group> userOrgGroups = GroupLocalServiceUtil.getUserOrganizationsGroups(userId, -1, -1);
	    
	    for (Group userOrgGroup : userOrgGroups) {
	        //get user roles related to user-role group relation %)
	       List<Role> userOrgRoles = RoleLocalServiceUtil.getUserGroupRoles(adminId, userOrgGroup.getGroupId());
	       //check user group is org relation
	       if (userOrgGroup.getClassName().equals(Organization.class.getName())) {
	           //get organization for user-org relation group
	           Organization organization = OrganizationLocalServiceUtil.fetchOrganization(userOrgGroup.getClassPK());
	           if (organization != null) {
	               for (Role userOrgRole : userOrgRoles) {
	                   if (userOrgRole.getType() == RoleConstants.TYPE_ORGANIZATION) {
	                       //YOUR code :)
	                   }
	               }
	           }
	       }
	   }
	    */
	    
	}


	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;		
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the p_auth
	 */
	public String getP_auth() {
		return p_auth;
	}

	/**
	 * @param p_auth the p_auth to set
	 */
	public void setP_auth(String p_auth) {
		this.p_auth = p_auth;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the organisationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public void setOrganizationId(String organisationId) {
		this.organizationId = organisationId;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the servletRequest
	 */
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

}
