package com.pipi.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pipi.constant.EvaluateConstant;
import com.pipi.constant.OrderConstant;
import com.pipi.dao.mapper.OrderMainMapper;
import com.pipi.dao.pojo.Disk;
import com.pipi.dao.pojo.OrderAddress;
import com.pipi.dao.pojo.OrderItem;
import com.pipi.dao.pojo.OrderMain;
import com.pipi.dao.pojo.OrderMainExample;
import com.pipi.dao.pojo.OrderMainExample.Criteria;
import com.pipi.entity.OrderBusinessShow;
import com.pipi.entity.OrderDetail;
import com.pipi.entity.OrderSample;
import com.pipi.entity.Statistics;
import com.pipi.service.CartService;
import com.pipi.service.DiskService;
import com.pipi.service.OrdeMainService;
import com.pipi.service.OrderAddressService;
import com.pipi.service.OrderItemService;
import com.pipi.utils.PIPIUtils;
import com.pipi.utils.sql.Query;

/**
 *订单主题服务实现类 
 *
 */
@Service
@Transactional
public class OrderMainServiceImpl implements OrdeMainService {

	@Autowired
	private OrderMainMapper orderMainMapper;

	@Autowired
	private OrderAddressService orderAddrService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private CartService cartService;

	@Autowired
	private DiskService diskService;

	@Override
	public String saveOrder(String openid, String totalMoney, String remarks, int selectedAddrId) {

		// 订单插入
		String orderId = PIPIUtils.getOrderId();
		OrderMain order = new OrderMain();
		order.setId(orderId);
		order.setOpenid(openid);
		order.setTotalMoney(totalMoney);
		order.setStatus(OrderConstant.NO_PAY);// 状态：1、未付款，2、已付款，3、已接单 ，4、已发货 5、交易成功，7、交易取消（未付款请款下取消）8、交易取消（付款情况下取消记得退款）
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		if (StringUtils.isNotBlank(remarks)) {
			order.setRemarks(remarks);
		}
		order.setIsEvaluate(EvaluateConstant.NO_EVAL);// 1-未评价 2-评价
		orderMainMapper.insert(order);
		// 存入地址
		orderAddrService.saveAddress(orderId, selectedAddrId);
		// 存入列表详情
		orderItemService.saveOrderItem(orderId, openid);
		// 删除购物车中的已经生成订单的商品
		cartService.delSelectedCart(openid);
		// 返回订单id
		return orderId;
	}

	/**
	 * 改变订单状态
	 */
	@Override
	public void changeStatus(String orderId, int flag) {

		OrderMain order = orderMainMapper.selectByPrimaryKey(orderId);
		order.setStatus(flag);
		// 如果改为2 位已付款 植入付款时间
		if (flag == OrderConstant.YES_PAY) {
			order.setPaymentTime(new Date());

		}
		// 如果状态改为4 即发货状态 应该更新发货时间
		if (flag == OrderConstant.DILIVER) {
			order.setConsignTime(new Date());
		}
		// 如果状态改为已完成 那么设置完成时间并且把销售商品数加1
		if (flag == OrderConstant.COMPLETE) {
			order.setEndTime(new Date());
			this.addDiskCount(orderId);
		}
		// 如果状态改为已取消 那么设置关闭时间
		if (flag >= OrderConstant.CANCEL_NO_PAY) {
			order.setCloseTime(new Date());
			if (flag == OrderConstant.CANCEL_PAY) {
				// 做退款操作
			}
		}

		// 更新订单更新时间
		order.setUpdateTime(new Date());
		orderMainMapper.updateByPrimaryKey(order);

	}

