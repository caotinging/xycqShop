<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>easyUI</title>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body class="easyui-layout">
	<!-- 北部区域 -->
    <div data-options="region:'north',title:'欣语长情管理系统',split:true" style="height:100px;">
    	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-edit'">系统设置</a>
		<div id="mm" style="width:150px;">
			<div onclick="javascript:$.messager.alert('标题','消息','info')">修改信息</div>
			<div class="menu-sep"></div>
			<div onclick="javascript:$.messager.confirm('退出系统','确认退出吗？',function(res){
				alert(res);
			})">退出系统</div>
		</div>
	</div>   
    
    <!-- 南部区域 -->
    <div data-options="region:'south',split:true" style="height:50px;">
    	<div style="width: 100%;">
			<div style="color: #999; font-size: 8pt;">
				欣语长情 | Powered by <a href="#">xycqShop.com</a>
			</div>
		</div>
    </div>
    
    <!-- 右边、西部区域 -->
    <div data-options="region:'east',iconCls:'icon-reload',title:'最新公告',split:true" style="width:150px;"></div>   
    
    <!-- 左边、东部区域 -->
    <div data-options="region:'west',title:'菜单导航',split:true" style="width:200px;">
		<div id="aa" class="easyui-accordion" data-options="fit:true">
			<div title="基本功能" data-options="iconCls:'icon-mini-add',selected:true" style="overflow:auto;padding:10px;">
				<ul id="ztree1" class="ztree"></ul>

				<script type="text/javascript">
					$(function() {
				
						var setting = {
							data : {
								simpleData : {
									enable : true,
									idKey : "id",
									pIdKey : "pId",
									rootPId : 0
								}
							},
							callback : {
								onClick : function zTreeOnClick(event, treeId, treeNode) {
									//判断当前页面是否是叶子节点、是否已经打开
									if (!treeNode.isParent) {
										//子节点
										//判断当前子节点是否已经打开
										var e = $("#dtabs").tabs('exists',treeNode.name);
										if(e) {
											//已经打开，置为选中
											$("#dtabs").tabs('select',treeNode.name);
										}else {
											//没有打开新建一个选项卡
											$('#dtabs').tabs('add',
											{
												title : treeNode.name,
												content : "<iframe frameborder='0' style='width:100%;heigth:100%' src='"+treeNode.page+"'></iframe>",
												closable : true,
												//选项卡栏的工具
												tools : [ {
													iconCls : 'icon-mini-refresh',
													handler : function() {
														//刷新页面的操作
														$('#dtabs').tabs('getSelected').panel('refresh');  // 获取选择的面板刷新
													}
												} ]
											});
										}
									}
								}
							}
						};
						$.post(
							"${pageContext.request.contextPath}/json/menu.json",
							function(zTreeNodes) {
								//初始化一颗ztree树
								$.fn.zTree.init($("#ztree1"), setting, zTreeNodes);
							}, 'json'
						);
				
						// 消息将显示在顶部中间
						$.messager.show({
							title : '欢迎回来！',
							msg : '欢迎您进入欣语长情管理系统',
							showType : 'slide',
							showSpeed : 5000,//单位毫秒
							style : {
								right : '',
								top : document.body.scrollTop + document.documentElement.scrollTop,
								bottom : ''
							}
						});
					});
				</script>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-mini-add'" style="padding:10px;">
				
			</div>
		</div>
	</div>
    
    <!-- 中心区域 -->
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    	<!-- 创建选项卡显示容器 -->
    	<div id="dtabs" class="easyui-tabs" data-options="fit:true"></div>
    </div>   
</body>
</body>
</html>
