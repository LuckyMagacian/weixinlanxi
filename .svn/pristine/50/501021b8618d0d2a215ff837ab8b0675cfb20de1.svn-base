package com.lanxi.weixin.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ParamManager {

	private static Logger log = Logger.getLogger(ParamManager.class);

	/**http超时时间*/
	public static String timeout;
	/**微信开发AppID(应用ID)*/
	public static String appid;
	/**微信开发AppSecret(应用密钥)*/
	public static String appsecret;
	/**串码验证秘钥*/
	public static String cmyzKey;
	/**串码验证URL*/
	public static String cmyzUrl;
	/**根据串码获得手机号码URL*/
	public static String getSjhmUrl;
	
	/**报文版本号*/
	public static String ver;
	/**地区编码*/
	public static String add;
	/**交易发起机构号*/
	public static String src;
	/**交易发起机构号 农行*/
	public static String src_nh;
	/**交易接收交易号*/
	public static String des;
	/**应用名称*/
	public static String app;
	/**0-需要蓝喜下发短信，1-不需要蓝喜下发短信*/
	public static String needSend;
	public static String needSendExchangeCode;
	/**小积分地址*/
	public static String xjfUrl;
	/**购买的报文编号*/
	public static String msgNo_buy;
	/**小积分秘钥*/
	public static String xjfKey;
	public static String xjfKey_nh;
	/**jyxh 交易序号起始*/
	public static int jyxhStart;

	/**中国移动号段*/
	public static String chinaMobile;
	/**中国联通号段*/
	public static String chinaUnicom;
	/**中国电信号段*/
	public static String chinaTelecom;
	/**飞机大战奖品过期时间（天）*/
	public static int airPlane_prize_deadline;
	/**大转盘奖品过期时间（天）*/
	public static int turnTable_prize_deadline;
	/**刮刮乐奖品过期时间(天)*/
	public static int happyScratch_prize_deadline;
	/**大转盘特等奖概率*/
	public static float TURNTABLE_TDJ;
	/**大转盘一等奖概率*/
	public static float TURNTABLE_YDJ;
	/**大转盘二等奖概率*/
	public static float TURNTABLE_EDJ;
	/**大转盘三等奖概率*/
	public static float TURNTABLE_SDJ;
	/**刮刮乐奖品1概率*/
	public static float HAPPYSCRATCH_J1;
	/**刮刮乐奖品2概率*/
	public static float HAPPYSCRATCH_J2;
	/**刮刮乐奖品3概率*/
	public static float HAPPYSCRATCH_J3;
	/**刮刮乐奖品4概率*/
	public static float HAPPYSCRATCH_J4;
	/**刮刮乐奖品5概率*/
	public static float HAPPYSCRATCH_J5;
	/**刮刮乐奖品6概率*/
	public static float HAPPYSCRATCH_J6;
	/**刮刮乐奖品7概率*/
	public static float HAPPYSCRATCH_J7;
	/**刮刮乐奖品8s概率*/
	public static float HAPPYSCRATCH_J8;
	
	/**扫码关注奖励的积分数*/
	public static int subscribe_points;
	
	/**产品介绍图片地址*/
	public static String product_img;
	/**产品介绍跳转地址*/
	public static String product_url;

	/** 农行串码兑换次数限制 */
	/**时间区间内限制 */
	public static boolean ifUseExchangeLimit;
	public static int exchangeLimit;
	public static String limitBeginDate;
	public static String limitEndDate;
	/** 每日限制 */
	public static boolean ifUseExchangeLimitForDay;
	public static int exchangeLimitForDay;
	
	public static void readConfig() {
		Properties pro = null;
		pro = new Properties();
		try {
			String s = Thread.currentThread().getContextClassLoader()
					.getResource("config.properties").toURI().getPath();
			pro.load(new InputStreamReader(new FileInputStream(s), "utf-8"));

			timeout = pro.getProperty("timeout");
			appid = pro.getProperty("appid");
			appsecret = pro.getProperty("appsecret");
			cmyzKey = pro.getProperty("cmyzKey");
			cmyzUrl = pro.getProperty("cmyzUrl");
			getSjhmUrl = pro.getProperty("getSjhmUrl");
			
			ver = pro.getProperty("ver");
			add = pro.getProperty("add");
			src = pro.getProperty("src");
			des = pro.getProperty("des");
			src_nh = pro.getProperty("src_nh");
			app = pro.getProperty("app");
			needSend = pro.getProperty("needSend");
			needSendExchangeCode = pro.getProperty("needSendExchangeCode");
			xjfUrl = pro.getProperty("xjfUrl");
			msgNo_buy = pro.getProperty("msgNo_buy");
			xjfKey = pro.getProperty("xjfKey");
			xjfKey_nh = pro.getProperty("xjfKey_nh");
			jyxhStart = Integer.parseInt(pro.getProperty("jyxhStart"));
			
			chinaMobile = pro.getProperty("chinaMobile");
			chinaUnicom = pro.getProperty("chinaUnicom");
			chinaTelecom = pro.getProperty("chinaTelecom");
			
			airPlane_prize_deadline = Integer.parseInt(pro.getProperty("airPlane_prize_deadline"));
			turnTable_prize_deadline = Integer.parseInt(pro.getProperty("turnTable_prize_deadline"));
			happyScratch_prize_deadline = Integer.parseInt(pro.getProperty("happyScratch_prize_deadline"));
			
			TURNTABLE_TDJ = Float.parseFloat(pro.getProperty("TURNTABLE_TDJ"));
			TURNTABLE_YDJ = Float.parseFloat(pro.getProperty("TURNTABLE_YDJ"));
			TURNTABLE_EDJ = Float.parseFloat(pro.getProperty("TURNTABLE_EDJ"));
			TURNTABLE_SDJ = Float.parseFloat(pro.getProperty("TURNTABLE_SDJ"));
			
			HAPPYSCRATCH_J1 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J1"));
			HAPPYSCRATCH_J2 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J2"));
			HAPPYSCRATCH_J3 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J3"));
			HAPPYSCRATCH_J4 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J4"));
			HAPPYSCRATCH_J5 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J5"));
			HAPPYSCRATCH_J6 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J6"));
			HAPPYSCRATCH_J7 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J7"));
			HAPPYSCRATCH_J8 = Float.parseFloat(pro.getProperty("HAPPYSCRATCH_J8"));
			
			subscribe_points = Integer.parseInt(pro.getProperty("subscribe_points"));
			product_img = pro.getProperty("product_img");
			product_url = pro.getProperty("product_url");

			// 农行串码兑换次数限制
			ifUseExchangeLimit = Boolean.valueOf(pro.getProperty("ifUseExchangeLimit"));
			exchangeLimit = Integer.valueOf(pro.getProperty("exchangeLimit"));
			limitBeginDate = pro.getProperty("limitBeginDate");
			limitEndDate = pro.getProperty("limitEndDate");
			if (null != limitEndDate && !limitEndDate.equals("")) {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				calendar.setTime(simpleDateFormat.parse(limitEndDate));
				calendar.add(Calendar.DATE, 1);
				limitEndDate = simpleDateFormat.format(calendar.getTime()); // 实际结束日期
			}
			ifUseExchangeLimitForDay = Boolean.valueOf(pro.getProperty("ifUseExchangeLimitForDay"));
			exchangeLimitForDay = Integer.valueOf(pro.getProperty("exchangeLimitForDay"));

			log.info("读取配置文件成功！");
			System.setProperty("sun.net.client.defaultConnectTimeout", timeout);
			return;
		} catch (UnsupportedEncodingException e) {
			log.error("配置文件编码格式不正确，请用GBK");
		} catch (FileNotFoundException e) {
			log.error("找不到配置文件");
		} catch (IOException e) {
			log.error("读取配置文件时IO错误");
		} catch (URISyntaxException e) {
			log.error("读取配置文件时IO错误");
		} catch (Exception e) {
			log.error("读取配置文件时错误", e);
		}
		return;
	}
	
	public static void main(String[] args) {
		ParamManager.readConfig();
		System.out.println(ParamManager.timeout);
		System.out.println(ParamManager.appid);
		System.out.println(ParamManager.appsecret);
	}
}
