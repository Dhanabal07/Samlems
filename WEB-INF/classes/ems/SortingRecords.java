package ems;


import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.io.IOException;  
import java.io.PrintWriter; 

public class SortingRecords extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException 
    {  

        Connection con=DBConnection.con;
      
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
       
        String column_name=request.getParameter("column_name");
        String order=request.getParameter("order");
        
        sorting(column_name,order,con,out);      
        out.close();  
    }  
   
    void sorting(String column_name,String order,Connection con,PrintWriter out)
    {
        try{
         PreparedStatement ps=null;
         if(order.equals("desc")) 
         {
             ps=con.prepareStatement("select * from employeedetails order by "+column_name+" Desc");
         } 
         else 
         {
            ps=con.prepareStatement("select * from employeedetails order by "+column_name);
            
         }
        
        ResultSet rs=ps.executeQuery();
        new Display().displayResultSet(rs,out);
        }catch(Exception e)
        {
            out.print(e);
        }
    }
}