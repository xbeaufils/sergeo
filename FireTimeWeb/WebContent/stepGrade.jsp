<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<script type="text/javascript">
	$('#linkSuivant').click(function() {
	  	$('#tabEtape2').removeClass('tabUnselected');
	  	$('#tabEtape2').addClass('tabSelected');
	  	$('#tabEtape1').removeClass('tabSelected');
	  	$('#tabEtape1').addClass('tabUnselected');
	  	
	});
</script>
<div style="float: left;">
	<jsp:include page="/divGrade.jsp"></jsp:include>
</div>
<div class="box_shadow" style="width: 400px;">
	<p>Saisissez ici les identifiants (codes) des <b>grades</b> qui correspondent à ceux de votre base de données.</p>
	<p></p>
</div>
