<%@page import="opensamlems.*"%>
<%@page import="ems.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

	<%
	    String logout=(String)session.getAttribute("logouttype");
	    if(logout!=null && logout.equals("normal"))
	    {
	    	session.setAttribute("logouttype","");
	    	session.setAttribute("username","");
	    	session.invalidate();
	    	request.getSession(false);
	    	response.sendRedirect(request.getContextPath()+"/login.html");
	    }
	    else
	    {
	    	session.setAttribute("username","");
	    	String nameId = null;
			if (session.getAttribute("nameId") != null) {
				nameId = session.getAttribute("nameId").toString();
			}
			
			MainClass main=new MainClass();
			main.logout(response,nameId);
	    }
	%>
</body>
</html>
