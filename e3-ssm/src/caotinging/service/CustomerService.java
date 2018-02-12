package caotinging.service;

import caotinging.pojo.Customer;
import caotinging.tools.Page;
import caotinging.tools.QueryVo;

public interface CustomerService {

	/**
	 * 根据查询条件查询每页显示的客户列表
	 * @param vo
	 * @return
	 */
	public Page<Customer> findCustList(QueryVo vo);
}
