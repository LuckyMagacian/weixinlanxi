package com.lanxi.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanxi.weixin.bean.GamePrizeBean;
import com.lanxi.weixin.bean.GameResultBean;
import com.lanxi.weixin.bean.OrderBean;
import com.lanxi.weixin.bean.UserPrizeBean;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.manager.SendOrderManager;
import com.lanxi.weixin.service.GamePrizeService;
import com.lanxi.weixin.service.GameResultService;
import com.lanxi.weixin.service.OrderService;
import com.lanxi.weixin.service.PrizeService;
import com.lanxi.weixin.service.UserPrizeService;
import com.lanxi.weixin.service.WeixinUserService;
import com.lanxi.weixin.utils.GamePrizeUtil;
import com.lanxi.weixin.utils.GameRandomResult;

@Controller
@RequestMapping("/turnTable")
public class TurnTableController {

	private static Logger log = Logger.getLogger(TurnTableController.class);
	
	@Autowired
	private GameResultService gameResultService;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private GamePrizeService gamePrizeService;
	@Autowired
	private UserPrizeService userPrizeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PrizeService prizeService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
	
	@RequestMapping("/goTurnTable.do")
	public String goTurnTable(Model model,HttpSession session,String openid){
		log.info("跳转到大转盘...");
		if(null == session.getAttribute("opBean")){
			OpenidDetailBean opBean = weixinUserService.getWeixinUserByOpenid(openid);
			session.setAttribute("opBean", opBean);
		}
		OpenidDetailBean opBean = (OpenidDetailBean)session.getAttribute("opBean");
		int number = gameResultService.getTurnTableDayCountByOpenid(opBean.getOpenid());
		model.addAttribute("number", number);
		return "turntable/turntable";
	}
	
