package caotinging.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.dao.CustomerDao;
import caotinging.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public Integer getTotalCount(DetachedCriteria criteria) {
		Long count = super.getCount(criteria);
		return count.intValue();
	}

	@Override
	public List<Customer> getCustomerList(DetachedCriteria criteria, Integer start, Integer pageCount) {
		List<Customer> list = super.getList(criteria, start, pageCount);
		return list;
	}

}
