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
import com.lara.oes.dao.admin_Question_Option_dao;
import com.lara.oes.dao.admin_Question_dao;
import com.lara.oes.dao.student_Exam_Ques_dao;
import com.lara.oes.dao.student_InsertAnswer_dao;
import com.lara.oes.entity.QuestionName;
import com.lara.oes.entity.QuestionOption;

/**
 * Submit Question answer to Database
 * @author Sourav Barman
 * @author Vinay Mittal
 */
public class SubmitQuesAndReturnNextQues extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SubmitQuesAndReturnNextQues() {
        
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
		int quesId = Integer.parseInt(request.getParameter("QuesId"));
		String quesAns[] = request.getParameterValues("answer");
		int studentId = 0;
		int notAnsStatus = 2; // its for change the status not attempted // after based on color change 
		int AnsStatus = 3;	// its for change the status when it's attempted// after based on color change
		int quesIndex = -1;
		int nextQuesId = -1;
		
		ArrayList<Integer>  AllQuesIds = null;
		HttpSession session=request.getSession(false);  
        if(session!=null)
		{  
       
        	studentId = (Integer) session.getAttribute("StudentId");
        	student_InsertAnswer_dao.InsertNewAndDeleteOldForUpdate(quesId, quesAns,studentId ,con);
        	
        	// update QuesIdsStatus Means (1,2,3) when submit the question paper ()
        	if(quesAns == null)
        	{
        		student_Exam_Ques_dao.TempUpdateQuesIdStatus(notAnsStatus, quesId, studentId, con);
        	}
        	else if(quesAns != null)
        	{
        		student_Exam_Ques_dao.TempUpdateQuesIdStatus(AnsStatus, quesId, studentId, con);
        	}
     	
        	ServletContext sc = getServletContext();		    // now we are try to get AllQuestionIds arrayList form ServletContext 
        	
        	AllQuesIds = (ArrayList<Integer>) sc.getAttribute("AllQuestionIds");

      //  	System.out.println(quesId);
        	
        	if((AllQuesIds.indexOf(quesId)) == (AllQuesIds.size()-1))
        	{  		
        		quesId = AllQuesIds.get(0);
        		quesIndex = AllQuesIds.indexOf(quesId);        	
            	nextQuesId = AllQuesIds.get(quesIndex);
            	session.setAttribute("QuestionNo", 1);
        	}
        	else
        	{
        		quesIndex = AllQuesIds.indexOf(quesId);        	
            	nextQuesId = AllQuesIds.get(quesIndex + 1);
            	
            	quesIndex = AllQuesIds.indexOf(nextQuesId); // for next QuesIndex no;
            	
            	session.setAttribute("QuestionNo", quesIndex+1);
            	
            	 List<QuestionName> questinDesc = admin_Question_dao.readQuestionDQuestionId(nextQuesId, con);
     			request.setAttribute("QuestionDesc", questinDesc); // read question id and description depends ques id
     			
     			List<QuestionOption> QuesOption = admin_Question_Option_dao.readQuesOptionDQuesId(nextQuesId, con);
     			request.setAttribute("QuesOptions", QuesOption);   // read question Options id and description depends ques id
             	
     			setConnection(con);
     	          RequestDispatcher rd = request.getRequestDispatcher("exam/ReturnNextQues.jsp");
     	          rd.forward(request, response);
        	}

        	
        }  
        else
		{         
        	response.sendRedirect("setStudentLogin.htm?LoginStatus=0");
        }  
		
		
	}
}
