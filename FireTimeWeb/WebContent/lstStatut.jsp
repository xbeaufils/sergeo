<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"  %>
<table class="moduletable">
<tr>
	<th>Appelation</th>
	<th>Code</th>
	<th>Duree</th>
</tr>
<s:iterator value="lstStatut" status="stStatus" >
	<tr class="<s:if test="#stStatus.odd == true ">pair</s:if><s:else>impair</s:else>">
		<td>
			<s:url id="selectStatutUrl" namespace="/periode" action="statut_select">
				<s:param name="idfStatutSelected" value="%{idfStatut}"></s:param>
			</s:url>
			<sj:a openDialog="dlgStatut" href="%{selectStatutUrl}" button="true" buttonIcon="ui-icon-pencil">Edit</sj:a>
		<s:property value="libelle"/></td>
		<td><s:property value="code"/></td>
		<td><s:property value="duree"/></td>
	</tr>
</s:iterator>
</table>
<s:url action="statut_create" id="urlCreateStatut" namespace="/periode"></s:url>
<sj:a href="%{urlCreateStatut}" button="true" buttonIcon="ui-icon-plusthick" openDialog="dlgStatut">Ajouter</sj:a>
