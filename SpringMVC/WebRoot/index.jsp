<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="fmt"  uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="scripts/jquery-2.2.0.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#testJson").click(function(){
				var url = this.href;
				var args = {};
				$post(url,args,function(data){
					for(var i = 0; i <= data.length;i++){
						var id = data[i].id;
						var lastName = data[i].lastName;
						alert(id + ": " + lastName);
					}
				});
				return false;
			});
		})
	</script>
  </head>
  
  <body>
    <a href="helloword">Hello Word！</a><br/><br/>
    <a href="springmvc/testmapping">Test RequestMapping！</a><br/><br/>
    
    <form action="springmvc/testMethod" method="POST">
    	<input type="submit" value="Test Method"></input>
    </form>
    
    <a href="springmvc/testparamsandheaders?username=dunso&age=19">Test ParamsAndHeaders！</a><br/><br/>
    
    <a href="springmvc/testantpath/dsfsds/abc">Test AntPath！</a><br/><br/>

    <a href="springmvc/testpathvariable/1">Test PathVariable！</a><br/><br/>
    
    <a href="springmvc/testrest/1">Test Rest Get！</a><br/><br/>
    
    <form action="springmvc/testrest/" method="post"> 
    	<input type="submit" value="Test Rest Post"/>
    </form>
    
    <form action="springmvc/testrest/1" method="post"> 
    	<input type="hidden" name="_method" value="DELETE"/>
    	<input type="submit" value="Test Rest DELETE"/>
    </form>
    
    <form action="springmvc/testrest/1" method="post"> 
    	<input type="hidden" name="_method" value="PUT"/>
    	<input type="submit" value="Test Rest PUT"/>
    </form>
    
    <a href="springmvc/testrequestparam?username=dunso&age=18">Test RequestParam</a><br/><br/>
    
    <a href="springmvc/testcookievalue">Test CookieValue</a>
    
  
    <form action="springmvc/testPojo" method="post">
      <table border="1">
		<tr><td>username: <input type="text" name="username"/><br/></td></tr>
		<tr><td>password: <input type="password" name="password"/><br/></td></tr>
		<tr><td>email:	  <input type="text" name="email"/><br/></td></tr>
		<tr><td>age:      <input type="text" name="age"/><br/></td></tr>
		<tr><td>province:  <input type="text" name="address.province"/><br/></td></tr>
		<tr><td>city:      <input type="text" name="address.city"/><br/></td></tr>
		<tr><td><input type="submit" value="submit" /></td></tr>
		</table>
	</form>
	
	<a href="springmvc/testservletapi">Test ServletAPI</a><br/><br/>
	
	<a href="springmvc/testmodelandview">Test ModelAndView</a><br/><br/>
	
	<a href="springmvc/testmap">Test Map</a><br/><br/>

	<a href="springmvc/testsession">Test @SessionAttributes</a><br/><br/>

	<!-- 
		模拟修改操作
		1、原始数据为：1，dunso,123,admin@dun.so,19
		2、密码不能修改
		3、表单回显，模拟操作直接在表单填写对应的属性值。
	 -->
	<form action="springmvc/testModelAttribute" method="post">
		<table border="1">
			<tr><td>id:		  <input type="hidden" name="id" value="1"></td></tr>
			<tr><td>username: <input type="text" name="username" value="dunso"/><br/></td></tr>
			<tr><td>email:	  <input type="text" name="email" value="admin@dun.so"/><br/></td></tr>
			<tr><td>age:      <input type="text" name="age" value="12"/><br/></td></tr>
			<tr><td><input type="submit" value="submit" /></td></tr>
		</table>
	</form>
	
	<br/><br/>
	
	<a href="springmvc/testViewAndViewResolver">Test ViewAndViewResolver</a><br/><br/>
	
	<a href="springmvc/testView">Test View</a><br/><br/>
	
	<a href="springmvc/testRedirct">Test Redirct</a><br/><br/>

	<a href="emps">Test ListAllEmployees</a><br/><br/>
	
	<a href="emp">Test AddNewEmployee</a><br/><br/>
	
	<form action="springmvc/testConversionServiceConverer" mathod="POST">
    	<!-- lastname-email-gender-department.id 例如dunso-admin@dun.so-0-10 -->
    	输入示例：dunso-admin@dun.so-0-10
    	Employee:<input type="text" name="employee"/>
    	<input type="submit" value="submit"/>
    </form>
    
    <a href="springmvc/testJson" id="testJson">Test Json</a><br/><br/>
    
    <form action="springmvc/testHttpMessageConverter" method="POST" enctype="multipart/form-data">
    	File: <input type="file" name="file"/>
    	Desc: <input type="text" name="desc"/>
    	<input type="submit" value="Submit"/>
    </form>
    
    <br/><br/>
    
     <a href="springmvc/testResponseEntity">Test ResponseEntity</a><br/><br/>
	
	 <a href="springmvc/i18n">Test I18n Page</a><br/><br/>
	 
	 <a href="springmvc/i18n?locale=zn_CN">中文</a><br/><br/>
	 
	 <a href="springmvc/i18n?locale=en_US">英文</a><br/><br/>
	 
	 <form action="springmvc/testFileUpload" method="POST" enctype="multipart/form-data">
    	File: <input type="file" name="file"/>
    	Desc: <input type="text" name="desc"/>
   		<input type="submit" value="Submit"/>
    </form>
	
	<br/><br/>
	
	<a href="springmvc/testExceptionHandlerExceptionResolver?i=10">Test ExceptionHandlerExceptionResolver</a><br/><br/>
	
	<a href="springmvc/testResponseStatusExceptionResolver?i=10">Test ResponseStatusExceptionResolver</a><br/><br/>
	
	<a href="springmvc/testDefaultHandlerExceptionResolver?i=10">Test DefaultHandlerExceptionResolver</a><br/><br/>
  
  	<a href="springmvc/testSimpleMappingExceptionResolver?i=9">Test SimpleMappingExceptionResolver</a><br/><br/>
  </body>
  
</html>













