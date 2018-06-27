package com.pipi.entity;

/**
 * 皮皮自定义响应结构
 */
public class Result {

	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	// 响应的数据总数 分页时用
	private Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Result() {
		super();
	}

	public Result(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 默认失败返回
	 * 
	 * @return
	 */
	public static Result error() {
		return new Result(400, "Bad Request!", null);
	}

	/**
	 * 自定义失败返回
	 * 
	 * @param msg
	 * @return
	 */
	public static Result error(String msg) {
		return new Result(400, msg, null);
	}

	/**
	 * 自定义数据成功返回
	 * 
	 * @param o
	 * @return
	 */
	public static Result ok(Object o) {
		return new Result(200, "OK!", o);
	}

	/**
	 * 默认成功返回
	 * 
	 * @return
	 */
	public static Result ok() {
		return new Result(200, "OK!", null);
	}

}
