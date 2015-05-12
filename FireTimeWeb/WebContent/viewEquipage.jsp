<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<table class="moduletable">
<tr>
	<th colspan="3">Emploi</th>
</tr>
<s:iterator value="lstPoste" status="stPoste" var="varPoste">
	<tr class="<s:if test="#stPoste.odd == true ">pair</s:if><s:else>impair</s:else>">
	<td>
		<s:url action="poste_" method="removeEmploi" namespace="/poste" id="urlDeleteEmploi">
			<s:param name="idfPoste" value="%{#varPoste.idfPoste}"></s:param>
		</s:url>
		<sj:a href="%{urlDeleteEmploi}" onCompleteTopics="loadEquipage" button="true" buttonIcon="ui-icon-trash" key="delete"></sj:a>
	</td>
	<td><s:property value="emploi.libelle"/></td>
	<td>
		<s:url action="poste_" method="upDownEmploi" namespace="/poste" id="urlDownEmploi">
			<s:param name="idfPoste" value="%{#varPoste.idfPoste}"></s:param>
		</s:url>
		<sj:a href="%{urlDownEmploi}" onCompleteTopics="loadEquipage" button="true" buttonIcon="ui-icon-arrowthickstop-1-s" key="delete"></sj:a>
	
	</td>
	</tr>
</s:iterator>
</table>
<s:url action="equipage_" method="select" namespace="/agres" id="urlSelectEquipage">
	<s:param name="idfEquipageSelected" value="%{#session.currentEquipage.idfEquipage}"></s:param>
</s:url>
<sj:a openDialog="editEquipage" href="%{urlSelectEquipage}" button="true" buttonIcon="ui-icon-pencil"></sj:a>
<s:url action="equipage_" method="viewAdd" namespace="/poste" id="urlAdd">
	<s:param name="idfEquipageSelected" value="%{#session.currentEquipage.idfEquipage}"></s:param>
</s:url>
<sj:a href="%{urlAdd}" openDialog="editPoste" button="true" buttonIcon="ui-icon-plusthick">Ajouter un poste</sj:a>
