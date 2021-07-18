package ems;


import java.util.*;
import java.sql.*;
import java.io.IOException;  
import java.io.PrintWriter;  

public class Display
{
	public static ArrayList<Users> getEmployeeList(ResultSet rs,PrintWriter out)
	{
		ArrayList<Users> list=new ArrayList<>();
		try{
			while(rs.next())
			{
				String name=rs.getString("name");
				String username=rs.getString("username");
				String empid=rs.getString("empid");
				String mail=rs.getString("mail");
				String mobile=rs.getString("mobile");
				String department=rs.getString("department");
				String company=rs.getString("company");
				Users user=new Users(name,username,empid,mobile,mail,department,company);
				
				list.add(user);
			}
		}catch(Exception e)
		{
			out.print(e);
		}
		return list;

	}
	public static String getEmployeeById(String $mail)
	{
		try{
	        Connection con=DBConnection.con;

	        PreparedStatement ps=con.prepareStatement("select * from employeedetails where mail=?");
			ps.setString(1,$mail);	
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String username=rs.getString("username");
				
				return username;
			}
		}
		catch(Exception e){};
		return null;
	}
	
	
	public static ArrayList<Users> getEmployeeList()
	{
		ArrayList<Users> list=new ArrayList<>();
		try{
        Connection con=DBConnection.con;

        PreparedStatement ps=con.prepareStatement("select * from employeedetails ");
			
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			
			String name=rs.getString("name");
			String username=rs.getString("username");
			String empid=rs.getString("empid");
			String mail=rs.getString("mail");
			String mobile=rs.getString("mobile");
			String department=rs.getString("department");
			String company=rs.getString("company");
			Users user=new Users(name,username,empid,mobile,mail,department,company);
			list.add(user);
			
		}
		}catch(Exception e)
		{
	
		}
		return list;

	}
	public void displayResultSet(ResultSet rs,PrintWriter out)
	{
		ArrayList<Users> list=getEmployeeList(rs,out);
		displayList(list,out);
		System.out.println("displayResultSet()");
	}
	public void displayList(ArrayList<Users> list,PrintWriter out)
	{
		out.print("<html>"
		+"<head>"
			+"<title>Display</title>"
			+"<style>"
				+"table{"
					+"border-collapse: collapse;}"
			+"</style>"
		+"</head>"
		+"<body>");
		out.println("<h1 style='text-align:center'>Users List</h1>");        
        out.print("<table border='1' width='80%' style='margin-left:auto;margin-right:auto'>");  
        
        out.print("<tr> <th>S.No</th> <th>Name</th> <th>EmployeeID</th>  <th>Username</th> <th>Mobile</th> <th>Mail</th>  <th>Department</th>  <th>Company</th>  </tr>");  
        int i=1;
        boolean flag=false;
        for(Users e:list)
        {  
        	flag=true;
         out.print("<tr><td style='text-align:center'>"+(i++)+"</td> <td>"+e.name+"</td> <td>"+e.empid+"</td> <td>"+e.username+"</td> <td>"+e.mobile+"</td> <td>"+e.mail+"</td>  <td>"+e.department+"</td> <td>"
         	+e.company+"</td> </tr>");  
        }  
        out.print("</table></body></html>");  
        if(!flag)
        	out.print("No Records");
	}
	public static Users getEmployeeByUserName(String $username) {
		ArrayList<Users> list=new ArrayList<>();
		try{
        Connection con=DBConnection.con;

        PreparedStatement ps=con.prepareStatement("select * from users where username=?");
		ps.setString(1, $username);	
		ResultSet rs=ps.executeQuery();
		
		if(rs.next())
		{
			
			String name=rs.getString("name");
			String username=rs.getString("username");
			String empid=rs.getString("empid");
			String mail=rs.getString("mail");
			String mobile=rs.getString("mobile");
			String department=rs.getString("department");
			String company=rs.getString("company");
			Users user=new Users(name,username,empid,mobile,mail,department,company);
			return user;
			
		}
		}catch(Exception e)
		{
			
		}
		return null;
	}

}