package com.lanxi.weixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lanxi.weixin.bean.oauth.OpenidDetailBean;
import com.lanxi.weixin.bean.oauth.WebAccessTokeanBean;
import com.lanxi.weixin.manager.OAuthManager;
import com.lanxi.weixin.manager.OAuthManager2;

public class OAuthServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(OAuthServlet.class);
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String code = request.getParameter("code");
		
		WebAccessTokeanBean wtBean = null;
		OpenidDetailBean odBean = null;
		log.info("获取到的code:"+code);
		if(null!=code && !"".equals(code)){
			wtBean = OAuthManager2.getWebAccessTokean(code);
			String access_token = wtBean.getAccess_token();
			String openid = wtBean.getOpenid();
			log.info("access_token:"+access_token+",openid:"+openid);
			
			odBean = OAuthManager.getOpenidDetail(access_token, openid);
			request.setAttribute("odBean", odBean);
		}
		request.getRequestDispatcher("/planeGame/toPlaneGameIndex.do").forward(request, response);
	}

}
