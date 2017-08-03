package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.SubjectName;



/**
 * @author Rajib Ranjan
 * @author Sourav Barman
 *
 */
public class admin_Subject_dao 
{
	public static int insertSubject(SubjectName sub , Connection con )
	{
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO SUBJECT values(SUBJECT_SEQ.nextval,");		
		sql.append("'" + sub.getSubName().trim() + "',");
		sql.append("'" + sub.getSubDese().trim() + "')");
		int status = 0;
	//	System.out.println(sql);
		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			stmt.executeQuery(sql.toString());
			status=1;
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
	
	/**
	 * @author Sourav Barman
	 * @param con
	 * @return
	 */
	public static List<SubjectName> readAllSubject(Connection con)
	{
		List<SubjectName> subjects = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT SUBJECTID, NAME , DESCRIPTION FROM SUBJECT ORDER BY SUBJECTID");
			SubjectName sub = null;
			while( rs.next())
			{
				sub = new SubjectName();
				sub.setId(rs.getInt("SUBJECTID"));
				sub.setSubName(rs.getString("NAME").trim());
				sub.setSubDese(rs.getString("DESCRIPTION").trim());
				subjects.add(sub);
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
		return subjects;
	}
	
	public static List<SubjectName> readAllSubjectWSubId(String subId , Connection con)
	{
		List<SubjectName> SubjectNameDescs = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "SELECT SUBJECTID, NAME , DESCRIPTION FROM SUBJECT where SUBJECTID = " + subId;
	//		System.out.println(sql);
			rs = stmt.executeQuery(sql);
			SubjectName subNdesc = null;
			while( rs.next())
			{
				subNdesc = new SubjectName();
				subNdesc.setId(rs.getInt("SUBJECTID"));
				subNdesc.setSubName(rs.getString("NAME"));
				subNdesc.setSubDese(rs.getString("DESCRIPTION"));
				SubjectNameDescs.add(subNdesc);
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
		return SubjectNameDescs;
	}
	
	/**
	 * @author Rajib Ranjan
	 * @param sub
	 * @param con
	 * @return
	 */
	public static int updateSubject(SubjectName sub , Connection con )
	{
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("update SUBJECT set ");		
		sql.append("NAME ='" + sub.getSubName() + "',");
		sql.append("DESCRIPTION='" + sub.getSubDese() + "' where ");
		sql.append("SUBJECTID="+ sub.getId() +"");
		int status = 0;
	//	System.out.println(sql);
		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			status = stmt.executeUpdate(sql.toString());
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
	
	/**
	 * @author Sourav Barman
	 * @param SubId
	 * @param con
	 * @return
	 */
	public static int deleteSubject(String SubId , Connection con )
	{
		
		int subid = Integer.parseInt(SubId);
		String sql="DELETE FROM SUBJECT WHERE SUBJECTID="+ subid;
		int status = 0;
		
		Statement stmt = null;
		status = admin_Topic_dao.deleteTopicDsubject(SubId, con);
		System.out.println(status);
		if(status == 1)
		{
			try
			{
				status = 0;
				stmt = con.createStatement();
				status = stmt.executeUpdate(sql.toString());
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				DbUtil.closeAll(null, stmt, null);
			}
		}
		return status;
	}
	
	/**
	 * @author Umesh Kumar
	 * @param con
	 * @param keys
	 * @return
	 */
	public static List<SubjectName> searchSubject(Connection con,String keys)
	{
		String key=keys;
		String search[] = key.split(" ");
		List<SubjectName> subjects=new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from Subject ");
		sql.append("where upper(subjectid) like upper('%"+key+"%') ");
		
		for (String str : search) 
		{
			sql.append("or upper(name) like upper('%"+str+"%') ");
		}
		for (String str : search) 
		{
			sql.append("or upper(description) like upper('%"+str+"%') order by subjectid");
		}

		Statement stmt= null;
		ResultSet rs=null;
		int slno =0;
		try
		{
			stmt=con.createStatement();
//			System.out.println(sql);
			rs=stmt.executeQuery(sql.toString());
			SubjectName subject=null;
			while(rs.next())
			{
				subject=new SubjectName();
				subject.setId(rs.getInt("SubjectId"));
				subject.setSubName(rs.getString("name"));
				subject.setSubDese(rs.getString("description"));
				subjects.add(subject);
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
		return subjects;
	}
}
