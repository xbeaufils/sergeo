<%@ taglib uri="/struts-tags" prefix="s" %>
<ul id="topnav">
    <li>
    	<a href="#">Parametres</a>
        <!--Subnav Starts Here-->
        <span>
			<s:url action="list_" method="makeListAgent" namespace="/effectif" id="viewAgents"></s:url>
			<s:a href="%{viewAgents}" >Agents</s:a> |
			<s:url action="view" namespace="/periode" id="viewHoraire"  ></s:url>
			<s:a href="%{viewHoraire}" >Periodes de validité</s:a> |
			<s:url action="view" namespace="/agres" id="viewEquipage"></s:url>
			<s:a href="%{viewEquipage}" >Equipage</s:a>			
        </span>
    </li>
    <li>
    	<a href="#">Configuration</a>
    	<span>
    		<s:url action="" id="viewGrade" ></s:url>
 			<a href="<%=request.getContextPath()%>/grade.jsp">Grades</a> |
			<a href="<%=request.getContextPath()%>/emploiOperationnel.jsp">Emplois opérationnels</a> |
			<s:url action="viewUpload" namespace="/load" id="viewUpload"></s:url>
			<!--   s:a href="%{viewUpload}" >Chargement s:a-->
			<a href="<%=request.getContextPath()%>/configuration.jsp">Chargement</a> 	
		</span>
    </li>
    <li>
    	<a href="#">Planning</a>
    	<span>
			<s:url action="mensuel_" method="viewMonth" namespace="/planning" id="selectMonth" includeParams="none"></s:url>
			<s:a href="%{selectMonth}">Agents</s:a>
			<s:url action="besoin_" method="initMonth" namespace="/planning" id="selectBesoinMonth" includeParams="none"></s:url>
			<s:a href="%{selectBesoinMonth}">Besoins</s:a>
	 	</span>
    </li>
 </ul>
<s:actionmessage/>
	<div style="clear: both; height: 30px;"></div>
