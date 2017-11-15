package caotinging.service.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import caotinging.dao.CustomerDao;
import caotinging.dao.LinkManDao;
import caotinging.domain.Customer;
import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;
import caotinging.utils.HibernateUtils;

public class LinkManServiceImpl implements LinkManService {

	private LinkManDao lmd;
	private CustomerDao cdao;

	@Override
	public boolean addLinkMan(LinkMan linkman, Long cust_id) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		Customer customer = cdao.getCustomerById(cust_id);

		lmd.addLinkMan(linkman, customer);
		transaction.commit();
		return true;
	}

	@Override
	public List<LinkMan> findAllLinkMan() {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		List<LinkMan> list = lmd.findAllLinkMan();
		transaction.commit();
		return list;
	}

	@Override
	public List<LinkMan> findLinkManByName(DetachedCriteria criteria) {
		Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
		List<LinkMan> list = lmd.findLinkManByName(criteria);
		transaction.commit();
		return list;
	}

	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}

	public void setCdao(CustomerDao cdao) {
		this.cdao = cdao;
	}

}
