package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.ErWeiCodeBean;
import com.lanxi.weixin.mapper.ErWeiCodeMapper;
import com.lanxi.weixin.service.ErWeiCodeService;

@Service
public class ErWeiCodeServiceImpl implements ErWeiCodeService {

	@Autowired
	private ErWeiCodeMapper erWeiCodeMapper;
	
	@Override
	public ErWeiCodeBean getErWeiCodeByOpenid(String openid) {
		
		return erWeiCodeMapper.getErWeiCodeByOpenid(openid);
	}

	@Override
	public int updateErWeiCode(ErWeiCodeBean erWeiCodeBean) {
		
		return erWeiCodeMapper.updateErWeiCode(erWeiCodeBean);
	}

	@Override
	public int insertErWeiCode(ErWeiCodeBean erWeiCodeBean) {
		
		return erWeiCodeMapper.insertErWeiCode(erWeiCodeBean);
	}

	@Override
	public ErWeiCodeBean getErWeiCodeByTicket(String ticket) {
		
		return erWeiCodeMapper.getErWeiCodeByTicket(ticket);
	}

}
