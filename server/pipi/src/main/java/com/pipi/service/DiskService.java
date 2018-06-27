package com.pipi.service;

import java.util.List;
import java.util.Map;

import com.pipi.dao.pojo.Disk;

public interface DiskService {
	List<Disk> getDisksByMenuId(int menuId);

	Disk getDiskById(int diskId);

	Map<String, Object> getDiskList(int menuId, int page, int rows);

	void deleteByDiskIds(String ids);

	void save(Disk disk);

	void update(Disk disk);

	void up(String ids);

	void down(String ids);

	void updateAllDiskSellNum(int num);
}
