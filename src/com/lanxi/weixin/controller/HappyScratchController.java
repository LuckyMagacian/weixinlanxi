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
@RequestMapping("/happyScratch")
public class HappyScratchController {

	private static Logger log = Logger.getLogger(HappyScratchController.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
	
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private GameResultService gameResultService;
	@Autowired
	private GamePrizeService gamePrizeService;
	@Autowired
	private UserPrizeService userPrizeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PrizeService prizeService;
	
	@RequestMapping("/getHappyScratch.do")
	public String getHappyScratch(Model model,HttpSession session,String openid){
		log.info("跳转到刮刮乐页面...");
		if(null == session.getAttribute("opBean")){
			OpenidDetailBean opBean = weixinUserService.getWeixinUserByOpenid(openid);
			session.setAttribute("opBean", opBean);
		}
		OpenidDetailBean opBean = (OpenidDetailBean) session.getAttribute("opBean");
		GameResultBean gameResultBean = new GameResultBean();
		gameResultBean.setOpenid(opBean.getOpenid());
		gameResultBean.setGameType("HappyScratch");
		int number = gameResultService.getGameDayCount(gameResultBean);
		model.addAttribute("number", number);
		return "happyScratch/happyScratch";
	}
	
	@RequestMapping("/getWinningResult.do")
	public String getWinningResult(HttpServletResponse response,HttpSession session) throws IOException{
		log.info("刮刮乐刮奖...");
		response.setContentType("text/html;chanset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		GameResultBean gameResultBean = null;
		OpenidDetailBean opBean = (OpenidDetailBean) session.getAttribute("opBean");
		try{
			String openid = opBean.getOpenid();
			int points = opBean.getPoints();
			gameResultBean = new GameResultBean();
			gameResultBean.setOpenid(openid);
			gameResultBean.setGameType("HappyScratch");
			int count = gameResultService.getGameDayCount(gameResultBean);
			if(count>0){
				if(points>=0){
					opBean.setPoints(points-10);
					// TODO 分数可以为负数?
					session.setAttribute("opBean", opBean);
					weixinUserService.updatePointsByOpenid(opBean);
				}else{
					out.println("{\"openid\":\""+openid+"\",\"result\":\"-1\",\"retCode\":\"9001\",\"retMsg\":\"积分已用完\"}");
					out.flush();
					out.close();
					return null;
				}
			}
			
			float a1 = ParamManager.HAPPYSCRATCH_J1;
			float a2 = ParamManager.HAPPYSCRATCH_J2;
			float a3 = ParamManager.HAPPYSCRATCH_J3;
			float a4 = ParamManager.HAPPYSCRATCH_J4;
			float a5 = ParamManager.HAPPYSCRATCH_J5;
			float a6 = ParamManager.HAPPYSCRATCH_J6;
			float a7 = ParamManager.HAPPYSCRATCH_J7;
			float a8 = ParamManager.HAPPYSCRATCH_J8;
			float result = GameRandomResult.getRandomResult();
			int retCode = 0;
			int prizeGrade = 0;
			if(result<=a1){	//奖品1
				retCode = 8;
				prizeGrade = 1;
				// TODO 可以简化为直接小于(a1+a2)甚至直接小于a2
			}else if(result>a1 && result<=(a1+a2)){	//奖品2
				retCode = 7;
				prizeGrade = 2;
			}else if(result>(a1+a2) && result<=(a1+a2+a3)){	//奖品3
				retCode = 6;
				prizeGrade = 3;
			}else if(result>(a1+a2+a3) && result<=(a1+a2+a3+a4)){	//奖品4
				retCode = 5;
				prizeGrade = 4;
			}else if(result>(a1+a2+a3+a4) && result<=(a1+a2+a3+a4+a5)){	//奖品5
				retCode = 4;
				prizeGrade = 5;
			}else if(result>(a1+a2+a3+a4+a5) && result<=(a1+a2+a3+a4+a5+a6)){//奖品6
				retCode = 3;
				prizeGrade = 6;
			}else if(result>(a1+a2+a3+a4+a5+a6) && result<=(a1+a2+a3+a4+a5+a6+a7)){//奖品7
				retCode = 2;
				prizeGrade = 7;
			}else if(result>(a1+a2+a3+a4+a5+a6+a7) && result<=(a1+a2+a3+a4+a5+a6+a7+a8)){//奖品8
				retCode = 1;
				prizeGrade = 8;
			}else{	//谢谢惠顾
				retCode = 0;
			}
			
			gameResultBean = new GameResultBean();
			gameResultBean.setOpenid(openid);
			gameResultBean.setGameType("HappyScratch");
			gameResultBean.setProbability(String.valueOf(result));
			gameResultBean.setResult(String.valueOf(retCode));
			gameResultBean.setCreateTime(sdf.format(new Date()));
			gameResultService.insertGameResult(gameResultBean);
			if(retCode>0){
				log.info("插入用户获奖表，用户id："+opBean.getOpenid()+",微信名:"+opBean.getNickname()+",奖品等级:"+prizeGrade);
				Date date = new Date();
				Calendar cal = Calendar.getInstance();//获得有效期
				cal.add(Calendar.DAY_OF_MONTH, ParamManager.happyScratch_prize_deadline);
				Date date2 = cal.getTime();
				String deadline = sdfDate.format(date2);
				GamePrizeBean gamePrizeBean = new GamePrizeBean();
				gamePrizeBean.setGameType("HappyScratch");
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
				userPrizeBean.setPointsStatus("1");
				userPrizeBean.setPoints(gamePrizeBean.getPoints());
				userPrizeService.insertUserPrize(userPrizeBean);
			}
			out.println("{\"openid\":\""+openid+"\",\"result\":\""+retCode+"\",\"retCode\":\"0000\",\"retMsg\":\"刮奖完毕\"}");
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
	// TODO 为什么不把领奖独立封装..这样不用每种游戏都写,只要留下参数就好O.O
	@RequestMapping("/getPrize.do")
	public String getPrize(HttpServletResponse response,HttpSession session,String phone) throws IOException{
		log.info("刮刮乐领奖...");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		OrderBean orderBean = null;
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			String openid = openidDetailBean.getOpenid();
			UserPrizeBean userPrizeParam = new UserPrizeBean();
			userPrizeParam.setOpenid(openid);
			userPrizeParam.setGameType("HappyScratch");
			UserPrizeBean userPrizeBean = userPrizeService.getUserPrizeToday(userPrizeParam);
			if(null == userPrizeBean){//用户未获奖
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",刮刮乐用户未获奖");
				out.println("{\"retCode\":\"9001\",\"retMsg\":\"刮刮乐用户未获奖\"}");
				out.flush();
				return null;
			}
			if("2".equals(userPrizeBean.getPrizeStatus())){//已领取
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",刮刮乐奖励已领取");
				out.println("{\"retCode\":\"9002\",\"retMsg\":\"刮刮乐奖励已领取\"}");
				out.flush();
				return null;
			}
			if("3".equals(userPrizeBean.getPrizeStatus())){//已过期
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",刮刮乐奖励已过期");
				out.println("{\"retCode\":\"9003\",\"retMsg\":\"刮刮乐奖励已过期\"}");
				out.flush();
				return null;
			}
			
			if(null != userPrizeBean.getPoints() && !"".equals(userPrizeBean.getPoints())){
				log.info("刮刮乐积分中奖处理...");
				int points = openidDetailBean.getPoints();
				openidDetailBean.setPoints(points+Integer.parseInt(userPrizeBean.getPoints())*Integer.parseInt(userPrizeBean.getSpsl()));
				session.setAttribute("opBean", openidDetailBean);
				weixinUserService.updatePointsByOpenid(openidDetailBean);
				userPrizeBean.setPointsStatus("2");
			}
			
			if(null != userPrizeBean.getSpbh() && !"".equals(userPrizeBean.getSpbh())){
				log.info("刮刮乐商品中奖处理...");
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
				userPrizeBean.setPhone(phone);
				userPrizeBean.setOrderId(orderId);
			}
			userPrizeBean.setPrizeStatus("2");
			userPrizeService.updateUserPrizeStatusByOpenid2(userPrizeBean);
			
			out.println("{\"retCode\":\"0000\",\"retMsg\":\"刮刮乐已兑换\"}");
			out.flush();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			out.println("{\"retCode\":\"9999\",\"retMsg\":\"系统报错，让用户联系客服(刮刮乐)\"}");
			out.flush();
		}
		return null;
	}
	
