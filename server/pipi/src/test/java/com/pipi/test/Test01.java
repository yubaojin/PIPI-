package com.pipi.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.pipi.utils.property.PropertyUtils;

public class Test01 {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void test01() {
		System.out.println(Collections.EMPTY_LIST.size());
		String[][] s = { { "sadasd", "dasd", "dasd" }, { "asda", "dsa" }, { "asas", "das" }, { "ASa", "dsa" } };
		for (String[] str : s) {
			str[0] = "a";
		}
		for (String[] str : s) {
			System.out.println(str[0]);
		}
	}

	@Test
	public void test02() {
		List emptyList = Collections.EMPTY_LIST;
		emptyList.add("dsaad");
		System.out.println(emptyList.get(0));
	}

	@Test
	public void test03() {
		System.out.println(new Date());
		System.out.println(new Date().getTime());
	}

	@Test
	public void test04() {
		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i) == "2") {
				list.remove(i);
				// continue;
			}
			System.out.println(list.get(i));
		}
	}

	@Test
	public void test05() {
		System.out.println(UUID.randomUUID().toString().length());
	}

	@Test
	public void test06() {
		List list = new ArrayList<>();
		list.add("asdsa0");
		list.add("dsada");
		list.add("dfgd");
		list.add(5);
		list.add(new ArrayList<>());
		for (int i = list.size() - 1; i >= 0; i--) {
			System.out.println(list.size());
			System.out.println(i+"----");
			list.remove(i);
			System.out.println(list.size());
		}
	}
	
	@Test
	public void test07() {
		Integer.parseInt("asd");
	}
	
	@Test
	public void test08() {
		PropertyUtils.updatePro("classpath:info.properties", "SHOP_NAME", "asdsa");
	}
	
}
