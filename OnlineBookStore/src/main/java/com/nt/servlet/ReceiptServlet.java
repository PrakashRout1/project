package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.ViewDAO;
import com.nt.dto.BookDTO;

/**
 * Servlet implementation class ReceiptServlet
 */
@WebServlet("/receipt")
public class ReceiptServlet extends HttpServlet {
	ViewDAO dao;
	@Override
	public void init() throws ServletException {
		dao=new ViewDAO();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			RequestDispatcher rd = req.getRequestDispatcher("Sample.jsp");
			rd.include(req, res);
			pw.println("<div class=\"tab\">You Successfully Paid for Following Books</div>");
			pw.println(
					"<div class=\"tab\">\r\n" + "		<table>\r\n" + "			<tr>\r\n" + "				\r\n"
							+ "				<th>Book Code</th>\r\n" + "				<th>Book Name</th>\r\n"
							+ "				<th>Book Author</th>\r\n" + "				<th>Book Price</th>\r\n"
							+ "				<th>Quantity</th><br/>\r\n" + "				<th>Amount</th><br/>\r\n" + "			</tr>");
			double total = 0.0;
			int i=0;
			List<BookDTO> al=new ArrayList<BookDTO>();
			al=dao.search();
			for(BookDTO dto:al)
			{
				float bPrice = dto.getPrice();
				String bCode = dto.getBookCode();
				String bName = dto.getBookName();
				String bAuthor = dto.getAuthor();
				int bQty = dto.getQuantity();
				i = i + 1;

				String qt = "qty" + Integer.toString(i);
				int quantity = Integer.parseInt(req.getParameter(qt));
				try {
					String check1 = "checked" + Integer.toString(i);
					String getChecked = req.getParameter(check1);
					if (bQty < quantity) {
						pw.println(
								"</table><div class=\"tab\">Please Select the Qty less than Available Books Quantity</div>");
						break;
					}

					if (getChecked.equals("pay")) {
						pw.println("<tr><td>" + bCode + "</td>");
						pw.println("<td>" + bName + "</td>");
						pw.println("<td>" + bAuthor + "</td>");
						pw.println("<td>" + bPrice + "</td>");
						pw.println("<td>" + quantity + "</td>");
						float amount = bPrice * quantity;
						total = total + amount;
						pw.println("<td>" + amount + "</td></tr>");
						bQty = bQty - quantity;
						System.out.println(bQty);
						dao.updateBook(bQty,bCode);
						
					}
				} catch (Exception e) {
				}
			}
			pw.println("</table><br/><div class='tab'>Total Paid Amount: " + total + "</div>");
			String fPay = req.getParameter("f_pay");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
