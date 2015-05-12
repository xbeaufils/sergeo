<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<sj:head  locale="fr" jqueryui="true" jquerytheme="blitzer" loadAtOnce="true"/>
   		<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
	</head>
	<body style="font-size: 0.8em;">
		<script type="text/javascript">
			function formatBesoinsLink(cellvalue, options, rowObject) {
				return "<a href='#' onClick='javascript:openBesoinsDiv("+rowObject.idfPlage+")'>" + cellvalue + "</a>";
			}
			function openBesoinsDiv(plage) {
				$("#frmViewBesoin_idfPlage").val(plage);
				$.publish('BesoinLoad');
			}
		    $(document).ready(function() {
		        $.subscribe('treeClicked', function(event,element) {
		        	console.log("Element " + element);
		         });
		    	
		    });
		</script>
		<script type="text/javascript">
		function datePick (elem) {
          $(elem).datepicker(
          // {} // options here
                 $(elem).datepicker({dateFormat:'dd/mm/yyyy'})
          );
         }
		</script>
		<jsp:include page="../menu.jsp" >
			<jsp:param value="currentPage" name="statut"/>
		</jsp:include>
 		<s:url action="cycle_" method="viewTree" namespace="/horaire/json" id="viewTreeHoraire"  ></s:url>
  		<s:url action="cycle_" method="view" namespace="/horaire" id="viewHoraire"  >
			<s:param name="idfPlage" value="0"></s:param>
		</s:url>
		<div style="float: left; width: 300;">
	        <sjt:tree 
	                id="treeCycle" 
	                rootNode="nodes" 
	                href="%{viewTreeHoraire}"
	                nodeHref=""             
	                onClickTopics="treeClicked"/>
	        </div>
		<!-- Gestion des besoins --> 
		<s:url id="urlViewBesoins" action="liste" namespace="/besoin" ></s:url>
		<sj:div href="%{urlViewBesoins}" id="divBesoin" formIds="frmViewBesoin"
  				listenTopics="BesoinLoad"
				deferredLoading="true" 
				cssClass="ui-widget-content ui-corner-all"
				cssStyle="float: left; width: 350px;">
		</sj:div>
		
		<s:form action="liste" namespace="/besoin" id="frmViewBesoin" cssStyle="display:none;">
			<s:textfield name="idfPlage"></s:textfield>
		</s:form>
		
		<s:url action="createPeriode" id="urlCreatePeriode" namespace="/periode" includeParams="none"></s:url>
		<sj:dialog id="createPeriode" href="%{urlCreatePeriode}" autoOpen="false" modal="true" width="710" height="262" title="Période de validité"></sj:dialog>
		<script type="text/javascript">
			function formatLink(cellvalue, options, rowObject) {
				return "<a href='#' onClick='javascript:openDialog("+cellvalue+")'>" + cellvalue + "</a>";
			}
			function openDialog(employee) {
				$("#createPeriode").load("<s:property value="empurl"/>?id="+employee);
				$("#createPeriode").dialog('open');
			}
		</script>
		
	</body>
</html>