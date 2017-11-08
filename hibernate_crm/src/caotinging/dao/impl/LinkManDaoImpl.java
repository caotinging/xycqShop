package caotinging.dao.impl;

import org.hibernate.Session;

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

}
