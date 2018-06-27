package com.pipi.dao.mapper;

import java.util.List;

import com.pipi.dao.pojo.Disk;
import com.pipi.dao.pojo.DiskExample;

public interface DiskMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Disk record);

	int insertSelective(Disk record);

	List<Disk> selectByExample(DiskExample example);

	Disk selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Disk record);

	int updateByPrimaryKey(Disk record);

	/**
	 *设置所有商品销售数量 每月清零用
	 */
	int updateAllDiskSellNum(int num);
}