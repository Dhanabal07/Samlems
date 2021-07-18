package ems;


import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.io.IOException;  
import java.io.PrintWriter; 

public class DisplayRecords extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException 
    {  
      
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        try{
        Connection con=DBConnection.con;

        PreparedStatement ps=con.prepareStatement("select * from employeedetails");
			
		ResultSet rs=ps.executeQuery();

		new Display().displayResultSet(rs,out);
		}catch(Exception e)
		{
			return;
		}
              
        out.close();  
    }  
}