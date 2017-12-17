package caotinging.dao;

import caotinging.entity.Customer;

public interface CustomerDao {

	/**
	 * 通过customer id获取customer实体
	 * @param id
	 * @return
	 */
	public Customer getCustById(Long id);
}
