<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="master.css">
<link href="https://fonts.googleapis.com/css?family=Karla|PT+Sans"
	rel="stylesheet">
<title>Your Concert Events</title>
<link rel="stylesheet" href="master.css">
</head>


<body>

<!-- This will dynamically include a reusable partial -->
<jsp:include page="partials/_appNav.jsp" />

<form action="updateConcert.do" method="post">
 	Date:<br><input type="text" value="${concert.date}" name="date"><br><br>
	<input type="hidden" name="concertId" value="${concert.id}">
	<br><input type="submit" value="Submit">
</form>

</body>
</html>