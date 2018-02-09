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
}
