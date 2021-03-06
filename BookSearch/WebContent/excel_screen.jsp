<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" import="java.util.*,com.nt.dto.BookDTO"%>

    <% 
    //make response of this jsp as downloadable file
    response.setContentType("application/vnd.ms-excel");
    response.addHeader("Content-Disposition","attachment;fileName=BooksResult.xls");
    List<BookDTO> listDTO=null;
    listDTO=(List<BookDTO>)request.getAttribute("booksList");
    //Display database table
    if(listDTO.size()!=0){%>
    	<h1 style='text-align: center'>Books REport for<%=request.getParameter("category") %></h1>
    	<table border='1' >
    	 <tr>
    	  	<th>BookID</th>
    	  	<th>BookName</th>
    	  	<th>Author</th>
    	  	<th>Price</th>
    	  	<th>Category</th>
    	  	<th>Status</th>
    	 </tr>
    	 <%
    	 	for(BookDTO dto:listDTO){%>
    	 	<tr>
    	 		<td><%=dto.getBookId() %></td>
    	 		<td><%=dto.getBookName() %></td>
    	 		<td><%=dto.getAuthor() %></td>
    	 		<td><%=dto.getPrice() %></td>
    	 		<td><%=dto.getCategory() %></td>
    	 		<td><%=dto.getStatus() %></td>
    	 	</tr>
    	 	<%}
    	 %>
    	</table>
    <%}
    else{%>
    	<h1 style='color: red:text-align:center'>NO Books FOund</h1>
    <%}
    %>