<%@ page language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:url action="emploi_show" namespace="/load" id="showEmploiUrl"></s:url>
<s:url action="emploi_edit" namespace="/load" id="editEmploiUrl"></s:url>
<sjg:grid gridModel="emploiOpeModel" 
			id="emploiOpeGrid" 
			href="%{#showEmploiUrl}" 
			cellEdit="true" 
			cellurl="%{#editEmploiUrl}" 
			prmNames="{search: null,id:'idEmploi', nd: null, rowid: 'idfCycle'}"
			altRows="true" >
	<sjg:gridColumn name="idEmploi" key="true" title="identifiant" hidden="true" />
	<sjg:gridColumn name="libelle" title="Appellation" width="300"></sjg:gridColumn>
	<sjg:gridColumn name="code" editable="true" title="Code" ></sjg:gridColumn>
</sjg:grid>
