package com.lara.oes.student;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.student_Exam_Ques_dao;
import com.lara.oes.dao.student_InsertAnswer_dao;
import com.lara.oes.dao.student_login_dao;
import com.lara.oes.entity.Student;


/**
 * @author Sourav Barman
 *
 */
public class StudentLogin extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

   
    public StudentLogin() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = getConnection();
		doPerform(request,response,con);
	}
	protected void doPerform(HttpServletRequest request, HttpServletResponse response,Connection con) throws ServletException, IOException {

		String s1 = request.getParameter("username");
		String s2 = request.getParameter("password");
		System.out.println(s1);
		System.out.println(s2);
		Student log = new Student();
		log.setUsername(s1);
		log.setPassword(s2);	
		int studentId = 0;
		String StuFullName = null;
		List<Student> stuDetails = student_login_dao.readLogin(log, con);
		
		for (Student studetails : stuDetails) 
		{
			StuFullName = studetails.getFirstName() + " " + studetails.getLastName();
			studentId = studetails.getStuId();
		}		
			
		if(studentId != 0)
		{
			student_InsertAnswer_dao.deleteExesQuesAns(studentId, con); // existing QuesAns Deleted where studentId.
			student_Exam_Ques_dao.DeleteTempStoreQuesIdToDatabase(studentId, con);
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(50*60);			
	//		session.setAttribute("stuDetails", stuDetails);	
			session.setAttribute("UserFullName",StuFullName);
			session.setAttribute("StudentId",studentId);
			response.sendRedirect("SetChooseQuesPaper.htm");
		}
		else
		{	
			response.sendRedirect("setStudentLogin.htm?LoginStatus=0");
		}
	}
}
