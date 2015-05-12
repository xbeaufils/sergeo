<%@ page language="java"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<script type="text/javascript">		
	$(document).ready(function() {
		var drawPlan  = new Sergeo.DrawPlage("divPlageDraw");
		<s:iterator value="lstPlageViewer" status="stPlage" var="varPlage">
		var plage = new Sergeo.Plage(
				'<s:property value="idViewer"/>',
				'<s:property value="libelle"/>',
				<s:property value="interval.debut.heure"/>,
				<s:property value="interval.debut.minute"/>,
				<s:property value="interval.fin.heure"/>,
				<s:property value="interval.fin.minute"/>,
				'<s:property value="statut"/>');
		drawPlan.drawPeriodeRaph(plage); 
		</s:iterator>
	});
</script>
<div id="divPlageDraw">
</div>
<sj:div id="detailPlage" reloadTopics="reloadPlage">
	<div id="divPlageBesoin">
		<s:if test="%{firstPlageViewer == null}">
			Pas de plage horaire définie.
		</s:if>
		<s:else>
			<sj:accordion id="accordionPlage">
				<s:iterator value="lstPlageViewer" status="stPlage" var="varPlage">
					<s:if test="statut.toString() == 'SAVED'">
						<sj:accordionItem title="%{#varPlage.libelle} %{#varPlage.formatedDebut}"  >
							<s:url action="liste" namespace="/besoin" id="urlListeBesoin" >
								<s:param name="idfPlage" value="%{#varPlage.idfPlage}"></s:param>
							</s:url>	
							<sj:div href="%{urlListeBesoin}" id="lstBesoin_%{#varPlage.idfPlage}" listenTopics="refreshBesoin_%{#varPlage.idfPlage}" ></sj:div>
							<s:url action="add"  id="urlCreateBesoin" namespace="/besoin">
								<s:param name="idfPlage" value="%{#varPlage.idfPlage}"></s:param>
							</s:url>
							<sj:a openDialog="dlgBesoin" href="%{urlCreateBesoin}" button="true" buttonIcon="ui-icon-plusthick">Ajouter un nouveau besoin</sj:a>
						</sj:accordionItem>
					</s:if>
				</s:iterator>
			</sj:accordion>
		</s:else>
	</div>
	<s:url action="plage_select" namespace="/horaire" id="urlSelectPlage">
	        <s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
	</s:url>
	<sj:a id="btnOpenPlage" openDialog="dlgPlage" href="%{urlSelectPlage}" button="true" buttonIcon="ui-icon-plusthick">Modifier les plages horaires</sj:a>
	
	<s:iterator value="lstPlageViewer" status="stPlage" var="varPlage">
		<s:url action="plage_select" namespace="/horaire" id="urlSelectPlage">
	 		<s:param name="idViewerSelect" value="%{#varPlage.idViewer}"></s:param>
		</s:url>
	
		<sj:a id="plageBtn_%{idViewer}" openDialog="dlgPlage" href="%{urlSelectPlage}"></sj:a>
	</s:iterator>
</sj:div>