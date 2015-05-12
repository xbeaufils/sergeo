<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<s:form action="trans" method="post" namespace="/load" theme="simple">
		<s:hidden name="selectedTypeImport" value="%{selectedTypeImport}"></s:hidden>
		<s:hidden name="separateurChamp" value="%{separateurChamp}"></s:hidden>
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
		<s:submit cssClass="button" key="send"/>
	</s:form>
