package com.pipi.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pipi.dao.mapper.DiskMapper;
import com.pipi.dao.mapper.MenuMapper;
import com.pipi.dao.pojo.Disk;
import com.pipi.dao.pojo.DiskExample;
import com.pipi.dao.pojo.DiskExample.Criteria;
import com.pipi.dao.pojo.Menu;
import com.pipi.service.DiskService;

/**
 * 商品服务实现类
 */
@Service
@Transactional
public class DiskServiceImpl implements DiskService {

	@Autowired
	private DiskMapper diskMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Disk> getDisksByMenuId(int menuId) {
		
		DiskExample example = new DiskExample();
		Criteria criteria = example.createCriteria();
		if (menuId != 1) {
			criteria.andMenuIdEqualTo(menuId);
		}
		return diskMapper.selectByExample(example);

	}

	@Override
	public Disk getDiskById(int diskId) {
		return diskMapper.selectByPrimaryKey(diskId);
	}

	@Override
	public Map<String, Object> getDiskList(int menuId, int page, int rows) {
		Page<Disk> pageInfo = PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<>();
		DiskExample example = new DiskExample();
		Criteria criteria = example.createCriteria();
		if (menuId > 1) {
			criteria.andMenuIdEqualTo(menuId);
		}
		List<Disk> list = diskMapper.selectByExample(example);
		for (Disk disk : list) {
			Menu menu = menuMapper.selectByPrimaryKey(disk.getMenuId());
			if (menu != null) {
				disk.setMenu(menu.getTitle());
			} else {
				disk.setMenu("<span style=\"color:red;\">未分类</span>");
			}

		}
		map.put("rows", list);
		map.put("total", pageInfo.getTotal());
		return map;
	}

	/**
	 * 删除该菜单
	 */
	@Override
	public void deleteByDiskIds(String ids) {
		String[] idss = ids.split(",");
		for (String id : idss) {
			int iid = Integer.parseInt(id);
			diskMapper.deleteByPrimaryKey(iid);
		}
	}

	@Override
	public void save(Disk disk) {
		disk.setCreated(new Date());
		disk.setUpdated(new Date());
		disk.setSellnum(0);
		disk.setStatus(1);
		diskMapper.insert(disk);
	}

	@Override
	public void update(Disk disk) {
		Disk disk2 = diskMapper.selectByPrimaryKey(disk.getId());

		disk.setUpdated(new Date());
		disk.setStatus(1);
		disk.setCreated(disk2.getCreated());
		disk.setSellnum(disk2.getSellnum());
		if (disk.getMenuId() == null) {
			disk.setMenuId(disk2.getMenuId());
		}
		diskMapper.updateByPrimaryKey(disk);
	}

	@Override
	public void up(String ids) {
		String[] idss = ids.split(",");
		for (String id : idss) {
			int iid = Integer.parseInt(id);
			Disk disk = diskMapper.selectByPrimaryKey(iid);
			disk.setStatus(1);
			disk.setUpdated(new Date());
			diskMapper.updateByPrimaryKey(disk);
		}
	}

	@Override
	public void down(String ids) {
		String[] idss = ids.split(",");
		for (String id : idss) {
			int iid = Integer.parseInt(id);
			Disk disk = diskMapper.selectByPrimaryKey(iid);
			disk.setStatus(3);
			disk.setUpdated(new Date());
			diskMapper.updateByPrimaryKey(disk);
		}
	}

	/**
	 *设置所有商品销售数量 每月清零用
	 */
	@Override
	public void updateAllDiskSellNum(int num) {
		diskMapper.updateAllDiskSellNum(num);
	}

}
