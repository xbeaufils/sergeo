<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<table class="moduletable" style="margin: 2%; width: 96%;">
	<thead>
	<tr>
		<th>Emploi opérationnel</th>
		<th>Début</th>
	</tr>
	
	</thead>
	<s:iterator value="lstCompetence">
		<s:url action="competence_select" namespace="/effectif" id="urlSelectCompet">
			<s:param name="idfSearchCompetence" value="idfCompetenceAgent"></s:param>
		</s:url>
		<tr>
			<td><sj:a href="%{urlSelectCompet}" openDialog="dlgCompetence" button="true" buttonIcon="ui-icon-pencil"></sj:a>
				<s:property value="emploi.libelle"/> </td>
			<td><s:date name="dteDebut" format="dd/MM/yyyy"/></td>
		</tr>
	</s:iterator>
	<s:url action="competence_prepare" namespace="/effectif" id="urlPrepareCompet"></s:url>
	<tr>
		<td colspan="2"><sj:a href="%{urlPrepareCompet}" openDialog="dlgCompetence" button="true" buttonIcon="ui-icon-plusthick">Ajouter une compétence</sj:a></td>
	</tr>
</table>
