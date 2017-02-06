package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.UserTicketDealBean;
import com.lanxi.weixin.mapper.UserTicketDealMapper;
import com.lanxi.weixin.service.UserTicketDealService;

@Service
public class UserTicketDealServiceImpl implements UserTicketDealService {

	@Autowired
	private UserTicketDealMapper userTicketDealMapper;
	
	@Override
	public int getReplicationNum(UserTicketDealBean userTicketDealBean) {
		
		return userTicketDealMapper.getReplicationNum(userTicketDealBean);
	}

	@Override
	public void insertUserTicketDeal(UserTicketDealBean userTicketDealBean) {
		
		userTicketDealMapper.insertUserTicketDeal(userTicketDealBean);
	}

}
