package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.QuestionName;
import com.lara.oes.entity.QuestionPaper;

/**
 * @author Sourav Barman
 *
 */
public class admin_QuestionPaper_dao 
{
	
	/**
	 * @author Rahul
	 * @param QPaperId
	 * @param con
	 * @return
	 */
	public static QuestionPaper questionPaperInformation(String QPaperId,Connection con)
	{
		QuestionPaper qpaper=new QuestionPaper();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from QUESTIONPAPER where QUESTIONPAPERID= "+QPaperId;
		
		try
		{
			
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				qpaper.setQuesPaperId(rs.getInt("QUESTIONPAPERID"));
				qpaper.setQuesPaperDesc(rs.getString("DESCRIPTION"));
				qpaper.setQuesPaperDuration(rs.getFloat("ALLOTEDTIME"));
				qpaper.setNoOfQues(rs.getInt("NUMBEROFQUESTIONS"));
				qpaper.setQuesPaperMarksPerQues(rs.getFloat("MARKSPERQUESTION"));
				qpaper.setQuesPaperTotalMark(rs.getInt("TOTALMARKS"));
				qpaper.setQuesPaperPassingMark(rs.getFloat("PASSINGMARKS"));
				qpaper.setQuesPaperNagativeMark(rs.getFloat("MARKSFORNEGATIVE"));
				qpaper.setQuesPaperInfo(rs.getString("QUESTIONPAPERINFO"));
				
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		finally {
			DbUtil.closeAll(rs, stmt, null);
		}
		
	return qpaper;
		
	}

	
	/**
	 * @author Sourav Barman
	 * @author Rahul
	 * @param paper
	 * @param con
	 * @return
	 */
	public static List<QuestionPaper> CreateQuestionPaper(QuestionPaper paper,Connection con)
	{

		 List<QuestionPaper> quesPaperDescs = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO QUESTIONPAPER ");
		sql.append("(QUESTIONPAPERID,DESCRIPTION,");
		sql.append("ALLOTEDTIME,NUMBEROFQUESTIONS,");
		sql.append("MARKSPERQUESTION,TOTALMARKS,");
		sql.append("PASSINGMARKS,MARKSFORNEGATIVE,");
		sql.append("QUESTIONPAPERINFO,STATUS) ");
		sql.append("values(QUESTIONPAPER_SEQ.nextval,");
		sql.append("'"+paper.getQuesPaperDesc()+"',");
		sql.append(paper.getQuesPaperDuration()+",");
		sql.append(paper.getNoOfQues()+",");
		sql.append(paper.getQuesPaperMarksPerQues()+",");
		sql.append(paper.getQuesPaperTotalMark()+",");
		sql.append(paper.getQuesPaperPassingMark()+",");
		sql.append(paper.getQuesPaperNagativeMark()+",");
		sql.append("'"+paper.getQuesPaperInfo()+"',");
		sql.append("'0')");

		int status =0;
		
		Statement stmt = null,stmt1 = null;
		ResultSet rs = null;
		try
		{
	     	stmt = con.createStatement();
			stmt.executeUpdate(sql.toString(),Statement.RETURN_GENERATED_KEYS);
			status = 1;
			rs=stmt.getGeneratedKeys();
			String rowid = null;
			if(rs.next())
			{
				rowid = rs.getString(1);
			}
			status = -1;
			String sql2 = "SELECT * FROM QUESTIONPAPER where rowid='"+ rowid +"'";
			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(sql2);
			status = 1;
			QuestionPaper quesPaperDesc = null;
			if(status == 1)
			{
				if(rs.next())
				{
					quesPaperDesc = new QuestionPaper();
					quesPaperDesc.setQuesPaperId(Integer.parseInt(rs.getString("QUESTIONPAPERID")));
					quesPaperDesc.setNoOfQues(Integer.parseInt(rs.getString("NUMBEROFQUESTIONS")));
					quesPaperDescs.add(quesPaperDesc);
				}
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
		return quesPaperDescs;	
	}
	
	/**
	 * @author Sourav Barman
	 * @author Rahul
	 * @param con
	 * @return
	 */
	public static List<QuestionPaper> questionPaperInformation(Connection con)
	{
		List<QuestionPaper> quespapers= new ArrayList<QuestionPaper>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM QUESTIONPAPER ORDER BY QUESTIONPAPERID";
		
		try
		{
			QuestionPaper qpaper=null;
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				qpaper=new QuestionPaper();
				
				qpaper.setQuesPaperId(rs.getInt("QUESTIONPAPERID"));
				qpaper.setQuesPaperDesc(rs.getString("DESCRIPTION"));
				qpaper.setQuesPaperDuration(rs.getFloat("ALLOTEDTIME"));
				qpaper.setNoOfQues(rs.getInt("NUMBEROFQUESTIONS"));
				qpaper.setQuesPaperMarksPerQues(rs.getFloat("MARKSPERQUESTION"));
				qpaper.setQuesPaperTotalMark(rs.getInt("TOTALMARKS"));
				qpaper.setQuesPaperPassingMark(rs.getFloat("PASSINGMARKS"));
				qpaper.setQuesPaperNagativeMark(rs.getFloat("MARKSFORNEGATIVE"));
				qpaper.setQuesPaperInfo(rs.getString("QUESTIONPAPERINFO"));
				qpaper.setStatus(Integer.parseInt(rs.getString("STATUS")));
				quespapers.add(qpaper);
			}
			}catch (SQLException e) 
		{
				e.printStackTrace();
			}
			
		finally {
			DbUtil.closeAll(rs, stmt, null);
		}
		
	return quespapers;
		
	}
	
	/**
	 * @author Rahul
	 * @param quesPaperId
	 * @param con
	 * @return
	 */
	public static List<QuestionPaper> questionPaperInformation(int quesPaperId ,Connection con)
	{
		List<QuestionPaper> quespapers= new ArrayList<QuestionPaper>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from QUESTIONPAPER where QUESTIONPAPERID="+quesPaperId;
		
		try
		{
			QuestionPaper qpaper=null;
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				qpaper=new QuestionPaper();
				
				qpaper.setQuesPaperId(rs.getInt("QUESTIONPAPERID"));
				qpaper.setQuesPaperDesc(rs.getString("DESCRIPTION"));
				qpaper.setQuesPaperDuration(rs.getFloat("ALLOTEDTIME"));
				qpaper.setNoOfQues(rs.getInt("NUMBEROFQUESTIONS"));
				qpaper.setQuesPaperMarksPerQues(rs.getFloat("MARKSPERQUESTION"));
				qpaper.setQuesPaperTotalMark(rs.getInt("TOTALMARKS"));
				qpaper.setQuesPaperPassingMark(rs.getFloat("PASSINGMARKS"));
				qpaper.setQuesPaperNagativeMark(rs.getFloat("MARKSFORNEGATIVE"));
				qpaper.setQuesPaperInfo(rs.getString("QUESTIONPAPERINFO"));
				quespapers.add(qpaper);
			}
			}catch (SQLException e){
				e.printStackTrace();

			}
			
		finally 
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		
		return quespapers;
	}
	
	/**
	 * @author Sourav Barman
	 * @param con
	 * @return
	 */
	public static List<QuestionPaper> quesPaperStatus(Connection con)
	{
		List<QuestionPaper> quespapers= new ArrayList<QuestionPaper>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from QUESTIONPAPER where Status=1";
		
		try
		{
			QuestionPaper qpaper=null;
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				qpaper=new QuestionPaper();
				
				qpaper.setQuesPaperId(rs.getInt("QUESTIONPAPERID"));
				qpaper.setQuesPaperDesc(rs.getString("DESCRIPTION"));
				qpaper.setQuesPaperDuration(rs.getFloat("ALLOTEDTIME"));
				qpaper.setNoOfQues(rs.getInt("NUMBEROFQUESTIONS"));
				qpaper.setQuesPaperMarksPerQues(rs.getFloat("MARKSPERQUESTION"));
				qpaper.setQuesPaperTotalMark(rs.getInt("TOTALMARKS"));
				qpaper.setQuesPaperPassingMark(rs.getFloat("PASSINGMARKS"));
				qpaper.setQuesPaperNagativeMark(rs.getFloat("MARKSFORNEGATIVE"));
				qpaper.setQuesPaperInfo(rs.getString("QUESTIONPAPERINFO"));
				quespapers.add(qpaper);
			}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				
			}
			
		finally 
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		
		return quespapers;
	}
	
