<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>2016豆瓣影评</title>
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
	#section {
	    width:100%;
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
<table align = center>
<tr>
<td><img src="Images/douban.jpg" height = 40px/></td> 
<td padding = 3px><h1><font id = "ch2" color = "#FFFFFF">豆瓣影评搜索</font></h1></td>
</tr>
</table>
</div>

<div id="section">
		<table width=100% align = center background="Images/bootBg.jpg" >
			<tr>
				<td>
					<form name="form1" method="post" action="DouBanManager.action">
						<table width=100% border="0" cellspacing="0" cellpadding="0" align = center>
							<tr align = center>
								<td>年份<input name="Years" type="text" class="text1" id="SearchKey"> </td>
								<td >影片名称<input name="MovieKey" type="text" class="text1" id="SearchKey"> </td>
								<td>关键词<input name="KeyWords" type="text" class="text1" id="SearchKey"> </td>
								<td>分数下限<input name="UpEdge" type="text" class="text1" id="SearchKey"> </td>
								<td>分数上限<input name="DownEdge" type="text" class="text1" id="SearchKey"> </td>
								<td>不含该影名<input name="DonHave" type="text" class="text1" id="SearchKey"> </td>
								<td><input type="submit" name="button" id="button" value="点击查询"></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			
		</table>
		<table align = center>
			<tr align = center>
				<td><strong>影片年份:</strong>${Years}</td>
				<td><strong>影片名称:</strong>${MovieKey}</td>
				<td><strong>关键词 :</strong>${KeyWords}</td>
				<td><strong>分数下限:</strong>${UpEdge}</td>
				<td><strong>分数上限 :</strong>${DownEdge}</td>
				<td><strong>不含该影名:</strong>${DonHave}</td>
			<tr>
		</table>
		<table width = 100% align = center border="1" >
				<tr>
					<td align="center" valign="top" >
					
						<table align=center width="100%" >
							<tr align="center" class="t1">
								<td height="25" bgcolor="#D5E4F4"><strong>电影名</strong></td>
								<td bgcolor="#D5E4F4"><strong>关键词</strong></td>
								<td bgcolor="#D5E4F4"><strong>评分</strong></td>
								<td bgcolor="#D5E4F4"><strong>年份</strong></td>
							</tr>
							
							<s:iterator  value="list">
								<tr align=center>
									<td height="25" align="center" bgcolor="66CC99">
									<a href="http://movie.douban.com/subject/${num}" target="_blank">
									${moivename}</a>></td>
									<td bgcolor="33CCCC">${stars}</td>
									<td bgcolor="33CCFF">${shows}</td>
									<td bgcolor="33CCFF">${year}</td>
								</tr>
							</s:iterator>
						</table></td>
				</tr>
		</table>
		<table width = 95% align = center border="0" >
		<tr><td><br></td></tr>
		</table>
</div>

<div id="footer">
Copyright @basasuya
</div>
</body>
</html>

</html>