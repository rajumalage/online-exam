package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Question_dao;

/**
 * @author Sourav Barman
 *
 */

public class UpdateQuestionFinal extends OesHttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		Connection con = getConnection();
		doPerform(request, response, con);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		
		Connection con = getConnection();
		doPerform(request, response, con);
		
	}

	/**
	 * @author Vinay Mittal
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPerform(HttpServletRequest request,
			HttpServletResponse response,Connection con) throws ServletException, IOException
	{
		int status = 0;
		String quesid=request.getParameter("QuestionId");
		String queDesc=request.getParameter("QuestionDesc");
		String answer=request.getParameter("answer");
		String options[]=request.getParameterValues("Options");
	
		PrintWriter out = response.getWriter();
		status = admin_Question_dao.updateQuestion(quesid, queDesc, options, answer, con);
		if(status == 1)
		{
			out.print("Data SuccessFully Update");
			 System.out.println("Data SuccessFully Update");

		}
		else
		{
			out.println("<p style='color:red;font-size:14px'>Data Not Update</p>");
			System.out.println("Data Not Update");
		}
	}
}
