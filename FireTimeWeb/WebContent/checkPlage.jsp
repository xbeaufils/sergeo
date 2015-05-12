<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table class="besoin">
	<tr>
	<th class="ui-widget-header" colspan="4"><s:property value="besoinPlage.libelle"/></th>
	</tr>
	<tr>
		<th>Emploi</th><th>Voulu</th><th>Obtenu</th><th>Affecté</th>
	</tr>
	<s:iterator value="besoinPlage.emploisCollection">
		<tr>
			<td class="ui-widget-content"><s:property value="libelle"/></td>
			<td class="ui-widget-content"><s:property value="nbVoulu"/> </td>
			<td class="ui-widget-content"><s:property value="nbObtenu"/></td> 
			<td class="ui-widget-content"><s:property value="nbAffecte"/></td> 
		</tr>
	</s:iterator>
</table>
    