package caotinging.service;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.beans.PageBean;
import caotinging.domain.Customer;

public interface CustomerService {

	/**
	 * 获取客户分页数据
	 * @param criteria 客户查询条件
	 * @param currentPage 当前页数
	 * @param pageCount 每页显示条数
	 * @return 当前页显示需要的数据
	 */
	PageBean<Customer> getPageBean(DetachedCriteria criteria, Integer currentPage, Integer pageCount);

	/**
	 * 保存或修改客户的信息
	 * @param customer
	 * @return
	 */
	boolean saveOrUpdateCustomer(Customer customer);

	/**
	 * 根据id查询customer
	 * @param cust_id
	 * @return
	 */
	Customer findCustomer(Long cust_id);

}
