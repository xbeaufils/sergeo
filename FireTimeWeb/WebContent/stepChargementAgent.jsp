<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test="#session.entete != null && selectedTypeImport != null">
	<jsp:include page="/DivAnalyseCsv.jsp"></jsp:include>
	<script type="text/javascript">
		$('#linkPrecedent').click(function() {
		  	$('#tabEtape2').removeClass('tabUnselected');
		  	$('#tabEtape2').addClass('tabSelected');
		  	$('#tabEtape3').removeClass('tabSelected');
		  	$('#tabEtape3').addClass('tabUnselected');
		  	
		});
		$('#linkSuivant').click(function() {
		  	$('#tabEtape4').removeClass('tabUnselected');
		  	$('#tabEtape4').addClass('tabSelected');
		  	$('#tabEtape3').removeClass('tabSelected');
		  	$('#tabEtape3').addClass('tabUnselected');
		  	
		});
	</script>
	<sj:submit formIds="frmLoad" cssClass="button" key="send" targets="divEtape3"  button="true" buttonIcon="ui-icon-gear"/>
</s:if>
<s:else>
	<div style="float: left;">
		<s:url action="fileUploadAgent"namespace="/config"  id="urlFileUploadAgent"></s:url>
		<s:form id="frmUploadAgent" action="fileUploadAgent" enctype="multipart/form-data" namespace="/config">
			 <s:file name="fileUpload" label="Fichier d'entrée" />
			 <s:hidden name="selectedTypeImport" value="AGENT"></s:hidden>
			 <s:textfield name="separateurChamp" value="," label="Séparateur de champs" maxlength="1"></s:textfield>
		     <sj:submit key="load" href="%{urlFileUploadAgent}" formIds="frmUploadAgent" targets="divEtape3"  button="true" buttonIcon="ui-icon-gear" ></sj:submit>
		</s:form>
	</div>
	<div class="box_shadow" >
	<p>Cet écran vous permet de charger le fichier des agents de votre caserne. Sergeo attend les données suivantes :</p>
	<ul>
		<li>son matricule</li>
		<li>son nom</li>
		<li>son prénom</li>
		<li>sa date de naissance</li>
	</ul>
	</div>
</s:else>