package com.lara.oes.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lara.oes.connnection.OesHttpServlet;
import com.lara.oes.dao.admin_Subject_dao;
import com.lara.oes.entity.SubjectName;

/**
 * @author Rajib Ranjan
 * @author Sourav Barman
 *
 */
public class UpdateSubjectFinal extends OesHttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateSubjectFinal() {
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = getConnection();
		
			response.setContentType("text/html");
		  PrintWriter out=response.getWriter();
		  String SubId= request.getParameter("SubJectID");
		  String SubName= request.getParameter("SubName");
		  String SubDesc= request.getParameter("SubDesc");
		  
		  if(Integer.parseInt(SubId) == 0 || SubName == null || SubName == "")
		  {
			  out.println("Please Select Existing Subject Name");
		  }
		  else
		  {			  
			  SubjectName sub = new SubjectName();
			  sub.setId(Integer.parseInt(SubId));
			  sub.setSubName(SubName.trim());
			  sub.setSubDese(SubDesc.trim());
			  
			  int status = 0;
			  
			  status = admin_Subject_dao.updateSubject(sub, con);
			  
			  if(status == 1)
			  {
				  out.println("<pre style='color:green;font-size:14px;'>Topic SuccessFully Updated</pre>");
			  }
			  else
			  {
				  out.println("<pre style='color:red;font-size:14px;'>Topic Not Updated</pre>");
			  }
		  }
	}

}