	@RequestMapping("/getPrizeFromGameIndex.do")
	public String getPrizeFromGameIndex(HttpServletResponse response,HttpSession session,String phone,
			String prizeGrade,String createDate,String createTime) throws IOException{
		log.info("刮刮乐领奖(游戏列表界面)...");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		OrderBean orderBean = null;
		try{
			OpenidDetailBean openidDetailBean = (OpenidDetailBean)session.getAttribute("opBean");
			String openid = openidDetailBean.getOpenid();
			UserPrizeBean userPrizeParam = new UserPrizeBean();
			userPrizeParam.setOpenid(openid);
			userPrizeParam.setGameType("HappyScratch");
			userPrizeParam.setPrizeGrade(prizeGrade);
			userPrizeParam.setCreateDate(createDate);
			userPrizeParam.setCreateTime(createTime);
			UserPrizeBean userPrizeBean = userPrizeService.getUserPrizeByPrizeGrade(userPrizeParam);
			if(null == userPrizeBean){//用户未获奖
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",刮刮乐用户未获奖");
				out.println("{\"retCode\":\"9001\",\"retMsg\":\"刮刮乐用户未获奖\"}");
				out.flush();
				return null;
			}
			if("2".equals(userPrizeBean.getPrizeStatus())){//已领取
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",刮刮乐奖励已领取");
				out.println("{\"retCode\":\"9002\",\"retMsg\":\"刮刮乐奖励已领取\"}");
				out.flush();
				return null;
			}
			if("3".equals(userPrizeBean.getPrizeStatus())){//已过期
				log.info("微信id:"+openid+",微信名："+openidDetailBean.getNickname()+",刮刮乐奖励已过期");
				out.println("{\"retCode\":\"9003\",\"retMsg\":\"刮刮乐奖励已过期\"}");
				out.flush();
				return null;
			}
			
			if(null != userPrizeBean.getPoints() && !"".equals(userPrizeBean.getPoints())){
				log.info("刮刮乐积分中奖处理...");
				int points = openidDetailBean.getPoints();
				openidDetailBean.setPoints(points+Integer.parseInt(userPrizeBean.getPoints())*Integer.parseInt(userPrizeBean.getSpsl()));
				session.setAttribute("opBean", openidDetailBean);
				weixinUserService.updatePointsByOpenid(openidDetailBean);
				userPrizeBean.setPointsStatus("2");
			}
			
			if(null != userPrizeBean.getSpbh() && !"".equals(userPrizeBean.getSpbh())){
				log.info("刮刮乐商品中奖处理...");
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
				userPrizeBean.setPhone(phone);
				userPrizeBean.setOrderId(orderId);
			}
			userPrizeBean.setPrizeStatus("2");
			userPrizeService.updateUserPrizeStatusByOpenid2(userPrizeBean);
			out.println("{\"retCode\":\"0000\",\"retMsg\":\"刮刮乐已兑换\"}");
			out.flush();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
			out.println("{\"retCode\":\"9999\",\"retMsg\":\"系统报错，让用户联系客服（刮刮乐）\"}");
			out.flush();
		}
		return null;
	}
}
