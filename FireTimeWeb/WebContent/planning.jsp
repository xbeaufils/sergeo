<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <s:head theme="ajax" />
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var mouseX=0;
	var mouseY=0;
	var currentIdfAgent = 0;
	var currentJour = 0;
    var servicePlanning = new dojo.rpc.JsonService("<s:url action="jsonCall" namespace="/rpc" includeParams="none"></s:url>");
    	
	var IE = document.all?true:false;
	
	if (!IE) document.captureEvents(Event.MOUSEMOVE);
	
	function showListStatut(aDiv, idfAgent, jour) {
	        document.getElementById('menu').style.top = mouseY + "px";
	        document.getElementById('menu').style.left = mouseX +"px";
	        document.getElementById('menu').style.display = "block";
	        currentIdfAgent = idfAgent;
	        currentJour = jour;
	}

    var renderJour = function (bean) {
        document.getElementById(currentAgent + "-" +currentJour).className= "jour_" + bean.statut.code;
        document.getElementById('menu').style.display = "none";
        
	}
	function sendStatut(idfStatut) {
        var planning = servicePlanning.setStatut(currentIdfAgent, currentJour, idfStatut);
        planning.addCallback(renderJour);		
	}
		
	function getMouseXY(e) {
	        if (IE) { // grab the x-y pos.s if browser is IE
		        tempX = event.clientX + document.body.scrollLeft;
		        tempY = event.clientY + document.body.scrollTop;
	        } else { // grab the x-y pos.s if browser is NS
                tempX = e.pageX;
                tempY = e.pageY;
	        }
	        if (tempX < 0){tempX = 0;}
	        if (tempY < 0){tempY = 0;}
	        mouseX = tempX;
	        mouseY = tempY;
	        return true;
	}
	document.onmousemove = getMouseXY;
	</script>
<title>Planning mensuel</title>
	<link href="<%=request.getContextPath()%>/css/jour.css" type="text/css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/context.css" type="text/css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/menu.css" type="text/css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/css/horiz-drop-down.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="/js/jquery.horizontalnav.js"></script>
		<script type="text/javascript" src="/js/jquery.hoverIntent.minified.js"></script>
	   	<script type="text/javascript" charset="utf-8">
	    $(function(){
	        $('.horizontal-dropdown').animatedHorizontalNav();
	    });
	       
	    </script> 
</head>
<body>
<div id="menu" class="menucontextuel">
<ul>
	<li><em>Menu 1</em></li>
	<s:iterator value="lstStatut">
		<li><a href="#" onclick="sendStatut(<s:property value="idfStatut"/>)"><s:property value="libelle"/> </a></li>
	</s:iterator>
</ul>
</div>

		<%---Affichage des jours du mois  --%>
	<table class="ligneCal">
	<thead>
		<tr><td class="Agent">Agents</td>
		<s:iterator value="lstDayOfMonth" id="aDay">
					<td class="jour_"><s:property value="#aDay" /></td>
				</s:iterator>
		</tr>
	</thead>
<%-- Iteration sur les agents --%>
	<tbody>
	<s:iterator value="lstPlanning" status="planningStatus">
		<tr class="ligneCal">
			<td class="Agent">
			<s:property value="agent.nom"/> <s:property value="agent.prenom"/>
			</td>
			<%-- Iteration sur les jours du mois --%>
			<s:iterator value="month">
				<td class="jour_<s:property value="idfStatut"/>" 
					onclick="showListStatut(this, <s:property value="agent.idfAgent"/>, <s:property value="jour"/>)">
					<div id="<s:property value="agent.idfAgent"/>-<s:property value="jour"/> " class="jour_<s:property value="idfStatut"/>"></div>  
				</td>
			</s:iterator>
		</tr>
	</s:iterator>
	</tbody>
</table>
 <script type="text/javascript">
// <![CDATA[
document.body.oncontextmenu = function(){return false;}
// ]]>
</script>

</body>
</html>