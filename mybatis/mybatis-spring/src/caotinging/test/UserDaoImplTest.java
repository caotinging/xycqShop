package caotinging.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import caotinging.dao.UserDao;
import caotinging.pojo.User;

public class UserDaoImplTest {

	private ApplicationContext context;
	
	@Before
	public void setUp() {
		this.context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@Test
	public void testFindUserById() {
		UserDao userDao = context.getBean(UserDao.class);
		User user = userDao.findUserById(10);
		
		System.out.println(user);
	}

	@Test
	public void testFindUserByName() {
		String name = "张";
		UserDao userDao = context.getBean(UserDao.class);
		
		List<User> list = userDao.findUserByName(name);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUsername("小红");
		user.setAddress("中国重庆");
		user.setSex("2");
		user.setBirthday(new Date());
		
		UserDao userDao = context.getBean(UserDao.class);
		userDao.saveUser(user);
		
	}

}
