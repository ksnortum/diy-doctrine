<%@ page language="java" 
         contentType="text/html; charset=ISO-8859-1" 
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                      "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" 
	      href="<c:url value='/resources/css/base.css' />">
	<link rel="stylesheet" type="text/css" 
	      href="<c:url value='/resources/css/menu.css' />">
	<link rel="stylesheet" type="text/css" 
	      href="<c:url value='/resources/css/header.css' />">
	<link rel="stylesheet" type="text/css" 
	      href="<c:url value='/resources/css/footer.css' />">
	<title>DIY Doctrine</title>
</head>

<body class="center">
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div id="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	
	<div id="body">
		<tiles:insertAttribute name="body" />
	</div>
	
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>

</html>