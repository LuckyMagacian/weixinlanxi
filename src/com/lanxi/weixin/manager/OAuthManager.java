package com.lanxi.weixin.manager;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.bean.oauth.WebAccessTokeanBean;
import com.lanxi.weixin.utils.HttpUtils;

public class OAuthManager {

	private static Logger log = Logger.getLogger(OAuthManager.class);
	
	/**
	 * 根据code获得网页access_token
	 * @param code
	 * @return
	 */
	public static WebAccessTokeanBean getWebAccessTokean(String code){
		WebAccessTokeanBean wtbean = null;
		String webAccessTokeanResult = null;
		try{
			String webAccessTokeanUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			webAccessTokeanUrl = webAccessTokeanUrl.replace("APPID", ParamManager.appid).replace("SECRET", ParamManager.appsecret).replace("CODE", code);
			log.info("调取网页access_token的url："+webAccessTokeanUrl);
			webAccessTokeanResult = HttpUtils.sendGet(webAccessTokeanUrl, "utf-8", "3000");
			log.info("调取网页access_token的返回结果："+webAccessTokeanResult);
			wtbean = JSONObject.parseObject(webAccessTokeanResult, WebAccessTokeanBean.class);
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return wtbean;
	}
	/**
	 * 获取用户的详细信息
	 * @param access_token	网页access_token凭证
	 * @param openid		用户的唯一标识
	 * @return
	 */
	public static OpenidDetailBean getOpenidDetail(String access_token,String openid){
		OpenidDetailBean odBean = null;
		try{
			String openidDetailUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
			openidDetailUrl = openidDetailUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
			log.info("调取openid用户详细信息url："+openidDetailUrl);
			String openidDetailResult = HttpUtils.sendGet(openidDetailUrl, "utf-8", "3000");
			log.info("调取openid用户详细信息的返回结果："+openidDetailResult);
			odBean = JSONObject.parseObject(openidDetailResult, OpenidDetailBean.class);
		}catch(Exception e){
			log.info(e.getClass().getName()+":"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return odBean;
	}
	
	public static void main(String[] args) {
		//String json = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200,\"refresh_token\":\"REFRESH_TOKEN\",\"openid\":\"OPENID\",\"scope\":\"SCOPE\"}";
		String json = "{\"errcode\":40029,\"errmsg\":\"invalid code\"}";
		WebAccessTokeanBean bean = JSONObject.parseObject(json, WebAccessTokeanBean.class);
		//System.out.println(bean.toString());
		if(bean==null){
			System.out.println("bean为null");
		}else{
			System.out.println("bean非null");
		}
	}
}
