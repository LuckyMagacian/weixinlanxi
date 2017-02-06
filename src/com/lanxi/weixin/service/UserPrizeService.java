package com.lanxi.weixin.service;

import java.util.List;

import com.lanxi.weixin.bean.PageBean;
import com.lanxi.weixin.bean.UserPrizeBean;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
// TODO 有点乱....
public interface UserPrizeService {

	/**
	 * 插入用户奖品表
	 * @param userPrizeBean
	 */
	public abstract void insertUserPrize(UserPrizeBean userPrizeBean);
	/**
	 * 查询用户上周是否飞机大战前十名
	 * @param openidDetailBean
	 * @return
	 */
	public abstract UserPrizeBean getUserPrizeByOpenidAndDate(OpenidDetailBean openidDetailBean);
	/**
	 * 根据用户ID更新用户奖品表状态
	 * @param userPrizeBean
	 */
	public abstract void updateUserPrizeStatusByOpenid(UserPrizeBean userPrizeBean);
	/**
	 * 根据用户ID,创建日期和创建时间更新用户奖品表状态
	 * @param userPrizeBean
	 */
	public abstract void updateUserPrizeStatusByOpenid2(UserPrizeBean userPrizeBean);
	/**
	 * 获取用户当日的获奖记录（大转盘）
	 * @param userPrizeBean
	 * @return
	 */
	public abstract UserPrizeBean getUserPrizeToday(UserPrizeBean userPrizeBean);
	/**
	 * 设置用户奖品过期
	 * @param date
	 */
	public abstract void setUserPrizeTimeout(String date);
	/**
	 * 获取用户的奖品列表
	 * @param openid
	 * @return
	 */
	public abstract List<UserPrizeBean> getUserPrizeListByOpenid(String openid);
	/**
	 * 获取用户的奖品列表(分页)
	 * @param openid
	 * @return
	 */
	public abstract List<UserPrizeBean> getUserPrizeListByOpenid2(PageBean pageBean);
	/**
	 * 根据奖品等级获取用户的获奖记录（大转盘）
	 * @param userPrizeBean
	 * @return
	 */
	public abstract UserPrizeBean getUserPrizeByPrizeGrade(UserPrizeBean userPrizeBean);
}
