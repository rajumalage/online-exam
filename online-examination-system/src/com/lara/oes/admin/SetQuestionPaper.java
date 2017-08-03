package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_QuestionPaper_dao;
import com.lara.oes.entity.QuestionPaper;

/**
 * @author Sourav Barman
 *
 * @author Rahul
 *
 */
public class SetQuestionPaper extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetQuestionPaper() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
		
	}
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		List<QuestionPaper> qpaper = admin_QuestionPaper_dao.questionPaperInformation(con);
		request.setAttribute("questionpaperdesc",qpaper);
	
		response.setContentType("text/html");
		RequestDispatcher rd=request.getRequestDispatcher("admin/quespaper/setQuestionPaper.jsp");
		rd.include(request, response);
		
	}
}
