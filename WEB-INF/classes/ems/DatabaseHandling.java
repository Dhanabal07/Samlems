package ems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHandling {

	public static String login(String username, String password) {
		try
		{
			Connection con=DBConnection.con;
			PreparedStatement ps=con.prepareStatement("select * from userlogin where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return username;
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

}
