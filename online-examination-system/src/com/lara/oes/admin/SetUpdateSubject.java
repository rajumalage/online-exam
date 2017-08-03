package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Subject_dao;
import com.lara.oes.entity.SubjectName;

/**
 * @author Rajib Ranjan
 * @author Sourav Barman
 *
 */
public class SetUpdateSubject extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetUpdateSubject() {
       
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();	
		String subId = request.getParameter("subId");
		if(subId != null)
		{
	       	List<SubjectName> subject = admin_Subject_dao.readAllSubjectWSubId(subId,con);
			request.setAttribute("subNameDesc", subject);
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("admin/subject/setUpdateSubject.jsp");
			rd.forward(request, response);				
		}
	}
}
