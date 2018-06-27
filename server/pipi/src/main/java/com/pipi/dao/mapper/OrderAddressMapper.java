package com.pipi.dao.mapper;

import java.util.List;

import com.pipi.dao.pojo.OrderAddress;
import com.pipi.dao.pojo.OrderAddressExample;

public interface OrderAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    List<OrderAddress> selectByExample(OrderAddressExample example);

    OrderAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}