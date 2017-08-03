package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.QuestionAnswer;
import com.lara.oes.entity.QuestionOption;


/**
 * @author Sourav Barman
 *
 */
public class admin_Question_Option_dao 
{

	public static List<QuestionOption> readOpsCountDQuesId(int quesId, Connection con)
	{
		List<QuestionOption> OptionsOptionCount = new ArrayList<>();
		
		
		StringBuffer optionQuery = new StringBuffer();
		optionQuery.append("(");
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			List<QuestionAnswer> quesAns = admin_Question_Answer_dao.readAnswerDQuestionId(quesId, con);
	
			for (int i = 0; i < quesAns.size(); i++)
			{
				optionQuery.append(quesAns.get(i).getOpesDesc());
				
				if (i != quesAns.size() - 1)
				{
					optionQuery.append(",");
				} else
				{
					optionQuery.append(")");
				}
			}


			String sql = "select optionsid, optionsidcount from options where Optionsid in " + optionQuery.toString() +" order by optionsid";

			rs = stmt.executeQuery(sql);
			
			QuestionOption quesOps = null;
			
			while( rs.next())
			{
				quesOps = new QuestionOption();
				quesOps.setOpsCount(Integer.parseInt(rs.getString("optionsidcount")));
				quesOps.setId(Integer.parseInt(rs.getString("optionsid")));
				OptionsOptionCount.add(quesOps);
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
		return OptionsOptionCount;
	}
	
	
	/**
	 * @author Sourav Barman
	 * @author Vinay Mittal
	 * @param quesId
	 * @param con
	 * @return
	 */
	public static List<QuestionOption> readQuesOptionDQuesId(int quesId, Connection con)
	{
		List<QuestionOption> Options = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select optionsid, description from options where optionsid2questionid =" + quesId + " order by optionsid";
			rs = stmt.executeQuery(sql);
			QuestionOption quesOps = null;
			while( rs.next())
			{
				quesOps = new QuestionOption();
				quesOps.setId(rs.getInt("optionsid"));
				quesOps.setOptionDesc(rs.getString("description"));
				Options.add(quesOps);
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
		return Options;
	}

}
