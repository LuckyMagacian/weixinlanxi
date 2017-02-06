package com.lanxi.weixin.bean.message;

public class ImageBean extends MsgBean {

	/**图片链接 */
	private String picUrl;
	/**
	 * (接收时)图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 
	 * (回复时)通过素材管理接口上传多媒体文件，得到的id
	 */
	private String mediaId;
	/**消息id，64位整型 */
	private String msgId;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
