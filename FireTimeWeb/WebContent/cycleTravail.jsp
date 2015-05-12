<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form action="cycle_save" method="POST" namespace="/cycle"  theme="simple">
<table class ="formulaire">
	<tr><th colspan="2">Cycle de travail</th></tr>
	<tr><td>Libellé :</td>
	<td><s:textfield name="libelle" label="Libellé" cssClass="inputbox"></s:textfield></td>
	</tr>
	<tr><td colspan="2">
		<s:hidden name="idfCycleSelect"></s:hidden>
		<s:submit method="save" cssClass="button" key="save"></s:submit>
	   	<s:submit method="cancel" cssClass="button" key="cancel"></s:submit>
	</td></tr>
	</table>
</s:form>