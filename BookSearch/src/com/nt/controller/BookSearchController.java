package com.nt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.BookDTO;
import com.nt.service.BookSearchService;
import com.nt.service.BookSearchServiceImpl;

@WebServlet("/controller")
public class BookSearchController extends HttpServlet {
	BookSearchService service;
	@Override
	public void init() throws ServletException {
		service=new BookSearchServiceImpl();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String category=null,source=null;
		List<BookDTO> listDTO=null;
		RequestDispatcher rd=null;
		//read form data
		category=req.getParameter("category");
		source=req.getParameter("source");
		try {
			listDTO=service.search(category);
			req.setAttribute("booksList",listDTO);
			if(source.equalsIgnoreCase("html")) {
				rd=req.getRequestDispatcher("/html_screen.jsp");
			}
			else {
				rd=req.getRequestDispatcher("/excel_screen.jsp");
			}
			rd.forward(req,res);
		}
		catch(Exception e) {
			rd=req.getRequestDispatcher("/error.jsp");
			rd.forward(req,res);
			return;
		}
		//keep listdto in req attribute
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
