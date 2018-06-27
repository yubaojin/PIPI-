package com.pipi.dao.mapper;

import java.util.List;

import com.pipi.dao.pojo.Address;
import com.pipi.dao.pojo.AddressExample;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
    
}