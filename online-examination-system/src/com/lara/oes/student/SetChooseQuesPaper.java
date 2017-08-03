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
public class SetChooseQuesPaper extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetChooseQuesPaper() {
        
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
		
		List<QuestionPaper> quesPaperDesc = admin_QuestionPaper_dao.quesPaperStatus(con);
		request.setAttribute("QuestionPaperDescription", quesPaperDesc);
		
		HttpSession session=request.getSession(false);  
        if(session!=null)
		{  
 
        	
        	if(quesPaperDesc.size() == 0)
        	{
        		request.setAttribute("QuestionPaperActiveStatus", 0);
        	}
        	else
        	{
        		request.setAttribute("QuestionPaperActiveStatus", 1);
        	}
          RequestDispatcher rd = request.getRequestDispatcher("exam/setChooseQuesPaper.jsp");
          rd.forward(request, response);
        }  
        else
		{         
        	RequestDispatcher rd = request.getRequestDispatcher("student/userLogin.jsp");
            rd.forward(request, response);
        }  
		
		
	}
}
