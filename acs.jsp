<%@page import="opensamlems.*"%>
<%@page import="ems.*"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%> 
<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta charset="utf-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
     <meta name="viewport" content="width=device-width, initial-scale=1">
	 <title>ACS page</title>
</head>
<body>
	<div class="container">
	<%
		String str=request.getParameter("SAMLResponse");
		//out.print(str);
		
		MainClass main=new MainClass();
		String nameId=main.handleResponce(str);
		out.println("MailId : "+nameId);
		session.setAttribute("nameId", nameId);

		
		String username=Display.getEmployeeById(nameId);
		if(username==null)
		{
			out.println("<p> No Details available for logged user");
		}else{
		session.setAttribute("username", username);
		
		response.sendRedirect(request.getContextPath()+"/admin.jsp");
		}
						
		
		%>
	</div>
</body>
</html>
