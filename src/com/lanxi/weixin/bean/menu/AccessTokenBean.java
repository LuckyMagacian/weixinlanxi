package com.lanxi.weixin.bean.menu;

public class AccessTokenBean {

	/**获取到的凭证*/
	private String access_token;
	/**创建时间*/
	private String createTime;
	/**凭证有效时间，单位：秒 */
	private String expires_in;
	/**备用*/
	private String beiy;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getBeiy() {
		return beiy;
	}
	public void setBeiy(String beiy) {
		this.beiy = beiy;
	}
}
