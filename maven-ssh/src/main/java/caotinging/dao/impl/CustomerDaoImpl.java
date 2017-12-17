package caotinging.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import caotinging.dao.CustomerDao;
import caotinging.entity.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	//注入sessionFactory
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	@Override
	public Customer getCustById(Long id) {
		return super.getHibernateTemplate().get(Customer.class, id);
	}

}
