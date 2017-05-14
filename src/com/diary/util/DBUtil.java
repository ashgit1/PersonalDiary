package com.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil 
{
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/diary";
	private static String username="root";
	private static String password="Root@123";
	
	static
	{
		try {
			Class.forName(driver);
			System.out.println("Driver Loaded...");
		} 
		catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		
				
	}
	public static Connection getCon()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection establish...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	public static void close(Connection con)
	{
		if(con!=null)
		{
			try 
			{
				con.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
	}


}
