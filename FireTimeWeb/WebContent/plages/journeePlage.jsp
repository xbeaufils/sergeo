<%@ page language="java"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
  <script type="text/javascript">
  	$.subscribe('sliderRange', function(event, data) {
		$('#heureDebut').val( Math.floor(event.originalEvent.ui.values[0]/6) ); 
		var minuteDebut = 
			(event.originalEvent.ui.values[0] -( 6 * Math.floor(event.originalEvent.ui.values[0]/6) )) * 10;
		if ( minuteDebut < 10)
			$('#minuteDebut').val( '0' + minuteDebut );
		else			
			$('#minuteDebut').val(minuteDebut);
		$('#heureFin').val( Math.floor(event.originalEvent.ui.values[1]/6) );
		var minuteFin = 
			(event.originalEvent.ui.values[1] -( 6 * Math.floor(event.originalEvent.ui.values[1]/6) )) * 10;
		if ( minuteFin < 10)
			$('#minuteFin').val( '0' + minuteFin );
		else			
			$('#minuteFin').val(minuteFin);
	});

    </script>
 <s:form id="plageForm" action="updatePlage" method="post" namespace="/horaire" theme="simple">
 	<s:textfield name="currentPlageViewer.libelle"></s:textfield><br>
	<s:textfield id="heureDebut"  name="currentPlageViewer.interval.debut.heure" value="%{currentPlageViewer.interval.debut.heure}"  cssClass="heurePlage"></s:textfield>
	<s:textfield id="minuteDebut" name="currentPlageViewer.interval.debut.minute" value="%{currentPlageViewer.interval.debut.minute}" cssClass="heurePlage"></s:textfield>	
      	<sj:slider id="echo4" 
		label="Echo" value="{0, 144}" min="currentPlageViewer.min" max="currentPlageViewer.max" 
		cssStyle="margin: 10px;" 
		onSlideTopics="sliderRange"/>
	<div>
		<s:textfield id="heureFin"  name="currentPlageViewer.interval.fin.heure" value="%{currentPlageViewer.interval.fin.heure}" cssClass="heurePlage"></s:textfield>
		<s:textfield id="minuteFin" name="currentPlageViewer.interval.fin.minute" value="%{currentPlageViewer.interval.fin.minute}" cssClass="heurePlage"></s:textfield>
		<s:checkbox id="nextDay" name="currentPlageViewer.interval.fin.onNextDay" value="false" ></s:checkbox>
 	</div>	
</s:form>	
<script type="text/javascript"  >
$(document).ready(function() {
	$("#plageHoraire").dialog();
	var index = 0;	
	$('.hour').click(function(e) {
		var saisie = prompt("Libell� de la plage horaire :", "");
		if (saisie == null)
			return false;
		appendIntervalle(saisie, 0, $(this).text(), 0);
	});

	function appendIntervalle(libelle, idfPlage, heure, minute) {
		// Ajout des champs de saisie
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].idfPlage' id='lstPlage_" + index + "_idfPlage' value='" + idfPlage +"'></input>");
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].libelle' id='lstPlage_" + index + "_libelle'></input>");
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].heureDebut' id='lstPlage_" + index + "_heure'></input>");
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].minuteDebut' id='lstPlage_" + index + "_minute'></input>");
		$("#lstPlage_" + index + "_libelle").val(libelle);
		$("#lstPlage_" + index + "_heure").val(heure);
		$("#lstPlage_" + index + "_minute").val(minute);
		// Ajout du curseur graphique
		$('#echelle').append('<div id="debut_' + index + '" class="cursorDebut"><div class="timeslider-slider">&nbsp;</div><div>&nbsp;</div><div class="titleHour" style="width:50px;">'+ libelle + '</div><div class="affichageHeure"></div></div>');
		$("#debut_" + index).data("hiddenIdfValue","lstPlage_" + index + "_idfPlage");
		$("#debut_" + index).data("hiddenLibelleValue","lstPlage_" + index + "_libelle");
		$("#debut_" + index).data("hiddenHeureValue","lstPlage_" + index + "_heure");
		$("#debut_" + index).data("hiddenMinuteValue","lstPlage_" + index + "_minute");
		// Positionnement sur l'heure cliqu�e
		$("#debut_"+index).css('left', getCoordHeure(heure) + getCoordMinute(minute) );
		$("#debut_" + index + " > .affichageHeure").text( heure + ":" + minute  );
		$( ".cursorDebut" ).draggable({
			axis: 'x', 
			containment: "#echelle",
			grid: [3,0],
			drag: changeTime,
			stop: setTimeIntervalle});
		$( ".cursorDebut" ).hover( 
			function() {
				$("#" + this.id + " > .affichageHeure").fadeIn('slow');
				$("#" + this.id + " > .titleHour").fadeIn('slow');
			},
			function() {
				$("#" + this.id + " > .affichageHeure").fadeOut('slow');
				$("#" + this.id + " > .titleHour").fadeOut('slow');
			}
		);
		$(".cursorDebut").dblclick( function() {
			$("#" + $(this).data("hiddenHeureValue") ).val("");
			$("#" + this.id).remove();			
		});
		index ++;
	}
	
	function changeTime(event, ui) {
		currentlyDraggedNode = this;	
		$("#" + currentlyDraggedNode.id + " > .affichageHeure").text( getTime( $("#" + currentlyDraggedNode.id).position().left - $("#echelle").position().left));
	}

	function setTimeIntervalle(event, ui) {
		currentlyDraggedNode = this;	
		$("#" + $("#" + currentlyDraggedNode.id).data("hiddenHeureValue")).val(getHourIntervalle($("#" + currentlyDraggedNode.id).position().left - $("#echelle").position().left));
		$("#" + $("#" + currentlyDraggedNode.id).data("hiddenMinuteValue")).val(getMinuteIntervalle($("#" + currentlyDraggedNode.id).position().left - $("#echelle").position().left));
	}
	
	function getTime(coord) {
		return getHourIntervalle(coord) + ":"+getMinuteIntervalle(coord);
	}

	function getHourIntervalle(coord) {
		var heure = parseInt( coord /18);
		return heure;
	}

	function getCoordHeure(heure) {
		var coord = heure * 18;
		return coord;
	}
	
	function getMinuteIntervalle(coord) {
		var minute = parseInt( Math.floor(coord %18) / 3 )*10 + '';
		if (minute.length == 1)
			minute = "0" + minute;
		return minute;
	}

	function getCoordMinute(minute) {
		var coord = (minute * 18) / 60;
		
		return coord;
	}

});

</script>	

<sj:submit formIds="plageForm" onCompleteTopics="reloadPlage" targets="tabPlages" key="save" button="true" buttonIcon="ui-icon-check" ></sj:submit>