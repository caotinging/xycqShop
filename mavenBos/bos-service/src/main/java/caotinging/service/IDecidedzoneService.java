package caotinging.service;

import java.util.List;

import caotinging.domain.Staff;

public interface IDecidedzoneService {

	/**
	 * 获取获取没有作废的取派员的信息
	 * @return
	 */
	List<Staff> getNDelStaff();
	
}
