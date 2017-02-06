package com.lanxi.weixin.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 获取周一的日期
	 * @param n		n为推迟的周数，0本周，-1向前推迟一周，2下周，依次类推
	 * @return
	 */
	public static String getMondayDate(int n){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n*7);
		//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		String dateString = sdfDate.format(cal.getTime());
		return dateString;
	}
	
	/**
	 * 获取周日的日期
	 * @param n		n为推迟的周数，1本周，0向前推迟一周，2下周，依次类推
	 * @return
	 */
	public static String getSundayDate(int n){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n*7);
		//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		String dateString = sdfDate.format(cal.getTime());
		return dateString;
	}
	
	//因为星期日是一周的第一天，和我们的一周的最后一天不一样，所以这里的工具类获取星期一和星期天要加上星期天的判断
	
	/**
	 * 获取上周一日期
	 * @return
	 */
	public static String getLastMonday(){
		Date dateToday = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToday);
        int todayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if(todayIndex == Calendar.SUNDAY){
        	return getMondayDate(-2);
        }
		return getMondayDate(-1);
	}
	/**
	 * 获取上周日日期
	 * @return
	 */
	public static String getLastSunDay(){
		Date dateToday = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToday);
        int todayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if(todayIndex == Calendar.SUNDAY){
        	return getSundayDate(-1);
        }
		return getSundayDate(0);
	}
	/**
	 * 获取这周一日期
	 * @return
	 */
	public static String getThisMonday(){
		Date dateToday = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToday);
        int todayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if(todayIndex == Calendar.SUNDAY){
        	return getMondayDate(-1);
        }
		return getMondayDate(0);
	}
	/**
	 * 获取这周日日期
	 * @return
	 */
	public static String getThisSunday(){
		Date dateToday = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToday);
        int todayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if(todayIndex == Calendar.SUNDAY){
        	return getSundayDate(0);
        }
		return getSundayDate(1);
	}

	/**
	 * yyyyMMddHHmmss日期格式
	 */
	public static SimpleDateFormat sdfYmdHms = new SimpleDateFormat("yyyyMMddHHmmss");
	public static String getCurrentyyyyMMddHHmmss() {
		return sdfYmdHms.format(new Date());
	}
	/**
	 * yyyyMMdd日期格式
	 */
	public static SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyyMMdd");
	public static String getCurrentyyyyMMdd() {
		return sdfYmd.format(new Date());
	}
	/**
	 * HHmmss日期格式
	 */
	public static SimpleDateFormat sdfHms = new SimpleDateFormat("HHmmss");
	public static String getCurrentHHmmss() {
		return sdfHms.format(new Date());
	}

	public static void main(String args[]){
		System.out.println("上周一日期："+getLastMonday());
		System.out.println("上周日日期："+getLastSunDay());
		System.out.println("这周一日期："+getThisMonday());
		System.out.println("这周日日期："+getThisSunday());
		System.out.println(new Date());
		System.out.println(Calendar.getInstance().getTime());
	}
}
