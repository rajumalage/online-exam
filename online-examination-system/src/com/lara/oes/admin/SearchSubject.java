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
 * @author Umesh Kumar
 * @author Sourav Barman
 * 
 */
public class SearchSubject extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchSubject() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}

	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con)throws ServletException, IOException {
		String searchsubject = request.getParameter("searchsubject");
		List<SubjectName> subDetails = admin_Subject_dao.searchSubject(con, searchsubject);
		request.setAttribute("Subjects", subDetails);	
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("admin/subject/searchBySubject.jsp");
		rd.include(request, response);
	}
}
