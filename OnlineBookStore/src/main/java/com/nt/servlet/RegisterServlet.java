package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.RegisterDAO;
import com.nt.dto.RegisterDTO;

@WebServlet("/userreg")
public class RegisterServlet extends HttpServlet {
	RegisterDAO dao=null;	
	@Override
		public void init() throws ServletException {
		dao=new RegisterDAO();
		}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String username=null;
		String password=null;
		String fName=null;
		String lName=null;
		String addr=null;
		int phNo=0;
		String mailId=null;
		RegisterDTO dto=null;
		//get printwriter obj
		pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//get req param
		username=req.getParameter("username");password=req.getParameter("password");
		fName=req.getParameter("firstname");lName=req.getParameter("lastname");
		addr=req.getParameter("address");phNo=Integer.parseInt(req.getParameter("phone"));mailId=req.getParameter("mailid");
		dto=new RegisterDTO();
		dto.setUsername(username);dto.setAddr(addr);dto.setfName(fName);dto.setlName(lName);dto.setMailId(mailId);
		dto.setPassword(password);dto.setPhNo(phNo);
		
		try {
			if(dao.register(dto)) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.jsp");
				rd.include(req, res);
				pw.println("<h3 class='tab'>User Registered Successfully</h3>");
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("userreg");
				rd.include(req, res);
				pw.println("Sorry for interruption! Register again");

			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
