<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form action="save" namespace="/besoin" id="formBesoin" theme="simple">
	<s:hidden name="idfBesoin"></s:hidden>
	<s:hidden name="idfPlage"></s:hidden>
	<table class ="formulaire">
		<tr>
			<td>Libellé :</td>
			<td><s:textfield name="libelle" label="Libellé" cssClass="inputbox"></s:textfield></td>
		</tr>
		<tr>
			<td>Type équipage :</td>
			<td><s:select list="lstEquipage" listKey="idfEquipage" listValue="libelle" name="idfEquipage"></s:select></td>
		</tr>
		<tr>
			<td>Secondaire :</td>
			<td><s:checkbox name="secondaire" fieldValue="true" label="Secondaire"/>
				<span style="font-style: italic;"> ( Ne sera pas pris en compte dans les besoins)</span>
			</td>
			
		</tr>
		<tr><td colspan="2">
			<s:url namespace="/besoin" action="save" id="urlSaveBesoin" ></s:url>
			<sj:submit formIds="formBesoin" href="%{urlSaveBesoin}" targets="lstBesoin_%{idfPlage}" onCompleteTopics="closeBesoins, refreshBesoin_%{idfPlage}" button="true" buttonIcon="ui-icon-check" key="save"></sj:submit>
		</td></tr>
	</table>
</s:form>