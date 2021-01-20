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

@WebServlet("/buybook")
public class BuyBookServlet extends HttpServlet {
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
			pw.println("<div class=\"tab hd brown \">Books Available In Our Store</div>");
			pw.println("<div class=\"tab\"><form action=\"receipt\" method=\"post\">");
			pw.println("<table>\r\n" + 
					"			<tr>\r\n" + 
					"				<th>Books</th>\r\n" + 
					"				<th>Code</th>\r\n" + 
					"				<th>Name</th>\r\n" + 
					"				<th>Author</th>\r\n" + 
					"				<th>Price</th>\r\n" + 
					"				<th>Avail</th>\r\n" + 
					"				<th>Qty</th>\r\n" + 
					"			</tr>");
			List<BookDTO> al=new ArrayList<BookDTO>();
			int i=0;
			al=dao.search();
			for(BookDTO dto:al)
			{	i=i+1;
				String n = "checked"+ Integer.toString(i);
				String q = "qty"+Integer.toString(i);
				pw.println("<tr>\r\n" + 
						"				<td>\r\n" + 
						"					<input type=\"checkbox\" name="+n+" value=\"pay\">\r\n" + //Value is made equal to bcode
						"				</td>");
				pw.println("<td>"+dto.getBookCode()+"</td>");
				pw.println("<td>"+dto.getBookName()+"</td>");
				pw.println("<td>"+dto.getAuthor()+"</td>");
				pw.println("<td>"+dto.getPrice()+"</td>");
				pw.println("<td>"+dto.getQuantity()+"</td>");
				pw.println("<td><input type=\"text\" name="+q+" value=\"0\" text-align=\"center\"></td></tr>");
				
			}
			pw.println("</table>\r\n" + "<input type=\"submit\" value=\" PAY NOW \">"+"<br/>"+
					"	</form>\r\n" + 
					"	</div>");
			//pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
