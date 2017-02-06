package com.lanxi.weixin.mapper;

import com.lanxi.weixin.bean.oauth.OpenidDetailBean;

public interface WeixinUserMapper {

	/**
	 * 根据openid获取微信用户的基本信息
	 * @param openid	微信用户的唯一id
	 * @return
	 */
	public abstract OpenidDetailBean getWeixinUserByOpenid(String openid);
	/**
	 * 将获取到的用户信息插入到微信用户表中
	 * @param openidDetailBean
	 */
	public abstract int insertWeixinUser(OpenidDetailBean openidDetailBean);
	/**
	 * 更新微信用户信息
	 * @param openidDetailBean(不包括用户的积分)
	 */
	public abstract int updateWeixinUser(OpenidDetailBean openidDetailBean);
	/**
	 * 更新用户积分
	 * @param openidDetailBean
	 */
	public abstract void updatePointsByOpenid(OpenidDetailBean openidDetailBean);
	/**
	 * 更新用户积分
	 * @param openidDetailBean
	 */
	public abstract void updatePointsByOpenid2(OpenidDetailBean openidDetailBean);
}
