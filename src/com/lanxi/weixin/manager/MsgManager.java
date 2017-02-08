package com.lanxi.weixin.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.lanxi.weixin.bean.message.EventBean;
import com.lanxi.weixin.bean.message.MsgBean;
import com.lanxi.weixin.bean.message.NewsArticleBean;
import com.lanxi.weixin.bean.message.NewsBean;
import com.lanxi.weixin.bean.message.TextBean;
import com.lanxi.weixin.utils.MessageUtil;

public class MsgManager {

	private static Logger log = Logger.getLogger(MsgManager.class);
	
	
	
	public static String handleMsg(HttpServletRequest request){
		String content = "您回复的内容有误！";
		String returnXml = null;
		try{
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
			}else if(MsgBean.REQ_MSG_TYPE_IMAGE.equals(msgType)){
				TextBean textBean = new TextBean(msgBean);
				textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
				content = "您发送的是图片消息！";
				textBean.setContent(content);	
				returnXml = textBean.getReturnXml();
			}else if(MsgBean.REQ_MSG_TYPE_VOICE.equals(msgType)){
				TextBean textBean = new TextBean(msgBean);
				textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
				content = "您发送的是语音消息！";
				textBean.setContent(content);
				returnXml = textBean.getReturnXml();
			}else if(MsgBean.REQ_MSG_TYPE_VIDEO.equals(msgType)){
				TextBean textBean = new TextBean(msgBean);
				textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
				content = "您发送的是视频消息！";
				textBean.setContent(content);
				returnXml = textBean.getReturnXml();
			}else if(MsgBean.REQ_MSG_TYPE_SHORTVIDEO.equals(msgType)){
				TextBean textBean = new TextBean(msgBean);
				textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
				content = "您发送的是小视频消息！";
				textBean.setContent(content);
				returnXml = textBean.getReturnXml();
			}else if(MsgBean.REQ_MSG_TYPE_LOCATION.equals(msgType)){
				TextBean textBean = new TextBean(msgBean);
				textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
				content = "您发送的是地理位置消息！";
				textBean.setContent(content);
				returnXml = textBean.getReturnXml();
			}else if(MsgBean.REQ_MSG_TYPE_LINK.equals(msgType)){
				TextBean textBean = new TextBean(msgBean);
				textBean.setMsgType(MsgBean.RESP_MSG_TYPE_TEXT);
				content = "您发送的是链接消息！";
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
				}else if(EventBean.EVENT_UNSUBSCRIBE.equals(event)){
					return null;
				}else if(EventBean.EVENT_CLICK.equals(event)){
					if("LEFT_SECOND".equals(xmlMap.get("EventKey"))){
						NewsArticleBean article = new NewsArticleBean();
						article.setTitle("产品中心");
						article.setDescription("易充值由杭州蓝喜联合中国移动、中国联通、中国电信与各大银行共同打造的，是面向在银行预留手机号码的用户推出的一项便捷的话费充值服务，系统将用户手机号、银行卡号进行绑定，通过手机短信交互的方式实现手机话费的欠费提醒、快捷支付等功能，为银行信用卡、借记卡用户提供便捷、快速、安全的缴费渠道。");
						article.setPicUrl("http://www.188lanxi.com/weixinlanxi/img/cultureShow5.jpg");
						article.setUrl("http://mp.weixin.qq.com/s?__biz=MzIyODE0NTgzMA==&mid=401768506&idx=1&sn=7185d2f81a686e10cd6b600178f0153a");
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
				}else if(EventBean.EVENT_VIEW.equals(event)){
					return null;
				}
			}
			
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return returnXml;
	}
	
	
}
