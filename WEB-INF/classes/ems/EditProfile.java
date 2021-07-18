package ems;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProfile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        
       HttpSession sec=request.getSession();
       String username=(String)sec.getAttribute("username");
       
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        Users e=Display.getEmployeeByUserName(username);
        if(e==null)
        {
        	out.print("NO Profile");
        	return;
        }
        out.print(
            //"<body>"
            "<link rel='stylesheet' type='text/css' href='style.css'>"
            +"<div class='container'>"
            +"<div class='header'>"
            +   "<h2>Update Account</h2>"
            +"</div>"

            +"<form id='form' class='form' action='editSave' method='get' target='_blank'>"
            +   "<div class='form-control'>"
            +       "<label for='username'>Username</label>"
            +       "<input type='text' placeholder='Enter the username' name='username' id='username' value="+e.username+" required readonly/>"
            +   "</div>"


            +   "<div class='form-control'>"
            +       "<label for='id'>EmployeeId</label>"
            +       "<input type='text' placeholder='Enter your id' id='id' value="+e.empid+" name='id' required readonly='readonly'/>"
            +   "</div>"
            +   "<div class='form-control'>"
            +       "<label for='name'>Name</label>"
            +       "<input type='text' placeholder='Enter your name' id='name' value="+e.name+" name='name' required/>"
            +   "</div>");

            

           
               
            out.print("<div class='form-control'>"
            +       "<label for='email'>Email</label>"
            +       "<input type='email' placeholder='abc@gmail.com' name='email'value="+e.mail+" id='email' required/>"
            +   "</div>"

            +   "<div class='form-control'>"
            +       "<label for'mobile'>Mobile Number</label>"
            +       "<input type='text' placeholder='10 digit number' maxlength='10' value="+e.mobile+" name='mobile' id='mobile' required />"
            +   "</div>"

            +   "<div class='form-control'>"
            +       "<label for='department'>Department</label>"
            +       "<input type='text'  placeholder='Enter the department' name='department' value="+e.department+" id='department' required />"
            +   "</div>"
            
			+   "<div class='form-control'>"
			+       "<label for='company'>Company</label>"
			+       "<input type='text'  placeholder='Enter the company' name='company' value="+e.company+" id='company' required />"
			+   "</div>"

            +   "<div >"
            +       "<input class='button' type='submit' name='submit' value='Save'>"
            +   "</div>"
            +"</form>"
        +"</div>");

        
        out.close();
    }
}