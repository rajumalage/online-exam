package com.lara.oes.student;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_QuestionPaper_dao;
import com.lara.oes.entity.QuestionPaper;

/**
 * @author Sourav Barman
 *
 */
public class SetExamTwo extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetExamTwo() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}
	/**
	 * @author Sourav Barman
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		
		int quesPaperId = 0; 
		
		HttpSession session=request.getSession(false); 
		
		if(session != null)
		{
			quesPaperId =Integer.valueOf((String)session.getAttribute("QuesPaperID"));
			List<QuestionPaper> quesPaperDesc = admin_QuestionPaper_dao.questionPaperInformation(quesPaperId, con);
			request.setAttribute("QuesPaperDesc", quesPaperDesc);
			RequestDispatcher rd = request.getRequestDispatcher("exam/setExamInstractionSet2.jsp");
			rd.forward(request, response);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("student/userLogin.jsp");
	        rd.forward(request, response);
		}
	}
}
