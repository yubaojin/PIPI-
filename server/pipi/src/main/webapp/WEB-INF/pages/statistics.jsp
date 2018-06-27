<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="padding: 10px 10px 10px 10px">
	<form id="statisticsForm" class="itemForm" method="post">
		<table>
			<tr>
				<td>开始日期:</td>
				<td><input class="easyui-datebox"
					data-options="sharedCalendar:'#cc'" id="startDate" name="startDate"></td>
				<td>结束日期:</td>
				<td><input class="easyui-datebox"
					data-options="sharedCalendar:'#cc'" id="endDate" name="endDate"></td>
			</tr>
		</table>
		<div id="cc" class="easyui-calendar"></div>
	</form>
	<table style="font-size: 30px;">
		<td style="padding: 25px; margin: 100px 50px;">
		<td>单数</td>
		<td>小计</td>
		</td>
		<tr style="padding: 25px; margin: 100px 50px;">
			<td>取消：</td>
			<td id="cancelSum">5</td>
			<td id="cancelTotal">18</td>
		</tr>
		<tr style="padding: 25px; margin: 100px 50px;">
			<td>完成：</td>
			<td id="okSum">20</td>
			<td id="okTotal">100</td>
		</tr>
	</table>
	<div style="padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">查询</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//页面初始化完毕后执行此方法
	$(function() {
		getstatistics();
	});
	//提交表单
	function submitForm() {

		//有效性验证
/* 		if (!$('#statisticsForm').form('validate')) {
			$.messager.alert('提示', '查询日期还未选着!');
			return;
		} */

		getstatistics();
	}

	function getstatistics() {
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/order/getStatistics", $("#statisticsForm").serialize(),
				function(data) {
					if (data.status == 200) {
						$("#cancelSum").text(data.data.cancel.sum);
						$("#cancelTotal").text("¥ " + data.data.cancel.total);
						$("#okSum").text(data.data.complete.sum);
						$("#okTotal").text("¥ " + data.data.complete.total);
					} else {
						$.messager.alert(data.msg);
					}
				});
	}

	function clearForm() {
		$('#statisticsForm').form('reset');
	}
</script>
