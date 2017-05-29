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

<%-- <c:if test="${sessionScope.user.username} != null"> 
 --%><h1>Welcome, ${sessionScope.user.fname} ${sessionScope.user.lname}</h1>
<%--  </c:if>
 --%>
<%-- <c:if test="${sessionScope.concertList}==null"> --%>
<%-- <h4>You do not have any saved concerts!</h4>
</c:if> --%>

<%-- <c:if test="${sessionScope.concertList} s!= null"> --%>
<h2>Your saved concerts</h2>

	<table>
		<tr>
			<th>Artist</th>
			<th>Venue</th>
			<th>Date</th>
			<th>Band</th>
		</tr>

		<c:forEach var="c" items="${sessionScope.concertList}">
			<tr>
				<td>${c.performer}</td>
				<td>${c.venue}</td>
				<td>${c.date}</td>
				<td><img src="${c.imageUrl}" alt="performer photo" /></td>
			</tr>
		</c:forEach>
	</table>

	<h3>Remove a show</h3>
	<form action="removeConcert.do" method="post">
		<select name="concertId">
			<c:forEach var="c" items="${sessionScope.concertList}">
				<option value="${c.id}">${c.performer}</option>
			</c:forEach>
		</select> 
		<input type="submit" value="submit" />
	</form>
<%-- 	</c:if> --%>
	<br>


	<h3>Return to index</h3>
	<a href="index.jsp">Return to index</a>
</body>