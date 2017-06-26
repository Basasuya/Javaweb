<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>杰拉网咖进销存管理系统</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 </head>
<style>
	#header {
	    background-color:black;
	    color:white;
	    height:15%;
	    text-align:center;
	}
	#nav {
	    background-color:#eeeeee;
	    height:79%;
	    width:15%;
	    float:left;
	    text-align:center;
	}
	#section {
	    width:85%;
	    float:left;
	    text-align:center;
	}
	#footer {
	    background-color:black;
	    color:white;
	    clear:both;
	    height:6%;
	    text-align:center;
	}
</style>
<body>
<div id="header">
<h1>杰拉网咖进销存管理系统</h1>
</div>

<div id="nav">
<%@ include file="Left.jsp"%>
</div>

<div id="section">
<br><br>
<h1>欢迎使用杰拉网咖进销存管理系统</h1>
<br>
<br>
<h2>
语言环境：JavaSE 1.8<br>
服务器：Apache Tomcat v7.0<br>
框架使用：strut2
</h2>
</div>

<div id="footer">
Copyright @basasuya
</div>
</body>
</html>

</html>