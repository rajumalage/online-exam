package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Question_dao;
import com.lara.oes.entity.QuestionName;

/**
 * @author Sourav Barman
 * @author Umesh Kumar
 */
public class SearchQuestion extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchQuestion() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}

	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con)throws ServletException, IOException {
		String SubjectId = request.getParameter("SubJectName");
		String TopicID = request.getParameter("TopicName");
		String SearchQuestion = request.getParameter("searchQuestion");
		List<QuestionName> SrcQuestion = admin_Question_dao.SearchQuestionWTopicNameAndSubName(SubjectId,TopicID,SearchQuestion, con);
		request.setAttribute("QuestionSearch", SrcQuestion);	
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("admin/searchByQuestion.jsp");
		rd.include(request, response);
	}
}
