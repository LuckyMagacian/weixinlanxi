package com.lanxi.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {

	private static Logger log = Logger.getLogger(MessageUtil.class);
	
	/**
	 * 解析xml报文，保存到map中(不在日志中打印接收到的原始xml，只打印解析后的)
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> parseXml(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String, String>();
		InputStream is = null;
		try{
			is = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(is);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			for(Element e : elementList){
				map.put(e.getName(), e.getText());
				log.info(e.getName() +","+ e.getText());
			}
		}catch(Exception e){
			log.error(e.getClass().getName()+"解析xml报错:"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+"解析xml报错:"+e.getMessage(),e);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					log.error(e.getClass().getName()+"关闭流报错:"+e.getMessage(),e);
				}
			}
		}
		return map;
	}
	
	/**
	 * 解析xml报文，放入map中（在日志中打印接收到的原始xml和解析后得到的信息）
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml2(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
			String line = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			String xml = sb.toString().trim();
			log.info("接收xml报文："+xml);
			Document document = XmlParser.getDocument(xml);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			for(Element e : elementList){
				log.info(e.getName() + "," + e.getText());
				map.put(e.getName(), e.getText());
			}
		}catch(Exception e){
			log.error(e.getClass().getName()+"解析xml报错:"+e.getMessage(),e);
			throw new RuntimeException(e.getClass().getName()+"解析xml报错:"+e.getMessage(),e);
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					log.error(e.getClass().getName()+"关闭流报错:"+e.getMessage(),e);
				}
			}
		}
		return map;
	}
}
