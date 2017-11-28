package caotinging.service;

import caotinging.domain.LinkMan;

public interface LinkManService {

	/**
	 * 保存或修改linkMan信息
	 * @param linkMan
	 * @return 
	 */
	Boolean saveOrUpdate(LinkMan linkMan);

}
