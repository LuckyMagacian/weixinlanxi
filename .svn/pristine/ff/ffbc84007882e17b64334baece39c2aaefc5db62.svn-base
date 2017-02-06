package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.menu.AccessTokenBean;
import com.lanxi.weixin.mapper.AccessTokenMapper;
import com.lanxi.weixin.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

	@Autowired
	private AccessTokenMapper accessTokenMapper;
	
	@Override
	public void insertAccessToken(AccessTokenBean accessTokenBean) {
		
		accessTokenMapper.insertAccessToken(accessTokenBean);
	}

	@Override
	public AccessTokenBean getAccessToken() {
		
		return accessTokenMapper.getAccessToken();
	}

}
