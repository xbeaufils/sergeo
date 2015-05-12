<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<sj:dialog title="Besoin" autoOpen="false" id="dlgBesoin" onCloseTopics="closeBesoin" width="640" height="180" modal="true"></sj:dialog>
<table class="moduletable">
	<tr>
		<th>Besoin</th>
		<th>Type équipage</th>
	</tr>
	<s:iterator value="#session.lstBesoin" status="statusBesoin">
		<s:url action="select" namespace="/besoin" id="urlSelectBesoin">
			<s:param name="idfBesoinSelected" value="idfBesoin"></s:param>
		</s:url>
		<tr class="<s:if test="#statusBesoin.odd">pair</s:if><s:else>impair</s:else>">
			<td><sj:a openDialog="dlgBesoin" href="%{#urlSelectBesoin}" button="true" buttonIcon="ui-icon-pencil"></sj:a><s:property value="libelle"/></td>
			<td><s:property value="equipage.libelle"/></td>
		</tr>	
	</s:iterator>
	<tr><td colspan="4">
	</td></tr>
</table>

<s:url action="add"  id="urlCreateBesoin" namespace="/besoin"></s:url>
<sj:a openDialog="dlgBesoin" href="%{urlCreateBesoin}" button="true" buttonIcon="ui-icon-plusthick">Ajouter un nouveau besoin</sj:a>
	