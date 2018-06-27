package com.pipi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pipi.dao.mapper.AddressMapper;
import com.pipi.dao.pojo.Address;
import com.pipi.dao.pojo.AddressExample;
import com.pipi.dao.pojo.AddressExample.Criteria;
import com.pipi.service.AddressService;

/**
 * 地址服务实现类
 * 
 * @author yubaojin
 *
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;

	/**
	 * 根据用户id得到用户地址列表
	 */
	@Override
	public List<Address> getAddrsByOpenid(String openid) {
		AddressExample example = new AddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openid);
		List<Address> list = addressMapper.selectByExample(example);
		return list;
	}

	/**
	 * 根据用户openid把该用户拥有的地址id为addrId的改为默认地址
	 */
	@Override
	public void changeDefaultAddr(int addrId, String openid) {
		// 根据用户id得到所有该用户地址
		List<Address> list = this.getAddrsByOpenid(openid);
		// 循环遍历修改
		for (Address address : list) {
			// 1表示默认地址 0表示不是默认地址
			if (address.getId() == addrId) {
				address.setIsDefault(1);
				// 把修改后的列表存入数据库
				addressMapper.updateByPrimaryKey(address);
			} else if (address.getIsDefault() == 1) {
				address.setIsDefault(0);
				// 把修改后的列表存入数据库
				addressMapper.updateByPrimaryKey(address);
			}
		}
	}

	/**
	 * 得到用户id为openid的默认地址
	 */
	@Override
	public Address getDefaultAddr(String openid) {
		AddressExample example = new AddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openid);
		criteria.andIsDefaultEqualTo(1);
		List<Address> list = addressMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return new Address();
	}

	/**
	 * 删除openid用户的addrId地址
	 */
	@Override
	public void delAddrByAddrId(String openid, int addrId) {

		// 得到要删除的地址
		Address address = addressMapper.selectByPrimaryKey(addrId);

		// 在数据库中删除它
		addressMapper.deleteByPrimaryKey(addrId);

		// 得到要删除后的地址列表
		List<Address> allAdress = this.getAddrsByOpenid(openid);

		// 判断要删除的是否是默认地址
		if (address.getIsDefault() == 1) {
			// 判断是否还有地址
			if (allAdress.size() > 0) {
				// 如果有把首个地址设为默认地址
				allAdress.get(0).setIsDefault(1);
				// 更新到数据中
				addressMapper.updateByPrimaryKey(allAdress.get(0));
			}
		}
	}

	/**
	 * 根据openid增加地址
	 */
	@Override
	public void addAddress(Address address) {
		/**
		 * 两种情况 如果是默认 就设为默认 不是默认 就判断是否这是第一个地址 是第一个地址就设为默认 不是则不做什么
		 */

		// 存入数据库
		addressMapper.insert(address);

		if (address.getIsDefault() == 1) {
			// 如果是默认
			this.changeDefaultAddr(address.getId(), address.getOpenid());
		} else {
			// 如果不是默认
			// 看是否还有其他地址 没有也设为默认
			// 得到所有地址列表
			List<Address> allAdress = this.getAddrsByOpenid(address.getOpenid());
			if (allAdress.size() == 1) {
				this.changeDefaultAddr(address.getId(), address.getOpenid());
			}
		}
	}

	/**
	 * 更新地址
	 */
	@Override
	public void updateAddress(Address address) {

		addressMapper.updateByPrimaryKey(address);

		if (address.getIsDefault() == 1) {
			// 如果是默认
			this.changeDefaultAddr(address.getId(), address.getOpenid());
		} else {
			// 如果不是默认
			// 看是否自己是最后一个地址 是的话无论如何都要是默认地址
			// 得到所有地址列表
			List<Address> allAdress = this.getAddrsByOpenid(address.getOpenid());
			if (allAdress.size() == 1) {
				this.changeDefaultAddr(address.getId(), address.getOpenid());
			}
		}

	}

	/**
	 * 通过地址id得到地址
	 */
	@Override
	public Address getAddrByAddrId(int addrId) {
		return addressMapper.selectByPrimaryKey(addrId);
	}

}
