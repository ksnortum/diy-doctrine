<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/base.css' />">
<title>Login to DIY Doctrine</title>
</head>

<body>

<h2>Login to DIY Doctrine</h2>

<sf:form method="POST" modelAttribute="editor">
	<fieldset>
		<table>
			<tr>
				<th><label for="username">Username</label></th>
				<td><sf:input path="username" size="20" maxlength="20" 
					id="username"/></td>
				<td><small>No spaces, please</small></td>
				<sf:errors path="username" cssClass="errors" />
			</tr>
			
			<tr>
				<th><label for="user_password">Password</label></th>
				<td><sf:password path="password" size="20" maxlength="20"
				                 showPassword="false" id="user_password"/>
				<td><small>Be Tricky</small></td>
				<sf:errors path="password" cssClass="errors" />
			</tr>
		</table>
	</fieldset>
</sf:form>

<input type="submit" value="Submit" />

</body>
</html>
