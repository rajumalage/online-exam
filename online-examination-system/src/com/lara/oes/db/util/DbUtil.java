package com.lara.oes.db.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DbUtil 
{
	public static ResultSet execute(String s,Connection con)
	{
		Statement stmt = null;
		ResultSet rs = null;
		String sql=s;
		try 
		{
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		
		return rs;

	}
	public static ResultSet execute(String s)
	{
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql=s;
		try 
		{
			con=getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		
		return rs;

	}
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","password");

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return con;

	}
	public static void closeStmt(Statement stmt)
	{
		try
		{
			if(stmt != null)
			{
				stmt.close();
				stmt = null;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	public static void closeAll(ResultSet rs, Statement stmt, Connection con)
	{
		try
		{
			if(rs != null)
			{
				rs.close();
				rs = null;
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		try
		{
			if(stmt != null)
			{
				stmt.close();
				stmt = null;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		try
		{
			if(con != null)
			{
				con.close();
				con = null;
			}
		}
		catch(SQLException ex)
		{
		ex.printStackTrace();
		}
	}
}
