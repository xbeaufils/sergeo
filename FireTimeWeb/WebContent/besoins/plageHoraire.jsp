<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.currentBesoinManager!= null">
		<div id="light" class="white_content" style="display: block;">
			<div id="divBesoin">
				<jsp:include page="/editBesoin.jsp" />
			</div>
		   <s:if test="lstEmploi != null">
			   <div id="addBesoin">
					<s:form action="besoin_" method="add" namespace="/admin"  theme="simple">
				   		<table class="formulaire">
				   		<tr><th colspan="2">Effectif</th></tr>
				   		<tr><td>Emploi :</td>
				   		<td><s:select list="lstEmploi" name="idfEmploi" cssClass="inputbox"
				   			listKey="idEmploi" listValue="libelle"></s:select></td></tr>
				   		<tr><td>Nombre :</td>
						<td><s:textfield name="nbEmploi" cssClass="inputbox"></s:textfield></td></tr>
						<tr><td colspan="2"><s:submit method="addBesoin" cssClass="button" key="add"></s:submit></td></tr>					
						</table>
					</s:form>
				</div>
			</s:if>
		   <s:if test="lstTypeVehicule != null">
			   <div id="addBesoin">
					<s:form action="besoin_" method="add" namespace="/admin"  theme="simple">
				   		<table class="formulaire">
				   		<tr><th colspan="2">Effectif</th></tr>
				   		<tr><td>Vehicule :</td>
				   		<td><s:checkboxlist list="lstTypeVehicule" name="lstTypeVehiculeSelect" ></s:checkboxlist>
				   		</td></tr>
				   		<tr><td>Nombre :</td>
						<td><s:textfield name="nbEmploi" cssClass="inputbox"></s:textfield></td></tr>
						<tr><td colspan="2"><s:submit method="addBesoinEquipage" cssClass="button" key="add"></s:submit></td></tr>					
						</table>
					</s:form>
				</div>
			</s:if>
		</div>
		<div id="fade" class="black_overlay" style="display: block;"></div>
	</s:if>
<div class="page">
	<div id="itsthetable">
	<table class="moduletable">
	<caption>Périodes de validité</caption>
	<thead>
		<tr>
			<th colspan="2">Libellé</th>
			<th>Date validité</th>
			<th>Date fin validité</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="#session.lstCycle" status="statusCycle" id="itCycle">		
			<tr <s:if test="#statusCycle.odd">class="odd"</s:if>>
				<td>
					<s:url action="cycle_" method="select" id="urlSelect" namespace="/horaire" includeParams="none">
						<s:param name="idfCycleSelect" value="idfCycle"></s:param>
					</s:url>
					<s:a href="%{urlSelect}"><img src="<%=request.getContextPath()%>/images/clock.gif" border="0" title="Plages horaires" /> </s:a>
					<s:url action="statut_" method="view" namespace="/horaire" id="viewStatut">
						<s:param name="idfCycleSelect" value="idfCycle"></s:param>
					</s:url>
					<s:a href="%{viewStatut}" ><img src="<%=request.getContextPath()%>/images/grpt.gif" border="0" title="Statut" /> </s:a>
					<s:url action="centre_" method="view" namespace="/besoin" id="viewBesoin">
						<s:param name="idfCycleSelect" value="idfCycle"></s:param>
					</s:url>
					<s:a href="%{viewBesoin}" ><img src="<%=request.getContextPath()%>/images/vehicule.jpg" border="0" title="Besoins equipage" /></s:a>
					<s:url action="cycle_" method="view" namespace="/cycle" includeParams="none" id="viewCycle">
						<s:param name="idfCycleSelect" value="idfCycle"></s:param>
					</s:url>
					<s:a href="%{viewCycle}"><img src="<%=request.getContextPath()%>/images/reload.png" border="0" title="Cycle de travail" /></s:a>
				</td>
				<td><s:property value="libelle"/></td>
				<td><s:date name="dateValidite" format="dd/MM/yyyy"/></td>
				<td>
					<s:if test="next != null">
						<s:date name="next.dateValidite" format="dd/MM/yyyy"/>
					</s:if>
					<s:else>En cours de validité</s:else> 
				</td>
			</tr>
		</s:iterator>
	</tbody>
	<tfoot>
		<tr><th colspan="4">
			<sj:a openDialog="createPlage" button="true" >Ajouter</sj:a>
		</th></tr>
	</tfoot>
	</table>
	</div>
</div>
	<s:url action="plage_" method="create" id="urlCreate" namespace="/horaire" includeParams="none"></s:url>
	<sj:dialog id="createPlage" href="%{urlCreate}" autoOpen="false" width="710" height="262" title="Plages horaires"></sj:dialog>
</body>
</html>
