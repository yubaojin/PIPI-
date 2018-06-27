package com.pipi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pipi.dao.pojo.Evaluate;
import com.pipi.entity.Evals;
import com.pipi.entity.Result;
import com.pipi.service.EvaluateService;
import com.pipi.utils.json.JsonUtils;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

	@Autowired
	private EvaluateService evaluateService;

	/**
	 * 根据菜的id得到该菜所有评价
	 * 
	 * @param diskId
	 * @return
	 */
	@RequestMapping("/getEvalsByDiskId")
	public Result getEvalsByDiskId(Integer diskId) {
		Evals evals = evaluateService.getEvalsByDiskId(diskId);
		return Result.ok(evals);
	}

	/**
	 * 得到所有的评价
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getEvals")
	public Map<String, Object> getEvals(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer rows, @RequestParam(defaultValue = "0") Integer evalValue) {
		Map<String, Object> evals = evaluateService.getEvals(page, rows, evalValue);
		return evals;
	}

	@RequestMapping("/saveEvals")
	public Result saveEvals(String evals, Integer isAnonymous, String avatarUrl, String nickname) {
		List<Evaluate> evalss = JsonUtils.jsonToList(evals, Evaluate.class);
		evaluateService.saveEvals(evalss, isAnonymous, avatarUrl, nickname);
		return Result.ok();
	}
}
