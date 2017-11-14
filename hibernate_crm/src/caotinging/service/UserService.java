package caotinging.service;

import caotinging.domain.User;

public interface UserService {

	/**
	 * 验证用户登录信息
	 * @param user
	 * @return
	 */
	User login(User user);

}
