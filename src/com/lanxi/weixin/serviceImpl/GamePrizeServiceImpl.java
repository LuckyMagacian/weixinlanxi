package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.GamePrizeBean;
import com.lanxi.weixin.mapper.GamePrizeMapper;
import com.lanxi.weixin.service.GamePrizeService;

@Service
public class GamePrizeServiceImpl implements GamePrizeService {

	@Autowired
	private GamePrizeMapper gamePrizeMapper;
	
	@Override
	public GamePrizeBean getGamePrize(GamePrizeBean gamePrizeBean) {
		
		return gamePrizeMapper.getGamePrize(gamePrizeBean);
	}

}
