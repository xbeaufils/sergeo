<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<table class="moduletable">
		<tr>
			<th>Libellé</th>
			<th>Séquences</th>
		</tr>
		<s:iterator value="#session.lstCycleTravail" status="statusCycle" id="itCycle">		
			<s:url id="selectUrl" action="cycle_" method="select" includeParams="none" namespace="/cycle">
				<s:param name="idfCycleSelected" value="idfCycle"/>
			</s:url>
			<tr <s:if test="#statusCycle.odd">class="odd"</s:if>>
				<td><s:a href="%{selectUrl}"><s:property value="libelle"/></s:a></td>
				<td>
					<s:action name="sequence_list" namespace="/cycle" id="idListSequence" 
						executeResult="false" ignoreContextParams="true">
						<s:param name="idfCycleTravail" value="idfCycle"></s:param>
					</s:action>
					<s:iterator value="#idListSequence.lstSequence">
						<div class="sequence">
							<div class="head_seq">
								<s:url action="sequence_remove"namespace="/cycle" id="urlRemoveSequence" includeParams="none" >
									<s:param name="ordre" value="id.ordre"></s:param>
									<s:param name="idfCycleTravail" value="id.cycle.idfCycle"></s:param>
								</s:url>
								<s:a href="%{urlRemoveSequence}">
									<img alt="" src="<%=request.getContextPath()%>/images/edit-delete.png" border="0">
								</s:a>
							</div> 
							<div class="body_seq">
								<s:property value="statut.code"/>
							</div>
						</div>
					</s:iterator>
					<div style="float: left;">
						<s:form action="sequence_add" namespace="/cycle" theme="simple">
							<s:hidden name="idfCycleTravail" value="%{#itCycle.idfCycle}"></s:hidden>
							<s:select list="#session.lstStatut"  listKey="idfStatut" listValue="libelle" 
								name="idfStatut" emptyOption="true"  cssClass="inputbox"></s:select>
							<s:submit type="image" src="../images/add.png" cssStyle="vertical-align: middle;"></s:submit>
						</s:form>
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>
	<s:url action="cycle_" method="create" id="urlCreateSequence" namespace="/cycle" includeParams="none"></s:url>
	<sj:a openDialog="dlgSequence" button="true" >Ajouter un statut</sj:a>
	<sj:dialog id="dlgSequence" href="%{urlCreateSequence}" autoOpen="false" onCloseTopics="closeSequence" modal="true" width="710" height="262" title="Cycles de travail"></sj:dialog>
	