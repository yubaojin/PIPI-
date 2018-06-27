package com.pipi.service;

import com.pipi.dao.pojo.OrderAddress;

public interface OrderAddressService {
	/**
	 * 保存订单地址
	 * 
	 * @param orderId
	 * @param openid
	 */
	void saveAddress(String orderId, Integer selectedAddrId);

	/**
	 * 根据订单id得到订单收件人地址
	 * 
	 * @param orderId
	 * @return
	 */
	OrderAddress getAddressByOrderId(String orderId);
}
