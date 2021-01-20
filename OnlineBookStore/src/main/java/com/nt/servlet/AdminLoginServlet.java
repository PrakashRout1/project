package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.AdminDAO;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/adminlog")
public class AdminLoginServlet extends HttpServlet {
	AdminDAO dao;
	@Override
		public void init() throws ServletException {
			dao=new AdminDAO();
		}
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw=null;
			String username=null,password=null;
			//get printwriter obj
			pw=res.getWriter();
			//set content type
			res.setContentType("text/html");
			username=req.getParameter("username");
			password=req.getParameter("password");
			try {
				if(dao.check(username, password)) {
					RequestDispatcher rd=req.getRequestDispatcher("Sample.jsp");
					Cookie ck = new Cookie("usertype","admin");
					rd.include(req, res);
					
					pw.println("<div class=\"tab\">Admin login Successful</div>");
					pw.println("<div class=\"tab\"><br/><a href=\"AddBook.html\">ADD BOOKS</a><br/></div>");
					pw.println("<div class=\"tab\"><br/><a href=\"RemoveBook.html\">REMOVE BOOKS</a><br/></div>");
					pw.println("<div class=\"tab\"><br/><a href=\"viewbook\">VIEW BOOKS</a></div>");
				} else {

					RequestDispatcher rd = req.getRequestDispatcher("admin.html");
					rd.include(req, res);
					pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
				}

			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
		

}
