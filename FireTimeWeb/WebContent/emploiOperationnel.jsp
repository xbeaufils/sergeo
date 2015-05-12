<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="blitzer" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  		<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
		<title>Concordances d'emplois opérationnels</title>
	</head>
	<body style="font-size: 0.8em">
		<jsp:include page="menu.jsp" >
			<jsp:param value="currentPage" name="statut"/>
		</jsp:include>
		<s:url action="emploi_show" namespace="/load" id="showEmploiUrl"></s:url>
		<s:url action="emploi_edit" namespace="/load" id="editEmploiUrl"></s:url>
		<sjg:grid gridModel="emploiOpeModel" id="emploiOpeGrid" 
				href="%{#showEmploiUrl}" 
				cellEdit="true" 
				cellurl="%{#editEmploiUrl}" 
				altRows="true" >
			<sjg:gridColumn name="idEmploi" title="identifiant" hidden="true" />
			<sjg:gridColumn name="libelle" title="Appellation"></sjg:gridColumn>
			<sjg:gridColumn name="code" editable="true" title="Code" ></sjg:gridColumn>
		</sjg:grid>
	
	</body>
</html>