package com.lanxi.weixin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionTimeoutInterceptor implements HandlerInterceptor {

	private static Logger log = Logger.getLogger(SessionTimeoutInterceptor.class);
	
	public String[] allowUrls;
	
	public void setAllowUrls(String[] allowUrls){
		this.allowUrls = allowUrls;
	}
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
//		StringBuffer requestURL = request.getRequestURL();
		log.info(requestUrl);
//		log.info(requestURL.toString());
//		String ip = StringUtil.getIpAddr(request);
//		log.info(ip);
//		String lastpage = request.getHeader("Referer");//获取上一个页面
//		log.info(lastpage);
		if(null != allowUrls && allowUrls.length>1){
			for(String url : allowUrls){
				if(requestUrl.contains(url)){
					return true;
				}
			}
		}
		return true;// TODO 应该默认不通过才对
	}

}
