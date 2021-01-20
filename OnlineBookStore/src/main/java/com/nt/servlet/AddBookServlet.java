package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.BookDAO;
import com.nt.dto.BookDTO;

@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
	BookDAO dao;
	@Override
	public void init() throws ServletException {
	dao=new BookDAO();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String bookCode=null;
		String bookName=null;
		String Author=null;
		float price=0.0f;
		int quantity=0;
		BookDTO dto=null;
		//get printwriter obj
		pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//get req param
		bookCode=req.getParameter("barcode");bookName=req.getParameter("name");
		Author=req.getParameter("author");price=Float.parseFloat(req.getParameter("price"));
		quantity=Integer.parseInt(req.getParameter("quantity"));
		dto=new BookDTO();
		dto.setAuthor(Author);dto.setBookCode(bookCode);dto.setBookName(bookName);dto.setPrice(price);dto.setQuantity(quantity);
		try {
			if(dao.addBook(dto)) {
				RequestDispatcher rd = req.getRequestDispatcher("AddBook.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Detail Updated Successfully!<br/>Add More Books</div>");
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("AddBook.html");
				pw.println("<div class=\"tab\">Failed to Add Books! Fill up CareFully</div>");
				rd.include(req, res);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
