<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理员登录</title>
		<%@include file="/WEB-INF/common.jsp"%>
	</head>

	<body style="background-color: #F3F3F3">
		<div class="easyui-dialog" title="管理员登录" data-options="closable:false,draggable:false" style="width: 400px; height: 300px; padding: 10px;">
			<div style="margin-left: 50px; margin-top: 50px;">
				<div style="margin-bottom: 20px;">
					<div>
						用户名: <input name="username" class="easyui-textbox" data-options="required:true" style="width: 200px; height: 32px" value="root" />
					</div>
				</div>
				<div style="margin-bottom: 20px">
					<div>
						密&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width: 200px; height: 32px" data-options="" />
					</div>
				</div>
				<div>
					<a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width: 100px; height: 32px; margin-left: 50px">登录</a>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$("#login").click(function() {
				var username = $("[name=username]").val();
				var password = $("[name=password]").val();

				if(username.trim() == "" || password.trim() == "") {
					alert("用户名或者密码不能为空!");
					return;
				}
				$.ajax({
					url: "/toLogin",
					data: {
						"username": username,
						"password": password
					},
					success: function(result) {
						if(result.status == 200) {
							window.location.href = "/toIndex"
						} else {
							alert(result.msg);
						}
					}
				});
			});
			$(document.documentElement).on("keyup", function(event) {
				var keyCode = event.keyCode;
				if (keyCode === 13) { // 捕获回车 
					$("#login").click(); // 提交表单
				}
			});
		</script>
	</body>

</html>