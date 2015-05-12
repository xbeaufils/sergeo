

/*
 * Debut de plage
 */
function Plage( id, index, libelle, heure, minute) {
	this.index = index;
	var id = id;
	var heure = heure;
	var minute = minute;
	var plageDiv = null;
	var libelle = libelle;
	var minX = null;
	var maxX = null;
	
	this.show = function (aSlider) {
	    plageDiv = document.createElement('div');
	    plageDiv.id="plage_" + id;
	    PlageSlider.addEvent(plageDiv, "dblclick", aSlider.removePlage );
	    PlageSlider.addEvent(plageDiv, "mousedown", aSlider.plageMouseDown)
	    aSlider.getRegle().appendChild(plageDiv);
	    plageDiv.style.position=" absolute";
	    plageDiv.style.top="3px";
	    var image = document.createElement('img');
	    image.src="../images/sl_h.gif" ;
	    image.width= "10";
	    plageDiv.appendChild(image);
	    var spanLbl = document.createElement('span');
	    spanLbl.className="libelle_plage";
	    spanLbl.innerHTML = libelle;
	    plageDiv.appendChild(spanLbl);
	    plageData = document.createElement('div');
	    plageData.id="plage_data_" + index;
	    inputIdfPlage = document.createElement('input');
	    inputIdfPlage.value=id;
	    inputIdfPlage.type="hidden";
	    inputIdfPlage.name="lstPlage["+ index +"].idfPlage";
	    plageData.appendChild(inputIdfPlage);
	    inputlibellePlage = document.createElement('input');
	    inputlibellePlage.value=libelle;
	    inputlibellePlage.type="hidden";
	    inputlibellePlage.name="lstPlage["+ index +"].libelle";
	    plageData.appendChild(inputlibellePlage);
	    inputHeurePlage = document.createElement('input');
	    inputHeurePlage.id="plage_data_" + index + "_heure";
	    inputHeurePlage.value=heure;
	    inputHeurePlage.type="hidden";
	    inputHeurePlage.name="lstPlage["+ index +"].heureDebut";
	    plageData.appendChild(inputHeurePlage);
	    inputMinutePlage = document.createElement('input');
	    inputMinutePlage.id="plage_data_" + index + "_minute";
	    inputMinutePlage.value=minute;
	    inputMinutePlage.type="hidden";
	    inputMinutePlage.name="lstPlage["+ index +"].MinuteDebut";
	    plageData.appendChild(inputMinutePlage);
	    aSlider.getDiv().appendChild(plageData);
	    plageDiv.style.left= ( 42* heure) + (42*minute/60)+ 3 + "px";
	};

	this.calculateHour = function() {
		var position = ( parseInt(plageDiv.offsetLeft) -3 )/42;
		heure =  Math.floor( position );
		minute = Math.round( ( position - Math.floor(position) ) *60 );
		console.log("New Hour " + heure + ":" + minute + " position " + position);
		document.getElementById("plage_data_" + index + "_heure").value = heure;
		document.getElementById("plage_data_" + index + "_minute").value = minute;
	};

	this.getDiv = function() {
		return plageDiv;
	};
	
	this.setMinX = function(min) {
		minX = min;
	};
	
	this.getMinX = function() {
		return minX;
	};
	
	this.setMaxX = function(max) {
		maxX = max;
	};
	this.getMaxX = function() {
		return maxX;
	};
};



