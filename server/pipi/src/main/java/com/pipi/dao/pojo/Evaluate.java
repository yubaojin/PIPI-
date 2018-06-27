package com.pipi.dao.pojo;

import java.util.Date;

public class Evaluate {
	private Integer id;

	private Integer diskId;

	private String orderId;

	private String openid;

	private String nickname;

	private String avatarUrl;

	private String content;

	private String imgs;

	private Integer isAnoymous;

	private Integer evalValue;

	private Date created;

	private Date updated;

	private String diskName;

	public String getDiskName() {
		return diskName;
	}

	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDiskId() {
		return diskId;
	}

	public void setDiskId(Integer diskId) {
		this.diskId = diskId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs == null ? null : imgs.trim();
	}

	public Integer getIsAnoymous() {
		return isAnoymous;
	}

	public void setIsAnoymous(Integer isAnoymous) {
		this.isAnoymous = isAnoymous;
	}

	public Integer getEvalValue() {
		return evalValue;
	}

	public void setEvalValue(Integer evalValue) {
		this.evalValue = evalValue;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}