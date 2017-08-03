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
 * @author Sourav Barman
 *
 */
public class SetDeleteQuestion extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetDeleteQuestion() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerforn(request,response,con);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerforn(request,response,con);
	}

	protected void doPerforn(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		String successMassage = request.getParameter("success");
		String color = null;
		int success = -1;
		if(successMassage == null)
		{
			successMassage = "";
		}
		else
		{
			success=Integer.parseInt(request.getParameter("success"));
		}
		if(success == 1)
		{
			color = "green";
			successMassage ="Question successfully Deleted";
		}
		else if(success == 0)
		{
			color = "red";
			successMassage ="Question Not Deleted";
		}
		
		request.setAttribute("colors", color);
		request.setAttribute("successMassage", successMassage);
		
		
		List<SubjectName> subject = admin_Subject_dao.readAllSubject(con);
		request.setAttribute("Subjects", subject);
		
		response.setContentType("text/html");
		RequestDispatcher rd = request
				.getRequestDispatcher("admin/setDeleteQuestion.jsp");
		rd.forward(request, response);
	}
}
