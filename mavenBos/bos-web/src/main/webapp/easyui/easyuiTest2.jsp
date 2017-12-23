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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
  </head>
  
  <body>
  
  	<!-- 方式三：js代码动态生成 -->
  	<table id="datagridTable"></table>
  	<script type="text/javascript">
  		$(function(){
  			$("#datagridTable").datagrid({
  				columns : [[
  					{field:"id",title:"编号",width:"100",align:"center"},
  					{field:"pId",title:"编号",width:"100",align:"center"},
  					{field:"name",title:"名称",width:"100",align:"center"}
  				]],  
  				url : "${pageContext.request.contextPath }/json/admin.json",
  				toolbar : [
  					{text:"添加",iconCls:"icon-add",handler:function(){
  							$.messager.alert("提示信息","添加！","info");
  						}
  					},
  					{text:"删除",iconCls:"icon-remove"}
  				],
  				//分页栏
  				pagination : true
  			});
  		});
  	</script>
  
  	<!-- 方式二：从json文件中动态获取 -->
  	<table class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath }/json/admin.json'">
  		<thead>
  			<tr>
  				<th data-options="field:'id'">编号</th>
    			<th data-options="field:'pId'">----</th>
    			<th data-options="field:'name'">名称</th>
  			</tr>
  		</thead>
  	</table>
  	
  
  	<!-- 方式一：静态显示数据表格 -->
    <table class="easyui-datagrid">
    	<thead>
    		<tr>
    			<th data-options="field:'id'">编号</th>
    			<th data-options="field:'name'">姓名</th>
    			<th data-options="field:'class'">班级</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
    			<td>001</td><td>caotinging</td><td>1502</td>
    		</tr>
    		<tr>
    			<td>002</td><td>Tom</td><td>1403</td>
    		</tr>
    	</tbody>
    </table>
  </body>
</html>
