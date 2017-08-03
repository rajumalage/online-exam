package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.QuestionAnswer;


public class admin_Question_Answer_dao 
{
	
	public static List<QuestionAnswer> readAnswerDQuestionId(int quesId, Connection con)
	{
		List<QuestionAnswer> quesAnswers = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select answerid, answerid2questionid , answerid2optionsid from answer where answerid2questionid = " + quesId;
	//		System.out.println(sql);
			rs = stmt.executeQuery(sql);
			QuestionAnswer quesans = null;
			while( rs.next())
			{
				quesans = new QuestionAnswer();
				quesans.setId(rs.getInt("answerid"));
				quesans.setQuesId(rs.getString("answerid2questionid"));
				quesans.setOpesDesc(rs.getString("answerid2optionsid"));
				quesAnswers.add(quesans);
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs,stmt,null);
		}
		return quesAnswers;
	}
}
