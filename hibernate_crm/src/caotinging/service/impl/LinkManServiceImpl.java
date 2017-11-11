package caotinging.service.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import caotinging.dao.CustomerDao;
import caotinging.dao.LinkManDao;
import caotinging.dao.impl.CustomerDaoImpl;
import caotinging.dao.impl.LinkManDaoImpl;
import caotinging.domain.Customer;
import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;
import caotinging.utils.HibernateUtils;

public class LinkManServiceImpl implements LinkManService {

	private LinkManDao dao = new LinkManDaoImpl();

	@Override
	public boolean addLinkMan(LinkMan linkman, Long cust_id) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		CustomerDao customerDao = new CustomerDaoImpl();
		Customer customer = customerDao.getCustomerById(cust_id);

		dao.addLinkMan(linkman, customer);
		transaction.commit();
		return true;
	}

	@Override
	public List<LinkMan> findAllLinkMan() {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		List<LinkMan> list = dao.findAllLinkMan();
		transaction.commit();
		return list;
	}

	@Override
	public List<LinkMan> findLinkManByName(DetachedCriteria criteria) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		List<LinkMan> list = dao.findLinkManByName(criteria);
		transaction.commit();
		return list;
	}

}
