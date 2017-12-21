package caotinging.service;

import caotinging.domain.User;

public interface IUserService {

	/**
	 * 检验用户是否存在
	 * @param user
	 * @return
	 */
	User checkUser(User user);

	
}
