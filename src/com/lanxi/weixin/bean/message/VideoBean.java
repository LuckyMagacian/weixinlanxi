package com.lanxi.weixin.bean.message;

public class VideoBean extends MsgBean {

	/**视频消息媒体id，可以调用多媒体文件下载接口拉取数据。*/
	private String mediaId;
	/**视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。*/
	private String thumbMediaId;
	/**消息id，64位整型 */
	private String msgId;
	
	/**视频消息的标题 */
	private String title;
	/**视频消息的描述 */
	private String description;
	
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
