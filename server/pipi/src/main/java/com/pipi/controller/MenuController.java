package com.pipi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pipi.dao.pojo.Menu;
import com.pipi.entity.Result;
import com.pipi.entity.TreeNode;
import com.pipi.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 得到所有菜单
	 * 
	 * @return
	 */
	@RequestMapping("/getAllMenu")
	@ResponseBody
	public Result getAllMenu() {
		List<Menu> allMenu = menuService.allMenu();
		return Result.ok(allMenu);
	}

	@RequestMapping("/list")
	@Cacheable(value = "listCache")
	@ResponseBody
	public List<TreeNode> getMenuList(@RequestParam(name = "id", defaultValue = "0") String parentId) {

		Integer id = Integer.parseInt(parentId);

		List<TreeNode> list = menuService.getMenuList(id);

		return list;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result addMenu(@RequestParam(defaultValue = "1") Integer parentId, String name) {
		TreeNode node = menuService.addMenu(parentId, name);
		return Result.ok(node);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(Integer id, String name) {
		menuService.update(id, name);
		return Result.ok();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer parentId, String id) {
		menuService.delete(id, parentId);
		return Result.ok();
	}
}
