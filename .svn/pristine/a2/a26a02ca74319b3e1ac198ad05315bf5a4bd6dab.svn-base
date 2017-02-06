package com.lanxi.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanxi.weixin.bean.ErWeiCodeBean;
import com.lanxi.weixin.bean.UserTicketDealBean;
import com.lanxi.weixin.bean.message.EventBean;
import com.lanxi.weixin.bean.message.MsgBean;
import com.lanxi.weixin.bean.message.NewsArticleBean;
import com.lanxi.weixin.bean.message.NewsBean;
import com.lanxi.weixin.bean.message.TextBean;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.service.ErWeiCodeService;
import com.lanxi.weixin.service.UserTicketDealService;
import com.lanxi.weixin.service.WeixinUserService;
import com.lanxi.weixin.utils.MessageUtil;
import com.lanxi.weixin.utils.SignUtil;

@Controller
@RequestMapping("/received")
public class ReceivedController {

	private static Logger log = Logger.getLogger(ReceivedController.class);
	
	@Autowired
	private ErWeiCodeService erWeiCodeService;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private UserTicketDealService userTicketDealService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@RequestMapping(value="/received.do")
	public String getMethod(HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("微信请求...");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String method = request.getMethod();
		
		if("GET".equalsIgnoreCase(method)){
			log.info("get请求...");
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			if(signature!=null && timestamp!=null && nonce!=null && echostr!=null){
				if (SignUtil.checkSignature(signature, timestamp, nonce)) {
					out.print(echostr);
				}
			}else{
				out.print("success");	
			}
			out.flush();
			out.close();
			return null;
		}else if("POST".equalsIgnoreCase(method)){
			log.info("post请求...");
			String returnXml = null;
			try{
				String content = "您回复的内容有误！";
				Map<String, String> xmlMap = MessageUtil.parseXml2(request);
				String toUserName = xmlMap.get("ToUserName");
				String fromUserName = xmlMap.get("FromUserName");
				String msgType = xmlMap.get("MsgType");
					
				MsgBean msgBean = new MsgBean();
				msgBean.setToUserName(fromUserName);
				msgBean.setFromUserName(toUserName);
				msgBean.setCreateTime(new Date().getTime());
					
				if(MsgBean.REQ_MSG_TYPE_TEXT.equals(msgType)){
					TextBean textBean = new TextBean(msgBean);
					textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
					content = "您发送的是文本消息！";
					textBean.setContent(content);
					returnXml = textBean.getReturnXml();
				}else if(MsgBean.REQ_MSG_TYPE_EVENT.equals(msgType)){
					String event = xmlMap.get("Event");
					if(EventBean.EVENT_SUBSCRIBE.equals(event)){
						TextBean textBean = new TextBean(msgBean);
						textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
						content = "您好！欢迎订阅积分微管家";
						textBean.setContent(content);
						returnXml = textBean.getReturnXml();
						if(null!=xmlMap.get("Ticket") && !"".equals(xmlMap.get("Ticket"))){
							log.info("扫码关注事件...");
							String ticket = xmlMap.get("Ticket");
							ErWeiCodeBean erWeiCodeBean = erWeiCodeService.getErWeiCodeByTicket(ticket);
							String openid = erWeiCodeBean.getOpenid();
							UserTicketDealBean userTicketDealBean = new UserTicketDealBean();
							userTicketDealBean.setOpenid(openid);
							userTicketDealBean.setFromUserName(fromUserName);
							userTicketDealBean.setEvent("subscribe");
							
							int num = userTicketDealService.getReplicationNum(userTicketDealBean);
							log.info("扫码次数："+num+",判断是否重复扫码");
							if(num==0){
								log.info("扫码者第一次扫码订阅，给二维码提供者加10分...");
								OpenidDetailBean openidDetailBean = new OpenidDetailBean();
								openidDetailBean.setOpenid(openid);
								openidDetailBean.setPoints(ParamManager.subscribe_points);
								weixinUserService.updatePointsByOpenid2(openidDetailBean);
							}
							userTicketDealBean.setCreateTime(sdf.format(new Date()));
							userTicketDealService.insertUserTicketDeal(userTicketDealBean);
						}
					}else if(EventBean.EVENT_CLICK.equals(event)){
						if("LEFT_SECOND".equals(xmlMap.get("EventKey"))){
							NewsArticleBean article = new NewsArticleBean();
							article.setTitle("产品中心");
							//article.setDescription("易充值由杭州蓝喜联合中国移动、中国联通、中国电信与各大银行共同打造的，是面向在银行预留手机号码的用户推出的一项便捷的话费充值服务，系统将用户手机号、银行卡号进行绑定，通过手机短信交互的方式实现手机话费的欠费提醒、快捷支付等功能，为银行信用卡、借记卡用户提供便捷、快速、安全的缴费渠道。");
							article.setDescription("杭州蓝喜信息技术有限公司是一家专业从事移动互联网技术开发、便民金融服务、通讯运营商与银行业务外包的移动互联网服务运营商。");
							article.setPicUrl(ParamManager.product_img);
							article.setUrl(ParamManager.product_url);
							List<NewsArticleBean> articleList = new ArrayList<NewsArticleBean>();
							articleList.add(article);
							
							NewsBean newsBean = new NewsBean(msgBean);
							newsBean.setArticleCount(String.valueOf(articleList.size()));
							newsBean.setArticlesList(articleList);
							newsBean.setMsgType(MsgBean.RESP_MSG_TYPE_NEWS);
							returnXml = newsBean.getReturnXml();
						}else if("MIDDLE_FIRST".equals(xmlMap.get("EventKey"))){
							TextBean textBean = new TextBean(msgBean);
							textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
							content = "【客户服务热线：4000552797】";
							textBean.setContent(content);
							returnXml = textBean.getReturnXml();
						}else{
							TextBean textBean = new TextBean(msgBean);
							textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
							content = "尚未开发，敬请期待！";
							textBean.setContent(content);
							returnXml = textBean.getReturnXml();
						}
					}
				}
				log.info("得到返回returnXml："+returnXml);
				if(null == returnXml || "".equals(returnXml)){
					returnXml = "success";
				}
			}catch(Exception e){
				log.error(e.getClass().getName()+":"+e.getMessage(),e);
				out.print("success");
			}
			log.info("返回returnXml："+returnXml);
			out.print(returnXml);
			out.flush();
			out.close();
			return null;
		}else{
			log.error("请求出错...");
			out.print("success");
			out.flush();
			out.close();
			return null;
		}
	}
	
}
