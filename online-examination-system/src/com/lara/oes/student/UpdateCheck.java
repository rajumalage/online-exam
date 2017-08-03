package com.lara.oes.student;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.student_Exam_Ques_dao;
import com.lara.oes.dao.student_InsertAnswer_dao;

/**
 * Servlet implementation class UpdateCheck
 */
/**
 * @author Vinay Mittal
 *
 */
public class UpdateCheck extends OesHttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
	doPerform(request, response,con);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request, response,con);
	}
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		int quesId = Integer.parseInt(request.getParameter("QuesId"));		
		String quesAns[] = request.getParameterValues("answer");		
		int studentId=0;
		int notAnsStatus = 2; // its for change the status not attempted // after based on color change 
		int AnsStatus = 3;	// its for change the status when it's attempted// after based on color change
		
		HttpSession session=request.getSession(false);  
        if(session!=null)
		{  
       
        	studentId = (Integer) session.getAttribute("StudentId");
        	student_InsertAnswer_dao.InsertNewAndDeleteOldForUpdate(quesId, quesAns,studentId ,con);
        	
        	if(quesAns == null)
        	{
        		student_Exam_Ques_dao.TempUpdateQuesIdStatus(notAnsStatus, quesId, studentId, con);
        	}
        	else if(quesAns != null)
        	{
        		student_Exam_Ques_dao.TempUpdateQuesIdStatus(AnsStatus, quesId, studentId, con);
        	}
        	RequestDispatcher rd = request.getRequestDispatcher("SetExamSummary.htm");
        	rd.forward(request, response);
		}
	}

}
