package com.lanxi.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.lanxi.weixin.bean.PageBean;
import com.lanxi.weixin.bean.UserPrizeBean;
import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.service.UserPrizeService;
import com.lanxi.weixin.service.WeixinUserService;

@Controller
@RequestMapping("/game")
public class GameController {

	private static Logger log = Logger.getLogger(GameController.class);
	
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private UserPrizeService userPrizeService;
	
	@RequestMapping("/gameIndex.do")
	public String gameIndex(HttpSession session,String openid){
		log.info("跳转到游戏首页...");
		OpenidDetailBean opBean = weixinUserService.getWeixinUserByOpenid(openid);
		session.setAttribute("opBean", opBean);
		return "gameIndex";
	}
	
	/**
	 * 获取用户的奖品列
	 * @param session
	 * @param response
	 * @param pageNum		第几页
	 * @param numPerPage	每页记录数
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getUserPrizeList.do")
	public String getUserPrizeList(HttpSession session,HttpServletResponse response,String pageNum,String numPerPage) throws IOException{
		log.info("获取用户奖品列表...");
		PrintWriter out = response.getWriter();
		response.setContentType("html/text;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		OpenidDetailBean opBean = null;
		try{
			opBean = (OpenidDetailBean) session.getAttribute("opBean");
			PageBean pageBean = new PageBean();
			System.err.println(opBean);
			pageBean.setOpenid(opBean.getOpenid());
			pageBean.setPageNum(Integer.parseInt(pageNum));
			pageBean.setNumPerPage(Integer.parseInt(numPerPage));
			pageBean.setStart((pageBean.getPageNum()-1)*pageBean.getNumPerPage());
			//List<UserPrizeBean> userPrizeList = userPrizeService.getUserPrizeListByOpenid(opBean.getOpenid());
			List<UserPrizeBean> userPrizeList = userPrizeService.getUserPrizeListByOpenid2(pageBean);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(userPrizeList);
			log.info("获取用户奖品列表json:"+jsonStr);
			out.println(jsonStr);
			out.flush();
			out.close();
		}catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return null;
	}
}
