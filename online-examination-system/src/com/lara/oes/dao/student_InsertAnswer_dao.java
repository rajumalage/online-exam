package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.StudentAnswer;


public class student_InsertAnswer_dao 
{
	public static void deleteExesQuesAns(int studentId,Connection con)
	{
		Statement stmt = null;
		ResultSet rs = null;;
		try
		{
			String sql = "DELETE FROM studentanswer WHERE studentanswer2STUDENTID="+studentId;
	//		System.out.println(sql);
			stmt = con.createStatement();
			stmt.executeQuery(sql);
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtil.closeAll(rs, null, null);
		}
	}
	
	
	/**
	 * Delete Old answer Insert new Answer
	 * @author Vinay Mittal
	 * @param quesId
	 * @param quesAns
	 * @param studentId
	 * @param con
	 */
	public static void InsertNewAndDeleteOldForUpdate(int quesId,String quesAns[],int studentId,Connection con)
	{
		Statement stmt = null;
		boolean flag=true;
		
		if(quesAns == null)
		{
			flag = false;
			try
			{

				stmt=con.createStatement();
				stmt.executeUpdate("delete from studentanswer where STUQUESTIONID= "+ quesId +" and studentanswer2STUDENTID="+studentId);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			flag=true;
		}
		if(flag)
		{
			int status = 0;
			String deleteAnswer = "delete from studentanswer where STUQUESTIONID= "+ quesId +" and studentanswer2STUDENTID="+studentId;

			try
			{
			//	con=Util.getConnection();
				stmt=con.createStatement();
				stmt.executeUpdate(deleteAnswer);
				status=1;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		
			try
			{
				//System.out.println("This is  my answer"+Arrays.toString(ans));
				for(int i=0;i<quesAns.length;i++)
				{
	//				
					StringBuffer sql = new StringBuffer();
					sql.append("insert into studentanswer (");
					sql.append("STUDENTANSWERID,STUQUESTIONID,STUOPTIONSID,studentanswer2STUDENTID)");
					sql.append(" values(studentanswer_SEQ.nextval,");
					sql.append(""+ quesId +","+ quesAns[i] +",");
					sql.append(""+ studentId +")");
				//	System.out.println(sql);
					stmt.executeUpdate(sql.toString());
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static List<StudentAnswer> displayAnsToStu(int questionId,int studentId,Connection con)
	{
		
		
		String sql="select * from studentanswer where STUQUESTIONID="+questionId +" and studentanswer2STUDENTID="+ studentId +" ORDER BY STUDENTANSWERID";
		
		List<StudentAnswer> StudentQuesOpesIdsAns = new ArrayList<StudentAnswer>();
		Statement stmt = null;
		ResultSet rs = null;;
		StudentAnswer StuAnsDetails = null;
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		//	System.out.println(sql);
			while(rs.next())
			{
				StuAnsDetails = new StudentAnswer();
				StuAnsDetails.setQuesId(rs.getInt("STUQUESTIONID"));
				StuAnsDetails.setOpesId(rs.getInt("STUOPTIONSID"));
				StudentQuesOpesIdsAns.add(StuAnsDetails);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtil.closeAll(rs, null, null);
		}
			
		
		return StudentQuesOpesIdsAns;
	}
	
}
