<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<style>
       
       .intervalle {
           border: solid thin black;
           clear: both;
       }
       
       .titleTime {
           float: left;
           border: solid thin red;
       }
       
       #slider{
           width:288px;
           margin: 10px;
       }
       
       #slider-Time {
           border: solid thin blue;
           width:400px;
           height:30px;
           display: none;
           float: right;
       }
       
       #month {
           /* border: solid thin yellow; */
           width: 1060px;
           height: 100%;
           clear: both;
           position: relative;
           overflow: hidden;
       }
       #monthRepresentation {
           left:200px;
          /*  border: solid thin red; */
           width: 870px;
           height: 100%;
           clear: both;
           position: absolute;
           overflow: scroll;
       }
       
       #monthGrid {
	       position: absolute;
       }

	#monthGrid .dayRepresentation:first-child {
	    border-left: thin solid blue;
	}

       .dayRepresentation {
           position: absolute;
           border-top: solid thin blue;
           border-right: solid thin blue;
           border-bottom: solid thin blue;
           width : 30px;
           top: 20px;
           height: 100%;
       }
       .dayTitle {
           position: absolute;
           border-right: solid thin blue;
           border-bottom: solid thin blue;
           width : 30px;
           top: 0px;
           height: 20px;
           z-index: 10;
       }

       .intervalleRepresentation {
           border: solid thin red;
           background-color: wheat;
           height: 16px;
           position: absolute;
           width: 25px;
       }
       
       #listAgent{
           position: absolute;
           top: 20px;    
           height: 90%;
           overflow-y: scroll;
           overflow-x: hidden;
           /* width: 900px; */            
       }
       
       .monthAgent {
           width:1040px;
           left: 0px;
           height: 22px;
           border-bottom: black dashed thin;
           text-align: left;
       }
   </style>
 	<div id="month">
            <div id="monthRepresentation">
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 0px;"><div>1</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 30px;"><div>2</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 60px;"><div>3</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 90px;"><div>4</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 120px;"><div>5</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 150px;"><div>6</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 180px;"><div>7</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 210px;"><div>8</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 240px;"><div>9</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 270px;"><div>10</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 300px;"><div>11</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 330px;"><div>12</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 360px;"><div>13</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 390px;"><div>14</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 420px;"><div>15</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 450px;"><div>16</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 480px;"><div>17</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 510px;"><div>18</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 540px;"><div>19</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 570px;"><div>20</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 600px;"><div>21</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 630px;"><div>22</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 660px;"><div>23</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 690px;"><div>24</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 720px;"><div>25</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 750px;"><div>26</div></div>
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 780px;"><div>27</div></div>
 				<s:if test="maxDay >= 28">
               	<div class="dayTitle ui-widget-content ui-state-default" style="left: 810px;"><div>28</div></div>
               	</s:if>
  				<s:if test="maxDay >= 29">
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 840px;"><div>29</div></div>
               	</s:if>
  				<s:if test="maxDay >= 30">
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 870px;"><div>30</div></div>
               	</s:if>
  				<s:if test="maxDay >= 31">
               <div class="dayTitle ui-widget-content ui-state-default" style="left: 900px;"><div>31</div></div>
               	</s:if>
               <div id="monthGrid">	
	               <div id="jour_1" class="dayRepresentation" style="left: 0px;"></div>
	               <div id="jour_2" class="dayRepresentation" style="left: 30px;"></div>
	               <div id="jour_3" class="dayRepresentation" style="left: 60px;"></div>
	               <div id="jour_4" class="dayRepresentation" style="left: 90px;"></div>
	               <div id="jour_5" class="dayRepresentation" style="left: 120px;"></div>
	               <div id="jour_6" class="dayRepresentation" style="left: 150px;"></div>
	               <div id="jour_7" class="dayRepresentation" style="left: 180px;"></div>
	               <div id="jour_8" class="dayRepresentation" style="left: 210px;"></div>
	               <div id="jour_9" class="dayRepresentation" style="left: 240px;"></div>
	               <div id="jour_10" class="dayRepresentation" style="left: 270px;"></div>
	               <div id="jour_11" class="dayRepresentation" style="left: 300px;"></div>
	               <div id="jour_12" class="dayRepresentation" style="left: 330px;"></div>
	               <div id="jour_13" class="dayRepresentation" style="left: 360px;"></div>
	               <div id="jour_14" class="dayRepresentation" style="left: 390px;"></div>
	               <div id="jour_15" class="dayRepresentation" style="left: 420px;"></div>
	               <div id="jour_16" class="dayRepresentation" style="left: 450px;"></div>
	               <div id="jour_17" class="dayRepresentation" style="left: 480px;"></div>
	               <div id="jour_18" class="dayRepresentation" style="left: 510px;"></div>
	               <div id="jour_19" class="dayRepresentation" style="left: 540px;"></div>
	               <div id="jour_20" class="dayRepresentation" style="left: 570px;"></div>
	               <div id="jour_21" class="dayRepresentation" style="left: 600px;"></div>
	               <div id="jour_22" class="dayRepresentation" style="left: 630px;"></div>
	               <div id="jour_23" class="dayRepresentation" style="left: 660px;"></div>
	               <div id="jour_24" class="dayRepresentation" style="left: 690px;"></div>
	               <div id="jour_25" class="dayRepresentation" style="left: 720px;"></div>
	               <div id="jour_26" class="dayRepresentation" style="left: 750px;"></div>
	               <div id="jour_27" class="dayRepresentation" style="left: 780px;"></div>
			<s:if test="maxDay >= 28">
	               		<div id="jour_28" class="dayRepresentation" style="left: 810px;"></div>
	               </s:if>
			<s:if test="maxDay >= 29">
	               		<div id="jour_29" class="dayRepresentation" style="left: 840px;"></div>
	               </s:if>
			<s:if test="maxDay >= 30">
	               		<div id="jour_30" class="dayRepresentation" style="left: 870px;"></div>
	               	</s:if>
			<s:if test="maxDay >= 31">
	               		<div id="jour_31" class="dayRepresentation" style="left: 900px;"></div>
	               	</s:if>
               	</div>
            </div>
  	<div id="listAgent">
		<s:iterator value="lstPlanning" status="planningStatus">
			<div class="monthAgent" id='agent_<s:property value="agent.idfAgent"/>'>
				<span class="matricule"><s:property value="agent.idfAgent"/></span>
				<s:property value="agent.nom"/> <s:property value="agent.prenom"/>
			</div>
		</s:iterator>
	</div>
</div>
	
