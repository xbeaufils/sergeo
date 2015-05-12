<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test="#session.entete != null && selectedTypeImport != null">
	<jsp:include page="/DivAnalyseCsv.jsp"></jsp:include>
	<script type="text/javascript">
		$('#linkPrecedent').click(function() {
		  	$('#tabEtape3').removeClass('tabUnselected');
		  	$('#tabEtape3').addClass('tabSelected');
		  	$('#tabEtape4').removeClass('tabSelected');
		  	$('#tabEtape4').addClass('tabUnselected');
		  	
		});
		$('#linkSuivant').click(function() {
		  	$('#tabEtape5').removeClass('tabUnselected');
		  	$('#tabEtape5').addClass('tabSelected');
		  	$('#tabEtape4').removeClass('tabSelected');
		  	$('#tabEtape4').addClass('tabUnselected');
		  	
		});
	</script>
	<sj:submit formIds="frmLoad" cssClass="button" key="send" targets="divEtape4"  button="true" buttonIcon="ui-icon-gear"/>
</s:if>
<s:else>
	<div style="float: left;">
		<s:url action="fileUploadAffection"namespace="/config"  id="urlFileUploadAffection"></s:url>
		<s:form action="fileUploadAffection" method="post" enctype="multipart/form-data" namespace="/config" id="frmUploadAffection">
			 <s:file name="fileUpload" label="Fichier d'entrée" />
			 <s:hidden name="selectedTypeImport" value="SITUATION"></s:hidden>
			 <s:textfield name="separateurChamp" value="," label="Séparateur de champs" maxlength="1"></s:textfield>
		     <sj:submit key="load" href="%{urlFileUploadAffection}" targets="divEtape4" button="true" buttonIcon="ui-icon-gear"></sj:submit>
		</s:form>
	</div>
	<div class="box_shadow" >
	<p>Cet écran vous permet de charger le fichier des affectations des agents de votre caserne.</p>
	<p>Sergeo attend les données suivantes :</p>
	<ul>
		<li>son matricule</li>
		<li>la date de début de son affectation dans votre caserne</li>
		<li>la date de fin de son affectation si il y en a une</li>
		<li>l'identifiant de son grade</li>		
	</ul>
	<p>Les dates doivent étre au format aaaa-MM-jj</p>
	</div>
</s:else>