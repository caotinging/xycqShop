package caotinging.test.springJdbc;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:caotinging/test/springJdbc/applicationContext2.xml")
public class TestSpringJdbc {
	
	@Resource(name="userDao")
	private UserDao dao;
	
	@Test
	public void fun1() {
		User u = new User();
		u.setName("mary");
		dao.save(u);
	}
	
	@Test
	public void fun2() {
		User user = dao.findById(1);
		System.out.println(user);
	}
	
	@Test
	public void fun3() {
		List<User> user = dao.findAll();
		System.out.println(user);
	}
}
