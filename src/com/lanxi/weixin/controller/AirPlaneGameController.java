package com.lanxi.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.lanxi.weixin.bean.DateTimeBean;
import com.lanxi.weixin.bean.GameScoreBean;
import com.lanxi.weixin.bean.OrderBean;
import com.lanxi.weixin.bean.UserPrizeBean;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.manager.SendOrderManager;
import com.lanxi.weixin.service.GameScoreService;
import com.lanxi.weixin.service.OrderService;
import com.lanxi.weixin.service.PrizeService;
import com.lanxi.weixin.service.UserPrizeService;
import com.lanxi.weixin.service.WeixinUserService;
import com.lanxi.weixin.utils.DateTimeUtil;
import com.lanxi.weixin.utils.GamePrizeUtil;

@Controller
@RequestMapping("/planeGame")
public class AirPlaneGameController {

	private static Logger log = Logger.getLogger(AirPlaneGameController.class);

	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private GameScoreService gameScoreService;
	@Autowired
	private UserPrizeService userPrizeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PrizeService prizeService;
	
	private SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHHmmss");
	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
	
	/**
	 * 跳转到飞机大战首页
	 * @param openid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/toPlaneGameIndex.do")
	public String toPlaneGameIndex(String openid,Model model,HttpSession session) {
		log.info("跳转到飞机大战index页面");
		if(null == session.getAttribute("opBean")){
			OpenidDetailBean opBean = weixinUserService.getWeixinUserByOpenid(openid);
			session.setAttribute("opBean", opBean);
		}
		DateTimeBean dateTimeBean = new DateTimeBean();
		dateTimeBean.setStartTime(DateTimeUtil.getThisMonday());
		dateTimeBean.setEndTime(DateTimeUtil.getThisSunday());
		log.info("这周一日期："+DateTimeUtil.getThisMonday()+"，这周日日期："+DateTimeUtil.getThisSunday());
		List<GameScoreBean> rankingList = gameScoreService.getRankingList(dateTimeBean);
		model.addAttribute("rankingList", rankingList);
		return "airplaneGame/index";
	}
	
	/**
	 * 跳转到飞机大战玩游戏页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/toPlaneGamePlay.do")
	public String toPlaneGamePlay(Model model,HttpSession session){
		log.info("跳转到飞机大战play页面");
		String openid = ((OpenidDetailBean)session.getAttribute("opBean")).getOpenid();
		String maxScore = gameScoreService.getMaxScore(openid);
		model.addAttribute("maxScore", maxScore);
		return "airplaneGame/play";
	}
	
	/**
	 * ajax提交飞机大战游戏分数
	 * @param openid		微信号
	 * @param nickname		微信名
	 * @param score			飞机大战游戏分数
	 * @param response
	 * @return
	 */
	@RequestMapping("/sendGameScore.do")
	public String sendGameScore(String openid,String nickname,String score,HttpServletResponse response){
		log.info("发送飞机大战游戏分数...");
		log.info("微信编号："+openid+"，微信名："+nickname+"，飞机大战分数："+score);
		try {
			PrintWriter out = response.getWriter();
			GameScoreBean gameScoreBean = new GameScoreBean();
			gameScoreBean.setOpenid(openid);
			gameScoreBean.setNickname(nickname);
			gameScoreBean.setGameType("AirPlaneGame");
			gameScoreBean.setGameScore(Integer.parseInt(score));
			gameScoreBean.setCreateTime(sdf.format(new Date()));
			gameScoreService.insertGameScore(gameScoreBean);
			out.println("{\"retCode\":\"0000\",\"retMsg\":\"success\"}");
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 获取排行榜(本周)
	 * @param response
	 * @return
	 */
	@RequestMapping("/getRankingList.do")
	public String getRankingList(HttpServletResponse response){
		log.info("获取飞机大战排行榜...");
		PrintWriter out = null;
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try{
			out = response.getWriter();
			DateTimeBean dateTimeBean = new DateTimeBean();
			dateTimeBean.setStartTime(DateTimeUtil.getThisMonday());
			dateTimeBean.setEndTime(DateTimeUtil.getThisSunday());
			log.info("这周一日期："+DateTimeUtil.getThisMonday()+"，这周日日期："+DateTimeUtil.getThisSunday());
			List<GameScoreBean> rankingList = gameScoreService.getRankingList(dateTimeBean);
			String jsonString = new Gson().toJson(rankingList);
			log.info(jsonString);
			out.println(jsonString);
			out.flush();
			out.close();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * 用户领取奖品
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getPrize.do")
	public String getPrize(HttpServletResponse response,HttpSession session,String phone,String idCard) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		OrderBean orderBean = null;
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			String openid = openidDetailBean.getOpenid();
			int points = openidDetailBean.getPoints();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -7);
			Date date = cal.getTime();
			String dateString = sdfDate.format(date);
			openidDetailBean.setDateTime(dateString);
			UserPrizeBean userPrizeBean = userPrizeService.getUserPrizeByOpenidAndDate(openidDetailBean);
			if(null == userPrizeBean){//上周排行榜中没有该用户的记录
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",飞机大战上周排行榜中没有该用户的记录");
				out.println("{\"retCode\":\"9001\",\"retMsg\":\"飞机大战上周排行榜中没有该用户的记录\"}");
				out.flush();
				return null;
			}
			if("2".equals(userPrizeBean.getPrizeStatus())){//已领取
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",飞机大战奖励已领取");
				out.println("{\"retCode\":\"9002\",\"retMsg\":\"飞机大战奖励已领取\"}");
				out.flush();
				return null;
			}
			if("3".equals(userPrizeBean.getPrizeStatus())){//已过期
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",飞机大战奖励已过期");
				out.println("{\"retCode\":\"9003\",\"retMsg\":\"飞机大战奖励已过期\"}");
				out.flush();
				return null;
			}
			Date date2 = new Date();
			String orderId = String.valueOf(System.currentTimeMillis());
			orderBean = new OrderBean();
			orderBean.setOrderId(orderId);
			orderBean.setOrganizeId(ParamManager.src);
			orderBean.setPhone(phone);
			//orderBean.setDealId(String.valueOf(jyxh));
			String spbh = GamePrizeUtil.getSpbhByPhone(phone, userPrizeBean.getSpbh());
			orderBean.setSpbh(spbh);
			orderBean.setSpsl(GamePrizeUtil.getSpslByPhone(phone, userPrizeBean.getSpsl()));
			orderBean.setOrderStatus("0");
			orderBean.setSendNum("0");
			orderBean.setMaxNum("3");
			orderBean.setOrderDate(sdfDate.format(date2));
			orderBean.setOrderTime(sdfTime.format(date2));
			orderService.insertOrder(orderBean);
			String splb = prizeService.getSplbBySpbh(spbh);
			orderBean = SendOrderManager.sendOrderToXjf(orderBean,splb);
			
