<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/base.css' />">
<title>Doctrine Home Page</title>
</head>
<body class="center">

<h1>DIY Doctrine</h1>

<p>
Welcome to DIY Doctrine, where you can make associations between the Bible
verses you read and the doctrines (or ideas) they support.  As you read the
Scriptures, think about what the verses mean and how they fit into the 
doctrines or principles you know.  Setup the doctrine and copy/paste the 
verse in.  You can then get reports on the verses that support a doctrine or
the doctrines supported by a verse. 
</p>

<p>
Start by logging in, or if this is your first time, register.  It's free and
easy.
</p>

<ul>
	<li><a href="<c:url value='/login/first_time' />">Login</a></li>
	<li><a href="<c:url value='/register/first_time' />">Register</a></li>
</ul>

</body>
</html>