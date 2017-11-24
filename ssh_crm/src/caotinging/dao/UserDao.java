package caotinging.dao;

import caotinging.domain.User;

public interface UserDao extends BaseDao<User> {
	
	/**
	 * 通过用户名获取用户信息
	 * @param user_code
	 * @return
	 */
	public User getUserByCode(String user_code);
}
