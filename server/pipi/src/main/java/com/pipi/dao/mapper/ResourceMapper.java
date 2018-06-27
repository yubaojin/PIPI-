package com.pipi.dao.mapper;

import com.pipi.dao.pojo.Resource;
import com.pipi.dao.pojo.ResourceExample;
import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long resId);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Long resId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}