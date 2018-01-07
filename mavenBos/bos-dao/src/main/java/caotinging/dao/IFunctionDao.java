package caotinging.dao;

import java.util.List;

import caotinging.dao.base.IBaseDao;
import caotinging.domain.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	/**
	 * 查询父功能点作为树结构
	 * @return
	 */
	List<Function> findAllAsTree();

	/**
	 * 通过用户id获取用户的所有权限
	 * @param id
	 * @return
	 */
	List<Function> findFunctionsByUserId(String id);

	/**
	 * 获取所有显示的菜单
	 * @return
	 */
	List<Function> findAllMenu();

	/**
	 * 获取当前登录用户的权限菜单
	 * @param id
	 * @return
	 */
	List<Function> findMenuByUserId(String id);

}
