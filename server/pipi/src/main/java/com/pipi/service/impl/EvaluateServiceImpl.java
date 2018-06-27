package com.pipi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pipi.constant.EvaluateConstant;
import com.pipi.dao.mapper.DiskMapper;
import com.pipi.dao.mapper.EvaluateMapper;
import com.pipi.dao.mapper.OrderMainMapper;
import com.pipi.dao.pojo.Disk;
import com.pipi.dao.pojo.Evaluate;
import com.pipi.dao.pojo.EvaluateExample;
import com.pipi.dao.pojo.EvaluateExample.Criteria;
import com.pipi.dao.pojo.OrderMain;
import com.pipi.entity.Eval;
import com.pipi.entity.Evals;
import com.pipi.service.EvaluateService;

/**
 * 评价服务实现类
 * 
 * @author yubaojin
 *
 */
@Service
@Transactional
public class EvaluateServiceImpl implements EvaluateService {

	@Autowired
	private EvaluateMapper evaluateMapper;

	@Autowired
	private DiskMapper diskMapper;

	@Autowired
	private OrderMainMapper orderMainMapper;

	/**
	 * 根据菜id获取到它的所有评价
	 */
	@Override
	public Evals getEvalsByDiskId(int diskId) {
		// 创建返回封装对象
		Evals evals = new Evals();
		// 根据菜id得到所有的评论
		EvaluateExample example = new EvaluateExample();
		Criteria criteria = example.createCriteria();
		criteria.andDiskIdEqualTo(diskId);
		List<Evaluate> list = evaluateMapper.selectByExample(example);
		// 创建返回对象Evals中需要的对象
		List<Eval> li = new ArrayList<Eval>();
		for (Evaluate evaluate : list) {
			Eval eval = new Eval();
			eval.setAvatarUrl(evaluate.getAvatarUrl());
			eval.setContent(evaluate.getContent());
			eval.setCreated(evaluate.getCreated());
			eval.setEvalValue(evaluate.getEvalValue());
			eval.setId(evaluate.getId());
			eval.setImgs(evaluate.getImgs());
			eval.setNickname(evaluate.getNickname());
			eval.setIsAnoymous(evaluate.getIsAnoymous());
			// 把每个Eval对象转给li中
			li.add(eval);

		}
		// 把li对象注入到返回对象中
		evals.setEvals(li);

		// 根据菜id得到比率
		String ratio = evaluateMapper.getRatioByDiskId(diskId);
		// 注入到返回对象中
		evals.setRatio(ratio);
		// 把数量注入到返回对象中
		evals.setCount(li.size());
		return evals;
	}

	@Override
	public Map<String, Object> getEvals(int pageNum, int pageSize, int evalValue) {
		// 开启分页
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		// 创建例子
		EvaluateExample example = new EvaluateExample();
		// 排序
		example.setOrderByClause("updated DESC");

		Criteria criteria = example.createCriteria();

		if (evalValue != 0) {
			criteria.andEvalValueEqualTo(evalValue);
		}

		List<Evaluate> list = evaluateMapper.selectByExample(example);
		for (Evaluate eval : list) {
			Disk disk = diskMapper.selectByPrimaryKey(eval.getDiskId());
			eval.setDiskName(disk.getTitle());
		}

		Map<String, Object> map = new HashMap<>();
		map.put("rows", list);
		map.put("total", page.getTotal());
		// 返回结果
		return map;

	}

	@Override
	public void saveEvals(List<Evaluate> evals, Integer isAnonymous, String avatarUrl, String nickname) {

		for (Evaluate eval : evals) {
			eval.setIsAnoymous(isAnonymous);
			if (isAnonymous == EvaluateConstant.NO_ANONYMOUS) {
				eval.setAvatarUrl(avatarUrl);
				eval.setNickname(nickname);
			}
			if (!StringUtils.isNotBlank(eval.getContent())) {
				if (eval.getEvalValue() == EvaluateConstant.GOOD) {
					eval.setContent("好评！");
				}
				if (eval.getEvalValue() == EvaluateConstant.COMMOM) {
					eval.setContent("一般！");
				}
				if (eval.getEvalValue() == EvaluateConstant.BAD) {
					eval.setContent("差评！");
				}
			}
			eval.setCreated(new Date());
			eval.setUpdated(new Date());
			evaluateMapper.insert(eval);
		}
		OrderMain order = orderMainMapper.selectByPrimaryKey(evals.get(0).getOrderId());
		order.setIsEvaluate(EvaluateConstant.YES_EVAL);
		orderMainMapper.updateByPrimaryKey(order);
	}

}
