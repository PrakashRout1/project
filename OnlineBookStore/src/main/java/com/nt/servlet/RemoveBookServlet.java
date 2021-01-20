package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.RemoveBookDAO;

@WebServlet("/removebook")
public class RemoveBookServlet extends HttpServlet {
	RemoveBookDAO dao;
	@Override
	public void init() throws ServletException {
		dao=new RemoveBookDAO();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String bookcode=null;
		//get printwriter obj
		pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		bookcode=req.getParameter("barcode");
		try {
			if(dao.remove(bookcode)) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.jsp");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Removed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveBook.html\">Remove more Books</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.jsp");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Not Available In The Store</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveBook.html\">Remove more Books</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
