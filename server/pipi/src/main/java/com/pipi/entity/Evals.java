package com.pipi.entity;

import java.util.List;

/**
 * 返回评价的封装
 * 
 * @author yubaojin
 *
 */
public class Evals {
	//好评率
	private String ratio;
	//总共评价数量
	private Integer count;
	//评价列表
	private List<Eval> evals;

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Eval> getEvals() {
		return evals;
	}

	public void setEvals(List<Eval> evals) {
		this.evals = evals;
	}

}
