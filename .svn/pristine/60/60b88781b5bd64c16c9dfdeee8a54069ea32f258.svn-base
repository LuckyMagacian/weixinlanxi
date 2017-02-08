package com.lanxi.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5Util {
	private static Logger log = Logger.getLogger(MD5Util.class);
	private static final String ENCODING = "UTF-8";
	private static final String MD5 = "MD5";
	private static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	// MD5加密
	public static final String md5LowerCaseWithCharset(String src, String charset) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(src.getBytes(charset));
		byte b[] = md.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();// 32位的加密
	}

	// MD5加密
	public static final String md5LowerCase(String src) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(src.getBytes("GBK"));
		byte b[] = md.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();// 32位的加密
	}
	public final static String MD5(String s) {
		try {
			s = URLEncoder.encode(s, ENCODING);
			byte[] btInput = s.getBytes(ENCODING);
			MessageDigest mdInst = MessageDigest.getInstance(MD5);
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = CHARS[byte0 >>> 4 & 0xf];
				str[k++] = CHARS[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public final static String md5UpperCase(String s) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] btInput = s.getBytes("GBK");
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// 使用指定的字节更新摘要
		mdInst.update(btInput);
		// 获得密文
		byte[] md = mdInst.digest();
		// 把密文转换成十六进制的字符串形式
		// byte[] data = { (byte) 0xfe, (byte) 0xff, 0x00, 0x61 };
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;

		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(md5LowerCase("123123fw efwefw"));
	}
	
}