package com.lanxi.weixin.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanxi.weixin.service.UserPrizeService;

public class UserPrizeTimeoutManager {

	private static Logger log = Logger.getLogger(UserPrizeTimeoutManager.class);
	
	@Autowired
	private UserPrizeService userPrizeService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	public void userPrizeTimeout(){
		log.info("用户奖品过期定时任务...");
		Date date = new Date();
		String today = sdf.format(date);
		userPrizeService.setUserPrizeTimeout(today);
		log.info("用户奖品过期定时任务结束...");
	}
}
