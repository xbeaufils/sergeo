<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<sj:head locale="fr" jqueryui="true" jquerytheme="blitzer" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Inscription</title>
		<script type="text/javascript" src="/js/jquery.horizontalnav.js"></script>
		<script type="text/javascript" src="/js/jquery.hoverIntent.minified.js"></script>
		<!--[if lte IE 8]> 
		<script type="text/javascript" src="script/roundies.js"> 
		</script><![endif]-->
		<link href="<%=request.getContextPath()%>/css/shadowbox.css" type="text/css" rel="stylesheet"/>
		<style type="text/css">
			.label {
				color: fireBrick;
			}
		</style>
	</head>
<body style="color: #000000;">
	<div class="box_shadow" style="width: 300px;">
		<h1>Bienvenue sur</h1>
		<img alt="" src="images/logosergeo.jpg">
		<p>Le logiciel de gestion des plannings des gardes des Sapeurs-Pompiers</p>
		<ul style="font-size: 0.8em;">
		    <li>Gestion du planning mensuel</li>
		    <li>Gestion de la feuille de garde journalière</li>
		    <li>Affectation des piquets par un algorithme génétique (Intelligence Artificielle)</li>
		    <li>Récapitulatif des heures par agent et par an</li>
		    <li>Autres fonctionnalités sur demande en fonction de leur complexité</li>
		</ul>
	</div>
	<div class="box_shadow" style="max-width: 600px; margin-left: 30px;">
	<p>L'inscription à sergeo permettra de vous envoyer un certificat numérique personnalisé 
	qui vous identifiera pour la gestion des plannings de votre centre.</p>
	<center><img alt="" src="images/monthlyPlan.jpg"></center>
	<p><span style="font-weight: bold;">Le premier mois est offert</span> 
	afin de vous laisser le temps d'alimenter les données nécessaires ensuite le tarif est de
	<span style="font-weight: bold;"> 20€ </span>/ mois / centre</p> 
	</div>
	<div class="box_shadow" style="max-width: 600px; margin-left: 30px;">
	<s:form action="inscription" namespace="/">
		<s:textfield label="Nom de l'organisation (SDIS)" name="organisationName"></s:textfield>
		<s:textfield label="Nom du centre" name="centre"></s:textfield>
		<s:textfield label="Adresse" name="adresse" ></s:textfield>
		<s:textfield label="Adresse complémentaire" name="adresseComplementaire"></s:textfield>
		<s:textfield label="Code postal" name="codePostal"></s:textfield>
		<s:textfield label="Commune" name="ville"></s:textfield>
		<s:textfield label="Email de contact" name="email"></s:textfield>
		<s:submit key="register"></s:submit>
	</s:form>
	</div>
</body>
</html>