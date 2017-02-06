package com.lanxi.weixin.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.bean.menu.AccessTokenBean;
import com.lanxi.weixin.service.AccessTokenService;
import com.lanxi.weixin.utils.HttpUtils;

public class SystemInitManager implements ApplicationListener<ContextRefreshedEvent> {

	private static Logger log = Logger.getLogger(SystemInitManager.class);
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
			log.info("启动加载方法......");
			//需要在这里启动加载，调取access_token方法，并保存到数据库中
			Date date = new Date();
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
			url = url.replace("APPID", ParamManager.appid).replace("APPSECRET", ParamManager.appsecret);
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
			log.info("启动加载方法完毕......");
		}
	}

}
