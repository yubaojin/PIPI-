<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="easyui-panel" title="Nested Panel" data-options="width:'100%',minHeight:800,noheader:true,border:false" style="padding: 10px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:false" style="width: 250px; padding: 5px">
			<ul id="orderCategoryTree" class="easyui-tree">
				<li id="0"><span>订单总览</span>
					<ul>
						<li id="1">未付款</li>
						<li id="2">已付款</li>
						<li id="3">已接单</li>
						<li id="4">已发货</li>
						<li id="5">已完成</li>
						<li id="7">已取消</li>
					</ul>
				</li>
			</ul>
		</div>
		<div data-options="region:'center'" style="padding: 5px">
			<table class="easyui-datagrid" id="orderList" title="评价列表" data-options="toolbar:orderListToolbar,singleSelect:true,collapsible:true,pagination:true,url:'/order/getOrders',method:'get',pageSize:30,remoteSort:false,multiSort:true">
				<thead>
					<tr>
						<th data-options="field:'orderId',width:200,sortable:true">订单ID</th>
						<th data-options="field:'mobile',width:100,sortable:true">手机号码</th>
						<th data-options="field:'totalMoney',width:40,sortable:true">总价</th>
						<th data-options="field:'status',width:200,sortable:true,formatter:PIPI.formatOrderStatus">状态</th>
						<th data-options="field:'remarks',width:500,sortable:true">备注</th>
						<th data-options="field:'createTime',width:130,align:'center',formatter:PIPI.formatDateTime,sortable:true">创建日期</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script>
	$(function() {
		var tree = $("#orderCategoryTree");
		var datagrid = $("#orderList");
		tree.tree({
			onClick: function(node) {
				datagrid.datagrid('reload', {
					status: node.id
				});
			}
		});
	});
	var orderListToolbar = [{
			text: '接单',
			iconCls: 'icon-ok',
			handler: function() {
				var ids = PP.getSelectionsIds("#orderList");
				if(ids.length == 0) {
					$.messager.alert('提示', '必须选择一个内容才能编辑!');
					return;
				}
				if(ids.indexOf(',') > 0) {
					$.messager.alert('提示', '只能选择一个内容!');
					return;
				}
				if($("#orderList").datagrid("getSelections")[0].status!=2){
					$.messager.alert('提示', '只有在订单是已付款状态下才能接单!');
					return;
				}
				$.messager.confirm(
					'确认',
					'确定接单ID为 ' + ids + ' 的商品吗？',
					function(r) {
						if(r) {
							var params = {
								"orderId": ids, //id列表 但是上面限制了只能选着一个 所以ids只有一个
								"flag": 3,
							};
							$.post(
								"/order/changeStatus",
								params,
								function(data) {
									if(data.status == 200) {
										$.messager.alert(
											'提示',
											'接单成功!',
											undefined,
											function() {
												$("#orderList").datagrid("reload");
											});
									}
								});
						}
					});
			}
		},
		{
			text: '发货',
			iconCls: 'icon-man',
			handler: function() {
				var ids = PP.getSelectionsIds("#orderList");
				if(ids.length == 0) {
					$.messager.alert('提示', '必须选择一个内容才能编辑!');
					return;
				}
				if(ids.indexOf(',') > 0) {
					$.messager.alert('提示', '只能选择一个内容!');
					return;
				}
				if($("#orderList").datagrid("getSelections")[0].status!=3){
					$.messager.alert('提示', '只有在订单是已接单状态下才能发货!');
					return;
				}
				$.messager.confirm(
					'确认',
					'确定发货ID为 ' + ids + ' 的商品吗？',
					function(r) {
						if(r) {
							var params = {
								"orderId": ids, //id列表 但是上面限制了只能选着一个 所以ids只有一个
								"flag": 4,
							};
							$.post(
								"/order/changeStatus",
								params,
								function(data) {
									if(data.status == 200) {
										$.messager.alert(
											'提示',
											'发货成功!',
											undefined,
											function() {
												$("#orderList").datagrid("reload");
											});
									}
								});
						}
					});
			}
		},
		{
			text: '查看',
			iconCls: 'icon-search',
			handler: function() {
				var ids = PP.getSelectionsIds("#orderList");
				if(ids.length == 0) {
					$.messager.alert('提示', '未选中商品!');
					return;
				}
				PP.createWindow({
						url: "/pages/order-detail",
						onLoad: function() {
							var data = $("#orderList").datagrid(
								"getSelections")[0];
							$.post(
									"/order/getOrderDetailByOrderId", {
										orderId: data.orderId,
									},
									function(data, status) {
										console.log("数据: \n",
											data, "\n状态: ",
											status);
										if(data.status = 200) {
											$("#orderId")
												.html(
													data.data.orderId);
											$("#createTime")
												.html(
													data.data.createTime);
											if(data.data.status == 1) {
												$("#status")
													.html(
														"未付款");
											} else if(data.data.status == 2) {
												$("#status")
													.html(
														"已付款");
											} else if(data.data.status == 3) {
												$("#status")
													.html(
														"已接单");
											} else if(data.data.status == 4) {
												$("#status")
													.html(
														"已发货");
											} else if(data.data.status == 5) {
												$("#status")
													.html(
														"已完成");
											} else if(data.data.status == 6) {
												$("#status")
													.html(
														"已关闭");
											} else if(data.data.status >= 7) {
												$("#status")
													.html(
														"已取消");
											} else {
												$("#status")
													.html(
														"未知状态");
											}

											$("#addr")
												.html(
													data.data.orderAddr.name +
													(data.data.orderAddr.gender == 1 ? '(先生)' :
														'(女士)') +
													"\t" +
													data.data.orderAddr.mobile +
													"\t" +
													data.data.orderAddr.address);

											if(data.data.remarks) {
												$("#remarks")
													.html(
														data.data.remarks);
											} else {
												$("#remarks")
													.html(
														"无备注");
											}
											var info = "";
											for(var i = 0; i < data.data.items.length; i++) {
												info += "<hr/><img src='" + data.data.items[i].img + "' width='50' height='50'>" +
													"\t标题：" +
													data.data.items[i].title +
													"\t单价：" +
													data.data.items[i].disPrice +
													"\t数量：" +
													data.data.items[i].num +
													"\t总价：" +
													data.data.items[i].totalPrice;
											}
											info += "<hr/>\t<h2>合计：" +
												data.data.totalMoney +
												"</h2>";

											$("#items").html(
												info);

										} else {
											alert(data.msg);
										}

									});
						}
					});
			}
		},
		'-',
		{
			text: '退单',
			iconCls: 'icon-cancel',
			handler: function() {
				var ids = PP.getSelectionsIds("#orderList");
				if(ids.length == 0) {
					$.messager.alert('提示', '未选中商品!');
					return;
				}
				if(($("#orderList").datagrid("getSelections")[0].status<=1)||($("#orderList").datagrid("getSelections")[0].status>=5)){
					$.messager.alert('提示', '只有在订单是付款、接单、发货状态下才能退单!');
					return;
				}
				$.messager.confirm(
					'确认',
					'确定退单ID为 ' + ids + ' 的商品吗？',
					function(r) {
						if(r) {
							var params = {
								"orderId": ids, //id列表 但是上面限制了只能选着一个 所以ids只有一个
								"flag": 8,
							};
							$.post(
								"/order/changeStatus",
								params,
								function(data) {
									if(data.status == 200) {
										$.messager.alert(
											'提示',
											'退单成功!',
											undefined,
											function() {
												$("#orderList").datagrid("reload");
											});
									}
								});
						}
					});
			}
		}
	];
</script>