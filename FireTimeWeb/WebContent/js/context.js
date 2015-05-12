/*
	Réalisé par oli.
	http://blog.oli-web.com
*/

var curX;
var curY;
var idMenu;

function menuContextuel(e, pIdMenu){
	if (e.button == 2){
		if(pIdMenu != ''){
			hideMenu();
			idMenu = pIdMenu;
			getCursorPosition(e);
			showContextualMenu();
		}
	}else{
		hideMenu();
	}
}

function hideMenu(){
	if((idMenu != '') && (idMenu != null)){
		document.getElementById(idMenu).style.display = 'none';
	}
}

function showContextualMenu(){
	if(idMenu != ''){
		document.getElementById(idMenu).style.display = 'block';
		document.getElementById(idMenu).style.top = curY + 'px';
		document.getElementById(idMenu).style.left = curX + 'px';
	}
}

function getCursorPosition(e){
	//ie
	if(document.all){
		curX = event.clientX;
		curY = event.clientY;
	}
	
	//netscape 4
	if(document.layers){
		curX = e.pageX;
		curY = e.pageY;
	}
	
	//mozilla
	if(document.getElementById){
		curX = e.clientX;
		curY = e.clientY;
	}
}