	@RequestMapping("/getWinningResult.do")
	public String getWinningResult(HttpServletResponse response,HttpSession session) throws IOException{
		log.info("大转盘抽奖...");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		GameResultBean gameResultBean = null;
		OpenidDetailBean opBean = (OpenidDetailBean)session.getAttribute("opBean");
		try{
			String openid = opBean.getOpenid();
			int points = opBean.getPoints();
			int count = gameResultService.getTurnTableDayCountByOpenid(openid);
			if(count>0){	//每天的免费次数就一次，若已使用，扣10积分玩一次
				if(points>=10){
					opBean.setPoints(points-10);
					session.setAttribute("opBean", opBean);
					weixinUserService.updatePointsByOpenid(opBean);
				}else{
					out.println("{\"openid\":\""+openid+"\",\"result\":\"-1\",\"retCode\":\"9001\",\"retMsg\":\"积分已用完\"}");
					out.flush();
					out.close();
					return null;
				}
			}
			
			int retCode = 0;
			int prizeGrade = 9;
			float a0 = ParamManager.TURNTABLE_TDJ;
			float a1 = ParamManager.TURNTABLE_YDJ;
			float a2 = ParamManager.TURNTABLE_EDJ;
			float a3 = ParamManager.TURNTABLE_SDJ;
			float result = GameRandomResult.getRandomResult(a0, a1, a2, a3);
			System.out.println("result---------"+result);
			//num代表特定的含义 0：谢谢参与 1：再来一次	9：特等奖 8：一等奖 7：二等奖 6：三等奖
			if(result<=a0){	//特等奖
				retCode = 9;
				prizeGrade = 0;
			}else if(result>a0 && result<=(a0+a1)){	//一等奖
				retCode = 8;
				prizeGrade = 1;
			}else if(result>(a0+a1) && result<=(a0+a1+a2)){	//二等奖
				retCode = 7;
				prizeGrade = 2;
			}else if(result>(a0+a1+a2) && result<=(a0+a1+a2+a3)){	//三等奖
				retCode = 6;
				prizeGrade = 3;
			}else{	//谢谢惠顾
				retCode = 0;
			}
			gameResultBean = new GameResultBean();
			gameResultBean.setOpenid(openid);
			gameResultBean.setGameType("TurnTable");
			gameResultBean.setProbability(String.valueOf(result));
			gameResultBean.setResult(String.valueOf(retCode));
			gameResultBean.setCreateTime(sdf.format(new Date()));
			gameResultService.insertGameResult(gameResultBean);
			
			if(retCode>0){
				log.info("插入用户获奖表，用户id："+opBean.getOpenid()+",微信名:"+opBean.getNickname()+",奖品等级:"+prizeGrade);
				//插入用户奖品表，当用户兑换时，发报文取小积分
				Date date = new Date();
				Calendar cal = Calendar.getInstance();//获得有效期
				cal.add(Calendar.DAY_OF_MONTH, ParamManager.turnTable_prize_deadline);
				Date date2 = cal.getTime();
				String deadline = sdfDate.format(date2);
				GamePrizeBean gamePrizeBean = new GamePrizeBean();
				gamePrizeBean.setGameType("TurnTable");
				gamePrizeBean.setPrizeGrade(String.valueOf(prizeGrade));
				gamePrizeBean = gamePrizeService.getGamePrize(gamePrizeBean);
				UserPrizeBean userPrizeBean = new UserPrizeBean();
				userPrizeBean.setOpenid(openid);
				userPrizeBean.setGameType(gamePrizeBean.getGameType());
				userPrizeBean.setSpbh(gamePrizeBean.getSpbh());
				userPrizeBean.setPrizeGrade(gamePrizeBean.getPrizeGrade());
				userPrizeBean.setSpsl(gamePrizeBean.getSpsl());
				userPrizeBean.setPrizeStatus("1");
				userPrizeBean.setCreateDate(sdfDate.format(date));
				userPrizeBean.setCreateTime(sdfTime.format(date));
				userPrizeBean.setDeadline(deadline);
				userPrizeService.insertUserPrize(userPrizeBean);
			}
			
			out.println("{\"openid\":\""+openid+"\",\"result\":\""+retCode+"\",\"retCode\":\"0000\",\"retMsg\":\"抽奖完毕\"}");
			out.flush();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			out.println("{\"openid\":\"\",\"result\":\"0\",\"retCode\":\"9999\",\"retMsg\":\"系统报错\"}");
			out.flush();
		}finally{
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 用户领取奖品
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getPrize.do")
	public String getPrize(HttpServletResponse response,HttpSession session,String phone) throws IOException{
		log.info("大转盘领奖...");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		OrderBean orderBean = null;
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			String openid = openidDetailBean.getOpenid();
			UserPrizeBean userPrizeParam = new UserPrizeBean();
			userPrizeParam.setOpenid(openid);
			userPrizeParam.setGameType("TurnTable");
			UserPrizeBean userPrizeBean = userPrizeService.getUserPrizeToday(userPrizeParam);
			if(null == userPrizeBean){//用户未获奖
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",大转盘用户未获奖");
				out.println("{\"retCode\":\"9001\",\"retMsg\":\"大转盘用户未获奖\"}");
				out.flush();
				return null;
			}
			if("2".equals(userPrizeBean.getPrizeStatus())){//已领取
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",大转盘奖励已领取");
				out.println("{\"retCode\":\"9002\",\"retMsg\":\"大转盘奖励已领取\"}");
				out.flush();
				return null;
			}
			if("3".equals(userPrizeBean.getPrizeStatus())){//已过期
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",大转盘奖励已过期");
				out.println("{\"retCode\":\"9003\",\"retMsg\":\"大转盘奖励已过期\"}");
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
			//更新用户奖品表
			userPrizeBean.setPrizeStatus("2");
			userPrizeBean.setPhone(phone);
			userPrizeBean.setOrderId(orderId);
			userPrizeService.updateUserPrizeStatusByOpenid2(userPrizeBean);
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
	 * 大转盘领奖（游戏列表界面）
	 * @param response
	 * @param session
	 * @param phone
	 * @param prizeGrade
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getPrizeFromGameIndex.do")
	public String getPrizeFromGameIndex(HttpServletResponse response,HttpSession session,String phone,
			String prizeGrade,String createDate,String createTime) throws IOException{
		log.info("大转盘领奖(游戏列表界面)...");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		OrderBean orderBean = null;
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			String openid = openidDetailBean.getOpenid();
			UserPrizeBean userPrizeParam = new UserPrizeBean();
			userPrizeParam.setOpenid(openid);
			userPrizeParam.setGameType("TurnTable");
			userPrizeParam.setPrizeGrade(prizeGrade);
			userPrizeParam.setCreateDate(createDate);
			userPrizeParam.setCreateTime(createTime);
			UserPrizeBean userPrizeBean = userPrizeService.getUserPrizeByPrizeGrade(userPrizeParam);
			if(null == userPrizeBean){//用户未获奖
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",大转盘用户未获奖");
				out.println("{\"retCode\":\"9001\",\"retMsg\":\"大转盘用户未获奖\"}");
				out.flush();
				return null;
			}
			if("2".equals(userPrizeBean.getPrizeStatus())){//已领取
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",大转盘奖励已领取");
				out.println("{\"retCode\":\"9002\",\"retMsg\":\"大转盘奖励已领取\"}");
				out.flush();
				return null;
			}
			if("3".equals(userPrizeBean.getPrizeStatus())){//已过期
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",大转盘奖励已过期");
				out.println("{\"retCode\":\"9003\",\"retMsg\":\"大转盘奖励已过期\"}");
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
			//更新用户奖品表
			userPrizeBean.setPrizeStatus("2");
			userPrizeBean.setPhone(phone);
			userPrizeBean.setOrderId(orderId);
			userPrizeService.updateUserPrizeStatusByOpenid2(userPrizeBean);
			out.println("{\"retCode\":\"0000\",\"retMsg\":\"已兑换\"}");
			out.flush();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			out.println("{\"retCode\":\"9999\",\"retMsg\":\"系统报错，让用户联系客服\"}");
			out.flush();
		}
		return null;
	}
}
