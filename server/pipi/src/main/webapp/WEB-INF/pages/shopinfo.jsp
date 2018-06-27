<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="padding: 10px 60px 20px 60px">
	<form id="ff" method="post">
		<table cellpadding="5">
			<tr>
				<td>店铺名称:</td>
				<td><input class="easyui-textbox" type="text" name="name"
					data-options="required:true"></input></td>
			</tr>
			<tr>
				<td>店铺地址:</td>
				<td><input class="easyui-textbox" type="text" name="address"
					data-options="required:true"></input></td>
			</tr>
			<tr>
				<td>配送时间:</td>
				<td><input class="easyui-textbox" type="text" name="time"
					data-options="required:true"></input></td>
			</tr>
			<tr>
				<td>联系号码:</td>
				<td><input class="easyui-textbox" type="text" name="phone"
					data-options="required:true"></input></td>
			</tr>
		</table>
	</form>
	<div style="padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">保存</a>
	</div>
</div>
<script>
	$(function() {
		loadLocal();
	});
	function loadLocal() {
		$.ajax({
			url : "/shop/getInfo",
			data : {},
			success : function(res) {
				if (res.status == 200) {
					$('#ff').form('load', res.data);
				} else {
					alert(res.msg);
				}
			}
		});
	}
	function submitForm() {
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/shop/updateInfo", $("#ff").serialize(), function(data) {
			if (data.status == 200) {
				alert("保存成功");
				loadLocal();
			} else {
				$.messager.alert(data.msg);
			}
		});
	}
	function clearForm() {
		$('#ff').form('clear');
	}
</script>