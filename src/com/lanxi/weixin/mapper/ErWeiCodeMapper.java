package com.lanxi.weixin.mapper;

import com.lanxi.weixin.bean.ErWeiCodeBean;

public interface ErWeiCodeMapper {

	/**
	 * 根据微信Id获取用户二维码信息
	 * @param openid
	 * @return
	 */
	public abstract ErWeiCodeBean getErWeiCodeByOpenid(String openid);
	/**
	 * 更新微信用户二维码信息
	 * @param erWeiCodeBean
	 * @return
	 */
	public abstract int updateErWeiCode(ErWeiCodeBean erWeiCodeBean);
	/**
	 * 插入微信用户二维码信息
	 * @param erWeiCodeBean
	 * @return
	 */
	public abstract int insertErWeiCode(ErWeiCodeBean erWeiCodeBean);
	/**
	 * 根据二维码ticket查找二维码
	 * @param ticket
	 * @return
	 */
	public abstract ErWeiCodeBean getErWeiCodeByTicket(String ticket);
}
