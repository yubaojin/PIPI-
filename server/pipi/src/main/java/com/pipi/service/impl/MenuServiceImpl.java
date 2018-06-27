package com.pipi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pipi.dao.mapper.MenuMapper;
import com.pipi.dao.pojo.Menu;
import com.pipi.dao.pojo.MenuExample;
import com.pipi.dao.pojo.MenuExample.Criteria;
import com.pipi.entity.TreeNode;
import com.pipi.service.MenuService;

/**
 * 菜单服务实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	/**
	 * 得到所有的菜单
	 */
	@Override
	public List<Menu> allMenu() {
		MenuExample example = new MenuExample();
		return menuMapper.selectByExample(example);
	}

	@Override
	public List<TreeNode> getMenuList(Integer parentId) {
		MenuExample example = new MenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<Menu> list = menuMapper.selectByExample(example);
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (Menu menu : list) {
			TreeNode node = new TreeNode();
			node.setId(menu.getId());
			node.setText(menu.getTitle());
			node.setState(menu.getIsParentid() == 1 ? "closed" : "open");
			nodes.add(node);
		}
		return nodes;
	}

	@Override
	public TreeNode addMenu(int parentId, String name) {
		Menu menu = new Menu();
		menu.setParentId(parentId);
		menu.setIsParentid(0);
		menu.setTitle(name);
		menuMapper.insert(menu);
		TreeNode node = new TreeNode();
		node.setId(menu.getId());
		node.setText(menu.getTitle());
		node.setState("open");
		return node;
	}

	@Override
	public void update(int id, String name) {
		Menu menu = menuMapper.selectByPrimaryKey(id);
		menu.setTitle(name);
		menuMapper.updateByPrimaryKey(menu);
	}

	@Override
	public void delete(String parentId, int id) {
		menuMapper.deleteByPrimaryKey(id);
	}

}
