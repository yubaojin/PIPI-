package com.pipi.dao.mapper;

import com.pipi.dao.pojo.RoleRes;
import com.pipi.dao.pojo.RoleResExample;
import java.util.List;

public interface RoleResMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleRes record);

    int insertSelective(RoleRes record);

    List<RoleRes> selectByExample(RoleResExample example);

    RoleRes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleRes record);

    int updateByPrimaryKey(RoleRes record);
}