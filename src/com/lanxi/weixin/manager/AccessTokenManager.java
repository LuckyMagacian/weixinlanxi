package com.lanxi.weixin.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.bean.menu.AccessTokenBean;
import com.lanxi.weixin.service.AccessTokenService;
import com.lanxi.weixin.utils.HttpUtils;

public class AccessTokenManager {

	private static Logger log = Logger.getLogger(AccessTokenManager.class);
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public void getAccessToken(){
		log.info("定时任务，加载access_token...");//记录日志
		Date date = new Date();//创建当前时间戳
		//微信获取access_token接口
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		//参数替换为自己的appid与appsecret
		url = url.replace("APPID", ParamManager.appid).replace("APPSECRET", ParamManager.appsecret);
		log.info("获取access_token的url:"+url);//记录日志
		String result = HttpUtils.sendGet(url, "utf-8", "3000");//发送get请求
		JSONObject jsonObject = new JSONObject();//创建json对象
		jsonObject = JSON.parseObject(result);//将get请求结果转换为json
		String access_token = jsonObject.getString("access_token");//从json中获取access_token
		String expires_in = jsonObject.getString("expires_in");//从json中获取access_token有效期
		AccessTokenBean accessTokenBean = new AccessTokenBean();//创建accessTokenBean对象
		accessTokenBean.setAccess_token(access_token);
		accessTokenBean.setExpires_in(expires_in);
		accessTokenBean.setCreateTime(sdf.format(date));
		accessTokenService.insertAccessToken(accessTokenBean);//将accessTokenBean对象加入到accessTokenService
		log.info("定时任务，加载access_token完毕...");//记录日志
	}
}
