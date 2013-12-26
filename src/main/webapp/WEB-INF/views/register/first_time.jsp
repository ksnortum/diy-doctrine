<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/base.css' />">
<title>Register for DIY Doctrine</title>
</head>
<body class="center">

<h2>Sign up for DIY Doctrine</h2>

<sf:form method="POST" modelAttribute="editor" action="update">
	<fieldset>
		<table cellspacing="0">
			<tr>
				<th><label for="username">Username:</label></th>
				<td><sf:input path="username" size="30" id="username"/></td>
			</tr>
			
			<tr>
				<th><label for="user_password">Password:</label></th>
				<td><sf:password path="password" size="30" showPassword="false"
					id="user_password"/>
			</td>
			</tr>
		</table>
	</fieldset>
	<input type="submit" value="Submit Registration" />
</sf:form>
</body>
</html>