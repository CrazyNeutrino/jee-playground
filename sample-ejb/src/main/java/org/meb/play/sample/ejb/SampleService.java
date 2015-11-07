package org.meb.play.sample.ejb;

import javax.ejb.Remote;

@Remote
public interface SampleService {

	int getCounter();
}