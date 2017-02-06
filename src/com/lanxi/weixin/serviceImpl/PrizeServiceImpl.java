package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.mapper.PrizeMapper;
import com.lanxi.weixin.service.PrizeService;
@Service
public class PrizeServiceImpl implements PrizeService {

	@Autowired
	private PrizeMapper prizeMapper;
	
	@Override
	public String getSplbBySpbh(String spbh) {
		
		return prizeMapper.getSplbBySpbh(spbh);
	}

}
