package com.pipi.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 和Evaluate类比 imgs做了改变 把字符串变为数组
 * 
 * @author yubaojin
 *
 */
public class Eval {
	private Integer id;
	private String nickname;
	private String avatarUrl;
	private String content;
	private String[] imgs;
	@JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")  
	private Date created;
	private Integer evalValue;
	private Integer isAnoymous;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = StringUtils.isNotBlank(nickname) ? nickname.trim() : null;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = StringUtils.isNotBlank(avatarUrl) ? avatarUrl.trim() : null;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = StringUtils.isNotBlank(content) ? content.trim() : null;
	}

	public String[] getImgs() {
		return imgs;
	}

	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}
	
	/**
	 * 重载
	 * 把用逗号分隔的imgs变为数组
	 * 
	 * @param imgs
	 */
	public void setImgs(String imgs) {

		this.imgs = StringUtils.isNotBlank(imgs) ? imgs.split(",") : null;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}