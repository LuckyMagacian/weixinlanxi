package com.lanxi.weixin.bean.message;

import java.util.List;

public class NewsBean extends MsgBean{

	/**图文消息个数，限制为10条以内*/
	private String articleCount;
	/**多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应 */
	//private String articles;
	/**多个图文信息的list集合*/
	private List<NewsArticleBean> articlesList;
	
	public NewsBean(){
		
	}
	
	public NewsBean(MsgBean msgBean){
		this.toUserName = msgBean.getToUserName();
		this.fromUserName = msgBean.getFromUserName();
		this.createTime = msgBean.getCreateTime();
	}
	
	public String getReturnXml(){
		StringBuffer sb = new StringBuffer();
//		<xml>
//		<ToUserName><![CDATA[toUser]]></ToUserName>
//		<FromUserName><![CDATA[fromUser]]></FromUserName>
//		<CreateTime>12345678</CreateTime>
//		<MsgType><![CDATA[news]]></MsgType>
//		<ArticleCount>2</ArticleCount>
//		<Articles>
//		<item>
//		<Title><![CDATA[title1]]></Title> 
//		<Description><![CDATA[description1]]></Description>
//		<PicUrl><![CDATA[picurl]]></PicUrl>
//		<Url><![CDATA[url]]></Url>
//		</item>
//		<item>
//		<Title><![CDATA[title]]></Title>
//		<Description><![CDATA[description]]></Description>
//		<PicUrl><![CDATA[picurl]]></PicUrl>
//		<Url><![CDATA[url]]></Url>
//		</item>
//		</Articles>
//		</xml> 
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(this.toUserName).append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(this.fromUserName).append("]]></FromUserName>");
		sb.append("<CreateTime>").append(this.createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[").append(this.msgType).append("]]></MsgType>");
		sb.append("<ArticleCount>").append(this.articleCount).append("</ArticleCount>");
		sb.append("<Articles>");
		for(NewsArticleBean article : this.articlesList){
			sb.append("<item>");
			sb.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>");
			sb.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>");
			sb.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>");
			sb.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>");
			sb.append("</item>");
		}
		sb.append("</Articles>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public List<NewsArticleBean> getArticlesList() {
		return articlesList;
	}
	public void setArticlesList(List<NewsArticleBean> articlesList) {
		this.articlesList = articlesList;
	}
}
