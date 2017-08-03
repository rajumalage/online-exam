package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.ShowStatusOnStatusBar;
import com.lara.oes.entity.ShowStatusOnDash;

/**
 * @author Sourav Barman
 *
 */
public class SetAdminDashboard extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetAdminDashboard() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=getConnection();
		ShowStatusOnDash show=ShowStatusOnStatusBar.showStatus(con);
		request.setAttribute("showStatusInDetail", show);
		response.setContentType("text/html");
		request.getRequestDispatcher("admin/AdminDashboard.jsp").include(request, response);
	}

}
