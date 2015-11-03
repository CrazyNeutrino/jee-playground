package test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.meb.play.sample.ejb.SampleServiceBean;

public class SampleServiceBeanTest {

	private EJBContainer container;

	@Before
	public void setUp() throws Exception {
		container = EJBContainer.createEJBContainer();
	}

	@After
	public void cleanUp() throws Exception {
		container.close();
	}

	@Test
	public void testTimeout() {
		try {
			Object object = container.getContext().lookup("java:global/sample-ejb/SampleServiceBean");
			assertTrue("Object not instance of " + SampleServiceBean.class.getName(),
					object instanceof SampleServiceBean);

			Thread.sleep(TimeUnit.SECONDS.toMillis(5));

			SampleServiceBean bean = (SampleServiceBean) object;
			assertTrue("Timeout counter too low", bean.getCounter() >= 5);
			assertTrue("Timeout counter too high", bean.getCounter() <= 6);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
