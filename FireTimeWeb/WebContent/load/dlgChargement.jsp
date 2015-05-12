<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form action="fileUpload" method="post" enctype="multipart/form-data" namespace="/load">
	 <s:file name="fileUpload" label="Fichier d'entrée" />
	 <s:radio list="lstImport" listValue="%{getText('TypeImport.'+name())}" 
	 name="typeImport"></s:radio>
	 <s:textfield name="separateurChamp" value="," label="Séparateur de champs" maxlength="1"></s:textfield>					 
     <s:submit cssClass="button" key="load"/>
</s:form>
