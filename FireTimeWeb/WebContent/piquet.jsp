<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<sj:head locale="fr" jqueryui="true" jquerytheme="blitzer"/>
	<sx:head debug="true"  />
	<link href="<%=request.getContextPath()%>/css/tabs.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/piquet.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/jour.css" type="text/css" rel="stylesheet"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Piquets</title>
      <script type="text/javascript">
		$.subscribe('ondrop', function(event,data) {
			console.log("Data " + data);
			var datas = $('#' + data.id + '> div:first-child').attr('id').split("_");
			$('#frmPiquet_idfBesoin').val(datas[1]);
			$('#frmPiquet_idfPoste').val(datas[2]);
			$.getJSON(
					'<s:url action="affecte" namespace="/garde/json" includeParams="none"></s:url>' ,
					{
					   	"idfPoste" : $('#frmPiquet_idfPoste').val(),
						"idfBesoin" : $('#frmPiquet_idfBesoin').val(),
						"idfPeriode" : $('#frmPiquet_idfPeriode').val(),
						"idfPlage" : $('#frmPiquet_idfPlage').val()
					},
					  function(json) {
						affectePiquet(json);
					  }
				);
		});
		
		function affectePiquet(json) {
			console.log("Json " + json); 
			$.publish("changePiquet_" + json.idfPlage + "_" + json.idfBesoin);
		}
		
		function deletePiquet(idfPiquet, idfPlage, idfBesoin) {
			console.log("Delete piquet " + idfPiquet);
			$.getJSON(
					'<s:url action="delete" namespace="/garde/json" includeParams="none"></s:url>' ,
					{
					   	"idfPiquet" : idfPiquet,
						"idfBesoin" : $('#frmPiquet_idfBesoin').val(),
						"idfPlage" : $('#frmPiquet_idfPlage').val()
					},
					  function(json) {
						affectePiquet(json);
					  }
				);

		}
		$.subscribe('startDrag', function(event,data) {
			console.log("Data " + data);
			var datas = data.id.split("_");
			$('#frmPiquet_idfPeriode').val(datas[1]);
			$('#frmPiquet_idfPlage').val(datas[2]);
		});
        </script>        
</head>
<body style="font-size: 0.8em;">
<s:form action="affecterPiquet" namespace="/garde" theme="simple"
	id='frmPiquet' cssStyle="display: none;">
	<s:hidden name="idfPeriode"/>
	<s:hidden name="idfPlage"/>
	<s:hidden name="idfPoste"/>
	<s:hidden name="idfBesoin"/>
</s:form> 
<sj:tabbedpanel id="tabPlages">
	<s:iterator value="#session.lstPlage" id="idPlage" var="varPlage">
		<s:url action="piquetOfPlage" method="selectPlageHoraire" namespace="/garde" id="urlSelectPlage" includeParams="none">
			<s:param name="idfPlageHoraire" value="idfPlage"></s:param>
		</s:url>
		<sj:tab  id="tabPlage_%{#varPlage.idfPlage}" listenTopics="loadPlage_%{#varPlage.idfPlage}" label="%{#varPlage.libelle}" href="%{urlSelectPlage}"></sj:tab>
	</s:iterator>	
</sj:tabbedpanel>

</body>
</html>