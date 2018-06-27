package com.pipi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pipi.dao.mapper.OrderItemMapper;
import com.pipi.dao.pojo.OrderItem;
import com.pipi.dao.pojo.OrderItemExample;
import com.pipi.dao.pojo.OrderItemExample.Criteria;
import com.pipi.entity.Cart;
import com.pipi.service.CartService;
import com.pipi.service.OrderItemService;
/**
 * 订单商品服务实现类
 *
 */
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
	 * 保存订单菜品
	 */
	@Override
	public void saveOrderItem(String orderId, String openid) {

		List<Cart> list = cartService.getSelectedCart(openid);

		for (Cart cart : list) {
			OrderItem item = new OrderItem();
			item.setImg(cart.getImg());
			item.setNum(cart.getNum());
			item.setOrderId(orderId);
			item.setPrice(cart.getPrice());
			item.setDisPrice(cart.getDisPrice());
			item.setTitle(cart.getTitle());
			item.setDiskId(cart.getId());
			item.setTotalPrice(cart.getNum() * cart.getDisPrice());
			// 保存到数据库
			orderItemMapper.insert(item);
		}
	}

	/**
	 * 通过orderId得到订单的菜
	 */
	@Override
	public List<OrderItem> getOrderItemByOrderId(String orderId) {
		OrderItemExample example = new OrderItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<OrderItem> list = orderItemMapper.selectByExample(example);
		return list;
	}

}
