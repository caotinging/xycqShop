package caotinging.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import caotinging.beans.PageBean;
import caotinging.dao.CustomerDao;
import caotinging.domain.Customer;
import caotinging.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public PageBean<Customer> getPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageCount) {
		//调用dao层方法查询符合条件的总条数
		Integer totalCount = customerDao.getTotalCount(criteria);
		
		PageBean<Customer> bean = new PageBean<Customer>(currentPage, pageCount, totalCount);
		
		//调用dao层方法查询当前页需要显示的数据
		List<Customer> customerList = customerDao.getCustomerList(criteria, bean.getStart(), bean.getPageCount());
		
		bean.setBeanList(customerList);
		return bean;
	}

	@Override
	public boolean saveOrUpdateCustomer(Customer customer) {
		Boolean res = customerDao.saveOrUpdate(customer);
		return res;
	}

	@Override
	public Customer findCustomer(Long cust_id) {
		Customer customer = customerDao.findById(cust_id);
		return customer;
	}

}
