package com.pipi.entity;

public class ShopInfo {
	private String name;
	private String address;
	private String time;
	private String phone;

	public ShopInfo() {
		super();
	}

	public ShopInfo(String name, String address, String time, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.time = time;
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
