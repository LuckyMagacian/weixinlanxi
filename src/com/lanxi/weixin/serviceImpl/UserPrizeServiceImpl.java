package com.lanxi.weixin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.PageBean;
import com.lanxi.weixin.bean.UserPrizeBean;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.mapper.UserPrizeMapper;
import com.lanxi.weixin.service.UserPrizeService;

@Service
public class UserPrizeServiceImpl implements UserPrizeService {

	@Autowired
	private UserPrizeMapper userPrizeMapper;
	
	@Override
	public void insertUserPrize(UserPrizeBean userPrizeBean) {
		userPrizeMapper.insertUserPrize(userPrizeBean);
	}

	@Override
	public UserPrizeBean getUserPrizeByOpenidAndDate(
			OpenidDetailBean openidDetailBean) {
		
		return userPrizeMapper.getUserPrizeByOpenidAndDate(openidDetailBean);
	}

	@Override
	public void updateUserPrizeStatusByOpenid(UserPrizeBean userPrizeBean) {
		userPrizeMapper.updateUserPrizeStatusByOpenid(userPrizeBean);
	}

	@Override
	public UserPrizeBean getUserPrizeToday(UserPrizeBean userPrizeBean) {
		
		return userPrizeMapper.getUserPrizeToday(userPrizeBean);
	}

	@Override
	public void setUserPrizeTimeout(String date) {
		
		userPrizeMapper.setUserPrizeTimeout(date);
	}

	@Override
	public List<UserPrizeBean> getUserPrizeListByOpenid(String openid) {
		
		return userPrizeMapper.getUserPrizeListByOpenid(openid);
	}

	@Override
	public List<UserPrizeBean> getUserPrizeListByOpenid2(PageBean pageBean) {
		
		return userPrizeMapper.getUserPrizeListByOpenid2(pageBean);
	}

	@Override
	public UserPrizeBean getUserPrizeByPrizeGrade(UserPrizeBean userPrizeBean) {
		
		return userPrizeMapper.getUserPrizeByPrizeGrade(userPrizeBean);
	}

	@Override
	public void updateUserPrizeStatusByOpenid2(UserPrizeBean userPrizeBean) {
		
		userPrizeMapper.updateUserPrizeStatusByOpenid2(userPrizeBean);
	}

}
