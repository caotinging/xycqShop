package caotinging.mapper;

import java.util.List;

import caotinging.pojo.Customer;
import caotinging.tools.QueryVo;

public interface CustMapper {

	/**
	 * 获取客户记录的总条数
	 * @return
	 */
	public Integer findCustListCount(QueryVo vo);
	
	/**
	 * 获取指定的客户记录信息
	 * @param vo
	 * @return
	 */
	public List<Customer> findCustList(QueryVo vo);

	/**
	 * 根据客户id获取客户信息
	 * @param id
	 * @return
	 */
	public Customer findCustById(Integer id);

	/**
	 * 更新客户信息
	 * @param customer
	 */
	public void updateCustomer(Customer customer);

	/**
	 * 删除客户信息
	 * @param id
	 */
	public void delCustomer(Integer id);
}
