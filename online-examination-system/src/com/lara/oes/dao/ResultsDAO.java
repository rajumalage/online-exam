package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.Marks;
import com.lara.oes.entity.Question2QuestionPaper;
import com.lara.oes.entity.QuestionPaper;

/**
 * @author Umesh Kumar
 *
 */
public class ResultsDAO
{

	public static Marks readMarks( int studentId, String QPaperId,ArrayList<Question2QuestionPaper> list,Connection con)
	{
		
		QuestionPaper qPaper= admin_QuestionPaper_dao.questionPaperInformation(QPaperId,con);
		int totalMarks=qPaper.getQuesPaperTotalMark();
		float passMarks=qPaper.getQuesPaperPassingMark();
		float marksPerQue=qPaper.getQuesPaperMarksPerQues();
		float negativeMarks=qPaper.getQuesPaperNagativeMark();
		
		int totalQAttempted=getAttempedQuestion(studentId, con);
		
		int totalCorrect=0;
		
		int status = -1;
		
		
		Statement stmt=null, stmt1=null;
		ResultSet rs= null,rs1=null;
		
		try
		{
			stmt= con.createStatement();
			stmt1= con.createStatement();
			for(int i=0;i<list.size();i++)
			{
				
				String sqlAnsTable="select answerid2optionsid from answer where answerid2questionid="+list.get(i);
				String sqlStuAnsTable="select stuOptionsId from studentAnswer where stuQuestionId="+list.get(i)+" and STUDENTANSWER2STUDENTID="+studentId;
				
				rs=stmt.executeQuery(sqlAnsTable);
			    rs1=stmt1.executeQuery(sqlStuAnsTable);
				
			    ArrayList ansOP= new ArrayList<>();
				ArrayList stuOP= new ArrayList<>();
				
				while(rs.next())
				{
					String answerid2optionsid=rs.getString("ANSWERID2OPTIONSID");
					ansOP.add(answerid2optionsid);
			
				}
				while(rs1.next())
				{
					String studentOptionId=rs1.getString("stuOptionsId");
					stuOP.add(studentOptionId);
				}
				if(ansOP.size()==stuOP.size())
				{
					if(ansOP.containsAll(stuOP))
					{
						totalCorrect++;
					}
				}
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
		Marks marks= new Marks();
		
		int wrongQue=(totalQAttempted-totalCorrect);
	
		float negativeScore=wrongQue*negativeMarks;
				
		float totalScore=marksPerQue*totalCorrect;//to add correct marks
				
		float obtaindmarks=totalScore-negativeScore;
		marks.setObtainedMarks(obtaindmarks);
			
		float pecentagemarks=(obtaindmarks*100)/totalMarks;
		marks.setPercentage(pecentagemarks);
		
		
		if(obtaindmarks>passMarks)
		{
			status=1;
		}	
		else
		{
			
			status=0;
		}
		marks.setStatus(status);
		System.out.println("PERCENtage: "+pecentagemarks);
		
		return marks;
	}
	
	public static int getAttempedQuestion(int studentId, Connection con)
	{
		String sqlAnsweredQuestions="select STUOPTIONSID from studentanswer where STUDENTANSWER2STUDENTID="+studentId;
		int totalQAttempted=0;
		
		Statement stmt=null;
		ResultSet rs=null ;
		try
		{
			stmt=con.createStatement();
			rs=stmt.executeQuery(sqlAnsweredQuestions);
			while(rs.next())
			{			
					totalQAttempted++ ;		
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
		return totalQAttempted;
	}
}
