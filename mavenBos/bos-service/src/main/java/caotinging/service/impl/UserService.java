package caotinging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IUserDao;
import caotinging.domain.User;
import caotinging.service.IUserService;
import caotinging.utils.MD5Utils;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User checkUser(User user) {
		User existU = userDao.getUserByNameAndPassword(user.getUsername(),MD5Utils.md5(user.getPassword()));
		return existU;
	}

	@Override
	public void modifyPassword(User loginUser, String newPassword) {
		userDao.executeUpdate("user.updatePassword", MD5Utils.md5(newPassword), loginUser.getId());
	}

}
