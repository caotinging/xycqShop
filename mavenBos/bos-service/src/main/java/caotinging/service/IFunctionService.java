package caotinging.service;

import java.util.List;

import caotinging.domain.Function;
import caotinging.utils.PageBean;

public interface IFunctionService {

	/**
	 * 查询所有权限数据
	 * @return
	 */
	List<Function> findAllAsTree();

	/**
	 * 保存一个新的权限数据
	 * @param model
	 */
	void save(Function model);

	/**
	 * 权限分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean<Function> pageBean);

}
