<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <sj:head locale="fr" jqueryui="true" jquerytheme="blitzer" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Périodes de validité</title>
   	<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
 	<link href="<%=request.getContextPath()%>/css/plage.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">
		function closeDialogStatut() {
			$('#editStatut').dialog('close');
		}
		function openBesoinsDiv(plage) {
			$("#frmAddBesoin_idfPlage").val(plage);
			$.publish('openBesoinTopic');
		}
		
		function formatImagePlage(cellvalue, options, rowObject) {
				return "<span class='ui-icon ui-icon-extlink' onClick='javascript:openBesoinsDiv("+rowObject.idfPlage+")' ></span>";
		}
		function openStatutDiv(statut) {
			$("#frmViewStatut_idfStatutSelected").val(statut);
			$("#frmViewStatut_idfStatut").val(statut);
			$.publish('openStatutTopic');
		}
		function formatImageStatut(cellvalue, options, rowObject) {
				return "<span class='ui-icon ui-icon-extlink' onClick='javascript:openStatutDiv("+rowObject.idfStatut+")' ></span>";
		}
		function datePick (elem) {
	      $(elem).datepicker(
	      // {} // options here
	             $(elem).datepicker({dateFormat:'dd/mm/yyyy'})
	      );
	     }
	</script>
	<style>
		.libelleCycle {
			margin-right: 30px; 
			padding-left: 15px;
			text-align: center;
		}
	</style>
</head>
<body style="font-size: 0.8em;">
	<jsp:include page="menu.jsp" >
		<jsp:param value="currentPage" name="planning"/>
	</jsp:include>
	<script>
		$(document).ready(function() {
			$.subscribe('topicSelectPeriode', function(event, data) {
				if (data.hrefparameter.split("=").length ==2) {
					$('div.libelleCycle.ui-state-active').removeClass('ui-state-active');
					var selectedCycle = data.hrefparameter.split("=")[1];
					$('#cycle_' + selectedCycle).addClass('ui-state-active');
				}
			});
		});
	</script>
	<div style="float: left; width:300px;">									
		<s:iterator value="lstCycle" status="stCycle" var="varCycle">
			<s:url action="viewPeriode" id="urlSelect" namespace="/periode"  includeParams="none">
		          <s:param name="idfCycleSelect" value="%{#varCycle.idfCycle}"></s:param>
		  	</s:url>
			<s:url action="modify" id="urlModify" namespace="/periode"  includeParams="none">
		          <s:param name="idfCycleSelect" value="%{#varCycle.idfCycle}"></s:param>
		  	</s:url>
			<div>
				<div style="background-image: url(/FireTimeWeb/images/border.gif); background-repeat: no-repeat;margin-left: 50px;">&nbsp;</div>
				<div style="background-image: url(/FireTimeWeb/images/border.gif);background-repeat: no-repeat;background-position: 50px;padding-left: 45px;">
					<div style="float: left;">
						<sj:a href="%{urlSelect}" targets="divPeriode" onClickTopics="topicSelectPeriode" id="link_%{#varCycle.idfCycle}" ><img src="/FireTimeWeb/images/timeIcon.gif"></sj:a>
					</div>
					<div style="margin-left: 35px;"> 
 						<sj:div  id="cycle_%{#varCycle.idfCycle}"  
								cssClass="ui-state-default ui-corner-top ui-corner-bottom libelleCycle">
							<div>
								<s:property value="%{#varCycle.libelle}"/>
								<sj:a openDialog="createPeriode" href="%{urlModify}" button="true" buttonIcon="ui-icon-pencil" ></sj:a>	
							</div>
							<div>	<s:date name="dateValidite" format="dd/MM/yyyy" />	</div>
						</sj:div>
				  	</div>
				</div>
				<div style="background-image: url(/FireTimeWeb/images/border.gif);background-repeat: no-repeat;margin-left: 50px;">&nbsp;</div>
			</div>									
		</s:iterator>
		<div><sj:a openDialog="createPeriode" button="true" buttonIcon="ui-icon-plusthick" >Ajouter</sj:a></div>
	</div>
	<sj:div id="divPeriode" style="float:left;width: 550px;" ></sj:div>
	<s:url action="createPeriode" id="urlCreatePeriode" namespace="/periode" includeParams="none"></s:url>
	<sj:dialog id="createPeriode" href="%{urlCreatePeriode}" autoOpen="false" modal="true" width="710" height="262" title="Période de validité"></sj:dialog>
</body>
</html>