	/**
	 * @author Sourav Barman
	 * @author Rahul
	 * @param quesPaperId
	 * @param con
	 * @return
	 */
	public static List<QuestionPaper> quesPaperFullInfoDQuesPaperId(String quesPaperId,Connection con)
	{
		List<QuestionPaper> quespapers= new ArrayList<QuestionPaper>();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from QUESTIONPAPER where QUESTIONPAPERID=" + quesPaperId;
		
		try
		{
			QuestionPaper qpaper=null;
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				qpaper=new QuestionPaper();
				
				qpaper.setQuesPaperId(rs.getInt("QUESTIONPAPERID"));
				qpaper.setQuesPaperDesc(rs.getString("DESCRIPTION"));
				qpaper.setQuesPaperDuration(rs.getFloat("ALLOTEDTIME"));
				qpaper.setNoOfQues(rs.getInt("NUMBEROFQUESTIONS"));
				qpaper.setQuesPaperMarksPerQues(rs.getFloat("MARKSPERQUESTION"));
				qpaper.setQuesPaperTotalMark(rs.getInt("TOTALMARKS"));
				qpaper.setQuesPaperPassingMark(rs.getFloat("PASSINGMARKS"));
				qpaper.setQuesPaperNagativeMark(rs.getFloat("MARKSFORNEGATIVE"));
				qpaper.setQuesPaperInfo(rs.getString("QUESTIONPAPERINFO"));
				quespapers.add(qpaper);
			}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				
			}
			
