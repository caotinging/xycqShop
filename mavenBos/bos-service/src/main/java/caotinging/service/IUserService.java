package caotinging.service;

import caotinging.domain.User;
import caotinging.utils.PageBean;

public interface IUserService {

	/**
	 * 检验用户是否存在
	 * @param user
	 * @return
	 */
	User checkUser(User user);

	/**
	 * 用户修改密码的操作
	 * @param loginUser
	 * @param newPassword
	 */
	void modifyPassword(User loginUser, String newPassword);

	/**
	 * 新增一个用户
	 * @param model
	 * @param rolesId
	 */
	void addUser(User model, String[] rolesId);

	/**
	 * 获取用户列表
	 * @param pageBean
	 */
	void pageQuery(PageBean<User> pageBean);

	
}
