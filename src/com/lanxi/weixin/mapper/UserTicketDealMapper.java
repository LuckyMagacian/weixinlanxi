package com.lanxi.weixin.mapper;

import com.lanxi.weixin.bean.UserTicketDealBean;

public interface UserTicketDealMapper {

	/**
	 * 获取扫描重复次数
	 * @param userTicketDealBean
	 * @return
	 */
	public abstract int getReplicationNum(UserTicketDealBean userTicketDealBean);
	/**
	 * 插入扫码记录
	 * @param userTicketDealBean
	 */
	public abstract void insertUserTicketDeal(UserTicketDealBean userTicketDealBean);
}