		finally 
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		
		return quespapers;
	}
	
	/**
	 * @author Sourav Barman
	 * @param quesids
	 * @param quesPaperId
	 * @param con
	 * @return
	 */
	public static int InserQuesIDIntoQuesPaper(String[] quesids ,String quesPaperId, Connection con )
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
				stmt.executeQuery(InsertQuesId);				
			}
	        
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
	
	/**
	 * @author Sourav Barman
	 * @param QuesPaperId
	 * @param con
	 * @return
	 */
	public static int deleteQuestionPaper(int QuesPaperId, Connection con)
	{
		Statement stmt= null;
		int status = 0;
			String sql="delete from QUESTION2QUESTIONPAPER where THISID2QUESTIONPAPERID="+QuesPaperId;
			String sql1="delete from QUESTIONPAPER WHERE QUESTIONPAPERID="+QuesPaperId;
		try
		{
			stmt=con.createStatement();
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			status=1;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return status;
	}
	
	/**
	 * @author Sourav Barman
	 * @param quesPaperId
	 * @param con
	 * @return
	 */
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
	
	/**
	 * @author Rahul
	 * @param quesId
	 * @param con
	 * @return
	 */
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
	 * @author Rahul
	 * @param status
	 * @param paperId
	 * @param con
	 * @return
	 */
	public static int UpdateQuestionPaperStatus(int status,int paperId, Connection con)
	{
		Statement stmt=null;
		int UpdateStatus = 0;
		String sql="update QUESTIONPAPER set STATUS="+status+" where QUESTIONPAPERID="+paperId;
		try
		{
			stmt=con.createStatement();
			UpdateStatus = stmt.executeUpdate(sql);		
		}
	
	catch(SQLException e)
	{
		e.printStackTrace();
		
	}	
	return UpdateStatus;
	
	}
	
}