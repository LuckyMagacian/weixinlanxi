package com.lanxi.weixin.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.bean.menu.AccessTokenBean;
import com.lanxi.weixin.utils.HttpUtils;

public class AccessTokenManager2{
	private static Logger log=Logger.getLogger(AccessTokenManager2.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//	public String getAccessToken(){
//		return HttpUtils.sendGet(ParamManager2.getAccessTokenUrl(), "utf-8", "2000");
////		log.info("定时任务，加载access_token...");
////		Date date = new Date();
////		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
////		url = url.replace("APPID", ParamManager.appid).replace("APPSECRET", ParamManager.appsecret);
////		log.info("获取access_token的url:"+url);
////		String result = HttpUtils.sendGet(url, "utf-8", "3000");
////		JSONObject jsonObject = new JSONObject();
////		jsonObject = JSON.parseObject(result);
////		String access_token = jsonObject.getString("accessToken");
////		String expires_in = jsonObject.getString("expiresIn");
////		AccessTokenBean accessTokenBean = new AccessTokenBean();
////		accessTokenBean.setAccess_token(access_token);
////		accessTokenBean.setExpires_in(expires_in);
////		accessTokenBean.setCreateTime(sdf.format(date));
////		accessTokenService.insertAccessToken(accessTokenBean);
////		log.info("定时任务，加载access_token完毕...");
//	}
	
	public static AccessTokenBean getAccessToken(){
		String url =ParamManager2.getAccessTokenUrl();
		log.info("获取access_token的url:"+url);
		Date date = new Date();
		String result = HttpUtils.sendGet(url, "utf-8", "3000");
		JSONObject jsonObject =JSONObject.parseObject(result);
		String access_token = jsonObject.getString("accessToken");
		String expires_in = jsonObject.getString("expiresIn");
		AccessTokenBean accessTokenBean = new AccessTokenBean();
		accessTokenBean.setAccess_token(access_token);
		accessTokenBean.setExpires_in(expires_in);
		accessTokenBean.setCreateTime(sdf.format(date));
		log.info("定时任务，加载access_token完毕...");
		return accessTokenBean;
	}
}
