package com.pipi.service;

import java.util.List;

import com.pipi.dao.pojo.OrderItem;

public interface OrderItemService {
	/**
	 * 保存订单菜品
	 * @param orderId
	 * @param openid
	 */
	void saveOrderItem(String orderId, String openid);

	/**
	 * 通过orderId得到订单的菜
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getOrderItemByOrderId(String orderId);
	
}
