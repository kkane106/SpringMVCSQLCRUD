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