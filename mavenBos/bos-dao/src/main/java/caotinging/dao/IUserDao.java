package caotinging.dao;

import caotinging.dao.base.IBaseDao;
import caotinging.domain.User;

public interface IUserDao extends IBaseDao<User> {

	/**
	 * 通过用户名和密码查询用户
	 * @param username
	 * @param md5
	 * @return
	 */
	User getUserByNameAndPassword(String username, String md5);
	
}
