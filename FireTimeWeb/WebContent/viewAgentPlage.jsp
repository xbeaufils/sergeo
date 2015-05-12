<%@ page language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%> 
<table id="agentTable" class="moduletable">
	<thead>
	<tr>
		<th>Matricule</th>
		<th>Nom</th>
		<th>Prénom</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="lstAgent" var="varAgent" status="stAgent">
		<tr  class="<s:if test="#stAgent.odd == true ">pair</s:if><s:else>impair</s:else>">
			<td><s:property value="#varAgent.matricule"/> </td>
			<td><s:property value="#varAgent.nom"/> </td>
			<td><s:property value="#varAgent.prenom"/></td>
		</tr>
	</s:iterator>	
	</tbody>
</table>
