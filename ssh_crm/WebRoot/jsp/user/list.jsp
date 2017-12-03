<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>业务员列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">
	function changePage(page) {
		$("#currentPage_Hbtn").val(page);
		$("#pageForm").submit();
	}
	function changeCount(count) {
		$("#pageCount_Hbtn").val(count);
		$("#pageForm").submit();
	}
	function selectUser(user_id, user_name){
		var win = window.opener;
		var document = win.document;
		
		document.getElementById("user_id_Hbtn").value=user_id;
		document.getElementById("user_name_Btn").value=user_name;
		
		window.close();
	}
</script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：访问记录管理 &gt; 业务员列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>	
										<FORM id="pageForm" name="customerForm" action="${pageContext.request.contextPath }/userAction_userList" method=post>
											<!-- 用于提交当前页的隐藏域 -->
											<input type="hidden" id="currentPage_Hbtn" name="curr_Page" value="${param.curr_Page }">
											<!-- 用户提交每页条数的隐藏域 -->
											<input type="hidden" id="pageCount_Hbtn" name="page_Count" value="${param.page_Count }">
											<%-- <TABLE cellSpacing=0 cellPadding=2 border=0>
												<TBODY>
													<TR>
														<TD>客户名称：</TD>
														<TD>
															<input type="hidden" id="cust_id_Hbtn" name="customer.cust_id" value="${param['customer.cust_id'] }">
															<INPUT class=textbox style="WIDTH: 80px" maxLength=50 name="customer.cust_name" id="cust_name_Btn" value="${param['customer.cust_name'] }" >
															<input type="button" value="选择客户" onclick="window.open('${pageContext.request.contextPath}/customerAction_custList?select=true','','width=600,height=300')">
														</TD>
														<TD>
															<INPUT class=button id=sButton2 type=submit value=" 筛选 " >
														</TD>
													</TR>
												</TBODY>
											</TABLE> --%>
										</FORM>
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>业务员名称</TD>
													<TD>登录用户名</TD>
												</TR>
												<s:iterator value="#pageBean.beanList" var="user" >
													<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD><s:property value="#user.user_name" /></TD>
														<TD><s:property value="#user.user_code" /></TD>
														<TD>
															<input type="button" value="选择" onclick="selectUser('${user.user_id}','${user.user_name }')" >
														</TD>
													</TR>
												</s:iterator>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${pageBean.totalCount }</B>]条记录,[<B>${pageBean.totalPage }</B>]页
												,每页显示 <select name="pageCount"
												onchange="changeCount($('#pageCount_sel option:selected').val())" id="pageCount_sel">
												<option value="3"
													<s:property value="#pageBean.currentPage==3?'selected':''" />>3</option>
												<option value="5"
													<s:property value="#pageBean.currentPage==5?'selected':''" />>5</option>
											</select> 条
												[<A href="javaScript:void(0)" onclick="changePage(<s:property value="#pageBean.currentPage-1" />)" >前一页</A>]
												<B><s:property value="#pageBean.currentPage" /></B>
												[<A href="javaScript:void(0)" onclick="changePage(<s:property value="#pageBean.currentPage+1" />)" >后一页</A>] 
												到
												<input type="text" size="3" id="page_text" name="page" />
												页
												<input type="button" value="Go" onclick="changePage($('#page_text').val())" />
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	<s:debug></s:debug>
</BODY>
</HTML>
