package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import caotinging.utils.BeanFactory;

public class Test1 {

	@Test
	public void test1() {
		Object obj = BeanFactory.getBean("adminDao");
		System.out.println(obj);
	}
	
	@Test
	public void test2() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.println(session);
		session.close();
		sessionFactory.close();
	}
}
