package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.Student;



public class student_login_dao 
{
	public static List<Student> readLogin(Student log ,Connection con )
	{
		List<Student> studentDetails = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM STUDENT WHERE USERNAME = ");		
		sql.append("'" + log.getUsername() + "' and ");
		sql.append("PASSWORD = ");
		sql.append("'" + log.getPassword() + "'");
		Statement stmt = null;
		ResultSet rs = null;
		Student stu = null;
	//	System.out.println(sql.toString());
		try
		{
		 con = DbUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if(rs.next())
			{
				stu = new Student();
				stu.setStuId(Integer.parseInt(rs.getString("STUDENTID")));
				stu.setFirstName(rs.getString("FIRSTNAME"));
				stu.setLastName(rs.getString("LASTNAME"));
				stu.setUsername(rs.getString("USERNAME"));
				stu.setPassword(rs.getString("PASSWORD"));
				studentDetails.add(stu);
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
		return studentDetails;
	}
	
	
}
