package caotinging.dao;

import java.util.List;

import caotinging.dao.base.IBaseDao;
import caotinging.domain.Staff;

public interface IStaffDao extends IBaseDao<Staff> {

	/**
	 * 获取没有被删除的staff集合
	 * @return
	 */
	List<Staff> getNDelStaff();

}
