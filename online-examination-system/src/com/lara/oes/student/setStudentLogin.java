package com.lara.oes.student;

import com.lara.oes.connnection.OesHttpServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sourav Barman
 *
 */
public class setStudentLogin extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

  
    public setStudentLogin() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPerforn(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPerforn(request,response);
	}

	protected void doPerforn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String loginstaus = request.getParameter("LoginStatus");

		 int msgstatus = 1 ;
		 int logstatus = 0 ;
		 if(loginstaus != null)
		 {
			 logstatus = Integer.parseInt(loginstaus);
			 if(logstatus == 0)
			 {
				 msgstatus = 0;

				 request.setAttribute("MsgStatus", msgstatus);
				 request.setAttribute("Massage", "Ooops Login Failed!! Provide Your Valid Credentials.");
			 }
			 RequestDispatcher rd = request.getRequestDispatcher("student/userLogin.jsp");
		     rd.forward(request, response);
		 }
		 else
		 {
			 request.setAttribute("Massage", "");
			 request.setAttribute("MsgStatus", msgstatus);
			 RequestDispatcher rd = request.getRequestDispatcher("student/userLogin.jsp");
		     rd.forward(request, response);
		 }
	}
	
}
