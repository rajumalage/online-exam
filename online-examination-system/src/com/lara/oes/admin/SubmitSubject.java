package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Subject_dao;
import com.lara.oes.entity.SubjectName;

/**
 * @author Rajib Ranjan
 * @author Sourav Barman
 */
public class SubmitSubject extends OesHttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SubmitSubject() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		submitSubjectToDao(request , response ,con);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		submitSubjectToDao(request , response ,con);
	}

	protected static void submitSubjectToDao(HttpServletRequest request, HttpServletResponse response , Connection con) throws ServletException, IOException 
	{
		
		String SubName = request.getParameter("setSubject");
		String SubNameDesc = request.getParameter("setSubDesc");
		int status = 0;
		if(SubName != null && SubNameDesc != null)
		{
				SubjectName sub = new SubjectName();
				sub.setSubName(SubName);
				sub.setSubDese(SubNameDesc);
			
			
			status = admin_Subject_dao.insertSubject(sub, con);
			System.out.println(status);
			if(status == 1)
			{
				System.out.println(status);
				response.sendRedirect("SetSubject.htm?success=1");	
			}
			else
			{
				System.out.println("Subject Quary Not Submited");
			}
		}
		else
		{
			
			response.sendRedirect("SetSubject.htm?success=0");
		}
	}
}
