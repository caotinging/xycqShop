package caotinging.dao;

import caotinging.domain.Customer;
import caotinging.domain.LinkMan;

public interface LinkManDao {

	/**
	 * 新增一个联系人
	 * @param linkman
	 * @param customer
	 */
	void addLinkMan(LinkMan linkman, Customer customer);

}
