package com.pipi.dao.mapper;

import com.pipi.dao.pojo.OrderMain;
import com.pipi.dao.pojo.OrderMainExample;
import com.pipi.entity.Statistics;
import com.pipi.utils.sql.Query;

import java.util.List;

public interface OrderMainMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderMain record);

    int insertSelective(OrderMain record);

    List<OrderMain> selectByExample(OrderMainExample example);

    OrderMain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderMain record);

    int updateByPrimaryKey(OrderMain record);
    
    Statistics getStatistics(Query query);
}