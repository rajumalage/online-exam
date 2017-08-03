package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.Question2QuestionPaper;
import com.lara.oes.entity.QuestionName;

public class admin_Question2QuestionPaper_dao 
{
//	static ResultSet rs = null;
	public static int InserQuesIDIntoQues2QuesPaper(String[] quesids ,String quesPaperId, Connection con )
	{	
		
		String InsertQuesId = null; 	
		int status = 0;
		Statement stmt = null;	
		try
		{			
			stmt = con.createStatement();
			
			for(int i=0;i<quesids.length;i++)
			{
				InsertQuesId = "INSERT INTO QUESTION2QUESTIONPAPER values(QUESTION2QUESTIONPAPER_SEQ.nextval,"+ quesPaperId +","+quesids[i]+")";
				
				//System.out.println(InsertQuesId);
				stmt.executeQuery(InsertQuesId);				
			}
	        
	        status = 1;
	        System.out.println(status);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(null, stmt, null);
		}
		return status;
	}
	
	// Show Question description in click when it is executed...
	public static List<QuestionName> getQuetionDescDQuesPaper(int quesPaperId,Connection con)
	{
		
		String sql="select THISID2QUESTIONID from  QUESTION2QUESTIONPAPER where THISID2QUESTIONPAPERID="+quesPaperId;

		List<QuestionName> questions= new ArrayList<QuestionName>();
		Statement stmt=null;
		ResultSet rs=null;
		try
		{
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				questions.add(readQuestionthroughQuestionids(rs.getInt("THISID2QUESTIONID"),con));		
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return questions;
		
	}
	public static QuestionName  readQuestionthroughQuestionids(int quesId, Connection con)
	{
		Statement stmt = null;
		ResultSet rs = null;
		QuestionName ques = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select  description from question where questionid = "+quesId;

			rs = stmt.executeQuery(sql);		
			if( rs.next())
			{
				ques = new QuestionName();
	            ques.setQuesDesc(rs.getString("description"));				
			}
		
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return ques;
	}
	
	/**
	 * @author Sourav Barman
	 * @param qpaperId
	 * @param con
	 * @return
	 */
	public static List<Question2QuestionPaper> getQuestionIds(String qpaperId,Connection con)
	{
		
		String sql="select * from  QUESTION2QUESTIONPAPER where THISID2QUESTIONPAPERID="+ qpaperId +" ORDER BY THISID";

		List<Question2QuestionPaper> questionids = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;;
		Question2QuestionPaper quesid = null;
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				quesid = new Question2QuestionPaper();
				quesid.setQuestionId(rs.getInt("THISID2QUESTIONID"));
				questionids.add(quesid);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtil.closeAll(rs, null, null);
		}
		return questionids;
		
	}
}