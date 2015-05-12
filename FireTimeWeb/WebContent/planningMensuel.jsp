<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<sj:head  locale="fr" jqueryui="true" jquerytheme="blitzer"/>
  	<link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Planning mensuel</title>
	<link href="<%=request.getContextPath()%>/css/demo_table.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/context.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/jour.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/firesoft.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/jquery.contextmenu.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/jquery.contextmenu.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/jquery.ui.position.js"></script>

	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/dataTables.jqueryui.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/dataTables.fixedColumns.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.fixedColumns.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dataTables.jqueryui.css">
<script type="text/javascript">
 	//var servicePlanning = new dojo.rpc.JsonService("<s:url action="jsonCall" namespace="/rpc" includeParams="none"></s:url>");
    //servicePlanning.serviceUrl = servicePlanning.serviceUrl.substring("<%=request.getContextPath()%>".length);
	var IE = document.all?true:false;
	
	if (!IE) document.captureEvents(Event.MOUSEMOVE);	
	
	
	var plage = [  
	         	<s:iterator value="lstPlage">
	         		{
	         		"idfPlage" : <s:property value="idfPlage" />,
	         		"libelle" : '<s:property value="libelle" />',
	         		"heureDebut": <s:property value="heureDebut" />,
	         		"minuteDebut": <s:property value="minuteDebut" />
	         		},
	         	</s:iterator>
	         		];
	
	$.subscribe('planningComplete', function(event,data) {
		$('.dayRepresentation').contextPopup({
		    title: 'Modification planning', 
		    items: [
		    	<s:iterator value="lstStatut">
				{ label : '<s:property value="libelle"/>', action:function(e){submitStatut(e, e.currentTarget, <s:property value="idfStatut"/>);} },
			</s:iterator>
				null,
		        {label: "Delete", action:function(e){submitDelete(e, e.currentTarget);}}
			]
		});
		
		var table = $('#month').DataTable( {
			scrollY:        "500px",
			scrollX:        true,
			scrollCollapse: true,
			paging:         false,
			"ordering": 	false,
			"searching": 	false,
			"jQueryUI": 	true,
			"columnDefs": [
			    			{ "width": "200px", "targets": 0 },
			    			{ "width": "35px", "targets": 1 },
			    			{ "width": "35px", "targets": 2 },
			    			{ "width": "35px", "targets": 3 },
			    			{ "width": "35px", "targets": 4 },
			    			{ "width": "35px", "targets": 5 },
			    			{ "width": "35px", "targets": 6 },
			    			{ "width": "35px", "targets": 7 },
			    			{ "width": "35px", "targets": 8 },
			    			{ "width": "35px", "targets": 9 },
			    			{ "width": "35px", "targets": 10 },
			    			{ "width": "35px", "targets": 11 },
			    			{ "width": "35px", "targets": 12 },
			    			{ "width": "35px", "targets": 13 },
			    			{ "width": "35px", "targets": 14 },
			  			]
			
		} );
		new $.fn.dataTable.FixedColumns( table );
		//$('.dayRepresentation').height( ( $('.monthAgent').length + 1 ) * ( $('.monthAgent').height() + 1 /* la bordure */) );
		//$('#listAgent').height( $('#monthRepresentation').height() - $('#listAgent').position().top -20);
		//$('#listAgent').scroll(function() {
		//	var scrolling = $(this).scrollTop();
		//	$('#monthGrid').css({ top :$(this).scrollTop() * -1});
		//});       
		var dteIterat = new Date($('#selYear').val(), $('#selMonth input:checked').val(), 1); 
		var dteDebut = new Date($('#selYear').val(), $('#selMonth input:checked').val() , 1);		
		var intervalle = 24*3600*1000;
		while ( dteDebut.getMonth() == dteIterat.getMonth()) {
			if ( dteIterat.getDay() == 0 || dteIterat.getDay() == 6 ) {
				$('.jour_' + dteIterat.getDate() ).addClass('weekend');
			}
			dteIterat.setTime(dteIterat.getTime() + intervalle);
		}
		$.getJSON(
				'<s:url action="makeMonth" namespace="/planning_json" includeParams="none"></s:url>' ,
				{
				},
				  function(json) {
					  for (var i=0; i< json.length; i++)
					  	drawPeriode(json[i].jour, json[i].idfAgent, json[i].statut.code,json[i].idfPeriode, json[i].statut.lstIdfPlage);
				  }
				 );
		$.getJSON(
				'<s:url action="makeDispoMonth" namespace="/planning_json" includeParams="none"></s:url>' ,
				{
				},
				  function(json) {
					  for (var i=0; i< json.length; i++)
					  	drawDispo(json[i].jour, json[i].idfAgent, json[i].idfPlage ,json[i].idfDispo);
				  }
				 );
		$.getJSON(
				'<s:url action="makeCommentMonth" namespace="/planning_json" includeParams="none"></s:url>' ,
				{
				},
				  function(json) {
					  for (var i=0; i< json.length; i++) 
						drawComment(json[i].jour, json[i].idfAgent, json[i].commentaire, json[i].idfComment);
				  }
				
		);
    });

	function submitStatut(e, target, idStatut) {
		var param = findParam(target);
		console.log("Day is " + param.jour);
		$.getJSON(
				'<s:url action="affecteStatut" namespace="/planning_json" includeParams="none"></s:url>' ,
				{
				   "idAgent" : param.idfAgent,
					"jour" : param.jour,
					"idStatut" : idStatut			  
				},
				  function(json) {
					  drawPeriode(json.jour, json.idfAgent, json.statut.code, json.idfPeriode);
				  }
				 );

	}

	function submitDelete(e, target) {
		param = findParam(target);
		console.log("Deleted day is " + param.jour);
		$.getJSON(
				'<s:url action="deleteStatut" namespace="/planning_json" includeParams="none"></s:url>' ,
				{
				   "idAgent" : param.idfAgent,
					"jour" : param.jour				},
				  function(json) {
					  deletePeriode(json.idfPeriode);
				  }
				 );

	}

	function findParam(target) {
		var param = new Object();
		var targetDay = $(target)[0];
		var targetAgent = $(targetDay).siblings(' td.monthAgent')[0];
		param.idfAgent = $(targetAgent).attr('id').split('_')[1];
		
		var classOfDay = targetDay.className.split(' ');
		for (var i =0; i < classOfDay.length; i++) {
			var aClass = classOfDay[i].match(/jour_[0-9]+/);
			if (aClass != null ) {
				param.jour = aClass[0].split('_')[1];	
			}
		}
		return param;
	}
	
	function drawPeriode(jour, idfAgent, code, idPeriode, lstIdfPlage) {
		var newPeriode = $('<div id="Periode_' + idPeriode +  '" >' + code  + '</div>').appendTo($('#agent_' + idfAgent).siblings(" .jour_" + jour).children('.statut'))
			.addClass('periode')
			.addClass('ui-state-default');
		for (var i = 0; i < lstIdfPlage.length; i++) { 
			$('#cpt_' + jour + '_' + lstIdfPlage[i]).text(parseInt($('#cpt_' + jour + '_' + lstIdfPlage[i]).text()) +1);
		}
	}
	
	function deletePeriode(idPeriode) {
		$('#Periode_' + idPeriode).remove();
	}
	
	function drawDispo(jour, idfAgent, idfPlage, idDispo) {
		$('#agent_' + idfAgent).siblings(" .jour_" + jour).children('.statut').children('#' + jour + "_" +idfPlage).addClass('DISPO');
	}

	function drawComment(jour, idfAgent, commentaire, idComment) {
		$('#agent_' + idfAgent).siblings(" .jour_" + jour).addClass( "comment" );
		$('#agent_' + idfAgent).siblings(" .jour_" + jour).attr('title', commentaire);
	}
	
	function showListStatut(aTd, idfAgent, jour) {
		document.getElementById('menuContext').style.top = mouseY + "px";
	    document.getElementById('menuContext').style.left = mouseX +"px";
	    document.getElementById('menuContext').style.display = "block";
	    
	    currentIdfAgent = idfAgent;
	    currentJour = jour;
	}

    var renderJour = function (bean) {
        try {
	        var divName = "div_" + bean.idfAgent + "_" + bean.jour;
	        if (bean.statut != null) {
		        	$("#" + divName).text( bean.statut.code);
	        }
	        else {
		        if (! IE)             
		        	$("#" +divName).text( "" );
	        }            
        }
        catch (err) {
            alert(err);
        }       
	}
	
    var checkBesoin = function (bean) {
        besoinOk = true;
        for (var i = 0; i<bean.length; i++)
            if (bean[i].nbVoulu > bean[i].nbObtenu)
                besoinOk = false;
        alert (besoinOk);
    }
    	
   var renderMonth = function (bean) {
       try {
        	window.location.reload();
          }
        catch (err) {
            alert(err);
        }
        
	}
		
    function submitFiliere(aCheckBox) {
        aCheckBox.form.action="mensuel_makeMonth.action";
        aCheckBox.form.submit();
	}

    function submitMonth(aRadioBox) {
        aRadioBox.form.action="mensuel_makeMonth.action";
        aRadioBox.form.submit();
	}

    function submitYear(aSelectBox) {
    	aSelectBox.form.action="mensuel_makeMonth.action";
    	aSelectBox.form.submit();
	}

    </script>
	
