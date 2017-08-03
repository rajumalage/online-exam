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
public class SetExam extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetExam() {
        
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
	
		String quesPaperId = request.getParameter("QuesPaperId");

		int QuestionPaperid = Integer.parseInt(quesPaperId);
		List<QuestionPaper> QuesPaperName = admin_QuestionPaper_dao.questionPaperInformation(QuestionPaperid, con);
		HttpSession session=request.getSession(false);  	
		  if(session!=null)
			{  

		    	session.setAttribute("QuesPaperID", quesPaperId);
		    	for (QuestionPaper quesPaperNameAndInfo : QuesPaperName) 
				{
					session.setAttribute("QuestionPaperName", quesPaperNameAndInfo.getQuesPaperDesc());
					session.setAttribute("QuestionPaperInfo", quesPaperNameAndInfo.getQuesPaperInfo());
					session.setAttribute("QuestionPaperDuration", quesPaperNameAndInfo.getQuesPaperDuration());
				}
		    	RequestDispatcher rd = request.getRequestDispatcher("exam/setExamInstraction.jsp");
		  		rd.forward(request, response);
		    	
		    }  
		    else
			{         
		    	RequestDispatcher rd = request.getRequestDispatcher("student/userLogin.jsp");
		        rd.forward(request, response);
		    }  
		
		  
	}
}
