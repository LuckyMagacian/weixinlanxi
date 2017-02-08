package com.lanxi.weixin.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import com.lanxi.weixin.manager.ParamManager;

public class Log4jListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent event) {
		String webAppRootKey = event.getServletContext().getInitParameter(
				"webAppRootKey");
		if (webAppRootKey != null && webAppRootKey.trim().length() > 0) {
			System.setProperty(webAppRootKey, event.getServletContext()
					.getRealPath("/"));
		}
		String log4jConfigLocation = event.getServletContext()
				.getInitParameter("log4jConfigLocation");
		if (log4jConfigLocation != null
				&& log4jConfigLocation.trim().length() > 0) {
			try {
				PropertyConfigurator.configure(event.getServletContext()
						.getRealPath(log4jConfigLocation));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ParamManager.readConfig();
	}

}
