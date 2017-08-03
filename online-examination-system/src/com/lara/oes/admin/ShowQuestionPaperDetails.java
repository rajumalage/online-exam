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
import com.lara.oes.entity.QuestionName;
import com.lara.oes.entity.QuestionPaper;

/**
 * @author Sourav Barman
 *
 */
public class ShowQuestionPaperDetails extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowQuestionPaperDetails() {
       
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

		int quesPaperId = (Integer.parseInt(request.getParameter("quesId")));
		List<QuestionPaper> qpaper = admin_QuestionPaper_dao.questionPaperInformation(quesPaperId, con);
		request.setAttribute("questionpaperdesc",qpaper);
	
		List<QuestionName> qdesc = admin_QuestionPaper_dao.getQuetionDescDQuesPaper(quesPaperId, con);
		request.setAttribute("questionDesc", qdesc);
		
//		for (QuestionName quesdesc : qdesc) {
//			System.out.println(quesdesc.getQuesDesc());
//		}
		
		response.setContentType("text/html");
		RequestDispatcher rd=request.getRequestDispatcher("admin/quespaper/showQuestionPaperDetails.jsp");
		rd.include(request, response);
		
	}
}
