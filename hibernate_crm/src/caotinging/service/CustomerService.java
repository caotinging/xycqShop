package caotinging.service;

import java.util.List;

import caotinging.domain.Customer;

public abstract interface CustomerService {
	
	/**
	 * 新增一个客户
	 * @param paramCustomer
	 */
	public abstract void addCustomer(Customer paramCustomer);

	/**
	 * 获得所有客户列表
	 * @return
	 */
	public abstract List<Customer> findAllCustomer();

	/**
	 * 根据名字对客户进行模糊查询
	 * @param custName
	 * @return
	 */
	public abstract List<Customer> findCustomerByName(String custName);
}
