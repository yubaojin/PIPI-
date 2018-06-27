package com.pipi.utils.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	private static Properties prop;

	public static void load(String path) {
		// 这里的path是项目文件的绝对路径
		// 先获取项目绝对路径：Thread.currentThread().getContextClassLoader().getResource("").getPath();
		// 然后在项目路径后面拼接"properties/sysConfig.properties";
		prop = new Properties();// 属性集合对象
		FileInputStream fis;
		try {
			// System.out.println(path);
			fis = new FileInputStream(path);
			
			prop.load(fis);
			fis.close();// 关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 参数为要修改的文件路径 以及要修改的属性名和属性值
	public static Boolean updatePro(String path, String key, String value) {
		if (prop == null) {
			load(path);
			// System.out.println("修改前重新加载一遍");
		}
		// System.out.println("获取添加或修改前的属性值：" + key + "=" + prop.getProperty(key));
		prop.setProperty(key, value);
		// 文件输出流
		try {
			FileOutputStream fos = new FileOutputStream(path);
			// 将Properties集合保存到流中
			prop.store(fos, "Copyright (c) Boxcode Studio");
			fos.flush();
			fos.close();// 关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		// System.out.println("获取添加或修改后的属性值：" + key + "=" + prop.getProperty(key));
		return true;
	}

	// 参数为要修改的文件路径 以及要修改的属性名和属性值
	public static String getPro(String path, String key) {
		if (prop == null) {
			load(path);
			// System.out.println("重新加载一遍");
		}
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("查询到的" + key + "的值：" + prop.getProperty(key));
		return prop.getProperty(key);
	}
}
