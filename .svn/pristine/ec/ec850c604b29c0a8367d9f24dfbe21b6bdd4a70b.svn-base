package com.lanxi.weixin.test;

import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.utils.AccessTokenUtil;
import com.lanxi.weixin.utils.HttpUtils;

public class SuCaiTest {

	public static void main(String[] args) {
		ParamManager.readConfig();
		String accessToken = AccessTokenUtil.getAccessToken();
		System.out.println(accessToken);
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", accessToken);
		String result = HttpUtils.sendGet(url, "utf-8", "3000");
		System.out.println(result);
	}
}
