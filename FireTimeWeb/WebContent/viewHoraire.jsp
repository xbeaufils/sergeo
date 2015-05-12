<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<table class="moduletable" style="margin: 2%; width: 96%;">
	<thead>
		<tr>
		<th>Mois</th>
		<th>Heures<br>effectuées </th>
		<th>Heures<br>comptabilisées <s:property value="#session.year" /></th></tr>
	</thead>
	<tbody>
		<s:iterator value="symbolMonth.months" status="statusMonth">
			<s:action name="agent_calculateCumul" namespace="/effectif" id="monthId" >
				<s:param name="month" value="#statusMonth.index"></s:param>
			</s:action>
			<tr>
				<td><s:property value="#monthId.monthString"/></td>
				<td></td>
				<td><s:property value="#monthId.cumulatedHours"/> </td>
			</tr>
		</s:iterator>
	</tbody>
</table>
