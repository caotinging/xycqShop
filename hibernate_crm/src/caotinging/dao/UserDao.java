package caotinging.dao;

import caotinging.domain.User;

public interface UserDao {

	/**
	 * 通过用户名获取用户信息
	 * @param user_name
	 * @return
	 */
	User findUserByName(String user_name);

}
