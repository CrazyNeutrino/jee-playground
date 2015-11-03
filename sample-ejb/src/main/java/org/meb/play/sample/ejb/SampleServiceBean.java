package org.meb.play.sample.ejb;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class SampleServiceBean {

	private static final Logger log = LoggerFactory.getLogger(SampleServiceBean.class);

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void initialize() {
		TimerConfig config;

		config = new TimerConfig("Config0", false);
		timerService.createIntervalTimer(Date.from(Instant.now().plusSeconds(5)), 5000, config);
		config = new TimerConfig("Config1", false);
		timerService.createIntervalTimer(Date.from(Instant.now().plusSeconds(5)), 10000, config);
	}

	@Timeout
	public void timeout(Timer timer) {
		Serializable info = null;
		if ("Config0".equals(timer.getInfo())) {
			info = timer.getInfo();
		} else if ("Config1".equals(timer.getInfo())) {
			info = timer.getInfo();
		}
		if (info != null) {
			log.info("Timeout: {}, at: {}", info, DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
		}
	}
}
