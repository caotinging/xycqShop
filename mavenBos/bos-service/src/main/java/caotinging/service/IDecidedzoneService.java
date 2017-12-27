package caotinging.service;

import java.util.List;

import caotinging.domain.Staff;
import caotinging.domain.Subarea;

public interface IDecidedzoneService {

	/**
	 * 获取获取没有作废的取派员的信息
	 * @return
	 */
	List<Staff> getNDelStaff();

	/**
	 * 获取还没有分配定区的分区集合
	 * @return
	 */
	List<Subarea> getNDidSubarea();
	
}
