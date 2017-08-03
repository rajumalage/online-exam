package com.lara.oes.student;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.ResultsDAO;
import com.lara.oes.dao.StudentDAO;
import com.lara.oes.dao.admin_QuestionPaper_dao;
import com.lara.oes.dao.student_Exam_Ques_dao;
import com.lara.oes.entity.Marks;
import com.lara.oes.entity.Question2QuestionPaper;
import com.lara.oes.entity.QuestionPaper;
import com.lara.oes.entity.Student;
import com.lara.oes.entity.TempQuesStatus;

/**
 * @author Umesh Kumar
 * @author Rahul
 * @author Vinay Mittal
 *
 */
public class SetResult extends OesHttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}
	
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con)throws ServletException, IOException {
		response.setContentType("text/html");
		ArrayList<Integer>  questionList = null;
		ServletContext sc=getServletContext();
		HttpSession session = request.getSession(false); 
		
		if(session != null)
		{
			
			String QPaperId=(String) session.getAttribute("QuesPaperID");
			
			System.out.println(QPaperId);
		
			questionList = (ArrayList<Integer>)sc.getAttribute("AllQuestionIds");
			
			
		
			int studentId= (Integer) session.getAttribute("StudentId");
			
			Student student=StudentDAO.getStudentDetail(studentId,con);
			request.setAttribute("studentDetails", student);
			
			QuestionPaper list2=admin_QuestionPaper_dao.questionPaperInformation(QPaperId, con);
			request.setAttribute("questionPaper", list2);
			
			
			ArrayList<Question2QuestionPaper> list=(ArrayList)sc.getAttribute("AllQuestionIds");
			Marks marks = ResultsDAO.readMarks(studentId,QPaperId,list,con);
			request.setAttribute("allMarks", marks);
			
			List<TempQuesStatus> QuestionIDs = student_Exam_Ques_dao.getQuestionIds(studentId, con);
			request.setAttribute("QuesIdsStatus", QuestionIDs);

        	
			RequestDispatcher rd = request.getRequestDispatcher("exam/showStudentResult.jsp");
			rd.include(request, response);
		}
		else
		{
			response.sendRedirect("setStudentLogin.htm?LoginStatus=0");
		}
	}
}
