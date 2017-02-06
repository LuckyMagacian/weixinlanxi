package com.lanxi.weixin.bean.message;

public class TextBean extends MsgBean {

	/** 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） */
	private String content;

	public TextBean(){
		
	}
	
	public TextBean(MsgBean msgBean){
		this.toUserName = msgBean.getToUserName();
		this.fromUserName = msgBean.getFromUserName();
		this.createTime = msgBean.getCreateTime();
	}
	
	public String getReturnXml() {
		StringBuffer sb = new StringBuffer();
		// <xml>
		// <ToUserName><![CDATA[toUser]]></ToUserName>
		// <FromUserName><![CDATA[fromUser]]></FromUserName>
		// <CreateTime>12345678</CreateTime>
		// <MsgType><![CDATA[text]]></MsgType>
		// <Content><![CDATA[你好]]></Content>
		// </xml>
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(this.toUserName).append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(this.fromUserName).append("]]></FromUserName>");
		sb.append("<CreateTime>").append(this.createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[").append(this.msgType).append("]]></MsgType>");
		sb.append("<Content><![CDATA[").append(this.content).append("]]></Content>");
		sb.append("</xml>");
		return sb.toString();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
