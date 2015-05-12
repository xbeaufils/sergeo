<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form id="frmPiquet" action="piquet_selectForDay" namespace="/garde" cssStyle="display:none;">
	<s:hidden name="dayOfMonth" ></s:hidden>
</s:form>
<table id="monthBesoin">
	<tr><th class="ui-widget-header">Lundi</th>
		<th class="ui-widget-header">Mardi</th>
		<th class="ui-widget-header">Mercredi</th>
		<th class="ui-widget-header">Jeudi</th>
		<th class="ui-widget-header">Vendredi</th>
		<th class="ui-widget-header">Samedi</th>
		<th class="ui-widget-header">Dimanche</th>
	</tr>
	<s:iterator value="lstBesoinMonth" var="besoinsWeek">
		<tr>
			<s:iterator value="#besoinsWeek.lstJour" var="besoinsJour">
				<td class="ui-widget-content day">
					<s:url id="piquetUrl" action="piquet_selectForDay" namespace="/garde">
						<s:param name="dayOfMonth" value="%{#besoinsJour.jour}"></s:param>
					</s:url>					
					<s:a cssClass="piquet" href="%{piquetUrl}" ><s:property value="#besoinsJour.jour"/></s:a>
					<sj:a formIds="frmPiquet" cssStyle="float: right;" 
   							button="true" 
   							buttonIcon="ui-icon-calculator" 
   							onclick="$('#frmPiquet_dayOfMonth').val(%{#besoinsJour.jour});" />				
					<s:iterator value="#besoinsJour.lstBesoinPlage" var="besoinPlage">
						<div class='periode <s:property value="#besoinPlage.statutPlanning" />'>
							<s:property value="libelle"/>  
							<s:url id="besoinUrl" action="view" namespace="/checkBesoin" escapeAmp="false">
								<s:param name="idfPlageBesoin" value="%{#besoinPlage.idfPlage}"></s:param>
								<s:param name="jourBesoin" value="%{#besoinsJour.jour}"></s:param>
							</s:url>
							<s:url id="effectifUrl" action="viewEffectif" namespace="/checkBesoin" escapeAmp="false">
								<s:param name="idfPlageBesoin" value="%{#besoinPlage.idfPlage}"></s:param>
								<s:param name="jourBesoin" value="%{#besoinsJour.jour}"></s:param>
							</s:url>
							<sj:a openDialog="besoinDialog"
	    							href="%{besoinUrl}" cssStyle="float: right;"
	    							button="true" buttonText="false"
	    							buttonIcon="ui-icon-info" />		
	    					<sj:a openDialog="effectifDialog"
	    							href="%{effectifUrl}" cssStyle="float: right;"
	    							button="true" buttonText="false"
	    							buttonIcon="ui-icon-person" />		
						</div>
					</s:iterator>
				</td>
			</s:iterator>		
		</tr>
	</s:iterator>
</table>
