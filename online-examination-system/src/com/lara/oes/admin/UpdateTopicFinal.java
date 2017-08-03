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
 * @author Sourav Barman
 *
 */
public class UpdateTopicFinal extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateTopicFinal() {
       
    }

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = getConnection();
		
			response.setContentType("text/html");
		  PrintWriter out=response.getWriter();
		  String TopicID= request.getParameter("TopicName");
		  String TopicUpdateName= request.getParameter("TopicUpdateName");
		  String TopicUpdateDesc= request.getParameter("TopicUpdateDesc");
		  System.out.println(TopicID);
		  if(Integer.parseInt(TopicID) == 0 || TopicID == null || TopicID == "")
		  {
			  out.println("Please Select Existing Topic Name");
		  }
		  else
		  {
			  TopicName topic = new TopicName();
			 topic.setId(Integer.parseInt(TopicID));
			  topic.setTopicName(TopicUpdateName.trim());
			  topic.setTopicDesc(TopicUpdateDesc.trim());
			  
			  int status = 0;
			  
			  status = admin_Topic_dao.updateTopic(topic, con);
			  
			  if(status == 1)
			  {
				  out.println("<pre style='color:green;font-size:14px;'>Topic SuccessFully Updated</pre>");
			  }
			  else
			  {
				  out.println("<pre style='color:red;font-size:14px;'>Topic Not Updated</pre>");
			  }
		  }  
	}

}
