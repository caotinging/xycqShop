package caotinging.service.impl;

import caotinging.dao.UserDao;
import caotinging.domain.User;
import caotinging.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByCodePassword(User u) {
		User user = userDao.getUserByCode(u.getUser_code());
		
		if(user == null) {
			throw new RuntimeException("用户名不存在！");
		} else if(!user.getUser_password().equals(u.getUser_password())) {
			throw new RuntimeException("密码输入错误！");
		}
		return user;
	}

}
