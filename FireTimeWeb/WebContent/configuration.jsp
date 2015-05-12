<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<sj:head locale="fr" jqueryui="true" jquerytheme="blitzer" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/style_wizard.css" rel="stylesheet" type="text/css">
		<link href="css/shadowbox.css" rel="stylesheet" type="text/css">
  		<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
		<title>Configuration</title>
	</head>
	<body>
	<jsp:include page="menu.jsp" >
		<jsp:param value="currentPage" name="agent"/>
	</jsp:include>
	
  	<sj:tabbedpanel id="wizardTab" selectedTab ="0" disabledTabs="1,2,3,4">
		<sj:tab id="tabEtape1" target="divEtape1" label="Etape 1: Grades" onselect="" ></sj:tab>
		<sj:tab id="tabEtape2" target="divEtape2" label="Etape 2: Emplois opérationnels" disabled="true"></sj:tab>
		<sj:tab id="tabEtape3" target="divEtape3" label="Etape 3: Chargement des agents" disabled="true"></sj:tab>
		<sj:tab id="tabEtape4" target="divEtape4" label="Etape 4: Chargement des affectations" disabled="true"></sj:tab>
		<sj:tab id="tabEtape5" target="divEtape5" label="Etape 5: Chargement des compétences" disabled="true"></sj:tab>
		<sj:div id="divEtape1" href="stepGrade.jsp"></sj:div>
		<sj:div id="divEtape2" href="stepEmploiOperationnel.jsp"></sj:div>
		<sj:div id="divEtape3" href="stepChargementAgent.jsp"></sj:div>
		<sj:div id="divEtape4" href="stepChargementAffectation.jsp"></sj:div>
		<sj:div id="divEtape5" href="stepChargementEmploi.jsp"></sj:div>
	</sj:tabbedpanel>

	</body>
</html>
