
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>admin</title>
	<link rel="stylesheet" type="text/css" href="style1.css">
</head>
<body>
	<div class="container">
	<%
		String username = (String) session.getAttribute("username");
		if(username==null)
		{
			response.sendRedirect(request.getContextPath()+"/login.html");
		}
	%>
	<h1 style='color:green' >Hi <%out.print(username);%>,Welcome to EMS </h1>
	
	</div>
 	<div class="container">
	<div class="header">
		<h2>Operation List</h2>
	</div>
	<form id="form" class="form" action="editProfile" method="get" target="_self">
		<div >
			<input class="button"type="submit" name="submit" value="Edit Profile">
		</div>
	</form>
	<form id="form" class="form" action="display" method="get" target="_self">
		<div >
			<input class="button"type="submit" name="submit" value="Display Records">
		</div>
	</form>
	<a href="Searching.jsp" id="form" class="form" style="text-decoration: none">
		<div class="urlButton">
			Searching Records
		</div>
	</a>
	
	<a href="Sorting.jsp" id="form" class="form" style="text-decoration: none">
		<div class="urlButton">
			Sorting Records
		</div>
	</a>
	<a href="dologout.jsp" id="form" class="form" style="text-decoration: none">
		<div class="urlButton">
			Logout
		</div>
	</a>
	
	

</div>
</div>
 	
</body>
