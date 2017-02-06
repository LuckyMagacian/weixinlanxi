package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.mapper.WeixinUserMapper;
import com.lanxi.weixin.service.WeixinUserService;

@Service
public class WeixinUserServiceImpl implements WeixinUserService{

	@Autowired
	private WeixinUserMapper weixinUserMapper;
	
	@Override
	public int insertWeixinUser(OpenidDetailBean openidDetailBean) {
		
		return weixinUserMapper.insertWeixinUser(openidDetailBean);
	}

	@Override
	public int updateWeixinUser(OpenidDetailBean openidDetailBean) {
		
		return weixinUserMapper.updateWeixinUser(openidDetailBean);
	}

	@Override
	public OpenidDetailBean getWeixinUserByOpenid(String openid) {
		
		return weixinUserMapper.getWeixinUserByOpenid(openid);
	}

	@Override
	public void updatePointsByOpenid(OpenidDetailBean openidDetailBean) {
		
		weixinUserMapper.updatePointsByOpenid(openidDetailBean);
	}

	@Override
	public void updatePointsByOpenid2(OpenidDetailBean openidDetailBean) {
		
		weixinUserMapper.updatePointsByOpenid2(openidDetailBean);
	}


}
