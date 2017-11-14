package caotinging.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.opensymphony.xwork2.ActionSupport;

import caotinging.domain.User;
import caotinging.utils.HibernateUtils;

public class TestAction extends ActionSupport {

	@Test
	public void fun1() {
		User user = new User();
		user.setUser_code("dada");
		user.setUser_name("曹婷");
		user.setUser_password("5678");
		
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(user);
		
		transaction.commit();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return
	 */
	public String execute() {
		System.out.println("测试Struts2搭建");
		return SUCCESS;
	}
}