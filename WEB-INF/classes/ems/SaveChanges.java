package ems;
import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class SaveChanges extends HttpServlet {  
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
	   
		try{         
		String username=request.getParameter("username"); 
		
		String id=request.getParameter("id");
		String name=request.getParameter("name");  
		String email=request.getParameter("email"); 
		String mobile=request.getParameter("mobile"); 
		String department=request.getParameter("department");
		String company=request.getParameter("company");
		          
		
		Connection con=DBConnection.con;


		PreparedStatement ps=con.prepareStatement(
			"UPDATE employeedetails set username=? ,name=? , mail=?, mobile=?, department=?, company=? where empid=?");  
		
		ps.setString(1,username);
		ps.setString(2,name); 
		ps.setString(3,email); 
		ps.setString(4,mobile);  
		ps.setString(5,department);  
		ps.setString(6, company);
		ps.setString(7,id);
		
		int i=ps.executeUpdate();  

		  
		if(i>0)  
		   out.print("You are successfully  update the User ...");  
		else
			out.print("update failed...");        
		          
		}catch (Exception e2) {out.print(e2); 
		          out.print("Update failed...");  } 
		out.close();  

	}  
  
}  