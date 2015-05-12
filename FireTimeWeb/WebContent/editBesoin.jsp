<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form action="besoin_saveCritere" namespace="/admin" theme="simple">
	<table class ="formulaire">
		<tr><th colspan="4">Besoins</th></tr>
		<tr>
		<td>Libellé :</td>
		<td colspan="3">
			<s:textfield name="libelle" label="Libellé" cssClass="inputbox"></s:textfield>
		</td></tr>
	 	<tr><td>Début :</td>
		<td>
			<div style="float: left; width: 30px; text-align: right;">
			<s:textfield name="heureDebut" id="txtHeureDebut" size="2" cssClass="inputbox"></s:textfield>
			</div>
			<div class="spin">
				<img alt="" src="../images/time-up.png" border="1" onclick="hourUp('txtHeureDebut')"/><br>
				<img alt="" src="../images/time-down.png" border="1" onclick="hourDown('txtHeureDebut')"/>
			</div>
			<div style="float: left; width: 30px; text-align: right;">
			<s:textfield name="minuteDebut" id="txtMinuteDebut" size="2" cssClass="inputbox"></s:textfield>
			</div>
			<div class="spin">
				<img alt="" src="../images/time-up.png" border="1" onclick="minuteUp('txtMinuteDebut')"/><br>
				<img alt="" src="../images/time-down.png" border="1" onclick="minuteDown('txtMinuteDebut')"/>
			</div>
		</td>
		<td>Fin :</td>
		<td>
			<div style="float: left; width: 30px; text-align: right;">
			<s:textfield name="heureFin" id="txtHeureFin" size="2" cssClass="inputbox"></s:textfield>
			</div>
			<div class="spin">
				<img alt="" src="../images/time-up.png" border="1" onclick="hourUp('txtHeureFin')"/><br>
				<img alt="" src="../images/time-down.png" border="1" onclick="hourDown('txtHeureFin')"/>
			</div>
			<div style="float: left; width: 30px; text-align: right;">
			<s:textfield name="minuteFin" id="txtMinuteFin" size="2" cssClass="inputbox"></s:textfield>
			</div>
			<div class="spin">
				<img alt="" src="../images/time-up.png" border="1" onclick="minuteUp('txtMinuteFin')"/><br>
				<img alt="" src="../images/time-down.png" border="1" onclick="minuteDown('txtMinuteFin')"/>
			</div>
		</td>
	   </tr>
	   <tr><td colspan="4" style="border: solid 1px brown;">
		<table class ="formulaire">
			<tr><th colspan="3">Effectif</th></tr>	   	
		   	<s:iterator value="lstBesoin">
		   		<s:if test="! deleted">
				<tr>
					<td><s:url action="besoin_" method="deleteBesoin" namespace="/admin" id="idDelete" includeParams="none">
						<s:param name="idfBesoinDelete" value="idfBesoin"/>
					</s:url>
						<s:a href="%{idDelete}"><img src="../images/edittrash.png" border="0"/></s:a>
					</td>
					<td><s:property value="emploi.libelle"/></td>
					<td><s:property value="nombre"/></td>
				</tr>
				</s:if>			
			</s:iterator>
			<tr><td colspan="4"><center>
	   			<s:submit method="viewAddBesoin" cssClass="button" key="add"></s:submit>
			</center></td></tr>
		</table>
		</td></tr>
	   <tr><td colspan="4" style="border: solid 1px brown;">
		<table class ="formulaire">
			<tr><th colspan="3">Vehicules</th></tr>	   	
		   	<s:iterator value="lstBesoinEquipage">
		   		<s:if test="! deleted">
				<tr>
					<td><s:url action="besoin_" method="deleteBesoinEquipage" namespace="/admin" id="idDelete" includeParams="none">
						<s:param name="idfBesoinDelete" value="idfBesoin"/>
					</s:url>
						<s:a href="%{idDelete}"><img src="../images/edittrash.png" border="0"/></s:a>
					</td>
					<td>
						<s:iterator value="lstTypeVehicule">
							<s:property value="type"/>&nbsp;
						</s:iterator>
					</td>
					<td><s:property value="nombre"/></td>
				</tr>
				</s:if>			
			</s:iterator>
			<tr><td colspan="4"><center>
	   			<s:submit method="viewAddBesoinEquipage" cssClass="button" key="add"></s:submit>
			</center></td></tr>
		</table>
		</td></tr>
	   	<tr><td colspan="4"><center>
	   		<s:submit method="saveCritere" cssClass="button" key="save"></s:submit>
	   		<s:submit method="deleteCritere" cssClass="button" key="delete"></s:submit>
	   		<s:submit method="cancel" cssClass="button" key="cancel"></s:submit>
	   		</center>
	   	</td>
	   	</tr> 
	</table>
</s:form>
