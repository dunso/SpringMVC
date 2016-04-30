<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
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

  </head>
  
  <body>
    <h1><center> 增加Employee </center></h1> <br/><br/>
        
    <center>
		<form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
			<!-- path对应html表单标签的name属性值 -->
			<table>
			<c:if test="${employee.id == null }">
				<tr><td>LastName:<form:input path="lastName"/><br/></td>
				<form:errors path="lastName"></form:errors></tr>
						
			</c:if>
			<c:if test="${employee.id != null }">
				<tr><td><form:hidden path="id"/><br/></td></tr>
				<!-- 对于_method 不能使用form：hidden标签，因为modelAttribute对应的bean中没有_method这个属性 -->
				<input type="hidden" name="_method" value="PUT"/>
			</c:if>
			
			
				<tr><td>Email:<form:input path="email"/><br/></td>
				<form:errors path="email"></form:errors></tr>
			<%
				Map<String,String> genders = new HashMap();
				genders.put("1", "Male");
				genders.put("2", "Female");
				request.setAttribute("genders", genders);
			 %>
			
				<tr><td>Gender:<form:radiobuttons path="gender" items="${genders}"/><br/></td></tr>
				<tr><td>Department:<form:select path="department" items="${departments}" itemLabel="departmentName" itemValue="id"></form:select><br/></td></tr>
				<!--1.数据类型转换
					2.数据类型格式化
					3.数据校验 -->
		 		<tr><td>Birth:<form:input path="birth"/><br/></td>
		 		<form:errors path="birth"></form:errors></tr> 
		 		<tr><td>Salary:<form:input path="salary"/><br/></td>
		 		<form:errors path="salary"></form:errors></tr> 
				<tr><td><input type="submit" value="submit"/></td></tr>
			</table>
		</form:form>
	</center> 
  </body>
</html>
