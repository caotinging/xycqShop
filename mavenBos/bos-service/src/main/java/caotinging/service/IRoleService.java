package caotinging.service;

import java.util.List;

import caotinging.domain.Role;
import caotinging.utils.PageBean;

public interface IRoleService {

	void save(Role model, String functionIds);

	/**
	 * 获取role列表对象
	 * @param pageBean
	 */
	void pageQuery(PageBean<Role> pageBean);

}
