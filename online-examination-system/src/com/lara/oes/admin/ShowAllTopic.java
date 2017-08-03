package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Topic_dao;
import com.lara.oes.entity.TopicName;

/**
 * @author Sourav Barman
 *
 */
public class ShowAllTopic extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowAllTopic() {
        
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
	 * Read All Topic Name
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
		List<TopicName> topicName = admin_Topic_dao.readSubTopic(subId, con);		
		request.setAttribute("TopiesNames", topicName);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");	
			out.println("<option value='0'>----- Select-Topic: -----</option>");
			
		for (TopicName topicnam: topicName) {
			out.println("<option value='"+ topicnam.getId() +"'>"+ topicnam.getTopicName().trim() +"</option>");
		}

	}
}
