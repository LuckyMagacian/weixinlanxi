package com.lanxi.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.bean.oauth.WebAccessTokeanBean;
import com.lanxi.weixin.manager.OAuthManager;
import com.lanxi.weixin.service.WeixinUserService;
import com.lanxi.weixin.utils.EmojiUtil;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

	private static Logger log = Logger.getLogger(OAuthController.class);
	
	@Autowired
	private WeixinUserService weixinUserService;
	
	@RequestMapping("/authorization.do")
	public String authorization(HttpServletRequest request,HttpServletResponse response){
		log.info("微信网页授权...");
		WebAccessTokeanBean wtBean = null;
		OpenidDetailBean odBean = null;
		try{
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			
			String code = request.getParameter("code");
			
			log.info("获取到的code:"+code);
			if(null!=code && !"".equals(code)){
				wtBean = OAuthManager.getWebAccessTokean(code);
				String access_token = wtBean.getAccess_token();
				String openid = wtBean.getOpenid();
				log.info("access_token:"+access_token+",openid:"+openid);
				odBean = OAuthManager.getOpenidDetail(access_token, openid);
				//TODO
				odBean.setNickname(EmojiUtil.filterEmoji(odBean.getNickname()));
				int odBeanId = weixinUserService.updateWeixinUser(odBean);
				//System.out.println("odBeanId---------"+odBeanId);
				if(odBeanId<=0){
					weixinUserService.insertWeixinUser(odBean);
				}
			}else{
				return "oauthFailed";	//跳转到网页授权失败页面
			}
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			return "oauthFailed";
		}
		//return "redirect:/planeGame/toPlaneGameIndex.do?openid="+odBean.getOpenid();
		return "redirect:/game/gameIndex.do?openid="+odBean.getOpenid();
	}
	
	@RequestMapping("/oauthFailed.do")
	public String oauthFailed(){
		log.info("跳转到网页授权失败页面...");
		
		return null;
	}
}
