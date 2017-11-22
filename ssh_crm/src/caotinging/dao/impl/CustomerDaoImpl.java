package caotinging.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import caotinging.dao.CustomerDao;
import caotinging.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public Integer getTotalCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		//清除criteria的聚合查询条件
		criteria.setProjection(null);
		if(list != null && list.size()>0) {
			return list.get(0).intValue();
		} else {
			return null;
		}
	}

	@Override
	public List<Customer> getCustomerList(DetachedCriteria criteria, Integer start, Integer pageCount) {
		@SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, start, pageCount);
		
		if(list != null && list.size() >0) {
			return list;
		} else {
			return null;
		}
	}

}
