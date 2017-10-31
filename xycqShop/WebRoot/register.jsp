<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
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

.error {
	color: red;
	font-size: 12px;
}
</style>

<script type="text/javascript">
	$(function(){
		$.validator.addMethod("checkUserName", function(value, element, param) {
			//value:被验证的值， element:当前验证的dom对象，param:参数(多个即是数组)
			var isNotExist = true;
			if(param) {
			
				$.ajax({
					//这里只能同步，因为下面的操作需要获取这里的返回结果
					"async": false,
					"data": {"username": value, "method": "checkUserName"},
					"dataType": "json",
					"type": "POST",
					"url": "UserServlet",
					"success": function(data) {
						isNotExist = data.isExist;
					}
				});
			}
			return !isNotExist;
		});
		
		$("#checkCode").blur(function(){
			var checkcode_res = document.getElementById("checkCode").value;
			
			$.ajax({
				"async": true,
				"data": {"checkcode_res": checkcode_res, "method": "checkCode"},
				"dataType": "json",
				"type": "POST",
				"url": "UserServlet",
				"success": function(data) {
					var flag = !data.res;
					if(flag) {
						$("#checkCode_span").css("display", "block");
					}else {
						$("#checkCode_span").css("display", "none");
					}
				}
			});
		});
		
		$("#userInfo").validate({
			"rules": {
				"username": {
					"required": true,
					"maxlength": 5,
					"checkUserName": true
				},
				"password": {
					"required": true,
					"maxlength": 16,
					"minlength": 6
				},
				"confirmpwd": {
					"required": true,
					"equalTo": "#id_Password"
				},
				"email": {
					"required": true,
					"email": true
				},
				"name": {
					"required": true,
					"maxlength": 8
				},
				"sex": {
					"required": true
				}
			},
			"messages": {
				"username": {
					"required": "用户名不能为空",
					"maxlength": "用户名不能超过五个字符",
					"checkUserName": "用户名已存在"
				},
				"password": {
					"required": "密码不能为空",
					"maxlength": "密码长度不能超过16个字符",
					"minlength": "密码长度不能低于6个字符"
				},
				"confirmpwd": {
					"required": "确认密码不能为空",
					"equalTo": "密码输入不一致"
				},
				"email": {
					"required": "邮箱不能为空",
					"email": "邮箱格式错误"
				},
				"name": {
					"required": "姓名不能为空",
					"maxlength": "长度不能超过8个字符"
				},
				"errorElement": "label"
			}
		});
	});
	
</script>

</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				会员注册 USER REGISTER
				<form id="userInfo" class="form-horizontal" style="margin-top: 5px;" action="UserServlet" method="post">
					<input type="hidden" name="method" value="register">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="id_Password" name="password" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd" name="confirmpwd" placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3" name="email" placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption" name="name" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> 
								<input type="radio" name="sex" id="male_id" value="male"> 男
							</label> 
							<label class="radio-inline"> 
								<input type="radio" name="sex" id="female_id" value="female"> 女
							</label>
							<label class="error" for="sex" style="display: none;">请选择您的性别</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday">
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="checkCode" name="checkCode" >
							<span id="checkCode_span" class="error" style="display: none;">验证码错误</span>
						</div>
						<div class="col-sm-2">
							<img src="CheckImgServlet " alt="验证码" id="check_code_img" />
							<a href="javascript: reload();">换一张</a>
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit" style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>
	<script type="text/javascript">
		function reload() {
			var time = new Date().getTime();
			$("#check_code_img").prop("src", "CheckImgServlet?time="+time);
			$("#checkCode").val("");
		}
		
	</script>
</body>
</html>
