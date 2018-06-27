package com.pipi.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pipi.constant.OrderConstant;
import com.pipi.dao.pojo.OrderItem;
import com.pipi.entity.OrderDetail;
import com.pipi.entity.OrderSample;
import com.pipi.entity.Result;
import com.pipi.entity.Statistics;
import com.pipi.service.OrdeMainService;
import com.pipi.service.OrderItemService;
import com.pipi.utils.PIPIUtils;
import com.pipi.utils.sql.Query;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrdeMainService orderMainService;

	@Autowired
	private OrderItemService orderItemService;

	/**
	 * 生成订单
	 * 
	 * @param openid
	 * @param totalMoney
	 * @param remarks
	 * @return
	 */
	@RequestMapping("/saveOrder")
	public Result saveOrder(String openid, String totalMoney, String remarks, Integer selectedAddrId) {
		String orderId = orderMainService.saveOrder(openid, totalMoney, remarks, selectedAddrId);
		return Result.ok(orderId);
	}

	@RequestMapping("/changeStatus")
	public Result changeStatus(String orderId, Integer flag) {
		orderMainService.changeStatus(orderId, flag);
		return Result.ok();
	}

	/**
	 * 得到openID用户的所有订单
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping("/getAllOrder")
	public Result getAllOrderSample(String openid) {
		List<OrderSample> list = orderMainService.getAllOrderSample(openid);
		return Result.ok(list);
	}

	/**
	 * 根据订单id得到订单详情
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/getOrderDetailByOrderId")
	public Result getOrderDetailByOrderId(String orderId) {
		OrderDetail detail = orderMainService.getOrderDetailByOrderId(orderId);
		return Result.ok(detail);
	}

	/**
	 * 商家获取所有订单
	 * 
	 * @return
	 */
	@RequestMapping("/getOrders")
	public Map<String, Object> getOrders(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer rows, @RequestParam(defaultValue = "0") Integer status) {
		Map<String, Object> map = orderMainService.getOrders(page, rows, status);
		return map;
	}

	/**
	 * 根据orderId得到菜单商品列表
	 */
	@RequestMapping("/getDisksByOrderId")
	public Result getDisksByOrderId(String orderId) {
		List<OrderItem> list = orderItemService.getOrderItemByOrderId(orderId);
		return Result.ok(list);
	}

	@RequestMapping("/getStatistics")
	public Result getStatistics(Map<String, Object> params) throws ParseException {

		// 查取消 未付款取消
		params.put("status", OrderConstant.CANCEL_NO_PAY);
		Statistics CANCEL_NO_PAY = orderMainService.getStatistics(new Query(params));

		// 查取消 付款后取消
		params.put("status", OrderConstant.CANCEL_PAY);
		Statistics CANCEL_PAY = orderMainService.getStatistics(new Query(params));

		Statistics cancel = new Statistics();
		cancel.setSum(CANCEL_NO_PAY.getSum() + CANCEL_PAY.getSum());
		double c1 = Double.parseDouble(CANCEL_NO_PAY.getTotal() == null ? "0" : CANCEL_NO_PAY.getTotal());
		double c2 = Double.parseDouble(CANCEL_PAY.getTotal() == null ? "0" : CANCEL_PAY.getTotal());
		cancel.setTotal(PIPIUtils.formatMoney(String.valueOf(c1 + c2)));

		// 查完成
		params.put("status", OrderConstant.COMPLETE);
		Statistics complete = orderMainService.getStatistics(new Query(params));
		complete.setTotal(complete.getTotal() == null ? "0" : PIPIUtils.formatMoney(complete.getTotal()));

		Map<String, Statistics> map = new HashMap<String, Statistics>();
		map.put("cancel", cancel);
		map.put("complete", complete);

		return Result.ok(map);
	}

}
