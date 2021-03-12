<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>

<head>
  <link  rel="stylesheet"  href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>   

<div class="container">
	<h1>Result Page</h1>
	<c:choose>
		<c:when test="${!empty listDTO }">
			<table class="table-bordered table-hover">
				<tr class="danger">
					<th>Employee Id</th><th>Employee Name</th><th>Department</th><th>Salary</th>
				</tr>
				<c:forEach var="dto" items="${listDTO }">
					<tr class="bg-success text-danger">
						<td>${dto.id }</td>
						<td>${dto.name }</td>
						<td>${dto.dept }</td>
						<td>${dto.salary }</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	<c:otherwise>
         <h1 class="bg-warning text-white">No Records found</h1>
     </c:otherwise>
  </c:choose>
  <br><br>
     <h3 > <a href="welcome">home</a></h3>
</div>