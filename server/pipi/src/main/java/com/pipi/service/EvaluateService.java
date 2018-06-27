package com.pipi.service;

import java.util.List;
import java.util.Map;

import com.pipi.dao.pojo.Evaluate;
import com.pipi.entity.Evals;

/**
 * 评价服务类接口
 * 
 * @author yubaojin
 *
 */
public interface EvaluateService {
	/**
	 * 根据菜的id得到该菜所有评价
	 * 
	 * @param diskId
	 * @return
	 */
	Evals getEvalsByDiskId(int diskId);

	Map<String, Object> getEvals(int page, int rows, int evalValue);

	void saveEvals(List<Evaluate> evals, Integer isAnonymous, String avatarUrl, String nickname);
}
