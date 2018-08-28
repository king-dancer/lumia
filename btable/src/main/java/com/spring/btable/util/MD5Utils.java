package com.spring.btable.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {
	public static void main(String[] args) {
		try {
			System.out.println(getMd5Str("abc123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMd5Str(String str) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		String md5 = new BigInteger(1,md.digest()).toString(16);//BigInteger 整数，如果加密后的字符串以0开头，0会被省去
		return fillMd5(md5);
	}
	/**
	 * BigInteger 整数，如果加密后的字符串以0开头，0会被省去，此方法在加密后的字符串前填充0
	 * @param str
	 * @return
	 */
	public static String fillMd5(String str){
		return str.length()==32?str:fillMd5("0"+str);
	}
}
