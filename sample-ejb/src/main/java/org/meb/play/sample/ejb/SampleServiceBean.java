package org.meb.play.sample.ejb;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class SampleServiceBean implements SampleService, Serializable {

	private static final long serialVersionUID = -6364352446462385276L;

	private static final Logger log = LoggerFactory.getLogger(SampleServiceBean.class);

	@Resource
	private TimerService timerService;

	private List<Timer> timers;

	private int counter;

	@PostConstruct
	public void postConstruct() {
		TimerConfig config;

		timers = new ArrayList<>();
		config = new TimerConfig("Config0", false);
		timers.add(timerService.createIntervalTimer(Date.from(Instant.now()), 5000, config));
		config = new TimerConfig("Config1", false);
		timers.add(timerService.createIntervalTimer(Date.from(Instant.now()), 10000, config));
	}

	@PreDestroy
	public void preDestroy() {
		for (Timer timer : timers) {
			timer.cancel();
		}
		timers = null;
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
			counter++;
			log.info("Timeout: {}, at: {}", info,
					DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
		}
	}

	@Override
	public int getCounter() {
		log.info("counter: " + counter);
		return counter;
	}
}
