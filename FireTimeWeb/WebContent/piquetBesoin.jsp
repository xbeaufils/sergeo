<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<img id="indicatorAffectation" src="<%=request.getContextPath()%>/images/calculCycle.gif" style="display:none; margin-top:50px;" />
	<s:iterator value="#session.lstBesoin" id="idBesoin" var="varBesoin">
		<s:url action="detailPiquet" namespace="/garde" id="urlSelectPiquet">
			<s:param name="idfBesoin" value="idfBesoin"></s:param>
		</s:url>
		<sj:div href="%{urlSelectPiquet}" 
			listenTopics="changePiquet_%{#session.currentPlage.idfPlage}_%{#varBesoin.idfBesoin}" 
			id="besoin%{#session.currentPlage.idfPlage}_%{#varBesoin.idfBesoin}" 
			cssClass="equipage ui-state-active ui-corner-all"></sj:div>
	</s:iterator>
