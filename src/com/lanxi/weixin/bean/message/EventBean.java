package com.lanxi.weixin.bean.message;

public class EventBean extends MsgBean {

	/**事件类型*/
	private String event;
	
	/**订阅事件*/
	public final static String EVENT_SUBSCRIBE = "subscribe";
	/**取消订阅事件*/
	public final static String EVENT_UNSUBSCRIBE = "unsubscribe";
	/**自定义菜单事件*/
	public final static String EVENT_CLICK = "CLICK";
	/**点击菜单跳转链接时的事件推送 */
	public final static String EVENT_VIEW = "VIEW";
	
	//还有上报地理位置事件
	//还有自定义菜单事件
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
}
