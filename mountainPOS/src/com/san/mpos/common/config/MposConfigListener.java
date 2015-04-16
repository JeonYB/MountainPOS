package com.san.mpos.common.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MposConfigListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent contextEvent) {

		MposConfigurer.initConfiguration(contextEvent.getServletContext());
		
	}
	
	public void contextDestroyed(ServletContextEvent contextEvent) {
		MposConfigurer.destroyConfiguration(contextEvent.getServletContext());
	}

}