	@Override
	public List<OrderSample> getAllOrderSample(String openid) {

		// 查出订单主信息
		OrderMainExample example = new OrderMainExample();
		example.setOrderByClause("create_time DESC");
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openid);
		List<OrderMain> orderMain = orderMainMapper.selectByExample(example);
		// 创建返回对象
		List<OrderSample> sampleOrderList = new ArrayList<OrderSample>();
		// 遍历订单
		for (OrderMain order : orderMain) {
			OrderSample sampleOrder = new OrderSample();
			sampleOrder.setOrderId(order.getId());
			sampleOrder.setStatus(order.getStatus());
			sampleOrder.setTotalMoney(order.getTotalMoney());
			sampleOrder.setIsEvaluate(order.getIsEvaluate());
			sampleOrder.setItems(orderItemService.getOrderItemByOrderId(order.getId()));
			sampleOrderList.add(sampleOrder);
		}
		return sampleOrderList;
	}

	/**
	 * 根据订单id得到订单详情
	 */
	@Override
	public OrderDetail getOrderDetailByOrderId(String orderId) {
		// 创建返回数据对象
		OrderDetail orderDetail = new OrderDetail();
		// 得到订单信息并且植入返回对象
		OrderMain orderMain = orderMainMapper.selectByPrimaryKey(orderId);
		orderDetail.setOrderId(orderId);
		orderDetail.setConsignTime(orderMain.getConsignTime());
		orderDetail.setCreateTime(orderMain.getCreateTime());
		orderDetail.setStatus(orderMain.getStatus());
		orderDetail.setTotalMoney(orderMain.getTotalMoney());
		orderDetail.setRemarks(orderMain.getRemarks());
		// 得到订单地址信息 并植入返回对象
		orderDetail.setOrderAddr(orderAddrService.getAddressByOrderId(orderId));
		// 得到订单菜品并植入对象
		orderDetail.setItems(orderItemService.getOrderItemByOrderId(orderId));
		// 返回
		return orderDetail;
	}

	@Override
	public List<OrderMain> getOrderByStutus(int status) {
		OrderMainExample example = new OrderMainExample();
		example.setOrderByClause("create_time ASC");
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(status);
		List<OrderMain> list = orderMainMapper.selectByExample(example);
		return list;
	}

	/**
	 * 更新orderMain
	 */
	@Override
	public void updateOrder(OrderMain orderMain) {
		orderMainMapper.updateByPrimaryKey(orderMain);
	}

	/**
	 * 商家得到订单列表
	 */
	@Override
	public Map<String, Object> getOrders(int page, int rows, int status) {
		Page<OrderMain> pageInfo = PageHelper.startPage(page, rows);
		// 先得到订单主信息
		OrderMainExample example = new OrderMainExample();
		example.setOrderByClause("create_time DESC");
		Criteria criteria = example.createCriteria();
		if (status != 0) {
			if (status == 7) {
				criteria.andStatusGreaterThanOrEqualTo(status);
			} else {
				criteria.andStatusEqualTo(status);
			}

		}

		List<OrderMain> list = orderMainMapper.selectByExample(example);
		List<OrderBusinessShow> orders = new ArrayList<OrderBusinessShow>();
		for (OrderMain orderMain : list) {
			// 把主信息注入返回对象中
			OrderBusinessShow orderBusinessShow = new OrderBusinessShow();
			orderBusinessShow.setCreateTime(orderMain.getCreateTime());
			orderBusinessShow.setOrderId(orderMain.getId());
			orderBusinessShow.setRemarks(orderMain.getRemarks());
			orderBusinessShow.setStatus(orderMain.getStatus());
			orderBusinessShow.setTotalMoney(orderMain.getTotalMoney());
			// 得到地址中的电话
			OrderAddress address = orderAddrService.getAddressByOrderId(orderMain.getId());
			orderBusinessShow.setMobile(address.getMobile());
			// 把信息注入返回列表中
			orders.add(orderBusinessShow);
		}
		// 返回对象
		Map<String, Object> map = new HashMap<>();
		map.put("rows", orders);
		map.put("total", pageInfo.getTotal());
		return map;
	}

	/**
	 * 收入统计 query里面字段主要是根据status startDate endDate
	 */
	@Override
	public Statistics getStatistics(Query query) throws ParseException {

		Statistics statistics = orderMainMapper.getStatistics(query);

		return statistics;
	}

	/**
	 * 把该订单中所有商品销售数加1
	 * 
	 * @param orderId
	 */
	private void addDiskCount(String orderId) {
		List<OrderItem> orderItems = orderItemService.getOrderItemByOrderId(orderId);
		for (OrderItem orderItem : orderItems) {
			Disk disk = diskService.getDiskById(orderItem.getDiskId());
			// 如果该商品查不到 说明已经被删除 跳过
			if (disk == null) {
				continue;
			}
			disk.setSellnum(disk.getSellnum() + orderItem.getNum());
			diskService.update(disk);
		}
	}
}
