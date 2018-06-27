package com.pipi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pipi.dao.pojo.Address;
import com.pipi.entity.Result;
import com.pipi.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/address")
@Api(value = "皮皮外卖地址接口")
public class AddressController {

	@Autowired
	private AddressService addressService;

	/**
	 * 根据用户id得到所有的地址列表
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/getAddrsByOpenid", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户id得到地址列表", notes = "查询数据库中某个用户的所有地址")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", 
					dataType = "String", name = "openid", 
					value = "用于开放id", required = true) })
	public Result getAddrsByOpenid(String openid) {
		List<Address> list = addressService.getAddrsByOpenid(openid);
		return Result.ok(list);
	}

	/**
	 * 根据用户openid把该用户拥有的地址id为addrId的改为默认地址
	 * 
	 * @param addrId
	 * @param openid
	 * @return
	 */
	@RequestMapping("/changeDefaultAddr")
	public Result changeDefaultAddr(String addrId, String openid) {
		int id = Integer.parseInt(addrId);
		addressService.changeDefaultAddr(id, openid);
		return Result.ok();
	}

	/**
	 * 得到用户id为openid的默认地址
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping("/getDefaultAddr")
	public Result getDefaultAddr(String openid) {
		Address address = addressService.getDefaultAddr(openid);
		return Result.ok(address);
	}

	/**
	 * 删除openid用户的addrId地址
	 * 
	 * @param openid
	 * @param addrId
	 * @return
	 */
	@RequestMapping("/delAddrByAddrId")
	public Result delAddrByAddrId(String openid, Integer addrId) {
		addressService.delAddrByAddrId(openid, addrId);
		return Result.ok();
	}

	/**
	 * 根据用户id和地址信息增加一个地址 这里本来有个openid的 刚好地址里有openid属性 刚好放进去
	 * 
	 * @param openid
	 * @param address
	 * @return
	 */
	@RequestMapping("/addAddress")
	public Result addAddress(Address address) {
		addressService.addAddress(address);
		return Result.ok();
	}

	/**
	 * 通过地址id得到地址
	 * 
	 * @param addrId
	 * @return
	 */
	@RequestMapping("/getAddrByAddrId")
	public Result getAddrByAddrId(Integer addrId) {
		Address address = addressService.getAddrByAddrId(addrId);
		return Result.ok(address);
	}

	/**
	 * 更新地址
	 * 
	 * @param address
	 * @return
	 */
	@RequestMapping("/updateAddress")
	public Result updateAddress(Address address) {
		addressService.updateAddress(address);
		return Result.ok();
	}
}
