package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.TempQuesStatus;


/**
 * @author Sourav Barman
 *
 */
/**
 * @author Sourav Barman
 *
 */

public class student_Exam_Ques_dao 
{
	/**
	 * Delete Student Answer form database
	 * @author Sourav Barman
	 * @param studentId
	 * @param con
	 */
	public static void deleteExesQuesAns(int studentId,Connection con)
	{
		Statement stmt = null;
		try
		{
			String sql = "DELETE * FROM studentanswer WHERE studentanswer2STUDENTID="+studentId;
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		//	System.out.println(sql);
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtil.closeAll(null, stmt, null);
		}
	}
	
	/**
	 * @param studentId
	 * @param AllQuesIds
	 * @param con
	 */
	public static void TempStoreQuesIdToDatabase(int studentId,ArrayList<Integer> AllQuesIds,Connection con)
	{
		Statement stmt = null;	
		try
		{
			stmt = con.createStatement();

			for(int i=0;i<AllQuesIds.size();i++)
			{
				StringBuffer tempQuesId = new StringBuffer();
				tempQuesId.append("INSERT INTO TEMP_QUESIDSTATUS_COUNT (");
				tempQuesId.append("QUESIDSTATUSID,QUESIDS,STATUS,STUDENTID)");
				tempQuesId.append(" VALUES(TEMP_QUESIDSTATUS_SEQ.nextval,");
				tempQuesId.append(""+ AllQuesIds.get(i) +",1,");
				tempQuesId.append(""+ studentId +")");

				stmt.executeUpdate(tempQuesId.toString());
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtil.closeAll(null, stmt, null);
		}
	}
	
	public static int DeleteTempStoreQuesIdToDatabase(int studentId,Connection con)
	{
		Statement stmt= null;
		int status = 0;
			String sql="DELETE FROM TEMP_QUESIDSTATUS_COUNT WHERE STUDENTID="+studentId;
		try
		{
			stmt=con.createStatement();
			stmt.executeUpdate(sql);
			status = 1;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return status;
	}
	
	/**
	 * Update question Update
	 * @author Sourav Barman
	 * @param status
	 * @param quesId
	 * @param studentId
	 * @param con
	 * @return
	 */
	public static int TempUpdateQuesIdStatus(int status,int quesId,int studentId,Connection con)
	{
		
		StringBuffer sql = new StringBuffer();
		sql.append("Update temp_quesidstatus_count ");		
		sql.append("SET STATUS=" + status + " WHERE");
		sql.append(" quesids=" + quesId + " AND");
		sql.append(" studentid="+studentId+"");
		int chkExecute = 0;
		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			stmt.executeUpdate(sql.toString());
			chkExecute = 1;
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(null, stmt, null);
		}
		return chkExecute;
	}
	
	/**
	 * @author Sourav Barman
	 * @author rahul
	 * @param studentId
	 * @param con
	 * @return
	 */
	public static List<TempQuesStatus> getQuestionIds(int studentId,Connection con)
	{
		
		String sql="select * from TEMP_QUESIDSTATUS_COUNT where studentid="+ studentId +" order by quesidstatusid";

		List<TempQuesStatus> questionids = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;;
		TempQuesStatus quesid = null;
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				quesid = new TempQuesStatus();
				quesid.setQuesId(rs.getInt("QUESIDS"));
				quesid.setStatus(rs.getInt("STATUS"));
				quesid.setStudentId(rs.getInt("STUDENTID"));
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
	
	/**
	 * @author Sourav Barman
	 * @param studentId
	 * @param querry
	 * @param con
	 * @return
	 */
	public static int getQuestionIds(int studentId,int querry, Connection con)
	{
		
		String sql="select * from TEMP_QUESIDSTATUS_COUNT where studentid="+ studentId +" and status="+querry;
		int count=0;
		
		Statement stmt = null;
		ResultSet rs = null;;
		
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				count++;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return count;
		
	}
}
