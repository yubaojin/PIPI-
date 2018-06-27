<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding: 10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
		<table cellpadding="5">
			<tr>
				<td>商品类目:</td>
				<td><a href="javascript:void(0)"
					class="easyui-linkbutton selectItemCat">选择类目</a> 
					<input
					type="hidden" name="menuId" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>商品标题:</td>
				<td><input class="easyui-textbox" type="text" name="title"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>商品卖点:</td>
				<td><input class="easyui-textbox" name="sellPoint"
					data-options="multiline:true,validType:'length[0,150]'"
					style="height: 60px; width: 280px;"></input></td>
			</tr>
			<tr>
				<td>商品价格:</td>
				<td><input class="easyui-numberbox" type="text" name="price"
					data-options="min:1,max:99999999,precision:2,required:true" /></td>
			</tr>
			<tr>
				<td>商品折扣价格:</td>
				<td><input class="easyui-numberbox" type="text" name="disPrice"
					data-options="min:1,max:99999999,precision:2,required:true" /></td>
			</tr>
			<tr>
	            <td>商品图片:</td>
	            <td>
	                <input type="hidden" name="img" />
	                <a href="javascript:void(0)" class="easyui-linkbutton onePicUpload">图片上传</a>
	            </td>
	        </tr>
		</table>
	</form>
	<div style="padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">提交</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//页面初始化完毕后执行此方法
	$(function() {
		//初始化类目选择和图片上传器
		PP.init();
	});
	//提交表单
	function submitForm() {

		//有效性验证
		if (!$('#itemAddForm').form('validate')||!$("#itemAddForm [name=menuId]").val()) {
			$.messager.alert('提示', '表单还未填写完成!');
			return;
		}

		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/disk/save", $("#itemAddForm").serialize(), function(data) {
			if (data.status == 200) {
				$.messager.alert('提示', '新增商品成功!');
			}
		});
	}

	function clearForm() {
		$('#itemAddForm').form('reset');
	}
</script>
