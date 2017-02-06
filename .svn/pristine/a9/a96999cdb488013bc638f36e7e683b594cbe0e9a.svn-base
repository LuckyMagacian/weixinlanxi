package com.lanxi.weixin.test;

import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {
		String result = "{\"retCode\":\"-1\",\"retMsg\":\"未找到该串码记录\"}";
		//String result = "{'retCode':-1,'retMsg':未找到该串码记录}";
		String retCode = JSONObject.parseObject(result).getString("retCode");
		String retMsg = JSONObject.parseObject(result).getString("retMsg");
	
		System.out.println(retCode+","+retMsg);
	}
}
