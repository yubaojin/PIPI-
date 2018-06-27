<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="easyui-panel" title="Nested Panel"
	data-options="width:'100%',minHeight:800,noheader:true,border:false"
	style="padding: 10px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:false"
			style="width: 250px; padding: 5px">
			<ul id="menuCategoryTree" class="easyui-tree"
				data-options="url:'/menu/list',animate: true,method : 'GET'">
			</ul>
		</div>
		<div data-options="region:'center'" style="padding: 5px">
			<table class="easyui-datagrid" id="menuList"
				data-options="toolbar:menuListToolbar,singleSelect:false,collapsible:true,pagination:true,method:'get',pageSize:30,url:'/disk/list',queryParams:{menuId:0}">
				<thead>
					<tr>
						<th data-options="field:'id',width:60">商品ID</th>
						<th data-options="field:'title',width:200">商品标题</th>
						<th data-options="field:'menu',width:100">叶子类目</th>
						<th data-options="field:'sellPoint',width:100">卖点</th>
						<th data-options="field:'sellnum',width:100,align:'right'">已卖数量</th>
						<th
							data-options="field:'price',width:70,align:'right',formatter:PIPI.formatPrice">原价</th>
						<th
							data-options="field:'disPrice',width:70,align:'right',formatter:PIPI.formatPrice">折扣价</th>
						<th
							data-options="field:'status',width:60,align:'center',formatter:PIPI.formatItemStatus">状态</th>
						<th
							data-options="field:'created',width:130,align:'center',formatter:PIPI.formatDateTime">创建日期</th>
						<th
							data-options="field:'updated',width:130,align:'center',formatter:PIPI.formatDateTime">更新日期</th>
						<th
							data-options="field:'img',width:50,align:'center',formatter:PIPI.formatUrl">图片</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		var tree = $("#menuCategoryTree");
		var datagrid = $("#menuList");
		tree.tree({
			onClick : function(node) {
				datagrid.datagrid('reload', {
					menuId : node.id
				});
			}
		});
	});
	var menuListToolbar = [
			{
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					var node = $("#menuCategoryTree").tree("getSelected");
					if (!node
							|| !$("#menuCategoryTree").tree("isLeaf",
									node.target)) {
						$.messager.alert('提示', '新增内容必须选择一个内容分类!');
						return;
					}
					PP.createWindow({
						url : "/pages/menu-disk-add"
					});
				}
			},
			{
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var ids = PP.getSelectionsIds("#menuList");
					if (ids.length == 0) {
						$.messager.alert('提示', '必须选择一个内容才能编辑!');
						return;
					}
					if (ids.indexOf(',') > 0) {
						$.messager.alert('提示', '只能选择一个内容!');
						return;
					}
					PP.createWindow({
						url : "/pages/menu-disk-edit",
						onLoad : function() {
							var data = $("#menuList").datagrid(
									"getSelections")[0];
							$("#contentEditForm").form("load", data);
							// 实现图片
							if (data.img) {
								PIPI.initPic({
									"pic" : data.img,
								});
							}
						}
					});
				}
			},
			{
				text : '删除',
				iconCls : 'icon-cancel',
				handler : function() {
					var ids = PP.getSelectionsIds("#menuList");
					if (ids.length == 0) {
						$.messager.alert('提示', '未选中商品!');
						return;
					}
					$.messager
							.confirm(
									'确认',
									'确定删除ID为 ' + ids + ' 的内容吗？',
									function(r) {
										if (r) {
											var params = {
												"ids" : ids
											};
											$
													.post(
															"/disk/delete",
															params,
															function(data) {
																if (data.status == 200) {
																	$.messager
																			.alert(
																					'提示',
																					'删除内容成功!',
																					undefined,
																					function() {
																						$(
																								"#menuList")
																								.datagrid(
																										"reload");
																					});
																}
															});
										}
									});
				}
			},
			'-',
			{
				text : '下架',
				iconCls : 'icon-remove',
				handler : function() {
					var ids = PP.getSelectionsIds("#menuList");
					if (ids.length == 0) {
						$.messager.alert('提示', '未选中商品!');
						return;
					}
					$.messager
							.confirm(
									'确认',
									'确定下架ID为 ' + ids + ' 的商品吗？',
									function(r) {
										if (r) {
											var params = {
												"ids" : ids
											};
											$
													.post(
															"/disk/down",
															params,
															function(data) {
																if (data.status == 200) {
																	$.messager
																			.alert(
																					'提示',
																					'下架商品成功!',
																					undefined,
																					function() {
																						$(
																								"#menuList")
																								.datagrid(
																										"reload");
																					});
																}
															});
										}
									});
				}
			},
			{
				text : '上架',
				iconCls : 'icon-remove',
				handler : function() {
					var ids = PP.getSelectionsIds("#menuList");
					if (ids.length == 0) {
						$.messager.alert('提示', '未选中商品!');
						return;
					}
					$.messager
							.confirm(
									'确认',
									'确定上架ID为 ' + ids + ' 的商品吗？',
									function(r) {
										if (r) {
											var params = {
												"ids" : ids
											};
											$
													.post(
															"/disk/up",
															params,
															function(data) {
																if (data.status == 200) {
																	$.messager
																			.alert(
																					'提示',
																					'上架商品成功!',
																					undefined,
																					function() {
																						$(
																								"#menuList")
																								.datagrid(
																										"reload");
																					});
																}
															});
										}
									});
				}
			} ];
</script>