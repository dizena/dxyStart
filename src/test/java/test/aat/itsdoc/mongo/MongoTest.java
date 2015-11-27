package test.aat.itsdoc.mongo;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aat.itsdoc.jsons.bean.Book;
import com.aat.itsdoc.mongo.service.MongoService;
import com.aat.utils.Atools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class MongoTest {

	
	private static ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		MongoService commService=context.getBean(MongoService.class);
		Book b=new Book();
		b.setId(Atools.getOneKeyS());
		b.setName("瓦尔登湖");
		b.setPages(360);
		b.setPrice(40.50);
		//commService.save(b);
		
		List<Book> ls = commService.findAll(Book.class);
		System.out.println(ls);
		context.close();
	}
	
	/**
	@Autowired
	private MongoService commService;
	@Test
	public void test01(){
		WebRule wr=new WebRule();
		wr.setId(Atools.getOneKeyS());
		wr.setWebName("测试3");
		wr.setWebRule("regex3");
		wr.setWebUrl("www.test3.com");
		commService.save(wr);
		
		List<WebRule> ls = commService.findAll(WebRule.class);
		
		System.out.println(ls);
	}
	 */
	
	/*
	@Resource
    private WebRuleDao webRuleDao;

	
	@Test
	public void test01(){
		WebRule wr=new WebRule();
		wr.setId("2");
		wr.setWebName("测试3");
		wr.setWebRule("regex3");
		wr.setWebUrl("www.test3.com");
		webRuleDao.addOne(wr);
		System.out.println("1 "+wr);
		WebRule res = webRuleDao.queryByID("2");
		System.out.println("2 "+res);
		
		System.out.println(webRuleDao.queryAll());
		webRuleDao.test();
		System.out.println(webRuleDao.queryAll());
	}
	
	*/

}
