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
<title>Upcoming Concert Events</title>
<link rel="stylesheet" href="master.css">
</head>

<body>

<!-- This will dynamically include a reusable partial -->
<jsp:include page="partials/_appNav.jsp" />

	<h2>Upcoming Shows:</h2>

	<table>
		<tr>
			<th>Artist</th>
			<th>Venue</th>
			<th>Date</th>
			<th>Band</th>
			<c:if test="${sessionScope.user.username != null }">
			<th></th>
			</c:if>
		</tr>

		<c:forEach var="c" items="${concerts}">
			<tr>
				<td>${c.performer}</td>
				<td>${c.venue}</td>
				<td>${c.date}
				<form action="GetConcertData.do" method="get">
						<input type="hidden" name="performer" value="${c.performer}">
						<input type="hidden" name="venue" value="${c.venue}">
						<input type="hidden" name="date" value="${c.date}">
							 <input type="submit" name="LookUp" value="Concert Details">
				</form>
				</td>
				<td><img src="${c.imageUrl}" alt="band photo" /></td>
				<c:if test="${sessionScope.user.username != null }">
				<td style="text-align: center" >
				<!-- <a style="text-decoration: none;" href="addConcertToList.do">Add</a> -->
					<form action="addConcertToList.do" method="post">
						<input type="hidden" name="concertId" value="${c.id}">
						<input type="submit" name="addThisEvent" value="Add Event">
					</form> 
				</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>

</body>
</html>