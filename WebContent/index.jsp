<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="master.css">
<link href="https://fonts.googleapis.com/css?family=Karla|PT+Sans"
	rel="stylesheet">
<title>Your Concert Events</title>
</head>


<body>

<nav>
<ul>
	<li><a href="index.jsp">Home</a></li>
	<li><a href="yourConcertsPage.jsp">Your shows</a></li>
	<li><a href="getAllShows.do">All shows</a></li>
	<li><a href="createUser.do">Log in/Sign up</a></li>
	<li><form action="GetConcertData.do" method="get">
			Look up artist: <input type="text" name="performer" /> <input
				type="submit" name="LookUp" value="Search" />
		</form></li>
</ul>
</nav>

<h2>Keep track of upcoming concerts</h2>

	<div id="left">
		<h3>Look up an artist:</h3>
	<form action="GetConcertData.do" method="get">
		<br> <input type="text" name="performer" /> <input type="submit"
			name="LookUp" value="Look up" />
	</form>
	</div>

	<div id="middle">
	<h3>Get all of your shows:</h3>
	<form action="GetConcertData.do" method="get">
		<input type="submit" name="GetConcertList" value="Submit" />
	</form>

	<h3>Get upcoming shows:</h3>
	<form action="getAllShows.do" method="get">
		<input type="submit" name="GetAll" value="Submit" />
	</form>
	</div>
	
	<h3>Add your own concert:</h3>
	<form action="createConcert.do" method="post">
		Artist: <input type="text" name="performer" /><br> 
		Venue: <input type="text" name="venue" /><br> 
		Date: <input type="text" name="date" /><br> 
		<input type="submit" name="AddYourEvent" value="Add Event" />
	</form>

</body>
</html>