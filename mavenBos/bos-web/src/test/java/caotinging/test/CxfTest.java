package caotinging.test;

import java.util.List;

//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import caotinging.utils.crm.Customer;
import caotinging.utils.crm.ICustomerService;

public class CxfTest {

	@Autowired
	private ICustomerService customerServiceProxy;
	
	//@Test
	public void fun1() {
		List<Customer> list = customerServiceProxy.findAll();
		for (Customer customer : list) {
			
			System.out.println(customer);
		}
	}
}
