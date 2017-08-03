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
import com.lara.oes.dao.student_InsertAnswer_dao;
import com.lara.oes.entity.QuestionName;
import com.lara.oes.entity.QuestionOption;
import com.lara.oes.entity.StudentAnswer;

/**
 * show Question With Question Option when click Question pattate 
 * @author Sourav Barman
 *
 */
public class ShowQuesDescResQuesId extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowQuesDescResQuesId() {
        
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
	 * Show Question With Question Option when click Question Palette 
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		int quesId = Integer.parseInt(request.getParameter("QuesId"));
		int quesIndex = -1;
		int studentId = 0;
		
		ArrayList<Integer>  AllQuesIds = null;
		HttpSession session=request.getSession(false);  
        if(session!=null)
		{  
    
        	ServletContext sc = getServletContext();		    // now we are try to get AllQuestionIds arrayList form ServletContext 
        	
        	AllQuesIds = (ArrayList<Integer>) sc.getAttribute("AllQuestionIds");
   
        	quesIndex = AllQuesIds.indexOf(quesId);
        	session.setAttribute("QuestionNo", quesIndex+1);
        	
        	List<QuestionName> questinDesc = admin_Question_dao.readQuestionDQuestionId(quesId, con);
			request.setAttribute("QuestionDesc", questinDesc); // read question id and description depends ques id
			
			List<QuestionOption> QuesOption = admin_Question_Option_dao.readQuesOptionDQuesId(quesId, con);
			request.setAttribute("QuesOptions", QuesOption);   // read question Options id and description depends ques id
			
			
			studentId = (Integer) session.getAttribute("StudentId");
			List<StudentAnswer> SubmitQues  = student_InsertAnswer_dao.displayAnsToStu(quesId, studentId, con);
			request.setAttribute("CurrectOpsId", SubmitQues); 	// read question options id for chaked the option
			
			
			/*for (StudentAnswer stuAns : SubmitQues) 
			{
				System.out.println(stuAns.getQuesId());
				System.out.println(stuAns.getOpesId());
			}*/
			setConnection(con);
			RequestDispatcher rd = request.getRequestDispatcher("exam/ReturnQuesDesc.jsp");
			rd.forward(request, response);
        }  
        else
		{         
        	response.sendRedirect("setStudentLogin.htm?LoginStatus=0");
        }  
		
		
	}
}
