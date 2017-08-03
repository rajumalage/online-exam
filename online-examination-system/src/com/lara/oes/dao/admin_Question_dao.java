package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.QuestionName;

public class admin_Question_dao 
{
	/**
	 * @author Vinay Mittal
	 * @param Question
	 * @param Options
	 * @param Answer
	 * @param topic_Id
	 * @param con
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int insertQues(String Question,String Options[],String Answer,String topic_Id,Connection con)
	{
		int ans1=0,ans2=0,ans3=0;
		int count=0;
	//	Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		String insertQuery = "insert into QUESTION(DESCRIPTION, QUESTIONID2TOPICID) values('"+Question+"', "+topic_Id+")";
		System.out.println(insertQuery);
		String maxQuestionId = "Select max(QUESTIONID) from QUESTION";
		String questionId = "";
		String optionId = "";
		String optionQuery = "";
		try
		{
		//	con=Util.getConnection();
			stmt=con.createStatement();
			ans1=stmt.executeUpdate(insertQuery);
			rs=stmt.executeQuery(maxQuestionId);
			if(rs.next()){
				questionId = rs.getString(1);
				System.out.println(questionId);
			}
			for(int i=0 , Opcount = 1;i<Options.length;i++,Opcount++)
			{
				String insertOption = "insert into OPTIONS(OPTIONSID2QUESTIONID,OPTIONSIDCOUNT, DESCRIPTION) values("+questionId+","+ Opcount +", '"+Options[i]+"')";
				ans2=stmt.executeUpdate(insertOption);

			}
			String insertAnswer = "";
			String ans[] = Answer.trim().split(",");
			int l = 1;
			int f = 0;
			boolean flag = true;
			for (String str : ans) {
				flag = true;

				for(l = 1; l<= Options.length; l++){

					if(flag && Integer.parseInt(str) == l){
						f = l-1;
						optionQuery = "select OPTIONSID from OPTIONS where dbms_lob.compare(DESCRIPTION , '"+ Options[f] +"') = 0  and OPTIONSID2QUESTIONID="+questionId;
						rs=stmt.executeQuery(optionQuery);
						if(rs.next()){
							optionId = rs.getString(1);
							System.out.println("option id="+optionId);
						}
						insertAnswer = "insert into ANSWER(ANSWERID2QUESTIONID, ANSWERID2OPTIONSID) values("+questionId+", "+optionId+")";
						 ans3=stmt.executeUpdate(insertAnswer);
						flag = false;
					}
				}

			}
			if((ans1==1)&&(ans2==1)&&(ans3==1))
			{
				count=1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, null);
			return count;
		}
	}
	
	public static List<QuestionName> readAllQuestion(Connection con)
	{
		List<QuestionName> questions = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select id,ques_dese,topic_id  from online_question";
			rs = stmt.executeQuery(sql);
			QuestionName question = null;
			while( rs.next())
			{
				question = new QuestionName();
				question.setId(rs.getInt("id"));
				question.setQuesDesc(rs.getString("ques_dese"));
				question.setTopicId(rs.getInt("topic_id"));
				questions.add(question);
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
		return questions;
	}
	
	
	/**
	 * @author Sourav Barman
	 * @author Vinay Mittal
	 * @param quesId
	 * @param con
	 * @return
	 */
	public static List<QuestionName> readQuestionDQuestionId(int quesId, Connection con)
	{
		List<QuestionName> questionss = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select questionid, description from question where questionid = " + quesId;
			rs = stmt.executeQuery(sql);
			QuestionName ques = null;
			while( rs.next())
			{
				ques = new QuestionName();
				ques.setId(rs.getInt("questionid"));
				ques.setQuesDesc(rs.getString("description"));
				questionss.add(ques);
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
		return questionss;
	}
	
	/**
	 * @author Sourav Barman
	 * @param SubjectID
	 * @param topicId
	 * @param key
	 * @param con
	 * @return
	 */
	public static List<QuestionName> SearchQuestionWTopicNameAndSubName(String SubjectID,String topicId,String key,Connection con)
	{
		List<QuestionName> questiones = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		int subid = Integer.parseInt(SubjectID);
		int topid = Integer.parseInt(topicId);
		if(subid != 0 && topid == 0)
		{
			// search by subjectID
			try
			{
				stmt = con.createStatement();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT Q.QUESTIONID, Q.DESCRIPTION,Q.QUESTIONID2TOPICID,T.TOPICID, ");
				sql.append("T.NAME AS TOPICNAME ,S.SUBJECTID, S.NAME AS SUBNAME FROM QUESTION Q ");
				sql.append("INNER JOIN TOPIC T ON Q.QUESTIONID2TOPICID = T.TOPICID ");
				sql.append("INNER JOIN SUBJECT S ON T.TOPICID2SUBJECTID = S.SUBJECTID ");
				sql.append("WHERE QUESTIONID IN (SELECT QUESTIONID FROM QUESTION ");
				sql.append("where ( upper(DESCRIPTION) like upper('%"+ key +"%')) ");
				sql.append("AND S.SUBJECTID = "+ SubjectID +" ) order by Q.QUESTIONID");
				rs = stmt.executeQuery(sql.toString());
				QuestionName questionn = null;
			
				while( rs.next())
				{
					questionn = new QuestionName();
					questionn.setId(rs.getInt("QUESTIONID"));
					questionn.setQuesDesc(rs.getString("DESCRIPTION"));
					questionn.setTopicName(rs.getString("TOPICNAME"));
					questionn.setSubName(rs.getString("SUBNAME"));
					questiones.add(questionn);
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
		}
		else if(subid != 0 && topid != 0)
		{
		//	search by SubjectId and respected TopicID
			try
			{
				stmt = con.createStatement();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT Q.QUESTIONID, Q.DESCRIPTION,Q.QUESTIONID2TOPICID,T.TOPICID, ");
				sql.append("T.NAME AS TOPICNAME ,S.SUBJECTID, S.NAME AS SUBNAME FROM QUESTION Q ");
				sql.append("INNER JOIN TOPIC T ON Q.QUESTIONID2TOPICID = T.TOPICID ");
				sql.append("INNER JOIN SUBJECT S ON T.TOPICID2SUBJECTID = S.SUBJECTID ");
				sql.append("WHERE QUESTIONID IN (SELECT QUESTIONID FROM QUESTION ");
				sql.append("where ( upper(DESCRIPTION) like upper('%"+ key +"%')) ");
				sql.append("AND Q.QUESTIONID2TOPICID = "+ topicId +" ) order by Q.QUESTIONID");
				rs = stmt.executeQuery(sql.toString());
				QuestionName questionn = null;
		
				while( rs.next())
				{
					questionn = new QuestionName();
					questionn.setId(rs.getInt("QUESTIONID"));
					questionn.setQuesDesc(rs.getString("DESCRIPTION"));
					questionn.setTopicName(rs.getString("TOPICNAME"));
					questionn.setSubName(rs.getString("SUBNAME"));
					questiones.add(questionn);
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
		}
		else
		{
//			search Question by key
			try
			{
				stmt = con.createStatement();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT Q.QUESTIONID, Q.DESCRIPTION,Q.QUESTIONID2TOPICID,T.TOPICID, ");
				sql.append("T.NAME AS TOPICNAME ,S.SUBJECTID, S.NAME AS SUBNAME FROM QUESTION Q ");
				sql.append("INNER JOIN TOPIC T ON Q.QUESTIONID2TOPICID = T.TOPICID ");
				sql.append("INNER JOIN SUBJECT S ON T.TOPICID2SUBJECTID = S.SUBJECTID ");
				sql.append("WHERE QUESTIONID IN (SELECT QUESTIONID FROM QUESTION ");
				sql.append("where upper(DESCRIPTION) like upper('%"+ key +"%')) order by Q.QUESTIONID");			
				rs = stmt.executeQuery(sql.toString());
				QuestionName questionn = null;

				while( rs.next())
				{
					questionn = new QuestionName();
					questionn.setId(rs.getInt("QUESTIONID"));
					questionn.setQuesDesc(rs.getString("DESCRIPTION"));
					questionn.setTopicName(rs.getString("TOPICNAME"));
					questionn.setSubName(rs.getString("SUBNAME"));
					questiones.add(questionn);
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
		}
		return questiones;
	}
	
	public static List<QuestionName> readQuestionDTopic(int topicId, Connection con)
	{
		List<QuestionName> questionss = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select id, ques_dese from online_question where topic_id = " + topicId;
			rs = stmt.executeQuery(sql);
			QuestionName ques = null;
			while( rs.next())
			{
				ques = new QuestionName();
				ques.setId(rs.getInt("id"));
				ques.setQuesDesc(rs.getString("ques_dese"));
				questionss.add(ques);
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
		return questionss;
	}
	
	/**
	 * Update Question
	 * @author Vinay Mittal
	 * @param q_id
	 * @param queName
	 * @param s
	 * @param answer
	 * @param con
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int updateQuestion(String q_id,String queName, String s[],String answer,Connection con)
	{
	    Statement stmt=null,stmt2=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		int status=0;
		try
		{
			
			int ans1=0,ans2=0,ans3=0;
			
	//		Connection con=Util.getConnection();
			stmt=con.createStatement();
			int min=0;
			String s1[]=answer.split(",");
			
			int a[]=new int[s1.length];
			
			String ques="update QUESTION SET DESCRIPTION='"+queName+"' where QUESTIONID="+q_id;
			ans1=stmt.executeUpdate(ques);
			
			String ansDelete="delete from answer where ANSWERID2QUESTIONID= "+q_id;
			stmt.executeUpdate(ansDelete);
			
			String optionDelete="delete from OPTIONS where OPTIONSID2QUESTIONID="+q_id;
			stmt.executeUpdate(optionDelete);
			
			//inserting into options table
			for(int i=0,count = 1;i<s.length;i++,count++)
			{
				String insertOption = "insert into OPTIONS(OPTIONSID2QUESTIONID, OPTIONSIDCOUNT ,DESCRIPTION) values("+q_id+","+ count +", '"+s[i]+"')";
				ans2=stmt.executeUpdate(insertOption);
			}
			
			//inserting into answer table
			String minimum="select min(OPTIONSID) FROM OPTIONS where OPTIONSID2QUESTIONID="+q_id;
			try
			{
				stmt2=con.createStatement();
				rs1=stmt2.executeQuery(minimum);
				while(rs1.next())
				{
					min=rs1.getInt(1);
				}
				for(int i=0;i<s1.length;i++)
				{
					a[i]=(min-1)+Integer.parseInt(s1[i]);
				}
				for(int i=0;i<a.length;i++)
				{
				String answerInsert="insert into answer (ANSWERID2QUESTIONID,ANSWERID2OPTIONSID) values("+q_id+","+a[i]+")";
				ans3=stmt.executeUpdate(answerInsert);
				}
				if((ans1==1)&&(ans2==1)&&(ans3==1))
				{
					status=1;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			DbUtil.closeAll(rs, stmt, null);
			return status;
		}
	}
	
	/**
	 * @author : Rahul Kumar
	 * @function : delete multiple ques respected Question Ids,
	 * @param quesid
	 * @param con
	 * @return
	 */
	public static int deleteQuestionDMQuseID(String[] quesid , Connection con )
	{	
		String deletAnswer = "delete  from answer where answerid2questionid= "+ quesid[0];
		String deletOptions = "delete from options where optionsid2questionid= "+ quesid[0];
		String deletQuestionns = "delete from question where questionid= "+ quesid[0];		
		int status = 0;		
		String sql="select THISID2QUESTIONID from question2questionpaper where THISID2QUESTIONID="+quesid[0];
		ResultSet rs=null;
		Statement stmt = null;	
		Statement stmt1=null;
		try
		{
			stmt1 = con.createStatement();
			rs=stmt1.executeQuery(sql);			
			if(rs.next())
			{
				status=0;
			}
			else
			{
			stmt = con.createStatement();
			stmt.addBatch(deletAnswer);
			stmt.addBatch(deletOptions);
			stmt.addBatch(deletQuestionns);
	        stmt.executeBatch();	        
	        status = 1;
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
		
		return status;
	}

	
	public static int deleteQuestion(Integer quesid , Connection con )
	{

		String deletAnswer = "delete  from ANSWER where ANSWERID2QUESTIONID="+ quesid ;
		String deletOptions = "delete from OPTIONS where OPTIONID2QUESTIONID="+ quesid ;
		String deletQuestionns = "delete from QUESTION where QUESTIONID ="+ quesid;
		int status = 0;
		Statement stmt = null;	
		try
		{			
			stmt = con.createStatement();
			stmt.execute(deletAnswer);
			stmt.execute(deletOptions);
			stmt.execute(deletQuestionns);
			status = 1;
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
}
