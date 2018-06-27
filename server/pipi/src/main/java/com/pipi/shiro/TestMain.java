package com.pipi.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class TestMain {
	public static void main(String[] args) {
		Md5Hash md5 = new Md5Hash("yubaojin", "1", 2);
		System.out.println(md5.toString());
		SimpleHash md = new SimpleHash("md5", "yubaojin", "1", 2);
		System.out.println(md.toString());
		System.out.println(md.toBase64());

	}
}
