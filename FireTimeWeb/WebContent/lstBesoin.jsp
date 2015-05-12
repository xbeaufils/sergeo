<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<table class="moduletable">
	<tr>
		<th colspan="3">Besoin</th>
		<th>Type équipage</th>
	</tr>
	<s:iterator value="lstBesoin" status="stBesoin" var="varBesoin">
		<tr class="<s:if test="#stBesoin.odd == true ">pair</s:if><s:else>impair</s:else>">
		<td><s:url action="delete" namespace="/besoin" id="urlDeleteBesoin">
				<s:param name="idfBesoinDelete" value="%{#varBesoin.idfBesoin}"></s:param>
				<s:param name="idfPlage" value="%{#varBesoin.plage.idfPlage}"></s:param>
				<s:param name="idfPlageHelper" value="%{#varBesoin.plage.idfPlage}"></s:param>
			</s:url>
			<sj:a href="%{urlDeleteBesoin}" targets="lstBesoin_%{#varBesoin.plage.idfPlage}" button="true" buttonIcon="ui-icon-trash" key="delete"></sj:a>
		</td>
		<td><s:property value="%{#varBesoin.libelle}"/></td>
		<td><s:property value="%{#varBesoin.idfPlageHelper}"/></td>
		<td><s:property value="%{#varBesoin.equipage.libelle}" /></td></tr>
	</s:iterator>
</table>
    