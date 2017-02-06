package com.lanxi.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(TestServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
	}
	
	/**
	 * 测试串码验证功能
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		String code = request.getParameter("code");
		String phone = request.getParameter("phone");
		String timeStamp = request.getParameter("timeStamp");
		String sign = request.getParameter("sign");
		log.info(code+","+phone+","+timeStamp+","+sign);
		
		PrintWriter out = response.getWriter();
		
		out.println("{\"retCode\":\"0000\",\"retMsg\":\"购买成功\"}");
		
		out.flush();
		out.close();
	}

}
