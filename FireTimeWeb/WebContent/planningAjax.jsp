<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
  	
     $(document).ready(function() {
			$('#calendAjaxTable').dataTable( {
			"bJQueryUI": true,
			"bPaginate": false,
            "bScrollCollapse": true,
            "sScrollY": "400px",
            "bAutoWidth": false,
            "oLanguage": {
                        "sInfo": "_TOTAL_ agents"
             },
             "bFilter": false,
             "bSort" : false,
             "bProcessing": true
			} );
			
			  $("#calendAjaxTable").delegate('td','mouseover mouseleave', function(e) {
			       if (e.type == 'mouseover') {
			           if ( $(this).index() > 0 ) {
			               $(this).parent().addClass("hover");
			               $('#tableone tr>td:nth-child(' + ($(this).index() +1) + ')').addClass("hover");
			           }
			       }
			       else {
			         $(this).parent().removeClass("hover");
			         $('#tableone tr>td:nth-child(' + ( $(this).index() +1 ) + ')').removeClass("hover");
			       }
			   });
			    $("#calendAjaxTable td").contextMenu({
			       menu: 'sendMenuContext'
			       },
			       function(action, el, pos) {
						currentIdfAgent = $(el).parent().children('td:first-child').children('span').text();
		       			currentJour = $(el).index();
		       			var actionArray = action.split('__');
						if (actionArray.length >=1)
				       	if ( actionArray[0] == 'sendStatut')  {
							var planning = servicePlanning.setStatut(currentIdfAgent, currentJour, actionArray[1], <s:property value="idfEchelon" />);
				    		planning.addCallback(renderJour);		
				    		var besoin = servicePlanning.checkBesoin(currentJour, <s:property value="idfEchelon" />);
				    		besoin.addCallback(checkBesoin);
				       	}
				       	if ( actionArray[0] == 'deleteStatut' ) {
				           var planning = servicePlanning.deleteStatut(currentIdfAgent, currentJour);
				           planning.addCallback(renderJour);		
				       	}
				       	if ( actionArray[0] == 'sendCycle') {
				   			var fin = prompt("Entrez la date du dernier jour", '31/12/<s:date name="#session.currentDate.time" format="yyyy"/>');
							if (fin != null) {
								if (! isNaN(Date.parse(fin)) ) {
					        		var planning = servicePlanning.setCycle(currentIdfAgent, currentJour, fin, actionArray[1]);
				    	    		planning.addCallback(renderMonth);
				    	    		document.getElementById("light").style.display="block";
				    	    		document.getElementById("fade").style.display="block";
								}
							}		
				       }
			   });
				   
			     
					     
		});
   </script>

 	<table id="calendAjaxTable">
	<thead>
		<tr>
			<th>Agents</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="1"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">1</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="2"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">2</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="3"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">3</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="4"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">4</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="5"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">5</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="6"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">6</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="7"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">7</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="8"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">8</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="9"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">9</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="10"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">10</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="11"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">11</s:a>
			</th>
				<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="12"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">12</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="13"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">13</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="14"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">14</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="15"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">15</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="16"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">16</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="17"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">17</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="18"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">18</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="19"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">19</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="20"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">20</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="21"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">21</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="22"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">22</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="23"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">23</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="24"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">24</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="25"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">25</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="26"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">26</s:a>
			</th>
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="27"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">27</s:a>
			</th>
			<s:if test="maxDay >= 28">
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="28"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">28</s:a>
			</th>
			</s:if>
			<s:if test="maxDay >= 29">
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="29"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">29</s:a>
			</th>
			</s:if>
			<s:if test="maxDay >= 30">
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="30"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">30</s:a>
			</th>
			</s:if>
			<s:if test="maxDay >= 31">
			<th>
				<s:url action="piquet_" method="selectForDay" namespace="/garde" id="urlPiquet">
					<s:param name="dayOfMonth" value="31"></s:param>
				</s:url>
				<s:a  href="%{urlPiquet}">31</s:a>
			</th>
			</s:if>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="lstPlanning" status="planningStatus">
			<tr><td><span class="matricule" style="display:none;"><s:property value="agent.idfAgent"/></span><s:property value="agent.nom"/> <s:property value="agent.prenom"/></td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_1'><s:property value="month[0].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_2'><s:property value="month[1].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_3'><s:property value="month[2].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_4'><s:property value="month[3].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_5'><s:property value="month[4].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_6'><s:property value="month[5].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_7'><s:property value="month[6].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_8'><s:property value="month[7].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_9'><s:property value="month[8].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_10'><s:property value="month[9].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_11'><s:property value="month[10].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_12'><s:property value="month[11].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_13'><s:property value="month[12].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_14'><s:property value="month[13].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_15'><s:property value="month[14].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_16'><s:property value="month[15].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_17'><s:property value="month[16].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_18'><s:property value="month[17].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_19'><s:property value="month[18].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_20'><s:property value="month[19].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_21'><s:property value="month[20].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_22'><s:property value="month[21].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_23'><s:property value="month[22].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_24'><s:property value="month[23].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_25'><s:property value="month[24].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_26'><s:property value="month[25].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_27'><s:property value="month[26].statut.code"/> </td>
			<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_28'><s:property value="month[27].statut.code"/> </td>
			<s:if test="month.size > 28">
				<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_29'><s:property value="month[28].statut.code"/> </td>
			</s:if>
			<s:if test="month.size > 29">
				<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_30'><s:property value="month[29].statut.code"/> </td>
			</s:if>
			<s:if test="month.size > 30">
				<td class="jour_" 
						id='div_<s:property value="agent.idfAgent"/>_31'><s:property value="month[30].statut.code"/> </td>
			</s:if>
			</tr>
		</s:iterator>	
	</tbody>
	<tfoot>	
		<s:iterator value="lstPlage">
			<tr><td><s:property value="libelle"/> </td>
			<th id='<s:property value="idfPlage"/>_1'></th>
			<th id='<s:property value="idfPlage"/>_2'></th>
			<th id='<s:property value="idfPlage"/>_3'></th>
			<th id='<s:property value="idfPlage"/>_4'></th>
			<th id='<s:property value="idfPlage"/>_5'></th>
			<th id='<s:property value="idfPlage"/>_6'></th>
			<th id='<s:property value="idfPlage"/>_7'></th>
			<th id='<s:property value="idfPlage"/>_8'></th>
			<th id='<s:property value="idfPlage"/>_9'></th>
			<th id='<s:property value="idfPlage"/>_10'></th>
			<th id='<s:property value="idfPlage"/>_11'></th>
			<th id='<s:property value="idfPlage"/>_12'></th>
			<th id='<s:property value="idfPlage"/>_13'></th>
			<th id='<s:property value="idfPlage"/>_14'></th>
			<th id='<s:property value="idfPlage"/>_15'></th>
			<th id='<s:property value="idfPlage"/>_16'></th>
			<th id='<s:property value="idfPlage"/>_17'></th>
			<th id='<s:property value="idfPlage"/>_18'></th>
			<th id='<s:property value="idfPlage"/>_19'></th>
			<th id='<s:property value="idfPlage"/>_20'></th>
			<th id='<s:property value="idfPlage"/>_21'></th>
			<th id='<s:property value="idfPlage"/>_22'></th>
			<th id='<s:property value="idfPlage"/>_23'></th>
			<th id='<s:property value="idfPlage"/>_24'></th>
			<th id='<s:property value="idfPlage"/>_25'></th>
			<th id='<s:property value="idfPlage"/>_26'></th>
			<th id='<s:property value="idfPlage"/>_27'></th>
			<s:if test="maxDay >= 28">
				<th id='<s:property value="idfPlage"/>_28'></th>
			</s:if>
			<s:if test="maxDay >= 29">
				<th id='<s:property value="idfPlage"/>_29'></th>
			</s:if>
			<s:if test="maxDay >= 30">
				<th id='<s:property value="idfPlage"/>_30'></th>
			</s:if>
			<s:if test="maxDay >= 31">
				<th id='<s:property value="idfPlage"/>_31'></th>
			</s:if>
			</tr>
		</s:iterator>
	</tfoot>
	</table>
