package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.ViewDAO;
import com.nt.dto.BookDTO;

@WebServlet("/viewservlet")
public class ViewBookServlet extends HttpServlet {
	ViewDAO dao;
	@Override
	public void init() throws ServletException {
		dao=new ViewDAO();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		try {
			List<BookDTO> al=dao.search();
			RequestDispatcher rd=req.getRequestDispatcher("Sample.jsp");
			rd.include(req, res);
			pw.println("<div class=\"tab\">Books Available In Our Store</div>");
			pw.println("<div class=\"tab\">\r\n" + 
					"		<table>\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>Book Code</th>\r\n" + 
					"				<th>Book Name</th>\r\n" + 
					"				<th>Book Author</th>\r\n" + 
					"				<th>Book Price</th>\r\n" + 
					"				<th>Quantity</th><br/>\r\n" + 
					"			</tr>");
				for(BookDTO dto:al) {
					pw.println("<tr><td>"+dto.getBookCode()+"</td>");
					pw.println("<td>"+dto.getBookName()+"</td>");
					pw.println("<td>"+dto.getAuthor()+"</td>");
					pw.println("<td>"+dto.getPrice()+"</td>");
					pw.println("<td>"+dto.getQuantity()+"</td></tr>");
				}	
				pw.println("</table>\r\n" + 
						"	</div>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
