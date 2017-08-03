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
import com.lara.oes.dao.admin_Topic_dao;
import com.lara.oes.entity.SubjectName;
import com.lara.oes.entity.TopicName;

public class SetUpdateTopic extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetUpdateTopic() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = getConnection();
		String topicId = request.getParameter("topid");
	List<TopicName> topic = admin_Topic_dao.readTopicNameAndDesc( topicId, con);
		request.setAttribute("topics",topic);
		
		response.setContentType("text/html");
		RequestDispatcher rd = request
				.getRequestDispatcher("admin/topic/setUpdateTopic.jsp");
		rd.forward(request, response);	
	}

}
