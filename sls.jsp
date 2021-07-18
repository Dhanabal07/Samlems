<%@page import="opensamlems.*"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="utf-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">
	 <title>Logout page</title>
	 <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"> 

</head>
	<div class="container">
    		
	<%
		out.println(" LOgout response");
		String responses=request.getParameter("SAMLResponse");
		out.print(responses);
		MainClass main=new MainClass();
	    String result=main.handleLogoutResponce(responses);
	    out.print(result);
	%>
	</div>
</body>
</html>
