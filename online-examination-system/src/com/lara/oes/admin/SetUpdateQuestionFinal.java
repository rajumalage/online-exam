package com.lara.oes.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Question_Option_dao;
import com.lara.oes.dao.admin_Question_dao;
import com.lara.oes.entity.QuestionName;
import com.lara.oes.entity.QuestionOption;

/**
 * @author Sourav Barman
 * @author Vinay Mittal
 */

public class SetUpdateQuestionFinal extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public SetUpdateQuestionFinal() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		updateQuestion(request,response,con);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		updateQuestion(request,response,con);
	}

	/**
	 * Update details Show to User respected QuesId
	 * @param request
	 * @param response
	 * @param con
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateQuestion(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {
		int quesId = Integer.parseInt(request.getParameter("questionId"));
		System.out.println(quesId);
		String qustion = "";
		int quesid = -1;
		String sql = "SELECT s.name as Sub_name,t.name as topic_name FROM Question q "
				+ "Full outer JOIN topic t ON q.questionid2topicid = t.topicid "
				+ "Full outer JOIN subject s on t.topicid2subjectid = s.subjectid where q.questionid=" + quesId;
	
		Statement stmt = null;
		ResultSet rs = null;
	
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{			
				request.setAttribute("SubjectName", rs.getString("Sub_name"));
				request.setAttribute("TopicName", rs.getString("topic_name"));
			}
		}
		catch(SQLException sqlEX)
		{
			sqlEX.printStackTrace();
		}
		
		List<QuestionName> QuestDesc = admin_Question_dao.readQuestionDQuestionId(quesId, con);
		request.setAttribute("QuestionDesc",QuestDesc);
		for (QuestionName quesName : QuestDesc) {
			quesid=quesName.getId();
			qustion=quesName.getQuesDesc();
		}

		request.setAttribute("QuestionID", quesid);
		request.setAttribute("Question", qustion);
		
		List<QuestionOption> Questopes = admin_Question_Option_dao.readQuesOptionDQuesId(quesId, con);
		request.setAttribute("Options",Questopes);
		
		List<QuestionOption> QuesOpsCount = admin_Question_Option_dao.readOpsCountDQuesId(quesId, con);
		request.setAttribute("QuestionOptionCount",QuesOpsCount);

		response.setContentType("text/html");
		request.getRequestDispatcher("admin/question/setUpdateQuestion.jsp").include(request, response);
	}
}
