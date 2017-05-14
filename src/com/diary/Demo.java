package com.diary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diary.util.DBUtil;
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String date=null;
		String subject=null;
		String text=null;
		
		try
		{
			con=DBUtil.getCon();
			
			ps=con.prepareStatement("select * from Diary");
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				date=rs.getString(2);
				subject=rs.getString(3);
				text=rs.getString(4);
				System.out.println("date: "+date+" subject: "+subject+" text: "+text);
			}
			else{
				System.out.println("Database Problem...");
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
		DBUtil.close(con);
		}
		
	}

}
