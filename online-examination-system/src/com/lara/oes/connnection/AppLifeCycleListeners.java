package com.lara.oes.connnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AppLifeCycleListeners implements ServletContextListener {

    public AppLifeCycleListeners() {

    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	 
		ServletContext context =arg0.getServletContext();
		ConnectionPool cp=(ConnectionPool) context.getAttribute("pool");
		context.removeAttribute("pool");
		cp.release();	
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
  
    	ServletContext context = arg0.getServletContext();
    	String driverClass = context.getInitParameter("driverClass");
    	String url = context.getInitParameter("url");
		String username = context.getInitParameter("username");
		String password= context.getInitParameter("password");
		String size= context.getInitParameter("size");
		ConnectionPool cp = new ConnectionPool(driverClass,url,username,password,Integer.parseInt(size));
		cp.init();
		context.setAttribute("pool", cp);
    }
	
}
