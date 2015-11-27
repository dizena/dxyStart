package test.aat.task.job;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestTask {
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) throws Throwable {
		context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		// start
		
		// end
		Thread.sleep(100000);
		context.close();
	}
}
