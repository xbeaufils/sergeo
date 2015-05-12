<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<div class="ui-widget-header ui-corner-all">
	<s:property value="libelleBesoin"/>
	</div>
	<div class="listEquipage">
		<s:iterator value="lstPiquet[idfBesoin]">
			<div class="ui-state-default"> 
				<s:property value="poste.emploi.libelle"/>
			</div>
			<sj:div 
				droppable="true" droppableOnDropTopics="ondrop" droppableHoverClass="hoverDrop" 
				cssClass="ui-widget ui-widget-content">
				<div id='besoin_<s:property value="idfBesoin"/>_<s:property value="poste.idfPoste" />'>
				<s:if test="periode != null">
					<span class="personnel">
						<s:url action="piquet_delete" namespace="/garde" id="urlDeletePiquet">
							<s:param name="idfPiquet" value="idfPiquet"></s:param>
						</s:url>
						<sj:a href="%{urlDeletePiquet}" 
								targets="result"  onCompleteTopics="changePiquet_%{#session.currentPlage.idfPlage}_%{idfBesoin}"
								button="true" 
								onclick="deletePiquet(%{idfPiquet}, %{#session.currentPlage.idfPlage}, %{idfBesoin})"
								buttonIcon="ui-icon-trash">
							</sj:a>
						<s:property value="periode.agent.nom"/> <s:property value="periode.agent.prenom"/></span><br>
				</s:if>
				<s:else>&nbsp;</s:else>
				</div>
			</sj:div>
		</s:iterator>
 	</div>	
