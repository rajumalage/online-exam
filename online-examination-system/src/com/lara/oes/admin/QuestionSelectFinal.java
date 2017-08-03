package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_QuestionPaper_dao;

/**
 * @author Rohit Yadav
 * @author Sourav Barman
 */
public class QuestionSelectFinal extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public QuestionSelectFinal() {
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		  Connection con = getConnection();
		
		  response.setContentType("text/html");

		  String quesPaperId = request.getParameter("quesId");
		  String[] QuesIds= request.getParameterValues("questionIDs");
			  
		  if(QuesIds != null)
		  {

			  int status = 0;
	  
			  status = admin_QuestionPaper_dao.InserQuesIDIntoQuesPaper(QuesIds, quesPaperId, con);
			  
			  if(status == 1)
			  {

				  response.sendRedirect("SetQuestionPaper.htm?success=1");
			  }
			  else
			  {

				  response.sendRedirect("SetQuestionPaper.htm?success=0");
			  }
		  }
		  else
		  {
			//  System.out.println("Select QuesID IS NUll");
			  response.sendRedirect("SetQuestionPaper.htm?success=0");
		  }
	}
}
