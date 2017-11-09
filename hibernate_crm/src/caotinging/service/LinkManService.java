package caotinging.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.domain.LinkMan;

public interface LinkManService {

	/**
	 * 添加联系人方法
	 * @param linkman
	 * @param cust_id
	 * @return 
	 */
	boolean addLinkMan(LinkMan linkman, Long cust_id);

	/**
	 * 获取所有联系人
	 * @return
	 */
	List<LinkMan> findAllLinkMan();

	/**
	 * 执行离线QBC
	 * @param criteria
	 * @return
	 */
	List<LinkMan> findLinkManByName(DetachedCriteria criteria);

}
