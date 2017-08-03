package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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
public class SetNewQuestionPaper extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetNewQuestionPaper() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request, response, con);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request, response, con);
		
	}
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		
		List<SubjectName> sub = admin_Subject_dao.readAllSubject(con);
		request.setAttribute("Subjects", sub);
		response.setContentType("text/html");
		request.getRequestDispatcher("admin/quespaper/setNewQuestionPaper.jsp").include(request, response);
		
	}


}
