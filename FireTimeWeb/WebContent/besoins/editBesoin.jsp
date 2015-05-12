<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:form action="liste" namespace="/besoin" theme="simple">
	<div class="ui-widget-header ui-corner-all">Plage horaire</div>
	<table class ="formulaire">
		<tr>
		<td>Libellé :</td>
		<td colspan="3">			
			<s:property value="plage.libelle"/>
		</td></tr>
	 	<tr><td>Début :</td>
		<td>
			<s:date name="plage.debut" format="HH:mm"/>
		</td>
		<td>Fin :</td>
		<td>
			<s:date name="plage.fin" format="HH:mm"/>
		</td>
	   </tr>
	</table>
	<s:url action="besoin_" method="view" namespace="/besoin/json" id="urlViewBesoin"  >
		<s:param name="idfPlage" value="%{idfPlage}" />
	</s:url>
	<sjg:grid gridModel="lstBesoin" 
			id="equipageGrid" 
			href="%{#urlViewBesoin}"  
			altRows="true"  
			autowidth="true"
			pager="true" 
			navigator="true"				
			navigatorCloneToTop="false"
			name="equipageGridName" 
			prmNames="{search: null,id:'idfBesoin', nd: null}"
       		resizable="true" 
       		navigatorEdit="false"
       		navigatorAdd="false"
       		caption="Equipages" 
       		listenTopics="closeBesoins" >
			<sjg:gridColumn name="idfBesoin" 
							key="true" 
							title="Id besoin" 
							hidden="true" 
							formatter="integer"/>
		<sjg:gridColumn name="libelle" 
						title="Libelle"  />
       	<sjg:gridColumn name="equipage.libelle" 
       					title="Equipage" />
        </sjg:grid>
 		 <s:url id="urlAddBesoinEquipage"  action="add"  namespace="/besoin">
		<s:param name="idfPlage" value="%{idfPlage}" />
	 </s:url>
	 <sj:dialog id="dlgBesoin" href="%{urlAddBesoinEquipage}" title="Besoins en équipage" autoOpen="false" closeTopics="closeBesoins" width="640" height="220" modal="true" ></sj:dialog>
        <sj:a 
   		openDialog="dlgBesoin" 
   			button="true"
   			buttonIcon="ui-icon-newwin">
   			Ajouter un équipage
   	</sj:a>
</s:form>
