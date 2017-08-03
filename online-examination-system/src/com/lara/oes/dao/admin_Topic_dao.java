package com.lara.oes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lara.oes.db.util.DbUtil;
import com.lara.oes.entity.TopicName;

/**
 * @author Sourav Barman
 *
 */
public class admin_Topic_dao
{
	
	public static List <TopicName> searchTopics(String key,Connection con)
	{
		List<TopicName> topics = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

			try
			{
				stmt = con.createStatement();
				
				StringBuffer sql = new StringBuffer();
				
				sql.append("SELECT T.TOPICID,T.DESCRIPTION,T.TOPICID2SUBJECTID, ");
				sql.append("T.NAME  ,S.SUBJECTID, S.NAME AS SUBNAME FROM TOPIC T ");
			//	sql.append("INNER JOIN TOPIC T ON Q.QUESTIONID2TOPICID = T.TOPICID ");
				sql.append("INNER JOIN SUBJECT S ON T.TOPICID2SUBJECTID = S.SUBJECTID ");
				sql.append("WHERE TOPICID IN (SELECT TOPICID FROM TOPIC ");
				sql.append("where upper(DESCRIPTION) like upper('%"+ key +"%')) order by T.TOPICID");			
				System.out.println(sql);
				rs = stmt.executeQuery(sql.toString());
				
				TopicName topic = null;

				while( rs.next())
				{
					topic = new TopicName();
					topic.setId(rs.getInt("TOPICID"));
					topic.setTopicDesc(rs.getString("DESCRIPTION"));
					topic.setTopicName(rs.getString("NAME"));
					topic.setSubName(rs.getString("SUBNAME"));
					topics.add(topic);
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
		
		return topics;
	}
	
	
	
	
	
	
	
	
	public static int insertTopic(TopicName topic, Connection con)
	{

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO Topic values(TOPIC_SEQ.nextval,");
		sql.append("'" + topic.getTopicName().trim() + "',");
		sql.append("'" + topic.getTopicDesc().trim() + "',");
		sql.append("'" + topic.getSubId() + "')");
		int status = 0;
//		System.out.println(sql);
		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			status = stmt.executeUpdate(sql.toString());
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		} finally
		{
			DbUtil.closeAll(null, stmt, null);
		}
		return status;
	}
	
	/**
	 * @author Sourav Barman
	 * @param topic
	 * @param con
	 * @return
	 */
	public static int updateTopic(TopicName topic , Connection con )
	{
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TOPIC set ");		
		sql.append("NAME ='" + topic.getTopicName().trim() + "',");
		sql.append("DESCRIPTION='" + topic.getTopicDesc().trim() + "' where ");
		sql.append("TOPICID="+ topic.getId() +"");
		int status = 0;
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
	 * @author Rohit Yadav
	 * @param con
	 * @param topicId
	 * @return
	 */
	public static int deleteTopic(Connection con , int topicId){
		int status=0;
		try
		{
			PreparedStatement ps=con.prepareStatement("DELETE FROM ANSWER WHERE ANSWERID2QUESTIONID IN(SELECT QUESTIONID FROM QUESTION WHERE QUESTIONID2TOPICID=?)");
			ps.setInt(1,topicId);
			status=ps.executeUpdate();
			
			PreparedStatement ps1=con.prepareStatement("DELETE FROM OPTIONS WHERE OPTIONSID2QUESTIONID IN(SELECT QUESTIONID FROM QUESTION WHERE QUESTIONID2TOPICID=?)");
			ps1.setInt(1,topicId);
			status=ps1.executeUpdate();
			
			PreparedStatement ps2=con.prepareStatement("DELETE FROM QUESTION WHERE QUESTIONID2TOPICID=?");
			ps2.setInt(1,topicId);
			status=ps2.executeUpdate();
			
			PreparedStatement ps3=con.prepareStatement("DELETE FROM TOPIC WHERE TOPICID=?");
			ps3.setInt(1,topicId);
			status=ps3.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static List<TopicName> readAllTipoc(Connection con)
	{
		List<TopicName> topics = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select id, topic_name , topic_desc from online_topic";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			TopicName topic = null;
			while (rs.next())
			{
				topic = new TopicName();
				topic.setId(rs.getInt("id"));
				topic.setTopicName(rs.getString("topic_name"));
				topic.setTopicDesc(rs.getString("topic_desc"));
				topics.add(topic);
			}
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		} finally
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return topics;
	}
	
	public static List<TopicName> readTopicNameAndDesc(String topicId, Connection con)
	{
		List<TopicName> topics = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select * from TOPIC where TOPICID = "+ topicId;
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			TopicName topic = null;
			while (rs.next())
			{
				topic = new TopicName();
				topic.setId(rs.getInt("TOPICID"));
				topic.setTopicName(rs.getString("NAME").trim());
				topic.setTopicDesc(rs.getString("DESCRIPTION").trim());
				topics.add(topic);
			}
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		} finally
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return topics;
	}

	/**
	 * @author Sourav Barman
	 * @param SUBJECTID
	 * @param key
	 * @param con
	 * @return
	 */
	public static List<TopicName> searchAllTipocWSubName(String SUBJECTID , String key, Connection con)
	{
		List<TopicName> topicsubNames = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		int SUBJEID ;
		if(SUBJECTID == null || SUBJECTID == "")
		{
			SUBJEID = 0 ;
		}
		else
		{
			SUBJEID = Integer.parseInt(SUBJECTID);
		}		
		try
		{
			if(SUBJEID == 0 || SUBJECTID == null || SUBJECTID == "" || SUBJECTID == " ")
			{
				try
				{	
					stmt = con.createStatement();
					StringBuffer sql = new StringBuffer();
					sql.append("SELECT T.TOPICID, T.NAME,T.DESCRIPTION,T.TOPICID2SUBJECTID, S.NAME AS SUBNAME ");
					sql.append("FROM TOPIC T Inner JOIN SUBJECT S ON T.TOPICID2SUBJECTID = S.SUBJECTID ");
					sql.append("WHERE TOPICID IN ");
					sql.append("(SELECT TOPICID FROM TOPIC");
					sql.append(" where upper(NAME) like upper('%" + key + "%')");
					sql.append(" or upper(DESCRIPTION) like upper('%" + key + "%')");
					sql.append(") order by T.TOPICID");						
			//		System.out.println(sql);
					 rs = stmt.executeQuery(sql.toString());
					TopicName topic = null;
					while (rs.next())
					{
						topic = new TopicName();
						topic.setId(rs.getInt("TOPICID"));
						topic.setTopicName(rs.getString("NAME"));
						topic.setTopicDesc(rs.getString("DESCRIPTION"));
						topic.setSubName(rs.getString("SUBNAME"));
						topicsubNames.add(topic);
					} 
				}
				catch (SQLException ex)
				{
					ex.printStackTrace();
				} 
			}
			else
			{
				try
				{
					stmt = con.createStatement();
					StringBuffer sql = new StringBuffer();
					sql.append("SELECT T.TOPICID, T.NAME,T.DESCRIPTION,T.TOPICID2SUBJECTID, S.NAME AS SUBNAME ");
					sql.append("FROM TOPIC T Inner JOIN SUBJECT S ON T.TOPICID2SUBJECTID = S.SUBJECTID ");
					sql.append("WHERE TOPICID IN ");
					sql.append("(SELECT TOPICID FROM TOPIC");
					sql.append(" where (upper(NAME) like upper('%" + key + "%')");
					sql.append(" or upper(DESCRIPTION) like upper('%" + key + "%'))");
					sql.append(" AND TOPICID2SUBJECTID = "+ SUBJECTID +" ) order by T.TOPICID");
 	 //			    System.out.println(sql);
					rs = stmt.executeQuery(sql.toString());
					TopicName topic = null;
					int SLNO = 0;
					while (rs.next())
					{
						topic = new TopicName();
						topic.setId(++SLNO);
						topic.setTopicName(rs.getString("NAME"));
						topic.setTopicDesc(rs.getString("DESCRIPTION"));
						topic.setSubName(rs.getString("SUBNAME"));
						topicsubNames.add(topic);
					} 
				}
				catch (SQLException ex)
				{
					ex.printStackTrace();
				} 
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		} 
		finally
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		
		return topicsubNames;
	}

	public static List<TopicName> readSubTopic(String subId, Connection con)
	{
		List<TopicName> topics = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = con.createStatement();
			String sql = "select TOPICID, NAME , DESCRIPTION from TOPIC where TOPICID2SUBJECTID = "+ subId;
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			TopicName topic = null;
			while (rs.next())
			{
				topic = new TopicName();
				topic.setId(rs.getInt("TOPICID"));
				topic.setTopicName(rs.getString("NAME").trim());
				topic.setTopicDesc(rs.getString("DESCRIPTION").trim());
				topics.add(topic);
			}
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		} finally
		{
			DbUtil.closeAll(rs, stmt, null);
		}
		return topics;
	}

	/**
	 * @author Sourav Barman
	 * @author Rajib Ranjan
	 * @param SubId
	 * @param con
	 * @return
	 */
	@SuppressWarnings("resource")
	public static int deleteTopicDsubject(String SubId, Connection con)
	{

		int subid = Integer.parseInt(SubId);
		
		String sql = "DELETE FROM TOPIC WHERE TOPICID2SUBJECTID=" + subid;
		String TpicBySubject = "SELECT TOPICID FROM TOPIC WHERE TOPICID2SUBJECTID="+ subid;
		
		int status = 0, QuestionDeleteStatus = 0;
		ResultSet rs = null, rs1 = null;
		Statement stmt = null, stmt1 = null, stmt2 = null;
		
		ArrayList<Integer> TopicID = new ArrayList<Integer>();
				
		StringBuffer QuestionIdByTopic = new StringBuffer();
		try
		{		
			try
			{
				stmt1 = con.createStatement();
				rs = stmt1.executeQuery(TpicBySubject);
				while (rs.next())
				{
					TopicID.add(rs.getInt("TOPICID"));
				}
			} 
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
			
			ArrayList<Integer> QuestionID = new ArrayList<Integer>();

			if (TopicID.size() != 0)
			{
				QuestionIdByTopic.append("SELECT QUESTIONID FROM QUESTION WHERE QUESTIONID2TOPICID in(");
				for (int i = 0; i < TopicID.size(); i++)
				{
					QuestionIdByTopic.append(TopicID.get(i));
					
					if (i != TopicID.size() - 1)
					{
						QuestionIdByTopic.append(",");
					} else
					{
						QuestionIdByTopic.append(")");
					}
				}
			
				
				try
				{
					stmt2 = con.createStatement();
					rs1 = stmt2.executeQuery(QuestionIdByTopic.toString());
					while (rs1.next())
					{
						QuestionID.add(rs1.getInt("QUESTIONID"));
					}
				} 
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
	
			if (QuestionID.size() != 0)
			{
				for (Integer QuestionId : QuestionID)
				{
					QuestionDeleteStatus = admin_Question_dao.deleteQuestion(QuestionId, con);
				}
			}
			if(QuestionDeleteStatus == 1)
			{
				
				try 
				{ 
					status = 0; 
					stmt = con.createStatement(); 
					stmt.executeUpdate(sql); 
		//			System.out.println(sql);
					status = 1;
				} 
				catch (SQLException ex) 
				{
					ex.printStackTrace(); 
				} 
			}
			else
			{
				try 
				{ 
					status = 0; 
					stmt = con.createStatement(); 
					stmt.executeUpdate(sql); 
	//				System.out.println(sql);
					status = 1;
				} 
				catch (SQLException ex) 
				{
					ex.printStackTrace(); 
				} 
			}
		}
		catch(Exception sqlex)
		{
			sqlex.printStackTrace();
		}
		finally 
		{ 
			DbUtil.closeStmt(stmt2);
			DbUtil.closeStmt(stmt1);
			DbUtil.closeStmt(stmt);
		} 
		return status;
	}
}

// if(TopicID.size() != 0)
// {
// for (Integer tipicid : TopicID) {
// QuestionIdByTopic= "SELECT ID FROM ONLINE_QUESTION WHERE Topic_id="+tipicid;
// try
// {
// stmt2=con.createStatement();
// rs1 = stmt2.executeQuery(QuestionIdByTopic);
// while(rs1.next())
// {
// QuestionID.add(rs1.getInt("id"));
// }
// }
// catch(Exception ex)
// {
// ex.printStackTrace();
// }
// }
// }
// System.out.println(TopicID);

