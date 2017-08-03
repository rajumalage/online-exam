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
import com.lara.oes.dao.student_Exam_Ques_dao;
import com.lara.oes.entity.TempQuesStatus;

/**
 * @author Sourav Barman
 *
 */
public class ShowQuesIdsStatus extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowQuesIdsStatus() {
        
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
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);  
        if(session!=null)
		{  
      
			int studentId = (Integer) session.getAttribute("StudentId");

			List<TempQuesStatus> QuestionIDs = student_Exam_Ques_dao.getQuestionIds(studentId, con);
			request.setAttribute("QuesIdsStatus", QuestionIDs); 
			
			RequestDispatcher rd = request.getRequestDispatcher("exam/ReturnQuesIdsStatus.jsp");
			rd.forward(request, response);
        }  
        else
		{         
        	response.sendRedirect("setStudentLogin.htm?LoginStatus=0");
        }  
		
		
	}
}
