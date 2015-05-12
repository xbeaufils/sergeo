var Sergeo = {};


Sergeo.Plage = function(id, nom, startHours, startMinuts, endHours, endMinuts, type ) {
	this.startHours = startHours;
	this.startMinuts = startMinuts;
	this.endHours = endHours;
	this.endMinuts = endMinuts;
	this.idPlage = id;
	this.nom = nom;
	this.type = type;
};
Sergeo.DrawPlage = function(divName) {

	this.paper = Raphael(divName, 500,200);
	this.backCircle = this.paper.circle(80, 80, 50);
	this.backCircle.attr({
		"stroke": "black",
		"stroke-width": "3",
		"fill" : "red"
	});
	this.plages= [];

	this.calculeCoord = function (heure, minute) {
		var angle = (heure + minute/60)*15 + 90;
		console.log('angle=' + angle);
		var grandRayon = 50;
		var coord = new Object();
		coord.x = 30 + grandRayon - grandRayon * Math.cos(Math.PI * (angle/180));
		coord.y = 30 + grandRayon - grandRayon * Math.sin(Math.PI * (angle/180));
		console.log("heure=" + heure + " minute=" + minute + " X=" + coord.x + " Y=" + coord.y); 			
		return coord;
	};

	this.drawPeriodeRaph = function (plage) {
		if (plage.startHours == 0 && plage.endHours == 24) {
			var emptyCircle = this.paper.circle(80, 80, 50);
			emptyCircle.attr({
				"stroke": "LightGreen",
				"stroke-width": "25",
				"fill" :"none"
			});
			$(emptyCircle.node).click(function(e) {
				$('#btnOpenPlage').click();
			});
			plage.geometry = emptyCircle;
			this.plages[this.plages.length] = plage;	
		}
		else {
			//if (plage.startHours > plage.endHours)
			//	plage.endHours += 24;
			var point1 = this.calculeCoord( plage.startHours, plage.startMinuts);
			var point2 = this.calculeCoord( plage.endHours, plage.endMinuts);
			var large_flag = null;
			large_flag = ( ( plage.endHours - plage.startHours)<=12)? 0: 1;	
			if (plage.type=='PADDING')
				color = 'LightGreen';
			else
				color = 'LightSteelBlue';
			var plagePicture = this.paper.path('d="M' + point1.x + ',' + point1.y + ' A50,50 0 ' + large_flag + ' 1 ' + point2.x + ',' +point2.y + '"' 
					+' />');
			plagePicture.attr( {
				'stroke': color,
				'stroke-width': '25',
				'fill': 'none'});
			$(plagePicture.node).click(function(e) {
				alert ("click on plage " + plage.idPlage); 
				$('#plageBtn_' + plage.startHours +"_" + plage.startMinuts).click();
 			});
//			$('<path id="plageHoraire_'  + plage.idPlage +'" d="M' + point1.x + ',' + point1.y + ' A50,50 0 ' + large_flag + ' 1 ' + point2.x + ',' +point2.y + '"' 
//			+' />').css('stroke', color).css('stroke-width','25').css('fill','none').appendTo("#PeriodeSvg");
//			$('<rect x="' + point1.x +'" y="' + point1.y +'" width="5px" height="5px" style="fill:blue;stroke:red"/>').appendTo("#PeriodeSvg");
//			$('<rect x="' + point2.x +'" y="' + point2.y +'" width="5px" height="5px" style="fill:green;stroke:red"/>'
//			).appendTo("#PeriodeSvg");
		} 	
	};
};



function drawPeriode(plage) {
	if (plage.startHours == 0 && plage.endHours == 24) {
		$('<circle id="plageEmpty_1" cx="80" cy="80" r="50" stroke="LightGreen" stroke-width="25" fill="none" />').appendTo("#PeriodeSvg");
	}
	else {
		var point1 = calculeCoord( plage.startHours, plage.startMinuts);
		var point2 = calculeCoord( plage.endHours, 	 plage.endMinuts);
		var large_flag = ( ( plage.endHours - plage.startHours)<=12)? 0: 1;	
		if (plage.type=='PADDING')
			color = 'LightGreen';
		else
			color = 'LightSteelBlue';
		$('<path id="plageHoraire_'  + plage.idPlage +'" d="M' + point1.x + ',' + point1.y + ' A50,50 0 ' + large_flag + ' 1 ' + point2.x + ',' +point2.y + '"' 
		+' />').css('stroke', color).css('stroke-width','25').css('fill','none').appendTo("#PeriodeSvg");
		$('<rect x="' + point1.x +'" y="' + point1.y +'" width="5px" height="5px" style="fill:blue;stroke:red"/>').appendTo("#PeriodeSvg");
		$('<rect x="' + point2.x +'" y="' + point2.y +'" width="5px" height="5px" style="fill:green;stroke:red"/>'
		).appendTo("#PeriodeSvg");
	} 	
};

function drawPeriode(idfPlage, debut, fin, padding) {
	console.log( "Date debut " + debut.getDate() + " fin " + fin.getDate() );
	if (fin.getDate() - debut.getDate() > 0) {
		$('<circle id="plageEmpty_1" cx="80" cy="80" r="50" stroke="LightGreen" stroke-width="25" fill="none" />').appendTo("#PeriodeSvg");
		$('#plageEmpty_1').click(function() {alert("Plage id " + $this.attr('id'));} );
	}
	else {
		var point1 = calculeCoord( debut.getHours(), debut.getMinutes());
		var point2 = calculeCoord( fin.getHours(), fin.getMinutes());
		var large_flag = ( ( fin.getHours() - debut.getHours())<=12)? 0: 1;	
		if (padding=='PADDING')
			color = 'LightGreen';
		else
			color = 'LightSteelBlue';
		$('<path id="plageHoraire_'  + idfPlage +'" d="M' + point1.x + ',' + point1.y + ' A50,50 0 ' + large_flag + ' 1 ' + point2.x + ',' +point2.y + '"' 
		+' />').css('stroke', color).css('stroke-width','25').css('fill','none').appendTo("#PeriodeSvg");
		$('<rect x="' + point1.x +'" y="' + point1.y +'" width="5px" height="5px" style="fill:blue;stroke:red"/>').appendTo("#PeriodeSvg");
		$('<rect x="' + point2.x +'" y="' + point2.y +'" width="5px" height="5px" style="fill:green;stroke:red"/>'
		).appendTo("#PeriodeSvg");
	} 	
};


function clickPeriode() {
	
};