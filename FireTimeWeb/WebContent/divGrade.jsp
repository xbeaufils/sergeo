<%@ page language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:url action="grade_show" namespace="/load" id="showGradeUrl"></s:url>
<s:url action="grade_edit" namespace="/load" id="editGradeUrl"></s:url>
<sjg:grid 
	gridModel="gradeModel" 
	id="gradeGrid" 
	href="%{showGradeUrl}" 
	cellEdit="true"
	editinline="true"
	editurl="%{editGradeUrl}" 
	altRows="true" 
	cellurl="%{editGradeUrl}" >
	<sjg:gridColumn name="id" key="true" title="identifiant"/>
	<sjg:gridColumn name="libelle" title="Appellation"></sjg:gridColumn>
	<sjg:gridColumn name="filiere" title="Filiere"></sjg:gridColumn>
	<sjg:gridColumn name="code" editable="true" title="Code" edittype="text"></sjg:gridColumn>
</sjg:grid>
