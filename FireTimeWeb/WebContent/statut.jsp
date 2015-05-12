<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page import="fr.firesoft.fireTime.ext.horaire.Statut" %>
<div id="divFormStatut" >
	<s:form id="formStatut" action="saveStatut" namespace="/periode" theme="simple" onsubmit="closeDialogStatut();">
		<table class ="formulaire">
		<tr><td>Libellé :</td>
		<td colspan="3">
			<s:property value="model.libelle" />
		</td></tr>
		<tr><td>Code :</td>
			<td>
				<s:property value="model.code" />
			<td>Durée :</td>
			<td>
				<s:property value="model.duree" />
		</tr>
		<tr><td>Présence :</td>
		<td colspan="3">
			<s:property value="%{getText('Presence.'+model.presence)}" />
		</td></tr>
		<tr>
		<td colspan="4">
			<div>
				<table>
					<s:checkboxlist list="lstPlage" name="model.lstIdfPlage" 
					listKey="idfPlage" listValue="libelle" 
					cssStyle="vertical" theme="fire"></s:checkboxlist>
				</table>
			</div>
		</td></tr>
		<tr><td colspan="4">
			<s:url namespace="/periode" action="saveStatut" id="urlSaveStatut" ></s:url>
			<sj:submit formIds="formStatut" href="%{urlSaveStatut}" targets="dlgStatut" button="true" buttonIcon="ui-icon-check" key="save"></sj:submit>
		</td></tr>
		</table>
	</s:form>
</div>