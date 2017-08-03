package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.Results;
import com.lara.oes.entity.Student;
import com.lara.oes.entity.StudentAnswer;


public class StudentDAO 
{
	public static Results getStudentResult(int studentId,Connection con)
	{
		Results result = new Results();
		
		Statement stmt= null;
		ResultSet rs =null;
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM RESULTS WHERE RESULTID2STUDENTID = "+studentId);
			
			
			if(rs.next())
			{
				
				result.setResultStudentId(rs.getInt("resultStudentId"));
				result.setMarks(rs.getInt("marks"));
				
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return result;
	}
	public static Student getStudentDetail(int resultId2StudentId,Connection con)
	{
		Student student= new Student();;
		
		Statement stmt= null;
		ResultSet rs =null;
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from STUDENT where studentId="+resultId2StudentId);
			
			
			while(rs.next())
			{
				
				student.setStuId(rs.getInt("studentId"));
				student.setFirstName(rs.getString("firstName"));
				
				//System.out.println("dao"+rs.getInt("studentId"));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return student;
	}
	public static int readLogin(Student student , Connection con )
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM STUDENT WHERE USERNAME = ");		
		sql.append("'" + student.getUsername() + "' and ");
		sql.append("PASSWORD = ");
		sql.append("'" + student.getPassword() + "'");
		int status = 0;
	//	System.out.println(sql);
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next())
			{
				status = 1;
			}
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
