package caotinging.service;

import caotinging.domain.LinkMan;

public interface LinkManService {

	/**
	 * 添加联系人方法
	 * @param linkman
	 * @param cust_id
	 * @return 
	 */
	boolean addLinkMan(LinkMan linkman, Long cust_id);

}
