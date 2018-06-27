package com.pipi.constant;

public class OrderConstant {
	/**
	 * 未付款
	 */
	public static final int NO_PAY = 1;
	/**
	 * 已付款
	 */
	public static final int YES_PAY = 2;
	/**
	 * 已接单
	 */
	public static final int RECEIVE = 3;
	/**
	 * 已发货
	 */
	public static final int DILIVER = 4;
	/**
	 * 交易完成
	 */
	public static final int COMPLETE = 5;
	/**
	 * 交易取消 未支付取消 不用退钱
	 */
	public static final int CANCEL_NO_PAY = 7;
	/**
	 * 交易取消 已付钱状态下取消一般为商家取消要退钱
	 */
	public static final int CANCEL_PAY = 8;
}
