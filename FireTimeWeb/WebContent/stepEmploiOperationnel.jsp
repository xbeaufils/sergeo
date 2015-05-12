<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<script type="text/javascript">
	$('#linkPrecedent').click(function() {
	  	$('#tabEtape1').removeClass('tabUnselected');
	  	$('#tabEtape1').addClass('tabSelected');
	  	$('#tabEtape2').removeClass('tabSelected');
	  	$('#tabEtape2').addClass('tabUnselected');
	  	
	});
	$('#linkSuivant').click(function() {
	  	$('#tabEtape3').removeClass('tabUnselected');
	  	$('#tabEtape3').addClass('tabSelected');
	  	$('#tabEtape2').removeClass('tabSelected');
	  	$('#tabEtape2').addClass('tabUnselected');
	  	
	});
</script>
<div style="float: left;">
	<jsp:include page="/divEmploiOperationnel.jsp"></jsp:include>
</div>
<div class="box_shadow" style="width: 400px;">
	<p>Saisissez ici les identifiants (codes) des <b>emplois opérationnels</b> qui correspondent à ceux de votre base de données.</p>
	<p></p>
</div>
