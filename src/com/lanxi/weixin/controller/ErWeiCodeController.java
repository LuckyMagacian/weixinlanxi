package com.lanxi.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanxi.weixin.bean.ErWeiCodeBean;
import com.lanxi.weixin.bean.menu.AccessTokenBean;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.manager.ErWeiCodeManager;
import com.lanxi.weixin.service.AccessTokenService;
import com.lanxi.weixin.service.ErWeiCodeService;

@Controller
@RequestMapping("/ewCode")
public class ErWeiCodeController {

	private static Logger log = Logger.getLogger(ErWeiCodeController.class);
	
	@Autowired
	private ErWeiCodeService erWeiCodeService;
	@Autowired
	private AccessTokenService accessTokenService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@RequestMapping("/getErWeiCode.do")
	public String getErWeiCode(HttpSession session,Model model){
		log.info("获取二维码...");
		String openid = "";
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			openid = openidDetailBean.getOpenid();
			ErWeiCodeBean erWeiCodeBean = erWeiCodeService.getErWeiCodeByOpenid(openid);
			if(null == erWeiCodeBean || !checkTicket(erWeiCodeBean)){
				//掉接口获取ticket
				AccessTokenBean accessTokenBean = accessTokenService.getAccessToken();
				String access_token = accessTokenBean.getAccess_token();
				erWeiCodeBean = ErWeiCodeManager.getErWeiCode(access_token, openid); 
				int count = erWeiCodeService.updateErWeiCode(erWeiCodeBean);
				if(count<=0){
					erWeiCodeService.insertErWeiCode(erWeiCodeBean);
				}
				model.addAttribute("img", "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+erWeiCodeBean.getTicket());
				return "erWeiCode";
			}
			model.addAttribute("img", "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+erWeiCodeBean.getTicket());
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			model.addAttribute("errMsg", "系统报错，获取二维码失败");
			model.addAttribute("img", "");
		}
		return "erWeiCode";
	}
	/**
	 * ajax调用二维码
	 * @param session
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getErWeiCodeByAjax.do")
	public String getErWeiCodeByAjax(HttpSession session,HttpServletResponse response) throws IOException{
		log.info("获取二维码...");
		String openid = "";
		PrintWriter out = response.getWriter();
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			openid = openidDetailBean.getOpenid();
			ErWeiCodeBean erWeiCodeBean = erWeiCodeService.getErWeiCodeByOpenid(openid);
			if(null == erWeiCodeBean || !checkTicket(erWeiCodeBean)){
				//掉接口获取ticket
				AccessTokenBean accessTokenBean = accessTokenService.getAccessToken();
				String access_token = accessTokenBean.getAccess_token();
				erWeiCodeBean = ErWeiCodeManager.getErWeiCode(access_token, openid); 
				int count = erWeiCodeService.updateErWeiCode(erWeiCodeBean);
				if(count<=0){
					erWeiCodeService.insertErWeiCode(erWeiCodeBean);
				}
				log.info("二维码地址："+"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+erWeiCodeBean.getTicket());
				out.println("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+erWeiCodeBean.getTicket());
				out.flush();
				return null;
			}
			log.info("二维码地址："+"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+erWeiCodeBean.getTicket());
			out.println("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+erWeiCodeBean.getTicket());
			out.flush();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			out.println("../img/default.jpg");
			out.flush();
		}
		return null;
	}
	
	/**
	 * 判断ticket是否过期
	 * @param erWeiCodeBean
	 * @return
	 * @throws ParseException
	 */
	public static boolean checkTicket(ErWeiCodeBean erWeiCodeBean) throws ParseException{
		String createTime = erWeiCodeBean.getCreateTime();
		long expire_seconds = Long.parseLong(erWeiCodeBean.getExpire_seconds())*1000;
		Date date = sdf.parse(createTime);
		long time = date.getTime();
		long now = System.currentTimeMillis();
		if(now < (expire_seconds+time-60000)){	//减去60秒，时间太精确可能出现问题
			return true;	//ticket未过期
		}else{
			return false;	//ticket已过期
		}
	}
	
	public static void main(String[] args) throws ParseException {
		String today = "20160304163600";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = sdf.parse(today);
		long time = date.getTime();
		System.out.println(time);
		System.out.println(System.currentTimeMillis());
	}
}
