package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Question_dao;

/**
 * @author Vinay Mittal
 *
 */
public class SubmitQuestions extends OesHttpServlet {


	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request, response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request, response,con);
		
	}

	private void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		int status=0;
		response.setContentType("text/html");
		String question = request.getParameter("QuestionDesc").trim();
		String options[]=request.getParameterValues("textbox");
		String answer = request.getParameter("QuesAws");
		String topicId = request.getParameter("TopicID");
			
		status= admin_Question_dao.insertQues(question, options, answer, topicId, con);
		
		if(status==1)
		{
			RequestDispatcher rd = request.getRequestDispatcher("SetNewQuestion.htm?paramSuccess=Question Saved Successfully!!");
			rd.include(request, response);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("SetNewQuestion.htm?paramSuccess=Sorry!!Please Input Proper Details");
			rd.include(request, response);
			
		}
	}
}
	

