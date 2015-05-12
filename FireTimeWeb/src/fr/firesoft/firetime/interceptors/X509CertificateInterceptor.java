/**
 * 
 */
package fr.firesoft.firetime.interceptors;

import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.naming.ldap.LdapName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import fr.firesoft.firetime.action.AuthentifiedAction;
import fr.firesoft.firetime.authentification.User;


/**
 * @author xbeaufils
 *
 */

public class X509CertificateInterceptor extends AbstractInterceptor implements StrutsStatics {

	Logger log = LoggerFactory.getLogger(X509CertificateInterceptor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8845826642774145459L;

	/**
	 * 
	 */
	public X509CertificateInterceptor() {
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
	    final ActionContext context = arg0.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
	    HttpSession session = request.getSession();
	    if (session.getAttribute("currentUser") == null)
	    	return Action.LOGIN;
	    if  (session.getAttribute("currentUser") != null){
	    	User currentUser = (User) session.getAttribute("currentUser");
	    	((AuthentifiedAction) arg0.getAction()).setIdfEchelon(currentUser.getOrganisationId());
	    	return arg0.invoke();
	    }
	    // FIXME Unreachable code
	    if (session.getAttribute("certifSerialNumber") != null)
	    	if (session.getAttribute("authentifiedEchelon") != null ) {
  				((AuthentifiedAction) arg0.getAction()).setIdfEchelon(( Integer) session.getAttribute("authentifiedEchelon"));
  				 return arg0.invoke();
	    	}
	    X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
   		//log.debug("Client Certificate [" +  certs.length);
   		boolean certFiresoft = false;
   		Integer idfEchelon = null;
   		if (certs != null) {
        	for (int i = 0; i < certs.length; i++) {
        		Date today = new Date();
        		if (today.after(certs[0].getNotAfter()) )
        				throw new Exception();
        		Principal principal = certs[0].getSubjectDN();
        		log.debug("serialNumber {}", certs[0].getSerialNumber());
           		LdapName ldapName = new LdapName(principal.getName());
           		for (int j = 0; j < ldapName.size(); j++) {
           			// FIXME if (ldapName.get(j).startsWith("CN=firesoft.fr"))
           			certFiresoft = true;
           			log.debug("ldapName {}", ldapName.get(j));
           			if (ldapName.get(j).startsWith("CN=")) { 
           				idfEchelon = Integer.parseInt(ldapName.get(j).substring(3));
           				//idfEchelon = 1;
           				session.setAttribute("authentifiedEchelon", idfEchelon);
           				((AuthentifiedAction) arg0.getAction()).setIdfEchelon(idfEchelon);
           				session.setAttribute("certifSerialNumber", certs[i].getSerialNumber());
           			}
           		}
           		if (! certFiresoft)
           			return Action.LOGIN;
           		log.debug("Client Certificate [" + i + "] = " + certs[i].toString());
        	}
        }
   		else
   			return Action.LOGIN;
/*   		if (certFiresoft && idfEchelon != null  && strOrganisation != null) {
   			//((AuthentifiedAction) arg0.getAction()).setIdfEchelon(idfEchelon);
   			((AuthentifiedAction) arg0.getAction()).setOrganisation(strOrganisation);
   		}
*/   		//request.setAttribute("idfEchelon", idfEchelon);
   		//request.setAttribute("organisation", strOrganisation);
		return arg0.invoke();
	}

}
 