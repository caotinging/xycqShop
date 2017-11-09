package caotinging.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import caotinging.dao.LinkManDao;
import caotinging.domain.Customer;
import caotinging.domain.LinkMan;
import caotinging.utils.HibernateUtils;

public class LinkManDaoImpl implements LinkManDao {

	@Override
	public void addLinkMan(LinkMan linkman, Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		
		linkman.setCustomer(customer);
		customer.getLinkManSet().add(linkman);
		
		session.saveOrUpdate(linkman);
	}

	@Override
	public List<LinkMan> findAllLinkMan() {
		Session session = HibernateUtils.getCurrentSession();
		Criteria criteria = session.createCriteria(LinkMan.class);
		
		@SuppressWarnings("unchecked")
		List<LinkMan> list = criteria.list();
		
		return list;
	}

	@Override
	public List<LinkMan> findLinkManByName(DetachedCriteria criteria) {
		Session session = HibernateUtils.getCurrentSession();
		
		Criteria c = criteria.getExecutableCriteria(session);
		@SuppressWarnings("unchecked")
		List<LinkMan> list = c.list();
		
		return list;
	}

}
