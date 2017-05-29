<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="master.css">
<link href="https://fonts.googleapis.com/css?family=Karla|PT+Sans"
	rel="stylesheet">
<title>Your Concert Events</title>
</head>

<nav>
<ul>
	<li><a href="index.jsp">Home</a></li>
	<li><a href="getYourShows.do">Your shows</a></li>
	<li><a href="getAllShows.do" method="get">All shows</a></li>
	<c:if test="${sessionScope.user.username == null }">
	<li><a href="loginForm.do">Sign in</a></li>
	</c:if>
	<c:if test="${sessionScope.user.username != null }">
	<li><a href="logout.do">Logout</a></li>
	</c:if>
	<li><form action="GetConcertData.do" method="get">
			Look up artist: <input type="text" name="performer" /> <input
				type="submit" name="LookUp" value="Search" />
		</form></li>
</ul>
</nav>

<body>

	<h3>${concert.performer}</h3>
	Playing at: ${concert.venue}
	<br> on: ${concert.date}
	<br>
	<br>


	<img src="${concert.imageUrl}" alt="performer photo" />
	<br> Add this show to your concert list:
	<form action="addConcertToList.do" method="post">
		<input type="hidden" name="concertId" value="${concert.id}">
		<input type="submit" name="addThisEvent" value="Add Event" />
	</form>
	<br>
	<br>

	<h3>Get all of your shows:</h3>
	<form action="GetConcertData.do" method="get">
		<input type="submit" name="GetConcertList" value="showAll" />
	</form>

	<h3>Return to index</h3>
	<a href="index.jsp">Return to index</a>

</body>