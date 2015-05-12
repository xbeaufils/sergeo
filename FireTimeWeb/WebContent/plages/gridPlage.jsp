<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<s:url action="plage_" method="viewPlage" namespace="/horaire/json" id="viewPlageHoraire"  ></s:url>
	<s:url action="plage_" method="updatePlage" namespace="/horaire/json" id="updatePlageHoraire"  ></s:url>		       		
	<sjg:grid
		id="plageGridtable"
		href="%{viewPlageHoraire}"
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
	<s:url action="viewStatut" namespace="/periode" id="viewLstStatut"  ></s:url>
	<sjg:grid
		id="statutGridtable"
		href="%{viewLstStatut}"
		gridModel="lstStatut"
		rowNum="-1" 
		width="300"
		navigator="true" 
		navigatorAdd="true" 
		prmNames="{search: null,id:'idfPlage', nd: null, rowid: 'idfCycle'}"
		editurl="%{updatePlageHoraire}"
		caption="Statut">
		<sjg:gridColumn name="idfStatut" key="true" title="Id plage" hidden="true" formatter="integer"/>
		<sjg:gridColumn name="duree" title="Durée" formatter="integer"/>
		<sjg:gridColumn name="presence" title="Presence"  />
		<sjg:gridColumn name="libelle" title="Libelle" width="300" editable="true" />
		<sjg:gridColumn name="code" title="Code"/>
	</sjg:grid>			       		
