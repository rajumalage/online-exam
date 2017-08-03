package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Subject_dao;
import com.lara.oes.entity.SubjectName;

/**
 * @author Sourav Barman
 *
 */

public class ShowAllSubjectName extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowAllSubjectName() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		showASubjectName(request , response ,con);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		showASubjectName(request , response ,con);
	}
	
	/**
	 * Show to Topic name to user
	 * @author Sourav Barman
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showASubjectName(HttpServletRequest request, HttpServletResponse response , Connection con) throws ServletException, IOException
	{
		
		List<SubjectName> sub = admin_Subject_dao.readAllSubject(con);
		request.setAttribute("subName", sub);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print("<option value='0'>---- Select Subject Name----</option>");
		for(SubjectName subjName: sub)
		{
			out.print("<option value='"+ subjName.getId() +"'>"+ subjName.getSubName() +"</option>");
			
		}
	}
}
