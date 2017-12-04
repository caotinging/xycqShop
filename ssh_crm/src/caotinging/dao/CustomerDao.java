package caotinging.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.domain.Customer;

public interface CustomerDao extends BaseDao<Customer> {

	/**
	 * 获取给定查询条件下的总条数
	 * @param criteria
	 * @return
	 */
	Integer getTotalCount(DetachedCriteria criteria);

	/**
	 * 查询给定条件和数据偏移量的指定数据
	 * @param criteria 查询条件
	 * @param start 起始索引
	 * @param pageCount 数据查询总量
	 * @return 符合条件的customer集合
	 */
	List<Customer> getCustomerList(DetachedCriteria criteria, Integer start, Integer pageCount);

	/**
	 * 获取客户行业和数量的统计信息
	 * @return
	 */
	List<Object[]> getCustIndustryCount();

	/**
	 * 统计客户来源
	 * @return
	 */
	List<Object[]> getCustSourceCount();
}
