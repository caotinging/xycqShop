package caotinging.service;

import java.sql.SQLException;

import caotinging.dao.UserDao;
import caotinging.domain.User;

public class UserService {

	private final UserDao dao = new UserDao();
	
	/**
	 * 存储用户注册信息
	 * @param user 用户信息
	 * @return 执行结果
	 */
	public boolean register(User user) {
		int res = 0;
		try {
			res = dao.register(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res>0?true:false;
	}

	/**
	 * 账户激活
	 * @param code 账户激活码
	 * @return 执行结果
	 */
	public boolean setState(String code) {
		int res = 0;
		try {
			res = dao.setState(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res>0? true:false;
	}

	/**
	 * 检验用户名是否存在
	 * @param username 用户名
	 * @return 查询结果
	 */
	public boolean checkUserName(String username) {
		Long res = 0L;
		try {
			res = dao.checkUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res>0? true:false;
	}

	/**
	 * 根据用户名和密码验证用户是否存在
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkUserLogin(String username, String password) {
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.checkUserLogin(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
