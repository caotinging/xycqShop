package caotinging.dao.impl;

import caotinging.dao.CustomerDao;
import caotinging.domain.Customer;
import caotinging.utils.HibernateUtils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDaoImpl implements CustomerDao {
	public void addCustomer(Customer customer) {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		session.save(customer);

		transaction.commit();
		session.close();
	}

	@Override
	public List<Customer> findAllCustomer() {
		Session session = HibernateUtils.getCurrentSession();
		Criteria criteria = session.createCriteria(Customer.class);
		@SuppressWarnings("unchecked")
		List<Customer> list = criteria.list();

		return list;
	}

	@Override
	public Customer getCustomerById(Long cust_id) {
		Session session = HibernateUtils.getCurrentSession();
		Customer customer = session.get(Customer.class, cust_id);

		return customer;
	}

	@Override
	public List<Customer> findCustomerByName(String custName) {
		Session session = HibernateUtils.getCurrentSession();
		String hql = "from Customer where cust_name like ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, "%" + custName + "%");
		@SuppressWarnings("unchecked")
		List<Customer> list = query.list();
		return list;
	}
}