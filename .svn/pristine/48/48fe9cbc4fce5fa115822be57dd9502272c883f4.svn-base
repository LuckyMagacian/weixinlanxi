package com.lanxi.weixin.mapper;

import java.util.List;

import com.lanxi.weixin.bean.DateTimeBean;
import com.lanxi.weixin.bean.GameScoreBean;

public interface GameScoreMapper {

	/**
	 * 插入游戏分数
	 * @param gameScoreBean
	 */
	public abstract void insertGameScore(GameScoreBean gameScoreBean);
	/**
	 * 飞机大战排行榜
	 * @return
	 */
	public abstract List<GameScoreBean> getRankingList(DateTimeBean dateTimeBean);
	/**
	 * 获取用户历史最高纪录
	 * @param openid	微信id
	 * @return
	 */
	public abstract String getMaxScore(String openid);
}
