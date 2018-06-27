package com.pipi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pipi.entity.Result;
import com.pipi.entity.ShopInfo;
import com.pipi.utils.PIPIUtils;
import com.pipi.utils.property.PropertyUtils;

@RestController
@RequestMapping("/shop")
public class ShopInfoController {

	private String path = null;

	{
		path = PIPIUtils.getAbsolutePath("info.properties");
	}

	/**
	 * 得到商家信息 信息是一个properties文件
	 * 
	 * @return
	 */
	@RequestMapping("/getInfo")
	public Result getInfo() {
		String name = PropertyUtils.getPro(path, "SHOP_NAME");
		String address = PropertyUtils.getPro(path, "SHOP_ADDRESS");
		String time = PropertyUtils.getPro(path, "SHOP_TIME");
		String phone = PropertyUtils.getPro(path, "SHOP_PHONE");
		ShopInfo info = new ShopInfo(name, address, time, phone);
		return Result.ok(info);
	}

	/**
	 * 更新商家信息
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping("/updateInfo")
	public Result updateInfo(ShopInfo info) {
		PropertyUtils.updatePro(path, "SHOP_NAME", info.getName());
		PropertyUtils.updatePro(path, "SHOP_ADDRESS", info.getAddress());
		PropertyUtils.updatePro(path, "SHOP_TIME", info.getTime());
		PropertyUtils.updatePro(path, "SHOP_PHONE", info.getPhone());
		return Result.ok();
	}

}
