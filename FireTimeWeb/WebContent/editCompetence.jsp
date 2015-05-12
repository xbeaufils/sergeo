<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form action="saveCompetence" id="formCompetence" method="POST" namespace="/effectif"  theme="simple">
<s:hidden name="idfCompetenceAgent"></s:hidden>
<s:hidden name="idfAgent"></s:hidden>
<table class ="formulaire">
	<tr>
		<td>Emploi opérationnel :</td>
		<td colspan="3"><s:select list="lstEmploi" listKey="idEmploi" listValue="libelle" name="idfEmploiOpe" cssClass="inputbox"></s:select></td>
	</tr>
	<tr>
		<td>Début :</td>
		<td>
			<s:textfield name="debut" cssClass="inputbox"></s:textfield></td>
		<td>Fin :</td>
		<td>
			<s:textfield name="fin" cssClass="inputbox" id="dteFinCompetence" disabled="true"></s:textfield>
			<input type="checkbox" id="degriseFinCompetence" onClick="degriser(this)">Cloturé</input>
			
	</tr> 
	<tr>
		<td colspan="4">
		<sj:submit formIds="formCompetence" targets="dlgCompetence"  button="true" buttonIcon="ui-icon-plusthick" key="save"></sj:submit>
	   	</td>
	</tr>
	</table>
</s:form>
