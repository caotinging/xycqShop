<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'easyuiTest2.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/exporting.js"></script>
</head>

<body>
	<div id="container" style="min-width:400px;height:400px"></div>
	<script type="text/javascript">
		$(function() {
			$('#container').highcharts({
				chart : {
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : '课程成绩占比'
				},
				tooltip : {
					headerFormat : '{series.name}<br>',
					pointFormat : '{point.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							format : '<b>{point.name}</b>: {point.percentage:.1f} %',
							style : {
								color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							}
						}
					}
				},
				series : [ {
					type : 'pie',
					name : '浏览器访问量占比',
					data : [
						[ 'Firefox', 45.0 ],
						[ 'IE', 26.8 ],
						{
							name : 'Chrome',
							y : 12.8,
							sliced : true,
							selected : true
						},
						[ 'Safari', 8.5 ],
						[ 'Opera', 6.2 ],
						[ '其他', 0.7 ]
					]
				} ]
			});
		});
	</script>
</body>

</html>
