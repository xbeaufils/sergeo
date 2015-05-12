<%@ page language="java"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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
