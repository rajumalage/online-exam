package com.lara.oes.student;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.lara.oes.dao.admin_Question2QuestionPaper_dao;
import com.lara.oes.dao.admin_Question_Option_dao;
import com.lara.oes.dao.admin_Question_dao;
import com.lara.oes.dao.student_Exam_Ques_dao;
import com.lara.oes.entity.Question2QuestionPaper;
import com.lara.oes.entity.QuestionName;
import com.lara.oes.entity.QuestionOption;

/**
 * @author Sourav Barman
 * @author Vinay Mittal
 *
 */
public class DoExamStart extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public DoExamStart() {
        
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
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		int studentId = 0 ;
		HttpSession session=request.getSession(false); 
		ArrayList<Integer>  AllQuesIds = new ArrayList<Integer>();
		if(session != null)
		{
			studentId = (Integer) session.getAttribute("StudentId");
			String qpaperId = (String) session.getAttribute("QuesPaperID");

			

			List<Question2QuestionPaper> QuestionIDs = admin_Question2QuestionPaper_dao.getQuestionIds(qpaperId, con);
			request.setAttribute("QuestionIdsDPaper", QuestionIDs); // read and set quesIds Respected questionPaperId
			
			for (Question2QuestionPaper quesids : QuestionIDs) 
			{
				AllQuesIds.add(quesids.getQuestionId());  // all question are store into ArrayList 
			}
			
			student_Exam_Ques_dao.TempStoreQuesIdToDatabase(studentId, AllQuesIds, con); // store questionsId to database for updated the status
			System.out.println("store questionsId");
			
			ServletContext sc=getServletContext();		    // get ServletContext 
			sc.setAttribute("AllQuestionIds", AllQuesIds);	// AllQuestionIds ArrayList  set Attribute to ServletContext
			
			session.setAttribute("ArrayListSize", AllQuesIds.size());  // set arrayList
			session.setAttribute("QuestionNo", 1); // Display QuestionNO Serial
			
			List<QuestionName> questinDesc = admin_Question_dao.readQuestionDQuestionId(AllQuesIds.get(0), con);
			request.setAttribute("QuestionDesc", questinDesc); // read question id and description depends ques id
			
			List<QuestionOption> QuesOption = admin_Question_Option_dao.readQuesOptionDQuesId(AllQuesIds.get(0), con);
			request.setAttribute("QuesOptions", QuesOption);   // read question Options id and description depends ques id
			
			RequestDispatcher rd = request.getRequestDispatcher("exam/doStartExam.jsp");
			rd.forward(request, response);
		}
		else
		{
			response.sendRedirect("setStudentLogin.htm?LoginStatus=0");
		}

	}
}

