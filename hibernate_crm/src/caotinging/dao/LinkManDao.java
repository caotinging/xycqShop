package caotinging.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.domain.Customer;
import caotinging.domain.LinkMan;

public interface LinkManDao {

	/**
	 * 新增一个联系人
	 * @param linkman
	 * @param customer
	 */
	void addLinkMan(LinkMan linkman, Customer customer);

	/**
	 * 获取所有联系人
	 * @return
	 */
	List<LinkMan> findAllLinkMan();

	/**
	 * 执行指定QBC语句
	 * @param criteria
	 * @return
	 */
	List<LinkMan> findLinkManByName(DetachedCriteria criteria);

}
