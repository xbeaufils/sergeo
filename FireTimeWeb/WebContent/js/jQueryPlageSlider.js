$(document).ready(function() {
	$("#plageHoraire").dialog();
	
	var intervalleCount = -1;
	$('.hour').click(function(e) {
		var saisie = prompt("Libellé de la plage horaire :", "");
		if (saisie == null)
			return false;
		appendIntervalle(saisie, 0, $(this).text(), 0);
		intervalleCount --;
	});

	function appendIntervalle(libelle, idfPlage, heure, minute) {
		// Ajout des champs de saisie
		var index = $("input:hidden").size() / 4;
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].idfPlage' id='lstPlage_" + index + "_idfPlage' value='" + idfPlage +"'></input>");
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].libelle' id='lstPlage_" + index + "_libelle'></input>");
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].heureDebut' id='lstPlage_" + index + "_heure'></input>");
		$("#plageForm").append("<input type='hidden' name='lstPlage[" + index + "].minuteDebut' id='lstPlage_" + index + "_minute'></input>");
		$("#lstPlage_" + index + "_heure").val(heure);
		$("#lstPlage_" + index + "_minute").val(minute);
		// Ajout du curseur graphique
		$('#echelle').append('<div id="debut_' + index + '" class="cursorDebut"><div class="timeslider-slider">&nbsp;</div><div>&nbsp;</div><div class="titleHour" style="width:50px;">'+ libelle + '</div><div class="affichageHeure"></div></div>');
		$("#debut_" + index).data("hiddenLibelleValue","lstPlage_" + index + "_libelle");
		$("#debut_" + index).data("hiddenHeureValue","lstPlage_" + index + "_heure");
		$("#debut_" + index).data("hiddenMinuteValue","lstPlage_" + index + "_minute");
		$("#lstPage_" + index + "_libelle").val(libelle);
		// Positionnement sur l'heure cliquée
		$("#debut_"+index).offset({left: getCoordHeure(heure) + getCoordMinute(minute) });
		$("#debut_" + index + " > .affichageHeure").text( heure + ":" + minute );
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
	}
	
	function changeTime(event, ui) {
		currentlyDraggedNode = this;	
		$("#" + currentlyDraggedNode.id + " > .affichageHeure").text( getTime( $("#" + currentlyDraggedNode.id).position().left));
	}

	function setTimeIntervalle(event, ui) {
		currentlyDraggedNode = this;	
		$("#" + $("#" + currentlyDraggedNode.id).data("hiddenHeureValue")).val(getHourIntervalle($("#" + currentlyDraggedNode.id).position().left));
		$("#" + $("#" + currentlyDraggedNode.id).data("hiddenMinuteValue")).val(getMinuteIntervalle($("#" + currentlyDraggedNode.id).position().left));
	}
	
	function getTime(coord) {
		return getHourIntervalle(coord) + ":"+getMinuteIntervalle(coord);
	}

	function getHourIntervalle(coord) {
		var heure = parseInt((coord - 18) /18);
		return heure;
	}

	function getCoordHeure(heure) {
		var coord = (heure + 18) * 18;
		return coord;
	}
	function getMinuteIntervalle(coord) {
		var minute = parseInt( Math.floor((coord-18) %18) / 3 )*10 + '';
		if (minute.length == 1)
			minute = "0" + minute;
		return minute;
	}

	function getCoordMinute(minute) {
		var coord = (minute * 18) / 60;
		return coord;
	}
});

