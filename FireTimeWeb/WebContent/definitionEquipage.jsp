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
	<title>Definition Equipage</title>
	<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
	
	<s:url id="smdUrl" namespace="/equipage/json" action="SMDAction" />
	<script>
	    $(document).ready(function() {
	        $.subscribe('startDrag', function(event,element) {
	        	console.log("Element " + element);
	         });
	        $.subscribe('stopDrag', function(event,element) {
	        	console.log("Element " + element);
	         });
	        $.subscribe('endDrop', function(event,element) {
	        	console.log("Element " + element);
	         });
	        $.subscribe('endDrag', function(event,element) {
				$.ajax({
				 	type: 'POST',
					url:'${smdUrl}',
					data: '{"params":[' + event.originalEvent.ui.item[0].id + ', ' + event.originalEvent.ui.item[0].rowIndex + '],"method":"changeRow","id":5}',
	    			contentType:"application/json-rpc" ,
					success: function (d){ console.log(d); },
					error: function(){alert('HAAAA!!!');}
				});
	         });
	
	    });
	</script>
</head>
<body style="font-size: 0.8em;">
	<jsp:include page="menu.jsp" >
		<jsp:param value="currentPage" name="equipage"/>
	</jsp:include>
	<s:url action="view" namespace="/equipage/json" id="urlViewEquipage" ></s:url> 
	<s:url action="editPoste" namespace="/equipage/json" id="urlEditEquipage" ></s:url> 
	<s:url action="viewPoste" namespace="/equipage/json" id="urlViewPoste" ></s:url> 
	<sjg:grid gridModel="lstEquipage" 
			id="equipageGrid" 
			href="%{#urlViewEquipage}"  
			altRows="true"  
			width="450"
			pager="true" 
			navigator="true"				
			navigatorCloneToTop="false"
			navigatorAdd="false"
       		navigatorEdit="false"
			name="equipageGridName" 
			prmNames="{search: null,id:'idfPoste', nd: null}"
			reloadTopics="loadEquipage"
       		resizable="true" 
       		caption="Equipages"
       		sortableRows="true">
    	<sjg:grid gridModel="lstPoste" 
    			subGridUrl="%{urlViewPoste}"
 				editurl="%{urlEditEquipage}"
				altRows="true"  
				width="450"
				pager="true" 
				navigator="true"				
				navigatorCloneToTop="false"
				navigatorAdd="false"
	       		navigatorEdit="false"
				name="posteGridName" 
    			prmNames="{search: null, id:'idfPoste', nd: null, subgridid:'idfEquipage'}"
				reloadTopics="loadPoste"
       			resizable="true" 
        		sortableRows="true" 
       			sortableOnUpdateTopics="endDrag" 
       			rownumbers="true" 
    			caption="Postes">
        	<sjg:gridColumn name="idfPoste" title="idf poste" key="true" hidden="true"></sjg:gridColumn>
	       	<sjg:gridColumn name="idfEquipage" title="id equipage" hidden="true"></sjg:gridColumn>	       	
	       	<sjg:gridColumn name="equipage" title="Equipage"></sjg:gridColumn>
	       	<sjg:gridColumn name="idEmploi" title="id emploi" hidden="true"></sjg:gridColumn>	
	       	<sjg:gridColumn name="emploi" title="Emploi"></sjg:gridColumn>
	       	<sjg:gridColumn name="optionnel" title="Optionnel"></sjg:gridColumn>	
	       	<sjg:gridColumn name="affichage" title="Affichage" hidden="true"></sjg:gridColumn>	
    	</sjg:grid>	
       	<sjg:gridColumn name="idfEquipage" title="id equipage" key="true" hidden="true"></sjg:gridColumn>	       	
       	<sjg:gridColumn name="libelle" title="Equipage"></sjg:gridColumn>
	</sjg:grid>
	<br>
	<sj:a 
   		openDialog="editPoste" 
   			button="true"
   			buttonIcon="ui-icon-newwin">
   			Ajouter un emploi
   	</sj:a>
	<sj:a 
   		openDialog="editEquipage" 
   			button="true"
   			buttonIcon="ui-icon-newwin">
   			Ajouter un agres
   	</sj:a>
   	<s:url id="urlEditPoste" namespace="/poste" action="equipage_viewAdd"></s:url>
    <sj:dialog title="Poste" href="%{urlEditPoste}" autoOpen="false" id="editPoste" closeTopics="closeDialogPoste" width="640" height="180"></sj:dialog>
 	<s:url id="urlEditAgres" namespace="/agres" action="equipage_create "></s:url>
    <sj:dialog title="Agres" href="%{urlEditAgres}" autoOpen="false" id="editEquipage" width="640" height="180"></sj:dialog>
</body>
</html>