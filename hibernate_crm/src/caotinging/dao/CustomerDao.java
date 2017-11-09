package caotinging.dao;

import java.util.List;

import caotinging.domain.Customer;

public abstract interface CustomerDao {
	
	/**
	 * 新增一个客户信息
	 * @param paramCustomer
	 */
	public abstract void addCustomer(Customer paramCustomer);

	/**
	 * 获取所有客户信息
	 * @return
	 */
	public abstract List<Customer> findAllCustomer();

	/**
	 * 通过客户id获取客户对象
	 * @param cust_id
	 * @return
	 */
	public abstract Customer getCustomerById(Long cust_id);

	/**
	 * 根据名字对数据库中客户进行模糊查询
	 * @param custName
	 * @return
	 */
	public abstract List<Customer> findCustomerByName(String custName);
}
