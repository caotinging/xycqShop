<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加业务受理单</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("body").css({visibility:"visible"});
		
		// 对save按钮条件 点击事件
		$('#save').click(function(){
			// 对form 进行校验
			if($('#noticebillForm').form('validate')){
				$('#noticebillForm').submit();
			}else{
				$.messager.alert("系统提示","信息录入有误","warning");
			}
		});
		
		//为手机添加校验规则
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		$.extend($.fn.validatebox.defaults.rules, {
			telephone : {
			//value：input的值，param：（validType:'telephone[params]')
				validator : function(value, param) {
					return reg.test(value);
				},
				message : '手机号输入有误！'
			}
		});
		
		//为电话号码输入框绑定离焦事件
		$("#tel-input-id").blur(function(){
			var tel = this.value;
			
			//异步获取号码对应的客户信息
			$.post("noticebillAction_getcustomerByTel", {"telephone" : tel}, function(customer){
				//判断是否为空
				if(customer != null ) {
					//获得了相关联的客户信息、进行回显
					$("input[name='customerName']").val(customer.name);
					$("input[name='customerId']").val(customer.id);
					$("input[name='delegater']").val(customer.name);
					$("input[name='pickaddress']").val(customer.address);
				} else {
					//没有相关客户信息、清空上一次的客户信息展示残留
					$("input[name='customerName']").val("");
					$("input[name='customerId']").val("");
					$("input[name='delegater']").val("");
					$("input[name='pickaddress']").val("");
				}
			},'json');
		});
	});
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false"
		border="false">
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="javascript:void(0);" class="easyui-linkbutton"
				plain="true">新单</a>
			<a id="edit" icon="icon-edit" href="${pageContext.request.contextPath }/page_qupai_noticebill.action" class="easyui-linkbutton"
				plain="true">工单操作</a>	
		</div>
	</div>
	<div region="center" style="overflow:auto;padding:5px;" border="false">
		<form id="noticebillForm" action="${pageContext.request.contextPath }/noticebillAction_save.action" method="post">
			<s:property value="#error"/>
			<table class="table-edit" width="95%" align="center">
				<tr class="title">
					<td colspan="4">客户信息</td>
				</tr>
				<tr>
					<td>来电号码:</td>
					<td><input id="tel-input-id" type="text" class="easyui-numberbox" name="telephone" required="true" data-options="validType: 'telephone'" /></td>
					<td>客户编号:</td>
					<td><input type="text" class="easyui-validatebox"  name="customerId" /></td>
				</tr>
				<tr>
					<td>客户姓名:</td>
					<td><input type="text" class="easyui-validatebox" name="customerName"  /></td>
					<td>联系人:</td>
					<td><input type="text" class="easyui-validatebox" name="delegater"  /></td>
				</tr>
				<tr class="title">
					<td colspan="4">货物信息</td>
				</tr>
				<tr>
					<td>品名:</td>
					<td><input type="text" class="easyui-validatebox" name="product"  /></td>
					<td>件数:</td>
					<td><input type="text" class="easyui-numberbox" name="num"  /></td>
				</tr>
				<tr>
					<td>重量:</td>
					<td><input type="text" class="easyui-numberbox" name="weight" /></td>
					<td>体积:</td>
					<td><input type="text" class="easyui-validatebox" name="volume" /></td>
				</tr>
				<tr>
					<td>取件地址</td>
					<td colspan="3"><input type="text" class="easyui-validatebox" name="pickaddress" required="true" size="144"/></td>
				</tr>
				<tr>
					<td>到达城市:</td>
					<td><input type="text" class="easyui-validatebox" name="arrivecity" /></td>
					<td>预约取件时间:</td>
					<td><input type="text" class="easyui-datebox" name="pickdate" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td colspan="3"><textarea rows="5" cols="80" type="text" class="easyui-validatebox" name="remark" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<s:debug></s:debug>
</body>
</html>