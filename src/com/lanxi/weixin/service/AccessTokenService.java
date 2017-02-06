package com.lanxi.weixin.service;

import com.lanxi.weixin.bean.menu.AccessTokenBean;

public interface AccessTokenService {

	/**
	 * 插入access_token到数据库
	 * @param accessTokenBean
	 */
	public abstract void insertAccessToken(AccessTokenBean accessTokenBean);
	/**
	 * 获取access_token
	 * @return
	 */
	public abstract AccessTokenBean getAccessToken();
}
