<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	 	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>支付成功</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="css/style.css" type="text/css" />
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
			
			.cart_empty {
				height: 273px;
				padding-left: 558px;
				margin: 65px 0 130px;
				background: url("./img/paySuccess.jpg") no-repeat 124px 0;
				color: #b0b0b0;
				overflow: hidden;
				font: 14px/1.5 "Helvetica Neue",Helvetica,Arial,"Microsoft Yahei","Hiragino Sans GB","Heiti SC","WenQuanYi Micro Hei",sans-serif;
			}
		</style>
  </head>
  
  <body>
  	<jsp:include page="/header.jsp"></jsp:include>
  		<div class="container">
  			<div class="cart_empty">
				<h1>支付成功！</h1>
				<a href="${pageContext.request.contextPath }">返回首页</a>
			</div>
  		</div>
    <jsp:include page="/footer.jsp"></jsp:include>
  </body>
</html>
