package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Question_dao;
import com.lara.oes.dao.admin_Subject_dao;
import com.lara.oes.entity.QuestionName;
import com.lara.oes.entity.SubjectName;

/**
 * @author Vinay Yadav
 * @author Rahul
 * @author Sourav Barman
 */
public class SetQuestion extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetQuestion() {
       
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
	 * Create question Module
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) 
			throws ServletException, IOException 
	{
		String successMassage = request.getParameter("paramSuccess");
		if(successMassage == null)
		{
			successMassage = "";
		}
		request.setAttribute("successMassage", successMassage);
		
		response.setContentType("text/html");
		
		List<SubjectName> subject = admin_Subject_dao.readAllSubject(con);
		request.setAttribute("Subjects", subject);	
		
		List<QuestionName> SrcQuestion = admin_Question_dao.SearchQuestionWTopicNameAndSubName("0","0","", con);
		request.setAttribute("questionsList", SrcQuestion);
		
		request.getRequestDispatcher("admin/question/setQuestion.jsp").include(request, response);
	}
}
