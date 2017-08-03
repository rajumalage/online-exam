package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Topic_dao;
import com.lara.oes.entity.TopicName;

/**
 * @author Rohit Yadav
 * @author Sourav Barman
 *
 */
public class SubmitTopic extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SubmitTopic() {
       
    }

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = getConnection();
		
			response.setContentType("text/html");
		  PrintWriter out=response.getWriter();
		  String SubId= request.getParameter("Subject").trim();
		  String TopName= request.getParameter("NewTopicName").trim();
		  String TopDesc= request.getParameter("TopicDesc").trim();
		  
		  if( TopName != null || TopName != "" || TopDesc != null || TopDesc != "")
		  {
		  
			  TopicName topic = new TopicName();
			  topic.setTopicName(TopName.trim());
			  topic.setTopicDesc(TopDesc.trim());
			  topic.setSubId(Integer.parseInt(SubId));
			  
			  int status = 0;
			  
			  status = admin_Topic_dao.insertTopic(topic, con);
			  if(status == 1)
				{
					System.out.println(status);
					response.sendRedirect("SetTopic.htm?success=1");	
				}
				else
				{
					response.sendRedirect("SetTopic.htm?success=0");
				}
		  } else
			response.sendRedirect("SetTopic.htm?success=0");
	}

}
