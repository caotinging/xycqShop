package caotinging.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.dao.CustomerDao;
import caotinging.dao.LinkManDao;
import caotinging.dao.impl.CustomerDaoImpl;
import caotinging.dao.impl.LinkManDaoImpl;
import caotinging.domain.Customer;
import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;

public class LinkManServiceImpl implements LinkManService {

	private LinkManDao dao = new LinkManDaoImpl();

	@Override
	public boolean addLinkMan(LinkMan linkman, Long cust_id) {

		CustomerDao customerDao = new CustomerDaoImpl();
		Customer customer = customerDao.getCustomerById(cust_id);

		dao.addLinkMan(linkman, customer);
		return true;
	}

	@Override
	public List<LinkMan> findAllLinkMan() {
		List<LinkMan> list = dao.findAllLinkMan();
		return list;
	}

	@Override
	public List<LinkMan> findLinkManByName(DetachedCriteria criteria) {
		List<LinkMan> list = dao.findLinkManByName(criteria);
		return list;
	}

}
