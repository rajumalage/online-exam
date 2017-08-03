package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Subject_dao;
import com.lara.oes.entity.SubjectName;

/**
 * @author Rajib ranjan
 * @author Sourav Barman
 *
 */
public class SetSubject extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetSubject() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = getConnection();
		setSubjectServlet(request , response ,con);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection con = getConnection();
		setSubjectServlet(request , response ,con);
	}
	
	protected static void setSubjectServlet(HttpServletRequest request, HttpServletResponse response , Connection con) throws ServletException, IOException 
	{
		String successMassage = request.getParameter("success");
		
		if(successMassage == null)
		{
			
			successMassage = "";
		}
		else
		{
			int successmsg = Integer.parseInt(successMassage);
			if(successmsg == 1)
			{
				request.setAttribute("successMassage", "Subject Successfully Inserted");
			}
			else if(successmsg == 0)
			{
				request.setAttribute("successMassage", "Subject Not Inserted");
			}
		}
		
		
		List<SubjectName> subject = admin_Subject_dao.readAllSubject(con);
		request.setAttribute("Subjects", subject);
		
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("admin/subject/setSubject.jsp");
		rd.forward(request, response);
	}
}
