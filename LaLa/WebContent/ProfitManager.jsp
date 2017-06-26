<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>杰拉网咖进销存管理系统</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
	<script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
	<script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
	<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>

<script>
    $(document).ready(function(){
      $("font#ch1").click(function(){
        $("table#a1").show();
        $("table#b1").hide();
        $("div#container").hide();
      });
      $("font#ch2").click(function(){
        $("table#a1").hide();
        $("table#b1").show();
        $("div#container").show();
      });
    });
</script>

<style>
#header {
	background-color: black;
	color: white;
	height: 15%;
	text-align: center;
}
#nav {
	background-color: #eeeeee;
	height: 79%;
	width: 15%;
	float: left;
	text-align: center;
}

#section {
	width: 85%;
	float: left;
	text-align: center;
}
#container {
	width: 100%;
	float: left;
	text-align: center;
}
#footer {
	background-color: black;
	color: white;
	clear: both;
	height: 6%;
	text-align: center;
}
</style>


<script>
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: '各用户上班时间的利润统计'
        },
        subtitle: {
            text: '数据来源: basasuya'
        },
        xAxis: {
            categories: [
            	<s:iterator value="users"> 
            	'${name}',
            	</s:iterator>
            ],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '盈利 (元)',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' 元'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true,
                    allowOverlap: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [
        	
        	
        	
        	{
            name: '盈利',
            data: [
            	<s:iterator value="users"> 
            	${profit},
            	</s:iterator>
            	]
        }]
    });
});
</script>

<body>
	<div id="header">
		<h1>杰拉网咖进销存管理系统</h1>
	</div>

	<div id="nav">
		<%@ include file="Left.jsp"%>
	</div>

	<div id="section">
		<table height="20" width = 100% background="Images/mainMenuBg.jpg">
		<tr>
			<td  align="center" 
				style="padding-left: 25px;">利润统计</td>
		</tr>
		</table>
		<table height="20" width = 100% >
		<tr align="center">
			<td><font id = "ch1">所有数据</font></td>
			<td><font id = "ch2">数据统计</font></td>
		</tr>
		</table>
		<table width = 100% border="0" cellspacing="0" cellpadding="0" id = "a1">
				<tr>
					<td align="center" valign="top" >
					<form name="form1" method="post" action="ProfitManager.action">
							<table  width = 100% border="0" cellspacing="0" cellpadding="0" background="Images/bootBg.jpg">
								<tr>
									<td  align="center"><select name="SearchRow"
										id="SearchRow">
											<option value="Profit_Mon">月份</option>
									</select> <input name="SearchKey" type="text" class="text1"
										id="SearchKey"> <input type="submit" name="button"
										id="button" value="点击查询"></td>
								</tr>
							</table>
						</form> 
						<table align="center" width="90%" border="0" cellspacing="0" cellpadding="0">
							<tr align="center" class="t1">
								<td height="25" bgcolor="#D5E4F4"><strong>编号</strong></td>
								<td bgcolor="#D5E4F4"><strong>操作人</strong></td>
								<td bgcolor="#D5E4F4"><strong>盈利量</strong></td>
								<td bgcolor="#D5E4F4"><strong>售卖时间</strong></td>
							</tr>
							
							<s:iterator  value="list">
								<tr align="center">
									
									<td height="25" align="center" bgcolor="#ADD8E6">
									${Profit_Id}</td>
									<td bgcolor="#ADD8E6">${Profit_Username}</td>
									<td bgcolor="#ADD8E6">${Profit_Gain}</td>
									<td bgcolor="#ADD8E6">${Profit_Time}</td>
									<td width = 10px></td>
									<td align="center"  bgcolor="#0000FF"><a
										href="ProfitUpdate.action?Profit_Id=${Profit_Id}" 
										><font color="white">修改</font></a> <a
										href="ProfitDel.action?Profit_Id=${Profit_Id}"
										onClick="return confirm('确定要删除该记录吗？')"><font color="white">删除</font></a></td>
								</tr>
							</s:iterator>
						</table></td>
				</tr>

			</table>
			<br>
			<table width=80% border="0" cellspacing="0" cellpadding="0" align = center id = "b1" style = "display:none;">
			<tr align="center" class="t1">
				<td height="25" bgcolor="#D5E4F4" width = 10%><strong>操作人</strong></td>
				<td bgcolor="#D5E4F4"><strong>盈利量</strong></td>
			</tr>
			<s:iterator value="users">
				<tr align="center">
					<td height="25" align="center" bgcolor="#ADD8E6">${name}</td>
					<td align="center" bgcolor="#ADD8E6"  >${profit}</td>
				</tr>
			</s:iterator>
		</table>
		<br>
		
	</div>
	<div id="container" style="min-width:400px;height:400px"  style = "display:none;"></div>
	<div id="footer">Copyright @basasuya</div>
</body>
</html>