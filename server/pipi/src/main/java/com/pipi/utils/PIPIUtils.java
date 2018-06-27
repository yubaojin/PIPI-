package com.pipi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public final class PIPIUtils {

	private PIPIUtils() {
	}

	/**
	 * 订单id主键生成
	 * 
	 * @return
	 */
	public static String getOrderId() {
		// return UUID.randomUUID().toString().replace("-", "");
		String s = "" + new Date().getTime();
		for (int i = 0; i < 6; i++) {
			s += random(0, 9);
		}
		return s;
	}

	/**
	 * 得到图片地址
	 */

	public static String getImgId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 判断是否过期
	 */
	public static boolean isExpired(Date date, Long minute) {
		return (Math.abs(new Date().getTime() - date.getTime()) / (60 * 1000)) >= minute;
	}

	/**
	 * 生成[min,max]的数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	/**
	 * 字符串日期变为;Date类型
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(s);
		return date;
	}

	/**
	 * 将日子加一天
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getNextDay(Date date) throws ParseException {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1); // 把日期往后增加一天,整数 往后推,负数往前移动
		return calendar.getTime(); // 这个时间就是日期往后推一天的结果
	}

	/**
	 * 得到当前日子格式如2017-8-8
	 * 
	 * @throws ParseException
	 */

	public static Date getCurrentDay() throws ParseException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String s = format.format(date);
		date = PIPIUtils.getDate(s);
		return date;
	}

	public static String formatMoney(String s) {
		String back = null;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '0') {
				continue;
			}
			back = s.substring(0, i + 1);
			break;
		}
		if (back.endsWith(".")) {
			back = back.substring(0, back.indexOf("."));
		}
		return back;
	}

	public static String getAbsolutePath(String path) {
		path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + path;
		return path;
	}
}
