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
public class SetNewQuestion extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetNewQuestion() {
       
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
		if(request.getParameter("paramSuccess")!=null)
		{
			String msg="";
			msg=request.getParameter("paramSuccess");
			request.setAttribute("paramSuccess", msg);
		}
		System.out.println("hi i a from set new");
		List<SubjectName> sub = admin_Subject_dao.readAllSubject(con);
		request.setAttribute("Subjects", sub);
		response.setContentType("text/html");
		/*request.getRequestDispatcher("admin/question/setNewQuestion.jsp").include(request, response);*/
		request.getRequestDispatcher("admin/question/setNewQuestion.jsp").forward(request, response);
		
	}


}
