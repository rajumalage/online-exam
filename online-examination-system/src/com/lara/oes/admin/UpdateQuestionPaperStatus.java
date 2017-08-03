package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_QuestionPaper_dao;

/**
 * @author Rahul
 * @author Sourav Barman
 *
 */
public class UpdateQuestionPaperStatus extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateQuestionPaperStatus() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		updateQuestionPaper(request,response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		updateQuestionPaper(request,response,con);
	}

	protected void updateQuestionPaper(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		String quesPaper = request.getParameter("quesId");
		if(quesPaper != null)
		{
			int quesPaperId = Integer.parseInt(request.getParameter("quesId"));
			int quesPaperStatus = Integer.parseInt(request.getParameter("status"));
			int status = 0;
			response.setContentType("text/html");
			status = admin_QuestionPaper_dao.UpdateQuestionPaperStatus(quesPaperStatus, quesPaperId, con);
			if(status == 1)
			{
				request.getRequestDispatcher("SetQuestionPaper.htm?status=1").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("SetQuestionPaper.htm?status=0").forward(request, response);
			}
		}
		else
		{
			request.getRequestDispatcher("SetQuestionPaper.htm").forward(request, response);
		}
	
		
	}
}
