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
 * @author Rahul
 */
public class DeleteQuestionPaper extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteQuestionPaper() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		deleteQuestionPaper(request,response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		deleteQuestionPaper(request,response,con);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteQuestionPaper(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		int quesPaperId = Integer.parseInt(request.getParameter("QuesPaperId"));
		int status = 0;
		response.setContentType("text/html");
		status = admin_QuestionPaper_dao.deleteQuestionPaper(quesPaperId, con);
		
		List<QuestionPaper> qpaper = admin_QuestionPaper_dao.questionPaperInformation(con);
		request.setAttribute("questionpaperdesc",qpaper);
		
		if(status == 1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("admin/quespaper/showQuestionPaper.jsp");
			rd.include(request, response);
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("admin/quespaper/showQuestionPaper.jsp");
			rd.include(request, response);
		}
		
	}
}
