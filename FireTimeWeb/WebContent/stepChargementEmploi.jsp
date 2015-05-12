<%@ page language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test="#session.entete != null && selectedTypeImport != null">
	<jsp:include page="/DivAnalyseCsv.jsp"></jsp:include>
	<sj:submit formIds="frmLoad" cssClass="button" key="send" targets="divEtape5"  button="true" buttonIcon="ui-icon-gear"/>
</s:if>
<s:else>
	<div style="float: left;">
	<s:form action="fileUpload" method="post" enctype="multipart/form-data" namespace="/config" id="frmUploadCompetence">
		<s:file name="fileUpload" label="Fichier d'entr�e" />
		<s:hidden name="typeImport" value="COMPETENCE"></s:hidden>
		<s:textfield name="separateurChamp" value="," label="S�parateur de champs" maxlength="1"></s:textfield>
		<sj:submit key="load" targets="divEtape2" button="true" buttonIcon="ui-icon-gear" id="subUploadCompetence"></sj:submit>	   
	</s:form>
	</div>
	<div class="box_shadow" >
		<p>Cet �cran vous permet de charger le fichier des emplois op�rationnels des agents de votre caserne.</p>
		<p>Sergeo attend les donn�es suivantes :</p>
		<ul>
			<li>son matricule</li>
			<li>la date de d�but de son affectation dans votre caserne</li>
			<li>la date de fin de son affectation si il y en a une</li>
			<li>l'identifiant de son emploi op�rationnel</li>		
		</ul>
		<b>NB:</b>Pour un agent, il peut y avoir plusieurs lignes d'emplois
	</div>
</s:else>