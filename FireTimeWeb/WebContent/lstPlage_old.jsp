<%@ page language="java"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="divHoraire" style="height: 35px;margin-bottom: 20px;">
	<div class="hour">00</div>
	<div class="hour">01</div>
	<div class="hour">02</div>
	<div class="hour">03</div>
	<div class="hour">04</div>
	<div class="hour">05</div>
	<div class="hour">06</div>
	<div class="hour">07</div>
	<div class="hour">08</div>
	<div class="hour">09</div>
	<div class="hour">10</div>
	<div class="hour">11</div>
	<div class="hour">12</div>
	<div class="hour">13</div>
	<div class="hour">14</div>
	<div class="hour">15</div>
	<div class="hour">16</div>
	<div class="hour">17</div>
	<div class="hour">18</div>
	<div class="hour">19</div>
	<div class="hour">20</div>
	<div class="hour">21</div>
	<div class="hour">22</div>
	<div class="hour">23</div>

	<div id="echelle"></div>
</div>		
<s:if test="%{lstPlageViewer.isEmpty()}">
	Pas de plage horaire définie.
</s:if>
<s:else>
	<sj:accordion id="accordionPlage">
		<s:iterator value="lstPlageViewer" status="stPlage" var="varPlage">
			<s:if test="paddingInterval == false">
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

<s:url action="plage_select" namespace="/horaire" id="urlSelectPlage">
        <s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
</s:url>
<sj:a openDialog="dlgPlage" href="%{urlSelectPlage}" button="true" buttonIcon="ui-icon-plusthick">Modifier les plages horaires</sj:a>

<s:iterator value="lstPlageViewer" status="stPlage" var="varPlage">
	<s:url action="plage_select" namespace="/horaire" id="urlSelectPlage">
        <s:param name="idfCycleSelect" value="#session.currentHoraireManager.currentCycle.idfCycle"></s:param>
 		<s:param name="idfPlageSelect" value="%{#varPlage.idfPlage}"></s:param>
	</s:url>

	<sj:a id="plageBtn_%{heureDebut}_%{minuteDebut}__%{heureFin}_%{minuteFin}" openDialog="dlgPlage" href="%{urlSelectPlage}"></sj:a>
</s:iterator>

<script type="text/javascript">
$(document).ready(function() {
 	<s:iterator value="lstPlageViewer" status="stPlage" var="varPlage">
 		var left = getCoordForHeure(<s:property value="heureDebut"/>) + getCoordForMinute(<s:property value="minuteDebut"/>);
 		var width = getCoordForHeure(<s:property value="heureFin"/>) + getCoordForMinute(<s:property value="minuteFin"/>) - left;
 		jQuery('<div/>', {
 				id: 'plage_' 
 					+ <s:property value="heureDebut"/> + '_' + <s:property value="minuteDebut"/> 
 					+ '__' + <s:property value="heureFin"/> + '_' +<s:property value="minuteFin"/>,
 				class: 'plage <s:if test="paddingInterval">paddingIntervall</s:if>',
 				style: 'left : ' + left +'px;' +'width: ' + width +'px;'
		}).appendTo('#echelle');
		
	</s:iterator>
	$(".plage").click(function(){
		//alert($(this).attr('id'));
		var idPlage = $(this).attr('id');
		console.log('Click on '+ idPlage.replace("plage_","plageBtn_"));
		$('#' + idPlage.replace("plage_","plageBtn_")).click();
		//$.publish("openPlageTopic");
	});
});
	function getCoordForHeure(heure) {
		var coord = (heure) * 18 +9;
		return coord;
	}

	function getCoordForMinute(minute) {
		var coord = (minute * 18) / 60;
		return coord;
	}

</script>