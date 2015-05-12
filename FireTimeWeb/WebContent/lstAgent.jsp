<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<sj:head  locale="fr" jqueryui="true" jquerytheme="blitzer"/>
  	<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Gestion des agents</title>
	<link href="<%=request.getContextPath()%>/css/moduletable.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
    $(document).ready(function() {
		$('#agentTable').dataTable( {
			"bJQueryUI": true,
			"bPaginate": false,
            "bScrollCollapse": true,
            "sScrollY": "400px",
            "bAutoWidth": false,
            "oLanguage": {
                        "sInfo": "_TOTAL_ agents"
             },
             "bFilter": true,
             "bSort" : true,
             "bProcessing": true
			} );
		
    });	
	function openCompetence(idfAgent) {
		$("#frmAddCompetence_idfAgent").val(idfAgent);
		$.publish('openCompetenceTopic');
	}
	
	function formatCompetence(cellvalue, options, rowObject) {
			return "<span class='ui-icon ui-icon-extlink' onClick='javascript:openCompetence("+rowObject.idfAgent+")' ></span>";
	}
	
 	function degriser(checkBox) {
		var inputId;
		switch (checkBox.id) {
		case 'degriseFinCompetence':
			inputId = 'dteFinCompetence';
			break;
		case 'degriseFinAffectation':
			inputId = 'dteFinAffectation';
			break;
		}
		if ($(checkBox).is(':checked')) {
			$("#" + inputId).removeAttr('disabled');
		}
		else {
			$("#" + inputId).attr('disabled', true);
		}
	}
	</script>

</head>
<body style="font-size: 0.8em">
	<jsp:include page="menu.jsp" >
		<jsp:param value="currentPage" name="agent"/>
	</jsp:include>
	<s:url action="ListAgent" namespace="/effectif/json" id="viewAgents"></s:url>
	<s:url action="ListAffectations" namespace="/effectif/json" id="urlViewJsonAffectation"></s:url>
	<s:url action="ListCompetence" namespace="/effectif/json" id="urlViewJsonCompetence"></s:url>
	<s:url action="DeleteCompetence"namespace="/effectif/json" id="urlDelJsonCompetence"   ></s:url>       		
	<sjg:grid
		id="agentGridtable"
		href="%{viewAgents}"
		gridModel="lstAgent"
		rowNum="-1" 
		width="900" height="500"
		navigator="true" 
		pager="true"
		navigatorAdd="false"
		navigatorEdit="true"
		navigatorDelete="false" 
		prmNames="{search: null,id:'idfAgentSelected', nd: null, rowid: 'idfAgentSelected'}"
		editurl="%{updatePlageHoraire}"
		navigatorAddOptions="{height:250, width:420, draggable:true}"
		navigatorEditOptions="{height:250, width:420, draggable:true}"
		caption="Agents"  >
		<sjg:grid
			id="CompetenceGridtable"
			subGridUrl="%{urlViewJsonCompetence}"
			gridModel="lstCompetence"
			rowNum="-1" 
			width="600"
			navigator="true" 
			navigatorAdd="false"
			navigatorEdit="false"									 
			prmNames="{search: null,id:'idfCompetenceAgentSelected', nd: null, rowid: 'idfCompetenceAgentSelected'}"
			editurl="%{urlDelJsonCompetence}"
			caption="Compétences">
			<sjg:gridColumn name="idfCompetenceAgent" key="true" title="" hidden="true"/>
			<sjg:gridColumn name="emploi" title="Emploi"/>
			<sjg:gridColumn name="debut" title="Debut" formatter="date"
						formatoptions="{newformat : 'd-m-Y', srcformat : 'Y-m-d H:i:s'}"/>
			<sjg:gridColumn name="fin" title="Fin" formatter="date"
						formatoptions="{newformat : 'd-m-Y', srcformat : 'Y-m-d H:i:s'}"/>
			
			</sjg:grid>
		<sjg:gridColumn name="idfAgent" key="true" title="idfAgent" hidden="true" />
		<sjg:gridColumn name="matricule" title="Matricule" editable="true"/>
		<sjg:gridColumn name="nom" title="Nom" editable="true"/>
		<sjg:gridColumn name="prenom" title="Prénom" editable="true"/>
		<sjg:gridColumn name="grade.libelle" title="Grade"/>
		<sjg:gridColumn name="debut" title="Debut" formatter="date"
						formatoptions="{newformat : 'd-m-Y', srcformat : 'Y-m-d H:i:s'}"/>
		<sjg:gridColumn name="fin" title="Fin" formatter="date"
						formatoptions="{newformat : 'd-m-Y', srcformat : 'Y-m-d H:i:s'}"/>
		<sjg:gridColumn name="name" 
						 title="Actions" 
						 editable="false" 
						 sortable="false"
						 align="center"
						 formatter="formatCompetence"/>
	</sjg:grid>
	<s:form action="competence_prepare" namespace="/effectif" id="frmAddCompetence" cssStyle="display:none;">
		<s:textfield name="idfAgent"></s:textfield>
	</s:form>
	<s:url action="competence_prepare" namespace="/effectif" id="urlAddCompetence"></s:url> 
	<sj:dialog id="dlgCompetence" width="800" title="Compétences" 
			formIds="frmAddCompetence" href="%{urlAddCompetence}" 
			autoOpen="false" openTopics="openCompetenceTopic"></sj:dialog>

</body>
</html>