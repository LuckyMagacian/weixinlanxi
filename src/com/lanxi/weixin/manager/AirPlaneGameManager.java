package com.lanxi.weixin.manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanxi.weixin.bean.DateTimeBean;
import com.lanxi.weixin.bean.GamePrizeBean;
import com.lanxi.weixin.bean.GameScoreBean;
import com.lanxi.weixin.bean.UserPrizeBean;
import com.lanxi.weixin.service.GamePrizeService;
import com.lanxi.weixin.service.GameScoreService;
import com.lanxi.weixin.service.UserPrizeService;
import com.lanxi.weixin.utils.DateTimeUtil;

public class AirPlaneGameManager {

	private static Logger log = Logger.getLogger(AirPlaneGameManager.class);
	
	@Autowired
	private GameScoreService gameScoreService;
	@Autowired
	private UserPrizeService userPrizeService;
	@Autowired
	private GamePrizeService gamePrizeService;
	
	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
	
	/**
	 * 每周一将上周的排行榜插入用户奖品表中
	 */
	public void getRankingResult(){
		try{
			log.info("每周一将上周的排行榜插入用户奖品表中...");
			Date dateToday = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateToday);
	        int todayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if(todayIndex != 1){
				log.info("当日不是星期一，结束方法："+getWeekOfDate(dateToday));
				return;
			}
			log.info("获取上周飞机大战排行榜,就排行榜用户插入用户奖品表中...");
			DateTimeBean dateTimeBean = new DateTimeBean();
			dateTimeBean.setStartTime(DateTimeUtil.getLastMonday());
			dateTimeBean.setEndTime(DateTimeUtil.getLastSunDay());
			log.info("上周一日期："+DateTimeUtil.getLastMonday()+"，上周日日期："+DateTimeUtil.getLastSunDay());
			List<GameScoreBean> rankingList = gameScoreService.getRankingList(dateTimeBean);
			UserPrizeBean userPrizeBean = null;
			Date date = null;
			GamePrizeBean gamePrizeBean = null;
			for(int i=0;i<rankingList.size();i++){
				date = new Date();
				String dateString = sdfDate.format(date);
				String timeString = sdfTime.format(date);
				
				Calendar cal = Calendar.getInstance();//获得有效期
				cal.add(Calendar.DAY_OF_MONTH, ParamManager.airPlane_prize_deadline);
				Date date2 = cal.getTime();
				String deadline = sdfDate.format(date2);
				
				userPrizeBean = new UserPrizeBean();
				userPrizeBean.setOpenid(rankingList.get(i).getOpenid());
				userPrizeBean.setGameType(rankingList.get(i).getGameType());
				userPrizeBean.setCreateDate(dateString);
				userPrizeBean.setCreateTime(timeString);
				userPrizeBean.setDeadline(deadline);
				
				gamePrizeBean = new GamePrizeBean();
				gamePrizeBean.setGameType("AirPlaneGame");
				
				if(i<=2){
					gamePrizeBean.setPrizeGrade(String.valueOf(i+1));
				}else{
					gamePrizeBean.setPrizeGrade(String.valueOf(4));
				}
				gamePrizeBean = gamePrizeService.getGamePrize(gamePrizeBean);
				userPrizeBean.setSpbh(gamePrizeBean.getSpbh());
				userPrizeBean.setSpsl(gamePrizeBean.getSpsl());
				userPrizeBean.setPrizeGrade(gamePrizeBean.getPrizeGrade());
				userPrizeBean.setPoints(gamePrizeBean.getPoints());
				/*if(i==0){	//第一名
					userPrizeBean.setSpbh("4002");
					userPrizeBean.setSpsl("1");
					userPrizeBean.setPrizeGrade("1");
					userPrizeBean.setPoints("500");
				}else if(i==1){	//第二名
					userPrizeBean.setSpbh("5001");
					userPrizeBean.setSpsl("1");
					userPrizeBean.setPrizeGrade("2");
					userPrizeBean.setPoints("300");
				}else if(i==2){	//第三名
					userPrizeBean.setSpbh("2002");
					userPrizeBean.setSpsl("1");
					userPrizeBean.setPrizeGrade("3");
					userPrizeBean.setPoints("100");
				}else{
					userPrizeBean.setSpbh("2001");
					userPrizeBean.setSpsl("1");
					userPrizeBean.setPrizeGrade("4");
					userPrizeBean.setPoints("50");
				}*/
				userPrizeBean.setPrizeStatus("1");
				userPrizeBean.setPointsStatus("1");
				userPrizeService.insertUserPrize(userPrizeBean);
			}
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
		}
	}
	
	public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	public static void main(String[] args) {
		Date date = new Date();
		//System.out.println(getWeekOfDate(date));
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(w);
	}
}
