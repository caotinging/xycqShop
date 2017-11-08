package caotinging.service.impl;

import org.hibernate.Transaction;

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
		boolean flag = true;
		Transaction transaction = HibernateUtils.startTransaction();
		
		try {
			CustomerDao customerDao = new CustomerDaoImpl();
			Customer customer = customerDao.getCustomerById(cust_id);
			
			dao.addLinkMan(linkman, customer);
		} catch (Exception e) {
			flag = false;
			transaction.rollback();
			e.printStackTrace();
		}
		transaction.commit();
		return flag;
	}

}
