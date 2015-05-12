<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="divFormStatut" >
<s:form id="formStatut" action="saveStatut" method="POST" namespace="/periode"  theme="simple" onsubmit="closeDialogStatut();">
<s:hidden name="idfStatut"></s:hidden>
<table class ="formulaire">
	<tr><td>Libellé :</td>
	<td colspan="3">
		<s:textfield name="libelle" label="Libellé" cssClass="inputbox"></s:textfield>
	</td></tr>
	<tr><td>Code :</td>
		<td><s:textfield name="code" label="Code" cssClass="inputbox" size="3" ></s:textfield></td>
		<td>Durée :</td>
		<td><s:textfield name="duree" label="Durée" cssClass="inputbox"size="3" ></s:textfield>
	</tr>
	<tr><td>Présence :</td>
	<td colspan="3">
		<s:radio list="lstPresence" listValue="%{getText('Presence.'+name())}" name="presence"></s:radio>
	</td></tr>
	<tr>
	<td colspan="4">
		<div>
			<table>
				<s:checkboxlist list="lstPlage" name="lstIdfPlage" 
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