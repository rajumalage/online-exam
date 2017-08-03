package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_login_dao;
import com.lara.oes.entity.Admin;

/**
 * @author Sourav Barman
 *
 */
public class AdminLogin extends OesHttpServlet  {
	private static final long serialVersionUID = 1L;
       
    
    public AdminLogin() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String s1 = request.getParameter("AuserName");
		String s2 = request.getParameter("ApassWord");
		Connection con = getConnection();
		Admin log = new Admin();
		log.setUsername(s1);
		log.setPassword(s2);
		int systus = admin_login_dao.readLogin(log,con);		
		if(systus == 1)
		{
			response.sendRedirect("SetAdminDashboard.htm");
		}
		else
		{
			response.sendRedirect("login/admin.jsp");
		}
	}

}
