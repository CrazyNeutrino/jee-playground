package org.meb.play.sample.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class SampleServiceBean {

	private static final Logger log = LoggerFactory.getLogger(SampleServiceBean.class);
	
	@PostConstruct
	public void initialize() {
		log.info("Initialized!");
	}
}
