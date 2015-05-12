<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:form action="affecterPiquet" namespace="/garde" theme="simple"
	id='piquetForm_%{#session.currentPlage.idfPlage}'
	cssClass="ui-widget-content">

	<div style="float: left;">
		<div style="padding: 10px;">
			<s:url action="mensuel_" method="viewMonth" namespace="/planning" id="selectMonth" includeParams="none"></s:url>
			<s:a href="%{selectMonth}"><img src="<%=request.getContextPath()%>/images/calendrier.gif" border="0"> </s:a>
			<s:url  action="garde" namespace="/print" id="printId" includeParams="none" >
				<s:param name="idfEchelonPrinted" value="idfEchelon" ></s:param>
			</s:url>
			<s:a href="%{printId}" ><img src="<%=request.getContextPath()%>/images/fileprint.gif" border="0"/> </s:a>
		</div>
		<div style="float:left; font-size:12px;">
			<s:iterator value="#session.lstPeriode" status="stPeriode" var="varPeriode">
				<sj:div draggable="true" draggableHelper="clone" 
					id="periode_%{idfPeriode}_%{#session.currentPlage.idfPlage}" draggableOnStartTopics="startDrag"> 
					<s:property value="agent.nom"/> &nbsp; <s:property value="agent.prenom"/>
				</sj:div>
			</s:iterator>
		</div>
	</div>
	<div id='piquetDiv_<s:property value="%{#session.currentPlage.idfPlage}"/>'>
		<jsp:include page="piquetBesoin.jsp"></jsp:include>
	</div>

</s:form>
