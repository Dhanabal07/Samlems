package ems;


import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.io.IOException;  
import java.io.PrintWriter; 

public class SearchingRecords extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException 
    {  

        Connection con=DBConnection.con;
      
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
       
        String column_name=request.getParameter("column_name");
        
        String type=request.getParameter("search_option_string");
        String value=request.getParameter("string_value");
        searchByString(column_name,type,value,con,out);
        
              
        out.close();  
    }  
    void searchByString(String column_name,String type,String value,Connection con,PrintWriter out)
    {
      
        try{
         PreparedStatement ps=null;
         if(type.equals("contains")) 
         {
             ps=con.prepareStatement("select * from employeedetails where "+column_name+" ilike '%"+value+"%' ");
         } 
         else if(type.equals("equal"))
         {
             ps=con.prepareStatement("select * from employeedetails where "+column_name+" ='"+value+"' ");
            
         }
         else if(type.equals("notContains"))
         {
             ps=con.prepareStatement("select * from employeedetails where "+column_name+" not ilike '%"+value+"%' ");
            
         }
         else if(type.equals("startswith"))
         {
             ps=con.prepareStatement("select * from employeedetails where "+column_name+" ilike '"+value+"%' " );
         }
         else
         {
             ps=con.prepareStatement("select * from employeedetails where "+column_name+" ilike '%"+value+"' ");
         } 
        
        ResultSet rs=ps.executeQuery();
        new Display().displayResultSet(rs,out);
        }catch(Exception e)
        {
            out.print(e);
        }
    }
    
}