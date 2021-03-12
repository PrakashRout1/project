<%@taglib uri="http://www.springframework.org/tags/form"  prefix="frm"%>
<head>
   <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"  rel="stylesheet">
   
</head>
<div class="container">
<h1 class="text-primary text-center"> Employees Registration page </h1>

<frm:form action="saveEmp" modelAttribute="empFrm"   onsubmit="return validate(this)">
   <table class="table table-responsive " bgcolor="cyan" >
      <tr>
        <td>Employee name :: </td>
        <td> <frm:input path="name"/>   </td>
      </tr>
      <tr>
        <td>Employee salary :: </td>
        <td> <frm:input path="salary"/>    </td>
      </tr>
      <tr>
        <td>Employee dept :: </td>
        <td> <frm:input path="dept"/>  </td>
      </tr>
      <tr>
           
        <td  > <input type="submit"  value="register"> </td> 
      </tr>
   </table>
    
</frm:form>
 </div>