/*
 *  Gestion du Slider
 */
 function PlageSlider() {

	var self = this;
	var arrayPlage = new Array();

	var regle;
	var myDiv;
	var plageInMove = null;
	var newId = -1;
	var index = 0;
	
	var ns4=document.layers;
	var ie4=document.all;
	var ns6=document.getElementById&&!document.all;

	this.show = function(divName) {
	    myDiv = document.getElementById(divName);
	    myDiv.style.position="relative";
	    myDiv.style.height= "60px";    
	    regle = document.createElement('div');
	    regle.style.background="transparent url(../images/bkg_time.gif) repeat scroll 7px";
	    regle.style.position="relative";
	    regle.style.height=" 20px";
	    regle.style.top=" 20px";
	    regle.style.width="1002px";
	    myDiv.appendChild(regle);
	    newId = -1;
	    for (i=0; i<24 ;i++) {
		    var plageDiv = document.createElement('div');
		    plageDiv.style.position="absolute";
		    plageDiv.style.top="0px";
		    plageDiv.style.left= ( 42* i) +"px";
		    if (i<10)
		    	plageDiv.innerHTML= '0'+i;
		    else
		    	plageDiv.innerHTML= i +' ';
		    PlageSlider.addEvent(plageDiv, "click", self.addNewPlage );
		    myDiv.appendChild(plageDiv);
	    }
	 };

	this.addPlage = function(id, libelle, heure, minute) {
		var plage = new Plage(id, index++, libelle, heure, minute)
		arrayPlage.push(plage);
		plage.show(self);
		arrayPlage.sort(sortPlage);
		calculateMinMax(plage);
	};

	this.addNewPlage = function( ev  ) {
		var saisie = prompt("Libellé de la plage horaire :", "");
		if (saisie == null)
			return false;
		var target = PlageSlider.is_ie ? window.event.srcElement : ev.target;
		var heure = parseInt(target.childNodes[0].data,10);
		var plage = new Plage(newId --, index++,  saisie, heure, 0);
		arrayPlage.push(plage);
		plage.show(self);
		arrayPlage.sort(sortPlage);
		calculateMinMax(plage);
	};

/*
 * 	Debut du DRAG
 */
	this.plageMouseDown = function(ev) {
		var target = PlageSlider.is_ie ? window.event.srcElement : ev.target;
		searchIndex = searchPlageFromDiv(target.parentNode);
		plageInMove = arrayPlage[searchIndex];
		PlageSlider.addEvent(regle, "mouseup", self.calDragEnd);
		PlageSlider.addEvent(regle, "mousemove", self.calDragIt);
		// PlageSlider.dragStart(ev);
		return PlageSlider.stopEvent(ev);
	};

	this.calDragIt = function (ev) {
		var target = PlageSlider.is_ie ? window.event.srcElement : ev.target;
		if (plageInMove != null) {
			if (ie4) { //&&dragapproved){
				// crossobj.style.left=tempx+event.clientX-offsetx;
				plageInMove.getDiv().style.top=tempy+event.clientY-offsety;
				return false;
			}
			else if (ns6) { //&&dragapproved){				
				if ( ev.clientX % 7== 0) {
					if (ev.clientX > plageInMove.getMinX() 
							&& ev.clientX < plageInMove.getMaxX()) {				
						plageInMove.getDiv().style.left =  
							ev.clientX - myDiv.offsetLeft +"px";
					}
				}
				return false;
			}
		}
		return PlageSlider.stopEvent(ev);
	};
/*
 * DROP
 */
	this.calDragEnd = function (ev) {
		PlageSlider.removeEvent(regle, "mousemove", self.calDragIt);
		PlageSlider.removeEvent(regle, "mouseup", self.calDragEnd);
		plageInMove.calculateHour();
		calculateMinMax(plageInMove);
		plageInMove = null;
	};
/*
 * Remove Plage Event
 */
	this.removePlage = function( ev  ) {
		var target = PlageSlider.is_ie ? window.event.srcElement : ev.target;
		if (! confirm("Etes vous sur de supprimer cette plage ?")  ) 
			return false;			
		plageToDelete = target.parentNode;
		if (! plageToDelete )
			return false;
		var searchIndex = searchPlageFromDiv(plageToDelete);
		if (searchIndex == -1 )
			// Pas trouvé !
			return;
		if (searchIndex!= 0) { 
			// Ce n'est pas le premier
			arrayPlage[searchIndex-1].setMaxX(arrayPlage[searchIndex].getMaxX());
		}		
		if (searchIndex != (arrayPlage.length -1) ) { 
			// Ce n'est pas le dernier	
			arrayPlage[searchIndex+1].setMinX(arrayPlage[searchIndex].getMinX());
		}
		divData = document.getElementById("plage_data_"+ arrayPlage[searchIndex].index);
		divData.removeChild(document.getElementById("plage_data_"+ arrayPlage[searchIndex].index+"_heure"));
		divData.removeChild(document.getElementById("plage_data_"+ arrayPlage[searchIndex].index+"_minute"));
		arrayPlage.splice(searchIndex, 1);
		
		var elemForDestroy = document.getElementById(target.parentNode.id);
		regle.removeChild(elemForDestroy);
	};	
	
	this.getDiv = function() {
		return myDiv;
	};

	this.getRegle = function() {
		return regle;
	};
	
	this.getPlages = function() {
		return arrayPlage;
	};
	
	function sortPlage(plage1, plage2) {
		return (plage1.getDiv().offsetLeft - plage2.getDiv().offsetLeft); 
	};
	
	function searchPlage(aPlage) {
		for(i=0;i< arrayPlage.length;i++) {
			if (arrayPlage[i].getDiv().id == aPlage.getDiv().id )
				return i;
        }		
		return -1;
	}
	
	function searchPlageFromDiv( divPlage ) {
		for(i=0;i< arrayPlage.length;i++) {
			if (arrayPlage[i].getDiv().id == divPlage.id )
				return i;
        }		
		return -1;
	}
	
	function calculateMinMax(aPlage) {
		var searchIndex = searchPlage(aPlage);
		if (searchIndex == -1 )
			// Pas trouvé !
			return;
		if (searchIndex == 0) { 
			// C'est le premier
			arrayPlage[searchIndex].setMinX(0);
		}
		else {
			arrayPlage[searchIndex].setMinX(arrayPlage[searchIndex-1].getDiv().offsetLeft);
			arrayPlage[searchIndex-1].setMaxX(arrayPlage[searchIndex].getMinX());
		}
		
		if (searchIndex == (arrayPlage.length -1) ) { 
			// C'est le dernier	
			arrayPlage[searchIndex].setMaxX(regle.offsetWidth);
		}
		else {
			arrayPlage[searchIndex].setMaxX(arrayPlage[searchIndex+1].getDiv().offsetLeft);
			arrayPlage[searchIndex+1].setMinX(arrayPlage[searchIndex].getDiv().offsetLeft);
		}
	}
};


/** Internal function.  Starts dragging the element. */

PlageSlider.is_ie5 = ( PlageSlider.is_ie && /msie 5\.0/i.test(navigator.userAgent) );
/// detect a special case of "web browser"
PlageSlider.is_ie = ( /msie/i.test(navigator.userAgent) &&
		   !/opera/i.test(navigator.userAgent) );

PlageSlider.addEvent = function(el, evname, func) {
	if (el.attachEvent) { // IE
		el.attachEvent("on" + evname, func);
	} else if (el.addEventListener) { // Gecko / W3C
		el.addEventListener(evname, func, true);
	} else {
		el["on" + evname] = func;
	}
};
PlageSlider.removeEvent = function(el, evname, func) {
	if (el.detachEvent) { // IE
		el.detachEvent("on" + evname, func);
	} else if (el.removeEventListener) { // Gecko / W3C
		el.removeEventListener(evname, func, true);
	} else {
		el["on" + evname] = null;
	}
};

PlageSlider.stopEvent = function(ev) {
	ev || (ev = window.event);
	if (PlageSlider.is_ie) {
		ev.cancelBubble = true;
		ev.returnValue = false;
	} else {
		ev.preventDefault();
		ev.stopPropagation();
	}
	return false;
};


