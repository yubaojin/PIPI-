package com.pipi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pipi.entity.Cart;
import com.pipi.entity.Result;
import com.pipi.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * 得到openid用户的购物车
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping("/getAllCart")
	@ResponseBody
	public Result getAllCart(String openid) {
		List<Cart> allCart = cartService.getAllCart(openid);
		return Result.ok(allCart);
	}

	/**
	 * 怎加diskId菜到opneid用户的购物车中
	 * 
	 * @param openid
	 * @param diskId
	 * @return
	 */
	@RequestMapping("/addCart")
	@ResponseBody
	public Result addCart(String openid, Integer diskId) {
		cartService.addCart(openid, diskId);
		return Result.ok();
	}

	/**
	 * 减去diskId菜从openid用户的购物车中
	 * 
	 * @param openid
	 * @param diskId
	 * @return
	 */
	@RequestMapping("/substractCart")
	@ResponseBody
	public Result substractCart(String openid, Integer diskId) {
		cartService.substractCart(openid, diskId);
		return Result.ok();
	}

	/**
	 * 改变diskId菜在openid用户的购物车中的选中状态
	 * 
	 * @param openid
	 * @param diskId
	 * @return
	 */
	@RequestMapping("/changeSelected")
	@ResponseBody
	public Result changeSelected(String openid, Integer diskId) {
		cartService.changeSelected(openid, diskId);
		return Result.ok();

	}

	/**
	 * 删除diskId菜从openid用户的购物车中
	 * 
	 * @param openid
	 * @param diskId
	 * @return
	 */
	@RequestMapping("/delCartByDiskId")
	@ResponseBody
	public Object delCartByDiskId(String openid, Integer diskId) {
		cartService.delCartByDiskId(openid, diskId);
		return Result.ok();

	}

	/**
	 * 得到openID用户购物车中所有选中的菜
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping("/getSelectedCart")
	@ResponseBody
	public Result getSelectedCart(String openid) {
		List<Cart> list = cartService.getSelectedCart(openid);
		return Result.ok(list);
	}
}
