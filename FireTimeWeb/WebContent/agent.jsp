<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<script type="text/javascript">
 	function degriser(checkBox) {
		var inputId;
		switch (checkBox.id) {
		case 'degriseFinCompetence':
			inputId = 'dteFinCompetence';
			break;
		case 'degriseFinAffectation':
			inputId = 'dteFinAffectation';
			break;
		}
		if ($(checkBox).is(':checked')) {
			$("#" + inputId).removeAttr('disabled');
		}
		else {
			$("#" + inputId).attr('disabled', true);
		}
	}
	$.subscribe('closeAffectation', function(event, data) {
		  $('#tabAgent').tabs('load', 1);
			});
	$.subscribe('closeCompetence', function(event, data) {
			  $('#tabAgent').tabs('load', 2);
			});
	</script>
	<s:url action="viewAffectation" namespace="/effectif" id="urlAffectation">
		<s:param name="idfAgent" value="#session.currentAgent.idfAgent"></s:param>
	</s:url>
	<s:url action="viewCompetence" namespace="/effectif" id="urlCompetence">
		<s:param name="idfAgent" value="#session.currentAgent.idfAgent"></s:param>
	</s:url>
	<s:url action="viewHoraire" namespace="/effectif" id="urlHoraire">
		<s:param name="idfAgent" value="#session.currentAgent.idfAgent"></s:param>
	</s:url>
	<sj:tabbedpanel id="tabAgent">
		<sj:tab href="/viewAgent.jsp"  label="Identité" ></sj:tab>
		<sj:tab href="%{urlAffectation}" label="Affectations"></sj:tab>
		<sj:tab href="%{urlCompetence}" label="Compétences"></sj:tab>
		<sj:tab href="%{urlHoraire}" label="Horaires"></sj:tab>
	</sj:tabbedpanel>
	<sj:dialog id="dlgAffectation"
				autoOpen="false" 					 
				modal="true"
				title="Affectation"
				width="760"
				height="190"
				onCloseTopics="closeAffectation"></sj:dialog>
	<sj:dialog id="dlgCompetence"
				autoOpen="false" 					 
				modal="true"
				title="Compétence"
				width="770"
				height="190"
				onCloseTopics="closeCompetence"></sj:dialog>
