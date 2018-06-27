<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="easyui-panel" title="Nested Panel"
	data-options="width:'100%',minHeight:800,noheader:true,border:false"
	style="padding: 10px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:false"
			style="width: 250px; padding: 5px">
			<ul id="evalCategoryTree" class="easyui-tree">
				<li id="0"><span>订单总览</span>
					<ul>
						<li id="3">好评</li>
						<li id="2">中评</li>
						<li id="1">差评</li>
					</ul></li>
			</ul>
		</div>
		<div data-options="region:'center'" style="padding: 5px">
			<table class="easyui-datagrid" id="evalList" title="评价列表"
				data-options="singleSelect:true,collapsible:true,pagination:true,url:'/evaluate/getEvals',method:'get',pageSize:30,remoteSort:false,multiSort:true">
				<thead>
					<tr>
						<th data-options="field:'id',width:60,sortable:true">评价ID</th>
						<th data-options="field:'diskName',width:200,sortable:true">商品标题</th>
						<th
							data-options="field:'evalValue',width:80,align:'center',formatter:PIPI.formatEavlStatus,sortable:true">评价等级</th>
						<th data-options="field:'content',width:600,sortable:true">评价内容</th>
						<th
							data-options="field:'created',width:130,align:'center',formatter:PIPI.formatDateTime,sortable:true">创建日期</th>
						<th
							data-options="field:'updated',width:130,align:'center',formatter:PIPI.formatDateTime,sortable:true">更新日期</th>
						<th
							data-options="field:'imgs',width:150,align:'center',formatter:PIPI.formatUrl">图片</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script>
	$(function() {
		var tree = $("#evalCategoryTree");
		var datagrid = $("#evalList");
		tree.tree({
			onClick : function(node) {
				datagrid.datagrid('reload', {
					evalValue : node.id
				});
			}
		});
	});
</script>