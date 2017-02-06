package com.lanxi.weixin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.GameResultBean;
import com.lanxi.weixin.mapper.GameResultMapper;
import com.lanxi.weixin.service.GameResultService;

@Service
public class GameResultServiceImpl implements GameResultService {

	@Autowired
	private GameResultMapper gameResultMapper;
	
	@Override
	public void insertGameResult(GameResultBean gameResultBean) {
		gameResultMapper.insertGameResult(gameResultBean);
	}

	@Override
	public int getTurnTableDayCountByOpenid(String openid) {
		
		return gameResultMapper.getTurnTableDayCountByOpenid(openid);
	}

	@Override
	public int getGameDayCount(GameResultBean gameResultBean) {
		
		return gameResultMapper.getGameDayCount(gameResultBean);
	}

}
