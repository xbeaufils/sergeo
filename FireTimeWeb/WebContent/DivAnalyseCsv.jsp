<%@ page language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<s:form id="frmLoad" action="trans" method="post" namespace="/load" theme="simple">
		<s:hidden name="separateurChamp"></s:hidden>
		<s:hidden name="selectedTypeImport"></s:hidden>
		<table class="moduletable">
			<thead>
			<tr>
				<s:iterator value="#session.entete" id="enteteCol" status="enteteStatus">
				<th>
				<s:property  value="#enteteCol"/>
					<s:select list="selectedTypeImport.xmlAttributes" 
					name="colTypeImport[%{#enteteStatus.index}]"
					headerKey="notSelected"
	    			headerValue="-- Non pris en compte --"></s:select> 
				</th>
			</s:iterator>
			</tr>
			</thead>
			<s:iterator value="#session.dataSample" id="row">
				<tr>
				<s:iterator value="#row" id="data">
					<td><s:property value="#data"/></td>
				</s:iterator>
				</tr>
			</s:iterator>
		</table>
	</s:form>
