package jammazwan.xbs;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XbsTest extends CamelSpringTestSupport {

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

	@Test
	public void testXbs() throws Exception {
		MyBean myBean = new MyBean();
		myBean.setFoo("foo, baby");
		String reply = template.requestBody("direct:xbs", myBean, String.class);
		assertEquals("!!jammazwan.xbs.MyBean {foo: 'foo, baby'}", reply.trim());
		Thread.sleep(2000); //allows file your.yml to run
	}

}