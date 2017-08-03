package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Subject_dao;

/**
 * @author Sourav Barman
 *
 */
public class DeleteSubjectFinal extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteSubjectFinal() {
       
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}
	protected void doPerform(HttpServletRequest request,
			HttpServletResponse response,Connection con) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String SubId = request.getParameter("SubJectID");

		if (Integer.parseInt(SubId) == 0 || SubId == null || SubId == "" || SubId.isEmpty() == true) {
			out.println("Please Select Existing Subject Name For Delete");
		} else {

			int status = 0;

			status = admin_Subject_dao.deleteSubject(SubId, con);

			if (status == 1) 
			{
				out.println("Subject has been Deleted");
			} else 
			{
				out.println("Subject Not Deleted");
			}
		}
	}

}
