package com.lanxi.weixin.mapper;

import com.lanxi.weixin.bean.menu.AccessTokenBean;

public interface AccessTokenMapper {

	/**
	 * 插入access_token到数据库
	 * @param accessTokenBean
	 */
	// TODO 为什么不是更新access_token 到数据库
	public abstract void insertAccessToken(AccessTokenBean accessTokenBean);
	/**
	 * 获取access_token
	 * @return
	 */
	public abstract AccessTokenBean getAccessToken();
}
