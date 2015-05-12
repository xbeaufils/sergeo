<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 <s:form action="save" namespace="/agres" theme="simple" id="formEquipageSave">
 	<table class ="formulaire">
		<tr><td>Libellé : <s:textfield name="libelle" cssClass="inputbox"></s:textfield> </td></tr>
		<tr><td>
			<sj:submit formIds="formEquipageSave" button="true" buttonIcon="ui-icon-plusthick" key="save"></sj:submit>
		</td></tr>
	</table>
</s:form>  