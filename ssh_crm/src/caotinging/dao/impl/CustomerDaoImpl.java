package caotinging.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;

import caotinging.dao.CustomerDao;
import caotinging.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	@Override
	public Integer getTotalCount(DetachedCriteria criteria) {
		Long count = super.getCount(criteria);
		return count.intValue();
	}
	
	@Override
	public List<Object[]> getCustIndustryCount() {
		List<Object[]> list = super.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			String sql=" SELECT "+
							" d.dict_item_name, "+
							" COUNT(*) total "+
						" FROM "+
							" base_dict d, "+
							" cst_customer c "+
						" WHERE "+
							" d.dict_id = c.cust_industry "+
						" GROUP BY "+
							" c.cust_industry ";
			
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				@SuppressWarnings("unchecked")
				List<Object[]> list = query.list();
				return list;
			}
		});
		
		return list;
	}
	
	@Override
	public List<Customer> getCustomerList(DetachedCriteria criteria, Integer start, Integer pageCount) {
		List<Customer> list = super.getList(criteria, start, pageCount);
		return list;
	}

	@Override
	public List<Object[]> getCustSourceCount() {
		List<Object[]> list = super.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			String sql=" SELECT "+
							" d.dict_item_name, "+
							" COUNT(*) total "+
						" FROM "+
							" base_dict d, "+
							" cst_customer c "+
						" WHERE "+
							" d.dict_id = c.cust_source "+
						" GROUP BY "+
							" c.cust_industry ";
			
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				@SuppressWarnings("unchecked")
				List<Object[]> list = query.list();
				return list;
			}
		});
		
		return list;
	}

}
