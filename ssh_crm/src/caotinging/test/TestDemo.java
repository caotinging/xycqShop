package caotinging.test;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import caotinging.dao.CustomerDao;
import caotinging.dao.UserDao;
import caotinging.domain.Customer;
import caotinging.domain.User;
import caotinging.utils.HibernateUtils;
import caotinging.web.action.UserAction;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestDemo {
	@Resource(name="userAction")
	private UserAction userAction;
	@Resource(name="sessionFactory")
	private SessionFactory factory;
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	
	@Test
	public void fun6() {
		List<Customer> list = customerDao.findAll();
		System.out.println(list);
	}
	
	@Test
	public void fun5() {
		User user = userDao.getUserByCode("caotinging");
		System.out.println(user);
	}
	
	@Test
	public void fun4() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User u = new User();
		u.setUser_name("秋冬");
		u.setUser_code("qiudong");
		u.setUser_password("2222");
		
		session.saveOrUpdate(u);
		tx.commit();
		session.close();
		factory.close();
	}
	
	@Test
	public void fun3() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User u = new User();
		u.setUser_name("春夏");
		u.setUser_code("springxia");
		u.setUser_password("1111");
		
		session.saveOrUpdate(u);
		tx.commit();
		session.close();
		factory.close();
	}
	
	@Test
	public void fun2() {
		Session session = HibernateUtils.getNewSession();
		
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setUser_name("曹操");
		u.setUser_code("caocaoao");
		u.setUser_password("5678");
		
		session.saveOrUpdate(u);
		
		tx.commit();
		session.close();
		HibernateUtils.closeFactory();
	}
	
	@Test
	public void fun1() {
		System.out.println(userAction);
	}
}
