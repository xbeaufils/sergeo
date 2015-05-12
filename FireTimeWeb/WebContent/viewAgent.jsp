<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<table class="moduletable" style="margin-left: 25%; margin-right:25%; width: 50%;">
	<thead>
	<tr><th colspan="4">Agent</th>
	</thead>
	<tbody>
	<tr>
		<td>Matricule : </td>
		<td><s:property value="#session.currentAgent.matricule"/> </td>
	</tr>
	<tr>
		<td>Nom :</td>
		<td><s:property value="#session.currentAgent.nom"/> </td>
		<td>Prénom :</td>
		<td><s:property value="#session.currentAgent.prenom"/></td>
		</tr>
	</tbody>
</table>
