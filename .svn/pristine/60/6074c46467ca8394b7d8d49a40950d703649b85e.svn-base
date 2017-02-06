package com.lanxi.weixin.utils;

public class GamePrizeUtil {

	/**
	 * 根据号码和商品编号得到商品编号
	 * @param phone		手机号码
	 * @param spbh		移动商品编号|联通商品编号|电信商品编号
	 * @return
	 */
	public static String getSpbhByPhone(String phone,String spbh){
		String[] str = spbh.split("\\|");
		if(str.length==1){
			return str[0];
		}else if(str.length==2){
			return str[0];
		}else if(str.length==3){
			int num = MobileUtil.getMobileType(phone);
			return str[num-1];
		}else{
			return str[0];
		}
	}
	/**
	 * 根据商品数量和手机号码获得商品数量
	 * @param phone		手机号码
	 * @param spsl		移动商品数量|联通商品数量|电信商品数量
	 * @return
	 */
	public static String getSpslByPhone(String phone,String spsl){
		String[] str = spsl.split("\\|");
		if(str.length==1){
			return str[0];
		}else if(str.length==2){
			return str[0];
		}else if(str.length==3){
			int num = MobileUtil.getMobileType(phone);
			return str[num-1];
		}else{
			return str[0];
		}
	}
	
	public static void main(String[] args) {
		String str = "1|2|3";
		String[] s1 = str.split("\\|");
		System.out.println(s1.length);
		for(String s : s1){
			System.out.println(s);
		}
	}
}
