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
}
