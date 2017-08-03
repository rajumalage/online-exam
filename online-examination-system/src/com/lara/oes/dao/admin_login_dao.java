package com.lara.oes.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.Admin;


public class admin_login_dao 
{
	/**
	 * @author Sourav Barman
	 * @param log
	 * @param con
	 * @return
	 */
	public static int readLogin(Admin log , Connection con )
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ADMIN WHERE USERNAME = ");		
		sql.append("'" + log.getUsername() + "' and ");
		sql.append("PASSWORD = ");
		sql.append("'" + log.getPassword() + "'");
		int status = 0;
	//	Connection con =null;
	//	System.out.println(sql);
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			// con = DbUtil.getConnection();
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
