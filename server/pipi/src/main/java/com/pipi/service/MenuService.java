package com.pipi.service;

import java.util.List;

import com.pipi.dao.pojo.Menu;
import com.pipi.entity.TreeNode;

public interface MenuService {
	List<Menu> allMenu();
	List<TreeNode> getMenuList(Integer parentId);
	TreeNode addMenu(int parentId,String name);
	void update(int id, String name);
	void delete(String parentId,int id);
}
