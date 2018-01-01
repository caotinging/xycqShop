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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
</head>

<body>
	<table id="datagridTable"></table>
  	<script type="text/javascript">
		$(function(){
			var editIndex = -1;//全局变量，值为正在编辑行的索引
			//页面加载完成后，创建数据表格datagrid
			$("#datagridTable").datagrid({
				//定义标题行所有的列
				columns:[[
				          {title:'编号',field:'id',checkbox:true},
				          {width:150,title:'姓名',field:'name',editor: {
				          											type : 'validatebox',
				          											options : {}
				          										}
				          },
				          {width:150,title:'年龄',field:'age',editor: {
				          		type:'numberbox',
				          		options: {}
				          }},
				          {width:150,title:'日期',field:'address',editor:{
				          		type:'datebox',
				          		options: {}
				          }}
				          ]],
				//指定数据表格发送ajax请求的地址
				url:'${pageContext.request.contextPath }/json/datagrid_data.json',
				rownumbers:true,
				singleSelect:true,
				//定义工具栏
				toolbar:[
				         {text:'添加',iconCls:'icon-add',
				        	 //为按钮绑定单击事件
				        	 handler : function(){
				        	 	//首先保存正在编辑的那一行
				        	 	$("#datagridTable").datagrid("endEdit",editIndex);
				        	 
				        	 	$("#datagridTable").datagrid('insertRow',{
				        	 		index : 0,//在第一行插入新数据
				        	 		row : {}//空行
				        	 	});
				        	 	
				        	 	//开启编辑器
				        	 	$("#datagridTable").datagrid("beginEdit",0);
				        	 	editIndex = 0;
				        	 }
				         },
				         {text:'删除',iconCls:'icon-remove',
				        		//获得选中的行对象
				        		handler: function() {
				        			//获取当前选取的对象
				        			var sel = $("#datagridTable").datagrid("getSelected");
				        			
				        			$("#datagridTable").datagrid("deleteRow",$("#datagridTable").datagrid("getRowIndex",sel));
				        		}
				        	},
				         {text:'修改',iconCls:'icon-edit',
					        	 //获得选中的行对象
				        		handler: function() {
				        			//结束这之前的编辑对象
				        			$("#datagridTable").datagrid("endEdit",editIndex);
				        		
				        			//获取当前选取的对象
				        			var sel = $("#datagridTable").datagrid("getSelected");
				        			editIndex = $("#datagridTable").datagrid("getRowIndex",sel);
				        			
				        			$("#datagridTable").datagrid("beginEdit",editIndex);
				        		}
				        	 },
				         {text:'保存',iconCls:'icon-save',
				         		//保存退出编辑
								handler : function(){
									//结束指定行的编辑状态
									$("#datagridTable").datagrid("endEdit",editIndex);
								}				         	
				         	}
				         ],
				//显示分页条
				pagination:true,
				pageList:[3,5,7,10],
				//数据表格提供的用于监听结束编辑事件
				onAfterEdit: function(index, row, changes){
					console.info(row);
					console.info(changes);
				}
			});
		});
	</script>
</body>

</html>
