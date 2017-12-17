package caotinging.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import caotinging.dao.CustomerDao;
import caotinging.entity.Customer;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerDaoImplTest {

	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void test() {
		Customer customer = customerDao.getCustById(1L);
		System.out.println(customer.getCustName());
	}

}
