package caotinging.service.impl;

import java.util.List;

import org.hibernate.Transaction;

import caotinging.dao.CustomerDao;
import caotinging.domain.Customer;
import caotinging.service.CustomerService;
import caotinging.utils.HibernateUtils;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public void addCustomer(Customer customer) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		this.customerDao.addCustomer(customer);
		transaction.commit();
	}

	@Override
	public List<Customer> findAllCustomer() {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		List<Customer> list = null;

		list = customerDao.findAllCustomer();
		transaction.commit();
		return list;
		
	}

	@Override
	public List<Customer> findCustomerByName(String custName) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		List<Customer> list = customerDao.findCustomerByName(custName);
		transaction.commit();
		return list;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
