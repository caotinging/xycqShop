package caotinging.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import caotinging.domain.Customer;
import caotinging.domain.LinkMan;
import caotinging.utils.HibernateUtils;

public class TestDemo {
	@Test
	public void test1() {
		Configuration conf = new Configuration().configure();
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.openSession();
		System.out.println(session);
		session.close();
		factory.close();
	}
	
	@Test
	public void test2() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		customer.setCust_name("欣语长情");
		
		LinkMan linkman = new LinkMan();
		linkman.setLkm_name("caoting");
		
		LinkMan linkman2 = new LinkMan();
		linkman2.setLkm_name("yuanhao");
		
		customer.getLinkManSet().add(linkman);
		customer.getLinkManSet().add(linkman2);
		
		session.save(linkman);
		session.save(linkman2);
		session.save(customer);
		
		transaction.commit();
		session.close();
		HibernateUtils.closeFactory();
	}
}