package caotinging.service.impl;

import java.util.List;

import caotinging.dao.CustomerDao;
import caotinging.dao.impl.CustomerDaoImpl;
import caotinging.domain.Customer;
import caotinging.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao dao = new CustomerDaoImpl();

	public void addCustomer(Customer customer) {
		this.dao.addCustomer(customer);
	}

	@Override
	public List<Customer> findAllCustomer() {

		List<Customer> list = null;

		list = dao.findAllCustomer();

		return list;
	}

	@Override
	public List<Customer> findCustomerByName(String custName) {
		List<Customer> list = dao.findCustomerByName(custName);
		return list;
	}
}
