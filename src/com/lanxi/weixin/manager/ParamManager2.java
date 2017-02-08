package com.lanxi.weixin.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ParamManager2 extends ParamManager {
 	public static final Properties properties;
 	static{
 		log.info("加载配置文件~");
 		properties=new Properties();
 		try {
 			String s = Thread.currentThread().getContextClassLoader()
 					.getResource("config.properties").toURI().getPath();
 			log.info("配置文件路径:"+s);
 			properties.load(new InputStreamReader(new FileInputStream(s), "utf-8"));
 			log.info("配置文件加载完成!");
		} catch (Exception e) {
			log.error("加载配置文件异常!");
			throw new RuntimeException("加载配置文件异常!",e);
		}
 	}
 	public static String getAccessTokenUrl(){
 		return properties.getProperty("accessTokenUrl");
 	}
 	public static String getWebAccessTokenUrl(){
 		return properties.getProperty("webAccessTokenUtl");
 	}
}
