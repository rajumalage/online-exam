package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Question_dao;

/**
 * @author Rahul Kumar
 * @author Sourav Barman
 * 
 */
public class DeleteQuestionFinal extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteQuestionFinal() {
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{ 
			Connection con = getConnection();	
			PrintWriter out = response.getWriter();
		  response.setContentType("text/html");
		  String[] QuesId= request.getParameterValues("DeleteQuestions");
		  
		  if(QuesId != null)
		  {
		  int status = 0;
		  
		  status = admin_Question_dao.deleteQuestionDMQuseID(QuesId, con);
			  
			  if(status == 1)
			  {				 
				  out.print("Question Deleted SuccessFully!!");
			  }
			  else
			  {
				  out.print("Question Deletion Not done !! Please Check QuesPaper.");
			  }
		  }
		  else
		  {
			  response.sendRedirect("SetQuestion.htm?success=0");
		  }
	}
}