			orderService.updateOrder(orderBean);
			//更新用户表中的积分
			openidDetailBean.setPoints(points+Integer.parseInt(userPrizeBean.getPoints()));
			weixinUserService.updatePointsByOpenid(openidDetailBean);
			session.setAttribute("opBean", openidDetailBean);
			//更新用户奖品表
			userPrizeBean.setPrizeStatus("2");
			userPrizeBean.setPointsStatus("2");
			userPrizeBean.setPhone(phone);
			userPrizeBean.setOrderId(orderId);
			userPrizeService.updateUserPrizeStatusByOpenid(userPrizeBean);
			out.println("{\"retCode\":\"0000\",\"retMsg\":\"已兑换\"}");
			out.flush();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			out.println("{\"retCode\":\"9999\",\"retMsg\":\"系统报错，让用户联系客服\"}");
			out.flush();
		}
		return null;
	}
	/**
	 * 查看领奖状态
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/checkUserPrize.do")
	public String checkUserPrize(HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			String openid = openidDetailBean.getOpenid();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -7);
			Date date = cal.getTime();
			String dateString = sdfDate.format(date);
			openidDetailBean.setDateTime(dateString);
			UserPrizeBean userPrizeBean = userPrizeService.getUserPrizeByOpenidAndDate(openidDetailBean);
			if(null == userPrizeBean){//上周排行榜中没有该用户的记录
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",飞机大战上周排行榜中没有该用户的记录");
				out.println("{\"retCode\":\"9001\",\"retMsg\":\"飞机大战上周排行榜中没有该用户的记录\"}");
				out.flush();
				return null;
			}
			if("2".equals(userPrizeBean.getPrizeStatus())){//已领取
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",飞机大战奖励已领取");
				out.println("{\"retCode\":\"9002\",\"retMsg\":\"飞机大战奖励已领取\"}");
				out.flush();
				return null;
			}
			if("3".equals(userPrizeBean.getPrizeStatus())){//已过期
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",飞机大战奖励已过期");
				out.println("{\"retCode\":\"9003\",\"retMsg\":\"飞机大战奖励已过期\"}");
				out.flush();
				return null;
			}
			log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",飞机大战奖励未领取");
			out.println("{\"retCode\":\"0000\",\"retMsg\":\""+userPrizeBean.getPrizeGrade()+"\"}");
			out.flush();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date date = cal.getTime();
		System.out.println(sdfDate.format(date));
	}
}
