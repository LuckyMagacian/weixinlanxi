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

	protected static Logger log = Logger.getLogger(AccessTokenManager.class);
	
	@Autowired
	protected AccessTokenService accessTokenService;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public void getAccessToken(){
		log.info("定时任务，加载access_token...");
		Date date = new Date();
		String url = ParamManager2.getAccessTokenUrl();
		log.info("获取access_token的url:"+url);
		String result = HttpUtils.sendGet(url, "utf-8", "3000");
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSON.parseObject(result);
		String access_token = jsonObject.getString("access_token");
		String expires_in = jsonObject.getString("expires_in");
		AccessTokenBean accessTokenBean = new AccessTokenBean();
		accessTokenBean.setAccess_token(access_token);
		accessTokenBean.setExpires_in(expires_in);
		accessTokenBean.setCreateTime(sdf.format(date));
		accessTokenService.insertAccessToken(accessTokenBean);
		log.info("定时任务，加载access_token完毕...");
	}
}
