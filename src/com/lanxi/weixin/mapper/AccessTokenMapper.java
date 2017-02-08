package com.lanxi.weixin.mapper;

import com.lanxi.weixin.bean.menu.AccessTokenBean;

public interface AccessTokenMapper {

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
