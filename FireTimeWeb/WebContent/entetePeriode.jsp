<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form id="periodeForm" action="savePeriode" method="post" namespace="/periode" >
	<s:hidden name="model.idfCycle"></s:hidden>
	<s:textfield name="model.libelle" label="Libellé" cssClass="inputbox" labelSeparator=":"></s:textfield>
	<s:textfield name="model.dateValidite" label="Date Validité" cssClass="inputbox" labelSeparator=":"></s:textfield>
	Date Fin validité :<s:property value="next.dateValidite"/>
	<sj:submit button="true" buttonIcon="ui-icon-check" key="save"></sj:submit>
</s:form>	
