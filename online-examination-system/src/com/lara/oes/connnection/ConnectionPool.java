package com.lara.oes.connnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool
{
	private Vector<Connection> pool;
	private int size;
	private String driverClass;
	private String url;
	private String username;
	private String password;

	public ConnectionPool(String driverClass, String url, String username,
			String password, int size)
	{
		pool = new Vector<Connection>();
		this.size = size;
		this.driverClass = driverClass;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	private Connection getConnection()
	{
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return con;
	}

	private void closeConnection(Connection con)
	{
		try
		{
			if (con != null)
			{
				con.close();
				con = null;
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	public void init()
	{
		try
		{
			Class.forName(driverClass);
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		Connection con = null;
		for (int i = 0; i < size; i++)
		{
			con = getConnection();
			pool.add(con);
		}
	}

	public Connection checkOut()
	{
		Connection con = null;
		if (pool.size() > 0)
		{
			con = pool.remove(0);
		}
		else
		{
			con = getConnection();
		}
		return con;
	}

	public void checkIn(Connection con)
	{
		if (con != null)
		{

			if (pool.size() < size)
			{
				pool.add(con);
			}
			else
			{
				closeConnection(con);
			}
		}
	}

	public void release()
	{
		Connection con = null;
		for (int i = 0; i < pool.size();)
		{
			con = pool.remove(0);
			closeConnection(con);
		}
	}
}