<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:url action="affectation_prepare" namespace="/effectif" id="urlPrepareAffect"></s:url>
<%-- Affichage Affectation --%>
<table class="moduletable" style="margin: 2%; width: 96%;">
	<thead>
	<tr>
		<th>Grade </th>
		<th>Début</th>
		<th>Fin</th>					
	</tr>
	</thead>
	<s:iterator value="lstAffectations">
		<s:url action="affectation_select" namespace="/effectif" id="urlSelectAffect">
			<s:param name="idfSearchAffectation" value="idfAffectation"></s:param>
		</s:url>
		<tr>
			<td><s:property value="grade.libelle"/></td>
			<td>
				<sj:a href="%{urlSelectAffect}" openDialog="dlgAffectation" button="true" buttonIcon="ui-icon-pencil"></sj:a>
				<s:date name="debut" format="dd/MM/yyyy"/>
			</td>
			<td><s:date name="fin" format="dd/MM/yyyy"/></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="3"><sj:a href="%{urlPrepareAffect}" openDialog="dlgAffectation" button="true" buttonIcon="ui-icon-plusthick">Ajouter une affectation</sj:a></td>
	</tr>
</table>
