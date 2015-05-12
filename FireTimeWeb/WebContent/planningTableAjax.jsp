<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<table id="month" class="compact">
 		<thead>
 		<tr id="monthRepresentation">
 			<th class="dayTitle ui-widget-content ui-state-default"><div>Agents</div></th>
            <th class="dayTitle ui-widget-content ui-state-default"><div>1</div></th>
            <th class="dayTitle ui-widget-content ui-state-default"><div>2</div></th>
            <th class="dayTitle ui-widget-content ui-state-default"><div>3</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>4</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>5</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>6</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>7</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>8</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>9</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>10</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>11</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>12</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>13</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>14</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>15</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>16</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>17</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>18</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>19</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>20</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>21</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>22</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>23</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>24</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>25</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>26</div></th>
               <th class="dayTitle ui-widget-content ui-state-default"><div>27</div></th>
 				<s:if test="maxDay >= 28">
               	<th class="dayTitle ui-widget-content ui-state-default"><div>28</div></th>
               	</s:if>
  				<s:if test="maxDay >= 29">
               <th class="dayTitle ui-widget-content ui-state-default"><div>29</div></th>
               	</s:if>
  				<s:if test="maxDay >= 30">
               <th class="dayTitle ui-widget-content ui-state-default"><div>30</div></th>
               	</s:if>
  				<s:if test="maxDay >= 31">
               <th class="dayTitle ui-widget-content ui-state-default"><div>31</div></th>
               	</s:if>
            </tr>
         </thead>
		 <tfoot>
		 	<tr>
	            <td class="dayRepresentation">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_<s:property value="#plage.idfPlage"/>'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
				</td>
  	            <td class="dayRepresentation jour_1">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_1_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
				</td>
	            <td class="dayRepresentation jour_2">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_2_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	            </td>
	               <td class="dayRepresentation jour_3">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_3_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_4">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_4_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_5">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_5_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_6">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_6_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_7">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_7_<s:property value="#plage.idfPlage"/>' >0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_8">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_8_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_9">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_9_<s:property value="#plage.idfPlage"/>' >0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_10">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_10_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_11">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_11_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_12">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_12_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_13">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_13_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_14">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_14_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_15">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_15_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_16">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_16_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_17">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_17_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_18">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_18_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_19">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_19_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_20">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_20_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_21">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_21_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_22">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_22_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_23">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_23_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_24">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_24_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_25">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_25_<s:property value="#plage.idfPlage"/>' >0</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_26">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_26_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
					</td>
	               <td class="dayRepresentation jour_27">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='cpt_27_<s:property value="#plage.idfPlage"/>'>0</div>
					</s:iterator>
					</div> 
	               	</td>
	               	<td class="dayRepresentation jour_28">
 						<div class="statut">
	               		<s:iterator value="lstPlage" var="plage">
							<div id='cpt_28_<s:property value="#plage.idfPlage"/>' >0</div>
						</s:iterator>
						</div> 
	               	</td>
					<s:if test="maxDay >= 29">
			       	<td class="dayRepresentation jour_29">
	 					<div class="statut">
						<s:iterator value="lstPlage" var="plage">
							<div id='cpt_29_<s:property value="#plage.idfPlage"/>'>0</div>
						</s:iterator>
						</div> 
               		</td>
			        </s:if>
					<s:if test="maxDay >= 30">
			        	<td class="dayRepresentation jour_30">
	 					<div class="statut">
							<s:iterator value="lstPlage" var="plage">
								<div id='cpt_30_<s:property value="#plage.idfPlage"/>'>0</div>
							</s:iterator>
							</div> 
	               		</td>
	               	</s:if>
					<s:if test="maxDay >= 31">
               			<td class="dayRepresentation jour_31">
	 					<div class="statut">
							<s:iterator value="lstPlage" var="plage">
								<div id='cpt_31_<s:property value="#plage.idfPlage"/>'>0</div>
							</s:iterator>
							</div> 
               			</td>
	               	</s:if>
		    </tr>
		 </tfoot>
         <tbody>
            <s:iterator value="lstPlanning" status="planningStatus">
           		<tr id='<s:property value="agent.idfAgent" />' >
 				<td class="monthAgent" id='agent_<s:property value="agent.idfAgent"/>'>
					<span class="matricule"><s:property value="agent.idfAgent"/></span>
						<s:property value="agent.nom"/> <s:property value="agent.prenom"/>
				</td>
 	            <td class="dayRepresentation jour_1">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='1_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
				</td>
	            <td class="dayRepresentation jour_2">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='2_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	            </td>
	               <td class="dayRepresentation jour_3">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='3_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_4">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='4_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_5">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='5_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_6">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='6_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_7">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='7_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_8">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='8_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_9">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='9_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_10">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='10_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_11">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='11_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_12">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='12_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_13">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='13_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_14">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='14_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_15">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='15_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_16">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='16_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_17">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='17_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_18">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='18_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_19">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='19_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_20">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='20_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_21">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='21_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_22">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='22_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_23">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='23_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_24">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='24_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_25">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='25_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               </td>
	               <td class="dayRepresentation jour_26">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='26_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
					</td>
	               <td class="dayRepresentation jour_27">
 					<div class="statut">
					<s:iterator value="lstPlage" var="plage">
						<div id='27_<s:property value="#plage.idfPlage"/>'  
							class='plageDispo'>
			            	<s:if test="#plage.heureDebut<10">
			            		0<s:property value="#plage.heureDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.heureDebut"/>
			            	</s:else>
			            	:
			            	<s:if test="#plage.minuteDebut<10">
			            		0<s:property value="#plage.minuteDebut"/>
			            	</s:if>
			            	<s:else>
			            		<s:property value="#plage.minuteDebut"/>
			            	</s:else>
						</div>
					</s:iterator>
					</div> 
	               	</td>
	               	<td class="dayRepresentation jour_28">
 						<div class="statut">
	               		<s:iterator value="lstPlage" var="plage">
							<div id='28_<s:property value="#plage.idfPlage"/>'  
								class='plageDispo'>
				            	<s:if test="#plage.heureDebut<10">
				            		0<s:property value="#plage.heureDebut"/>
				            	</s:if>
				            	<s:else>
				            		<s:property value="#plage.heureDebut"/>
				            	</s:else>
				            	:
				            	<s:if test="#plage.minuteDebut<10">
				            		0<s:property value="#plage.minuteDebut"/>
				            	</s:if>
				            	<s:else>
				            		<s:property value="#plage.minuteDebut"/>
				            	</s:else>
							</div>
						</s:iterator>
						</div> 
	               	</td>
					<s:if test="maxDay >= 29">
			       	<td class="dayRepresentation jour_29">
	 					<div class="statut">
						<s:iterator value="lstPlage" var="plage">
							<div id='29_<s:property value="#plage.idfPlage"/>'  
								class='plageDispo'>
				            	<s:if test="#plage.heureDebut<10">
				            		0<s:property value="#plage.heureDebut"/>
				            	</s:if>
				            	<s:else>
				            		<s:property value="#plage.heureDebut"/>
				            	</s:else>
				            	:
				            	<s:if test="#plage.minuteDebut<10">
				            		0<s:property value="#plage.minuteDebut"/>
				            	</s:if>
				            	<s:else>
				            		<s:property value="#plage.minuteDebut"/>
				            	</s:else>
							</div>
						</s:iterator>
						</div> 
               		</td>
			        </s:if>
					<s:if test="maxDay >= 30">
			        	<td class="dayRepresentation jour_30">
	 					<div class="statut">
							<s:iterator value="lstPlage" var="plage">
								<div id='30_<s:property value="#plage.idfPlage"/>'  
									class='plageDispo'>
					            	<s:if test="#plage.heureDebut<10">
					            		0<s:property value="#plage.heureDebut"/>
					            	</s:if>
					            	<s:else>
					            		<s:property value="#plage.heureDebut"/>
					            	</s:else>
					            	:
					            	<s:if test="#plage.minuteDebut<10">
					            		0<s:property value="#plage.minuteDebut"/>
					            	</s:if>
					            	<s:else>
					            		<s:property value="#plage.minuteDebut"/>
					            	</s:else>
								</div>
							</s:iterator>
							</div> 
	               		</td>
	               	</s:if>
					<s:if test="maxDay >= 31">
               			<td class="dayRepresentation jour_31">
	 					<div class="statut">
							<s:iterator value="lstPlage" var="plage">
								<div id='31_<s:property value="#plage.idfPlage"/>'  
									class='plageDispo'>
					            	<s:if test="#plage.heureDebut<10">
					            		0<s:property value="#plage.heureDebut"/>
					            	</s:if>
					            	<s:else>
					            		<s:property value="#plage.heureDebut"/>
					            	</s:else>
					            	:
					            	<s:if test="#plage.minuteDebut<10">
					            		0<s:property value="#plage.minuteDebut"/>
					            	</s:if>
					            	<s:else>
					            		<s:property value="#plage.minuteDebut"/>
					            	</s:else>
								</div>
							</s:iterator>
							</div> 
               			</td>
	               	</s:if>
	               	</tr>
				</s:iterator>
	</tbody>				
 </table>
	
