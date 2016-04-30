<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<!-- SpringMVC 处理静态资源 -->
	<!-- 在SpringMVC的配置文件中配置<mvc:default-servlet-handler/> -->
	<script type="text/javascript" src="scripts/jquery-2.2.0.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".delete").click(function(){
				var href=$(this).attr("href");
				$("form").attr("action", href).submit();
				return false;
			});
		})
	</script>
	
  </head>
  
  <body>
  	
  	<form action="" method="POST">
  		<input type="hidden" name="_method" value="DELETE"/>
  	</form>
  		
  	
    <h1><center> SUCCESS ！</center></h1> <br/><br/>
    <center>
	<c:if test="${empty requestScope.employees}">
		没有员工信息！
	</c:if>
	<c:if test="${!empty requestScope.employees}">
		<table>
			<tr>
			<th>ID</th>
			<th>LastName</th>
			<th>Email</th>
			<th>Gender</th>
			<th>Department</th>
			<th>Edit</th>
			<th>Delete</th>
			</tr>
			<c:forEach items="${requestScope.employees}" var="emp">
			<tr>
			<td>${emp.id}</td>
			<td>${emp.lastName}</td>
			<td>${emp.email}</td>
			<td>${emp.gender == 0 ? 'Female' : 'Male' }</td>
			<td>${emp.department.departmentName}</td>
			<td><a href="emp/${emp.id}">Edit</a></td>
			<td><a class="delete" href="emp/${emp.id }">Delete</a></td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	</center>
  </body>
</html>
