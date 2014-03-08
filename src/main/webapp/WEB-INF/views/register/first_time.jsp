<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>Sign up for DIY Doctrine</h2>

<sf:form method="POST" modelAttribute="editor" action="update">
	<fieldset>
		<table>
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
