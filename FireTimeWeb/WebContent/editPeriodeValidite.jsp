<%@ page language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
    
<sj:tabbedpanel id="tabParameters">
	<!-- Entete de période de validité -->
	<s:url action="modify" namespace="/periode" id="urlSelectPeriode" includeParams="none">
		<s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
	</s:url>
	<sj:tab id="periodeTab" label="Periode" href="%{urlSelectPeriode}"></sj:tab>
	<!-- Plages de garde -->
	<s:url action="plage_select" namespace="/horaire" id="urlSelectPlage" includeParams="none">
		<s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
	</s:url>
	<s:url action="viewPlage" namespace="/horaire" id="urlViewPlage" includeParams="none">
		<s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
	</s:url>
	<sj:tab id="plageTab" label="Plages de garde" href="%{urlViewPlage}" ></sj:tab>
	<!-- Statuts de journée -->
	<s:url action="viewStatut" namespace="/periode" id="viewStatut">
		<s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
	</s:url>
	<sj:tab id="statutTab" label="statut" href="%{viewStatut}"  ></sj:tab>
	<!--  Cycle de travail -->
	<s:url action="cycle_" method="view" namespace="/cycle" includeParams="none" id="viewCycle">
		<s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
	</s:url>
	<sj:tab id="cycleTab" label="Cycles" href="%{viewCycle}"></sj:tab>
</sj:tabbedpanel>