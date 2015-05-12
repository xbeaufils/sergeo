<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 <s:actionerror theme="jquery" />
 <s:actionmessage theme="jquery"/>  
 <sj:a button="true" onclick="$(this).parent().dialog('close')">OK</sj:a>
  