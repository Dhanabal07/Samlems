package ems;


import java.sql.*;
import java.util.*;


public class DBConnection
{
	static Connection con=null;
	static
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres","postgres","postgres");
			System.out.println("Connection is created");
		}catch(SQLException e)
		{
			System.out.println(e);
		}catch(Exception e1)
		{
			System.out.println(e1);
		}
	 
	}

}