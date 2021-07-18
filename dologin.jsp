<%@page import="opensamlems.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<%
		MainClass main=new MainClass();
		System.out.println("I am in dologin");
	    main.login(response);
	%>
</body>
</html>
