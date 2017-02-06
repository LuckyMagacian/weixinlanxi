package com.lanxi.weixin.test;

import com.alibaba.fastjson.JSONObject;

public class FastJsonTest2 {

	public static void main(String[] args) {
		String result = "{\"retCode\":\"0000\",\"retMsg\":\"购买成功\"}";
		String retCode = JSONObject.parseObject(result).getString("retCode");
		String retMsg = JSONObject.parseObject(result).getString("retMsg");
		System.out.println(retCode+","+retMsg);
	}
}
