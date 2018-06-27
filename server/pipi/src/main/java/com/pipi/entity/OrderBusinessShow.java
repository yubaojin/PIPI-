package com.pipi.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderBusinessShow {
	private String orderId;

	private String totalMoney;

	private Integer status;

	private String remarks;
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	private String mobile;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "OrderBusinessShow [orderId=" + orderId + ", totalMoney=" + totalMoney + ", status=" + status
				+ ", remarks=" + remarks + ", createTime=" + createTime + ", mobile=" + mobile + "]";
	}

	
}
