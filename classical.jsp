<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> <style>
#header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:#eeeeee;
    height:300px;
    width:100px;
    float:left;
    padding:5px;	      
}
#section {
    width:350px;
    float:left;
    padding:10px;	 	 
}
#footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}
</style>
<head>
<script src="text/javascript">
$(document).ready(function(){
	  $("#hide").click(function(){
	  	$("p").hide();
	  });
	  $("#show").click(function(){
	  	$("p").show();
	  });
});
</script>
<script src="E:/Downloads/jquery-3.1.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My first html</title>
</head>
<body>
<div id="header">
<h1>杰拉网咖进销存管理系统</h1>
</div>

<div id="nav">
London<br>
Paris<br>
Tokyo<br>
</div>
</body>
<div id="section">
<h2>London</h2>
<p>
London is the capital city of England. It is the most populous city in the United Kingdom,
with a metropolitan area of over 13 million inhabitants.
</p>
<p>
Standing on the River Thames, London has been a major settlement for two millennia,
its history going back to its founding by the Romans, who named it Londinium.
</p>
</div>
<div id="footer">
Copyright@ basasuya 
</div>
</html>
