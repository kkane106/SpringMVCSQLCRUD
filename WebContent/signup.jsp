<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="master.css">
<link href="https://fonts.googleapis.com/css?family=Karla|PT+Sans"
	rel="stylesheet">
<title>Sign up</title>
</head>
<body>

<!-- This will dynamically include a reusable partial -->
<jsp:include page="partials/_appNav.jsp" />

	<form:form action="createUser.do" method="POST" modelAttribute="user">

		<form:label path="fname">First name:</form:label>
		<form:input path="fname" />
		<form:errors path="fname" />
		<br />
		<form:label path="lname">Last name:</form:label>
		<form:input path="lname" />
		<form:errors path="lname" />
		<br />
		<form:label path="username">Username:</form:label>
		<form:input path="username" />
		<form:errors path="username" />
		<br />
		<form:label path="password">Password:</form:label>
		<form:input path="password" />
		<form:errors path="password" />
		<br />
		<input type="submit" value="Sign up" />

	</form:form>
</body>
</html>