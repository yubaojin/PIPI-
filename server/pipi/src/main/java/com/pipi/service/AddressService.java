package com.pipi.service;

import java.util.List;

import com.pipi.dao.pojo.Address;

/**
 * 地址服务类接口
 * 
 * @author yubaojin
 *
 */
public interface AddressService {
	/**
	 * 根据用户id得到所有的地址列表
	 * 
	 * @param openid
	 * @return
	 */
	List<Address> getAddrsByOpenid(String openid);

	/**
	 * 根据用户openid把该用户拥有的地址id为addrId的改为默认地址
	 * 
	 * @param addrId
	 * @param openid
	 * @return
	 */
	void changeDefaultAddr(int addrId, String openid);

	/**
	 * 得到用户id为openid的默认地址
	 * 
	 * @param openid
	 * @return
	 */
	Address getDefaultAddr(String openid);

	/**
	 * 删除addrId地址
	 * 
	 * @param openid
	 * @param addrId
	 * @return
	 */
	void delAddrByAddrId(String openid, int addrId);

	/**
	 * 增加一个地址
	 */
	void addAddress(Address address);

	/**
	 * 更新地址
	 * @param address
	 * @return
	 */
	void updateAddress(Address address);
	
	/**
	 * 通过地址id得到地址
	 * @param addrId
	 * @return
	 */
	Address getAddrByAddrId(int addrId);
}
