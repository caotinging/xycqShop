<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
$(function(){
	alert("111");
	
	var jsonStr = '{"id": 1,"name": "测试商品","price": 99.9,"detail": "测试商品描述","pic": "123456.jpg"}';

	$.ajax({
		url: "${pageContext.request.contextPath }/jsonTest.action",
		data: jsonStr,
		contentType: "application/json;charset=utf-8",
 	 	type: "POST",
 	 	dataType: "json",
 	 	success: function(data) {
 	 		alert(data.name);
 	 	}
	});
});
</script>
</head>
<body> 
<form action="${pageContext.request.contextPath }/items/queryitem.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td><input type="submit" value="查询"/></td>
</tr>
</table>
</form>
<!-- <form action="${pageContext.request.contextPath }/items/deleteitem.action" method="post"> -->
<form action="${pageContext.request.contextPath }/items/editItemList.action" method="post">
商品列表：
<table width="100%" border=1>
<tr>
	<td><input type="checkbox" name="ids"></td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${itemList }" var="item" varStatus="i">
<tr>
	<td><input type="checkbox" name="ids" value="${item.id }"></td>
	<td><input type="text" name="itemList[${i.index }].name" value="${item.name }"></td>
	<td><input type="text" name="itemList[${i.index }].price" value="${item.price }"></td>
	<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd"/></td>
	<td><input type="text" name="itemList[${i.index }].detail" value="${item.detail }"></td>
	<td><a href="${pageContext.request.contextPath }/items/itemEdit.action?id=${item.id}">修改</a></td>
</tr>
</c:forEach>
</table>
<!-- <input type="submit" value="批量删除" > -->
<input type="submit" value="批量修改" >
</form>
</body>
</html>