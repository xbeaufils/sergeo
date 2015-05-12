<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 <s:form action="poste_addEmploi" namespace="/poste" theme="simple" id="formAddPoste">
 	<table class ="formulaire">
		<tr><td><s:select list="lstOperationnel" listKey="idEmploi" listValue="libelle" name="idEmploi"></s:select></td></tr>
		<tr><td><s:select list="lstEquipage" listKey="idfEquipage" listValue="libelle" name="idfEquipage"></s:select></td></tr>
		<tr><td><s:checkbox name="optionnel" cssClass="inputbox">Emploi optionnel</s:checkbox> </td></tr>
		<tr><td>
			<sj:submit targets="editPoste" formIds="formAddPoste" onSuccessTopics="loadEquipage_%{#session.currentEquipage.idfEquipage},loadEquipage,closeDialogPoste" button="true" buttonIcon="ui-icon-check" key="add"></sj:submit>
		</td></tr>
	</table>
</s:form >  
