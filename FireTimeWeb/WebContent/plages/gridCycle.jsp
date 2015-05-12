<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<s:url action="cycle_" method="view" namespace="/horaire/json" id="viewHoraire"  ></s:url>
		<s:url action="cycle_" method="save" namespace="/horaire/json" id="urlCreatePeriode"  ></s:url>
		<s:url action="plage_" method="viewPlage" namespace="/horaire/json" id="viewPlageHoraire"  ></s:url>
		<s:url action="plage_" method="updatePlage" namespace="/horaire/json" id="updatePlageHoraire"  ></s:url>		
		<sjg:grid gridModel="lstCycle" 
				id="cycleGrid" 
				href="%{#viewHoraire}"  
				altRows="true"  
				width="450"
				pager="true" 
				navigator="true"				
				navigatorCloneToTop="false"
				name="cycleGridName" 
				prmNames="{search: null,id:'idfCycle', nd: null, subgridid:'idfPlage'}"
        		resizable="true" 
        		navigatorEdit="true"
        		editurl="%{urlCreatePeriode}"
        		caption="Periodes de validité">
        		
			<sjg:grid
				id="plageGridtable"
				subGridUrl="%{viewPlageHoraire}"
				gridModel="lstPlage"
				rowNum="-1" 
				width="300"
				navigator="true" 
				navigatorAdd="true" 
				prmNames="{search: null,id:'idfPlage', nd: null, rowid: 'idfCycle'}"
				editurl="%{updatePlageHoraire}"
				caption="Plages horaires">
					<sjg:gridColumn name="idfPlage" key="true" title="Id plage" hidden="true" formatter="integer"/>
					<sjg:gridColumn name="libelle" title="Libelle" width="300" editable="true" formatter="formatBesoinsLink"/>
					<sjg:gridColumn name="debut" title="Debut" align="right" editable="false" formatter="date"
									formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
					<sjg:gridColumn name="heureDebut" title="Debut" align="right" editable="true" hidden="true" edittype="select"
									formoptions="{ rowpos:2, colpos:1 }"
									editrules="{edithidden:true}"
									editoptions="{value:'00:00;01:01;02:02;03:03;04:04;05:05;06:06;07:07;08:08;09:09;10:10;11:11;12:12;13:13;14:14;15:15;16:16;17:17;18:18;19:19;20:20;21:21;22:22;23:23'}" />
					<sjg:gridColumn name="minuteDebut" title="Debut" align="right" editable="true" hidden="true" edittype="select"
									formoptions="{ rowpos:2, colpos:2 }"
									editrules="{edithidden:true}"
									editoptions="{value: '00:00;05:05;10:10;15:15;20:20;25:25;30:30;35:35;40:40;45:45;50:50;55:55'}"/>
					<sjg:gridColumn name="fin" title="Fin" align="right" editable="false" formatter="date"
									formatoptions="{newformat : 'H:i', srcformat : 'Y-m-d H:i:s'}"/>
					<sjg:gridColumn name="heureFin" title="Fin (Heure)" align="right" editable="true" hidden="true" edittype="select"
									formoptions="{ rowpos:3, colpos:1 }"
									editrules="{edithidden:true}"
									editoptions="{value:'00:00;01:01;02:02;03:03;04:04;05:05;06:06;07:07;08:08;09:09;10:10;11:11;12:12;13:13;14:14;15:15;16:16;17:17;18:18;19:19;20:20;21:21;22:22;23:23'}" />
					<sjg:gridColumn name="minuteFin" title="Fin (Min)" align="right" editable="true" hidden="true" edittype="select"
									formoptions="{ rowpos:3, colpos:2 }"
									editrules="{edithidden:true}"
									editoptions="{value: '00:00;05:05;10:10;15:15;20:20;25:25;30:30;35:35;40:40;45:45;50:50;55:55'}"/>
				</sjg:grid>
		 	<sjg:gridColumn
		 		id="colIdfCycle"
				name="idfCycle"
				title="id cycle" 
				key="true" hidden="true"
				sortable="true"/>
		 	<sjg:gridColumn
				name="libelle"
				title="Libellé"
				editable="true"
				formatter="formatLink"			
				sortable="true"/>
		 	<sjg:gridColumn
				name="dateValidite"
				title="Date de validité"
				formatter="date"
				editoptions="{ dataInit:datePick}"
				editable="true"
				sortable="true"
			/>
		</sjg:grid>
		<sj:dialog id="dlgStatut" title="Statut de journée" autoOpen="false" onCloseTopics="closeStatut" modal="true"  width="710" height="262" ></sj:dialog>
	<sj:dialog id="dlgPlage"  title="Plages horaires" 	autoOpen="false" openTopics="openPlageTopic" onCloseTopics="closePlage,reloadPlage" width="640" height="300" modal="true"></sj:dialog>
		