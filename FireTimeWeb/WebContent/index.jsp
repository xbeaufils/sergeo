<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
	<head>
        <sj:head locale="fr" jqueryui="true" jquerytheme="blitzer" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sergeo</title>
		<link href="<%=request.getContextPath()%>/css/table.css" type="text/css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/css/menu.css" type="text/css" rel="stylesheet"/>
        
        <link href="<%=request.getContextPath()%>/css/topnav.css" type="text/css" rel="stylesheet"/>
        
 		<link href="css/shadowbox.css" rel="stylesheet" type="text/css">
	</head>
	<body style="font-size: 0.8em">
		 <s:action name="welcome" namespace="/config" executeResult="true" ></s:action>
	</body>
</html>
