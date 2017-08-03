package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lara.oes.entity.ShowStatusOnDash;

public class ShowStatusOnStatusBar {

	public static  ShowStatusOnDash showStatus(Connection con)
	{
		int subjectCount=0,topicCount=0,questionCount=0,questionPaperCount=0;
		int activePaper=0;
		ShowStatusOnDash show=new ShowStatusOnDash();
		String sql1="select count(SUBJECTID) from subject";
		String sql2="select count(TOPICID) from topic";
		String sql3="select count(questionid) from question";
		String sql4="select count(questionpaperid) from questionpaper";
		String sql5="select count(status) from questionpaper where status=1";
		
		try
		{
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery(sql1);
			if(rs.next())
			{
				subjectCount=rs.getInt(1);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery(sql2);
			if(rs.next())
			{
				topicCount=rs.getInt(1);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery(sql3);
			if(rs.next())
			{
				questionCount=rs.getInt(1);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery(sql4);
			if(rs.next())
			{
				questionPaperCount=rs.getInt(1);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery(sql5);
			if(rs.next())
			{
				activePaper=rs.getInt(1);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		show.setNoOfSubjects(subjectCount);
		show.setNoOfTopics(topicCount);
		show.setNoOfQuestions(questionCount);
		show.setNoOfQuestioPaper(questionPaperCount);
		show.setNoOfActiveQuestionPaper(activePaper);
		return show;
	}
}
