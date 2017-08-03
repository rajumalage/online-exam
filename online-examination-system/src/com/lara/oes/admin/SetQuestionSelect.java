package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_QuestionPaper_dao;
import com.lara.oes.dao.admin_Subject_dao;
import com.lara.oes.entity.QuestionPaper;
import com.lara.oes.entity.SubjectName;

/**
 * @author Rohit Yadav
 
 */
public class SetQuestionSelect extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetQuestionSelect() {
       
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
	 * Create Question Select Module
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) 
			throws ServletException, IOException {
		int quesPaperId = 0;	
		QuestionPaper qp= new QuestionPaper();
		
		  qp.setQuesPaperDesc(request.getParameter("quesPaperDescription"));
		  qp.setQuesPaperDuration(Float.parseFloat(request.getParameter("duration")));		  
		  qp.setNoOfQues(Integer.parseInt(request.getParameter("noofquestions")));
		  qp.setQuesPaperMarksPerQues(Float.parseFloat(request.getParameter("perquestionmarks")));
		  qp.setQuesPaperPassingMark(Float.parseFloat(request.getParameter("passingmarks")));
		  qp.setQuesPaperNagativeMark(Float.parseFloat(request.getParameter("negativemarks")));
		  qp.setQuesPaperTotalMark(Integer.parseInt(request.getParameter("totalmarks")));
		  qp.setQuesPaperInfo(request.getParameter("QuesPaperInfo"));
      
		  List<QuestionPaper> quesPaperDescs= admin_QuestionPaper_dao.CreateQuestionPaper(qp, con);
		  for (QuestionPaper quesPaper : quesPaperDescs) 
		  {
			  quesPaperId= quesPaper.getQuesPaperId();
		  }
		  if (quesPaperId == 0) 
		  {
			  	List<SubjectName> sub = admin_Subject_dao.readAllSubject(con);
				request.setAttribute("Subjects", sub);
				response.setContentType("text/html"); 
				request.getRequestDispatcher("SetNewQuestionPaper.htm").forward(request, response);
		  }
		  else
		  {
			  	List<SubjectName> sub = admin_Subject_dao.readAllSubject(con);
				request.setAttribute("Subjects", sub);
				request.setAttribute("QuestionPaperDesc", quesPaperDescs);
				response.setContentType("text/html");
				request.getRequestDispatcher("admin/quespaper/setQuestionSelect.jsp").forward(request, response);
			  	
		  }
	}
}
