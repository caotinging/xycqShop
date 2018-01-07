package caotinging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IUserDao;
import caotinging.domain.Role;
import caotinging.domain.User;
import caotinging.service.IUserService;
import caotinging.utils.MD5Utils;
import caotinging.utils.PageBean;

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

	@SuppressWarnings("unchecked")
	@Override
	public void addUser(User model, String[] rolesId) {
		//维护用户和角色的关系
		for (String roleId : rolesId) {
			Role role = new Role(roleId);
			model.getRoles().add(role);
		}
		//对密码进行加密
		model.setPassword(MD5Utils.md5(model.getPassword()));
		
		userDao.save(model);
	}

	@Override
	public void pageQuery(PageBean<User> pageBean) {
		userDao.queryPageBeanList(pageBean);
	}

}