</head>
<body>
<jsp:include page="menu.jsp" >
	<jsp:param value="currentPage" name="planning"/>
</jsp:include>

<div>
	<div style="height: 100px; padding-left: 10px;"
		class="ui-widget-header">
		<div class="titreSelection">
			
			<s:form action="ajax_makeMonth" namespace="/planning" theme="simple" id="frmMakeMonth">
			<table>
			<tr><td>Mois</td>
			<td>Année</td>
			<td>Filère</td>
			<td>Compétences</td>
			</tr>
			<tr>	
				<td>
					<sj:radio list="#{'0':'Jan','1':'Fev', '2':'Mar', '3':'Avr', '4':'Mai', '5':'Juin', '6':'Juil', '7':'Aou', '8':'Sep', '9':'Oct','10':'Nov', '11':'Déc'}"
							id="selMonth" indicator="indicatorPlanning"
							name="selectedMonth" onclick="$.publish('topicMakeMonth');"></sj:radio></td>
				<td>
					<s:select id="selYear" list="lstYearCritere" name="selectedYear" label="Année" onchange="$.publish('topicMakeMonth');"></s:select>
				</td>
				<td>
					<s:select list="lstFiliere" name="selectedFiliere" emptyOption="true" onchange="$.publish('topicMakeMonth');"></s:select>
				</td>
				<td>
					<s:select list="lstEmploi" name="selectedEmploi" listKey="idEmploi" listValue="libelle" emptyOption="true" onchange="$.publish('topicMakeMonth');"></s:select>
				</td>
			</tr>
			</table>				
 			</s:form>
 			<div style="float: right;">				
 				<sj:submit listenTopics="topicMakeMonth" onBeforeTopics="planningBegin" onSuccessTopics="planningComplete" formIds="frmMakeMonth" targets="planingDiv" indicator="ajaxLoadingDlg" loadingText="Chargement du mois" cssStyle="display:none;" ></sj:submit>
	 			<s:form action="planning" namespace="/print" id="frmPrint" target="_new" method="get"></s:form>
	 			<sj:a buttonIcon="ui-icon-print" button="true"  formIds="frmPrint">Imprimer</sj:a>
	 		</div>
			<div style="clear: left;"></div>
		</div>
	</div>
	<div style="text-align: center; height: 100%; vertical-align: middle; background-color: gainsboro;">
		<img id="indicatorPlanning" src="<%=request.getContextPath()%>/images/calculCycle.gif" style="display:none; margin-top:50px;" />
		<s:url action="ajax_" method="makeMonth" namespace="/planning" id="ajaxMonth" includeParams="none"></s:url>
		<sj:div id="planingDiv" href="%{ajaxMonth}" indicator="indicatorPlanning" onSuccessTopics="planningComplete" ></sj:div>
	</div>
 </div>
</body>
</html>
