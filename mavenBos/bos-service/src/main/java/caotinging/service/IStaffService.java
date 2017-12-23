package caotinging.service;

import caotinging.domain.Staff;
import caotinging.utils.PageBean;

public interface IStaffService {

	/**
	 * 保存收派员信息
	 * @param staff
	 */
	void saveStaff(Staff staff);

	/**
	 * 获取staff的分页数据
	 * @param pageBean
	 */
	void getList(PageBean<Staff> pageBean);

}
