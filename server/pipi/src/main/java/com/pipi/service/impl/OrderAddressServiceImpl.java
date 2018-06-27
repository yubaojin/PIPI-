package com.pipi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pipi.dao.mapper.OrderAddressMapper;
import com.pipi.dao.pojo.Address;
import com.pipi.dao.pojo.OrderAddress;
import com.pipi.dao.pojo.OrderAddressExample;
import com.pipi.dao.pojo.OrderAddressExample.Criteria;
import com.pipi.service.AddressService;
import com.pipi.service.OrderAddressService;

/**
 * 订单地址服务实现类
 *
 */
@Service
@Transactional
public class OrderAddressServiceImpl implements OrderAddressService {

	@Autowired
	private OrderAddressMapper orderAddrMapper;

	@Autowired
	private AddressService addrService;

	/**
	 * 保存订单地址
	 */
	@Override
	public void saveAddress(String orderId, Integer selectedAddrId) {
		Address address = addrService.getAddrByAddrId(selectedAddrId);
		OrderAddress orderAddr = new OrderAddress();
		orderAddr.setAddress(address.getAddress());
		orderAddr.setGender(address.getGender());
		orderAddr.setMobile(address.getMobile());
		orderAddr.setName(address.getName());
		orderAddr.setOrderId(orderId);
		// 保存到数据库
		orderAddrMapper.insert(orderAddr);
	}

	/**
	 * 根据订单id得到订单收件人地址
	 */
	@Override
	public OrderAddress getAddressByOrderId(String orderId) {
		OrderAddressExample example = new OrderAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<OrderAddress> list = orderAddrMapper.selectByExample(example);
		return list.get(0);
	}

}
