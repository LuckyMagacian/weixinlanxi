package com.lanxi.weixin.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.bean.ErWeiCodeBean;
import com.lanxi.weixin.utils.HttpUtils;

public class ErWeiCodeManager {

	private static Logger log = Logger.getLogger(ErWeiCodeManager.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 获取二维码的信息
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static ErWeiCodeBean getErWeiCode(String access_token,String openid){
		ErWeiCodeBean erWeiCodeBean = null;
		try{
			String erWeiCodeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
			erWeiCodeUrl = erWeiCodeUrl.replace("TOKEN", access_token);
			log.info("获取二维码ticket信息的erWeiCodeUrl:"+erWeiCodeUrl);
			String json = getJson();
			log.info("获取二维码ticket信息的json:"+json);
			String result = HttpUtils.sendPostJson(erWeiCodeUrl, json, "utf-8", "utf-8", "60000");
			String createTime = sdf.format(new Date());
			log.info("获取二维码ticket信息的返回结果:"+result);
			JSONObject jsonObject = new JSONObject();
			jsonObject = JSON.parseObject(result);
			String ticket = jsonObject.getString("ticket");
			String expire_seconds = jsonObject.getString("expire_seconds");
			String url = jsonObject.getString("url");
			erWeiCodeBean = new ErWeiCodeBean();
			erWeiCodeBean.setOpenid(openid);
			erWeiCodeBean.setTicket(ticket);
			erWeiCodeBean.setUrl(url);
			erWeiCodeBean.setExpire_seconds(expire_seconds);
			erWeiCodeBean.setCreateTime(createTime);
			erWeiCodeBean.setAction_name("QR_SCENE");
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return erWeiCodeBean;
	}
	
	/**
	 * 获取临时二维码的json
	 * @return
	 */
	public static String getJson(){
		//{"expire_seconds":604800,"action_name":"QR_SCENE","action_info":{"scene":{"scene_id":123}}}
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";
		return json;
	}
	
	public static void main(String[] args) {
		System.out.println(getJson());
	}
}
