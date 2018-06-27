package com.pipi.dao.mapper;

import com.pipi.dao.pojo.Evaluate;
import com.pipi.dao.pojo.EvaluateExample;
import java.util.List;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    List<Evaluate> selectByExample(EvaluateExample example);

    Evaluate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
    
    String getRatioByDiskId(Integer diskId);
}