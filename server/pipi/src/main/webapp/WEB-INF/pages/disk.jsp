<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="商品列表" data-options="singleSelect:false,collapsible:true,pagination:true,url:'/disk/list',method:'get',pageSize:30,toolbar:toolbar">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:60">商品ID</th>
			<th data-options="field:'title',width:200">商品标题</th>
			<th data-options="field:'menu',width:100">叶子类目</th>
			<th data-options="field:'sellPoint',width:100">卖点</th>
			<th data-options="field:'sellnum',width:100,align:'right'">已卖数量</th>
			<th data-options="field:'price',width:70,align:'right',formatter:PIPI.formatPrice">原价</th>
			<th data-options="field:'disPrice',width:70,align:'right',formatter:PIPI.formatPrice">折扣价</th>
			<th data-options="field:'status',width:60,align:'center',formatter:PIPI.formatItemStatus">状态</th>
			<th data-options="field:'created',width:130,align:'center',formatter:PIPI.formatDateTime">创建日期</th>
			<th data-options="field:'updated',width:130,align:'center',formatter:PIPI.formatDateTime">更新日期</th>
			<th data-options="field:'img',width:50,align:'center',formatter:PIPI.formatUrl">图片</th>
		</tr>
	</thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/pages/disk-edit'" style="width: 80%; height: 80%; padding: 10px;"></div>
<script>
	function getSelectionsIds() {
		var itemList = $("#itemList");
		var sels = itemList.datagrid("getSelections");
		var ids = [];
		for(var i in sels) {
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}

	var toolbar = [{
			text: '新增',
			iconCls: 'icon-add',
			handler: function() {
				$(".tree-title:contains('新增商品')").parent().click();
			}
		},
		{
			text: '编辑',
			iconCls: 'icon-edit',
			handler: function() {
				var ids = getSelectionsIds();
				if(ids.length == 0) {
					$.messager.alert('提示', '必须选择一个商品才能编辑!');
					return;
				}
				if(ids.indexOf(',') > 0) {
					$.messager.alert('提示', '只能选择一个商品!');
					return;
				}

				$("#itemEditWindow").window({
					onLoad: function() {
						//回显数据
						var data = $("#itemList").datagrid("getSelections")[0];
						data.priceView = PIPI.formatPrice(data.price);
						
						$("#itemeEditForm").form("load", data);

						PIPI.initItemCat({
							"menu": data.menu,
						});
						PIPI.initPic({
							"pic": data.img,
						});
					}
				}).window("open");
			}
		},
		{
			text: '删除',
			iconCls: 'icon-cancel',
			handler: function() {
				var ids = getSelectionsIds();
				if(ids.length == 0) {
					$.messager.alert('提示', '未选中商品!');
					return;
				}
				$.messager
					.confirm(
						'确认',
						'确定删除ID为 ' + ids + ' 的商品吗？',
						function(r) {
							if(r) {
								var params = {
									"ids": ids
								};
								$
									.post(
										"/disk/delete",
										params,
										function(data) {
											if(data.status == 200) {
												$.messager
													.alert(
														'提示',
														'删除商品成功!',
														undefined,
														function() {
															$(
																	"#itemList")
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
			text: '下架',
			iconCls: 'icon-remove',
			handler: function() {
				var ids = getSelectionsIds();
				if(ids.length == 0) {
					$.messager.alert('提示', '未选中商品!');
					return;
				}
				$.messager
					.confirm(
						'确认',
						'确定下架ID为 ' + ids + ' 的商品吗？',
						function(r) {
							if(r) {
								var params = {
									"ids": ids
								};
								$
									.post(
										"/disk/down",
										params,
										function(data) {
											if(data.status == 200) {
												$.messager
													.alert(
														'提示',
														'下架商品成功!',
														undefined,
														function() {
															$(
																	"#itemList")
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
			text: '上架',
			iconCls: 'icon-remove',
			handler: function() {
				var ids = getSelectionsIds();
				if(ids.length == 0) {
					$.messager.alert('提示', '未选中商品!');
					return;
				}
				$.messager
					.confirm(
						'确认',
						'确定上架ID为 ' + ids + ' 的商品吗？',
						function(r) {
							if(r) {
								var params = {
									"ids": ids
								};
								$
									.post(
										"/disk/up",
										params,
										function(data) {
											if(data.status == 200) {
												$.messager
													.alert(
														'提示',
														'上架商品成功!',
														undefined,
														function() {
															$(
																	"#itemList")
																.datagrid(
																	"reload");
														});
											}
										});
							}
						});
			}
		}
	];
</script>