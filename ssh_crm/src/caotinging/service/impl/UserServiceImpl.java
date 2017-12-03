package caotinging.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.beans.PageBean;
import caotinging.dao.UserDao;
import caotinging.domain.User;
import caotinging.service.UserService;
import cn.itcast.estore.utils.MD5Utils;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public PageBean<User> getUserList(DetachedCriteria criteria, Integer curr_Page, Integer page_Count) {
		Long count = userDao.getCount(criteria);
		if(count != 0) {
			
			PageBean<User> pageBean = new PageBean<User>(curr_Page, page_Count, count.intValue());
			List<User> list = userDao.getList(criteria, pageBean.getStart(), pageBean.getPageCount());
			pageBean.setBeanList(list);
			return pageBean;
		}
		else
			return null;
	}
	
	@Override
	public User getUserByCodePassword(User u) {
		User user = userDao.getUserByCode(u.getUser_code());
		
		if(user == null) {
			throw new RuntimeException("用户名不存在！");
		} else if(!user.getUser_password().equals(MD5Utils.md5(u.getUser_password()))) {
			throw new RuntimeException("密码输入错误！");
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		User existU = userDao.getUserByCode(user.getUser_code());
		
		if(existU != null) {
			throw new RuntimeException("用户名已存在！");
		}
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		userDao.save(user);
	}

}
