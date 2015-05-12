<%@ page language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
		<style type="text/css">
			span.tabSelected {
    			background: url("images/arrow_white.gif") no-repeat scroll right center transparent;
			    color: darkred;
    			padding: 3px 30px 3px 10px;
    			font-weight: bold;
			}
			span.tabUnselected {
    			background: url("images/arrow_red.gif") no-repeat scroll right center darkred;
    			border: thin solid;
    			color: wheat;
    			padding: 3px 30px 3px 10px;
			}
		</style>
		<div style="height: 30px;">
			<span id="tabEtape1" class="tabSelected">Etape 1: Grades</span>
			<span id="tabEtape2" class="tabUnselected">Etape 2: Emplois opérationnels</span>
			<span id="tabEtape3" class="tabUnselected">Etape 3: Agents</span>
			<span id="tabEtape4" class="tabUnselected">Etape 4: Affectations</span>
			<span id="tabEtape5" class="tabUnselected">Etape 5: Compétences</span>
		</div>
		<img src="images/calculCycle.gif" id="calculGif" style="display: none;"/> 
		<sj:div id="divEtape" href="/stepGrade.jsp" indicator="calculGif" ></sj:div>
