package org.meb.play.sample.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class SampleServletContextListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(SampleServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("Servlet context destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("Servlet context initialized");
	}
}
