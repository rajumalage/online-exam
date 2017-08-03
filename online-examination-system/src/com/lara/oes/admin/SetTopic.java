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

/**
 * @author Rohit yadav
 
 */
public class SetTopic extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetTopic() {
       
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = getConnection();
		doPerform(request , response ,con);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request , response ,con);
	}
	
    
    
    
    
    
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		

		String successMassage = request.getParameter("success");
		
		if(successMassage == null)
		{
			
			successMassage = "";
		}
		else
		{
			int successmsg = Integer.parseInt(successMassage);
			if(successmsg == 1)
			{
				request.setAttribute("successMassage", "topic Successfully Inserted");
			}
			else if(successmsg == 0)
			{
				request.setAttribute("successMassage", "topic Not Inserted");
			}
		}		
		String SearchTopics= "";
		List<TopicName> top = admin_Topic_dao.searchTopics(SearchTopics,con);
		request.setAttribute("Topics", top);
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("admin/topic/setTopic.jsp");
		rd.forward(request, response);
		
	}
}
