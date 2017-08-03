package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Topic_dao;

/**
 * @author Rohit Yadav
 * @author Sourav Barman
 *
 */
public class DeleteTopicFinal extends OesHttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection con =  getConnection();
		DoPerformDeleteTopic(request,response,con);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection con =  getConnection();
		DoPerformDeleteTopic(request,response,con);
	}
	
	protected void DoPerformDeleteTopic(HttpServletRequest request, HttpServletResponse response,Connection con) 
			throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		int topicId=Integer.parseInt(request.getParameter("TopicName"));		
		int status = 0;
		if(topicId != 0)
		{
			status=admin_Topic_dao.deleteTopic(con, topicId);
			if(status == 1)
			{
				out.println("Topic Deletion Successfull!!");
			}
			else
			{
				out.println("Topic Deletion not Success");
			}
		}
		else
		{
			out.println("Please Select Existing Topic Name");
		}
	}
}
