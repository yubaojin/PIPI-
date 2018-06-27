package com.pipi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pipi.dao.mapper.DiskMapper;
import com.pipi.dao.pojo.Disk;
import com.pipi.entity.Cart;
import com.pipi.service.CartService;
import com.pipi.utils.jedis.JedisClient;
import com.pipi.utils.json.JsonUtils;

/**
 * 购物车相关操作的服务实现类
 * 
 * @author yubaojin
 *
 */
@Service
@Transactional
@PropertySource(value = { "classpath:redis.properties" }, ignoreResourceNotFound = true)
public class CartServiceImpl implements CartService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${redis.cart.name}")
	private String PIPI_CART_NAME;

	@Autowired
	private DiskMapper diskMapper;

	/**
	 * 根据用户id得到购物车列表
	 */
	@Override
	public List<Cart> getAllCart(String openid) {

		// 获取购物车json字符串
		String cartsJson = jedisClient.get(PIPI_CART_NAME + ":" + openid);
		// 如果json字符串为空 返货空集合
		if (!StringUtils.isNotBlank(cartsJson)) {
			/**
			 * 注意：这个不能返回Collections.EMPTY_LIST Collections.EMPTY_LIST是immutable不可变类
			 * 不能对其进行增加会抛出java.lang.UnsupportedOperationException
			 */
			return new ArrayList<Cart>();
		}
		// 如果不为空 转换成集合对象返回
		List<Cart> cartList = JsonUtils.jsonToList(cartsJson, Cart.class);
		/**
		 * 购物车查询的时候去对比数据库看商品下架没 下架了或者删除了 就从购物车中删除
		 */
		for (int i=cartList.size()-1;i>=0;i--) {
			Disk disk = diskMapper.selectByPrimaryKey(cartList.get(i).getId());
			//如果该菜品状态不等于1
			if (disk.getStatus() != 1) {
				cartList.remove(i);
			}
		}
		
		this.saveAllCart(cartList, openid);

		return cartList;
	}

	/**
	 * 根据用户id和菜id增加购物中数量
	 */
	@Override
	public void addCart(String openid, int diskId) {
		// 先得到所有购物车列表
		List<Cart> allCart = this.getAllCart(openid);

		boolean flag = false; // 是否在购物车中的标志位

		// 判断此商品是否在购物车中存在
		for (Cart cart : allCart) {
			// 1.存在的话直接将数量加1
			if (cart.getId() == diskId) {
				cart.setNum(cart.getNum() + 1);
				flag = true;
				break;
			}
		}
		if (!flag) {
			// 2.不存在还要添加该商品到购物车中 并把数量置1;
			Disk disk = diskMapper.selectByPrimaryKey(diskId);
			Cart cart = new Cart();
			cart.setId(disk.getId());
			cart.setImg(disk.getImg());
			cart.setNum(1); // 数量设为1
			cart.setPrice(disk.getPrice());// 打折才是实际价格 没打折 时 折扣价和原价相等
			cart.setDisPrice(disk.getDisPrice());
			cart.setSelected(true);
			cart.setTitle(disk.getTitle());
			// 把该商品加入购物车中
			allCart.add(cart);
		}
		// 把购物车列表列表存入redis中
		this.saveAllCart(allCart, openid);
	}

	/**
	 * 根据用户id和菜id减去购物车的数量
	 */
	@Override
	public void substractCart(String openid, int diskId) {
		// 得到购物车列表
		List<Cart> allCart = this.getAllCart(openid);
		boolean flag = false;
		// 1.如果存在购物车中-直接减1 - 减到0时移除该对象在购物车中 - 保存到redis中
		for (int i = allCart.size() - 1; i >= 0; i--) {
			Cart cart = allCart.get(i);
			if (cart.getId() == diskId) {
				cart.setNum(cart.getNum() - 1);
				if (cart.getNum() <= 0) {
					allCart.remove(i);
				}
				flag = true;
				break;
			}
		}
		if (!flag) {
			// 2.如果不在购物车中 则不做操作 直接返回原购物车列表 返回购物车列表
			// 不在购物车中 不需要再去存redis

		}
		// 保存到redis中
		this.saveAllCart(allCart, openid);

	}

	/**
	 * 把购物车列表存入redis数据库
	 * 
	 * @param allCart
	 *            购物车列表
	 * @param openid
	 *            用户id
	 */
	private void saveAllCart(List<Cart> allCart, String openid) {
		// 先把列表转换成字符串
		String json = JsonUtils.objectToJson(allCart);
		// 把字符串存入redis中
		jedisClient.set(PIPI_CART_NAME + ":" + openid, json);
	}

	/**
	 * 根据菜ID改变菜是否在购物车中被选中
	 */
	@Override
	public void changeSelected(String openid, int diskId) {
		// 得到购物车列表
		List<Cart> allCart = this.getAllCart(openid);
		// 遍历购物车列表，根据id把菜的选中状态变化
		for (Cart cart : allCart) {
			if (cart.getId() == diskId) {
				cart.setSelected(!cart.getSelected());
				break;
			}
		}
		String json = JsonUtils.objectToJson(allCart);
		// 存入数据库
		jedisClient.set(PIPI_CART_NAME + ":" + openid, json);
	}

	/**
	 * 根据菜id删除购物车中的菜
	 */
	@Override
	public void delCartByDiskId(String openid, int diskId) {
		// 得到购物车列表
		List<Cart> allCart = this.getAllCart(openid);
		// 循环列表删除该菜样
		for (int i = allCart.size() - 1; i >= 0; i--) {
			if (allCart.get(i).getId() == diskId) {
				allCart.remove(i);
				break;
			}
		}
		// 把数据列表转换为json字符串
		String json = JsonUtils.objectToJson(allCart);
		// 把修改后的购物车列表存入redis中
		jedisClient.set(PIPI_CART_NAME + ":" + openid, json);
	}

	@Override
	public List<Cart> getSelectedCart(String openid) {
		List<Cart> allCart = this.getAllCart(openid);
		for (int i = allCart.size() - 1; i >= 0; i--) {
			if (!allCart.get(i).getSelected()) {
				allCart.remove(i);
			}
		}
		return allCart;
	}

	/**
	 * 删除选中的菜
	 */
	@Override
	public void delSelectedCart(String openid) {
		// 得到所有的购物车菜品
		List<Cart> allCart = this.getAllCart(openid);
		for (int i = allCart.size() - 1; i >= 0; i--) {
			if (allCart.get(i).getSelected()) {
				allCart.remove(i);
			}
		}
		String json = JsonUtils.objectToJson(allCart);
		jedisClient.set(PIPI_CART_NAME + ":" + openid, json);
	}
}
