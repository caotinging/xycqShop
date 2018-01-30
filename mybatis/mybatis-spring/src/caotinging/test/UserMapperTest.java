package caotinging.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import caotinging.mapper.UserMapper;
import caotinging.pojo.User;

public class UserMapperTest {

private ApplicationContext context;
	
	@Before
	public void setUp() {
		this.context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@Test
	public void testFindUserById() {
		UserMapper userMapper = context.getBean(UserMapper.class);
		User user = userMapper.findUserById(10);
		
		System.out.println(user);
	}

	@Test
	public void testFindUserByName() {
		String name = "张";
		UserMapper userMapper = context.getBean(UserMapper.class);
		
		List<User> list = userMapper.findUserByName(name);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUsername("小花");
		user.setAddress("中国重庆");
		user.setSex("2");
		user.setBirthday(new Date());
		
		UserMapper userMapper = context.getBean(UserMapper.class);
		userMapper.saveUser(user);
		
	}

}
