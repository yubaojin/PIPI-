package com.pipi.dao.pojo;

import java.util.Date;

public class Disk {
	private String menu;

	private Integer id;

	private Integer menuId;

	private String img;

	private String title;

	private Float price;

	private Float disPrice;

	private String sellPoint;

	private Date created;

	private Date updated;

	private Integer sellnum;

	private Integer status;

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDisPrice() {
		return disPrice;
	}

	public void setDisPrice(Float disPrice) {
		this.disPrice = disPrice;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint == null ? null : sellPoint.trim();
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

	public Integer getSellnum() {
		return sellnum;
	}

	public void setSellnum(Integer sellnum) {
		this.sellnum = sellnum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Disk [menu=" + menu + ", id=" + id + ", menuId=" + menuId + ", img=" + img + ", title=" + title
				+ ", price=" + price + ", disPrice=" + disPrice + ", sellPoint=" + sellPoint + ", created=" + created
				+ ", updated=" + updated + ", sellnum=" + sellnum + ", status=" + status + "]";
	}

}