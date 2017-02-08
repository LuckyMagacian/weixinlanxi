package com.lanxi.weixin.manager;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.bean.oauth.WebAccessTokeanBean;
import com.lanxi.weixin.utils.HttpUtils;

public class OAuthManager2 extends OAuthManager {
	/**
	 * 根据code获得网页access_token
	 * @param code
	 * @return
	 */
	public static WebAccessTokeanBean getWebAccessTokean(String code){
		WebAccessTokeanBean wtbean = null;
		String webAccessTokeanResult = null;
		try{
			String webAccessTokeanUrl = ParamManager2.getWebAccessTokenUrl()+"code="+code;
			log.info("调取网页access_token的url："+webAccessTokeanUrl);
			webAccessTokeanResult = HttpUtils.sendGet(webAccessTokeanUrl, "utf-8", "3000");
			log.info("调取网页access_token的返回结果："+webAccessTokeanResult);
			JSONObject jobj=JSONObject.parseObject(webAccessTokeanResult);
			wtbean =new WebAccessTokeanBean();
			wtbean.setAccess_token(jobj.getString("accessToken"));
			wtbean.setExpires_in(jobj.getString("expiresIn"));
			wtbean.setOpenid(jobj.getString("openId"));
			wtbean.setRefresh_token(jobj.getString("refreshToken"));
			wtbean.setScope(jobj.getString("scope"));
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
}
