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
public class ShowAllSubject extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowAllSubject() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		showAllTopic(request , response ,con);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		showAllTopic(request , response ,con);
	}
	
	/**
	 * Show To subject Name And Description Form call ajax
	 * @author Sourav Barman
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showAllTopic(HttpServletRequest request, HttpServletResponse response , Connection con) throws ServletException, IOException
	{
		String subId = request.getParameter("SubId");
		List<SubjectName> sub = admin_Subject_dao.readAllSubjectWSubId(subId, con);
		request.setAttribute("subNameDesc", sub);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int SubjectId = Integer.parseInt(request.getParameter("SubId"));
		if(SubjectId != 0)
		{
			for(SubjectName subjects: sub)
			{
		
				out.print("<tr> <td>Update Subject Name</td>");
				out.print("<td><textarea id='SubName' name='SubName' rows='2' cols='21'>"+ subjects.getSubName() +"</textarea></td></tr>");
				
				out.print("<tr><td style='vertical-align: top'>Update Subject Description</td>");
				out.print("<td><textarea id='SubDesc' name='SubDesc' rows='8' cols='21'>"+ subjects.getSubDese() +"</textarea></td></tr>");
				
				out.print("<tr> <td colspan='2'><input id='submit' type='submit' value='Update Subject Names' onclick='return UpdateSubjectvalidate()'></td><tr>");
	
			}
		}
		else
		{
			out.print("<tr> <td></td>");
			out.print("<td><label Style='Color:red;font-size: 14px'> Please Select Existing Subject Name </label></td></tr>");
		}
	}
}
