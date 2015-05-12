<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <sj:head locale="fr" jqueryui="true" jquerytheme="blitzer" />
	<title>Chargement</title>
	<link href="<%=request.getContextPath()%>/css/menu.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/table.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/lightbox.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/horiz-drop-down.css" type="text/css" rel="stylesheet"/>
	<!-- script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.horizontalnav.js"></script -->
	<!--   script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.hoverIntent.minified.js"></script -->
 	<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
   	<script type="text/javascript" charset="utf-8">
    /*
    $(function(){
        $('.horizontal-dropdown').animatedHorizontalNav();
    });
    */7   
    </script> 
</head>
<body>
	<jsp:include page="menu.jsp" >
		<jsp:param value="currentPage" name="loader"/>
	</jsp:include>
<s:if test="#session.entete != null">
	<s:form action="trans" method="post" namespace="/load" theme="simple">
		<s:hidden name="typeImport" value="%{selectedTypeImport}"></s:hidden>
		<table class="moduletable">
			<thead>
			<tr>
				<s:iterator value="#session.entete" id="enteteCol" status="enteteStatus">
					<th>
					<s:property  value="#enteteCol"/>
						<s:select list="selectedTypeImport.xmlAttributes" 
						name="colTypeImport[%{#enteteStatus.index}]"
						headerKey="notSelected"
		    			headerValue="-- Non pris en compte --"></s:select> 
					</th>
				</s:iterator>
			</tr>
			</thead>
			<s:iterator value="#session.dataSample" id="row">
				<tr>
				<s:iterator value="#row" id="data">
					<td><s:property value="#data"/></td>
				</s:iterator>
				</tr>
			</s:iterator>
		</table>
		<s:submit cssClass="button" key="send"/>
	</s:form>
</s:if>
<s:else>
		<div id="light" class="white_content" style="display: block;">
			<div id="divCycle" style="color: dimgray; font-weight: bold; font-family: arial; background-color: whitesmoke; font-size: 11px;">
				<s:form action="fileUpload" method="post" enctype="multipart/form-data" namespace="/load">
					 <s:file name="fileUpload" label="Fichier d'entrée" />
					 <s:radio list="lstImport" listValue="%{getText('TypeImport.'+name())}" 
					 name="typeImport"></s:radio>
					 <s:textfield name="separateurChamp" value="," label="Séparateur de champs" maxlength="1"></s:textfield>					 
				     <s:submit cssClass="button" key="load"/>
				</s:form>
			</div>
		</div>
		<div id="fade" class="black_overlay" style="display: block;"></div>
</s:else>
</body>
</html>