<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<sj:head  locale="fr" jqueryui="true" jquerytheme="blitzer"/>
  	<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Planning de besoin mensuel</title>	
	<link href="<%=request.getContextPath()%>/css/moduletable.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/planningBesoin.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/firesoft.css" type="text/css" rel="stylesheet"/>
</head>
 <body>
 <jsp:include page="menu.jsp" >
	<jsp:param value="currentPage" name="planning"/>
</jsp:include>
	<div class="ui-widget-header" style="height: 60px; padding-bottom: 10px; padding-left: 10px; padding-top: 2px;">
		<div class="titreSelection">
			
			<s:form action="ajaxBesoin_changeDateBesoin" namespace="/planning" theme="simple" id="frmMakeMonth">
				<div style="float: left;">
					<sj:radio list="#{'0':'Jan','1':'Fev', '2':'Mar', '3':'Avr', '4':'Mai', '5':'Juin', '6':'Juil', '7':'Aou', '8':'Sep', '9':'Oct','10':'Nov', '11':'Déc'}" 
							name="selectedMonth" onclick="$.publish('topicMakeMonth');"></sj:radio></div>
				<div style="float: left;">
					<s:select list="lstYearCritere" name="selectedYear" label="Année" onselect="$.publish('topicMakeMonth');"></s:select>
				</div>
 			</s:form>
 			<sj:submit listenTopics="topicMakeMonth" onBeforeTopics="planningBegin" onSuccessTopics="planningComplete" formIds="frmMakeMonth" targets="planingDiv" indicator="ajaxLoadingDlg" loadingText="Chargement du mois" cssStyle="display:none;" ></sj:submit>
			<div style="clear: left;"></div>
		</div>
	</div>
   <sj:dialog 
    	id="besoinDialog" 
    	autoOpen="false" 
    	modal="true" 
    	title="Besoins"
    	openTopics="openRemoteDialog"
    	width="600"
    />
   <sj:dialog 
    	id="effectifDialog" 
    	autoOpen="false" 
    	modal="true" 
    	title="Effectif" 
    	width="600"
    />
	<img id="indicatorPlanning" src="<%=request.getContextPath()%>/images/calculCycle.gif" style="display:none; margin-top:50px;" />
	<s:url action="ajaxBesoin_" method="makeListOfWeeks" namespace="/planning" id="selectBesoinMonth" includeParams="none"></s:url>
	<sj:div id="planingDiv" href="%{selectBesoinMonth}" indicator="indicatorPlanning" onSuccessTopics="planningComplete" loadingText="Chargement du mois" ></sj:div>
</body>
</html>
