package test.aat.itskv.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aat.itskv.redis.service.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class RedisTest {

	@Autowired
	private RedisService redisService;

	@Test
	public void testRedisSpring() {
		redisService.addKV("2", "223");
		System.out.println(redisService.getKV("2"));
	}

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		RedisService redisService = context.getBean(RedisService.class);
		// start
		redisService.addKV("1", "!23");
		System.out.println(redisService.getKV("1"));

		// end
		context.close();
	}

}
