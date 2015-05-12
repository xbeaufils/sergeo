<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<sj:head  locale="fr" jqueryui="true" jquerytheme="blitzer" loadAtOnce="true"/>
   		<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
   		<title>Sergeo - Périodes de validité des parametres</title>
	</head>
	<body style="font-size: 0.8em;">
		<script type="text/javascript">
			function openBesoinsDiv(plage) {
				$("#frmAddBesoin_idfPlage").val(plage);
				$.publish('openBesoinTopic');
			}
			
			function formatImagePlage(cellvalue, options, rowObject) {
 				return "<span class='ui-icon ui-icon-extlink' onClick='javascript:openBesoinsDiv("+rowObject.idfPlage+")' ></span>";
			}
			function openStatutDiv(statut) {
				$("#frmViewStatut_idfStatutSelected").val(statut);
				$("#frmViewStatut_idfStatut").val(statut);
				$.publish('openStatutTopic');
			}
			function formatImageStatut(cellvalue, options, rowObject) {
 				return "<span class='ui-icon ui-icon-extlink' onClick='javascript:openStatutDiv("+rowObject.idfStatut+")' ></span>";
			}
		</script>
		<script type="text/javascript">
		function datePick (elem) {
          $(elem).datepicker(
          // {} // options here
                 $(elem).datepicker({dateFormat:'dd/mm/yyyy'})
          );
         }
		</script>
		<jsp:include page="../menu.jsp" >
			<jsp:param value="currentPage" name="statut"/>
		</jsp:include>
 		<div style="float: left; width:300px;">									
			<s:iterator value="lstCycle" status="stCycle" var="varCycle">
			<div>
				<div style="background-image: url(/FireTimeWeb/images/border.gif); background-repeat: no-repeat;margin-left: 50px;">&nbsp;</div>
				<div style="background-image: url(/FireTimeWeb/images/border.gif);background-repeat: no-repeat;background-position: 50px;padding-left: 45px;">
					<div style="float: left;"><img src="/FireTimeWeb/images/timeIcon.gif"></div>
					<div style="margin-left: 35px;"> 
  						<div style="margin-right: 30px; padding-left: 15px;text-align: center;" class="ui-state-active ui-corner-top ui-corner-bottom">
						<div><s:property value="%{#varCycle.libelle}"/></div>
						<div><s:date name="dateValidite" format="dd/MM/yyyy" /></div>
					  	</div>
					</div>
				</div>
				<div style="background-image: url(/FireTimeWeb/images/border.gif);background-repeat: no-repeat;margin-left: 50px;">&nbsp;</div>
			</div>									
			</s:iterator>
			<div><sj:a openDialog="createPeriode" button="true" buttonIcon="ui-icon-plusthick">Ajouter</sj:a></div>
		</div>
 		<s:url action="cycle_" method="view" namespace="/horaire" id="viewHoraire"  ></s:url>
		<div style="float:left;width: 550px;">
			<div class="ui-widget-header ui-corner-all">Periode de validité</div>
			<s:if test="%{lstCycle.isEmpty()}">
				Pas de parametre défini.
			</s:if>
			<s:else>
				<sj:accordion id="accordionPlage" cssStyle="float:left;width: 550px;">
					<s:iterator value="lstCycle" status="stCycle" var="varCycle">
						<sj:accordionItem title="%{#varCycle.libelle}"  >
							<s:url action="listPlage" namespace="/horaire/json" id="viewPlageHoraire"  >
								<s:param name="idfCycle" value="%{#varCycle.idfCycle}"></s:param>
							</s:url>
							<s:url id="updatePlageHoraire" action="savePlage" namespace="/horaire/json"   ></s:url>	
							<s:url id="urlViewJsonBesoins" action="besoin_" method="viewGrid" namespace="/besoin/json" ></s:url>	
							<s:url id="urlDelJsonBesoins" action="besoin_" method="delete" namespace="/besoin/json" ></s:url>       		
							<sjg:grid
								id="plageGridtable_%{#varCycle.idfCycle}"
								href="%{viewPlageHoraire}"
								gridModel="lstPlage"
								rowNum="-1" 
								width="500"
								navigator="true" 
								pager="true"
								navigatorAdd="true" 
								prmNames="{search: null,id:'idfPlage', nd: null, rowid: 'idfCycle'}"
								editurl="%{updatePlageHoraire}"
								navigatorAddOptions="{height:250, width:420, draggable:true}"
								navigatorEditOptions="{height:250, width:420, draggable:true}"
								caption="Plages horaires"  >
								<sjg:grid
									id="besoinGridtable_%{#varCycle.idfCycle}"
									subGridUrl="%{urlViewJsonBesoins}"
									gridModel="lstBesoin"
									rowNum="-1" 
									width="300"
									navigator="true" 
									navigatorAdd="false"
									navigatorEdit="false"									 
									prmNames="{search: null,id:'idfBesoin', nd: null, rowid: 'idfEquipage'}"
									editurl="%{urlDelJsonBesoins}"
									caption="Besoins">
									<sjg:gridColumn name="idfBesoin" key="true" title="" hidden="true"/>
									<sjg:gridColumn name="idfEquipage" title="" hidden="true"/>
									<sjg:gridColumn name="libelle" title="Nom"/>
									<sjg:gridColumn name="equipage.libelle" title="Equipage"/>
									<sjg:gridColumn name="idfPlageHelper" title=""/>
									
									</sjg:grid>
								<sjg:gridColumn name="idfPlage" key="true" title="Id plage" hidden="true" formatter="integer" />
								<sjg:gridColumn name="idfCycle" title="Id param" hidden="true" 
													formatter="integer" 
													editoptions="{defaultValue: %{#varCycle.idfCycle} }"			 
													editable="true"/>
								<sjg:gridColumn name="libelle" title="Libelle" width="300" editable="true"/>
								<sjg:gridColumn name="debut" title="Debut" align="right" editable="false" formatter="date"
												formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
								<sjg:gridColumn name="heureDebut" title="Debut" align="right" editable="true" hidden="true" edittype="select"
												formoptions="{ rowpos:3, colpos:1 }"
												editrules="{edithidden:true}"
												editoptions="{value:'0:00;1:01;2:02;3:03;4:04;5:05;6:06;7:07;8:08;9:09;10:10;11:11;12:12;13:13;14:14;15:15;16:16;17:17;18:18;19:19;20:20;21:21;22:22;23:23'}" />
								<sjg:gridColumn name="minuteDebut" title="Debut" align="right" editable="true" hidden="true" edittype="select"
												formoptions="{ rowpos:3, colpos:2 }"
												editrules="{edithidden:true}"
												editoptions="{value: '0:00;5:05;10:10;15:15;20:20;25:25;30:30;35:35;40:40;45:45;50:50;55:55'}"/>
								<sjg:gridColumn name="fin" title="Fin" align="right" editable="false" formatter="date"
												formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
								<sjg:gridColumn name="heureFin" title="Fin (Heure)" align="right" editable="true" hidden="true" edittype="select"
												formoptions="{ rowpos:4, colpos:1 }"
												editrules="{edithidden:true}"
												editoptions="{value:'0:00;1:01;2:02;3:03;4:04;5:05;6:06;7:07;8:08;9:09;10:10;11:11;12:12;13:13;14:14;15:15;16:16;17:17;18:18;19:19;20:20;21:21;22:22;23:23'}" />
								<sjg:gridColumn name="minuteFin" title="Fin (Min)" align="right" editable="true" hidden="true" edittype="select"
												formoptions="{ rowpos:4, colpos:2 }"
												editrules="{edithidden:true}"
												editoptions="{value: '0:00;5:05;10:10;15:15;20:20;25:25;30:30;35:35;40:40;45:45;50:50;55:55'}"/>
								<sjg:gridColumn name="name" 
												 title="Actions" 
												 editable="false" 
												 sortable="false"
												 align="center"
												 formatter="formatImagePlage"/>
							</sjg:grid>
							<br><span  ></span>
							<s:url action="viewListStatut" namespace="/periode/json" id="urlListStatut"  >
								<s:param name="idfCycleSelect" value="%{#varCycle.idfCycle}"></s:param>
							</s:url>
							<s:url action="saveStatut" namespace="/periode/json" id="updateStatut"  ></s:url>	
							<s:url action="viewListPlage" namespace="/periode/json" id="urlListPlage"  ></s:url>	       		
							<sjg:grid
								id="statutGridtable_%{#varCycle.idfCycle}"
								href="%{urlListStatut}"
								gridModel="lstStatut"
								rowNum="-1" 
								width="500"
								pager="true"
								navigator="true" 
								navigatorAdd="true" 
								prmNames="{search: null,id:'idfStatut', nd: null, rowid: 'idfCycleSelect'}"
								editurl="%{updateStatut}"
								caption="Statut">
								<sjg:grid
									id="statutPlageGridtable_%{#varCycle.idfCycle}"
									subGridUrl="%{urlListPlage}"
									gridModel="lstPlage"
									rowNum="-1" 
									width="300"
									navigator="true" 
									navigatorAdd="false"
									navigatorDelete="false"
									navigatorEdit="false" 
									prmNames="{search: null,id:'idfPlage', nd: null, rowid: 'idfCycle'}"
									editurl="%{updatePlageHoraire}"
									caption="Plages horaires">
									<sjg:gridColumn name="idfPlage" key="true" title="Id plage" hidden="true" formatter="integer" />
									<sjg:gridColumn name="libelle" title="Libelle" width="300" editable="true"/>
									<sjg:gridColumn name="debut" title="Debut" align="right" editable="false" formatter="date"
													formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
									<sjg:gridColumn name="fin" title="Fin" align="right" editable="false" formatter="date"
													formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
									
									</sjg:grid>
								<sjg:gridColumn name="idfStatut" key="true" title="Id plage" hidden="true" formatter="integer"/>
								<sjg:gridColumn name="idfCycleSelect" title="Id param" hidden="true" 
												formatter="integer" 
												editoptions="{defaultValue: %{#varCycle.idfCycle} }"			 
												editable="true"/>
								<sjg:gridColumn name="libelle" title="Libelle" width="300" editable="true" />
								<sjg:gridColumn name="code" title="Code" editable="true"/>
								<sjg:gridColumn name="duree" title="Durée" formatter="integer" editable="true"/>
								<sjg:gridColumn name="presence" title="Presence"   
												editable="true"
												edittype="select"
  												editoptions="{value:'PRESENT:Présent;ABSENT:Absent;DISPO:Disponible'}" />
								<sjg:gridColumn name="name" 
												 title="Actions" 
												 editable="false" 
												 sortable="false"
												 align="center"
												 formatter="formatImageStatut"/>
							</sjg:grid>			       		
						</sj:accordionItem>
					</s:iterator>
				</sj:accordion>
			</s:else>
		</div>
		<!-- Gestion des besoins --> 
		<s:url id="urlViewBesoins" action="liste" namespace="/besoin" ></s:url>
		<s:form action="liste" namespace="/besoin" id="frmViewBesoin" cssStyle="display:none;">
			<s:textfield name="idfPlage"></s:textfield>
		</s:form>
		<s:form action="selectStatut" namespace="/periode" id="frmViewStatut"  cssStyle="display:none;">
			<s:textfield name="idfStatutSelected"></s:textfield>
			<s:textfield name="idfStatut"></s:textfield>
			
		</s:form>
		<s:url action="selectStatut" namespace="/periode" id="urlViewStatut">
		</s:url>
		<s:url action="createPeriode" id="urlCreatePeriode" namespace="/periode" includeParams="none"></s:url>
		<sj:dialog id="createPeriode" href="%{urlCreatePeriode}" autoOpen="false" modal="true" width="710" height="262" title="Période de validité"></sj:dialog>
		<script type="text/javascript">
			function formatLink(cellvalue, options, rowObject) {
				return "<a href='#' onClick='javascript:openDialog("+cellvalue+")'>" + cellvalue + "</a>";
			}
			function openDialog(employee) {
				$("#createPeriode").load("<s:property value="empurl"/>?id="+employee);
				$("#createPeriode").dialog('open');
			}
		</script>
		<sj:dialog id="dlgStatut" title="Statut de journée" formIds="frmViewStatut" href="%{urlViewStatut}" autoOpen="false" openTopics="openStatutTopic" onCloseTopics="closeStatut" modal="true"  width="710" height="262" >
		</sj:dialog>
		<sj:dialog id="dlgPlage"  title="Plages horaires"   formIds="frmViewBesoin" href="%{urlViewBesoins}" autoOpen="false" openTopics="openPlageTopic" onCloseTopics="closePlage,reloadPlage" width="640" height="300" modal="true"></sj:dialog>
	 	<s:url id="urlAddBesoinEquipage"  action="add"  namespace="/besoin">
			<s:param name="idfPlage" value="%{idfPlage}" />
	 	</s:url>
		<s:form action="add" namespace="/besoin" id="frmAddBesoin" cssStyle="display:none;">
			<s:textfield name="idfPlage"></s:textfield>
		</s:form>
	 	<sj:dialog id="dlgBesoin" title="Besoins en équipage" formIds="frmAddBesoin" href="%{urlAddBesoinEquipage}" autoOpen="false" openTopics="openBesoinTopic" closeTopics="closeBesoins" width="640" height="220" modal="true" ></sj:dialog>
		<sj:a openDialog="createPeriode" button="true" buttonIcon="ui-icon-plusthick">Ajouter</sj:a>
		<s:url action="createPeriode" id="urlCreatePeriode" namespace="/periode" includeParams="none"></s:url>
		<sj:dialog id="createPeriode" href="%{urlCreatePeriode}" autoOpen="false" modal="true" width="710" height="262" title="Période de validité"></sj:dialog>
	</body>
</html>