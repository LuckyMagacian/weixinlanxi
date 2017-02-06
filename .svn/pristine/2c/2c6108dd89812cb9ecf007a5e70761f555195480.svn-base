package com.lanxi.weixin.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.lanxi.weixin.manager.ParamManager;
import com.lanxi.weixin.utils.HttpUtils;
import com.lanxi.weixin.utils.MD5Util;

@Controller
@RequestMapping("/codeCheck")
public class CodeCheckControlled {

	private static Logger log = Logger.getLogger(CodeCheckControlled.class);
	
	@RequestMapping("/toCodeCheck.do")
	public String toCodeCheck(){
		log.info("跳转到串码验证页面");
		
		return "CodeCheck/toCodeCheck";
	}
	
	@RequestMapping("/checkCode.do")
	public String checkCode(Model model,String code,String phone){
		try {
			log.info("串码验证...");
			log.info("输入的code为："+code);
			log.info("输入的phone为："+phone);
			String timeStamp = String.valueOf(System.currentTimeMillis());
			StringBuffer sb = new StringBuffer();
			sb.append(code).append(phone).append(timeStamp).append(ParamManager.cmyzKey);
			String sign = MD5Util.md5LowerCase(sb.toString());
			log.info("签名："+sign);
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", code);
			map.put("phone", phone);
			map.put("timeStamp", timeStamp);
			map.put("sign", sign);
			
			String result = HttpUtils.sendPost(ParamManager.cmyzUrl, map, "utf-8", "60000");
			log.info("返回结果："+result);
			String retCode = JSONObject.parseObject(result).getString("retCode");
			//String retMsg = JSONObject.parseObject(result).getString("retMsg");
			//9002	未找到该串码记录
			//9003	该串码已使用
			if("9002".equals(retCode)){//未找到该串码记录
				model.addAttribute("retMsg", "未找到该兑换码记录");
				return "CodeCheck/fail";
			}else if("9003".equals(retCode)){//串码已使用
				model.addAttribute("retMsg", "兑换码已使用");
				return "CodeCheck/fail";
			}else if("9004".equals(retCode)){//串码已注销
				model.addAttribute("retMsg", "兑换码已注销");
				return "CodeCheck/fail";
			}else if("9005".equals(retCode)){//串码已过期
				model.addAttribute("retMsg", "兑换码已过期");
				return "CodeCheck/fail";
			}else{
				//model.addAttribute("retMsg", retMsg);
				return "CodeCheck/success";
			}
		} catch(Exception e){
			log.error(e.getClass().getName()+":"+e.getMessage(),e);
		}
		return "CodeCheck/success";
	}
	
	@RequestMapping("/getPhoneByCode.do")
	public String getPhoneByCode(HttpServletRequest request,HttpServletResponse response,String code){
		PrintWriter out = null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			log.info("根据串码获取手机号码...");
			log.info("串码："+code);
			String timestamp = String.valueOf(System.currentTimeMillis());
			StringBuffer sb = new StringBuffer();
			sb.append(code).append(timestamp).append(ParamManager.cmyzKey);
			String sign = MD5Util.md5LowerCase(sb.toString());
			
			log.info("签名："+sign);
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", code);
			map.put("timestamp", timestamp);
			map.put("sign", sign);
			
			String result = HttpUtils.sendPost(ParamManager.getSjhmUrl, map, "utf-8", "60000");
			log.info("根据串码查询手机号码返回结果："+result);
			String phone = JSONObject.parseObject(result).getString("phone");
			if(phone.length()!=11){
				log.info("{\"phone\":\"\",\"retMsg\":\"未查到手机号码\"}");
				out.println("{\"phone\":\"\",\"retMsg\":\"未查到手机号码\"}");
			}else{
				log.info("{\"phone\":\""+phone+"\",\"retMsg\":\"查询成功\"}");
				out.println("{\"phone\":\""+phone+"\",\"retMsg\":\"查询成功\"}");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			log.info(e.getClass().getName()+":"+e.getMessage(),e);
			out.println("{\"phone\":\"\",\"retMsg\":\"查询手机号码失败\"}");
			out.flush();
			out.close();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			ParamManager.readConfig();
//			String code = "155845784287";
			String code = "123456789012";
			String timestamp = String.valueOf(System.currentTimeMillis());
			StringBuffer sb = new StringBuffer();
			sb.append(code).append(timestamp).append(ParamManager.cmyzKey);
			String sign = MD5Util.md5LowerCase(sb.toString());
			
			log.info("签名："+sign);
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", code);
			map.put("timestamp", timestamp);
			map.put("sign", sign);
			
			String result = HttpUtils.sendPost(ParamManager.getSjhmUrl, map, "utf-8", "60000");
			log.info("返回结果："+result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
