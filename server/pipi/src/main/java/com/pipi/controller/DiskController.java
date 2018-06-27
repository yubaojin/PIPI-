package com.pipi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pipi.dao.pojo.Disk;
import com.pipi.entity.Result;
import com.pipi.service.DiskService;
import com.pipi.utils.jedis.JedisClientPool;
import com.pipi.utils.json.JsonUtils;

@Controller
@RequestMapping("/disk")
public class DiskController {

	@Autowired
	private DiskService diskService;

	@Autowired
	private JedisClientPool jedisClient;

	private final String DISKCACHENAME = "DISKCACHENAME";

	/**
	 * 根据菜单id得到菜单的菜品
	 * 
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/getMenuDisk")
	@ResponseBody
	public Result getDisksByMenuId(Integer menuId) {

		String json = jedisClient.get(DISKCACHENAME + ":" + menuId);

		List<Disk> menuDisk = null;

		if (null != json) {
			menuDisk = JsonUtils.jsonToList(json, Disk.class);
		} else {
			menuDisk = diskService.getDisksByMenuId(menuId);
			jedisClient.set(DISKCACHENAME + ":" + menuId, JsonUtils.objectToJson(menuDisk));
		}

		return Result.ok(menuDisk);
	}

	/**
	 * 根据菜的id得到菜的详情
	 * 
	 * @param diskId
	 * @return
	 */
	@RequestMapping("/getDiskById")
	@Cacheable(value = "DISK",key="'diskId_'+#diskId")
	@ResponseBody
	public Result getDiskById(String diskId) {
		Integer id = Integer.parseInt(diskId);
		Disk disk = diskService.getDiskById(id);
		return Result.ok(disk);
	}
 
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> getDiskList(@RequestParam(defaultValue = "0") Integer menuId, Integer page,
			Integer rows) {
		Map<String, Object> map = diskService.getDiskList(menuId, page, rows);
		return map;
	}

	@RequestMapping("/delete")  
	@ResponseBody
	public Result deleteByDiskIds(String ids) {
		diskService.deleteByDiskIds(ids);
		return Result.ok();
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result save(Disk disk) {
		diskService.save(disk);
		return Result.ok();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(Disk disk) {
		diskService.update(disk);
		return Result.ok();
	}

	/**
	 * 上架
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/up")
	@ResponseBody
	public Result up(String ids) {
		diskService.up(ids);
		return Result.ok();
	}

	/**
	 * 下架
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/down")
	@ResponseBody
	public Result down(String ids) {
		diskService.down(ids);
		return Result.ok();
	}
}
