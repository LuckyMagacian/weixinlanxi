package com.lanxi.weixin.utils;

import java.util.Random;

import org.apache.log4j.Logger;

public class GameRandomResult {

	private static Logger log = Logger.getLogger(GameRandomResult.class);
	
	/**
	 * 获得奖项的随机数
	 * @param a0	特等奖的概率
	 * @param a1	一等奖的概率
	 * @param a2	二等奖的概率
	 * @param a3	三等奖的概率
	 * @return
	 */
	public synchronized static float getRandomResult(float a0,float a1,float a2,float a3){
		float result = 0f;
		try{
			float a4 = 1-a0-a1-a2-a3;
			float aSum = a0+a1+a2+a3+a4;
			Random random = new Random();
			float randomNum = random.nextFloat();
			result = aSum * randomNum;
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return result;
	}
	/**
	 * 获得奖项的随机数
	 * @return
	 */
	public synchronized static float getRandomResult(){
		float result = 0f;
		try{
			Random random = new Random();
			float randomNum = random.nextFloat();
			result = randomNum;
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return result;
	}
}
