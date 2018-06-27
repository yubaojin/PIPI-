package com.pipi.entity;

/**
 * 返回购物车中每个元素的封装
 * @author yubaojin
 *
 */
public class Cart {
	private Integer id;
	private String title;
	private String img;
	private Integer num;
	private Float price;
	private Float disPrice;
	private Boolean selected;

	public Float getDisPrice() {
		return disPrice;
	}

	public void setDisPrice(Float disPrice) {
		this.disPrice = disPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

}
