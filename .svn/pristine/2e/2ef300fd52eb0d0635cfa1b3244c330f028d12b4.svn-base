package com.lanxi.weixin.service;

import com.lanxi.weixin.bean.GameResultBean;

public interface GameResultService {

	/**
	 * 插入游戏结果
	 * @param gameResultBean
	 */
	public abstract void insertGameResult(GameResultBean gameResultBean);
	/**
	 * 获得大转盘游戏用户当日玩的次数
	 * @param openid
	 */
	public abstract int getTurnTableDayCountByOpenid(String openid);
	/**
	 * 获取用户当日玩游戏的次数
	 * @param gameResultBean
	 * @return
	 */
	public abstract int getGameDayCount(GameResultBean gameResultBean);
}
