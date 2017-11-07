package caotinging.service.impl;

import java.util.List;

import org.hibernate.Transaction;

import caotinging.dao.CustomerDao;
import caotinging.dao.impl.CustomerDaoImpl;
import caotinging.domain.Customer;
import caotinging.service.CustomerService;
import caotinging.utils.HibernateUtils;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao dao = new CustomerDaoImpl();

	public void addCustomer(Customer customer) {
		this.dao.addCustomer(customer);
	}

	@Override
	public List<Customer> findAllCustomer() {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		
		List<Customer> list = null;
		try {
			list = dao.findAllCustomer();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		transaction.commit();
		return list;
	}
}
