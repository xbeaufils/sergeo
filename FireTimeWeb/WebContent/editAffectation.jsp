<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form action="saveAffectation" method="POST" namespace="/effectif" id="formAffectation" theme="simple">
<s:hidden name="idfAffectation"></s:hidden>
<table class ="formulaire">
	<tr>
		<td>Grade :</td>
		<td><s:select list="lstGrade" listKey="id" listValue="libelle" name="idfGrade" cssClass="inputbox"></s:select></td>
		<td>Organigramme :</td>
		<td></td>
	</tr>
	<tr>
		<td>Debut :</td>
		<td>
			<s:textfield name="debut" cssClass="inputbox"></s:textfield></td>
		<td>Fin :</td>
		<td>
			<s:textfield name="fin" cssClass="inputbox" id="dteFinAffectation" disabled="true"></s:textfield>
			<input type="checkbox" id="degriseFinAffectation" onClick="degriser(this)">Cloturé</input>
			
	</tr>
	<tr>
		<td colspan="4">
		<sj:submit formIds="formAffectation" targets="dlgAffectation" button="true" buttonIcon="ui-icon-plusthick" key="save"></sj:submit>
	   	</td>
	</tr>
	</table>
</s:form>
