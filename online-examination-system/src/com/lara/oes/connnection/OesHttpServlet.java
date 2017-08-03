package com.lara.oes.connnection;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class OesHttpServlet extends HttpServlet 
{
	public Connection getConnection()
	{
		ServletContext context=getServletContext();
		ConnectionPool cp=(ConnectionPool) context.getAttribute("pool");
		Connection con=cp.checkOut();
		return con;
	}

	public void setConnection (Connection con)
	{
		ServletContext context=getServletContext();
		ConnectionPool cp=(ConnectionPool) context.getAttribute("pool");
		cp.checkIn(con);
	}
}
