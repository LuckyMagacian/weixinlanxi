package com.lanxi.weixin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.manager.ParamManager;

public class AccessTokenUtil {

	/**
	 * 获取凭证
	 * @return
	 */
	public static String getAccessToken(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		url = url.replace("APPID", ParamManager.appid).replace("APPSECRET", ParamManager.appsecret);
		System.out.println(url);
		String result = HttpUtils.sendGet(url, "utf-8", "3000");
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSON.parseObject(result);
		String access_token = jsonObject.getString("access_token");
		return access_token;
	}
	
	public static void main(String[] args) {
		ParamManager.readConfig();
		String access_token = getAccessToken();
		System.out.println(access_token);
		//oyBu_-9qB3K_fYjsD2j-DMxffXMayEvnDixFHvlLQC4GTYWSwlm1rDQ5Px0A4-08nFxQ1KYlIGHBgrpgy-IIE6F88Fe2Mr1MObdKXnIaThKyBSpZIXeXVEf2rEaw2dbODTCcAAAMCL
	}
}
