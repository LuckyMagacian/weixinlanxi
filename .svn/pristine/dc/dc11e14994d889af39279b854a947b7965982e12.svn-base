package com.lanxi.weixin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanxi.weixin.bean.DateTimeBean;
import com.lanxi.weixin.bean.GameScoreBean;
import com.lanxi.weixin.mapper.GameScoreMapper;
import com.lanxi.weixin.service.GameScoreService;

@Service
public class GameScoreServiceImpl implements GameScoreService {

	@Autowired
	private GameScoreMapper gameScoreMapper;
	
	@Override
	public void insertGameScore(GameScoreBean gameScoreBean) {
		gameScoreMapper.insertGameScore(gameScoreBean);
	}

	@Override
	public List<GameScoreBean> getRankingList(DateTimeBean dateTimeBean) {
		
		return gameScoreMapper.getRankingList(dateTimeBean);
	}

	@Override
	public String getMaxScore(String openid) {
		
		return gameScoreMapper.getMaxScore(openid);
	}

}
