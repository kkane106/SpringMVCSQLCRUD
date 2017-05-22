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

<nav>
<ul>
	<li><a href="index.jsp">Home</a></li>
	<li><a href="yourConcertsPage.jsp">Your shows</a></li>
		<li><a href="getAllShows.do">All shows</a></li>
		<li><form action="GetConcertData.do" method="get">
			Look up artist: <input type="text" name="performer" /> <input
				type="submit" name="LookUp" value="Search" />
		</form></li>
</ul>
</nav>

<h2>Your saved concerts</h2>
	<c:if test="${sessionScope.concertList} != null">
You do not have any saved concerts!
</c:if>

	<table>
		<tr>
			<th>Artist</th>
			<th>Venue</th>
			<th>Date</th>
			<th></th>
		</tr>

		<c:forEach var="c" items="${sessionScope.concertList}">
			<tr>
				<td>${c.performer}
				</td>
				<td>${c.venue}</td>
				<td>${c.date}
				</td>
				<td><img src=${c.imageUrl } alt="band photo" /></td>
			</tr>
		</c:forEach>
	</table>

	<h3>Remove a show</h3>
	<form action="removeConcert.do" method="get">
		<select name="performer">
			<c:forEach var="c" items="${sessionScope.concertList}">
				<option>${c.performer}</option>
			</c:forEach>
		</select> <input type="submit" value="submit" />
	</form>
	<br>

	<h3>Save concerts to a file:</h3>
	<form action="saveConcerts.do" method="get">
		<input type="submit" value="Save" />
	</form>
	<br>

	<h3>Return to index</h3>
	<a href="index.jsp">Return to index</a>
</body>